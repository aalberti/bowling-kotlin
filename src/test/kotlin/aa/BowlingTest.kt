package aa

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class BowlingTest : StringSpec({
    "start at 0" {
        Bowling.score() shouldBe 0
    }

    "count 1st try" {
        Bowling.score("1") shouldBe 1
    }

    "count all tries" {
        Bowling.score(
                "1", "1",
                "1", "1",
                "1", "1",
                "1", "1",
                "1", "1",
                "1", "1",
                "1", "1",
                "1", "1",
                "1", "1",
                "1", "1"
                ) shouldBe 20
    }
})
