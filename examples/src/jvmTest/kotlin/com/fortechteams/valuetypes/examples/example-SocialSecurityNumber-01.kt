// This file was automatically generated from SocialSecurityNumber.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleSocialSecurityNumber01

import com.fortechteams.valuetypes.person.switzerland.SwissSocialSecurityNumber
import com.fortechteams.valuetypes.person.GenericSocialSecurityNumber
import io.kotest.matchers.shouldBe

fun test() {
  // Use country-specific implementation
  val swissSsn = SwissSocialSecurityNumber.fromString("756.1234.5678.97").getOrThrow()
  swissSsn.toString() shouldBe "756.1234.5678.97"

  // Use generic implementation for other countries
  val genericSsn = GenericSocialSecurityNumber.fromString("123-45-6789").getOrThrow()
  genericSsn.toString() shouldBe "123-45-6789"
}
