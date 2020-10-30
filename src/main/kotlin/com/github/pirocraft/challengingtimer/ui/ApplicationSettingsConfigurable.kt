package com.github.pirocraft.challengingtimer.ui

import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

class ApplicationSettingsConfigurable : Configurable {
    override fun createComponent(): JComponent? {
        return ApplicationSettingsComponent().content()
    }

    override fun isModified(): Boolean {
        return false
    }

    override fun apply() {
//        TODO("Not yet implemented")
    }

    override fun getDisplayName() = "Challenging Timer"
}
