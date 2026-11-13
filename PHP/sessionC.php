<?php 
  session_start();
?>
<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Session C</title>
  </head>
  <body>
    <div class="header"><h1>Session C</h1></div>
    <br>
    <div class="menu">
      <?php
        include 'includeM.php';
      ?>
    </div>
    <form method="post" action="<?php echo $_SERVER['$PHP_SELF'];?>">
      <h4>Re-select your favorite color:</h4>
      <select name="favColor">
        <option value="green">Green</option>
        <option value="yellow">Yellow</option>
        <option value="purple">Purple</option>
        <option value="orange" selected>Orange</option>
        <option value="red">Red</option>
      </select>
      <h4>Select your favorite tree:</h4>
      <select name="favTree">
        <option value="oak">Oak</option>
        <option value="maple">Maple</option>
        <option value="pine">Pine</option>
        <option value="willow">Willow</option>
        <option value="cherry">Cherry</option>
      </select>
      <br><br>
      <input type="submit" value="Go">
    </form>
    <br><br>
    <?php
      if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $_SESSION["favColor"] = $_POST['favColor'];
        $_SESSION["favTree"] = $_POST['favTree'];
      }
      echo "<h4>Your selections are: <font color=\"" . 
        $_SESSION["favColor"] . "\">" . $_SESSION["favColor"] . 
        "</font>, " . $_SESSION["favAnimal"] . ", " . 
        $_SESSION["favTree"] . "</h4>";
    ?>
    <form method="post" action="sessionD.php">
      <input type="submit" value="Destroy Session">
    </form>
    <a href="/PHP/sessionA.php">Session A</a>
    <a href="/PHP/sessionB.php">Session B</a>
  </body>
</html>
