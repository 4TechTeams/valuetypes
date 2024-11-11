// This file was automatically generated from AmericanSocialSecurityNumber.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleAmericanSocialSecurityNumber01

import com.fortechteams.valuetypes.person.usa.AmericanSocialSecurityNumber
import io.kotest.matchers.shouldBe

fun test() {
  val ssn = AmericanSocialSecurityNumber.fromString("123-45-6789").getOrThrow()

  ssn.areaNumber shouldBe "123"
  ssn.groupNumber shouldBe "45"
  ssn.serialNumber shouldBe "6789"

  ssn.toString() shouldBe "123-45-6789"
}
