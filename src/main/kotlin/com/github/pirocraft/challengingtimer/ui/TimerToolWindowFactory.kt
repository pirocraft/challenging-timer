package com.github.pirocraft.challengingtimer.ui

import com.github.pirocraft.challengingtimer.application.Configuration
import com.github.pirocraft.challengingtimer.application.parse
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

class TimerToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        Configuration.duration = parse(ApplicationSettingsState.getInstance().duration)

        val timerToolWindow = TimerToolWindow(toolWindow)
        val contentFactory = ContentFactory.SERVICE.getInstance()
        val content = contentFactory.createContent(timerToolWindow.content, "", false)
        toolWindow.contentManager.addContent(content)
    }
}
