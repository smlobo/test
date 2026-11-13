<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Multidimential Arrays</title>
  </head>
  <body>
    <div class="header"><h1>Multidimential Arrays</h1></div>
    <br>
    <?php

      function printIndexed2DArray($x) {
        for ($i = 0; $i < count($x); $i++) {
          echo "&nbsp&nbsp[$i] -> ";
          for ($j = 0; $j < count($x[$i]); $j++) {
            echo $x[$i][$j]."&nbsp&nbsp";
          }
          echo "<br>";
        }
      }

      function printAssociativeArray($x) {
        foreach ($x as $k=>$v) {
          echo "&nbsp&nbsp[$k] => $v<br>";
        }     
      }

      // Indexed 2d array
      $i2Array = array(
        array("Rock", "Jazz", "Blues", "Pop"),
        array("Mystery", "Romance", "Biographical", "History"),
        array("Thriller", "Drama", "Comedy", "Action")
      );
      echo ("<b><i>Original order indexed 2d array:</i></b><br>");
      printIndexed2DArray($i2Array);

      // Indexed Sorting
      echo ("<b><i>Sorted order indexed array:</i></b><br>");
      for ($i = 0; $i < count($i2Array); $i++) {
        sort($i2Array[$i]);
      }
      printIndexed2DArray($i2Array);

    ?>
  </body>
</html>
