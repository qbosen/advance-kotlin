package functional

import functional.ListFoldable.fold

/**
 * @author qiubaisen
 * @date 2020/5/26
 */
interface Applicative<F> : Functor<F> {
    fun <A> pure(a: A): Kind<F, A>
    fun <A, B> Kind<F, A>.ap(f: Kind<F, (A) -> B>): Kind<F, B>
    override fun <A, B> Kind<F, A>.map(f: (A) -> B): Kind<F, B> {
        return ap(pure(f))
    }
}

/**根据[Applicative]重定义[Monad]*/
interface MonadR<F> : Applicative<F> {
    fun <A, B> Kind<F, A>.flatMap(f: (A) -> Kind<F, B>): Kind<F, B>
    override fun <A, B> Kind<F, A>.ap(f: Kind<F, (A) -> B>): Kind<F, B> {
        return f.flatMap { fn ->
            this.flatMap { a -> pure(fn(a)) }
        }
    }
}

object ListMonadRe : MonadR<List.K> {
    override fun <A> pure(a: A): Kind<List.K, A> = ListMonad.pure(a)

    override fun <A, B> Kind<List.K, A>.flatMap(f: (A) -> Kind<List.K, B>): Kind<List.K, B> =
        ListMonad.run { flatMap(f) }
}

fun main() {
    val cons = Cons(1, Cons(2, Cons(3, Nil)))
    fun <A> Kind<List.K, A>.printItr() = fold(StringBuilder()) { sb, i -> sb.append("$i ") }.let(::println)
    ListMonadRe.run {
        ListFoldable.run {
            cons.map { it + 1 }.printItr()
            cons.ap(pure { a: Int -> "<$a>" }).printItr()
        }
    }
}