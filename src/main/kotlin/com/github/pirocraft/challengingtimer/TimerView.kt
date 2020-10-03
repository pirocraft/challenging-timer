package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.*
import java.awt.Color
import java.time.Duration

class TimerView {
    var color: Color = Color.GREEN
        private set
    private var timeLeft = Configuration.duration
    private var currentTimerJob: Job? = null

    init {
        Configuration.subscribe {
            runBlocking { currentTimerJob?.cancelAndJoin() }
            color = Color.GREEN
            timeLeft = it
        }
    }

    fun timeLeft(): Duration {
        return timeLeft
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
        Timer(Configuration.duration).countdown {
            timeLeft = it
        }.join()
        color = Color.RED
    }
}
