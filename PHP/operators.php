<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Operators</title>
  </head>
  <body>
    <div class="header"><h1>Operators</h1></div>
    <br>
    <?php
      $x = 25;
      $y = 3;
      
      // Arithmetic
      echo "<br><strong>Arithmetic</strong><br>";
      echo "$x + $y = " . ($x + $y) . "<br>";
      echo "$x - $y = " . ($x - $y) . "<br>";
      echo "$x * $y = " . ($x * $y) . "<br>";
      echo "$x / $y = " . ($x / $y) . "<br>";
      echo "$x % $y = " . ($x % $y) . "<br>";
      #echo "$y ** 4 = " . ($y ** 4) . "<br>"; // Need php 5.6

      // Assignment
      echo "<br><strong>Assignment</strong><br>";
      echo "$x *= $y results in \$x = " . ($x *= $y) . "<br>";

      // Comparison
      echo "<br><strong>Comparison</strong><br>";
      $x1 = "1";
      $x2 = 1;
      echo "\$x1 ($x1) == \$x2 ($x2) ? " . ($x1 == $x2) . "<br>";
      echo "\$x1 ($x1) !== \$x2 ($x2) ? " . ($x1 !== $x2) . "<br>";

      // Increment/Decrement
      echo "<br><strong>Increment/Decrement</strong><br>";
      echo "++$y results in \$y = " . (++$y) . "<br>";

      // Logical
      echo "<br><strong>Logical</strong><br>";
      echo "1 and 1 = " . (1 and 1) . "<br>";
      echo "1 || 0 = " . (1 || 0) . "<br>";
      echo "!0 = " . (!0) . "<br>";

      // Concatenation
      echo "<br><strong>Concatenation</strong><br>";
      $foo = 'Foo';
      for ($i=0; $i<2; $i++) {
        echo "String concatenation assignment: " . ($foo.="Bar") . "<br>";
      };
    ?>
  </body>
</html>
