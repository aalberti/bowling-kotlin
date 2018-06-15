package aa

object Bowling {
    fun score(vararg tries: String): Int = tries.asIterable()
            .map {
                if (it == "/")
                    10
                else
                    it.toInt()
            }
            .sum()
}
