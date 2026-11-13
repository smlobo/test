<?php
echo nl2br("\nSample: prepare fail\n");
date_default_timezone_set('America/Los_Angeles');
$timezone = date_default_timezone_get();
$date = date('m/d/Y h:i:s a', time());
echo nl2br("The current server time is: " . $date. "\n\n");

$mysqli = new mysqli('127.0.0.1', 'root', 'Oracle@123', 'mysql');

$sql = "select a.table_name, a.table_comment from information_schema.wrongs a, information_schema.tables b, information_schema.tables c, information_schema.tables d  where a.table_comment <> '' limit 3;";
$stmt = $mysqli->prepare($sql);
echo nl2br("$stmt->prepare() Errno: " . $mysqli->errno . "\n");
echo nl2br("$stmt->prepare() Error: " . $mysqli->error . "\n");

//$stmt->execute();
//echo nl2br("$stmt->execute() Errno: " . $mysqli->errno . "\n");
//echo nl2br("$stmt->execute() Error: " . $mysqli->error . "\n");

//$stmt->close();
$mysqli->close();
?>
