package aa

fun score(vararg tries: String): Int {
    return tries.toList()
            .toFrames()
            .map { it.score() }
            .sum()
}

fun List<String>.toFrames(): List<Frame> = when {
    isEmpty() -> emptyList()
    this[0] == "X" -> listOf(Frame.of(this)) + drop(1).toFrames()
    else -> listOf(Frame.of(this)) + drop(2).toFrames()
}

sealed class Frame {
    companion object {
        fun of(tries: List<String>): Frame = when {
            tries[0] == "X" -> Strike(
                    if (tries.size > 1) tries[1] else null,
                    if (tries.size > 2) tries[2] else null
            )
            tries[1] == "/" -> Spare(
                    if (tries.size > 2) tries[2] else null
            )
            else -> Incomplete(
                    tries[0],
                    if (tries.size > 1) tries[1] else null,
                    if (tries.size > 2) tries[2] else null
            )
        }
    }

    abstract fun score(): Int
}

data class Strike(val nextFirst: String?, val nextSecond: String?) : Frame() {
    override fun score(): Int = if (nextSecond == null) 0 else 10 + nextTwo()
    private fun nextTwo() = if (nextSecond == "/") 10 else nextFirst?.value()!! + nextSecond?.value()!!
}

data class Spare(val next: String?) : Frame() {
    override fun score(): Int = if (next == null) 0 else 10 + next.value()
}

data class Incomplete(val first: String, val second: String?, val third: String? = null) : Frame() {
    override fun score(): Int = when (second) {
        "/" -> if (third == null) 0 else 10 + third.value()
        else -> first.value() + second?.value()!!
    }
}

private fun String.value() = when (this) {
    "-" -> 0
    "/", "X" -> 10
    else -> this.toInt()
}
