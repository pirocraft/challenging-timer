package com.github.pirocraft.challengingtimer.ui

import com.github.pirocraft.challengingtimer.application.SettingsView
import com.intellij.ui.DocumentAdapter
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.event.DocumentEvent

/**
 * IntelliJ settings component that contains an Input Text for duration changes and its label
 */
class ApplicationSettingsComponent(private val settingsView: SettingsView) {
    private lateinit var inputText: JBTextField

    fun content(): JPanel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel(settingsView.timerPeriodLabel),
                    inputField(),
                    1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel

    fun updateDurationInputText(duration: String) {
        inputText.text = duration
    }

    private fun inputField(): JComponent = JBTextField(settingsView.duration).apply {
        inputText = this
        document.addDocumentListener(object : DocumentAdapter() {
            override fun textChanged(e: DocumentEvent) {
                settingsView.duration = this@apply.text
            }

        })
    }
}
