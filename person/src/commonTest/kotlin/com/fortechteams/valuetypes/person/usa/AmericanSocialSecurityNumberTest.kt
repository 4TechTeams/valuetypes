package com.fortechteams.valuetypes.person.usa

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class AmericanSocialSecurityNumberTest {

  @Test
  fun `should create valid SSN from standard format`() {
    val validNumbers = listOf(
      "123-45-6789",
      "345-67-8901",
      "789-01-2345"
    )

    validNumbers.forEach { ssn ->
      AmericanSocialSecurityNumber.fromString(ssn).isSuccess shouldBe true
      AmericanSocialSecurityNumber.fromString(ssn).getOrNull().toString() shouldBe ssn
    }
  }

  @Test
  fun `should create valid SSN from different formats`() {
    val validNumber = "123-45-6789"
    val validDigits = "123456789"

    // Numbers without separators
    AmericanSocialSecurityNumber.fromString(validDigits).isSuccess shouldBe true
    AmericanSocialSecurityNumber.fromString(validDigits).getOrNull().toString() shouldBe validNumber

    // Numbers with different separators
    AmericanSocialSecurityNumber.fromString("123.45.6789").isSuccess shouldBe true
    AmericanSocialSecurityNumber.fromString("123 45 6789").isSuccess shouldBe true
    AmericanSocialSecurityNumber.fromString("123.45-6789").isSuccess shouldBe true
  }

  @Test
  fun `should provide correct SSN components`() {
    val ssn = AmericanSocialSecurityNumber.fromString("123-45-6789").getOrThrow()

    ssn.areaNumber shouldBe "123"
    ssn.groupNumber shouldBe "45"
    ssn.serialNumber shouldBe "6789"
  }

  @Test
  fun `should handle formatting consistently`() {
    val validNumber = "123-45-6789"
    val inputs = listOf(
      "123-45-6789",
      "123456789",
      "123.45.6789",
      "123 45 6789",
      "123...45....6789",
      " 123-45-6789 "
    )

    inputs.forEach { input ->
      AmericanSocialSecurityNumber.fromString(input).getOrNull().toString() shouldBe validNumber
    }
  }

  @Test
  fun `should fail for invalid formats`() {
    // Empty or blank
    AmericanSocialSecurityNumber.fromString("").isFailure shouldBe true
    AmericanSocialSecurityNumber.fromString("   ").isFailure shouldBe true

    // Wrong length
    AmericanSocialSecurityNumber.fromString("123-45-678").isFailure shouldBe true
    AmericanSocialSecurityNumber.fromString("123-45-67890").isFailure shouldBe true

    // Invalid area numbers
    AmericanSocialSecurityNumber.fromString("000-45-6789").isFailure shouldBe true
    AmericanSocialSecurityNumber.fromString("666-45-6789").isFailure shouldBe true
    AmericanSocialSecurityNumber.fromString("900-45-6789").isFailure shouldBe true

    // Invalid group number
    AmericanSocialSecurityNumber.fromString("123-00-6789").isFailure shouldBe true

    // Invalid serial number
    AmericanSocialSecurityNumber.fromString("123-45-0000").isFailure shouldBe true

    // Non-digit characters only
    AmericanSocialSecurityNumber.fromString("abc-de-fghi").isFailure shouldBe true

    // Mixed valid and invalid characters
    AmericanSocialSecurityNumber.fromString("12X-4Y-67Z9").isFailure shouldBe true
  }

  @Test
  fun `should maintain value semantics`() {
    val ssn1 = AmericanSocialSecurityNumber.fromString("123-45-6789").getOrThrow()
    val ssn2 = AmericanSocialSecurityNumber.fromString("123-45-6789").getOrThrow()
    val ssn3 = AmericanSocialSecurityNumber.fromString("345-67-8901").getOrThrow()

    // Same values should be equal
    ssn1 shouldBe ssn2

    // Different values should not be equal
    (ssn1 == ssn3) shouldBe false
  }

  @Test
  fun `should handle error messages correctly`() {
    // Wrong length
    AmericanSocialSecurityNumber.fromString("123-45-678")
      .fold(onSuccess = { throw AssertionError("Should fail") }, onFailure = { error ->
        error.message shouldBe "Invalid SSN: 123-45-678 - Must contain exactly 9 digits, found 8"
      })

    // Invalid area number
    AmericanSocialSecurityNumber.fromString("000-45-6789")
      .fold(onSuccess = { throw AssertionError("Should fail") }, onFailure = { error ->
        error.message shouldBe "Invalid SSN: 000-45-6789 - Area number cannot be 000"
      })

    // Invalid group number
    AmericanSocialSecurityNumber.fromString("123-00-6789")
      .fold(onSuccess = { throw AssertionError("Should fail") }, onFailure = { error ->
        error.message shouldBe "Invalid SSN: 123-00-6789 - Group number cannot be 00"
      })

    // Invalid serial number
    AmericanSocialSecurityNumber.fromString("123-45-0000")
      .fold(onSuccess = { throw AssertionError("Should fail") }, onFailure = { error ->
        error.message shouldBe "Invalid SSN: 123-45-0000 - Serial number cannot be 0000"
      })
  }
}
