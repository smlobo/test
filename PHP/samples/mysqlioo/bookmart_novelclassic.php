<?php
echo nl2br("\nSample: __construct succeed - 4 parameters\n");
date_default_timezone_set('America/Los_Angeles');
$timezone = date_default_timezone_get();
$date = date('m/d/Y h:i:s a', time());
echo nl2br("The current server time is: " . $date. "\n\n");

$mysqli = new mysqli('127.0.0.1', 'root', 'Oracle@123', 'mysql');
echo nl2br("$mysqli->mysqli() Errno: " . $mysqli->errno . "\n");
echo nl2br("$mysqli->mysqli() Error: " . $mysqli->error . "\n");

$mysqli->close();
?>
