// This file was automatically generated from AmericanSocialSecurityNumber.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleAmericanSocialSecurityNumber02

import com.fortechteams.valuetypes.person.usa.AmericanSocialSecurityNumber
import io.kotest.matchers.shouldBe

fun test() {
  // All these formats are accepted if valid
  AmericanSocialSecurityNumber.fromString("123-45-6789").isSuccess shouldBe true
  AmericanSocialSecurityNumber.fromString("123456789").isSuccess shouldBe true
  AmericanSocialSecurityNumber.fromString("123.45.6789").isSuccess shouldBe true

  // These formats will fail
  AmericanSocialSecurityNumber.fromString("000-45-6789").isFailure shouldBe true // Invalid area
  AmericanSocialSecurityNumber.fromString("666-45-6789").isFailure shouldBe true // Invalid area
  AmericanSocialSecurityNumber.fromString("900-45-6789").isFailure shouldBe true // Invalid area
}
