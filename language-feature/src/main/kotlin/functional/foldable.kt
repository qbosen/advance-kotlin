package functional

/**
 * @author qiubaisen
 * @date 2020/5/23
 */
interface Foldable<F> {
    fun <A, B> Kind<F, A>.fold(init: B, f: (B, A) -> B): B
}

object ListFoldable : Foldable<List.K> {
    override fun <A, B> Kind<List.K, A>.fold(init: B, f: (B, A) -> B): B {
        fun fold0(l: List<A>, v: B): B = when (l) {
            is Cons -> fold0(l.tail, f(v, l.head))
            else -> v
        }

        return fold0(unwrap(), init)
    }
}