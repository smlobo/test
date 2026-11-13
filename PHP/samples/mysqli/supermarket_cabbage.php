<?php

echo "start supermarket_cabbage";

// mysqli_real_connect success, mysqli_multi_query success
$link = mysqli_init();
mysqli_real_connect($link, '127.0.0.1', 'root', 'Oracle@123', 'mysql');
mysqli_multi_query($link, "delete from mysql.mike; insert into mike values ('mysqli_multi_query')");
mysqli_close($link);

echo "<br/>calling page supermarket_mango.php<br/>";
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/supermarket_mango.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);  //return the transfer as a string 
$output = curl_exec($ch); 
echo $output;
curl_close($ch);      

echo "<br/>calling page supermarket_melon.php<br/>";
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/supermarket_melon.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
$output = curl_exec($ch); 
echo $output;
curl_close($ch);      

?>


