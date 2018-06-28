package aa

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class BowlingTest : StringSpec({
    "count 0" {
        score("0", "0") shouldBe 0
    }

    "count 1 try" {
        score("1", "0") shouldBe 1
    }

    "count many tries" {
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

    "count spare as 10" {
        score(
                "0", "/",
                "0", "0"
        ) shouldBe 10
    }

    "ignore 1st try of a spare" {
        score(
                "9", "/",
                "0", "0"
        ) shouldBe 10
    }

    "spare adds next try" {
        score(
                "0", "/",
                "1", "0"
        ) shouldBe 12
    }

    "strike is 10" {
        score(
                "X",
                "0", "0"
        ) shouldBe 10
    }
})
