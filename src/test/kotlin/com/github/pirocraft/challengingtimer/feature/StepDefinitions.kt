package com.github.pirocraft.challengingtimer.feature

import com.github.pirocraft.challengingtimer.Configuration
import com.github.pirocraft.challengingtimer.Period
import com.github.pirocraft.challengingtimer.TimerView
import io.cucumber.java8.En
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import java.awt.Color
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StepDefinitions : En {

    init {
        val timerView = TimerView()
        var startTimerJob: Job? = null
        var pauseTimerJob: Job? = null
        var newPeriod = Period(2,30)

        Given("the default parameters") {
            Configuration.reset()
        }

        Then("the timer has periods of 1:30") {
            assertEquals(Period(1, 30), timerView.timeLeft())
        }


        Then("the timer is green") {
            assertEquals(Color.GREEN, timerView.color)
        }


        When("I simple-click the timer") {
            if (startTimerJob?.isActive != true) {
                startTimerJob = timerView.click()
            } else {
                pauseTimerJob = timerView.click()
            }
        }

        Then("the timer switch to red at the end of the period") {
            runBlocking {
                startTimerJob?.cancelAndJoin()

                assertEquals(0, timerView.timeLeft().inSeconds())
                assertEquals(Color.RED, timerView.color)
            }
        }

        When("I change the parameter to 2:30") {
            newPeriod = Period(2,30)
            Configuration.period = newPeriod
        }

        Then("the timer has periods of 2:30") {
            assertEquals(Period(2, 30), timerView.timeLeft())
        }

        Given("a started timer") {
            startTimerJob = timerView.click()
        }

        Then("the timer is reset and paused with the new period") {
            assertTrue(startTimerJob?.isCancelled == true)
            assertEquals(newPeriod, timerView.timeLeft())
        }

        Then("the timer is paused") {
            runBlocking {
                startTimerJob?.join()
                assertTrue(startTimerJob?.isCancelled == true)
            }
        }

        Then("the timer is yellow") {
            assertEquals(Color.YELLOW, timerView.color)
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
