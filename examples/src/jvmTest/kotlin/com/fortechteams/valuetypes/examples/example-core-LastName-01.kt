// This file was automatically generated from Name.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCoreLastName01

import com.fortechteams.valuetypes.core.LastName
import io.kotest.matchers.shouldBe

fun test() {
  // Basic usage
  val name = LastName.of("Smith").getOrThrow()
  name.toString() shouldBe "Smith"

  // Compound names
  val compound = LastName.of("van der Berg").getOrThrow()
  compound.toString() shouldBe "van der Berg"

  // Special characters
  val hyphenated = LastName.of("Smith-Jones").getOrThrow()
  hyphenated.toString() shouldBe "Smith-Jones"
}
