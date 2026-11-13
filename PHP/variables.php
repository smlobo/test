<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>Variables & Comments</title>
  </head>
  <body>
    <div class="header"><h1>Variables & Comments</h1></div>
    <br>
    <?php
      // I'm a comment
      # I too am a comment
      /* multi line 
        comment */
      $myName = "Sheldon";
      echo $myName . " is cool!";
      echo "<br>";
      $myAge = 42;
      echo $myName . " is " . $myAge;

      // Variables are case sensitive, keywords are not
      $MYNAME = "Lobo";
      ECHO "<br>My name is " . $myName . " " . $MYNAME;

      // Variables in quotes are evaluated, math
      echo "<br>My age is: $myAge, in 10 years I'll be " . 
        ($myAge + 10);

      // Variable scope
      $x = "GLOBAL"; // global

      function variableTest() {
        $x = "LOCAL";  // local
        echo "<br>In function variableTest(), local \$x = $x";
      }

      function globalInFunction() {
        global $x;
        echo "<br>In function globalInFunction(), global \$x = $x";
        echo "<br>Or, via the \$GLOBALS array \$x = " . 
          $GLOBALS['x'];
      }

      variableTest();
      globalInFunction();
      echo "<br>Outside global \$x = $x";

      // Static variable
      function staticVariable() {
        static $y = 10;
        echo "<br>\$y = $y";
        $y += 10;
      }
      staticVariable();
      staticVariable();
      staticVariable();
    ?>
  </body>
</html>
