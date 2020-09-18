package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.awt.Color

private const val SECOND_IN_MINUTE = 60
private const val MS_IN_SECOND = 1000

class Timer(val period: Period) {
    var color: Color = Color.GREEN
        private set

    fun start(): Job {
        return runBlocking {
            launch {
                delay((period.minutes * SECOND_IN_MINUTE * MS_IN_SECOND + period.seconds * MS_IN_SECOND).toLong())
                color = Color.RED
            }
        }
    }

    fun createCountdown(): Flow<Int> = flow {
        for (i in 1..period.seconds) {
            delay(1000)
            emit (i)
//            emit(Period(0, period.seconds - i))
        }
    }
}
