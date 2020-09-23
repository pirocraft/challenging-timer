package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.awt.Color

class TimerView {
    var color: Color = Color.GREEN
        private set
    private var periodLeft: Period = Configuration.period

    init {
        Configuration.subscribe {
            periodLeft = it
        }
    }

    fun timeLeft(): Period {
        return periodLeft
    }

    /**
     * Start the timer
     */
    fun click() = GlobalScope.launch {
        Timer(Configuration.period).countdown {
            periodLeft = it
        }.join()
        color = Color.RED
    }
}
