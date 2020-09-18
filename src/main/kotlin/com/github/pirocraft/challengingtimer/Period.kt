package com.github.pirocraft.challengingtimer

data class Period(val minutes: Int, val seconds: Int) {
    fun inSeconds(): Int {
        return seconds + minutes * 60
    }
}
