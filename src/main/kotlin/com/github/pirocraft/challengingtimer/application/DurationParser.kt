package com.github.pirocraft.challengingtimer.application

import java.time.Duration

const val MINUTES_IN_HOUR = 60
const val SECONDS_IN_HOUR = 60
const val DELIMITER = ":"

fun Duration.display() = "${toMinutes() % MINUTES_IN_HOUR}:${(seconds % SECONDS_IN_HOUR).toString().padStart(2, '0')}"

fun parse(duration: String): Duration {
    if (duration.split(DELIMITER).size != 2)
        throw DurationParseException()
    try {
        val (minutes, seconds) = duration.split(DELIMITER).map { it.toLong() }
        return Duration.ofSeconds(seconds).plusMinutes(minutes)
    } catch (e: Exception) {
        throw DurationParseException(e)
    }
}

class DurationParseException(cause: Exception? = null) : Exception("Duration should respect this template <minutes>:<seconds> like 01:30", cause)
