package com.github.pirocraft.challengingtimer

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import java.time.Duration

private const val DEFAULT_MINUTE = 1.toLong()
private const val DEFAULT_SECONDS = 30.toLong()

object Configuration {
    var duration: Duration = defaultDuration()
        set(value) {
            field = value
            durationSubject.onNext(value)
        }
    private val durationSubject: Subject<Duration> = BehaviorSubject.create()

    /**
     * Reset the default configuration
     */
    fun reset() {
        duration = defaultDuration()
    }

    /**
     * Subscribe to period modifications
     */
    fun subscribe(action: (Duration) -> Unit) {
        durationSubject.subscribe(action)
    }

    private fun defaultDuration() = Duration.ofSeconds(DEFAULT_SECONDS).plusMinutes(DEFAULT_MINUTE)
}
