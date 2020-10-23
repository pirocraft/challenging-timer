package com.github.pirocraft.challengingtimer

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import java.awt.Color
import java.time.Duration
import java.util.concurrent.TimeUnit

class TimerView {
    var color: Color = Color.GREEN
        private set
    var timeLeft = Configuration.duration
        private set(value) {
            field = value
            durationSubject.onNext(value)
        }
    private var currentTimerDisposable: Disposable? = null
    private var configurationDisposable: Disposable
    private val durationSubject: Subject<Duration> = BehaviorSubject.create()

    init {
        configurationDisposable = Configuration.subscribe {
            disposeCurrentTimer()
            color = Color.GREEN
            timeLeft = it
        }
    }

    /**
     * Start the timer at first click, pause it at second click and restart at third
     * @param scheduler for testing purpose to manipulate time
     */
    fun click(scheduler: Scheduler? = null) {
        if (currentTimerDisposable != null) {
            disposeCurrentTimer()
            color = Color.YELLOW
        } else {
            launchATimer(scheduler)
        }
    }

    private fun launchATimer(scheduler: Scheduler?): Disposable {
        val intervalRange = if (scheduler == null) {
            // TODO Begin interval range with the previous pause section
            Observable.intervalRange(1, Configuration.duration.seconds, 1, 1, TimeUnit.SECONDS)
        } else {
            Observable.intervalRange(1, Configuration.duration.seconds, 1, 1, TimeUnit.SECONDS, scheduler)
        }
        return intervalRange.map { Configuration.duration.seconds - it }
                .doOnNext { timeLeft = Duration.ofSeconds(it) }
                .doOnComplete { color = Color.RED }
                .subscribe()
                .apply {
                    currentTimerDisposable = this
                }
    }

    fun subscribe(action: (durationLeft: Duration) -> Unit) {
        durationSubject.subscribe(action)
    }

    private fun disposeCurrentTimer() {
        currentTimerDisposable?.dispose()
        currentTimerDisposable = null
    }

    fun dispose() {
        configurationDisposable.dispose()
        disposeCurrentTimer()
    }
}

fun Duration.display() = "${this.toMinutesPart()}:${this.toSecondsPart()}"