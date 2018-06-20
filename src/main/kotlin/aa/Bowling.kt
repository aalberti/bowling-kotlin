package aa

fun score(vararg tries: String): Int {
    return tries.toList()
            .toFrames()
            .map { it.score() }
            .sum()
}

fun List<String>.toFrames(): List<Frame> = when {
    isEmpty() -> emptyList()
    this[0] == "X" -> listOf(Frame(this)) + drop(1).toFrames()
    else -> listOf(Frame(this)) + drop(2).toFrames()
}

data class Frame(val first: String, val second: String?, val third: String? = null) {
    constructor(tries: List<String>) : this(
            tries[0],
            if (tries.size > 1) tries[1] else null,
            if (tries.size > 2) tries[2] else null
    )

    fun score(): Int = when {
        first == "X" -> if (third == null) 0 else first.value() + nextTwo()
        second == "/" -> if (third == null) 0 else 10 + third.value()
        else -> first.value() + second?.value()!!
    }

    private fun nextTwo() = if (third == "/") 10 else second?.value()!! + third?.value()!!
}

private fun String.value() = when (this) {
    "-" -> 0
    "/", "X" -> 10
    else -> this.toInt()
}
