package aa

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class BowlingTest : StringSpec({
    "compile" {
        Bowling().score() shouldBe 0
    }
})

