package aa

fun score(vararg tries: String): Int = tries.toList().toFrames()?.score() ?: 0

fun List<String>.toFrames(): Frames? = this.toFrames(null)

fun List<String>.toFrames(frames: Frames?): Frames? = when {
    isEmpty() -> frames
    last() == "X" -> dropLast(1).toFrames(
            Strike(next = frames)
    )
    last() == "/" -> dropLast(2).toFrames(
            Spare(
                    first = dropLast(1).last().score(),
                    next = frames
            )
    )
    else -> dropLast(2).toFrames(
            Incomplete(
                    first = dropLast(1).last().score(),
                    second = last().score(),
                    next = frames
            )
    )
}

fun String.score(): Int = if (this == "-") 0 else toInt()

sealed class Frames(open val first: Int, open val next: Frames?) {
    fun score(): Int = frameScore() + (next?.score() ?: 0)
    abstract fun frameScore(): Int
    abstract val two: Int?
}
data class Strike(override val next: Frames? = null): Frames(10, next) {
    override val two: Int? = if (next == null) null else 10 + next.first
    override fun frameScore(): Int = if (next?.two == null) 0 else 10 + (next.two ?: 0)
}
data class Spare(override val first: Int, override val next: Frames? = null): Frames(first, next) {
    override val two: Int = 10
    override fun frameScore(): Int = 10 + (next?.first ?: 0)
}
data class Incomplete(override val first: Int, val second: Int, override val next: Frames? = null): Frames(first, next) {
    override fun frameScore(): Int = first + second
    override val two: Int = first + second
}
