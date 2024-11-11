// This file was automatically generated from GenericSocialSecurityNumber.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleGenericSocialSecurityNumber01

import com.fortechteams.valuetypes.person.GenericSocialSecurityNumber
import io.kotest.matchers.shouldBe

fun test() {
  // Accepts various formats
  val ssn1 = GenericSocialSecurityNumber.fromString("123-45-6789").getOrThrow()
  val ssn2 = GenericSocialSecurityNumber.fromString("ABC123456789").getOrThrow()

  ssn1.toString() shouldBe "123-45-6789"

  // Empty or blank values fail
  GenericSocialSecurityNumber.fromString("").isFailure shouldBe true
  GenericSocialSecurityNumber.fromString("   ").isFailure shouldBe true
}
