package aa

const val strikePadding = "?"

fun score(vararg tries: String): Int = tries.asIterable()
        .flatMap { if (it == "X") listOf("X", strikePadding) else listOf(it) }
        .windowed(size = 5, step = 2, partialWindows = true)
        .map { Frame(it) }
        .map { it.score() }
        .sum()

class Frame(private val tries: List<String>) {

    fun score(): Int = when {
        tries.size == 1 -> first.score()
        isStrike() -> 10 + nextTwoCounts()
        isSpare() -> 10 + nextFirst.score()
        else -> first.score() + second.score()
    }

    private fun nextTwoCounts() = if (nextSecond == "/") 10 else nextFirst.score() + nextSecond.score()
    private fun isSpare() = second == "/"
    private fun isStrike() = first == "X"

    private val first get() = tries[0]
    private val second get() = tries[1]
    private val nextFirst get() = tries[2]
    private val nextSecond get() = if (tries[3] == strikePadding) tries[4] else tries[3]

    private fun String.score() = when {
        this == "-" -> 0
        this == "X" -> 10
        this == "/" -> 10
        else -> toInt()
    }
}
