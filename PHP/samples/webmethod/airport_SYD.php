<?php 
echo 'airport_SYD<br>';
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/airport_SFO.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1); 
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "PUT");
$output = curl_exec($ch);
echo $output;
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");
curl_setopt($ch, CURLOPT_HTTPGET, true);
$output = curl_exec($ch); 
echo $output;
curl_close($ch);
?>

