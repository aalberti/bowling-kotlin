package aa

fun score(vararg tries: String): Int = tries.toList()
        .toFrames()
        .map { it.score() }
        .sum()

private fun List<String>.toFrames(): List<Frame> = toFrames(null)

private fun List<String>.toFrames(nextFrame: Frame?): List<Frame> = when (size) {
    0 -> emptyList()
    else -> {
        val lastFrame = takeLast(2).toFrame(nextFrame)
        val rest = dropLast(2)
        rest.toFrames(lastFrame) + lastFrame
    }
}

private fun List<String>.toFrame(nextFrame: Frame?): Frame = when {
    this[1] == "/" -> Spare(
            this[0],
            nextFrame
    )
    else -> Incomplete(
            this[0],
            this[1]
    )
}

sealed class Frame(open val first: String) {
    abstract fun score(): Int
}

data class Spare(override val first: String, val nextFrame: Frame?) : Frame(first) {
    override fun score(): Int = 10 + (nextFrame?.first?.toInt() ?: 0)
}

data class Incomplete(override val first: String, val second: String) : Frame(first) {
    override fun score(): Int {
        return first.toInt() + second.toInt()
    }
}