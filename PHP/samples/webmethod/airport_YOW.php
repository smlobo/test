<?php 
echo 'airport_YOW<br>';
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/airport_SFO.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1); 
$output = curl_exec($ch); 
echo $output;
curl_close($ch);
?>

