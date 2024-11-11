package com.fortechteams.valuetypes.person

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class GenericSocialSecurityNumberTest {

  @Test
  fun `should create valid SSN from various formats`() {
    val validFormats = listOf(
      // Common hyphenated format
      "123-45-6789",
      // Alphanumeric
      "ABC123456789",
      // Mixed with slashes
      "12345/ABC/XY",
      // Mixed with hyphens
      "SSN-123456-ABCD",
      // Long numeric
      "1234567890123456789",
      // Minimum length
      "ABCD"
    )

    validFormats.forEach { ssn ->
      GenericSocialSecurityNumber.fromString(ssn).isSuccess shouldBe true
      GenericSocialSecurityNumber.fromString(ssn).getOrNull().toString() shouldBe ssn
    }
  }

  @Test
  fun `should fail for invalid formats`() {
    val invalidFormats = listOf(
      // Empty string
      "",
      // Single space
      " ",
      // Multiple spaces
      "   ",
      // Tab
      "\t",
      // Newline
      "\n",
      // Too short
      "ABC",
      // Too short
      "123"
    )

    invalidFormats.forEach { ssn ->
      GenericSocialSecurityNumber.fromString(ssn).isFailure shouldBe true
    }
  }

  @Test
  fun `should reject non-printable characters`() {
    val invalidInputs = listOf(
      // Null character
      "123\u0000456",
      // Bell
      "ABC\u0007XYZ",
      // Escape
      "123\u001B456",
      // Start of heading
      "ABC\u0001XYZ"
    )

    invalidInputs.forEach { input ->
      GenericSocialSecurityNumber.fromString(input).fold(
        onSuccess = { throw AssertionError("Should fail for non-printable characters: $input") },
        onFailure = { error ->
          error.message shouldBe "Invalid SSN: $input - Can only contain printable characters"
        }
      )
    }
  }

  @Test
  fun `should handle special characters and whitespace`() {
    val validInputs = listOf(
      // Hyphens
      "A-B-C-123",
      // Dots
      "A.B.C.123",
      // Slashes
      "A/B/C/123",
      // Underscores
      "A_B_C_123",
      // Special characters
      "A#B#C#123",
      // Mixed special characters
      "ABC\$123%456",
      // Multiple special characters
      "!@#\$%^&*()123"
    )

    validInputs.forEach { input ->
      GenericSocialSecurityNumber.fromString(input).isSuccess shouldBe true
      GenericSocialSecurityNumber.fromString(input).getOrNull().toString() shouldBe input
    }
  }

  @Test
  fun `should handle boundary length cases`() {
    // Minimum length (4 characters)
    GenericSocialSecurityNumber.fromString("1234").isSuccess shouldBe true
    GenericSocialSecurityNumber.fromString("ABCD").isSuccess shouldBe true

    // Just below minimum length
    GenericSocialSecurityNumber.fromString("123").isFailure shouldBe true
    GenericSocialSecurityNumber.fromString("ABC").isFailure shouldBe true

    // Very long inputs should be accepted
    GenericSocialSecurityNumber.fromString("1".repeat(100)).isSuccess shouldBe true
    GenericSocialSecurityNumber.fromString("A".repeat(100)).isSuccess shouldBe true
  }

  @Test
  fun `should preserve original format exactly`() {
    // Test various input formats and their expected output
    val cases = mapOf(
      "123-45-6789" to "123-45-6789",
      "ABC.123.456" to "ABC.123.456",
      "SSN:123456" to "SSN:123456",
      "12345 67890" to "12345 67890"
    )

    cases.forEach { (input, expected) ->
      GenericSocialSecurityNumber.fromString(input).getOrNull().toString() shouldBe expected
    }
  }

  @Test
  fun `should handle error messages correctly`() {
    // Empty string
    GenericSocialSecurityNumber.fromString("").fold(
      onSuccess = { throw AssertionError("Should fail") },
      onFailure = { error ->
        error.message shouldBe "Invalid SSN:  - Cannot be empty or blank"
      }
    )

    // Blank string
    GenericSocialSecurityNumber.fromString("   ").fold(
      onSuccess = { throw AssertionError("Should fail") },
      onFailure = { error ->
        error.message shouldBe "Invalid SSN:     - Cannot be empty or blank"
      }
    )

    // Too short
    GenericSocialSecurityNumber.fromString("123").fold(
      onSuccess = { throw AssertionError("Should fail") },
      onFailure = { error ->
        error.message shouldBe "Invalid SSN: 123 - Must be at least 4 characters long"
      }
    )
  }

  @Test
  fun `should maintain value semantics`() {
    val ssn1 = GenericSocialSecurityNumber.fromString("123-45-6789").getOrThrow()
    val ssn2 = GenericSocialSecurityNumber.fromString("123-45-6789").getOrThrow()
    val ssn3 = GenericSocialSecurityNumber.fromString("987-65-4321").getOrThrow()

    // Same values should be equal
    ssn1 shouldBe ssn2

    // Different values should not be equal
    (ssn1 == ssn3) shouldBe false
  }
}
