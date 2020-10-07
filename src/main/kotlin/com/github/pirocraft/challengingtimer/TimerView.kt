package com.github.pirocraft.challengingtimer

import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject
import kotlinx.coroutines.*
import java.awt.Color
import java.time.Duration

class TimerView {
    var color: Color = Color.GREEN
        private set
    var timeLeft = Configuration.duration
        private set(value) {
            field = value
            durationSubject.onNext(value)
        }
    private var currentTimerJob: Job? = null
    private val durationSubject: Subject<Duration> = BehaviorSubject.create()
    private var configurationDisposable: Disposable

    init {
        configurationDisposable = Configuration.subscribe {
            runBlocking { currentTimerJob?.cancelAndJoin() }
            color = Color.GREEN
            timeLeft = it
        }
    }

    /**
     * Start the timer
     */
    fun click(): Job {
        return if (currentTimerJob?.isActive == true) {
            GlobalScope.launch {
                currentTimerJob?.cancelAndJoin()
                color = Color.YELLOW
            }
        } else {
            val launchedTimerJob = GlobalScope.launch {
                launchATimer()
            }
            currentTimerJob = launchedTimerJob
            launchedTimerJob
        }
    }

    private suspend fun launchATimer() {
        Timer(Configuration.duration).countdown {
            timeLeft = it
        }
        color = Color.RED
    }

    fun subscribe(action: (durationLeft: Duration) -> Unit) {
        durationSubject.subscribe(action)
    }

    fun dispose() {
        configurationDisposable.dispose()
    }
}
