package aa

fun score(vararg tries: String): Int = tries.toList()
        .windowed(size = 3, step = 2, partialWindows = true)
        .map { it.toFrame() }
        .map { it.value() }
        .sum()

private fun List<String>.toFrame(): Frame = if (this[1] == "/") Spare(this[0], this[2]) else Incomplete(this[0], this[1])

sealed class Frame {
    abstract fun value(): Int
}

data class Spare(val first: String, val next: String?) : Frame() {
    override fun value(): Int = 10 + (next?.toInt() ?: 0)
}

data class Incomplete(val first: String, val second: String): Frame() {
    override fun value(): Int {
        return first.toInt() + second.toInt()
    }
}