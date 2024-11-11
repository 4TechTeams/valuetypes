// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleBirthDate01

import com.fortechteams.valuetypes.person.BirthDate
import io.kotest.matchers.shouldBe
import java.time.LocalDate

fun test() {
  // Create from different string formats
  val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()

  birthDate.toString() shouldBe "1990-01-15"

  // Access components
  birthDate.year shouldBe 1990
  birthDate.month shouldBe 1
  birthDate.day shouldBe 15

  // Calculate age at specific date
  birthDate.getAgeAt(LocalDate.of(2020, 1, 15)) shouldBe 30
}
