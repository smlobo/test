<?php 
echo 'airport_LHR2<br>';
$ch = curl_init(); 
$fields = array("id" => 1);
$options = array(CURLOPT_URL => 'localhost/airport_SFO.php',
                 CURLOPT_RETURNTRANSFER => 1,
                 CURLOPT_CUSTOMREQUEST => "PUT",
                 CURLOPT_POSTFIELDS => http_build_query($fields)
                );
curl_setopt_array($ch, $options);
$output = curl_exec($ch); 
echo $output;
curl_close($ch);
?>

