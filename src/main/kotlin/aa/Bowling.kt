package aa

fun score(vararg tries: String): Int = tries.toList()
        .windowed(size = 3, step = 2, partialWindows = true)
        .map { it.value() }
        .sum()

private fun List<String>.value(): Int = when {
    this[1] == "/" -> 10 + this[2].toInt()
    else -> this[0].toInt() + this[1].toInt()
}