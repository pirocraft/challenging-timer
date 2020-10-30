package com.github.pirocraft.challengingtimer.ui

import com.github.pirocraft.challengingtimer.application.ConfigurationView
import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

class ApplicationSettingsConfigurable : Configurable {
    override fun createComponent(): JComponent? {
        val configurationView = ConfigurationView(ApplicationSettingsState.duration)
        return ApplicationSettingsComponent(configurationView).content()
    }

    override fun apply() {
//        TODO("Not yet implemented")
    }

    override fun isModified(): Boolean {
        return false
    }

    override fun getDisplayName() = "Challenging Timer"
}
