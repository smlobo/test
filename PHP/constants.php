<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Constants</title>
  </head>
  <body>
    <div class="header"><h1>Constants</h1></div>
    <br>
    <?php
      define("HELLO", "Hello World :-)");
      echo "Global access of constant: " . HELLO . "<br>";

      function testConstant() {
        echo "Local access of constant: " . HELLO . "<br>";
      }
      testConstant();
    ?>
  </body>
</html>
