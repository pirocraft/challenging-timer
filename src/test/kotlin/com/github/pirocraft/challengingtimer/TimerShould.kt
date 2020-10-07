package com.github.pirocraft.challengingtimer

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.TestScheduler
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlin.test.assertEquals


internal class TimerShould {

    @Test
    internal fun `count down and execute an action each seconds`() = runBlockingTest {
        var seconds = 190
        val timer = Timer(Duration.ofSeconds(seconds.toLong()))
        timer.countdown { duration: Duration ->
            assertEquals(--seconds, duration.toSeconds().toInt())
        }
    }

    @Test
    internal fun tt() {
        val scheduler = TestScheduler()
        val seconds = 10
        var toto = "toot"
        Observable.intervalRange(1, 10, 0, 1, TimeUnit.SECONDS, scheduler)
                .map { seconds - it }
                .doOnComplete {
                    toto = "titi"
                }
                .subscribe {
                    println(it)
                }
        scheduler.advanceTimeBy(15, TimeUnit.SECONDS)
        assertEquals("titi", toto)
    }
}

