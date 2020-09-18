package com.github.pirocraft.challengingtimer.feature

import com.github.pirocraft.challengingtimer.Configuration
import com.github.pirocraft.challengingtimer.Period
import com.github.pirocraft.challengingtimer.Timer
import io.cucumber.java8.En
import kotlin.test.assertEquals

class StepDefinitions : En {
    private lateinit var timer: Timer

    init {
        When("I click on the timer for the first time") {
            timer = Timer(Configuration.period)
        }

        Then("the timer has periods of {int}:{int}") { minutes: Int, seconds: Int ->
            assertEquals(Period(1, 30), timer.period)
        }

        Then("the timer begin with a green color") {
            TODO()
        }

        Then("the timer switch to red at the end of the period") {
            TODO()
        }

        When("I parametrize the timer to {int}:{int}") { minutes: Int, seconds: Int ->
            Configuration.period = Period(minutes, seconds)
        }

        Then("the timer display {int}:{int}") { minutes: Int, seconds: Int ->
            TODO()
        }
    }
}
