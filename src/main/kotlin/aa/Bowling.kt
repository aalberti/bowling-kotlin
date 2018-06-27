package aa

fun score(vararg tries: String): Int = tries.toList()
        .toFrames()
        .overallScore()

private fun List<String>.toFrames(): Frame = toFrames(null)!!

private fun List<String>.toFrames(nextFrame: Frame?): Frame? = when (size) {
    0 -> nextFrame
    else -> {
        val lastFrame = takeLast(2).toFrame(nextFrame)
        val head = dropLast(2)
        head.toFrames(lastFrame)
    }
}

private fun List<String>.toFrame(nextFrame: Frame?): Frame = when {
    this[1] == "/" -> Spare(
            this[0],
            nextFrame
    )
    else -> Incomplete(
            this[0],
            this[1],
            nextFrame
    )
}

sealed class Frame(open val first: String, open val next: Frame?) {
    abstract fun frameValue(): Int
    fun overallScore(): Int = frameValue() + (next?.overallScore() ?: 0)
}

data class Spare(override val first: String, override val next: Frame?) : Frame(first, next) {
    override fun frameValue(): Int = 10 + (next?.first?.toInt() ?: 0)
}

data class Incomplete(override val first: String, val second: String, override val next: Frame?) : Frame(first, next) {
    override fun frameValue(): Int {
        return first.toInt() + second.toInt()
    }
}