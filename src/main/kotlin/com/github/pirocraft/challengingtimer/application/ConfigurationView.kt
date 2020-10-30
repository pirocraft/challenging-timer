package com.github.pirocraft.challengingtimer.application

import java.time.Duration

class ConfigurationView(duration: String,
                        val timerPeriodLabel: String = "Timer period :",
                        private var modified: Boolean = false) {
    var duration = duration
        set(value) {
            value.split(":").map { it.toLong() }
            field = value
            modified = true
        }

    fun validateChanges() {
        val (minutes, seconds) = duration.split(":").map { it.toLong() }
        Configuration.duration = Duration.ofSeconds(seconds).plusMinutes(minutes)
        modified = false
    }

    fun isChanged(): Boolean {
        return modified
    }
}
