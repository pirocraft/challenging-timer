package com.github.pirocraft.challengingtimer.feature

import com.github.pirocraft.challengingtimer.Configuration
import com.github.pirocraft.challengingtimer.TimerView
import io.cucumber.java8.En
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import java.awt.Color
import java.time.Duration
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StepDefinitions : En {

    init {
        val timerView = TimerView()
        var startTimerJob: Job? = null
        var pauseTimerJob: Job? = null
        var newDuration = Duration.ofSeconds(30).plusMinutes(2)

        Given("the default parameters") {
            Configuration.reset()
        }

        Then("the timer has periods of 1:30") {
            assertEquals(Duration.ofSeconds(30).plusMinutes(1), timerView.timeLeft)
        }


        Then("the timer is green") {
            assertEquals(Color.GREEN, timerView.color)
        }


        When("I simple-click the timer") {
            TODO()
            if (startTimerJob?.isActive != true) {
//                startTimerJob = timerView.click()
            } else {
//                pauseTimerJob = timerView.click()
            }
        }

        Then("the timer switch to red at the end of the period") {
            runBlocking {
                startTimerJob?.join()

                assertEquals(0, timerView.timeLeft.seconds)
                assertEquals(Color.RED, timerView.color)
            }
        }

        When("I change the parameter to 2:30") {
            newDuration = Duration.ofSeconds(30).plusMinutes(2)
            Configuration.duration = newDuration
        }

        Then("the timer has periods of 2:30") {
            assertEquals(Duration.ofSeconds(30).plusMinutes(2), timerView.timeLeft)
        }

        Given("a started timer") {
            TODO()
//            startTimerJob = timerView.click()
        }

        Then("the timer is reset and paused with the new period") {
            runBlocking {
                startTimerJob?.join()
            }
            assertTrue(startTimerJob?.isCancelled == true)
            assertEquals(newDuration, timerView.timeLeft)
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
