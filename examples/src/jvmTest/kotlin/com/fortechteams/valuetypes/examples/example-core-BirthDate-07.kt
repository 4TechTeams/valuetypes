// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCoreBirthDate07

import com.fortechteams.valuetypes.core.BirthDate
import io.kotest.matchers.shouldBe
import kotlinx.datetime.LocalDate

fun test() {
  val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()

  // Using a fixed date for testing
  val fixedDate = LocalDate(2024, 1, 15)
  birthDate.getAgeAt(fixedDate) shouldBe 34

  // Current age will depend on system clock
  val currentAge = birthDate.getAge()
  println("Current age: $currentAge")
}
