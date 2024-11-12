// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCoreBirthDate09

import com.fortechteams.valuetypes.core.BirthDate
import io.kotest.matchers.shouldBe

fun test() {
  // ISO format works
  val iso = BirthDate.fromString("1990-12-31").getOrThrow()
  iso.toString() shouldBe "1990-12-31"

  // European format works
  val european = BirthDate.fromString("31.12.1990").getOrThrow()
  european.toString() shouldBe "1990-12-31"

  // Slash formats work
  val yearFirst = BirthDate.fromString("1990/12/31").getOrThrow()
  yearFirst.toString() shouldBe "1990-12-31"

  val dayFirst = BirthDate.fromString("31/12/1990").getOrThrow()
  dayFirst.toString() shouldBe "1990-12-31"

  // Invalid format fails
  BirthDate.fromString("invalid-date").isFailure shouldBe true
}
