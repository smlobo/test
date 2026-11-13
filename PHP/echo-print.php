<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Echo & Print</title>
  </head>
  <body>
    <div class="header"><h1>Echo & Print</h1></div>
    <br>
    <?php
      $txt1 = "This is fun!";
      $txt2 = "I enjoy php.";
      $pi = 3.14;
      $radius = 10;
      echo "I think \"$txt1\", because \"$txt2\"<br>";
      $result = print("The area of a circle of radius $radius 
        is: " . ($pi * $radius * $radius) . "<br>");
      print "Hooray - the return value of the print is $result";
    ?>
  </body>
</html>
