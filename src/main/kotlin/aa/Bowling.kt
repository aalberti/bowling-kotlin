package aa

import kotlin.collections.windowed

fun score(vararg tries: String): Int = tries.toList()
        .windowed(size = 2, partialWindows = true)
        .map { it.toScore() }
        .sum()

private fun List<String>.toScore() = when {
    this[0] == "/" -> 10 + this[1].toInt()
    else -> this[0].toInt()
}