package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.*
import java.awt.Color

class TimerView {
    var color: Color = Color.GREEN
        private set
    private var periodLeft: Period = Configuration.period
    private var startedTimerJob: Job? = null

    init {
        Configuration.subscribe {
            runBlocking { startedTimerJob?.cancelAndJoin() }
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
        val launchedTimerJob = GlobalScope.launch {
            try {
                Timer(Configuration.period).countdown {
                    periodLeft = it
                }.join()
            } finally {
                periodLeft = Period(0, 0)
                color = Color.RED
            }
        }
        startedTimerJob = launchedTimerJob
        return launchedTimerJob
    }
}
