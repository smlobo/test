<?php

echo "start supermarket_filbert";

// mysqli_real_query failure
$con=mysqli_connect('127.0.0.1', 'root', 'Oracle@123', 'mysql');
mysqli_real_query ($con, "select * from wrong_table;");
mysqli_close($con);

?>


