package com.fortechteams.valuetypes.switzerland

import io.kotest.matchers.shouldBe
import kotlin.test.Test
import kotlin.test.assertNotNull

class SocialSecurityNumberTest {

  @Test
  fun shouldCreateValidSsnFromStandardFormat() {
    // These numbers have valid checksums
    val validNumbers = listOf(
      "756.9217.0769.85",
      "756.4559.9987.04",
      "756.6788.9137.32",
      "756.5115.8357.16"
    )

    // Verify each number is valid
    validNumbers.forEach { ssn ->
      SocialSecurityNumber.fromString(ssn).isSuccess shouldBe true
      SocialSecurityNumber.fromString(ssn).getOrNull().toString() shouldBe ssn
    }
  }

  @Test
  fun shouldCreateValidSsnFromDifferentFormats() {
    // Using a known valid number with proper checksum
    val validNumber = "756.9217.0769.85"
    val validDigits = "7569217076985"

    // Numbers without separators
    SocialSecurityNumber.fromString(validDigits).isSuccess shouldBe true
    SocialSecurityNumber.fromString(validDigits).getOrNull().toString() shouldBe validNumber

    // Numbers with different separators
    SocialSecurityNumber.fromString("756-9217-0769-85").isSuccess shouldBe true
    SocialSecurityNumber.fromString("756 9217 0769 85").isSuccess shouldBe true

    // Numbers with mixed separators
    SocialSecurityNumber.fromString("756.9217-0769 85").isSuccess shouldBe true
  }

  @Test
  fun shouldHandleFormattingConsistently() {
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
      SocialSecurityNumber.fromString(input).getOrNull().toString() shouldBe validNumber
    }
  }

  @Test
  fun shouldFailForInvalidFormats() {
    // Empty or blank
    SocialSecurityNumber.fromString("").isFailure shouldBe true
    SocialSecurityNumber.fromString("   ").isFailure shouldBe true

    // Wrong length
    SocialSecurityNumber.fromString("756.9217.0769").isFailure shouldBe true
    SocialSecurityNumber.fromString("756.9217.0769.855").isFailure shouldBe true

    // Wrong country code
    SocialSecurityNumber.fromString("755.9217.0769.85").isFailure shouldBe true
    SocialSecurityNumber.fromString("123.4567.8901.23").isFailure shouldBe true

    // Non-digit characters only
    SocialSecurityNumber.fromString("abc.defg.hijk.lm").isFailure shouldBe true

    // Mixed valid and invalid characters
    SocialSecurityNumber.fromString("756.abcd.efgh.ij").isFailure shouldBe true
  }

  @Test
  fun shouldValidateChecksumCorrectly() {
    // Valid checksums
    SocialSecurityNumber.fromString("756.9217.0769.85").isSuccess shouldBe true

    // Invalid checksums (changing last digit of valid numbers)
    SocialSecurityNumber.fromString("756.9217.0769.84").isFailure shouldBe true
    SocialSecurityNumber.fromString("756.3047.5009.64").isFailure shouldBe true
    SocialSecurityNumber.fromString("756.1234.5678.94").isFailure shouldBe true
  }

  @Test
  fun shouldMaintainValueSemantics() {
    val ssn1 = SocialSecurityNumber.fromString("756.9217.0769.85").getOrThrow()
    val ssn2 = SocialSecurityNumber.fromString("756.9217.0769.85").getOrThrow()
    val ssn3 = SocialSecurityNumber.fromString("756.3047.5009.62").getOrThrow()

    assertNotNull(ssn1)
    assertNotNull(ssn2)
    assertNotNull(ssn3)

    // Same values should be equal
    ssn1 shouldBe ssn2

    // Different values should not be equal
    (ssn1 == ssn3) shouldBe false
  }

  @Test
  fun shouldHandleWhitespaceAndSpecialCharacters() {
    val validNumber = "756.9217.0769.85"

    // Extra whitespace
    SocialSecurityNumber.fromString(" $validNumber ").isSuccess shouldBe true
    SocialSecurityNumber.fromString("\t$validNumber\n").isSuccess shouldBe true

    // Multiple separators
    SocialSecurityNumber.fromString("756...9217...0769...85").isSuccess shouldBe true
    SocialSecurityNumber.fromString("756--9217--0769--85").isSuccess shouldBe true

    // Mixed separators
    SocialSecurityNumber.fromString("756.9217-0769 85").isSuccess shouldBe true
  }

  @Test
  fun shouldHandleErrorMessagesCorrectly() {
    // Wrong length
    SocialSecurityNumber.fromString("756.9217.0769").fold(
      onSuccess = { throw AssertionError("Should fail") },
      onFailure = { error ->
        error.message shouldBe "Invalid Social Security Number for Switzerland: 756.9217.0769 - Must contain exactly 13 digits, found 11"
      }
    )

    // Wrong country code
    SocialSecurityNumber.fromString("123.4567.8901.23").fold(
      onSuccess = { throw AssertionError("Should fail") },
      onFailure = { error ->
        error.message shouldBe "Invalid Social Security Number for Switzerland: 123.4567.8901.23 - Must start with Swiss country code 756"
      }
    )

    // Invalid checksum
    SocialSecurityNumber.fromString("756.9217.0769.84").fold(
      onSuccess = { throw AssertionError("Should fail") },
      onFailure = { error ->
        error.message shouldBe "Invalid Social Security Number for Switzerland: 756.9217.0769.84 - Invalid checksum digit: expected 5 but found 4"
      }
    )
  }

  @Test
  fun shouldProvideAccessToSsnComponents() {
    val ssn = SocialSecurityNumber.fromString("756.9217.0769.85").getOrThrow()

    ssn.countryCode shouldBe "756"
    ssn.personalNumber shouldBe "921707698"
    ssn.checkDigit shouldBe "5"
  }

  @Test
  fun shouldExtractComponentsFromDifferentInputFormats() {
    val inputs = listOf(
      "756.9217.0769.85",
      "7569217076985",
      "756-9217-0769-85",
      "756.9217-0769 85"
    )

    inputs.forEach { input ->
      val ssn = SocialSecurityNumber.fromString(input).getOrThrow()

      ssn.countryCode shouldBe "756"
      ssn.personalNumber shouldBe "921707698"
      ssn.checkDigit shouldBe "5"
    }
  }
}
