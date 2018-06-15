package aa

object Bowling {
    fun score(vararg tries: String?): Int = tries.firstOrNull()?.toInt() ?: 0
}