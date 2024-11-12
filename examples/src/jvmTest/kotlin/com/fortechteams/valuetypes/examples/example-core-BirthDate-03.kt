// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCoreBirthDate03

import com.fortechteams.valuetypes.core.BirthDate
import io.kotest.matchers.shouldBe

fun test() {
  // January = 1, December = 12
  val januaryDate = BirthDate.fromString("1990-01-15").getOrThrow()
  januaryDate.month shouldBe 1

  val decemberDate = BirthDate.fromString("1990-12-31").getOrThrow()
  decemberDate.month shouldBe 12
}
