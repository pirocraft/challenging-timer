Feature: Challenging timer

  The challenging timer is used to practice TDD baby-step challenging yourself to implement green tests before each
  period end

  Scenario: I challenge myself with a 1:30 timer
    When I click on the timer for the first time
    Then the timer has periods of 1:30
    And the timer begin with a green color
    Then the timer starts
    And the timer switch to red at the end of the period
