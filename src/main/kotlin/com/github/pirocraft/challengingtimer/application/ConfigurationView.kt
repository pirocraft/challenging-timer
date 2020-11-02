package com.github.pirocraft.challengingtimer.application

class ConfigurationView(duration: String,
                        val timerPeriodLabel: String = "Timer period :",
                        private var modified: Boolean = false) {
    var duration = duration
        set(value) {
            field = value
            modified = true
        }

    fun validateChanges() {
        Configuration.duration = parse(duration)
        modified = false
    }

    fun isChanged(): Boolean {
        return modified
    }
}
