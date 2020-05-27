package functional

import java.time.DayOfWeek

/**
 * @author qiubaisen
 * @date 2020/5/24
 */


class PartialFunction<in P1, out R>(val defineAt: (P1) -> Boolean, val f: (P1) -> R) : (P1) -> R {
    override fun invoke(p1: P1): R {
        if (defineAt(p1)) {
            return f(p1)
        } else {
            throw IllegalArgumentException("Value:[$p1] is not supported by this function")
        }
    }
}

data class MeetEvent(val date: DayOfWeek, val greeting: String)

val Boss = run {
    val defineAt: (MeetEvent) -> Boolean = { it.date == DayOfWeek.MONDAY }
    val handler: (MeetEvent) -> String = { "Boss\t>>It's ${it.date.name}! ${it.greeting}! Hurry Up!" }
    PartialFunction(defineAt, handler)
}

val Worker = run {
    val defineAt: (MeetEvent) -> Boolean = { it.date != DayOfWeek.SATURDAY && it.date != DayOfWeek.SUNDAY }
    val handler: (MeetEvent) -> String = { "Worker\t>>It's ${it.date.name}! ${it.greeting}! I have to work!" }
    PartialFunction(defineAt, handler)
}
val Programmer = run {
    val defineAt: (MeetEvent) -> Boolean = { true }
    val handler: (MeetEvent) -> String = { "Programmer\t>>It's ${it.date.name}! ${it.greeting}! Work Everyday!" }
    PartialFunction(defineAt, handler)
}

object PartialFunctionMonoid : PlusableMonoid<PartialFunction<MeetEvent, String>> {
    override val zero: PartialFunction<MeetEvent, String> = PartialFunction({ false }, { "" })

    override fun PartialFunction<MeetEvent, String>.append(b: PartialFunction<MeetEvent, String>): PartialFunction<MeetEvent, String> {
        return PartialFunction({ this.defineAt(it) || b.defineAt(it) }) {
            if (this.defineAt(it)) this(it) else b(it)
        }
    }

    override operator fun PartialFunction<MeetEvent, String>.plus(other: PartialFunction<MeetEvent, String>) =
        this.append(other)
}

fun main() {
    PartialFunctionMonoid.run {
        val events = listOf(
            MeetEvent(DayOfWeek.MONDAY, "Hello"),
            MeetEvent(DayOfWeek.TUESDAY, "Hi"),
            MeetEvent(DayOfWeek.FRIDAY, "Yeah"),
            MeetEvent(DayOfWeek.SUNDAY, "Wow")
        )

        println("Boss > Worker > Programmer")
        (Boss + Worker + Programmer).let { func -> events.forEach { func(it).also(::println) } }

        println("Boss > Programmer > Worker")
        (Boss + Programmer + Worker).let { func -> events.forEach { func(it).also(::println) } }

        println("Worker > Programmer > Boss")
        (Worker + Programmer + Boss).let { func -> events.forEach { func(it).also(::println) } }

        println("Only programmer work on Sunday")
        (Boss + Worker).invoke(MeetEvent(DayOfWeek.SUNDAY, "hey")).let(::println)
    }
}