package aa

fun score(vararg tries: String): Int = tries.toList()
        .toFrames(Incomplete(42, 42))
        .map { it.value() }
        .sum()

private fun List<String>.toFrames(next: Frame): List<Frame> = when {
    isEmpty() -> emptyList()
    else -> {
        val frameSize = if (last() == "X") 1 else 2
        val frame = takeLast(frameSize).toFrame(next)
        dropLast(frameSize).toFrames(frame) + frame
    }
}

private fun List<String>.toFrame(next: Frame): Frame = when {
    this[0] == "X" -> Strike(next)
    this[1] == "/" -> Spare(this[0].toInt(), next)
    else -> Incomplete(this[0].toInt(), this[1].toInt())
}

sealed class Frame(open val first: Int) {
    abstract fun value(): Int
}

data class Strike(val next: Frame) : Frame(10) {
    override fun value(): Int = 10 + next.value()
}

data class Spare(override val first: Int, val next: Frame) : Frame(first) {
    override fun value() = 10 + next.first
}

data class Incomplete(override val first: Int, val second: Int) : Frame(first) {
    override fun value() = this.first + this.second
}
