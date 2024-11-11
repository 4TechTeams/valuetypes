package com.fortechteams.valuetypes.person.switzerland

import com.fortechteams.valuetypes.person.SocialSecurityNumber
import com.fortechteams.valuetypes.person.exception.InvalidSocialSecurityNumberException

/**
 * Represents a Swiss Social Security Number (AHV-Nummer).
 *
 * <!--- TEST_NAME SwissSocialSecurityNumberKnitTest -->
 *
 * Swiss social security numbers (AHV-Nummer) are unique personal identifiers in Switzerland.
 * They follow a specific format and include validation rules:
 *
 * - Format: 756.XXXX.XXXX.XX
 * - Starts with country code 756 (Switzerland)
 * - Contains 13 digits in total
 * - Includes a checksum digit using the EAN-13 algorithm
 *
 * ## Example
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.person.switzerland.SwissSocialSecurityNumber
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   val ssn = SwissSocialSecurityNumber.fromString("756.1234.5678.97").getOrThrow()
 *   ssn.toString() shouldBe "756.1234.5678.97"
 *
 *   // Invalid formats fail
 *   SwissSocialSecurityNumber.fromString("756.1234.5678.98").isFailure shouldBe true
 * }
 * ```
 * <!--- KNIT example-SwissSocialSecurityNumber-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 */
@JvmInline
value class SwissSocialSecurityNumber private constructor(
  override val value: String
) : SocialSecurityNumber {

  override fun toString(): String = value

  /**
   * The country code part of the SSN (always "756" for Switzerland)
   */
  val countryCode: String
    get() = value.substring(0, 3)

  /**
   * The personal identification number part (8 digits)
   */
  val personalNumber: String
    get() = value.substring(4, 8) + value.substring(9, 13) + value.substring(14, 15)

  /**
   * The check digit (last digit)
   */
  val checkDigit: String
    get() = value.substring(15)

  companion object {
    private const val REQUIRED_LENGTH = 13
    private const val COUNTRY_CODE = "756"
    private const val DOT = "."

    /**
     * Creates a [SwissSocialSecurityNumber] from a string.
     *
     * The input can be provided with or without dots. All other characters are filtered out.
     * The method performs validation and formats the number according to the official format.
     *
     * ## Validation Rules
     * - Must contain exactly 13 digits after filtering non-digits
     * - Must start with Swiss country code (756)
     * - Must have a valid EAN-13 checksum
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.person.switzerland.SwissSocialSecurityNumber
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   // All these formats are accepted if valid
     *   SwissSocialSecurityNumber.fromString("756.1234.5678.97").isSuccess shouldBe true
     *   SwissSocialSecurityNumber.fromString("7561234567897").isSuccess shouldBe true
     *   SwissSocialSecurityNumber.fromString("756-1234-5678-97").isSuccess shouldBe true
     *
     *   // These formats will fail
     *   SwissSocialSecurityNumber.fromString("756.1234.5678").isFailure shouldBe true    // Too short
     *   SwissSocialSecurityNumber.fromString("123.4567.8901.23").isFailure shouldBe true // Wrong country code
     *   SwissSocialSecurityNumber.fromString("756.1234.5678.98").isFailure shouldBe true // Invalid checksum
     * }
     * ```
     * <!--- KNIT example-SwissSocialSecurityNumber-02.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromString(value: String): Result<SwissSocialSecurityNumber> = runCatching {
      // Extract digits
      val digits = value.filter { it.isDigit() }

      // Validate length
      if (digits.length != REQUIRED_LENGTH) {
        throw InvalidSocialSecurityNumberException(
          value,
          "Must contain exactly $REQUIRED_LENGTH digits, found ${digits.length}"
        )
      }

      // Validate country code
      if (!digits.startsWith(COUNTRY_CODE)) {
        throw InvalidSocialSecurityNumberException(
          value,
          "Must start with Swiss country code $COUNTRY_CODE"
        )
      }

      // Validate checksum
      validateChecksum(digits, value)

      // Format and create valid instance
      SwissSocialSecurityNumber(formatWithDots(digits))
    }

    private fun validateChecksum(digits: String, originalValue: String) {
      val numbers = digits.dropLast(1).map { it.toString().toInt() }
      val checksum = digits.last().toString().toInt()

      var sum = 0
      numbers.reversed().forEachIndexed { index, digit ->
        val factor = if (index % 2 == 0) 3 else 1
        sum += digit * factor
      }

      val calculatedChecksum = (10 - (sum % 10)) % 10
      if (checksum != calculatedChecksum) {
        throw InvalidSocialSecurityNumberException(
          originalValue,
          "Invalid checksum digit: expected $calculatedChecksum but found $checksum"
        )
      }
    }

    private fun formatWithDots(digits: String): String =
      "${digits.substring(0, 3)}$DOT${digits.substring(3, 7)}$DOT${digits.substring(7, 11)}$DOT${digits.substring(11)}"
  }
}
