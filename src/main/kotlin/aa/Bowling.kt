package aa

fun score(vararg tries: String): Int {
    return tries.toList()
            .toFrames()
            ?.score() ?: 0
}

fun List<String>.toFrames(): Frames? {
    return if (isEmpty())
        null
    else {
        val frameSize = if (this[0] == "X") 1 else 2
        val next = drop(frameSize).toFrames()
        when {
            this[0] == "X" -> Strike(
                    next
            )
            this[1] == "/" -> Spare(
                    this[0].value(),
                    next
            )
            else -> Incomplete(
                    this[0].value(),
                    this[1].value(),
                    next
            )
        }
    }
}

sealed class Frames(open val first: Int, open val next: Frames?) {
    fun score():Int = frameScore() + (next?.score() ?: 0)
    abstract fun frameScore(): Int
    abstract val firstTwo: Int?
}
data class Strike(override val next: Frames?) : Frames(10, next) {
    override fun frameScore(): Int = if (next?.firstTwo == null) 0 else 10 + (next.firstTwo ?: 0)
    override val firstTwo: Int? = if (next == null) null else 10 + next.first
}
data class Spare(override val first: Int, override val next: Frames?) : Frames(first, next) {
    override fun frameScore(): Int = if (next == null) 0 else 10 + next.first
    override val firstTwo: Int = 10
}
data class Incomplete(override val first: Int, val second: Int, override val next: Frames?) : Frames(first, next) {
    override fun frameScore(): Int = first + second
    override val firstTwo: Int = first + second
}

private fun String.value() = if (this == "-") 0 else this.toInt()
