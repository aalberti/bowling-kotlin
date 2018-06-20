package aa

fun score(vararg tries: String): Int = tries.map { it.toScore() }.sum()

private fun String.toScore() = when {
    this == "/" -> 10
    else -> toInt()
}