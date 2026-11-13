<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Functions</title>
  </head>
  <body>
    <div class="header"><h1>Functions</h1></div>
    <br>
    <?php
      function aPlusBMinusC($a = 10, $b = 20, $c = 5) {
        return $a + $b - $c;
      }

      echo "Default function : " . aPlusBMinusC() . "<br>";
      echo "7 + 8 - default : " . aPlusBMinusC(7, 8) . "<br>";
      echo "15 ... default : " . aPlusBMinusC(15) . "<br>";
      echo "7 + 8 - 2 : " . aPlusBMinusC(7, 8, 2) . "<br>";
    ?>
  </body>
</html>
