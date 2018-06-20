package aa

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class BowlingTest : StringSpec({
    "count 0" {
        score("0", "0") shouldBe 0
    }
    "count 1" {
        score("1", "0") shouldBe 1
    }
    "count many" {
        score("9", "0") shouldBe 9
    }
    "count 2nd try" {
        score("0", "9") shouldBe 9
    }
    "count many tries" {
        score(*20 times "1") shouldBe 20
    }
    "spare before miss" {
        score(
                "0", "/",
                "0", "0"
        ) shouldBe 10
    }
    "spare counts next incomplete try" {
        score(
                "0", "/",
                "1", "0"
        ) shouldBe 12
    }
    "support '-' in independent tries" {
        score("-", "-") shouldBe 0
    }
    "support '-' after spare" {
        score(
                "-", "/",
                "-", "-"
        ) shouldBe 10
    }
    "strike followed by 2 misses is 10" {
        score(
                "X",
                "-", "-"
        ) shouldBe 10
    }
    "strike adds next 2 shots" {
        score(
                "X",
                "1", "1"
        ) shouldBe 14
    }
})

infix fun Int.times(item:String):Array<String> = Array(this) {item}
