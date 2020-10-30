package com.github.pirocraft.challengingtimer.ui

import com.github.pirocraft.challengingtimer.application.ConfigurationView
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel

class ApplicationSettingsComponent(private val configurationView: ConfigurationView) {
    fun content(): JPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel(configurationView.timerPeriodLabel),
                    inputField(),
                    1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel

    private fun inputField(): JComponent = JBTextField(configurationView.duration).apply {
        addPropertyChangeListener {
            configurationView.duration = it.newValue.toString()
        }
    }
}
