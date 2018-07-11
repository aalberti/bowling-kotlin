package aa

fun score(vararg tries: String): Int = tries.toList()
        .toFrames(42)
        .map { it.value() }
        .sum()

private fun List<String>.toFrames(next: Int):List<Frame> = when {
    isEmpty() -> emptyList()
    else -> dropLast(2).toFrames(penultimate().toInt()) + takeLast(2).toFrame(next)
}
private fun List<String>.toFrame(next: Int): Frame = when {
    this[1] == "/" -> Spare(this[0].toInt(), next)
    else -> Incomplete(this[0].toInt(), this[1].toInt())
}
private fun List<String>.penultimate() = dropLast(1).last()

sealed class Frame {
    abstract fun value(): Int
}
data class Spare(val first: Int, val next: Int): Frame() {
    override fun value() = 10 + next
}
data class Incomplete(val first: Int, val second: Int): Frame() {
    override fun value() = this.first + this.second
}
