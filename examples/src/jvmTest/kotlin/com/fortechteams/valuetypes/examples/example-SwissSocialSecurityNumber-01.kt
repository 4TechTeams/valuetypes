// This file was automatically generated from SwissSocialSecurityNumber.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleSwissSocialSecurityNumber01

import com.fortechteams.valuetypes.person.switzerland.SwissSocialSecurityNumber
import io.kotest.matchers.shouldBe

fun test() {
  val ssn = SwissSocialSecurityNumber.fromString("756.1234.5678.97").getOrThrow()
  ssn.toString() shouldBe "756.1234.5678.97"

  // Invalid formats fail
  SwissSocialSecurityNumber.fromString("756.1234.5678.98").isFailure shouldBe true
}
