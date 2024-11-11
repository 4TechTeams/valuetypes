package com.fortechteams.valuetypes.person

import com.fortechteams.valuetypes.person.BirthDate.Companion.fromDate
import com.fortechteams.valuetypes.person.BirthDate.Companion.fromLocalDate
import com.fortechteams.valuetypes.person.BirthDate.Companion.fromString
import com.fortechteams.valuetypes.person.exception.InvalidBirthDateException
import java.time.LocalDate
import java.time.Period

/**
 * Represents a human birthdate as a value class wrapping a LocalDate
 *
 * <!--- TEST_NAME BirthDateKnitTest -->
 *
 * This implementation provides a type-safe way to handle birthdates while
 * ensuring valid date ranges and offering convenient age calculation methods.
 *
 * ## Example
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.person.BirthDate
 * import io.kotest.matchers.shouldBe
 * import java.time.LocalDate
 *
 * fun test() {
 *   // Create from different string formats
 *   val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()
 *
 *   birthDate.toString() shouldBe "1990-01-15"
 *
 *   // Access components
 *   birthDate.year shouldBe 1990
 *   birthDate.month shouldBe 1
 *   birthDate.day shouldBe 15
 *
 *   // Calculate age at specific date
 *   birthDate.getAgeAt(LocalDate.of(2020, 1, 15)) shouldBe 30
 * }
 * ```
 * <!--- KNIT example-BirthDate-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 *
 * ## See Also
 *
 * The class provides several factory methods for creating birthdates:
 * - [fromString] for parsing date strings in various formats:
 *   - ISO format (YYYY-MM-DD)
 *   - European format (DD.MM.YYYY)
 *   - Alternative formats (YYYY/MM/DD, DD/MM/YYYY)
 * - [fromDate] for creating from year, month, and day values
 * - [fromLocalDate] for creating from a LocalDate instance
 *
 * Date-related calculations are available:
 * - [getAge] calculates current age in years
 * - [getAgeAt] calculates age at a specific date
 *
 * @property value The internal LocalDate representation of the birthdate
 */
