<?php

echo "page waimai_addtocart.php<br/>";

//
// call Oracle DB
//
$db = "(DESCRIPTION=(CONNECT_DATA=(SID=orcl))(ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCP)(HOST = pleej22.us.oracle.com)(PORT = 1521))))" ;
$conn = oci_connect('system', 'system', $db);
if ($conn) 
{
	echo "Successfully connected to Oracle.\n";
}
else
{
    $e = oci_error();
    trigger_error(htmlentities($e['message'], ENT_QUOTES), E_USER_ERROR);
}

// Prepare the statement
$stid = oci_parse($conn, 'select owner, table_name from all_tables where rownum < 5');
if (!$stid) {
    $e = oci_error($conn);
    trigger_error(htmlentities($e['message'], ENT_QUOTES), E_USER_ERROR);
}

// Perform the logic of the query
$r = oci_execute($stid);
if (!$r) {
    $e = oci_error($stid);
    trigger_error(htmlentities($e['message'], ENT_QUOTES), E_USER_ERROR);
}

// Fetch the results of the query
print "<table border='1'>\n";
while ($row = oci_fetch_array($stid, OCI_ASSOC+OCI_RETURN_NULLS)) {
    print "<tr>\n";
    foreach ($row as $item) {
        print "    <td>" . ($item !== null ? htmlentities($item, ENT_QUOTES) : "&nbsp;") . "</td>\n";
    }
    print "</tr>\n";
}
print "</table>\n";

oci_free_statement($stid);
oci_close($conn);

//
// call other pages
//
echo "<br/>calling page waimai_checkout.php<br/>";
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/waimai_checkout.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);  //return the transfer as a string 
$output = curl_exec($ch); 
echo $output;
curl_close($ch);      

?>


