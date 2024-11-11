// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleBirthDate10

import com.fortechteams.valuetypes.person.BirthDate
import io.kotest.matchers.shouldBe
import java.time.LocalDate

fun test() {
  val localDate = LocalDate.of(1990, 1, 15)
  BirthDate.fromLocalDate(localDate).isSuccess shouldBe true
}
