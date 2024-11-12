// This file was automatically generated from InvalidBirthDateException.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCoreInvalidBirthDateException01

import com.fortechteams.valuetypes.core.BirthDate
import com.fortechteams.valuetypes.core.exception.InvalidBirthDateException
import io.kotest.matchers.shouldBe

fun test() {
  // Future date
  val futureResult = BirthDate.fromString("2525-01-01")
  futureResult.exceptionOrNull() shouldBe instanceof<InvalidBirthDateException>()

  // Invalid format
  val invalidResult = BirthDate.fromString("not-a-date")
  invalidResult.exceptionOrNull() shouldBe instanceof<InvalidBirthDateException>()

  // Invalid date
  val invalidDateResult = BirthDate.fromDate(2000, 13, 1)
  invalidDateResult.exceptionOrNull() shouldBe instanceof<InvalidBirthDateException>()
}
