<?php

echo "start supermarket_almond";

// connect failure
$con=mysqli_connect($host=NULL, 'root', 'Oracle@123');

echo "<br/>calling page supermarket_cabbage.php<br/>";
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/supermarket_cabbage.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);  //return the transfer as a string 
$output = curl_exec($ch); 
echo $output;
curl_close($ch);      

echo "<br/>calling page supermarket_cucumber.php<br/>";
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/supermarket_cucumber.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
$output = curl_exec($ch); 
echo $output;
curl_close($ch);      

echo "<br/>calling page supermarket_coconut.php<br/>";
$ch = curl_init();
curl_setopt($ch, CURLOPT_URL, "localhost/supermarket_coconut.php");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
$output = curl_exec($ch);
echo $output;
curl_close($ch);


?>


