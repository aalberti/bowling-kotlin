package aa

fun score(vararg tries: String): Int = tries.toList()
        .toFrames()
        .map { it.value() }
        .sum()

private fun List<String>.toFrames():List<Frame> = when {
    isEmpty() -> emptyList()
    else -> dropLast(2).toFrames() + takeLast(2).toFrame()
}
private fun List<String>.toFrame(): Frame = when {
    this[1] == "/" -> Spare(this[0].toInt())
    else -> Incomplete(this[0].toInt(), this[1].toInt())
}

sealed class Frame {
    abstract fun value(): Int
}
data class Spare(val first: Int): Frame() {
    override fun value() = 10
}
data class Incomplete(val first: Int, val second: Int): Frame() {
    override fun value() = this.first + this.second
}
