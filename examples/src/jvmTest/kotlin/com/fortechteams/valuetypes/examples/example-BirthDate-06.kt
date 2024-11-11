// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleBirthDate06

import com.fortechteams.valuetypes.person.BirthDate
import io.kotest.matchers.shouldBe
import java.time.LocalDate

fun test() {
  val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()
  val today = LocalDate.of(2024, 1, 15)
  birthDate.getAgeAt(today) shouldBe 34
}
