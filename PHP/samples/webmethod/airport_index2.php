<?php
$ch = curl_init(); 

curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1); 

curl_setopt($ch, CURLOPT_URL, "localhost/airport_PVG2.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_SHA2.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_DEL2.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_JFK2.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_LHR2.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_MUC2.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_CDG2.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_ZRH2.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_BCN2.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_ATH2.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_SYD2.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_WLG2.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_YOW2.php"); 
$output = curl_exec($ch); 
echo $output;

curl_close($ch);      
?>

