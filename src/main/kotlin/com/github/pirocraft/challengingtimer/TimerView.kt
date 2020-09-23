package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.awt.Color

class TimerView {
    var color: Color = Color.GREEN
        private set

    fun timeLeft(): Period {
        return Period(1, 30)
    }

    /**
     * Start the timer
     */
    fun click(): Job {
        return runBlocking {
            launch {
                color = Color.RED
            }
        }
    }
}
