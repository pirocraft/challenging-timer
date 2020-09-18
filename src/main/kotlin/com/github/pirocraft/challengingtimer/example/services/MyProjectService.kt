package com.github.pirocraft.challengingtimer.example.services

import com.intellij.openapi.project.Project
import com.github.pirocraft.challengingtimer.example.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
