package aa

object Bowling {
    fun score(vararg tries: String): Int = tries.map { it.toInt() }.sum()
}