package com.fortechteams.valuetypes.person.switzerland

import io.kotest.matchers.shouldBe
import kotlin.test.Test
import kotlin.test.assertNotNull

class SwissSocialSecurityNumberTest {

  @Test
  fun `should create valid SSN from standard format`() {
    // These numbers have valid checksums
    val validNumbers = listOf(
      "756.9217.0769.85",
      "756.4559.9987.04",
      "756.6788.9137.32",
      "756.5115.8357.16"
    )

    // Verify each number is valid
    validNumbers.forEach { ssn ->
      SwissSocialSecurityNumber.fromString(ssn).isSuccess shouldBe true
      SwissSocialSecurityNumber.fromString(ssn).getOrNull().toString() shouldBe ssn
    }
  }

  @Test
  fun `should create valid SSN from different formats`() {
    // Using a known valid number with proper checksum
    val validNumber = "756.9217.0769.85"
    val validDigits = "7569217076985"

    // Numbers without separators
    SwissSocialSecurityNumber.fromString(validDigits).isSuccess shouldBe true
    SwissSocialSecurityNumber.fromString(validDigits).getOrNull().toString() shouldBe validNumber

    // Numbers with different separators
    SwissSocialSecurityNumber.fromString("756-9217-0769-85").isSuccess shouldBe true
    SwissSocialSecurityNumber.fromString("756 9217 0769 85").isSuccess shouldBe true

    // Numbers with mixed separators
    SwissSocialSecurityNumber.fromString("756.9217-0769 85").isSuccess shouldBe true
  }

  @Test
  fun `should handle formatting consistently`() {
    val validNumber = "756.9217.0769.85"
    val inputs = listOf(
      "756.9217.0769.85",
      "7569217076985",
      "756-9217-0769-85",
      "756.9217-0769 85",
      "756...9217.....0769...85",
      " 756 9217 0769 85 "
    )

    inputs.forEach { input ->
      SwissSocialSecurityNumber.fromString(input).getOrNull().toString() shouldBe validNumber
    }
  }

  @Test
  fun `should fail for invalid formats`() {
    // Empty or blank
    SwissSocialSecurityNumber.fromString("").isFailure shouldBe true
    SwissSocialSecurityNumber.fromString("   ").isFailure shouldBe true

    // Wrong length
    SwissSocialSecurityNumber.fromString("756.9217.0769").isFailure shouldBe true
    SwissSocialSecurityNumber.fromString("756.9217.0769.855").isFailure shouldBe true

    // Wrong country code
    SwissSocialSecurityNumber.fromString("755.9217.0769.85").isFailure shouldBe true
    SwissSocialSecurityNumber.fromString("123.4567.8901.23").isFailure shouldBe true

    // Non-digit characters only
    SwissSocialSecurityNumber.fromString("abc.defg.hijk.lm").isFailure shouldBe true

    // Mixed valid and invalid characters
    SwissSocialSecurityNumber.fromString("756.abcd.efgh.ij").isFailure shouldBe true
  }

  @Test
  fun `should validate checksum correctly`() {
    // Valid checksums
    SwissSocialSecurityNumber.fromString("756.9217.0769.85").isSuccess shouldBe true

    // Invalid checksums (changing last digit of valid numbers)
    SwissSocialSecurityNumber.fromString("756.9217.0769.84").isFailure shouldBe true
    SwissSocialSecurityNumber.fromString("756.3047.5009.64").isFailure shouldBe true
    SwissSocialSecurityNumber.fromString("756.1234.5678.94").isFailure shouldBe true
  }

  @Test
  fun `should maintain value semantics`() {
    val ssn1 = SwissSocialSecurityNumber.fromString("756.9217.0769.85").getOrThrow()
    val ssn2 = SwissSocialSecurityNumber.fromString("756.9217.0769.85").getOrThrow()
    val ssn3 = SwissSocialSecurityNumber.fromString("756.3047.5009.62").getOrThrow()

    assertNotNull(ssn1)
    assertNotNull(ssn2)
    assertNotNull(ssn3)

    // Same values should be equal
    ssn1 shouldBe ssn2

    // Different values should not be equal
    (ssn1 == ssn3) shouldBe false
  }

  @Test
  fun `should handle whitespace and special characters`() {
    val validNumber = "756.9217.0769.85"

    // Extra whitespace
    SwissSocialSecurityNumber.fromString(" $validNumber ").isSuccess shouldBe true
    SwissSocialSecurityNumber.fromString("\t$validNumber\n").isSuccess shouldBe true

    // Multiple separators
    SwissSocialSecurityNumber.fromString("756...9217...0769...85").isSuccess shouldBe true
    SwissSocialSecurityNumber.fromString("756--9217--0769--85").isSuccess shouldBe true

    // Mixed separators
    SwissSocialSecurityNumber.fromString("756.9217-0769 85").isSuccess shouldBe true
  }

  @Test
  fun `should handle error messages correctly`() {
    // Wrong length
    SwissSocialSecurityNumber.fromString("756.9217.0769").fold(
      onSuccess = { throw AssertionError("Should fail") },
      onFailure = { error ->
        error.message shouldBe "Invalid SSN: 756.9217.0769 - Must contain exactly 13 digits, found 11"
      }
    )

    // Wrong country code
    SwissSocialSecurityNumber.fromString("123.4567.8901.23").fold(
      onSuccess = { throw AssertionError("Should fail") },
      onFailure = { error ->
        error.message shouldBe "Invalid SSN: 123.4567.8901.23 - Must start with Swiss country code 756"
      }
    )

    // Invalid checksum
    SwissSocialSecurityNumber.fromString("756.9217.0769.84").fold(
      onSuccess = { throw AssertionError("Should fail") },
      onFailure = { error ->
        error.message shouldBe "Invalid SSN: 756.9217.0769.84 - Invalid checksum digit: expected 5 but found 4"
      }
    )
  }

  @Test
  fun `should provide access to SSN components`() {
    val ssn = SwissSocialSecurityNumber.fromString("756.9217.0769.85").getOrThrow()

    ssn.countryCode shouldBe "756"
    ssn.personalNumber shouldBe "921707698"
    ssn.checkDigit shouldBe "5"
  }

  @Test
  fun `should extract components from different input formats`() {
    val inputs = listOf(
      "756.9217.0769.85",
      "7569217076985",
      "756-9217-0769-85",
      "756.9217-0769 85"
    )

    inputs.forEach { input ->
      val ssn = SwissSocialSecurityNumber.fromString(input).getOrThrow()

      ssn.countryCode shouldBe "756"
      ssn.personalNumber shouldBe "921707698"
      ssn.checkDigit shouldBe "5"
    }
  }
}
