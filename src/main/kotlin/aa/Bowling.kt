package aa

class Bowling {
    fun score(vararg tries: String): Int = tries[0].toInt() + tries[1].toInt()
}