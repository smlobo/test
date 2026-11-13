<?php

echo "start supermarket_agaric";

// connect success
$con=mysqli_connect('127.0.0.1', 'root', 'Oracle@123');
mysqli_close($con);

echo "<br/>calling page supermarket_banana.php<br/>";
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/supermarket_banana.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);  //return the transfer as a string 
$output = curl_exec($ch); 
echo $output;
curl_close($ch);      

echo "<br/>calling page supermarket_broccoli.php<br/>";
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/supermarket_broccoli.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
$output = curl_exec($ch); 
echo $output;
curl_close($ch);      

?>

