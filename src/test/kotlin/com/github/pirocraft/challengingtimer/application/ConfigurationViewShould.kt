package com.github.pirocraft.challengingtimer.application

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ConfigurationViewShould {
    private lateinit var configurationView: ConfigurationView

    @BeforeEach
    internal fun setUp() {
        configurationView = ConfigurationView()
    }

    @AfterEach
    internal fun tearDown() {
        Configuration.reset()
    }

    @Test
    internal fun `display current configuration`() {
        assertEquals(Configuration.duration.display(), configurationView.duration)
    }

    @Test
    internal fun `change duration configuration`() {
        shouldChangeDurationConfiguration("0:00")
        shouldChangeDurationConfiguration("1:00")
        shouldChangeDurationConfiguration("1:05")
        shouldChangeDurationConfiguration("1:23")
        shouldChangeDurationConfiguration("11:23")
    }

    private fun shouldChangeDurationConfiguration(newDuration: String) {
        configurationView.modifyDuration(newDuration)
        assertEquals(Configuration.duration.display(), newDuration)
    }
}
