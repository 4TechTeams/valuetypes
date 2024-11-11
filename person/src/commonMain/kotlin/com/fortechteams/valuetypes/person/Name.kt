package com.fortechteams.valuetypes.person

import com.fortechteams.valuetypes.network.exception.InvalidNameException
import com.fortechteams.valuetypes.person.NameValidator.validateName

/**
 * Represents any name of a natural person
 *
 * Serves as base class for [FirstName] and [LastName]
 *
 * <!--- TEST_NAME NameKnitTest -->
 */
sealed interface Name {
  val value: String
}

/**
 * Provides validation capabilities specifically for personal names
 */
object NameValidator {

  /**
   * Checks if a character is printable.
   *
   * @return true if the character is printable, false otherwise
   */
  fun Char.isPrintable(): Boolean =
    this.isWhitespace() || this.category.let { category ->
      category != CharCategory.CONTROL &&
        category != CharCategory.FORMAT &&
        category != CharCategory.PRIVATE_USE &&
        category != CharCategory.SURROGATE &&
        category != CharCategory.UNASSIGNED
    }

  /**
   * Validates a name string according to common rules.
   *
   * @param value The string to validate
   * @param maxLength The maximum allowed length
   * @param fieldName The name of the field for error messages
   * @return A [Result] containing either the validated string or a failure with [IllegalArgumentException]
   */
  fun validateName(value: String, maxLength: Int): Result<String> {
    val trimmed = value.trim()
    return when {
      trimmed.isBlank() -> Result.failure(
        InvalidNameException(value, "Cannot be blank")
      )

      trimmed.length > maxLength -> Result.failure(
        InvalidNameException(value, "Cannot exceed $maxLength characters")
      )

      trimmed.any { it.isDigit() } -> Result.failure(
        InvalidNameException(value, "Cannot contain numbers")
      )

      trimmed.any { !it.isPrintable() } -> Result.failure(
        InvalidNameException(value, "Can only contain printable characters")
      )

      else -> Result.success(trimmed)
    }
  }
}

/**
 * Represents a person's first name as a validated value class.
 *
 * This implementation provides a type-safe way to handle first names while ensuring
 * basic validation rules and proper formatting.
 *
 * ## Example
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.person.FirstName
 * import io.kotest.matchers.shouldBe
 * import io.kotest.matchers.types.shouldBeInstanceOf
 *
 * fun test() {
 *   // Create valid first name
 *   val name = FirstName.of("John").getOrThrow()
 *
 *   // String representation
 *   name.toString() shouldBe "John"
 *
 *   // Validation handles whitespace
 *   FirstName.of("  Mary  ").getOrThrow().toString() shouldBe "Mary"
 *
 *   // Validation fails for empty names
 *   FirstName.of("").isFailure shouldBe true
 *
 *   // Validation fails for names with numbers
 *   FirstName.of("John2").isFailure shouldBe true
 *
 *   // Validation fails for non-printable characters
 *   FirstName.of("John\u0000").isFailure shouldBe true
 *
 *   // Handles special characters correctly
 *   FirstName.of("Mary-Jane").getOrThrow().toString() shouldBe "Mary-Jane"
 *   FirstName.of("O'Connor").getOrThrow().toString() shouldBe "O'Connor"
 * }
 * ```
 * <!--- KNIT example-FirstName-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 *
 * ## Validation Rules
 *
 * The following validation rules are applied:
 * - Must not be blank
 * - Must not exceed [MAX_LENGTH] characters after trimming
 * - Must only contain printable characters
 * - Must not contain numbers
 * - Leading and trailing whitespace is removed
 * - Special characters (hyphens, apostrophes) are preserved
 *
 * ## Common Use Cases
 *
 * This class is typically used in:
 * - User registration forms
 * - Profile management
 * - Name formatting and validation
 *
 * @property value The internal string representation of the first name
 */
@JvmInline
value class FirstName private constructor(
  override val value: String
) : Name {

  companion object {

    /**
     * Maximum length for first names according to German naming law (Namensrecht).
     * This limit is set based on practical considerations in German civil registry offices.
     */
    const val MAX_LENGTH = 1000

    /**
     * Creates a new [FirstName] instance after applying validation rules.
     *
     * @param value The string to create the first name from
     * @return A [Result] containing either the valid [FirstName] or a failure with [IllegalArgumentException]
     */
    fun of(value: String): Result<FirstName> =
      validateName(value, MAX_LENGTH)
        .map { FirstName(it) }
  }

  override fun toString(): String = value
}

/**
 * Represents a person's last name as a validated value class.
 *
 * This implementation provides a type-safe way to handle last names while ensuring
 * basic validation rules and proper formatting.
 *
 * ## Example
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.person.LastName
 * import io.kotest.matchers.shouldBe
 * import io.kotest.matchers.types.shouldBeInstanceOf
 *
 * fun test() {
 *   // Create valid last name
 *   val name = LastName.of("Smith").getOrThrow()
 *
 *   // String representation
 *   name.toString() shouldBe "Smith"
 *
 *   // Validation handles whitespace
 *   LastName.of("  Jones  ").getOrThrow().toString() shouldBe "Jones"
 *
 *   // Validation fails for empty names
 *   LastName.of("").isFailure shouldBe true
 *
 *   // Validation fails for names with numbers
 *   LastName.of("Smith2").isFailure shouldBe true
 *
 *   // Validation fails for non-printable characters
 *   LastName.of("Smith\u0000").isFailure shouldBe true
 *
 *   // Handles compound names correctly
 *   LastName.of("Smith-Jones").getOrThrow().toString() shouldBe "Smith-Jones"
 *   LastName.of("O'Brien").getOrThrow().toString() shouldBe "O'Brien"
 *   LastName.of("van der Berg").getOrThrow().toString() shouldBe "van der Berg"
 * }
 * ```
 * <!--- KNIT example-LastName-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 *
 * ## Validation Rules
 *
 * The following validation rules are applied:
 * - Must not be blank
 * - Must not exceed [MAX_LENGTH] characters after trimming
 * - Must only contain printable characters
 * - Must not contain numbers
 * - Leading and trailing whitespace is removed
 * - Special characters (hyphens, apostrophes) are preserved
 * - Spaces are allowed for compound names (e.g., "van der Berg")
 *
 * ## Common Use Cases
 *
 * This class is typically used in:
 * - User registration forms
 * - Profile management
 * - Name formatting and validation
 *
 * @property value The internal string representation of the last name
 */
@JvmInline
value class LastName private constructor(
  override val value: String
) : Name {

  companion object {
    /**
     * Maximum length for last names according to German naming law (Namensrecht).
     * This limit is set based on practical considerations in German civil registry offices.
     */
    const val MAX_LENGTH = 1000

    /**
     * Creates a new [LastName] instance after applying validation rules.
     *
     * @param value The string to create the last name from
     * @return A [Result] containing either the valid [LastName] or a failure with [IllegalArgumentException]
     */
    fun of(value: String): Result<LastName> =
      validateName(value, MAX_LENGTH)
        .map { LastName(it) }
  }

  override fun toString(): String = value
}
