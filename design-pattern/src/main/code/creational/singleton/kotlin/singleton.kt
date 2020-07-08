package creational.singleton.kotlin

import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
object Singleton {
    // lazy 的本质也是个双重检验锁
    private val idGenerator by lazy {
        TimeUnit.SECONDS.sleep(1)
        AtomicLong()
    }

    fun generateId(): Long = idGenerator.incrementAndGet()
}