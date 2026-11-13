<?php 
echo 'airport_JFK2<br>';
$ch = curl_init(); 
$options = array(CURLOPT_URL => 'localhost/airport_SFO.php',
                 CURLOPT_RETURNTRANSFER => 1,
                 CURLOPT_CUSTOMREQUEST => "CONNECT"
                );
curl_setopt_array($ch, $options);
$output = curl_exec($ch); 
echo $output;
curl_close($ch);
?>

