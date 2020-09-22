Feature: Challenging timer

  The challenging timer is used to practice TDD baby-steps, challenging yourself to make your tests pass after each period

  Background:
    Given the default parameters

  Scenario: I challenge myself with the default 1:30 timer
    Then the timer has periods of 1:30
    And the timer begin with a green color
    When I simple-click the timer
    Then the timer switch to red at the end of the period

  Scenario: I parametrize the timer to 2:30
    When I change the parameter to 2:30
    Then the timer has periods of 2:30

  Scenario: The timer is reset if I change the parameters
    Given a started timer
    When I change the parameter to 2:30
    Then the timer is reset and paused with the new period
    And the timer is green

  Scenario: I pause and resume the timer
    Given a started timer
    When I simple-click the timer
    Then the timer is paused
    And the timer is yellow
    When I simple-click the timer
    Then the timer is resumed
    And the timer is green

  Scenario: I restart the timer
    Given a started timer
    When I double-click the timer
    Then the timer is restarted
