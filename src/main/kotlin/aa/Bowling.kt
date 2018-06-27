package aa

fun score(vararg tries: String): Int = tries.toList()
        .windowed(size = 3, step = 2, partialWindows = true)
        .map {it.toFrame() }
        .map { it.value() }
        .sum()

private fun List<String>.toFrame(): Frame = Frame(this[0], this[1], if (size > 2) this[2] else null)

data class Frame(val first:String, val second: String, val next: String?)

private fun Frame.value(): Int = when {
    second == "/" -> 10 + (next?.toInt() ?: 0)
    else -> first.toInt() + second.toInt()
}