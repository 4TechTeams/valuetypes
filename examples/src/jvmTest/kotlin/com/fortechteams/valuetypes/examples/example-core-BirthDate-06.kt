// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCoreBirthDate06

import com.fortechteams.valuetypes.core.BirthDate
import io.kotest.matchers.shouldBe
import kotlinx.datetime.LocalDate

fun test() {
  val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()
  val localDate = birthDate.toLocalDate()

  // Can use LocalDate APIs directly
  localDate.year shouldBe 1990
  localDate.monthNumber shouldBe 1
  localDate.dayOfMonth shouldBe 15

  // Can convert back to BirthDate (if value still matches validation rules)
  val roundTrip = BirthDate.fromLocalDate(localDate).getOrThrow()
  roundTrip shouldBe birthDate
}
