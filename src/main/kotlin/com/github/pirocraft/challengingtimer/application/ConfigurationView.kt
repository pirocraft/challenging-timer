package com.github.pirocraft.challengingtimer.application

import java.time.Duration

class ConfigurationView(duration: String) {
    var duration = duration
        set(value) {
            value.split(":").map { it.toLong() }
            field = value
        }

    fun validateChanges() {
        val (minutes, seconds) = duration.split(":").map { it.toLong() }
        Configuration.duration = Duration.ofSeconds(seconds).plusMinutes(minutes)
    }
}
