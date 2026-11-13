<?php

echo "page waimai_restaurants.php<br/>";

//
// call Oracle DB
//
$db = "(DESCRIPTION=(CONNECT_DATA=(SID=orcl))(ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCP)(HOST = pleej22.us.oracle.com)(PORT = 1521))))" ;
$conn = oci_connect('system', 'wrongps', $db);
if ($conn) 
{
	echo "Successfully connected to Oracle.\n";
}

oci_close($conn);

//
// call other pages
//
echo "<br/>calling page waimai_pickup.php<br/>";
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/waimai_pickup.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);  //return the transfer as a string 
$output = curl_exec($ch); 
echo $output;
curl_close($ch);      

echo "<br/>calling page waimai_addtocart.php<br/>";
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/waimai_addtocart.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);  //return the transfer as a string 
$output = curl_exec($ch); 
echo $output;
curl_close($ch);      

//
// print info
//
phpinfo();
print_r(ini_get_all(null, false));

?>


