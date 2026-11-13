<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Super Globals _REQUEST</title>
  </head>
  <body>
    <div class="header"><h1>Super Globals _REQUEST</h1></div>
    <br>

    <form method="post" action="<?php echo $_SERVER['PHP_SELF'];?>">
      Name: <input type="text" name="fname">
      <input type="submit">
    </form>

    <?php
      if ($_SERVER["REQUEST_METHOD"] == "POST") {
        // collect value of input field
        $name = $_REQUEST['fname'];
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
