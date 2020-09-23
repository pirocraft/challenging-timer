package com.github.pirocraft.challengingtimer

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PeriodTest {

    @Test
    fun inSeconds() {
        assertEquals(60 + 30, Period(1, 30).inSeconds())
        assertEquals(120 + 30, Period(2, 30).inSeconds())
        assertEquals(120 + 28, Period(2, 28).inSeconds())
        assertEquals(120 + 59, Period(2, 59).inSeconds())
        assertEquals(120 + 61, Period(2, 61).inSeconds())
    }
}