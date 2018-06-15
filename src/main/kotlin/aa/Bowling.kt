package aa

fun score(vararg tries: String): Int = tries.asIterable()
        .flatMap { if (it == "X") listOf("X", "?") else listOf(it) }
        .windowed(size = 5, step = 2, partialWindows = true)
        .map { Frame(it) }
        .map { it.score() }
        .sum()

class Frame(private val tries: List<String>) {

    fun score(): Int = when {
        tries.size == 1 -> first.score()
        isStrike() -> 10 + nextFirst.score() + nextSecond.score()
        isSpare() -> 10 + nextFirst.score()
        else -> first.score() + second.score()
    }

    private fun isSpare() = second == "/"
    private fun isStrike() = first == "X"

    private val first get() = tries[0]
    private val second get() = tries[1]
    private val nextFirst get() = tries[2]
    private val nextSecond get() = tries[3]

    private fun String.score() = when {
        this == "-" -> 0
        this == "X" -> 10
        else -> toInt()
    }
}
