<?php 
  session_start();
?>
<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Session B</title>
  </head>
  <body>
    <div class="header"><h1>Session B</h1></div>
    <br>
    <div class="menu">
      <?php
        include 'includeM.php';
      ?>
    </div>
    <?php
      if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $_SESSION["favColor"] = $_POST['favColor'];
        $_SESSION["favAnimal"] = $_POST['favAnimal'];
        echo "<br>";
        echo "Session variables set: " . $_SESSION["favColor"] . 
          "; " . $_SESSION["favAnimal"];
      }
    ?>
    <br><br>
    <a href="/PHP/sessionA.php">Back</a>
    <a href="/PHP/sessionC.php">Forward</a>
  </body>
</html>
