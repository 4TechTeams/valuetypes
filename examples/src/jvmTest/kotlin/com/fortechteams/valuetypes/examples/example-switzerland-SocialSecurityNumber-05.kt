// This file was automatically generated from SocialSecurityNumber.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleSwitzerlandSocialSecurityNumber05

import com.fortechteams.valuetypes.switzerland.SocialSecurityNumber
import io.kotest.matchers.shouldBe

fun test() {
  // All these formats are accepted if valid
  SocialSecurityNumber.fromString("756.1234.5678.97").isSuccess shouldBe true
  SocialSecurityNumber.fromString("7561234567897").isSuccess shouldBe true
  SocialSecurityNumber.fromString("756-1234-5678-97").isSuccess shouldBe true

  // These formats will fail
  SocialSecurityNumber.fromString("756.1234.5678").isFailure shouldBe true    // Too short
  SocialSecurityNumber.fromString("123.4567.8901.23").isFailure shouldBe true // Wrong country code
  SocialSecurityNumber.fromString("756.1234.5678.98").isFailure shouldBe true // Invalid checksum
}
