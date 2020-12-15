package com.github.pirocraft.challengingtimer.feature

import com.github.pirocraft.challengingtimer.application.Configuration
import com.github.pirocraft.challengingtimer.application.TimerView
import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import io.reactivex.rxjava3.schedulers.TestScheduler

class FeedbackStepDefinitions : En {

    init {
        val timerView = TimerView()
        val scheduler = TestScheduler()

        After { scenario: Scenario -> Configuration.reset() }

        Given("successful tests developed") {
            TODO()
        }

        When("the timer is finished") {
            TODO()
        }

        Then("the unit tests of my project are launched") {
            TODO()
        }

        And("I am notified of the success") {
            TODO()
        }
    }
}
