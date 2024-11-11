package com.fortechteams.valuetypes.person.usa

import com.fortechteams.valuetypes.person.SocialSecurityNumber
import com.fortechteams.valuetypes.person.exception.InvalidSocialSecurityNumberException

/**
 * Represents an American Social Security Number (SSN).
 *
 * <!--- TEST_NAME AmericanSocialSecurityNumberKnitTest -->
 *
 * The format is XXX-XX-XXXX where:
 * - First three digits (XXX): Area number (001-899)
 * - Middle two digits (XX): Group number (01-99)
 * - Last four digits (XXXX): Serial number (0001-9999)
 *
 * Invalid area numbers:
 * - 000
 * - 666
 * - 900-999
 *
 * ## Example
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.person.usa.AmericanSocialSecurityNumber
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   val ssn = AmericanSocialSecurityNumber.fromString("123-45-6789").getOrThrow()
 *
 *   ssn.areaNumber shouldBe "123"
 *   ssn.groupNumber shouldBe "45"
 *   ssn.serialNumber shouldBe "6789"
 *
 *   ssn.toString() shouldBe "123-45-6789"
 * }
 * ```
 * <!--- KNIT example-AmericanSocialSecurityNumber-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 */
@JvmInline
value class AmericanSocialSecurityNumber private constructor(
  override val value: String
) : SocialSecurityNumber {

  override fun toString(): String = value

  /**
   * The area number (first three digits)
   */
  val areaNumber: String
    get() = value.substring(0, 3)

  /**
   * The group number (middle two digits)
   */
  val groupNumber: String
    get() = value.substring(4, 6)

  /**
   * The serial number (last four digits)
   */
  val serialNumber: String
    get() = value.substring(7)

  companion object {
    private const val REQUIRED_LENGTH = 9
    private const val AREA_LENGTH = 3
    private const val GROUP_LENGTH = 2
    private const val SERIAL_LENGTH = 4
    private const val HYPHEN = "-"

    /**
     * Creates an [AmericanSocialSecurityNumber] from a string.
     *
     * The input can be provided with or without hyphens. All other characters are filtered out.
     * The method performs validation and formats the number according to the official format.
     *
     * ## Validation Rules
     * - Must contain exactly 9 digits after filtering non-digits
     * - Area number must be between 001-899, excluding 000 and 666
     * - Group number must be between 01-99
     * - Serial number must be between 0001-9999
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.person.usa.AmericanSocialSecurityNumber
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   // All these formats are accepted if valid
     *   AmericanSocialSecurityNumber.fromString("123-45-6789").isSuccess shouldBe true
     *   AmericanSocialSecurityNumber.fromString("123456789").isSuccess shouldBe true
     *   AmericanSocialSecurityNumber.fromString("123.45.6789").isSuccess shouldBe true
     *
     *   // These formats will fail
     *   AmericanSocialSecurityNumber.fromString("000-45-6789").isFailure shouldBe true // Invalid area
     *   AmericanSocialSecurityNumber.fromString("666-45-6789").isFailure shouldBe true // Invalid area
     *   AmericanSocialSecurityNumber.fromString("900-45-6789").isFailure shouldBe true // Invalid area
     * }
     * ```
     * <!--- KNIT example-AmericanSocialSecurityNumber-02.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromString(value: String): Result<AmericanSocialSecurityNumber> = runCatching {
      val digits = value.filter { it.isDigit() }

      if (digits.length != REQUIRED_LENGTH) {
        throw InvalidSocialSecurityNumberException(
          value,
          "Must contain exactly 9 digits, found ${digits.length}"
        )
      }

      // Validate area number
      val areaNumber = digits.substring(0, AREA_LENGTH).toInt()
      when {
        areaNumber == 0 -> throw InvalidSocialSecurityNumberException(value, "Area number cannot be 000")
        areaNumber == 666 -> throw InvalidSocialSecurityNumberException(value, "Area number cannot be 666")
        areaNumber >= 900 -> throw InvalidSocialSecurityNumberException(value, "Area number must be less than 900")
      }

      // Validate group number
      val groupNumber = digits.substring(AREA_LENGTH, AREA_LENGTH + GROUP_LENGTH).toInt()
      if (groupNumber == 0) {
        throw InvalidSocialSecurityNumberException(value, "Group number cannot be 00")
      }

      // Validate serial number
      val serialNumber = digits.substring(AREA_LENGTH + GROUP_LENGTH).toInt()
      if (serialNumber == 0) {
        throw InvalidSocialSecurityNumberException(value, "Serial number cannot be 0000")
      }

      // Format with hyphens: XXX-XX-XXXX
      val formatted = buildString {
        append(digits.substring(0, AREA_LENGTH))
        append(HYPHEN)
        append(digits.substring(AREA_LENGTH, AREA_LENGTH + GROUP_LENGTH))
        append(HYPHEN)
        append(digits.substring(AREA_LENGTH + GROUP_LENGTH))
      }

      AmericanSocialSecurityNumber(formatted)
    }
  }
}
