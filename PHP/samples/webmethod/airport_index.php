<?php
$ch = curl_init(); 

curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1); 

curl_setopt($ch, CURLOPT_URL, "localhost/airport_PVG.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_SHA.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_DEL.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_JFK.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_LHR.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_MUC.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_CDG.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_ZRH.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_BCN.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_ATH.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_SYD.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_WLG.php"); 
$output = curl_exec($ch); 
echo $output;
sleep(2);

curl_setopt($ch, CURLOPT_URL, "localhost/airport_YOW.php"); 
$output = curl_exec($ch); 
echo $output;

curl_close($ch);      
?>

