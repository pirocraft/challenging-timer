package com.github.pirocraft.challengingtimer.application

import java.time.Duration

class ConfigurationView {
    var duration = Configuration.duration.display()

    fun modifyDuration(newDuration: String) {
        val (minutes, seconds) = newDuration.split(":").map { it.toLong() }
        Configuration.duration = Duration.ofSeconds(seconds).plusMinutes(minutes)
    }
}
