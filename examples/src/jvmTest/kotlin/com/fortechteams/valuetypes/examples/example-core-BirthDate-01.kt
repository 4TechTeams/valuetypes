// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCoreBirthDate01

import com.fortechteams.valuetypes.core.BirthDate
import io.kotest.matchers.shouldBe
import kotlinx.datetime.LocalDate

fun test() {
  // Create from string
  val birthDate1 = BirthDate.fromString("1990-01-15").getOrThrow()

  // Create from components
  val birthDate2 = BirthDate.fromDate(1990, 1, 15).getOrThrow()

  // Both creation methods produce the same value
  birthDate1 shouldBe birthDate2

  // Access components
  birthDate1.year shouldBe 1990
  birthDate1.month shouldBe 1
  birthDate1.day shouldBe 15

  // Calculate age
  val referenceDate = LocalDate(2024, 1, 15)
  birthDate1.getAgeAt(referenceDate) shouldBe 34
}
}
