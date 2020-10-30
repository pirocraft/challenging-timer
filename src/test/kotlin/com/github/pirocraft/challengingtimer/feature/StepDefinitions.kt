package com.github.pirocraft.challengingtimer.feature

import com.github.pirocraft.challengingtimer.application.Configuration
import com.github.pirocraft.challengingtimer.application.ConfigurationView
import com.github.pirocraft.challengingtimer.application.TimerView
import com.github.pirocraft.challengingtimer.application.display
import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import io.reactivex.rxjava3.schedulers.TestScheduler
import java.awt.Color
import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals

class StepDefinitions : En {

    init {
        val timerView = TimerView()
        val configurationView = ConfigurationView(Configuration.duration.display())
        val scheduler = TestScheduler()
        val newDuration = Duration.ofSeconds(30).plusMinutes(2)

        After { scenario: Scenario -> Configuration.reset() }

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
            timerView.click(scheduler)
        }

        Then("the timer switch to red at the end of the period") {
            scheduler.advanceTimeBy(1, TimeUnit.HOURS)
            assertEquals(0, timerView.timeLeft.seconds)
            assertEquals(Color.RED, timerView.color)
        }

        When("I change the parameter to 2:30") {
            configurationView.duration = "2:30"
            configurationView.validateChanges()
        }

        Then("the timer has periods of 2:30") {
            assertEquals(Duration.ofSeconds(30).plusMinutes(2), timerView.timeLeft)
        }

        Given("a started timer") {
            timerView.click(scheduler)
            scheduler.advanceTimeBy(10, TimeUnit.SECONDS)
        }

        Then("the timer is reset and paused with the new period") {
            assertEquals(newDuration, timerView.timeLeft)
        }

        Then("the timer is paused") {
            val timeLeft = timerView.timeLeft
            scheduler.advanceTimeBy(10, TimeUnit.SECONDS)
            assertEquals(timeLeft, timerView.timeLeft)
        }

        Then("the timer is yellow") {
            assertEquals(Color.YELLOW, timerView.color)
        }

        Then("the timer is resumed") {
            val timeLeft = timerView.timeLeft
            scheduler.advanceTimeBy(10, TimeUnit.SECONDS)
            assertEquals(timeLeft.minusSeconds(10), timerView.timeLeft)
        }

        When("I double-click the timer") {
            timerView.doubleClick(scheduler)
        }

        Then("the timer is restarted") {
            assertEquals(Configuration.duration, timerView.timeLeft)
            assertEquals(Color.GREEN, timerView.color)
        }

        Given("a finished timer") {
            timerView.click(scheduler)
            scheduler.advanceTimeBy(1, TimeUnit.HOURS)
        }
    }
}
