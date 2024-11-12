// This file was automatically generated from Name.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCoreLastName02

import com.fortechteams.valuetypes.core.LastName
import io.kotest.matchers.shouldBe

fun test() {
  // Valid cases
  val simple = LastName.of("Smith")
  simple.isSuccess shouldBe true

  val compound = LastName.of("van der Berg")
  compound.isSuccess shouldBe true

  // Invalid cases with error messages
  val empty = LastName.of("")
  empty.exceptionOrNull()?.message shouldBe "Invalid name:  - Cannot be blank"

  val withNumber = LastName.of("Smith2")
  withNumber.exceptionOrNull()?.message shouldBe "Invalid name: Smith2 - Cannot contain numbers"
}
