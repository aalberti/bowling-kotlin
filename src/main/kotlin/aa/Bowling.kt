package aa

fun score(vararg tries: String): Int = tries.asIterable()
        .windowed(size = 3, step = 2, partialWindows = true)
        .map {
            when {
                it.size == 1 -> it[0].toInt()
                it[0] == "X" -> 10
                it[1] == "/" -> 10 + it[2].toInt()
                else -> it[0].toInt() + it[1].toInt()
            }
        }
        .sum()
