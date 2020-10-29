package com.github.pirocraft.challengingtimer

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import java.awt.Color
import java.time.Duration
import java.util.concurrent.TimeUnit

/**
 * A timer that you can start, pause, resume and restart
 *
 * The timer receive [Configuration][Configuration] modification that restart and pause it.
 * Don't forget to [dispose][dispose] it
 */
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
        configurationDisposable = Configuration.subscribe { reset() }
    }

    /**
     * Start the timer at first click, pause it at second click and restart at third
     * @param scheduler for testing purpose to manipulate time
     */
    fun click(scheduler: Scheduler? = null) {
        if (currentTimerDisposable != null) disposeCurrentTimer()
        else launchATimer(scheduler)
    }

    /**
     * Restart the timer
     * @param scheduler for testing purpose to manipulate time
     */
    fun doubleClick(scheduler: Scheduler? = null) {
        reset()
        launchATimer(scheduler)
    }

    /**
     * Subscribe to the timer countdown
     * @param action use the next timer value
     */
    fun subscribe(action: (durationLeft: Duration) -> Unit) {
        durationSubject.subscribe(action)
    }

    fun dispose() {
        configurationDisposable.dispose()
        disposeCurrentTimer()
    }

    private fun launchATimer(scheduler: Scheduler?): Disposable =
            intervalRange(scheduler).map { Configuration.duration.seconds - it }
                    .doOnSubscribe { color = Color.GREEN }
                    .doOnNext { timeLeft = Duration.ofSeconds(it) }
                    .doOnComplete { color = Color.RED }
                    .doOnDispose { color = Color.YELLOW }
                    .subscribe()
                    .apply {
                        currentTimerDisposable = this
                    }

    private fun intervalRange(scheduler: Scheduler?): Observable<Long> =
            if (scheduler == null)
                Observable.intervalRange(startOrResumeInterval(), Configuration.duration.seconds,
                        1, 1, TimeUnit.SECONDS)
            else
                Observable.intervalRange(startOrResumeInterval(), Configuration.duration.seconds,
                        1, 1, TimeUnit.SECONDS, scheduler)

    private fun startOrResumeInterval() = (Configuration.duration - timeLeft).seconds + 1

    private fun disposeCurrentTimer() {
        currentTimerDisposable?.dispose()
        currentTimerDisposable = null
    }

    private fun reset() {
        disposeCurrentTimer()
        color = Color.GREEN
        timeLeft = Configuration.duration
    }
}

fun Duration.display() = "${this.toMinutesPart()}:${this.toSecondsPart()}"
