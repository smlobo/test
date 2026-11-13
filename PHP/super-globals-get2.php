<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Super Globals _GET</title>
  </head>
  <body>
    <div class="header"><h1>Super Globals _GET</h1></div>
    <br>

    <form method="get" action="<?php echo $_SERVER['PHP_SELF'];?>">
      Name: <input type="text" name="fname">
      <input type="submit">
    </form>

    <?php
      if ($_SERVER["REQUEST_METHOD"] == "GET") {
        // collect value of input field
        $name = $_GET['fname'];
        if (empty($name)) {
          echo "Name is empty";
        } 
        else {
          echo $name;
        }
      }
    ?>
  </body>
</html>
