package aa

import kotlin.collections.windowed

fun score(vararg tries: String): Int = tries.toList()
        .windowed(size = 3, partialWindows = true)
        .map { it.tryScore() }
        .sum()

private fun List<String>.tryScore(): Int = when {
    size > 1 && this[1] == "/" -> 0
    this[0] == "/" -> if (size >= 2) this[0].value() + this[1].value() else 0
    this[0] == "X" -> if (size >= 3) this[0].value() + drop(1).value() else 0
    else -> this[0].value()
}

private fun List<String>.value() = if (this[1] == "/") 10 else this[0].value() + this[1].value()

private fun String.value() = when (this) {
    "-" -> 0
    "/", "X" -> 10
    else -> this.toInt()
}
