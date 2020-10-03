package com.github.pirocraft.challengingtimer

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Duration

internal var MILLISECONDS_IN_SECOND = 1000

class Timer(private val duration: Duration) {
    /**
     * Begin a period countdown and call action each second
     */
    fun countdown(action: (Duration) -> Unit) = GlobalScope.launch {
        var timeLeft = duration
        repeat(duration.toSeconds().toInt()) {
            delay(MILLISECONDS_IN_SECOND.toLong())
            timeLeft = timeLeft.minusSeconds(1)
            action(timeLeft)
        }
    }
}
