package aa

fun score(vararg tries: String): Int = tries.toList()
        .toFrames(null)
        .score()

private fun List<String>.toFrames(next: Frame?): Frame =
        if (isEmpty()) next!!
        else {
            val frameSize = if (last() == "X") 1 else 2
            val frame = takeLast(frameSize).toFrame(next)
            dropLast(frameSize).toFrames(frame)
        }

private fun List<String>.toFrame(next: Frame?): Frame = when {
    this[0] == "X" -> Strike(next)
    this[1] == "/" -> Spare(this[0].toInt(), next)
    else -> Incomplete(this[0].toInt(), this[1].toInt(), next)
}

private sealed class Frame(open val first: Int, open val next: Frame?) {
    fun score(): Int = value() + (next?.score() ?: 0)
    abstract fun value(): Int
    abstract fun firstTwo(): Int
}

private data class Strike(override val next: Frame?) : Frame(10, next) {
    override fun value(): Int = 10 + (next?.firstTwo() ?: 42)
    override fun firstTwo(): Int = 10 + (next?.first ?: 42)
}

private data class Spare(override val first: Int, override val next: Frame?) : Frame(first, next) {
    override fun value() = 10 + (next?.first ?: 42)
    override fun firstTwo(): Int = 10
}

private data class Incomplete(override val first: Int, val second: Int, override val next: Frame?) : Frame(first, next) {
    override fun value() = this.first + this.second
    override fun firstTwo(): Int = this.first + this.second
}
