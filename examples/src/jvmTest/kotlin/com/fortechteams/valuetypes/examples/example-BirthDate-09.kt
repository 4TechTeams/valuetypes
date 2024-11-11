// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleBirthDate09

import com.fortechteams.valuetypes.person.BirthDate
import io.kotest.matchers.shouldBe

fun test() {
  // Valid date
  BirthDate.fromDate(1990, 1, 15).isSuccess shouldBe true

  // Future date fails
  BirthDate.fromDate(2525, 1, 15).isFailure shouldBe true
}
