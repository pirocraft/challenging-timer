package com.github.pirocraft.challengingtimer.ui

import com.github.pirocraft.challengingtimer.application.ConfigurationView
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener

class ApplicationSettingsComponent(private val configurationView: ConfigurationView) {
    fun content(): JPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel(configurationView.timerPeriodLabel),
                    inputField(),
                    1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel

    private fun inputField(): JComponent = JBTextField(configurationView.duration).apply {
        document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent?) {
                configurationView.duration = this@apply.text
            }

            override fun removeUpdate(e: DocumentEvent?) {
                configurationView.duration = this@apply.text
            }

            override fun changedUpdate(e: DocumentEvent?) {
                configurationView.duration = this@apply.text
            }
        })
    }
}
