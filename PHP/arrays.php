<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Arrays</title>
  </head>
  <body>
    <div class="header"><h1>Arrays</h1></div>
    <br>
    <?php

      function printIndexedArray($x) {
        for ($i = 0; $i < count($x); $i++) {
          echo "&nbsp&nbsp[$i] -> $x[$i]<br>";
        }
      }

      function printAssociativeArray($x) {
        foreach ($x as $k=>$v) {
          echo "&nbsp&nbsp[$k] => $v<br>";
        }     
      }

      // Indexed array
      $iArray = array("Rock", "Jazz", "Blues", "Pop");
      echo ("<b><i>Original order indexed array:</i></b><br>");
      printIndexedArray($iArray);

      // Associative array
      $aArray = array("Bird"=>"Peacock", "Mammal"=>"Elk", "Reptile"=>"Snake", 
        "Fish"=>"Shark");
      echo ("<b><i>Original order associative array:</i></b><br>");
      printAssociativeArray($aArray);

      // Indexed Sorting
      echo ("<b><i>Sorted order indexed array:</i></b><br>");
      sort($iArray);
      printIndexedArray($iArray);
      echo ("<b><i>Reverse sorted order indexed array:</i></b><br>");
      rsort($iArray);
      printIndexedArray($iArray);

      // Associative Sorting
      echo ("<b><i>Sorted order associative array:</i></b><br>");
      asort($aArray);
      printAssociativeArray($aArray);
      echo ("<b><i>Reverse sorted order associative array:</i></b><br>");
      arsort($aArray);
      printAssociativeArray($aArray);

      // Associative Sorting by Key
      echo ("<b><i>Key sorted order associative array:</i></b><br>");
      ksort($aArray);
      printAssociativeArray($aArray);
      echo ("<b><i>Reverse key sorted order associative array:</i></b><br>");
      krsort($aArray);
      printAssociativeArray($aArray);
    ?>
  </body>
</html>
