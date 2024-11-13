package com.fortechteams.valuetypes.switzerland

import com.fortechteams.valuetypes.common.BaseSocialSecurityNumber
import com.fortechteams.valuetypes.common.Country
import com.fortechteams.valuetypes.common.exception.InvalidSocialSecurityNumberException
import kotlin.jvm.JvmInline

/**
 * Represents a Swiss Social Security Number (AHV-Nummer).
 *
 * <!--- TEST_NAME SwitzerlandSocialSecurityNumberKnitTest -->
 *
 * Swiss social security numbers (AHV-Nummer) are unique personal identifiers in Switzerland.
 *
 * ## Validation
 *
 * Swiss SSN follow a specific format and include validation rules:
 *
 * - Format: 756.XXXX.XXXX.XX
 * - Starts with country code 756 (Switzerland)
 * - Contains 13 digits in total
 * - Includes a checksum digit using the EAN-13 algorithm
 *
 * ## Example
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.switzerland.SocialSecurityNumber
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   val ssn = SocialSecurityNumber.fromString("756.1234.5678.97").getOrThrow()
 *   ssn.toString() shouldBe "756.1234.5678.97"
 *
 *   // Invalid formats fail
 *   SocialSecurityNumber.fromString("756.1234.5678.98").isFailure shouldBe true
 * }
 * ```
 * <!--- KNIT example-switzerland-SocialSecurityNumber-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 */
@JvmInline
value class SocialSecurityNumber private constructor(
  override val value: String,
) : BaseSocialSecurityNumber {

  override fun toString(): String = value

  /**
   * The country code part of the SSN (always "756" for Switzerland)
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.switzerland.SocialSecurityNumber
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val ssn = SocialSecurityNumber.fromString("756.1234.5678.97").getOrThrow()
   *   ssn.countryCode shouldBe "756"
   * }
   * ```
   * <!--- KNIT example-switzerland-SocialSecurityNumber-02.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  val countryCode: String
    get() = value.substring(0, 3)

  /**
   * The personal identification number part (8 digits)
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.switzerland.SocialSecurityNumber
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val ssn = SocialSecurityNumber.fromString("756.1234.5678.97").getOrThrow()
   *   ssn.personalNumber shouldBe "123456789"
   * }
   * ```
   * <!--- KNIT example-switzerland-SocialSecurityNumber-03.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  val personalNumber: String
    get() = value.substring(4, 8) + value.substring(9, 13) + value.substring(14, 15)

  /**
   * The check digit (last digit)
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.switzerland.SocialSecurityNumber
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val ssn = SocialSecurityNumber.fromString("756.1234.5678.97").getOrThrow()
   *   ssn.checkDigit shouldBe "7"
   * }
   * ```
   * <!--- KNIT example-switzerland-SocialSecurityNumber-04.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  val checkDigit: String
    get() = value.substring(15)

  companion object {
    private const val REQUIRED_LENGTH = 13

    /**
     * Creates a [SocialSecurityNumber] from a string.
     *
     * The input can be provided with or without dots. All other characters are filtered out.
     * The method performs validation and formats the number according to the official format.
     *
     * - Must contain exactly 13 digits after filtering non-digits
     * - Must start with Swiss country code (756)
     * - Must have a valid EAN-13 checksum
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.switzerland.SocialSecurityNumber
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   // All these formats are accepted if valid
     *   SocialSecurityNumber.fromString("756.1234.5678.97").isSuccess shouldBe true
     *   SocialSecurityNumber.fromString("7561234567897").isSuccess shouldBe true
     *   SocialSecurityNumber.fromString("756-1234-5678-97").isSuccess shouldBe true
     *
     *   // These formats will fail
     *   SocialSecurityNumber.fromString("756.1234.5678").isFailure shouldBe true    // Too short
     *   SocialSecurityNumber.fromString("123.4567.8901.23").isFailure shouldBe true // Wrong country code
     *   SocialSecurityNumber.fromString("756.1234.5678.98").isFailure shouldBe true // Invalid checksum
     * }
     * ```
     * <!--- KNIT example-switzerland-SocialSecurityNumber-05.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromString(value: String): Result<SocialSecurityNumber> = runCatching {
      // Extract digits
      val digits = value.filter { it.isDigit() }

      // Validate length
      if (digits.length != REQUIRED_LENGTH) {
        throw InvalidSocialSecurityNumberException(
          value,
          "Must contain exactly $REQUIRED_LENGTH digits, found ${digits.length}",
          Country.SWITZERLAND.englishName
        )
      }

      // Validate country code
      if (!digits.startsWith(Country.SWITZERLAND.numericCode.toString())) {
        throw InvalidSocialSecurityNumberException(
          value,
          "Must start with Swiss country code ${Country.SWITZERLAND.numericCode}",
          Country.SWITZERLAND.englishName
        )
      }

      // Validate checksum
      validateChecksum(digits, value)

      // Format and create valid instance
      SocialSecurityNumber(formatWithDots(digits))
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
          "Invalid checksum digit: expected $calculatedChecksum but found $checksum",
          Country.SWITZERLAND.englishName
        )
      }
    }

    private fun formatWithDots(digits: String): String =
      "${digits.substring(0, 3)}.${digits.substring(3, 7)}.${digits.substring(7, 11)}.${
        digits.substring(
          11
        )
      }"
  }
}
