package com.github.pirocraft.challengingtimer.ui

import com.github.pirocraft.challengingtimer.application.ConfigurationView
import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

class ApplicationSettingsConfigurable : Configurable {
    private lateinit var configurationView: ConfigurationView

    override fun createComponent(): JComponent? {
        configurationView = ConfigurationView(ApplicationSettingsState.getInstance().duration)
        return ApplicationSettingsComponent(configurationView).content()
    }

    override fun apply() {
        configurationView.validateChanges()
        ApplicationSettingsState.getInstance().duration = configurationView.duration
    }

    override fun isModified(): Boolean {
        return configurationView.isChanged()
    }

    override fun getDisplayName() = "Challenging Timer"
}
