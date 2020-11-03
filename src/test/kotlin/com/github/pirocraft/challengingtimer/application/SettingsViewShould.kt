package com.github.pirocraft.challengingtimer.application

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

internal class SettingsViewShould {
    private lateinit var settingsView: SettingsView

    @BeforeEach
    internal fun setUp() {
        settingsView = SettingsView(Configuration.duration.format())
    }

    @AfterEach
    internal fun tearDown() {
        Configuration.reset()
    }

    @Test
    internal fun `have a timer period input`() {
        assertEquals("2:30", SettingsView("2:30").duration)
        assertEquals(Configuration.duration.format(), settingsView.duration)
        settingsView.duration = "1:00"
        assertEquals("1:00", settingsView.duration)
    }

    @Test
    internal fun `have a timer period label`() {
        assertEquals("Timer period :", settingsView.timerPeriodLabel)
    }

    @Test
    internal fun `be modified if duration is different from init value`() {
        assertFalse(settingsView.modified)
        settingsView.duration = "1:00"
        assertTrue(settingsView.modified)
        settingsView.validateChanges()
        assertFalse(settingsView.modified)
    }

    @Test
    internal fun `be not modified if duration is equal to init value`() {
        val initialDuration = settingsView.duration
        assertFalse(settingsView.modified)
        settingsView.duration = "1:00"
        assertTrue(settingsView.modified)
        settingsView.duration = initialDuration
        assertFalse(settingsView.modified)
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
        settingsView.duration = newDuration
        assertNotEquals(Configuration.duration.format(), newDuration)
        settingsView.validateChanges()
        assertEquals(Configuration.duration.format(), newDuration)
    }

    @Test
    internal fun `keep previous duration for reset`() {
        val newDuration = "1:00"
        settingsView.duration = newDuration
        assertEquals(defaultDuration(), parse(settingsView.previousDuration))
    }
}
