// This file was automatically generated from Name.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCoreFirstName02

import com.fortechteams.valuetypes.core.FirstName
import io.kotest.matchers.shouldBe

fun test() {
  // Valid cases
  val simple = FirstName.of("John")
  simple.isSuccess shouldBe true

  val compound = FirstName.of("Mary-Jane")
  compound.isSuccess shouldBe true

  // Invalid cases with error messages
  val empty = FirstName.of("")
  empty.exceptionOrNull()?.message shouldBe "Invalid name:  - Cannot be blank"

  val withNumber = FirstName.of("John2")
  withNumber.exceptionOrNull()?.message shouldBe "Invalid name: John2 - Cannot contain numbers"
}
