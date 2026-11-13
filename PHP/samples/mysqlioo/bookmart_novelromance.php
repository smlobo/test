<?php
echo nl2br("\nSample: connect fail\n");
date_default_timezone_set('America/Los_Angeles');
$timezone = date_default_timezone_get();
$date = date('m/d/Y h:i:s a', time());
echo nl2br("The current server time is: " . $date. "\n\n");

$mysqli = new mysqli();
echo nl2br("$mysqli->mysqli() Errno: " . $mysqli->errno . "\n");
echo nl2br("$mysqli->mysqli() Error: " . $mysqli->error . "\n");

$mysqli->connect('127.0.0.1', 'root', 'wrongpw', 'mysql');
echo nl2br("$mysqli->connect() Errno: " . mysqli_connect_errno() . "\n");
echo nl2br("$mysqli->connect() Error: " . mysqli_connect_error() . "\n");

$mysqli->close();
?>
