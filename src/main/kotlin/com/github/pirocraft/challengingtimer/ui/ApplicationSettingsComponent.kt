package com.github.pirocraft.challengingtimer.ui

import com.github.pirocraft.challengingtimer.application.DurationParseException
import com.github.pirocraft.challengingtimer.application.SettingsView
import com.github.pirocraft.challengingtimer.application.parse
import com.intellij.openapi.Disposable
import com.intellij.openapi.ui.ComponentValidator
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.openapi.util.Disposer
import com.intellij.ui.DocumentAdapter
import com.intellij.ui.components.JBLabel
import com.intellij.ui.components.JBTextField
import com.intellij.util.ui.FormBuilder
import java.util.function.Supplier
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.event.DocumentEvent

/**
 * IntelliJ settings component that contains an Input Text for duration changes and its label
 */
class ApplicationSettingsComponent(private val settingsView: SettingsView) {
    private lateinit var inputText: JBTextField
    private var disposable: Disposable? = null
    val content: JPanel

    init {
        content = FormBuilder.createFormBuilder()
                .addLabeledComponent(JBLabel(settingsView.timerPeriodLabel),
                        inputField(),
                        1, false)
                .addComponentFillVertically(JPanel(), 0)
                .panel
    }

    fun updateDurationInputText(duration: String) {
        inputText.text = duration
    }

    private fun inputField(): JComponent = JBTextField(settingsView.duration).apply {
        inputText = this
        addValidator()
        addTextChangedListener()
    }

    private fun JBTextField.addTextChangedListener() {
        document.addDocumentListener(
                object : DocumentAdapter() {
                    override fun textChanged(e: DocumentEvent) {
                        settingsView.duration = inputText.text
                        ComponentValidator.getInstance(inputText).ifPresent { v -> v.revalidate() }
                    }
                })
    }

    private fun addValidator() {
        ComponentValidator(Disposer.newDisposable().also { disposable = it })
                .withValidator(Supplier<ValidationInfo> {
                    try {
                        parse(inputText.text)
                        return@Supplier null
                    } catch (e: DurationParseException) {
                        return@Supplier ValidationInfo(e.message!!, inputText)
                    }
                })
                .installOn(inputText)
    }

    fun dispose() {
        disposable?.dispose()
    }
}
