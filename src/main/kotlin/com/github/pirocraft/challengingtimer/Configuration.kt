package com.github.pirocraft.challengingtimer

private const val DEFAULT_MINUTE = 1
private const val DEFAULT_SECONDS = 30

object Configuration {
    var period = Period(DEFAULT_MINUTE, DEFAULT_SECONDS)
        set(value) {
            field = value
            subscribers.forEach { it(value) }
        }
    var subscribers = mutableListOf<(Period) -> Unit>()

    init {
        reset()
    }

    fun reset() {
        period = Period(DEFAULT_MINUTE, DEFAULT_SECONDS)
    }

    /**
     * Subscribe to configuration period modifications
     */
    fun subscribe(action: (Period) -> Unit) {
        subscribers.add(action)
    }
}
