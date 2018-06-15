package aa

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class BowlingTest : StringSpec({
    "start at 0" {
        Bowling.score() shouldBe 0
    }

    "count 1st shot" {
        Bowling.score("1") shouldBe 1
    }
})

