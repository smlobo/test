<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>File</title>
  </head>
  <body>
    <div class="header"><h1>File</h1></div>
    <br>
    <div class="menu">
      <?php
        include 'includeM.php';
      ?>
    </div>
    <h2>Read file: webdictionary.txt</h2>
    <?php
      echo readfile("webdictionary.txt");
      echo "<br>--------------------------<br>";

      $myfile = fopen("webdictionary.txt", "r") or die("Unable to open file!");
      echo fread($myfile, filesize("webdictionary.txt"));
      fclose($myfile);
      echo "<br>--------------------------<br>";

      $myfile = fopen("webdictionary.txt", "r") or die("Unable to open file!");
      while(!feof($myfile)) {
        echo fgets($myfile) . "<br>";
      }
      fclose($myfile);
      echo "<br>--------------------------<br>";

      $myfile = fopen("webdictionary.txt", "r") or die("Unable to open file!");
      echo "->&nbsp&nbsp";
      while (!feof($myfile)) {
        $mychar = fgetc($myfile);
        if ($mychar == "\n") {
          echo "<br>->&nbsp&nbsp";
        }
        else {
          echo $mychar;
        }
      }
      fclose($myfile);
      echo "<br>--------------------------<br>";
    ?>
    <h2>Write file: randominput.txt</h2>
    <form method="post" action="<?php echo $_SERVER['PHP_SELF'];?>">
      Anything: <input type="text" name="randomString">
      <input type="submit">
    </form>

    <?php
      if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $input = $_POST['randomString'];
        $myfile = fopen("randominput.txt", "a") or die("Unable to open file!");
        fwrite($myfile, $input);
        fwrite($myfile, "\n");
        fclose($myfile);
        echo "<h4>Current contents of randominput.txt is:</h4>";
        $myfile = fopen("randominput.txt", "r") or die("Unable to open file!");
        while (!feof($myfile)) {
          echo "->&nbsp&nbsp" . fgets($myfile) . "<br>";
        }
        fclose($myfile);
      }
    ?>
  </body>
</html>
