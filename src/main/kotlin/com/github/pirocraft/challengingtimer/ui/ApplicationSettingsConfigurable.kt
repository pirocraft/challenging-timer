package com.github.pirocraft.challengingtimer.ui

import com.github.pirocraft.challengingtimer.application.SettingsView
import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

/**
 * IntelliJ controller to manage plugins settings
 */
class ApplicationSettingsConfigurable : Configurable {
    private lateinit var settingsView: SettingsView
    private lateinit var applicationSettingsComponent: ApplicationSettingsComponent

    override fun createComponent(): JComponent? {
        settingsView = SettingsView(ApplicationSettingsState.getInstance().duration)
        applicationSettingsComponent = ApplicationSettingsComponent(settingsView)
        return applicationSettingsComponent.content()
    }

    override fun apply() {
        settingsView.validateChanges()
        ApplicationSettingsState.getInstance().duration = settingsView.duration
    }

    override fun isModified(): Boolean {
        return settingsView.isChanged()
    }

    override fun reset() {
        applicationSettingsComponent.updateDurationInputText(settingsView.previousDuration)
    }

    override fun getDisplayName() = "Challenging Timer"
}
