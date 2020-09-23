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

    @Test
    fun decrement() {
        assertEquals(Period(0,0), Period(0, 1).decrement())
        assertEquals(Period(0,1), Period(0, 2).decrement())
        assertEquals(Period(0,2), Period(0, 3).decrement())
        assertEquals(Period(0,0), Period(0, 0).decrement())
        assertEquals(Period(1,0), Period(1, 1).decrement())
        assertEquals(Period(0,59), Period(1, 0).decrement())
        assertEquals(Period(2,1), Period(2, 2).decrement())
        assertEquals(Period(1,59), Period(2, 0).decrement())
    }
}