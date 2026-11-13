<?php

echo "start supermarket_apple";

// mysqli_real_connect success
$link = mysqli_init();
mysqli_real_connect($link, '127.0.0.1', 'root', 'Oracle@123', 'mysql');
mysqli_close($link);

echo "<br/>calling page supermarket_fig.php<br/>";
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/supermarket_fig.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);  //return the transfer as a string 
$output = curl_exec($ch); 
echo $output;
curl_close($ch);      

echo "<br/>calling page supermarket_filbert.php<br/>";
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/supermarket_filbert.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
$output = curl_exec($ch); 
echo $output;
curl_close($ch);      

?>


