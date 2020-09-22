package com.github.pirocraft.challengingtimer

private const val DEFAULT_MINUTE = 1
private const val DEFAULT_SECONDS = 30

object Configuration {
    lateinit var period: Period

    init {
        reset()
    }

    fun reset() {
        period = Period(DEFAULT_MINUTE, DEFAULT_SECONDS)
    }
}
