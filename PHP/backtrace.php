<!DOCTYPE html>
<html>
  <head>
    <link type='text/css' rel='stylesheet' href='style.css'/>
    <title>PHP Info</title>
  </head>
  <body>
    <div class="header"><h1>PHP Info</h1></div>
    <br>
    <?php
		function a() {
			echo "Middle backtrace:<br>";
			debug_print_backtrace();
			echo "<br>";
			b();
		}

		function b() {
			c();
		}

		function c() {
			echo "Inner backtrace:<br>";
			debug_print_backtrace();
			echo "<br>";
		}

		a();
		echo "Outer backtrace:<br>";
		debug_print_backtrace();
		echo "<br>";
	?>
   </body>
</html>