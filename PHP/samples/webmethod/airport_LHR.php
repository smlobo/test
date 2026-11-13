<?php 
echo 'airport_LHR<br>';
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/airport_SFO.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1); 
curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "PUT");
$fields = array("id" => 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($fields));
$output = curl_exec($ch); 
echo $output;
curl_close($ch);
?>

