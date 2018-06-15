package aa

object Bowling {
    fun score(vararg shots: String?): Int = shots.firstOrNull()?.toInt() ?: 0
}