package aa

fun score(vararg tries: String): Int {
    return tries.toList()
            .toFrames()
            ?.score() ?: 0
}

fun List<String>.toFrames(): Frame? {
    return if (isEmpty())
        null
    else {
        val frameSize = if (this[0] == "X") 1 else 2
        val next = drop(frameSize).toFrames()
        when {
            this[0] == "X" -> Strike(
                    if (this.size > 1) this[1] else null,
                    if (this.size > 2) this[2] else null,
                    next
            )
            this[1] == "/" -> Spare(
                    this[0],
                    next
            )
            else -> Incomplete(
                    this[0],
                    if (this.size > 1) this[1] else null,
                    if (this.size > 2) this[2] else null,
                    next
            )
        }
    }
}

//TODO pull nextTwo up
sealed class Frame(open val first: String, open val next: Frame?) {
    fun score():Int = frameScore() + (next?.score() ?: 0)
    abstract fun frameScore(): Int
}
data class Strike(val nextFirst: String?, val nextSecond: String?, override val next: Frame?) : Frame("10", next) {
    override fun frameScore(): Int = if (next == null) 0 else 10 + nextTwo()
    private fun nextTwo() = if (nextSecond == "/") 10 else nextFirst?.value()!! + nextSecond?.value()!!
}
data class Spare(override val first: String, override val next: Frame?) : Frame(first, next) {
    override fun frameScore(): Int = if (next == null) 0 else 10 + next.first.value()
}
data class Incomplete(override val first: String, val second: String?, val third: String? = null, override val next: Frame?) : Frame(first, next) {
    override fun frameScore(): Int = when (second) {
        "/" -> if (third == null) 0 else 10 + third.value()
        else -> first.value() + second?.value()!!
    }
}

private fun String.value() = when (this) {
    "-" -> 0
    "/", "X" -> 10
    else -> this.toInt()
}
