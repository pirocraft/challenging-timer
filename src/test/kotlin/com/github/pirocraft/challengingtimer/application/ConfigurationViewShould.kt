package com.github.pirocraft.challengingtimer.application

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

internal class ConfigurationViewShould {
    private lateinit var configurationView: ConfigurationView

    @BeforeEach
    internal fun setUp() {
        configurationView = ConfigurationView(Configuration.duration.display())
    }

    @AfterEach
    internal fun tearDown() {
        Configuration.reset()
    }

    @Test
    internal fun `keep the duration settings`() {
        assertEquals("2:30", ConfigurationView("2:30").duration)
        assertEquals(Configuration.duration.display(), configurationView.duration)
        configurationView.duration = "1:00"
        assertEquals("1:00", configurationView.duration)
    }

    @Test
    internal fun `change duration configuration after validation`() {
        shouldChangeDurationConfigurationAfterValidation("0:00")
        shouldChangeDurationConfigurationAfterValidation("1:00")
        shouldChangeDurationConfigurationAfterValidation("1:05")
        shouldChangeDurationConfigurationAfterValidation("1:23")
        shouldChangeDurationConfigurationAfterValidation("11:23")
    }

    private fun shouldChangeDurationConfigurationAfterValidation(newDuration: String) {
        configurationView.duration = newDuration
        assertNotEquals(Configuration.duration.display(), newDuration)
        configurationView.validateChanges()
        assertEquals(Configuration.duration.display(), newDuration)
    }
}
