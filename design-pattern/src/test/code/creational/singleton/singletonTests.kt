package creational.singleton

import creational.singleton.java.*
import creational.singleton.kotlin.Singleton
import org.junit.jupiter.api.Assertions.assertEquals

import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

/**
 * @author qiubaisen
 * @date 2020/6/22
 */

class TestSingletons {
    @Test
    fun testSingletons() {
        measureTimeMillis {
            for (i: Long in 1..100L) {
                assertEquals(i, EagerSingleton.getInstance().id)
                assertEquals(i, LazySingleton.getInstance().id)
                assertEquals(i, EnumSingleton.INSTANCE.id)
                assertEquals(i, StaticInnerSingleton.getInstance().id)
                assertEquals(i, DoubleCheckSingleton.getInstance().id)
                assertEquals(i, VolatileDoubleCheckSingleton.getInstance().id)
                assertEquals(i, Singleton.id)
            }
        }.let(::println)
    }
}

