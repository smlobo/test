<?php

  function foo() {
    echo "in class.php -> foo() \n";
  }

  // base class with member properties and methods
  class Vegetable {

    var $edible;
    var $color;

    function __construct($edible, $color="green")
    {
      $this->edible = $edible;
      $this->color = $color;
    }

    function is_edible()
    {
      return $this->edible;
    }

    function what_color()
    {
      return $this->color;
    }

    function print_color()
    {
      echo $this->color . "\n";
    }
  } // end of class Vegetable

  // extends the base class
  class Spinach extends Vegetable {

    var $cooked = false;

    function __construct()
    {
      parent::__construct(true, "green");
    }

    function cook_it()
    {
      $this->cooked = true;
    }

    function is_cooked()
    {
      return $this->cooked;
    }

    function bar($myarg = "moo") {
      echo "In Spinach::bar(): Arg passed is: " . $myarg . "\n";
      return $myarg . "+bar";
    }
  } // end of class Spinach
?>