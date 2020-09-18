Feature: Challenging timer

  The challenging timer is used to practice TDD baby-step challenging yourself to implement green tests before each
  phase end

  Scenario: I challenge myself with a 1:30 timer
    Given I parametrized 1:30 for the timer
    When I click on the timer for the first time
    Then the timer begin with a green color
    And the timer switch to red at the end of the phase