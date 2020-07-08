package creational.singleton.kotlin

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
object Singleton {
    // lazy 的本质也是个双重检验锁
    var id = 0L
        get() = ++field
        private set
}