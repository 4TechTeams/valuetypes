package com.fortechteams.valuetypes.person

import com.fortechteams.valuetypes.person.exception.InvalidSocialSecurityNumberException

/**
 * A generic implementation of [SocialSecurityNumber] that performs basic validation.
 *
 * <!--- TEST_NAME GenericSocialSecurityNumberKnitTest -->
 *
 * This implementation can be used for countries that don't have a specific implementation,
 * ensuring at least basic format validation while preserving the original format.
 *
 * ## Example
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.person.GenericSocialSecurityNumber
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   // Accepts various formats
 *   val ssn1 = GenericSocialSecurityNumber.fromString("123-45-6789").getOrThrow()
 *   val ssn2 = GenericSocialSecurityNumber.fromString("ABC123456789").getOrThrow()
 *
 *   ssn1.toString() shouldBe "123-45-6789"
 *
 *   // Empty or blank values fail
 *   GenericSocialSecurityNumber.fromString("").isFailure shouldBe true
 *   GenericSocialSecurityNumber.fromString("   ").isFailure shouldBe true
 * }
 * ```
 * <!--- KNIT example-GenericSocialSecurityNumber-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 */
@JvmInline
value class GenericSocialSecurityNumber private constructor(
  override val value: String
) : SocialSecurityNumber {

  override fun toString(): String = value

  companion object {
    /**
     * Creates a [GenericSocialSecurityNumber] from a string, performing basic validation.
     */
    fun fromString(value: String): Result<GenericSocialSecurityNumber> = runCatching {
      if (value.isBlank()) {
        throw InvalidSocialSecurityNumberException(value, "Cannot be empty or blank")
      }
      if (value.length < 4) {
        throw InvalidSocialSecurityNumberException(value, "Must be at least 4 characters long")
      }
      if (!value.all { it.isPrintable() }) {
        throw InvalidSocialSecurityNumberException(value, "Can only contain printable characters")
      }
      GenericSocialSecurityNumber(value)
    }
  }
}
