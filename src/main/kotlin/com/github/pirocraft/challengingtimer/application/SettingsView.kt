package com.github.pirocraft.challengingtimer.application

/**
 * View to manage the settings of the application
 */
class SettingsView(duration: String,
                   val timerPeriodLabel: String = "Timer period :") {
    var duration = duration
        set(value) {
            // TODO Check parsing
            field = value
            modified = true
        }
    var previousDuration = duration
        private set
    var modified = false
        private set

    /**
     * Validate and propagate settings changes
     */
    fun validateChanges() {
        Configuration.duration = parse(duration)
        modified = false
    }
}
