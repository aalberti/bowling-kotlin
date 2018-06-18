package aa

fun score(vararg tries: String): Int = tries.toList().toFrames()?.score() ?: 0

fun List<String>.toFrames(): Frames? = this.toFrames(null)

fun List<String>.toFrames(frames: Frames?): Frames? = when {
    isEmpty() -> frames
    last() == "X" -> dropLast(1).toFrames(Strike(frames))
    last() == "/" -> dropLast(2).toFrames(Spare(dropLast(1).last().score(), frames))
    else -> dropLast(2).toFrames(Incomplete(dropLast(1).last().score(), last().score(), frames))
}

fun String.score(): Int = if (this == "-") 0 else toInt()

sealed class Frames(open val first: Int) {
    abstract operator fun plus(next: Frames): Frames
    abstract fun score(): Int
    abstract val two: Int
}
data class Strike(val next: Frames? = null): Frames(10) {
    override val two: Int = 10 + (next?.first ?: 0)
    override fun score(): Int = 10 + (next?.two ?: 0) + (next?.score() ?: 0)
    override operator fun plus(next: Frames): Frames = Strike(next)
}
data class Spare(override val first: Int, val next: Frames? = null): Frames(first) {
    override val two: Int = 10
    override fun score(): Int = 10 + (next?.first ?: 0) + (next?.score() ?: 0)
    override operator fun plus(next: Frames): Frames = Spare(first, next)
}
data class Incomplete(override val first: Int, val second: Int, val next: Frames? = null): Frames(first) {
    override fun score(): Int = first + second + (next?.score() ?: 0)
    override val two: Int = first + second
    override operator fun plus(next: Frames): Frames = Incomplete(first, second, next)
}
