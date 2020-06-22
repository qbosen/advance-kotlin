package creational.singleton.kotlin

import creational.singleton.IdGenerator
import java.util.concurrent.atomic.AtomicLong

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
object Singleton :IdGenerator{
    // lazy 的本质也是个双重检验锁
    private val idGenerator = lazy { AtomicLong() }
    override fun getId(): Long = idGenerator.value.incrementAndGet()
}