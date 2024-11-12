// This file was automatically generated from Name.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCoreFirstName01

import com.fortechteams.valuetypes.core.FirstName
import io.kotest.matchers.shouldBe

fun test() {
  // Create first name
  val name1 = FirstName.of("John").getOrThrow()
  val name2 = FirstName.of("John").getOrThrow()

  // Value equality
  name1 shouldBe name2

  // Normalization
  val trimmed = FirstName.of("  John  ").getOrThrow()
  trimmed.toString() shouldBe "John"

  // Special characters
  val special = FirstName.of("Mary-Jane").getOrThrow()
  special.toString() shouldBe "Mary-Jane"
}
