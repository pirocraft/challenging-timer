package com.github.pirocraft.challengingtimer.application

import java.time.Duration

fun Duration.display() = "${toMinutes() % MINUTES_IN_HOUR}:${(seconds % SECONDS_IN_HOUR).toString().padStart(2, '0')}"