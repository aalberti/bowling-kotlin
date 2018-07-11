package aa

fun score(vararg tries: String): Int = tries.toList()
        .windowed(size = 2, step = 2, partialWindows = true)
        .map { it.value() }
        .sum()

private fun List<String>.value() = when {
    this[1] == "/" -> 10
    else -> this[0].toInt() + this[1].toInt()
}
