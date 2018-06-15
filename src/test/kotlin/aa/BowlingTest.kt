package aa

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class BowlingTest : StringSpec({
    "start at 0" {
        score() shouldBe 0
    }

    "count 1st try" {
        score("1") shouldBe 1
    }

    "count all tries" {
        score(
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
        score(
                "0", "/",
                "0"
        ) shouldBe 10
    }

    "spare is 10 for the whole frame" {
        score(
                "2", "/",
                "0"
        ) shouldBe 10
    }

    "spare is 10 + next try" {
        score(
                "0", "/",
                "1"
        ) shouldBe 12
    }

    "strike is 10" {
        score(
                "X",
                "0", "0"
        ) shouldBe 10
    }

    "strike is 10 + next 2 tries" {
        score(
                "X",
                "1", "1"
        ) shouldBe 14
    }

    "- == 0 for the 1st try" {
        score(
                "-", "0"
        ) shouldBe 0
    }

    "- == 0 for the 2nd try" {
        score(
                "0", "-"
        ) shouldBe 0
    }

    "spare understands -" {
        score(
                "0", "/",
                "-"
        ) shouldBe 10
    }

    "strike understands -" {
        score(
                "X",
                "-", "-"
        ) shouldBe 10
    }
})
