<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Super Globals _GET</title>
  </head>
  <body>
    <div class="header"><h1>Super Globals _GET</h1></div>
    <br>

    <a href="super-globals-get.php?subject=PHP&web=W3schools.com">Test $GET
    </a>
    <br>

    <?php
      echo "Study " . $_GET['subject'] . " at " . $_GET['web'];
    ?>
  </body>
</html>
