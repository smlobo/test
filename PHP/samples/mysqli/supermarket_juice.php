<?php

echo "start supermarket_juice";

// mysqli_stmt_init failure
$con = mysqli_connect('127.0.0.1', 'root', 'Oracle@123', 'mysql');
$statement = mysqli_stmt_init(null);
mysqli_close($con);

?>


