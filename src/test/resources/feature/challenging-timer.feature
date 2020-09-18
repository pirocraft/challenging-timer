Feature: Challenging timer

  The challenging timer is used to practice TDD baby-step challenging yourself to implement green tests before each
  period end

  Scenario: I challenge myself with a 1:30 timer
    Given A non parametrized timer
    When I click on the timer for the first time
    Then the timer has periods of 1:30
    And the timer begin with a green color
    And the timer switch to red at the end of the period

  Scenario: I parametrize the timer to 2:30
    Given A non parametrized timer
    When I parametrize the timer to 2:30
    Then the timer display 2:30