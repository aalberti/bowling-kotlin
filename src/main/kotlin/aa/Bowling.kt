package aa

fun score(vararg tries: String): Int = tries.asIterable()
        .flatMap { if (it == "X") listOf("X", "?") else listOf(it) }
        .windowed(size = 4, step = 2, partialWindows = true)
        .map { Frame(it) }
        .map { it.score() }
        .sum()

class Frame(private val tries: List<String>) {

    fun score(): Int = when {
        tries.size == 1 -> first().score()
        isStrike() -> 10 + nextFirst().score() + nextSecond().score()
        isSpare() -> 10 + nextFirst().score()
        else -> first().score() + second().score()
    }

    private fun isSpare() = second() == "/"
    private fun isStrike() = first() == "X"

    private fun first() = tries[0]
    private fun second() = tries[1]
    private fun nextFirst() = tries[2]
    private fun nextSecond() = tries[3]

    private fun String.score() = if (this == "-") 0 else toInt()
}
