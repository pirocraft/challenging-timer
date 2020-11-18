package com.github.pirocraft.challengingtimer.application

/**
 * View to manage the settings of the application
 */
class SettingsView(duration: String, val timerPeriodLabel: String = "Timer period :") {
    var duration = duration
        set(value) {
            field = value
            modified = value != previousDuration
        }
    var previousDuration = duration
        private set
    var modified = false
        private set

    /**
     * Validate and propagate settings changes
     *
     * @throws DurationParseException if the current duration is invalid
     */
    fun validateChanges() {
        previousDuration = duration
        Configuration.duration = parse(duration)
        modified = false
    }
}
