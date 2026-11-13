<?php

echo "start supermarket_hazel";

// mysqli_prepare failure
$con = mysqli_connect('127.0.0.1', 'root', 'Oracle@123', 'mysql');
$statement = mysqli_prepare($con, "select * from wrong_table;");
mysqli_stmt_execute($statement);
mysqli_close($con);

?>


