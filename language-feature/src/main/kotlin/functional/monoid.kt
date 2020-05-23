package functional

/**
 * @author qiubaisen
 * @date 2020/5/24
 */

interface Monoid<A> {
    val zero: A
    fun A.append(b: A): A
}

interface PlusableMonoid<A> : Monoid<A>{
    operator fun A.plus(other: A):A
}

object StringConcatMonoid : Monoid<String> {
    override val zero: String = ""
    override fun String.append(b: String): String = this + b
}

fun <A> List<A>.sum(ma: Monoid<A>): A {
    return ListFoldable.run {
        this@sum.fold(ma.zero) { s, i ->
            ma.run { s.append(i) }
        }
    }
}

fun main() {
    val cons = Cons("Dive ", Cons("into ", Cons("Kotlin", Nil)))
    cons.sum(StringConcatMonoid).let(::println)
}