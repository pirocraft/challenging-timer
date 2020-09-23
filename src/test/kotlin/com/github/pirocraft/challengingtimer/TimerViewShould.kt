package com.github.pirocraft.challengingtimer

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.concurrent.timer

internal class TimerViewShould {
    @Test
    fun `be updated after configuration change`() {
        val timerView = TimerView()
        assertEquals(Configuration.period, timerView.timeLeft())
        Configuration.period = Period(2, 30)
        assertEquals(Configuration.period, timerView.timeLeft())
    }
}