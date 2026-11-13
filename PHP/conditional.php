<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Conditional</title>
  </head>
  <body>
    <div class="header"><h1>Conditional</h1></div>
    <br>
    <?php

    $time_hours = date("H");
    echo "Its $time_hours:00; ";

    // If
    if ($time_hours < "9") {
      echo "Good morning!";
    }
    elseif ($time_hours < "12") {
      echo "Good day!";
    }
    elseif ($time_hours < "16") {
      echo "Good afternoon!";
    }
    else {
      echo "Good night!";
    }
    echo "<br>";

    // Switch
    switch ($time_hours) {
      case "16":
      case "17":
      case "18":
        echo "It is evening.";
        break;
      default:
        echo "Some other time.";
    }
    echo "<br>";
    ?>
  </body>
</html>
