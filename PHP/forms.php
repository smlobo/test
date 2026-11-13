<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Forms</title>
  </head>
  <body>
    <div class="header"><h1>Forms</h1></div>
    <br>

    <form method="post" action=
      "<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">

      Name: <input type="text" name="name"><br>
      E-mail: <input type="text" name="email"><br>
      Website: <input type="text" name="website"><br>
      Comment: <textarea name="comment" rows="5" cols="40"></textarea><br>

      Gender:
      <input type="radio" name="gender" value="f">Female
      <input type="radio" name="gender" value="m">Male
      <br>
      <input type="submit">

    </form>

    <?php

      // Initialize
      $name = $email = $website = $comment = $gender = "";

      function test_input($data) {
        $data = trim($data);              # Strip extra spaces, newlines, etc
        $data = stripcslashes($data);     # Remove backslashes
        $data = htmlspecialchars($data);  # HTMLify
        return $data;
      }

      if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $name = test_input($_POST["name"]);
        $email = test_input($_POST["email"]);
        $website = test_input($_POST["website"]);
        $comment = test_input($_POST["comment"]);
        $gender = test_input($_POST["gender"]);

        echo "<br><br>You entered:<br>";
        echo "<b>Name:</b> $name<br>";
        echo "<b>E-mail:</b> $email<br>";
        echo "<b>Website:</b> $website<br>";
        echo "<b>Comment:</b> $comment<br>";
        echo "<b>Gender:</b> ";
        if ($gender == "f") {
          echo "Female";
        }
        else {
          echo "Male";
        }
        echo "<br>";
      }
    ?>
  </body>
</html>
