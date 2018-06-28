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
        score(*10 times listOf("1", "1")) shouldBe 20
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
    "spare adds next strike" {
        score(
                "0", "/",
                "X",
                "0", "0"
        ) shouldBe 30
    }

    "strike is 10" {
        score(
                "X",
                "0", "0"
        ) shouldBe 10
    }

    "strike adds next try" {
        score(
                "X",
                "1", "0"
        ) shouldBe 12
    }

    "strike adds 2nd next try" {
        score(
                "X",
                "0", "1"
        ) shouldBe 12
    }

    "strike adds next spare with missed 1st try" {
        score(
                "X",
                "0", "/",
                "0", "0"
        ) shouldBe 30
    }

    "strike adds next spare with incomplete 1st try" {
        score(
                "X",
                "2", "/",
                "0", "0"
        ) shouldBe 30
    }

    "strike adds next strike" {
        score(
                "X",
                "X",
                "0", "0"
        ) shouldBe 30
    }

    "11th and 12th strikes count as tens" {
        score(*12 times "X") shouldBe 300
    }

    "11th spare counts as a ten" {
        score(*9 times listOf("0", "0"),
                "X",
                "1", "/"
        ) shouldBe 20
    }

    "11th strike counts as 10 after spare" {
        score(*9 times listOf("0", "0"),
                "0", "/",
                "X"
        ) shouldBe 20
    }

    "- as incomplete's first means 0" {
        score("-", "0") shouldBe 0
    }

    "- as incomplete's second means 0" {
        score("0", "-") shouldBe 0
    }

    "- as spare's first means 0" {
        score(
                "-", "/",
                "0", "0"
        ) shouldBe 10
    }
})

infix fun Int.times(item: String): Array<String> = times(listOf(item))
infix fun Int.times(frame: List<String>): Array<String> = List(this) { frame }.flatMap { it }.toTypedArray()
