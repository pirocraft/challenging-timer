package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.awt.Color

internal class TimerViewShould {
    @Test
    fun `be green by default`() {
        assertEquals(Color.GREEN, TimerView().color)
    }

    @Test
    fun `be updated after configuration change`() {
        val timerView = TimerView()
        assertEquals(Configuration.period, timerView.timeLeft())
        Configuration.period = Period(2, 30)
        assertEquals(Configuration.period, timerView.timeLeft())
        Configuration.period = Period(3, 30)
        assertEquals(Configuration.period, timerView.timeLeft())
        assertEquals(Color.GREEN, TimerView().color)
    }

    @Test
    fun `be paused and reset after configuration change`() {
        val timerView = TimerView()
        val startedTimerJob = timerView.click()

        Configuration.period = Period(2, 30)

        assertTrue(startedTimerJob.isCancelled)
        assertEquals(Configuration.period, timerView.timeLeft())
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
}