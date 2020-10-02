package com.github.pirocraft.challengingtimer

private const val SECONDS_IN_MINUTE = 60
private const val LAST_SECOND_IN_A_MINUTE = 59

data class Period(private val minutes: Int, private val seconds: Int) {

    /**
     * Total of seconds in this Period
     */
    fun inSeconds(): Int {
        return seconds + minutes * SECONDS_IN_MINUTE
    }

    fun decrement(): Period {
        return when {
            seconds > 0 -> Period(minutes, seconds - 1)
            seconds == 0 && minutes > 0 -> Period(minutes - 1, LAST_SECOND_IN_A_MINUTE)
            else -> Period(minutes, seconds)
        }
    }

    fun print(): String {
        return "$minutes:${if (seconds.toString().length < 2) "0$seconds" else "$seconds"}"
    }
}
