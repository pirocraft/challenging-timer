package com.github.pirocraft.challengingtimer

data class Period(val minutes: Int, val seconds: Int) {
    fun inSeconds(): Int {
        return seconds + minutes * 60
    }

    fun decrement(): Period {
        return when {
            seconds > 0 -> Period(minutes, seconds - 1)
            seconds == 0 && minutes > 0 -> Period(minutes - 1, 59)
            else -> Period(minutes, seconds)
        }
    }
}
