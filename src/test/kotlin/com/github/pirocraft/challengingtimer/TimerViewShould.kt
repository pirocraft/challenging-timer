package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.awt.Color
import java.time.Duration
import kotlin.test.assertNotEquals

internal class TimerViewShould {
    private lateinit var timerView: TimerView

    @BeforeEach
    internal fun setUp() {
        timerView = TimerView()
    }

    @AfterEach
    internal fun tearDown() {
        timerView.dispose()
    }

    @Test
    internal fun `be green by default`() {
        assertEquals(Color.GREEN, timerView.color)
    }

    @Test
    internal fun `be updated after configuration change`() {
        assertEquals(Configuration.duration, timerView.timeLeft)
        Configuration.duration = Duration.ofSeconds(30).plusMinutes(2)
        assertEquals(Configuration.duration, timerView.timeLeft)
        Configuration.duration = Duration.ofSeconds(30).plusMinutes(3)
        assertEquals(Configuration.duration, timerView.timeLeft)
        assertEquals(Color.GREEN, timerView.color)
    }

    @Test
    internal fun `be paused and reset after configuration change`() {
        val startedTimerJob = timerView.click()

        Configuration.duration = Duration.ofSeconds(30).plusMinutes(2)

        assertTrue(startedTimerJob.isCancelled)
        assertEquals(Configuration.duration, timerView.timeLeft)
        assertEquals(Color.GREEN, timerView.color)
    }

    @Test
    internal fun `pause the started timer when click for the second time`() {
        val startedTimerJob = timerView.click()
        runBlocking { timerView.click().join() }
        assertTrue(startedTimerJob.isCancelled)
        assertEquals(Color.YELLOW, timerView.color)
    }

    @Test
    internal fun `publish time changes`() {
        var secondsLefts = 3
        Configuration.duration = Duration.ofSeconds(3)
        timerView.subscribe { assertEquals(secondsLefts--, it.seconds.toInt()) }
        runBlocking { timerView.click().join() }
    }

    @Test
    internal fun `dispose the configuration subscription`() {
        timerView.dispose()
        Configuration.duration = Duration.ofSeconds(21)
        assertNotEquals(Configuration.duration, timerView.timeLeft)
    }
}