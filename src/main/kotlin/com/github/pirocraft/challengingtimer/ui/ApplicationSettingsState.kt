package com.github.pirocraft.challengingtimer.ui

import com.github.pirocraft.challengingtimer.application.defaultDuration
import com.github.pirocraft.challengingtimer.application.display
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(name = "com.github.pirocraft.challengingtimer.ui.ApplicationSettingsState",
        storages = [Storage("SdkSettingsPlugin.xml")])
object ApplicationSettingsState : PersistentStateComponent<ApplicationSettingsState> {
    var duration: String = defaultDuration().display()

    override fun getState(): ApplicationSettingsState? = this

    override fun loadState(state: ApplicationSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }
}
