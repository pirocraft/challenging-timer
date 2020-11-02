package com.github.pirocraft.challengingtimer.application

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.Duration
import kotlin.test.assertEquals

internal class DurationParserKtShould {
    @Test
    internal fun `format a duration for display`() {
        Assertions.assertEquals("0:00", Duration.ofSeconds(0).format())
        Assertions.assertEquals("0:05", Duration.ofSeconds(5).format())
        Assertions.assertEquals("0:30", Duration.ofSeconds(30).format())
        Assertions.assertEquals("1:30", Duration.ofSeconds(30).plusMinutes(1).format())
    }

    @Test
    internal fun `parse a duration`() {
        assertEquals(Duration.ofSeconds(0), parse("0:0"))
        assertEquals(Duration.ofSeconds(1), parse("0:1"))
        assertEquals(Duration.ofSeconds(0), parse("0:00"))
        assertEquals(Duration.ofSeconds(1), parse("0:01"))
        assertEquals(Duration.ofSeconds(20), parse("0:20"))
        assertEquals(Duration.ofSeconds(20), parse("00:20"))
        assertEquals(Duration.ofSeconds(20).plusMinutes(1), parse("1:20"))
        assertEquals(Duration.ofSeconds(20).plusMinutes(1), parse("01:20"))
        assertEquals(Duration.ofSeconds(40).plusMinutes(10), parse("10:40"))
    }

    @Test
    internal fun `throw parse error`() {
        shouldThrowParseError("0")
        shouldThrowParseError("10")
        shouldThrowParseError("0:0:10")
        shouldThrowParseError("0-10")
        shouldThrowParseError("0:100")
        shouldThrowParseError("000:10")
    }

    private fun shouldThrowParseError(duration: String) {
        val exception = assertThrows<DurationParseException> { parse(duration) }
        assertEquals("Duration should respect this template \\d{1,2}:\\d{1,2} like 01:30", exception.message)
    }
}
