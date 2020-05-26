package functional

/**
 * @author qiubaisen
 * @date 2020/5/26
 */

interface Monad<F> {
    fun <A> pure(a: A): Kind<F, A>
    fun <A, B> Kind<F, A>.flatMap(f: (A) -> Kind<F, B>): Kind<F, B>
}

object ListMonad : Monad<List.K> {
    private fun <A> append(fa: Kind<List.K, A>, fb: Kind<List.K, A>): Kind<List.K, A> {
        return when (fa) {
            is Cons -> Cons(fa.head, append(fa.tail, fb).unwrap())
            else -> fb
        }
    }

    override fun <A> pure(a: A): Kind<List.K, A> {
        return Cons(a, Nil)
    }

    override fun <A, B> Kind<List.K, A>.flatMap(f: (A) -> Kind<List.K, B>): Kind<List.K, B> {
        val fa = this
        val empty: Kind<List.K, B> = Nil
        return ListFoldable.run {
            fa.fold(empty) { r, l ->
                append(r, f(l))
            }
        }
    }
}

fun main() {
    val cons = Cons(1, Cons(2, Cons(3, Nil)))
    ListMonad.run {
        ListFoldable.run {
            cons.flatMap { s -> pure("<$s>") }.fold(StringBuilder()) { sb, i -> sb.append(i) }.let(::println)
        }
    }
}