package aa

fun score(vararg tries: String): Int = tries.toList()
        .windowed(size = 2, step = 2, partialWindows = true)
        .map { it.toFrame() }
        .map { it.value() }
        .sum()

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
