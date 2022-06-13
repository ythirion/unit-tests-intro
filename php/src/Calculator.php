<?php

class Calculator() {

  private $supportedOperators = [
    "add" => function(a, b) { return a + b},
    "multiply" => function(a, b) { return a * b},
    "divide" => function(a, b) { return a / b},
    "substract" => function(a, b) { return a - b}
  ]

  public static function calculate($a, $b, $operator) {
    if(!array_key_exists($operator, $this->supportedOperators)) {
      throw new Exception("Unsupported operator {$operator}")
    }
    return $this->supportedOperators[$operator](a, b);
  }

}


?>