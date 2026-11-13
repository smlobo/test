<?php

echo "start supermarket_melon";

// mysqli_stmt_execute failure
$con = mysqli_connect('127.0.0.1', 'root', 'Oracle@123', 'mysql');
$statement = mysqli_stmt_init($con);
mysqli_stmt_prepare ($statement, "insert into wrong_table values ('mysqli_stmt_init + mysqli_stmt_prepare + mysqli_stmt_execute')");
mysqli_stmt_execute($statement);
mysqli_close($con);

?>


