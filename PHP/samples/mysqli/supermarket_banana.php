<?php

echo "start supermarket_banana";

// mysqli_query success
$con=mysqli_connect('127.0.0.1', 'root', 'Oracle@123', 'mysql');
$sql = "SELECT table_name, table_type, engine, TABLE_COMMENT FROM information_schema.tables Where TABLE_SCHEMA = 'mysql' limit 3;";
$result = mysqli_query($con,$sql);
while ($row = $result->fetch_assoc())
{
    foreach($row as $value) echo "<td>$value</td>";
}
mysqli_close($con);

echo "<br/>calling page supermarket_jam.php<br/>";
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/supermarket_jam.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);  //return the transfer as a string 
$output = curl_exec($ch); 
echo $output;
curl_close($ch);      

echo "<br/>calling page supermarket_juice.php<br/>";
$ch = curl_init(); 
curl_setopt($ch, CURLOPT_URL, "localhost/supermarket_juice.php"); 
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
$output = curl_exec($ch); 
echo $output;
curl_close($ch);      

?>


