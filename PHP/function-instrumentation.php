<?php
  //ini_set('display_errors', 'On');
  //error_reporting(E_ALL);


  function foo() {
    echo "in foo<br>";
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

  echo "<br>Calling make_db_connection() 1<br>";
  make_db_connection();
  echo "<br>Calling foo() first<br>";
  foo();
  echo "<br>Calling make_db_connection() 2<br>";
  make_db_connection();
  echo "<br>Calling foo() again<br>";
  foo();
?>