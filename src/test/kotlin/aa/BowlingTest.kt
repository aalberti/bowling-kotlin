package aa

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class BowlingTest : StringSpec({
    "count 0" {
        Bowling().score("0", "0") shouldBe 0
    }
    "count 1" {
        Bowling().score("1", "0") shouldBe 1
    }
    "count many" {
        Bowling().score("9", "0") shouldBe 9
    }
    "count 2nd try" {
        Bowling().score("0", "9") shouldBe 9
    }
})

