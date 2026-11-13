<?php
$ch = curl_init(); 

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_novelclassic.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_novelmodern.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_novelurban.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_novelromance.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_noveldetective.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_novelhorror.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_noveladventure.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_novelsuspense.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_novelhistory.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_economicsmicro.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_economicsmacro.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_economicspolitical.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_economicstax.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_economicstrade.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_economicsinflation.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_economicsinsurance.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_computeros.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_computerdb.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_computernetwork.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_sportsnba.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/bookmart_sportsgo.php"); 
$output = curl_exec($ch); 
echo $output;

curl_close($ch);      

?>




