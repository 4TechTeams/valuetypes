// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCoreBirthDate10

import com.fortechteams.valuetypes.core.BirthDate
import io.kotest.matchers.shouldBe

fun test() {
  // Regular date works
  val regular = BirthDate.fromDate(1990, 1, 15)
  regular.isSuccess shouldBe true

  // Leap year date works
  val leapYear = BirthDate.fromDate(2000, 2, 29)
  leapYear.isSuccess shouldBe true

  // Future date fails
  val future = BirthDate.fromDate(2525, 1, 1)
  future.isFailure shouldBe true
}
