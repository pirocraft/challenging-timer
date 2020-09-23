package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class TimerShould {

    @Test
    fun `return a countdown as a flow when started`() {
        val seconds = 5
        val timer = Timer(Period(0, seconds))
        val countdown: Flow<Int> = timer.countdown()

//        listOf(1, 2, 3).forEach { value -> println(value)}

        runBlocking {
            countdown.collect { value -> println(value) }
        }
        TODO()
    }

}

