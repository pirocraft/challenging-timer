package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.*
import java.awt.Color

class TimerView {
    var color: Color = Color.GREEN
        private set
    private var periodLeft: Period = Configuration.period
    private var currentTimerJob: Job? = null

    init {
        Configuration.subscribe {
            runBlocking { currentTimerJob?.cancelAndJoin() }
            color = Color.GREEN
            periodLeft = it
        }
    }

    fun timeLeft(): Period {
        return periodLeft
    }

    /**
     * Start the timer
     */
    fun click(): Job {
        return if (currentTimerJob?.isActive == true) {
            GlobalScope.launch {
                currentTimerJob?.cancelAndJoin()
                color = Color.YELLOW
            }
        } else {
            val launchedTimerJob = GlobalScope.launch {
                launchATimer()
            }
            currentTimerJob = launchedTimerJob
            launchedTimerJob
        }
    }

    private suspend fun launchATimer() {
        Timer(Configuration.period).countdown {
            periodLeft = it
        }.join()
        color = Color.RED
    }
}
