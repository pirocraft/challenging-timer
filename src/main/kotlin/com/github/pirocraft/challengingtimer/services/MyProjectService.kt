package com.github.pirocraft.challengingtimer.services

import com.intellij.openapi.project.Project
import com.github.pirocraft.challengingtimer.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
