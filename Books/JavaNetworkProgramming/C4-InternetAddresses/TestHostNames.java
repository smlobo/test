import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
public class TestHostNames
{
    /**
     * Enumerates all local IP addresses so we can match what a container
     * listening on 0.0.0.0 is actually accepting traffic for.
     * @param hostNames -- boolean, if true, return list of host names, otherwise, list of IP addresses
     * @param requireHostNames -- boolean, if true, only return addresses that have host names
     * @return a List of addresses (either IP address or hostnames)
     */
    public static List <String> getAllLocalIPInformation(boolean hostNames, boolean requireHostNames)
    {
        List<String> retList = new ArrayList <>();
        try
        {
            Enumeration <NetworkInterface> ifcs = NetworkInterface.getNetworkInterfaces();
            while (ifcs.hasMoreElements())
            {
                NetworkInterface ifc = ifcs.nextElement();
                Enumeration<InetAddress> ips = ifc.getInetAddresses();
                // several utils try to use the first entry to get a canonical hostname, sometimes that entry does
                // not have a host name associated with that ip, so try to put one with a host name first
                // see ContainerInformationBase
                boolean firstHasHostName = false;
                while (ips.hasMoreElements())
                {
                    InetAddress ip = ips.nextElement();
                    // TODO: not sure I fully understand why one would choose
                    // to use a link local address instead of a real IPv6
                    // address, but they don't really seem useful outside of
                    // a given machine, so I am going to exclude them for now
                    if (!ip.isLoopbackAddress() && !ip.isLinkLocalAddress())
                    {
                        String addr = ip.getHostAddress();
                        String hostName = ip.getCanonicalHostName();
                        boolean curHasHostName = !addr.equals(ip.getHostName()) && requireHostNames;
                        if(addr.contains("%")) {
                            addr = addr.split("%")[0];
                        }
                        if (hostNames)
                        {
                            if (curHasHostName)
                            {
                                retList.add(hostName);
                            }
                        }
                        else
                        {
                            if(!firstHasHostName && curHasHostName)
                            {
                                retList.add(0, addr);
                                firstHasHostName = true;
                            }
                            else
                            {
                                retList.add(addr);
                            }
                        }
                    }
                }
            }
        }
        catch (SocketException se)
        {
            // ignore
        }
        if (retList.size() == 0)
        {
            // Well, that didn't work. Try just getting IPs for localhost
            try
            {
                List<String> shortHostnameList = new ArrayList <>();
                InetAddress localhost = InetAddress.getLocalHost();
                InetAddress[] allIPs = InetAddress.getAllByName(localhost.getHostName());
                for(InetAddress allIP : allIPs)
                {
                    if(!allIP.isLoopbackAddress())
                        if(hostNames)
                        {
                            // Get both FQHN(hn1) and HN(hn2). If hn1 is not IP, add it to the list. If hn1 is indeed IP,
                            // then add hn2 to short hostname list if it is not IP.
                            // Later if the retList at the end is still empty, then hostnames from shartHostnameList
                            // is added to the retList just to make sure the return list is not empty.
                            // This is a fix for AP-19364.
                            String hn1 = allIP.getCanonicalHostName();
                            String hn2 = allIP.getHostName();
                            if(!allIP.getHostAddress().equals(hn1))
                            {
                                retList.add(hn1);
                            }
                            else if(!allIP.getHostAddress().equals(hn2))
                            {
                                shortHostnameList.add(hn2);
                            }
                        }
                        else
                        {
                            retList.add(allIP.getHostAddress());
                        }
                }
                if (retList.isEmpty())
                {
                    retList.addAll(shortHostnameList);
                }
            }
            catch (UnknownHostException ignored)
            {
            }
        }
        return retList;
    }
    /**
     * Look up host name for given ip address
     * @param ipAddress for which you want the host name
     * @return hostname (in fqdn form) if available, original input if not
     */
    public static String getHostForIPAddress(String ipAddress)
    {
        String retval = ipAddress;
        try
        {
            InetAddress target = InetAddress.getByName(ipAddress);
            retval = target.getCanonicalHostName();
        }
        catch (UnknownHostException uhe)
        {
            // Defaults to returning original input
        }
        return retval;
    }
    /**
     * Get the UDS (really monitoring service) compatible host name for this box
     *
     * @return String that represents the UDS/Monitoring Service compatible host name
     */
    public static String getUDSHostName()
    {
        List<String> hostNames = getAllLocalIPInformation(true, true);
        if (hostNames.size() == 0)
        {
            hostNames = getAllLocalIPInformation(false, true);  // If no real names, use the ip addresses but compare as text
        }
        String lowestHostName = "";
        if (hostNames.size() > 0)
        {
            lowestHostName = hostNames.get(0);
        }
        for (String thisName : hostNames)
        {
            if (thisName.compareToIgnoreCase(lowestHostName) < 0)
            {
                lowestHostName = thisName;
            }
        }
        return lowestHostName.toLowerCase();    // Some tests seem to produce upper case node names
        // Since node names by design are caseless, we'll
        // standardize on lowercase names.
    }
    public static void main (String [] args)
    {
        System.out.println("UDS Host = '" + getUDSHostName() + "'");
        List <String> allHosts = getAllLocalIPInformation(true, true);
        for(String currHost : allHosts)
        {
            System.out.println("Host = '" + currHost + "'");
        }
        List <String> allIps = getAllLocalIPInformation(false, true);
        for(String currIp : allIps)
        {
            System.out.print("IP = '" + currIp + "'");
            System.out.println(" for host = '" + getHostForIPAddress(currIp) + "'");
        }
    }
}