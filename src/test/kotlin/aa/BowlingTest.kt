package aa

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class BowlingTest : StringSpec({
    "count 0" {
        score("0", "0") shouldBe 0
    }
    "count 1 for the 1st try" {
        score("1", "0") shouldBe 1
    }
    "count many for the 1st try" {
        score("9", "0") shouldBe 9
    }
    "count 2nd try" {
        score("0", "1") shouldBe 1
    }
    "sum up many incomplete tries" {
        score(*20 times "1") shouldBe 20
    }
    "spare after gutter is 10" {
        score(
                "0", "/",
                "0", "0"
        ) shouldBe 10
    }
    "spare after incomplete is 10" {
        score(
                "9", "/",
                "0", "0"
        ) shouldBe 10
    }
    "spare adds next incomplete try" {
        score(
                "9", "/",
                "1", "0"
        ) shouldBe 12
    }
    "strike is 10" {
        score(
                "X",
                "0", "0"
        ) shouldBe 10
    }
    "strike adds next incomplete 2" {
        score(
                "X",
                "1", "1"
        ) shouldBe 14
    }
})

private infix fun Int.times(item: String): Array<out String> = Array(this) { item }
