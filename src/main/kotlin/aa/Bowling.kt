package aa

fun score(vararg tries: String): Int {
    return tries.toList()
            .toFrames()
            ?.score() ?: 0
}

fun List<String>.toFrames(): Frame? = toFrames(null)

private fun List<String>.toFrames(nextFrame: Frame?): Frame? = when {
    isEmpty() -> nextFrame
    last() == "X" -> dropLast(1).toFrames(
            Strike(
                    nextFrame
            )
    )
    last() == "/" -> dropLast(2).toFrames(
            Spare(
                    dropLast(1).last().value(),
                    nextFrame
            )
    )
    else -> dropLast(2).toFrames(
            Incomplete(
                    dropLast(1).last().value(),
                    last().value(),
                    nextFrame
            )
    )
}

sealed class Frame(open val first: Int, open val next: Frame?) {
    fun score(): Int = frameScore() + (next?.score() ?: 0)
    abstract fun frameScore(): Int
    abstract val firstTwo: Int?
}

data class Strike(override val next: Frame?) : Frame(10, next) {
    override fun frameScore(): Int = if (next?.firstTwo == null) 0 else 10 + (next.firstTwo ?: 0)
    override val firstTwo: Int? = if (next == null) null else 10 + next.first
}

data class Spare(override val first: Int, override val next: Frame?) : Frame(first, next) {
    override fun frameScore(): Int = if (next == null) 0 else 10 + next.first
    override val firstTwo: Int = 10
}

data class Incomplete(override val first: Int, val second: Int, override val next: Frame?) : Frame(first, next) {
    override fun frameScore(): Int = first + second
    override val firstTwo: Int = first + second
}

private fun String.value() = if (this == "-") 0 else this.toInt()
