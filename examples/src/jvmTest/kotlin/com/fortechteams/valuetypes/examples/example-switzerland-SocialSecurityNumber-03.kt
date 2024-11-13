// This file was automatically generated from SocialSecurityNumber.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleSwitzerlandSocialSecurityNumber03

import com.fortechteams.valuetypes.switzerland.SocialSecurityNumber
import io.kotest.matchers.shouldBe

fun test() {
  val ssn = SocialSecurityNumber.fromString("756.1234.5678.97").getOrThrow()
  ssn.personalNumber shouldBe "123456789"
}
