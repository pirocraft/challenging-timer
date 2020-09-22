package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.awt.Color

class TimerView(var color: Color = Color.GREEN) {
    fun timeLeft(): Period {
        return Period(1, 30)
    }

    fun click(): Job {
        return runBlocking {
            launch {
                color = Color.RED
            }
        }
    }
}