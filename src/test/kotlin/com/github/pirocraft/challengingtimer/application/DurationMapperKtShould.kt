package com.github.pirocraft.challengingtimer.application

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Duration

internal class DurationMapperKtShould {
    @Test
    internal fun `format a duration for display`() {
        Assertions.assertEquals("0:00", Duration.ofSeconds(0).display())
        Assertions.assertEquals("0:05", Duration.ofSeconds(5).display())
        Assertions.assertEquals("0:30", Duration.ofSeconds(30).display())
        Assertions.assertEquals("1:30", Duration.ofSeconds(30).plusMinutes(1).display())
    }
}