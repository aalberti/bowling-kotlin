package aa

class Bowling {
    fun score(vararg tries: String): Int = when {
        tries[0] == "1" -> 1
        else -> 0
    }
}