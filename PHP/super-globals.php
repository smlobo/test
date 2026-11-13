<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Super Globals</title>
  </head>
  <body>
    <div class="header"><h1>Super Globals</h1></div>
    <br>
    <?php
      // $GLOBALS
      $x = 10;
      $y = 20;

      function addition() {
        $GLOBALS['z'] = $GLOBALS['x'] + $GLOBALS['y'];
      }

      addition();
      echo "GLOBALS: $x + $y = $z<br>";

      // $_SERVER
      echo "_SERVER PHP_SELF: " . $_SERVER['PHP_SELF'] . "<br>";
      echo "_SERVER SERVER_NAME: " . $_SERVER['SERVER_NAME'] . "<br>";
      echo "_SERVER SERVER_SOFTWARE: " . $_SERVER['SERVER_SOFTWARE'] . "<br>";
      echo "_SERVER SERVER_SIGNATURE: " . $_SERVER['SERVER_SIGNATURE'] . "<br>";
      echo "_SERVER SERVER_PORT: " . $_SERVER['SERVER_PORT'] . "<br>";
      echo "_SERVER HTTP_HOST: " . $_SERVER['HTTP_HOST'] . "<br>";
      echo "_SERVER HTTP_USER_AGENT: " . $_SERVER['HTTP_USER_AGENT'] . "<br>";
      echo "_SERVER REMOTE_ADDR: " . $_SERVER['REMOTE_ADDR'] . "<br>";
      echo "_SERVER REMOTE_PORT: " . $_SERVER['REMOTE_PORT'] . "<br>";
      echo "_SERVER SCRIPT_NAME: " . $_SERVER['SCRIPT_NAME'] . "<br>";
      echo "_SERVER SCRIPT_URI: " . $_SERVER['SCRIPT_URI'] . "<br>";
      echo "_SERVER REQUEST_METHOD: " . $_SERVER['REQUEST_METHOD'] . "<br>";
    ?>
  </body>
</html>
