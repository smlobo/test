
<?php
$ch = curl_init(); 

curl_setopt($ch, CURLOPT_URL, "localhost/garden_rose.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/garden_lily.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/garden_carnation.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/garden_lotus.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/garden_tulip.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/garden_violet.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/garden_cactus.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/garden_orchid.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/garden_narcissus.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/garden_sunflower.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/garden_daisy.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/garden_jasmine.php"); 
$output = curl_exec($ch); 
echo $output;

curl_setopt($ch, CURLOPT_URL, "localhost/garden_ginkgo.php"); 
$output = curl_exec($ch); 
echo $output;

curl_close($ch);      

?>

