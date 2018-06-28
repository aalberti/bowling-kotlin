package aa

fun score(vararg tries: String): Int = tries.toList()
        .toFrames()
        .overallScore()

private fun List<String>.toFrames(): Frame = toFrames(null)!!

private fun List<String>.toFrames(nextFrame: Frame?): Frame? = when (size) {
    0 -> nextFrame
    else -> {
        val frameSize = if (last() == "X") 1 else 2
        val lastFrame = takeLast(frameSize).toFrame(nextFrame)
        val head = dropLast(frameSize)
        head.toFrames(lastFrame)
    }
}

private fun List<String>.toFrame(nextFrame: Frame?): Frame = when {
    this[0] == "X" -> Strike(nextFrame)
    this[1] == "/" -> Spare(
            this[0].toInt(),
            nextFrame
    )
    else -> Incomplete(
            this[0].toInt(),
            this[1].toInt(),
            nextFrame
    )
}

sealed class Frame(open val first: Int, open val next: Frame?) {
    abstract fun frameValue(): Int
    fun overallScore(): Int = frameValue() + (next?.overallScore() ?: 0)
    abstract fun firstTwo():Int
}

data class Spare(override val first: Int, override val next: Frame?) : Frame(first, next) {
    override fun frameValue(): Int = 10 + (next?.first ?: 42)
    override fun firstTwo(): Int = 10
}

data class Strike(override val next: Frame?) : Frame(10, next) {
    override fun frameValue(): Int = 10 + (next?.firstTwo() ?: 42)
    override fun firstTwo(): Int = 10 + (next?.first ?: 42)
}

data class Incomplete(override val first: Int, val second: Int, override val next: Frame?) : Frame(first, next) {
    override fun frameValue(): Int = first + second
    override fun firstTwo(): Int = first + second
}
