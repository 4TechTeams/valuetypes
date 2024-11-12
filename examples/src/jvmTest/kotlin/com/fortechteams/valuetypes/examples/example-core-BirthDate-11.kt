// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCoreBirthDate11

import com.fortechteams.valuetypes.core.BirthDate
import io.kotest.matchers.shouldBe
import kotlinx.datetime.LocalDate

fun test() {
  // Past date works
  val past = BirthDate.fromLocalDate(LocalDate(1990, 1, 15))
  past.isSuccess shouldBe true

  // Future date fails
  val future = BirthDate.fromLocalDate(LocalDate(2525, 1, 1))
  future.isFailure shouldBe true
}
