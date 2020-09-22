package com.github.pirocraft.challengingtimer.feature

import com.github.pirocraft.challengingtimer.Configuration
import com.github.pirocraft.challengingtimer.Period
import com.github.pirocraft.challengingtimer.Timer
import io.cucumber.java8.En
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import java.awt.Color
import java.time.Instant
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class StepDefinitions : En {
    private var timer = Timer(Configuration.period)

    init {
        var start: Job = Job()
        var startTime: Instant? = null

        Given("the default parameters") {
            TODO()
        }

        Then("the timer has periods of 1:30") {
            assertEquals(Period(1, 30), timer.period)
        }


        Then("the timer begin with a green color") {
            assertEquals(Color.GREEN, timer.color)
        }

        When("the timer starts") {
            start = timer.start()
            startTime = Instant.now()
        }

        Then("the timer switch to red at the end of the period") {
            runBlocking {
                start.join()
                val endTime = Instant.now()

                assertEquals(Color.RED, timer.color)
            }
        }

        When("I change the parameter to 2:30") {
            TODO()
        }

        Then("the timer has periods of 2:30") {
            TODO()
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

        When("I simple-click the timer") {
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
