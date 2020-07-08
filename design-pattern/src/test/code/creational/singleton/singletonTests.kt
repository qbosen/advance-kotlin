package creational.singleton

import creational.singleton.java.*
import creational.singleton.kotlin.Singleton
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.system.measureTimeMillis

/**
 * @author qiubaisen
 * @date 2020/6/22
 */

class TestSingletons {
    @Test
    fun `简单测试`() {
        measureTimeMillis {
            for (i: Long in 1..100L) {
                assertEquals(i, EagerSingleton.getInstance().generateId())
                assertEquals(i, LazySingleton.getInstance().generateId())
                assertEquals(i, EnumSingleton.INSTANCE.generateId())
                assertEquals(i, StaticInnerSingleton.getInstance().generateId())
                assertEquals(i, DoubleCheckSingleton.getInstance().generateId())
                assertEquals(i, VolatileDoubleCheckSingleton.getInstance().generateId())
                assertEquals(i, Singleton.generateId())
            }
        }.let(::println)
    }

    @Test
    fun `多线程环境测试`() {
        val pool = Executors.newFixedThreadPool(5)
        val times = 100
        repeat(times) {
            pool.execute {
                EagerSingleton.getInstance().generateId()
                LazySingleton.getInstance().generateId()
                EnumSingleton.INSTANCE.generateId()
                StaticInnerSingleton.getInstance().generateId()
                DoubleCheckSingleton.getInstance().generateId()
                VolatileDoubleCheckSingleton.getInstance().generateId()
                Singleton.generateId()
            }
        }
        pool.shutdown()
        while (!pool.isTerminated) {
            TimeUnit.SECONDS.sleep(1).also { println("等待任务执行结束...") }
        }
        val expectId: Long = times + 1L

        assertEquals(expectId, EagerSingleton.getInstance().generateId())
        assertEquals(expectId, LazySingleton.getInstance().generateId())
        assertEquals(expectId, EnumSingleton.INSTANCE.generateId())
        assertEquals(expectId, StaticInnerSingleton.getInstance().generateId())
        assertEquals(expectId, DoubleCheckSingleton.getInstance().generateId())
        assertEquals(expectId, VolatileDoubleCheckSingleton.getInstance().generateId())
        assertEquals(expectId, Singleton.generateId())
    }
}

