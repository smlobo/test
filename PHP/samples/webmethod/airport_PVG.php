<?php 
echo 'airport_PVG<br>';
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/airport_SFO.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1); 
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "OPTIONS");
$output = curl_exec($ch); 
echo $output;
curl_close($ch);
?>

