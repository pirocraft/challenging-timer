Feature: Feedback

  The challenging timer launch your unit tests at the end of each period and warn you if you succeed to keep your tests
  green or not, proposing to make a commit if they are green, rollback if they are not

  Scenario: I am notified if my tests are green at the end of the timer
    Given successful tests developed
    When the timer is finished
    Then the unit tests of my project are launched
    And I am notified of the success
