// This file was automatically generated from SwissSocialSecurityNumber.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleSwissSocialSecurityNumber02

import com.fortechteams.valuetypes.person.switzerland.SwissSocialSecurityNumber
import io.kotest.matchers.shouldBe

fun test() {
  // All these formats are accepted if valid
  SwissSocialSecurityNumber.fromString("756.1234.5678.97").isSuccess shouldBe true
  SwissSocialSecurityNumber.fromString("7561234567897").isSuccess shouldBe true
  SwissSocialSecurityNumber.fromString("756-1234-5678-97").isSuccess shouldBe true

  // These formats will fail
  SwissSocialSecurityNumber.fromString("756.1234.5678").isFailure shouldBe true    // Too short
  SwissSocialSecurityNumber.fromString("123.4567.8901.23").isFailure shouldBe true // Wrong country code
  SwissSocialSecurityNumber.fromString("756.1234.5678.98").isFailure shouldBe true // Invalid checksum
}
