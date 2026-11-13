<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Loops</title>
  </head>
  <body>
    <div class="header"><h1>Loops</h1></div>
    <br>
    <?php
      // While
      $i = 5;
      while ($i > 0) {
        echo "While loop counter: $i<br>";
        $i--;
      }

      // Do ... while
      do {
        echo "Do ... while loop counter: $i<br>";
        $i++;
      } while ($i < 5);

      // For
      for ($i = 5; $i > 0; $i--) {
        echo "For loop counter: $i<br>";        
      }

      // Foreach
      $count = array(1, 2, 3, 4, 5);
      foreach ($count as $c) {
        echo "Foreach loop [$i] $c<br>";
        $i++;
      }
    ?>
  </body>
</html>
