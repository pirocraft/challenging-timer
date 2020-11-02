package com.github.pirocraft.challengingtimer.application

import java.time.Duration

const val MINUTES_IN_HOUR = 60
const val SECONDS_IN_HOUR = 60
const val DELIMITER = ":"
const val DURATION_PATTERN = """\d{1,2}:\d{1,2}"""

/**
 * Display a duration with a 1:10 style
 */
fun Duration.format() = "${toMinutes() % MINUTES_IN_HOUR}:${(seconds % SECONDS_IN_HOUR).toString().padStart(2, '0')}"

/**
 * Parse a duration with a 1:10 style
 * @throws DurationParseException if the duration has not a correct format
 */
fun parse(duration: String): Duration {
    if (!duration.matches(Regex(DURATION_PATTERN)))
        throw DurationParseException()
    try {
        val (minutes, seconds) = duration.split(DELIMITER).map { it.toLong() }
        return Duration.ofSeconds(seconds).plusMinutes(minutes)
    } catch (e: Exception) {
        throw DurationParseException(e)
    }
}

class DurationParseException(cause: Exception? = null) : Exception("Duration should respect this template $DURATION_PATTERN like 01:30", cause)
