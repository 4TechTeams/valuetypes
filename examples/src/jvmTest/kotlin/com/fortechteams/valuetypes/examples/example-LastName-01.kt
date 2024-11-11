// This file was automatically generated from Name.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleLastName01

import com.fortechteams.valuetypes.person.LastName
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

fun test() {
  // Create valid last name
  val name = LastName.of("Smith").getOrThrow()

  // String representation
  name.toString() shouldBe "Smith"

  // Validation handles whitespace
  LastName.of("  Jones  ").getOrThrow().toString() shouldBe "Jones"

  // Validation fails for empty names
  LastName.of("").isFailure shouldBe true

  // Validation fails for names with numbers
  LastName.of("Smith2").isFailure shouldBe true

  // Validation fails for non-printable characters
  LastName.of("Smith\u0000").isFailure shouldBe true

  // Handles compound names correctly
  LastName.of("Smith-Jones").getOrThrow().toString() shouldBe "Smith-Jones"
  LastName.of("O'Brien").getOrThrow().toString() shouldBe "O'Brien"
  LastName.of("van der Berg").getOrThrow().toString() shouldBe "van der Berg"
}
