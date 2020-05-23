package functional

/**
 * @author qiubaisen
 * @date 2020/5/23
 */
interface Show<F> {
    fun F.show(): String
}

class Book(val name: String)
object BookShow : Show<Book> {
    override fun Book.show(): String = name
}

abstract class ListShow<A>(val a: Show<A>) : Show<Kind<List.K, A>> {
    override fun Kind<List.K, A>.show(): String {
        val fa = this
        return ListFoldable.run {
            fa.fold(listOf<String>()) { r, i ->
                r + a.run { i.show() }
            }.joinToString(prefix = "[", postfix = "]")
        }
    }
}

object BookListShow : ListShow<Book>(BookShow)

fun main() {
    BookListShow.run {
        val bookList = Cons(Book("Dive into Kotlin"), Cons(Book("Thinking in Java"), Nil))
        bookList.show().let(::println)
    }
}