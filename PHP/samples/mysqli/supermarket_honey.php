<?php

echo "start supermarket_honey";

// mysqli_prepare success
$con = mysqli_connect('127.0.0.1', 'root', 'Oracle@123', 'mysql');
$statement = mysqli_prepare($con, "insert into mike values ('mysqli_prepare + mysqli_stmt_execute')");
mysqli_stmt_execute($statement);
mysqli_close($con);

?>


