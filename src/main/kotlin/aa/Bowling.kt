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
            this[0].value(),
            nextFrame
    )
    else -> Incomplete(
            this[0].value(),
            this[1].value(),
            nextFrame
    )
}

private fun String.value() = if (this == "-") 0 else this.toInt()

sealed class Frame(open val first: Int, open val next: Frame?) {
    fun overallScore(): Int = frameValue() + (next?.overallScore() ?: 0)
    abstract fun frameValue(): Int
    abstract fun firstTwo():Int?
}

data class Spare(override val first: Int, override val next: Frame?) : Frame(first, next) {
    override fun frameValue(): Int = if (next == null) 0 else 10 + next.first
    override fun firstTwo(): Int = 10
}

data class Strike(override val next: Frame?) : Frame(10, next) {
    override fun frameValue(): Int = if (next?.firstTwo() == null) 0 else 10 + next.firstTwo()!!
    override fun firstTwo(): Int? = if (next == null) null else 10 + next.first
}

data class Incomplete(override val first: Int, val second: Int, override val next: Frame?) : Frame(first, next) {
    override fun frameValue(): Int = first + second
    override fun firstTwo(): Int = first + second
}
