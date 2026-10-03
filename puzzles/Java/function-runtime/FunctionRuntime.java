import java.lang.System;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Stack;

public class FunctionRuntime {
    public static void main(String[] args) {
        int n = 2;
        LinkedList<String> logs = new LinkedList<>();

        /*logs.add("0:start:0");
        logs.add("1:start:2");
        logs.add("1:end:5");
        logs.add("0:end:6");*/

        logs.add("0:start:0");
        logs.add("1:start:2");
        logs.add("1:end:5");
        logs.add("1:start:7");
        logs.add("1:end:9");
        logs.add("0:end:11");

        /*logs.add("0:start:0");
        logs.add("0:start:2");
        logs.add("0:end:5");
        logs.add("0:start:6");
        logs.add("0:end:6");
        logs.add("0:end:7");*/
 
        ListIterator<String> li = logs.listIterator();
        while (li.hasNext()) {
            String x = li.next();
            System.out.println(x);
        }

        // Solution
        int[] ftimes = new int[n];
        Stack<Integer> callstack = new Stack<>();
        ListIterator<String> liter = logs.listIterator();

        int ptime = -1;
        callstack.push(-1);
        String pevent = "";
        
        while (liter.hasNext()) {
            String x = liter.next();
            String[] parts = x.split(":");
            int cfunc = Integer.parseInt(parts[0]);
            String cevent = parts[1];
            int ctime = Integer.parseInt(parts[2]);
            //System.out.println(cfunc + " " + ctime + " " + cevent);

            int elapsed = 0;
            if (ptime != -1)
                elapsed = ctime - ptime;
            ptime = ctime;

            // Adjust
            if (pevent.equals("start") &&
                cevent.equals("end"))
                elapsed++;
            else if (pevent.equals("end") &&
                     cevent.equals("start"))
                elapsed--;

            int credit = -1;
            if (cevent.equals("start"))
                credit = callstack.peek();
            else if (cevent.equals("end"))
                credit = cfunc;
            else
                assert false;

            if (cevent.equals("start"))
                callstack.push(cfunc);
            else
                callstack.pop();
            pevent = cevent;

            if (credit != -1)
                ftimes[credit] += elapsed;
            //System.out.println("credit: " + credit + " of: " + elapsed);
        }

        System.out.print("[");
        for (int i = 0; i < n-1; i++) {
            System.out.print(ftimes[i] + ", ");
        }
        System.out.println(ftimes[n-1] + "]");
    }
}
