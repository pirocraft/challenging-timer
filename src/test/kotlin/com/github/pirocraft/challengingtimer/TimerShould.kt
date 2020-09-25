package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class TimerShould {

    @Test
    fun `count down to a period and execute an action each seconds`() {
        MILLISECONDS_IN_SECOND = 10
        var seconds = 5
        val timer = Timer(Period(0, seconds))

        runBlocking {
            timer.countdown { period ->
                assertEquals(--seconds, period.inSeconds())
            }.join()
        }
    }
}

