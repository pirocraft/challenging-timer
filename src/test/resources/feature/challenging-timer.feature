Feature: Challenging timer

  Use the challenging timer to practice TDD baby-steps,
  challenging yourself to make small changes that keep your tests green after each period

  Background:
    Given the default parameters

  Scenario: I challenge myself with the default 3:00 timer
    Then the timer has periods of 3:00
    And the timer is green
    When I simple-click the timer
    Then the timer switch to red at the end of the period

  Scenario: I pause and resume the timer
    Given a started timer
    When I simple-click the timer
    Then the timer is paused
    And the timer is yellow
    When I simple-click the timer
    Then the timer is resumed
    And the timer is green

  Scenario: I restart the timer with a double click
    Given a started timer
    When I double-click the timer
    Then the timer is restarted

  Scenario: I restart the timer with a simple click when it is finished
    Given a finished timer
    When I simple-click the timer
    Then the timer is restarted

  Scenario: I parametrize the timer to 2:30
    When I change the parameter to 2:30
    Then the timer has periods of 2:30

  Scenario: The timer is reset if I change the parameters
    Given a started timer
    When I change the parameter to 2:30
    Then the timer is reset and paused with the new period
    And the timer is green
