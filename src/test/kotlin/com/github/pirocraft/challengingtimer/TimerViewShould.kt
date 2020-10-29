package com.github.pirocraft.challengingtimer

import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.awt.Color
import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlin.test.assertNotEquals

internal class TimerViewShould {
    private lateinit var timerView: TimerView
    private lateinit var scheduler: TestScheduler

    @BeforeEach
    internal fun setUp() {
        timerView = TimerView()
        scheduler = TestScheduler()
    }

    @AfterEach
    internal fun tearDown() {
        timerView.dispose()
        Configuration.reset()
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
    internal fun countdown() {
        var timeLeft = timerView.timeLeft.seconds

        timerView.click(scheduler)

        while (timeLeft > 0) {
            assertEquals(timeLeft--, timerView.timeLeft.seconds)
            assertEquals(Color.GREEN, timerView.color)
            scheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        }
        assertEquals(0, timerView.timeLeft.seconds)
        assertEquals(Color.RED, timerView.color)
    }

    @Test
    internal fun `reset after configuration change`() {
        timerView.click(scheduler)
        scheduler.advanceTimeBy(10, TimeUnit.SECONDS)
        assertEquals(80, timerView.timeLeft.seconds)

        Configuration.duration = Duration.ofSeconds(30).plusMinutes(2)
        scheduler.advanceTimeBy(10, TimeUnit.SECONDS)
        assertEquals(Configuration.duration, timerView.timeLeft)
        assertEquals(Color.GREEN, timerView.color)
    }

    @Test
    internal fun `pause the started timer when click for the second time`() {
        timerView.click(scheduler)

        scheduler.advanceTimeBy(10, TimeUnit.SECONDS)
        timerView.click()
        assertEquals(80, timerView.timeLeft.seconds)
        assertEquals(Color.YELLOW, timerView.color)

        scheduler.advanceTimeBy(10, TimeUnit.SECONDS)
        assertEquals(80, timerView.timeLeft.seconds)
        assertEquals(Color.YELLOW, timerView.color)
    }

    @Test
    internal fun `resume when click for the third time`() {
        timerView.click(scheduler)
        scheduler.advanceTimeBy(10, TimeUnit.SECONDS)
        timerView.click()
        scheduler.advanceTimeBy(10, TimeUnit.SECONDS)
        timerView.click(scheduler)
        scheduler.advanceTimeBy(10, TimeUnit.SECONDS)

        assertEquals(70, timerView.timeLeft.seconds)
        assertEquals(Color.GREEN, timerView.color)
    }

    @Test
    internal fun `publish time changes`() {
        var timeLeft = timerView.timeLeft.seconds

        timerView.subscribe({ timeLeft = it.seconds }, {})
        timerView.click(scheduler)

        while (timeLeft > 0) {
            assertEquals(timeLeft, timerView.timeLeft.seconds)
            scheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        }
    }

    @Test
    internal fun `publish color changes`() {
        var color = timerView.color
        timerView.subscribe({}, { color = it })
        timerView.click(scheduler)
        scheduler.advanceTimeBy(10, TimeUnit.SECONDS)

        assertEquals(Color.GREEN, color)
        timerView.click()
        assertEquals(Color.YELLOW, color)
        timerView.click(scheduler)
        assertEquals(Color.GREEN, color)
        scheduler.advanceTimeBy(1, TimeUnit.HOURS)
        assertEquals(Color.RED, color)
    }

    @Test
    internal fun `dispose the configuration subscription`() {
        timerView.dispose()
        Configuration.duration = Duration.ofSeconds(21)
        assertNotEquals(Configuration.duration, timerView.timeLeft)
    }

    @Test
    internal fun `restart the timer after a double click`() {
        timerView.click(scheduler)
        scheduler.advanceTimeBy(30, TimeUnit.SECONDS)
        timerView.doubleClick(scheduler)
        scheduler.advanceTimeBy(10, TimeUnit.SECONDS)

        assertEquals(80, timerView.timeLeft.seconds)
        assertEquals(Color.GREEN, timerView.color)
    }

    @Test
    internal fun `restart the timer after a double click during a pause`() {
        timerView.click(scheduler)
        scheduler.advanceTimeBy(30, TimeUnit.SECONDS)
        timerView.click(scheduler)
        assertEquals(Color.YELLOW, timerView.color)

        timerView.doubleClick(scheduler)
        scheduler.advanceTimeBy(10, TimeUnit.SECONDS)

        assertEquals(80, timerView.timeLeft.seconds)
        assertEquals(Color.GREEN, timerView.color)
    }

    @Test
    internal fun `format a duration for display`() {
        // TODO Better display with 0:00 and 0:05
        assertEquals("0:0", Duration.ofSeconds(0).display())
        assertEquals("0:5", Duration.ofSeconds(5).display())
        assertEquals("0:30", Duration.ofSeconds(30).display())
        assertEquals("1:30", Duration.ofSeconds(30).plusMinutes(1).display())
    }
}