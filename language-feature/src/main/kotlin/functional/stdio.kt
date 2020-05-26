package functional

/**
 * @author qiubaisen
 * @date 2020/5/26
 */


@Suppress("NOTHING_TO_INLINE")
inline fun <A> Kind<StdIO.K, A>.unwrap(): StdIO<A> = this as StdIO<A>

sealed class StdIO<A> : Kind<StdIO.K, A> {
    object K
    companion object {
        fun read(): StdIO<String> = ReadLine
        fun write(l: String): StdIO<Unit> = WriteLine(l)
        fun <A> pure(a: A): StdIO<A> = Pure(a)
    }
}

object ReadLine : StdIO<String>()
data class WriteLine(val line: String) : StdIO<Unit>()
data class Pure<A>(val a: A) : StdIO<A>()

data class FlatMap<A, B>(val fa: StdIO<A>, val f: (A) -> StdIO<B>) : StdIO<B>()
object StdIOMonad : MonadR<StdIO.K> {
    override fun <A> pure(a: A): Kind<StdIO.K, A> {
        return Pure(a)
    }

    override fun <A, B> Kind<StdIO.K, A>.flatMap(f: (A) -> Kind<StdIO.K, B>): Kind<StdIO.K, B> {
        return FlatMap(this.unwrap()) { a -> f(a).unwrap() }
    }
}

fun <A> perform(stdIO: StdIO<A>): A {
    fun <C, D> runFlatMap(fm: FlatMap<C, D>) {
        perform(fm.f(perform(fm.fa)))
    }
    return when (stdIO) {
        is ReadLine -> readLine() as A
        is Pure<A> -> stdIO.a
        is WriteLine -> println(stdIO.line) as A
        is FlatMap<*, A> -> runFlatMap(stdIO) as A
    }
}

fun main() {
    val io = StdIOMonad.run {
        StdIO.read().flatMap { a ->
            StdIO.read().flatMap { b ->
                StdIO.write("${a.toInt() + b.toInt()}")
            }
        }
    }

    perform(io.unwrap())
}