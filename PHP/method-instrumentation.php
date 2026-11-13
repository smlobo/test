<?php
  include "class.php";

  // instantiate 2 objects
  $veggie = new Vegetable(true, "blue");
  $leafy = new Spinach();

  /*echo "Calling class.php foo()\n";
  foo();
  echo "Done calling foo()\n";*/

  echo "Calling veggie method print_color()\n";
  $veggie->print_color();
  echo "Done calling print_color()\n";
  
  /*echo "Calling class.php foo() again\n";
  foo();
  echo "Done calling foo() again\n";*/

  echo "Calling leafy method bar()\n";
  $returnvalue = $leafy->bar("zoo");
  echo "Return value from  bar(): " . $returnvalue . "\n";
  echo "Done calling bar()\n";
?>