// This file was automatically generated from Name.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleFirstName01

import com.fortechteams.valuetypes.personal.FirstName
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

fun test() {
  // Create valid first name
  val name = FirstName.of("John").getOrThrow()

  // String representation
  name.toString() shouldBe "John"

  // Validation handles whitespace
  FirstName.of("  Mary  ").getOrThrow().toString() shouldBe "Mary"

  // Validation fails for empty names
  FirstName.of("").isFailure shouldBe true

  // Validation fails for names with numbers
  FirstName.of("John2").isFailure shouldBe true

  // Validation fails for non-printable characters
  FirstName.of("John\u0000").isFailure shouldBe true

  // Handles special characters correctly
  FirstName.of("Mary-Jane").getOrThrow().toString() shouldBe "Mary-Jane"
  FirstName.of("O'Connor").getOrThrow().toString() shouldBe "O'Connor"
}
