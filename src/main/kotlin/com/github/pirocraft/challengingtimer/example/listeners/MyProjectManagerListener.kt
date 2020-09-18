package com.github.pirocraft.challengingtimer.example.listeners

import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener
import com.github.pirocraft.challengingtimer.example.services.MyProjectService
import com.intellij.openapi.components.service

internal class MyProjectManagerListener : ProjectManagerListener {

    override fun projectOpened(project: Project) {
//        project.getService(MyProjectService::class.java)
        project.service<MyProjectService>()
    }
}
