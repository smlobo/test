<?php

echo "start supermarket_longan";

// mysqli_stmt_prepare failure
$con = mysqli_connect('127.0.0.1', 'root', 'Oracle@123', 'mysql');
$statement = mysqli_stmt_init($con);
mysqli_stmt_prepare ($statement, "select * from wrong_table;");
mysqli_stmt_execute($statement);
mysqli_close($con);

?>


