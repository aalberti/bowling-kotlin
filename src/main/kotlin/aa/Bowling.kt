package aa

fun score(vararg tries: String): Int = tries.asIterable()
        .flatMap { if (it == "X") listOf("X", "?") else listOf(it) }
        .windowed(size = 4, step = 2, partialWindows = true)
        .map { Frame(it) }
        .map { it.score() }
        .sum()

class Frame(private val tries: List<String>) {

    fun score(): Int = when {
        tries.size == 1 -> firstScore()
        isStrike() -> 10 + nextFirstScore() + nextSecondScore()
        isSpare() -> 10 + nextFirstScore()
        else -> firstScore() + secondScore()
    }

    private fun isSpare() = tries[1] == "/"
    private fun isStrike() = tries[0] == "X"
    private fun firstScore(): Int = tries[0].toInt()
    private fun secondScore(): Int = tries[1].toInt()
    private fun nextFirstScore(): Int = tries[2].toInt()
    private fun nextSecondScore(): Int = tries[3].toInt()
}
