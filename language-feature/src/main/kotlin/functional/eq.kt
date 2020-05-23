package functional


/**
 * @author qiubaisen
 * @date 2020/5/23
 */

interface Eq<F> {
    fun F.eq(that: F): Boolean
}

abstract class ListEq<A>(val a: Eq<A>) : Eq<Kind<List.K, A>> {
    override fun Kind<List.K, A>.eq(that: Kind<List.K, A>): Boolean {
        val curr = this
        return if (curr is Cons && that is Cons) {
            val headEq = a.run { curr.head.eq(that.head) }
            if (headEq) curr.tail.eq(that.tail) else false
        } else curr is Nil && that is Nil
    }
}

object IntEq : Eq<Int> {
    override fun Int.eq(that: Int): Boolean {
        return this == that
    }
}

object IntListEq : ListEq<Int>(IntEq)

fun main() {
    val a = Cons(1, Cons(2, Nil))
    val a2 = Cons(1, Cons(2, Nil))
    val b = Cons(1, Nil)
    IntListEq.run {
        check(a.eq(a2))
        check(!a.eq(b))
    }
}