package com.github.pirocraft.challengingtimer.application

import java.time.Duration

const val MINUTES_IN_HOUR = 60
const val SECONDS_IN_HOUR = 60

fun Duration.display() = "${toMinutes() % MINUTES_IN_HOUR}:${(seconds % SECONDS_IN_HOUR).toString().padStart(2, '0')}"

fun parse(duration: String): Duration {
    val (minutes, seconds) = duration.split(":").map { it.toLong() }
    return Duration.ofSeconds(seconds).plusMinutes(minutes)
}