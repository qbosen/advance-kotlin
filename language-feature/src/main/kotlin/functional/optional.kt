package functional

/**
 * @author qiubaisen
 * @date 2020/5/26
 */
@Suppress("NOTHING_TO_INLINE")
inline fun <A> Kind<Option.K, A>.unwrap(): Option<A> = this as Option<A>

sealed class Option<out A> : Kind<Option.K, A> {
    object K
}

data class Some<V>(val v: V) : Option<V>()
object None : Option<Nothing>()

object OptionMonad : MonadR<Option.K> {
    override fun <A> pure(a: A): Kind<Option.K, A> {
        return Some(a)
    }

    override fun <A, B> Kind<Option.K, A>.flatMap(f: (A) -> Kind<Option.K, B>): Kind<Option.K, B> {
        return when (val oa = this) {
            is Some -> f(oa.v)
            else -> None
        }
    }
}

// 用option解决stdio读取的异常情况
fun readInt(): StdIO<Option<Int>> {
    return StdIOMonad.run {
        val a = StdIO.read().map {
            when {
                it.matches(Regex("[0-9]+")) -> Some(it.toInt())
                else -> None
            }
        }
        a.unwrap()
    }
}

fun addOption(oa: Option<Int>, ob: Option<Int>): Option<Int> {
    return OptionMonad.run {
        oa.flatMap { a -> ob.map { b -> a + b } }
    }.unwrap()
}

fun errorHandleWithOption() :Kind<StdIO.K, Unit>{
    return StdIOMonad.run {
        readInt().flatMap { oi ->
            readInt().flatMap { oj ->
                val display = when (val r = addOption(oi, oj)) {
                    is Some<*> -> r.v.toString()
                    is None -> ""
                }
                StdIO.write(display)
            }
        }
    }
}

fun main() {
    perform(errorHandleWithOption().unwrap())
}