@JvmInline
value class BirthDate private constructor(private val value: LocalDate) {

  /**
   * The year component of the birthdate.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.person.BirthDate
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()
   *   birthDate.year shouldBe 1990
   * }
   * ```
   * <!--- KNIT example-BirthDate-02.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  val year: Int get() = value.year

  /**
   * The month component of the birthdate (1-12).
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.person.BirthDate
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()
   *   birthDate.month shouldBe 1
   * }
   * ```
   * <!--- KNIT example-BirthDate-03.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  val month: Int get() = value.monthValue

  /**
   * The day component of the birthdate (1-31).
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.person.BirthDate
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()
   *   birthDate.day shouldBe 15
   * }
   * ```
   * <!--- KNIT example-BirthDate-04.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  val day: Int get() = value.dayOfMonth

  /**
   * Returns the string representation of the birthdate in ISO format (YYYY-MM-DD).
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.person.BirthDate
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val birthDate = BirthDate.fromString("15.01.1990").getOrThrow()
   *   birthDate.toString() shouldBe "1990-01-15"
   * }
   * ```
   * <!--- KNIT example-BirthDate-05.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  override fun toString(): String = buildString {
    append(value.year.toString().padStart(4, '0'))
    append('-')
    append(value.monthValue.toString().padStart(2, '0'))
    append('-')
    append(value.dayOfMonth.toString().padStart(2, '0'))
  }

  /**
   * Calculates the person's current age in years.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.person.BirthDate
   * import io.kotest.matchers.shouldBe
   * import java.time.LocalDate
   *
   * fun test() {
   *   val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()
   *   val today = LocalDate.of(2024, 1, 15)
   *   birthDate.getAgeAt(today) shouldBe 34
   * }
   * ```
   * <!--- KNIT example-BirthDate-06.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  fun getAge(): Int = getAgeAt(LocalDate.now())

  /**
   * Calculates the person's age in years at a specific date.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.person.BirthDate
   * import io.kotest.matchers.shouldBe
   * import java.time.LocalDate
   *
   * fun test() {
   *   val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()
   *   val age = birthDate.getAgeAt(LocalDate.of(2020, 1, 1))
   *   age shouldBe 29
   * }
   * ```
   * <!--- KNIT example-BirthDate-07.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  fun getAgeAt(date: LocalDate): Int = Period.between(value, date).years

  companion object {
    private val datePatterns = listOf(
      DatePattern("\\d{4}-\\d{2}-\\d{2}") { it.split('-').map(String::toInt).let { (y, m, d) -> Triple(y, m, d) } },
      DatePattern("\\d{2}\\.\\d{2}\\.\\d{4}") { it.split('.').map(String::toInt).let { (d, m, y) -> Triple(y, m, d) } },
      DatePattern("\\d{4}/\\d{2}/\\d{2}") { it.split('/').map(String::toInt).let { (y, m, d) -> Triple(y, m, d) } },
      DatePattern("\\d{2}/\\d{2}/\\d{4}") { it.split('/').map(String::toInt).let { (d, m, y) -> Triple(y, m, d) } }
    )

    /**
     * Creates a [BirthDate] from a string in various formats.
     * Supported formats:
     * - ISO format: "YYYY-MM-DD" (e.g., "1990-12-31")
     * - European format: "DD.MM.YYYY" (e.g., "31.12.1990")
     * - Alternative formats: "YYYY/MM/DD" or "DD/MM/YYYY"
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.person.BirthDate
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   // Different formats - all valid
     *   BirthDate.fromString("1990-12-31").isSuccess shouldBe true
     *   BirthDate.fromString("31.12.1990").isSuccess shouldBe true
     *   BirthDate.fromString("1990/12/31").isSuccess shouldBe true
     *   BirthDate.fromString("31/12/1990").isSuccess shouldBe true
     *
     *   // Invalid formats fail
     *   BirthDate.fromString("1990.12.31").isFailure shouldBe true
     *   BirthDate.fromString("invalid").isFailure shouldBe true
     * }
     * ```
     * <!--- KNIT example-BirthDate-08.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromString(date: String): Result<BirthDate> = runCatching {
      val (year, month, day) = datePatterns
        .firstOrNull { it.regex.matches(date) }
        ?.parse?.invoke(date)
        ?: throw InvalidBirthDateException(
          date,
          "Invalid date format. Supported formats: YYYY-MM-DD, DD.MM.YYYY, YYYY/MM/DD, DD/MM/YYYY"
        )

      fromDate(year, month, day).getOrThrow()
    }

    /**
     * Creates a [BirthDate] from year, month, and day values.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.person.BirthDate
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   // Valid date
     *   BirthDate.fromDate(1990, 1, 15).isSuccess shouldBe true
     *
     *   // Future date fails
     *   BirthDate.fromDate(2525, 1, 15).isFailure shouldBe true
     * }
     * ```
     * <!--- KNIT example-BirthDate-09.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromDate(year: Int, month: Int, day: Int): Result<BirthDate> = runCatching {
      fromLocalDate(LocalDate.of(year, month, day)).getOrThrow()
    }

    /**
     * Creates a [BirthDate] from a LocalDate instance.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.person.BirthDate
     * import io.kotest.matchers.shouldBe
     * import java.time.LocalDate
     *
     * fun test() {
     *   val localDate = LocalDate.of(1990, 1, 15)
     *   BirthDate.fromLocalDate(localDate).isSuccess shouldBe true
     * }
     * ```
     * <!--- KNIT example-BirthDate-10.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromLocalDate(date: LocalDate): Result<BirthDate> {
      val today = LocalDate.now()
      return when {
        date.isAfter(today) ->
          Result.failure(InvalidBirthDateException(date.toString(), "Cannot be in the future"))

        else ->
          Result.success(BirthDate(date))
      }
    }

    private data class DatePattern(
      val regex: Regex,
      val parse: (String) -> Triple<Int, Int, Int>
    ) {
      constructor(pattern: String, parse: (String) -> Triple<Int, Int, Int>) : this(pattern.toRegex(), parse)
    }
  }
}
