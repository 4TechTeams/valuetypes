// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCoreBirthDate05

import com.fortechteams.valuetypes.core.BirthDate
import io.kotest.matchers.shouldBe

fun test() {
  // All formats normalize to ISO output
  val date1 = BirthDate.fromString("15.01.1990").getOrThrow()
  date1.toString() shouldBe "1990-01-15"

  val date2 = BirthDate.fromString("1990/01/15").getOrThrow()
  date2.toString() shouldBe "1990-01-15"
}
