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

sealed class Frames(open val first: Int, open val next: Frames?) {
    fun score(): Int = frameScore() + (next?.score() ?: 0)
    abstract fun frameScore(): Int
    abstract operator fun plus(next: Frames): Frames
    abstract val two: Int
}
data class Strike(override val next: Frames? = null): Frames(10, next) {
    override val two: Int = 10 + (next?.first ?: 0)
    override fun frameScore(): Int = 10 + (next?.two ?: 0)
    override operator fun plus(next: Frames): Frames = Strike(next)
}
data class Spare(override val first: Int, override val next: Frames? = null): Frames(first, next) {
    override val two: Int = 10
    override fun frameScore(): Int = 10 + (next?.first ?: 0)
    override operator fun plus(next: Frames): Frames = Spare(first, next)
}
data class Incomplete(override val first: Int, val second: Int, override val next: Frames? = null): Frames(first, next) {
    override fun frameScore(): Int = first + second
    override val two: Int = first + second
    override operator fun plus(next: Frames): Frames = Incomplete(first, second, next)
}
