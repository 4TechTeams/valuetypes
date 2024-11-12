// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCoreBirthDate04

import com.fortechteams.valuetypes.core.BirthDate
import io.kotest.matchers.shouldBe

fun test() {
  val midMonthDate = BirthDate.fromString("1990-01-15").getOrThrow()
  midMonthDate.day shouldBe 15

  val endOfMonthDate = BirthDate.fromString("1990-01-31").getOrThrow()
  endOfMonthDate.day shouldBe 31
}
