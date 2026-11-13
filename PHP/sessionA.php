<?php 
  session_start();
?>
<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Session A</title>
  </head>
  <body>
    <div class="header"><h1>Session A</h1></div>
    <br>
    <div class="menu">
      <?php
        include 'includeM.php';
      ?>
    </div>
    <form method="post" action="sessionB.php">
      <h4>Select your favorite color:</h4>
      <select name="favColor">
        <option value="green">Green</option>
        <option value="yellow">Yellow</option>
        <option value="purple">Purple</option>
        <option value="orange">Orange</option>
        <option value="red">Red</option>
      </select>
      <h4>Select your favorite animal:</h4>
      <select name="favAnimal">
        <option value="dog">Dog</option>
        <option value="cat">Cat</option>
        <option value="parrot">Parrot</option>
        <option value="pig">Pig</option>
        <option value="horse">Horse</option>
      </select>
      <br><br>
      <input type="submit" value="Go">
    </form>
  </body>
</html>
