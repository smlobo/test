<?php
  function foo() {
    echo "Calling bar()<br>";
    bar();
  }

  function bar() {
    echo "Calling make_db_connection()<br>";
    make_db_connection();
  }

  function make_db_connection() {

    $servername = "localhost";
    $username = "smlobo";
    $password = "welcome";

    // Create connection
    echo "Calling mysqli_connect()<br>";
    $conn = mysqli_connect($servername, $username, $password);

    // Check connection
    if (!$conn) {
      die("Connection failed (p): " . mysqli_connect_error());
    }

    echo "Connected successfully<br>";
    mysqli_close($conn);
  }

  echo "<br>Calling foo()<br>";
  foo();
?>