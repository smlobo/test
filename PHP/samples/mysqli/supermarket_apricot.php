<?php

echo "start supermarket_apricot";

// mysqli_real_connect failure
$link = mysqli_init();
mysqli_real_connect($link, '127.0.0.1', 'root', 'wrongPs', 'mysql');

echo "<br/>calling page supermarket_hazel.php<br/>";
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/supermarket_hazel.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);  //return the transfer as a string 
$output = curl_exec($ch); 
echo $output;
curl_close($ch);      

echo "<br/>calling page supermarket_honey.php<br/>";
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/supermarket_honey.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
$output = curl_exec($ch); 
echo $output;
curl_close($ch);      

?>


