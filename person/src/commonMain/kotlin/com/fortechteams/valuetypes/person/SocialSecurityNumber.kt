package com.fortechteams.valuetypes.person

/**
 * Represents a Social Security Number with basic validation and formatting capabilities.
 *
 * <!--- TEST_NAME SocialSecurityNumberKnitTest -->
 *
 * This interface provides a common contract for different social security number implementations,
 * allowing for country-specific validation rules while maintaining a consistent API.
 *
 * ## Example
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.person.switzerland.SwissSocialSecurityNumber
 * import com.fortechteams.valuetypes.person.GenericSocialSecurityNumber
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   // Use country-specific implementation
 *   val swissSsn = SwissSocialSecurityNumber.fromString("756.1234.5678.97").getOrThrow()
 *   swissSsn.toString() shouldBe "756.1234.5678.97"
 *
 *   // Use generic implementation for other countries
 *   val genericSsn = GenericSocialSecurityNumber.fromString("123-45-6789").getOrThrow()
 *   genericSsn.toString() shouldBe "123-45-6789"
 * }
 * ```
 * <!--- KNIT example-SocialSecurityNumber-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 */
interface SocialSecurityNumber {

  /**
   * The string representation of the social security number.
   */
  val value: String
}
