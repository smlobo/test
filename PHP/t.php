<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>MySQL</title>
  </head>
  <body>
    <div class="header"><h1>MySQL</h1></div>
    <br>
    <div class="menu">
      <?php
        include 'includeM.php';
      ?>
    </div>
    <?php
      $servername = "localhost";
      $username = "smlobo";
      $password = "welcome";

      // Create connection - object oriented
      $conn = new mysqli($servername, $username, $password);

      // Check connection
      if ($conn->connect_error) {
        die("Connection failed (oo): " . $conn->connect_error);
      }
      echo "Connected successfully - object oriented<br>";
      $conn->close();

      // Create connection - procedural
      $conn = mysqli_connect($servername, $username, $password);

      // Check connection
      if (!$conn) {
        die("Connection failed (p): " . mysqli_connect_error());
      }
      echo "Connected successfully - procedural<br>";
      mysqli_close($conn);
    ?>

    <h3>Enter a book and rating into 'book_list'</h3>
    <form method="post" action=
      "<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">

      Book Name: <input type="text" name="bname">
      <br>
      Rating: 
      <select name="brating">
        <?php
          for ($i = 1; i <= 10; i++) {
            echo "<option value=".$i.">".$i."</option>";
          }
        ?>
      </select>
      <br>
      <input type="submit">
    </form>

    <?php
      $servername = "localhost";
      $username = "smlobo";
      $password = "welcome";
      $dbname = "test";
      $conn = new mysqli($servername, $username, $password, $dbname);
      if ($conn->connect_error) {
        die("Connection to mysql localhost/test failed: " . 
          $conn->connect_error);
      }

      if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $sql = "insert into book_list (name, rating, data_added) values (".
          $_POST['bname'].", ".$_POST['brating'].", ".date('Y-m-d H:i:s').")";
        if ($conn->query($sql) === true) {
          echo "New record created successfully";
        }
        else {
          echo "Error: ".$sql."<br>".$conn->error;
        }
      }

      $conn->close();
    ?>
  </body>
</html>
