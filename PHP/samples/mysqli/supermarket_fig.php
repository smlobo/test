<?php

echo "start supermarket_fig";

// mysqli_real_query success
$con=mysqli_connect('127.0.0.1', 'root', 'Oracle@123', 'mysql');
mysqli_real_query ($con, "insert into mike values ('mysqli_real_query')");
mysqli_close($con);

?>


