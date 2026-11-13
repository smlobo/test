<?php
echo nl2br("\nSample: real_connect succeed\n");
date_default_timezone_set('America/Los_Angeles');
$timezone = date_default_timezone_get();
$date = date('m/d/Y h:i:s a', time());
echo nl2br("The current server time is: " . $date. "\n\n");

$mysqli = new mysqli();
echo nl2br("$mysqli->mysqli() Errno: " . $mysqli->errno . "\n");
echo nl2br("$mysqli->mysqli() Error: " . $mysqli->error . "\n");

$mysqli->real_connect('127.0.0.1', 'root', 'Oracle@123', 'mysql');
echo nl2br("$mysqli->connect() Errno: " . mysqli_connect_errno() . "\n");
echo nl2br("$mysqli->connect() Error: " . mysqli_connect_error() . "\n");

$sql = "select a.table_name, a.table_comment from information_schema.tables a, information_schema.tables b, information_schema.tables c, information_schema.tables d  where a.table_comment <> '' limit 3;";
$result = $mysqli->query($sql);
echo nl2br("$mysqli->query() Errno: " . $mysqli->errno . "\n");
echo nl2br("$mysqli->query() Error: " . $mysqli->error . "\n");
$res = $result->fetch_assoc();
echo nl2br("Sometimes I see " . $res['table_name'] . " " . $res['table_comment'] . " on TV.\n");
echo nl2br("$mysqli_result->fetch_assoc() Errno: " . $mysqli->errno . "\n");
echo nl2br("$mysqli_result->fetch_assoc() Error: " . $mysqli->error . "\n");

$mysqli->close();
?>
