package aa

import kotlin.collections.windowed

fun score(vararg tries: String): Int = tries.toList()
        .windowed(size = 2, partialWindows = true)
        .map { it.tryScore() }
        .sum()

private fun List<String>.tryScore() = when {
    this[0] == "/" -> this[0].value() + this[1].value()
    else -> this[0].value()
}

private fun String.value() = when(this) {
    "-" -> 0
    "/" -> 10
    else -> this.toInt()
}