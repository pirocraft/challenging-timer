package com.github.pirocraft.challengingtimer.ui

import com.github.pirocraft.challengingtimer.application.ConfigurationView
import com.intellij.ui.components.JBCheckBox
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JPanel

class ApplicationSettingsComponent(val configurationView: ConfigurationView) {
    fun content(): JPanel {
        return FormBuilder.createFormBuilder()
                .addLabeledComponent(JBLabel("Enter user name: "), JBTextField(), 1, false)
                .addComponent(JBCheckBox("Do you use IntelliJ IDEA? "), 1)
                .addComponentFillVertically(JPanel(), 0)
                .panel
    }
}
