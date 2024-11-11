// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleBirthDate07

import com.fortechteams.valuetypes.person.BirthDate
import io.kotest.matchers.shouldBe
import java.time.LocalDate

fun test() {
  val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()
  val age = birthDate.getAgeAt(LocalDate.of(2020, 1, 1))
  age shouldBe 29
}
