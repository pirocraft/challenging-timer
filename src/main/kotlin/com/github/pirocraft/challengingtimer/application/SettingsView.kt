package com.github.pirocraft.challengingtimer.application

/**
 * View to manage the settings of the application
 */
class SettingsView(duration: String,
                   val timerPeriodLabel: String = "Timer period :",
                   private var modified: Boolean = false) {
    var duration = duration
        set(value) {
            field = value
            modified = true
        }

    /**
     * Validate and propagate settings changes
     */
    fun validateChanges() {
        Configuration.duration = parse(duration)
        modified = false
    }

    /**
     * Define if the current settings are changed
     */
    fun isChanged(): Boolean {
        return modified
    }
}
