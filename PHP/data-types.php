<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Data Types</title>
  </head>
  <body>
    <div class="header"><h1>Data Types</h1></div>
    <br>
    <?php
      // Integer
      $grand = 5000;
      echo "\$grand is an Integer with value [$grand]; ";
      echo "var_dump(\$grand) = ";
      var_dump($grand);
      echo "<br><br>";

      // Float
      $e = 2.718;
      echo "\$e is a Float with value [$e]; ";
      echo "var_dump(\$e) = ";
      var_dump($e);
      echo "<br><br>";

      // Boolean
      $b = true;
      echo "\$b is a Boolean with value [$b]; ";
      echo "var_dump(\$b) = ";
      var_dump($b);
      echo "<br><br>";

      // Array
      $cars = array("Toyota", "Ford", "Renault");
      echo "\$cars is an Array with values:<br>";
      foreach ($cars as $car) {
        echo "<li>$car</li>";
      }
      echo "var_dump(\$cars) = ";
      var_dump($cars);
      echo "<br><br>";

      // Object
      class Fruit {
        function Fruit($t) {
          $this->type = $t;
        }
      }
      $apple = new Fruit("Apple");
      echo "\$apple is an Object with \"type\" member [$apple->type];<br>";
      echo "  var_dump(\$apple) = ";
      var_dump($apple);
      echo "<br><br>";

      // NULL
      $n;
      echo "\$n is a NULL with value [$n]; ";
      echo "var_dump(\$n) = ";
      var_dump($n);
      echo "<br><br>";      
    ?>
  </body>
</html>
