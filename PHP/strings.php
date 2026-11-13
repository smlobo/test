<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Strings</title>
  </head>
  <body>
    <div class="header"><h1>Strings</h1></div>
    <br>
    <?php
      // Some string manipulation functions
      $myStr = "It was the best of times.";
      echo "\"$myStr\" length: " . strlen($myStr) . "<br>";
      echo "\"$myStr\" word count: " . str_word_count($myStr) . "<br>";
      echo "Reverse \"$myStr\" : \"" . strrev($myStr) . "\"<br>";
      echo "Position of \"of\" in \"$myStr\" is " . strpos($myStr, "of") . 
        "<br>";
      echo "Replacing \"es\" with \"ft\" yeilds: \"" . 
        str_replace("es", "ft", $myStr) . "\"<br>";
    ?>
  </body>
</html>
