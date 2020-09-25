package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal var MILLISECONDS_IN_SECOND = 1000

class Timer(private val period: Period) {
    /**
     * Begin a period countdown and call action each second
     */
    fun countdown(action: (periodLeft: Period) -> Unit) = GlobalScope.launch {
        var periodLeft = period
        repeat(period.inSeconds()) {
            delay(MILLISECONDS_IN_SECOND.toLong())
            periodLeft = periodLeft.decrement()
            action(periodLeft)
        }
    }
}
