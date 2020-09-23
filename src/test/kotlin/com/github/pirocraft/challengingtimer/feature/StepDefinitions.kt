package com.github.pirocraft.challengingtimer.feature

import com.github.pirocraft.challengingtimer.Configuration
import com.github.pirocraft.challengingtimer.Period
import com.github.pirocraft.challengingtimer.Timer
import com.github.pirocraft.challengingtimer.TimerView
import io.cucumber.java8.En
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import java.awt.Color
import kotlin.test.assertEquals

class StepDefinitions : En {

    init {
        val timerView = TimerView()
        var clickJob: Job = Job()

        Given("the default parameters") {
//            Configuration.reset()
            Configuration.period = Period(0, 2)
        }

        Then("the timer has periods of 1:30") {
            assertEquals(Period(1, 30), timerView.timeLeft())
        }


        Then("the timer begin with a green color") {
            assertEquals(Color.GREEN, timerView.color)
        }


        When("I simple-click the timer") {
            clickJob = timerView.click()
        }

        Then("the timer switch to red at the end of the period") {
            runBlocking {
                clickJob.join()

                assertEquals(0, timerView.timeLeft().inSeconds())
                assertEquals(Color.RED, timerView.color)
            }
        }

        When("I change the parameter to 2:30") {
            Configuration.period = Period(2, 30)
        }

        Then("the timer has periods of 2:30") {
            assertEquals(Period(2, 30), timerView.timeLeft())
        }

        Given("a started timer") {
            TODO()
        }

        Then("the timer is reset and paused with the new period") {
            TODO()
        }

        Then("the timer is green") {
            TODO()
        }

        Then("the timer is paused") {
            TODO()
        }

        Then("the timer is yellow") {
            TODO()
        }

        Then("the timer is resumed") {
            TODO()
        }

        When("I double-click the timer") {
            TODO()
        }

        Then("the timer is restarted") {
            TODO()
        }
    }
}
