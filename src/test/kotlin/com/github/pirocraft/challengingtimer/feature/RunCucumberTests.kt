package com.github.pirocraft.challengingtimer.feature

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

@RunWith(Cucumber::class)
@CucumberOptions(
        features = ["src/test/resources/feature"],
        tags = "not @ignored"
)
class RunCucumberTests