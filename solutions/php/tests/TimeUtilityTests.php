<?php
use PHPUnit\Framework\TestCase;

require_once __DIR__ . "../../src/TimeUtility.php";

class TimeUtilityTests extends TestCase
{

  public function test6AMShouldBeMorning() {

    $stub = $this->createStub(Clock::class);

    $stub->method('time')
         ->willReturn(new DateTime('2011-01-01T06:00:00.012345Z'));

    $this->assertSame("Morning", TimeUtility::timeOfDay($stub));
  }

}

?>