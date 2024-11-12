// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCoreBirthDate08

import com.fortechteams.valuetypes.core.BirthDate
import io.kotest.matchers.shouldBe
import kotlinx.datetime.LocalDate

fun test() {
  val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()

  // Age before birthday
  birthDate.getAgeAt(LocalDate(2024, 1, 14)) shouldBe 33

  // Age on birthday
  birthDate.getAgeAt(LocalDate(2024, 1, 15)) shouldBe 34

  // Age after birthday
  birthDate.getAgeAt(LocalDate(2024, 1, 16)) shouldBe 34
}
