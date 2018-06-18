package aa

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class BowlingTest : StringSpec({
    "start at 0" {
        score() shouldBe 0
    }

    "count 1st try" {
        score("1", "0") shouldBe 1
    }

    "count all tries" {
        score(*20 times "1") shouldBe 20
    }

    "spare is 10" {
        score(
                "0", "/",
                "0", "0"
        ) shouldBe 10
    }

    "spare is 10 for the whole frame" {
        score(
                "2", "/",
                "0", "0"
        ) shouldBe 10
    }

    "spare is 10 + next try" {
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
                "-", "-"
        ) shouldBe 10
    }

    "strike understands -" {
        score(
                "X",
                "-", "-"
        ) shouldBe 10
    }

    "strike after spare" {
        score(
                "0", "/",
                "X",
                "-", "-"
        ) shouldBe 30
    }

    "spare after missed after strike" {
        score(
                "X",
                "-", "/",
                "-", "-"
        ) shouldBe 30
    }

    "spare after incomplete after strike" {
        score(
                "X",
                "2", "/",
                "-", "-"
        ) shouldBe 30
    }

    "strike after strike" {
        score(
                "X",
                "X",
                "-", "-"
        ) shouldBe 30
    }

    "3 strikes in a row" {
        score(
                "X",
                "X",
                "X",
                "-", "-"
        ) shouldBe 60
    }

    "strikes don't count as strikes after 10th frame" {
        score(*12 times "X") shouldBe 300
    }
})

infix fun Int.times(item:String) = Array(this) {item}
