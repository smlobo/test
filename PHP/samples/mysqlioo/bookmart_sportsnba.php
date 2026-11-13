<?php
echo nl2br("\nSample: stmt_init/stmt_prepare/stmt_execute succeed\n");
date_default_timezone_set('America/Los_Angeles');
$timezone = date_default_timezone_get();
$date = date('m/d/Y h:i:s a', time());
echo nl2br("The current server time is: " . $date. "\n\n");

$mysqli = new mysqli('127.0.0.1', 'root', 'Oracle@123', 'mysql');

$stmt = $mysqli->stmt_init();
$sql = "select a.table_name, a.table_comment from information_schema.tables a, information_schema.tables b, information_schema.tables c, information_schema.tables d  where a.table_comment <> '' limit 3;";

$stmt->prepare($sql);
echo nl2br("$stmt->prepare() Errno: " . $mysqli->errno . "\n");
echo nl2br("$stmt->prepare() Error: " . $mysqli->error . "\n");

$stmt->execute();
echo nl2br("$stmt->execute() Errno: " . $mysqli->errno . "\n");
echo nl2br("$stmt->execute() Error: " . $mysqli->error . "\n");

$stmt->bind_result($table_name, $comment);
$stmt->fetch();
echo nl2br("Query Result: " . $table_name . " " . $comment . "\n");
$stmt->fetch();
echo nl2br("Query Result: " . $table_name . " " . $comment . "\n");

$stmt->close();
$mysqli->close();
?>
