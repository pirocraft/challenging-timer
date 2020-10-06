package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.awt.Color
import java.time.Duration

internal class TimerViewShould {
    @Test
    fun `be green by default`() {
        assertEquals(Color.GREEN, TimerView().color)
    }

    @Test
    fun `be updated after configuration change`() {
        val timerView = TimerView()
        assertEquals(Configuration.duration, timerView.timeLeft)
        Configuration.duration = Duration.ofSeconds(30).plusMinutes(2)
        assertEquals(Configuration.duration, timerView.timeLeft)
        Configuration.duration = Duration.ofSeconds(30).plusMinutes(3)
        assertEquals(Configuration.duration, timerView.timeLeft)
        assertEquals(Color.GREEN, TimerView().color)
    }

    @Test
    fun `be paused and reset after configuration change`() {
        val timerView = TimerView()
        val startedTimerJob = timerView.click()

        Configuration.duration = Duration.ofSeconds(30).plusMinutes(2)

        assertTrue(startedTimerJob.isCancelled)
        assertEquals(Configuration.duration, timerView.timeLeft)
        assertEquals(Color.GREEN, TimerView().color)
    }

    @Test
    fun `pause the started timer when click for the second time`() {
        val timerView = TimerView()
        val startedTimerJob = timerView.click()
        runBlocking { timerView.click().join() }
        assertTrue(startedTimerJob.isCancelled)
        assertEquals(Color.YELLOW, timerView.color)
    }

    @Test
    fun `publish time changes`() {
        val timerView = TimerView()
        var secondsLefts = 3
        Configuration.duration = Duration.ofSeconds(3)
        timerView.subscribe { assertEquals(secondsLefts--, it.seconds.toInt()) }
        runBlocking { timerView.click().join() }
    }
}