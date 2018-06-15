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

    "spare is 10" {
        Bowling.score(
                "0", "/",
                "0") shouldBe 10
    }

    "spare is 10 for the frame" {
        Bowling.score(
                "2", "/",
                "0") shouldBe 10
    }

    "spare is 10 + next try" {
        Bowling.score(
                "0", "/",
                "1") shouldBe 12
    }
})
