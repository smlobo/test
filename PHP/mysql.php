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
      //$servername = "localhost";
      $servername = "127.0.0.1";
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
      <br><br>
      Rating: <input type="number" name="brating" min="1" max="10">
      <br><br>
      <input type="submit">
      <br><br>
    </form>

    <?php
      //$servername = "localhost";
      $servername = "127.0.0.1";
      $username = "smlobo";
      $password = "welcome";
      $dbname = "test";
      $conn = new mysqli($servername, $username, $password, $dbname);
      if ($conn->connect_error) {
        die("Connection to mysql localhost/test failed: " . 
          $conn->connect_error);
      }

      if ($_SERVER["REQUEST_METHOD"] == "POST") {
        //$sql = "insert into book_list (name, rating, date_added) values ('".
        //  $_POST['bname']."', '".$_POST['brating']."', '".date('Y-m-d H:i:s').
        //  "')";
        //if ($conn->query($sql) === true) {
        $stmt = $conn->prepare("insert into book_list (name, rating, ".
          "date_added, application) values (?, ?, ?, ?)");
        $stmt->bind_param("ssss", $bname, $brating, $bdate, $app);
        $bname = $_POST['bname'];
        $brating = $_POST['brating'];
        $bdate = date("Y-m-d H:i:s");
        $app = $_SERVER['PHP_SELF'];
        if ($stmt->execute() === true) {
          echo "New record created successfully. Last id prepare : " . 
            $conn->insert_id;
        }
        else {
          echo "Error: ".$conn->error;
        }
        echo "<br><br>";
      }

      // Display the current table
      $sql = "select * from book_list";
      $result = $conn->query($sql);
      if ($result->num_rows > 0) {
        echo "<table><tr>";
        echo "<th>ID</th><th>Book Name</th><th>Rating</th><th>Date</th>".
          "<th>Application</th>";
        while ($row = $result->fetch_assoc()) {
          echo "<tr><td>".$row["id"]."</td><td>".$row["name"]."</td><td>".
            $row["rating"]."</td><td>".$row["date_added"]."</td><td>".
            $row["application"]."</td></tr>";
        }
        echo "</table>";
      }
      else {
        echo "0 results";
      }

      $conn->close();
    ?>
  </body>
</html>
