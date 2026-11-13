<?php

echo "start supermarket_cucumber";

// mysqli_multi_query failure
$con=mysqli_connect('127.0.0.1', 'root', 'Oracle@123', 'mysql');
mysqli_multi_query($con, "select * from wrong_table;");
mysqli_close($con);

?>


