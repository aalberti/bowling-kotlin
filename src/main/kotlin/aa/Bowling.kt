package aa

fun score(vararg tries: String): Int = tries.map { it.value() }.sum()

private fun String.value() = when (this) {
    "/" -> 10
    else -> toInt()
}