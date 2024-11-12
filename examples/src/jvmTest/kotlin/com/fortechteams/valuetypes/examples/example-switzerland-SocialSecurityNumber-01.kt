// This file was automatically generated from SocialSecurityNumber.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleSwitzerlandSocialSecurityNumber01

import com.fortechteams.valuetypes.switzerland.SocialSecurityNumber
import io.kotest.matchers.shouldBe

fun test() {
  val ssn = SocialSecurityNumber.fromString("756.1234.5678.97").getOrThrow()
  ssn.toString() shouldBe "756.1234.5678.97"

  // Invalid formats fail
  SocialSecurityNumber.fromString("756.1234.5678.98").isFailure shouldBe true
}
