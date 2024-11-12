package com.fortechteams.valuetypes.core

import com.fortechteams.valuetypes.core.exception.InvalidNameException
import com.fortechteams.valuetypes.core.util.isPrintable
import kotlin.jvm.JvmInline

/**
 * Base interface for name types in the system.
 *
 * <!--- TEST_NAME CoreNameKnitTest -->
 *
 * Provides common functionality for [FirstName] and [LastName] while ensuring
 * type safety through sealed interface design.
 *
 * @property value The string representation of the name
 */
sealed interface Name {
  val value: String

  companion object {
    /**
     * Maximum length for names according to German naming law (Namensrecht).
     * This limit is set based on practical considerations in German civil registry offices.
     */
    const val MAX_LENGTH = 1000
  }
}

/**
 * Represents a person's first name as a validated value class.
 *
 * <!--- TEST_NAME CoreFirstNameKnitTest -->
 *
 * A type-safe implementation for handling first names that ensures validation rules
 * based on German naming law constraints, including maximum length and character restrictions.
 *
 * ## Example
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.core.FirstName
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   // Create first name
 *   val name1 = FirstName.of("John").getOrThrow()
 *   val name2 = FirstName.of("John").getOrThrow()
 *
 *   // Value equality
 *   name1 shouldBe name2
 *
 *   // Normalization
 *   val trimmed = FirstName.of("  John  ").getOrThrow()
 *   trimmed.toString() shouldBe "John"
 *
 *   // Special characters
 *   val special = FirstName.of("Mary-Jane").getOrThrow()
 *   special.toString() shouldBe "Mary-Jane"
 * }
 * ```
 * <!--- KNIT example-core-FirstName-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 *
 * ## Validation Rules
 *
 * | Rule          | Description                          | Example                   |
 * |---------------|--------------------------------------|---------------------------|
 * | Not blank     | Must contain non-whitespace chars    | "John" ✓, "" ✗           |
 * | Length limit  | Max [NAME_MAX_LENGTH] after trim          | "John" ✓, [1001 chars] ✗ |
 * | Characters    | Only printable, no numbers           | "Mary-Jane" ✓, "John2" ✗ |
 * | Whitespace    | Auto-trimmed from start/end         | " John " → "John"        |
 * | Special chars | Hyphens and apostrophes allowed     | "Mary-Jane" ✓, "O'Neill" ✓|
 *
 * @property value The internal string representation of the first name
 * @throws InvalidNameException when validation fails
 */
@JvmInline
value class FirstName private constructor(
  override val value: String
) : Name {

  companion object {

    /**
     * Creates a new [FirstName] instance after applying validation rules.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.core.FirstName
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   // Valid cases
     *   val simple = FirstName.of("John")
     *   simple.isSuccess shouldBe true
     *
     *   val compound = FirstName.of("Mary-Jane")
     *   compound.isSuccess shouldBe true
     *
     *   // Invalid cases with error messages
     *   val empty = FirstName.of("")
     *   empty.exceptionOrNull()?.message shouldBe "Invalid name:  - Cannot be blank"
     *
     *   val withNumber = FirstName.of("John2")
     *   withNumber.exceptionOrNull()?.message shouldBe "Invalid name: John2 - Cannot contain numbers"
     * }
     * ```
     * <!--- KNIT example-core-FirstName-02.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun of(value: String): Result<FirstName> =
      validateName(value, Name.MAX_LENGTH)
        .map { FirstName(it) }
  }

  override fun toString(): String = value
}

/**
 * Represents a person's last name as a validated value class.
 *
 * <!--- TEST_NAME CoreLastNameKnitTest -->
 *
 * A type-safe implementation for handling last names that ensures validation rules
 * based on German naming law constraints, including maximum length and character restrictions.
 * Additionally allows spaces for compound names.
 *
 * ## Example
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.core.LastName
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   // Basic usage
 *   val name = LastName.of("Smith").getOrThrow()
 *   name.toString() shouldBe "Smith"
 *
 *   // Compound names
 *   val compound = LastName.of("van der Berg").getOrThrow()
 *   compound.toString() shouldBe "van der Berg"
 *
 *   // Special characters
 *   val hyphenated = LastName.of("Smith-Jones").getOrThrow()
 *   hyphenated.toString() shouldBe "Smith-Jones"
 * }
 * ```
 * <!--- KNIT example-core-LastName-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 *
 * ## Validation Rules
 *
 * | Rule          | Description                          | Example                      |
 * |---------------|--------------------------------------|------------------------------|
 * | Not blank     | Must contain non-whitespace chars    | "Smith" ✓, "" ✗             |
 * | Length limit  | Max [NAME_MAX_LENGTH] after trim          | "Smith" ✓, [1001 chars] ✗   |
 * | Characters    | Only printable, no numbers           | "van der Berg" ✓, "Smith2" ✗|
 * | Whitespace    | Auto-trimmed edges, allowed inside   | "van der Berg" ✓            |
 * | Special chars | Hyphens, apostrophes, spaces allowed | "Smith-Jones" ✓, "O'Brien" ✓|
 *
 * @property value The internal string representation of the last name
 * @throws InvalidNameException when validation fails
 */
@JvmInline
value class LastName private constructor(
  override val value: String
) : Name {

  companion object {

    /**
     * Creates a new [LastName] instance after applying validation rules.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.core.LastName
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   // Valid cases
     *   val simple = LastName.of("Smith")
     *   simple.isSuccess shouldBe true
     *
     *   val compound = LastName.of("van der Berg")
     *   compound.isSuccess shouldBe true
     *
     *   // Invalid cases with error messages
     *   val empty = LastName.of("")
     *   empty.exceptionOrNull()?.message shouldBe "Invalid name:  - Cannot be blank"
     *
     *   val withNumber = LastName.of("Smith2")
     *   withNumber.exceptionOrNull()?.message shouldBe "Invalid name: Smith2 - Cannot contain numbers"
     * }
     * ```
     * <!--- KNIT example-core-LastName-02.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun of(value: String): Result<LastName> =
      validateName(value, Name.MAX_LENGTH)
        .map { LastName(it) }
  }

  override fun toString(): String = value
}

/**
 * Validates a name string according to common rules.
 *
 * @param value The string to validate
 * @param maxLength The maximum allowed length
 * @param fieldName The name of the field for error messages
 * @return A [Result] containing either the validated string or a failure with [InvalidNameException]
 */
private fun validateName(value: String, maxLength: Int): Result<String> {
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
