<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Date & Time</title>
  </head>
  <body>
    <div class="header"><h1>Date & Time</h1></div>
    <br>
    <?php
      echo "Today is " . date("Y/m/d, l") . "<br>";
      echo "The time is " . date("h:i:sa") . "<br><br>";

      $d1 = mktime(10, 11, 12, 11, 23, 2018);
      echo "Random date is: " . date("Y-m-d, l, h.i.sa", $d1) . "<br>";

      $d2 = strtotime("next week");
      echo "Next week date is: " . date("Y-m-d, l, h.i.sa", $d2) . "<br>";

      $d3 = strtotime("next month", $d2);
      echo "Month after next week date is: " . date("Y-m-d, l, h.i.sa", $d3) 
        . "<br>";
    ?>
  </body>
</html>
