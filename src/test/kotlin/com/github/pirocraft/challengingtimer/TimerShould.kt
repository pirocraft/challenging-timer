package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import java.time.Duration
import kotlin.test.assertEquals

internal class TimerShould {

    @Test
    fun `count down and execute an action each seconds`() = runBlockingTest {
        var seconds = 190
        val timer = Timer(Duration.ofSeconds(seconds.toLong()))
        timer.countdown { duration: Duration ->
            assertEquals(--seconds, duration.toSeconds().toInt())
        }
    }
}

