package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.flow.Flow
import org.junit.jupiter.api.Test

internal class TimerShould {

    @Test
    suspend fun `return a countdown as a flow when started`() {
        val seconds = 5
        val timer = Timer(Period(0, seconds))
        val countdown: Flow<Int> = timer.createCountdown()

        var remainingSeconds = seconds
        TODO()
//        countdown.collect { value -> println(value) }

    }
}
