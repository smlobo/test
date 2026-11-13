<?php
echo nl2br("\nSample: real_query fail\n");
date_default_timezone_set('America/Los_Angeles');
$timezone = date_default_timezone_get();
$date = date('m/d/Y h:i:s a', time());
echo nl2br("The current server time is: " . $date. "\n\n");

$mysqli = new mysqli('127.0.0.1', 'root', 'Oracle@123', 'mysql');

$sql = "select a.table_name, a.table_comment from information_schema.wrongs a, information_schema.tables b, information_schema.tables c, information_schema.tables d  where a.table_comment <> '' limit 3;";
$result = $mysqli->real_query($sql);
echo nl2br("$mysqli->real_query() Errno: " . $mysqli->errno . "\n");
echo nl2br("$mysqli->real_query() Error: " . $mysqli->error . "\n");

$mysqli->close();
?>
