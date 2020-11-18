package com.github.pirocraft.challengingtimer.application

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import java.awt.Color
import java.time.Duration
import java.util.concurrent.TimeUnit
import javax.annotation.Nullable

/**
 * A timer that you can start, pause, resume and restart
 *
 * The timer receive [Configuration][Configuration] modification that restart and pause it.
 * Don't forget to [dispose][dispose] it
 */
class TimerView {
    var color: Color = Color.GREEN
        private set(value) {
            field = value
            colorSubject.onNext(value)
        }
    var timeLeft = Configuration.duration
        private set(value) {
            field = value
            durationSubject.onNext(value)
        }
    private var currentTimerDisposable: Disposable? = null
    private var configurationDisposable: Disposable
    private val durationSubject: Subject<Duration> = BehaviorSubject.create()
    private val colorSubject: Subject<Color> = BehaviorSubject.create()

    init {
        configurationDisposable = Configuration.subscribe { reset() }
    }

    /**
     * Start the timer at first click, pause it at second click and restart at third
     * Restart the timer if it was previously finished
     * @param scheduler for testing purpose to manipulate time
     */
    fun click(@Nullable scheduler: Scheduler? = null) {
        when {
            timeLeft.seconds == 0.toLong() -> restart(scheduler)
            currentTimerDisposable != null -> disposeCurrentTimer()
            else -> launchATimer(scheduler)
        }
    }

    /**
     * Restart the timer
     * @param scheduler for testing purpose to manipulate time
     */
    fun doubleClick(@Nullable scheduler: Scheduler? = null) {
        restart(scheduler)
    }

    /**
     * Subscribe to the timer and color changes
     * @param updateTime update with the new duration left
     * @param updateColor update with the new color
     */
    fun subscribe(updateTime: (durationLeft: Duration) -> Unit, updateColor: (newColor: Color) -> Unit) {
        durationSubject.subscribe(updateTime)
        colorSubject.subscribe(updateColor)
    }

    fun dispose() {
        configurationDisposable.dispose()
        disposeCurrentTimer()
    }

    private fun launchATimer(scheduler: Scheduler?) =
            intervalRange(scheduler).map { timeLeft = timeLeft.minusSeconds(1) }
                    .doOnSubscribe { color = Color.GREEN }
                    .doOnComplete { color = Color.RED }
                    .doOnDispose { color = Color.YELLOW }
                    .subscribe()
                    .apply { currentTimerDisposable = this }

    private fun intervalRange(scheduler: Scheduler?) =
            if (scheduler == null) Observable.intervalRange(1, timeLeft.seconds, 1, 1, TimeUnit.SECONDS)
            else Observable.intervalRange(1, timeLeft.seconds, 1, 1, TimeUnit.SECONDS, scheduler)

    private fun disposeCurrentTimer() {
        currentTimerDisposable?.dispose()
        currentTimerDisposable = null
    }

    private fun restart(scheduler: Scheduler?) {
        reset()
        launchATimer(scheduler)
    }

    private fun reset() {
        disposeCurrentTimer()
        color = Color.GREEN
        timeLeft = Configuration.duration
    }
}
