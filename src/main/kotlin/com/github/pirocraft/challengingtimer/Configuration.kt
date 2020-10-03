package com.github.pirocraft.challengingtimer

import java.time.Duration

private const val DEFAULT_MINUTE = 1.toLong()
private const val DEFAULT_SECONDS = 30.toLong()

object Configuration {
    var duration: Duration = defaultDuration()
        set(value) {
            field = value
            subscribers.forEach { it(value) }
        }
    private var subscribers = mutableListOf<(Duration) -> Unit>()

    fun reset() {
        duration = defaultDuration()
    }

    private fun defaultDuration() = Duration.ofSeconds(DEFAULT_SECONDS).plusMinutes(DEFAULT_MINUTE)

    /**
     * Subscribe to configuration period modifications
     */
    fun subscribe(action: (Duration) -> Unit) {
        subscribers.add(action)
    }
}
