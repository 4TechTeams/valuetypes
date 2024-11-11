// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleBirthDate08

import com.fortechteams.valuetypes.person.BirthDate
import io.kotest.matchers.shouldBe

fun test() {
  // Different formats - all valid
  BirthDate.fromString("1990-12-31").isSuccess shouldBe true
  BirthDate.fromString("31.12.1990").isSuccess shouldBe true
  BirthDate.fromString("1990/12/31").isSuccess shouldBe true
  BirthDate.fromString("31/12/1990").isSuccess shouldBe true

  // Invalid formats fail
  BirthDate.fromString("1990.12.31").isFailure shouldBe true
  BirthDate.fromString("invalid").isFailure shouldBe true
}
