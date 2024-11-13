package com.fortechteams.valuetypes.core

import com.fortechteams.valuetypes.core.exception.InvalidBirthDateException
import kotlinx.datetime.*
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import kotlin.jvm.JvmSynthetic

/**
 * Represents a human birthdate as a value class wrapping a LocalDate
 *
 * <!--- TEST_NAME CoreBirthDateKnitTest -->
 *
 * A type-safe implementation for handling birthdates that ensures valid date ranges
 * and provides convenient age calculation methods. This value class validates dates
 * to prevent future dates and offers multiple parsing options for different date formats.
 *
 * Note: This implementation uses kotlinx-datetime internally for multiplatform compatibility,
 * rather than java.time.LocalDate which is JVM-only.
 *
 * ## Example
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.core.BirthDate
 * import io.kotest.matchers.shouldBe
 * import kotlinx.datetime.LocalDate
 *
 * fun test() {
 *   // Create from string
 *   val birthDate1 = BirthDate.fromString("1990-01-15").getOrThrow()
 *
 *   // Create from components
 *   val birthDate2 = BirthDate.fromDate(1990, 1, 15).getOrThrow()
 *
 *   // Both creation methods produce the same value
 *   birthDate1 shouldBe birthDate2
 *
 *   // Access components
 *   birthDate1.year shouldBe 1990
 *   birthDate1.month shouldBe 1
 *   birthDate1.day shouldBe 15
 *
 *   // Calculate age
 *   val referenceDate = LocalDate(2024, 1, 15)
 *   birthDate1.getAgeAt(referenceDate) shouldBe 34
 * }
 * ```
 * <!--- KNIT example-core-BirthDate-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 *
 * ## Supported Date Formats
 *
 * | Format        | Example      | Description                 |
 * |---------------|--------------|-----------------------------|
 * | ISO           | `1990-01-15` | Year-Month-Day with hyphens |
 * | European      | `15.01.1990` | Day.Month.Year with dots    |
 * | Alternative 1 | `1990/01/15` | Year/Month/Day with slashes |
 * | Alternative 2 | `15/01/1990` | Day/Month/Year with slashes |
 *
 * @property value The internal LocalDate representation of the birthdate
 * @throws InvalidBirthDateException when date validation fails
 */
@JvmInline
value class BirthDate private constructor(
  val value: LocalDate,
) {

  /**
   * The year component of the birthdate.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.core.BirthDate
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()
   *   birthDate.year shouldBe 1990
   * }
   * ```
   * <!--- KNIT example-core-BirthDate-02.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  val year: Int get() = value.year

  /**
   * The month component of the birthdate (1-12).
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.core.BirthDate
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   // January = 1, December = 12
   *   val januaryDate = BirthDate.fromString("1990-01-15").getOrThrow()
   *   januaryDate.month shouldBe 1
   *
   *   val decemberDate = BirthDate.fromString("1990-12-31").getOrThrow()
   *   decemberDate.month shouldBe 12
   * }
   * ```
   * <!--- KNIT example-core-BirthDate-03.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  val month: Int get() = value.monthNumber

  /**
   * The day component of the birthdate (1-31).
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.core.BirthDate
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val midMonthDate = BirthDate.fromString("1990-01-15").getOrThrow()
   *   midMonthDate.day shouldBe 15
   *
   *   val endOfMonthDate = BirthDate.fromString("1990-01-31").getOrThrow()
   *   endOfMonthDate.day shouldBe 31
   * }
   * ```
   * <!--- KNIT example-core-BirthDate-04.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  val day: Int get() = value.dayOfMonth

  /**
   * Returns the string representation of the birthdate in ISO format (YYYY-MM-DD).
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.core.BirthDate
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   // All formats normalize to ISO output
   *   val date1 = BirthDate.fromString("15.01.1990").getOrThrow()
   *   date1.toString() shouldBe "1990-01-15"
   *
   *   val date2 = BirthDate.fromString("1990/01/15").getOrThrow()
   *   date2.toString() shouldBe "1990-01-15"
   * }
   * ```
   * <!--- KNIT example-core-BirthDate-05.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  override fun toString(): String = buildString {
    append(value.year.toString().padStart(4, '0'))
    append('-')
    append(value.monthNumber.toString().padStart(2, '0'))
    append('-')
    append(value.dayOfMonth.toString().padStart(2, '0'))
  }

  /**
   * Returns the underlying LocalDate representation of this birthdate.
   * Useful when you need to perform date operations not provided by BirthDate.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.core.BirthDate
   * import io.kotest.matchers.shouldBe
   * import kotlinx.datetime.LocalDate
   *
   * fun test() {
   *   val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()
   *   val localDate = birthDate.toLocalDate()
   *
   *   // Can use LocalDate APIs directly
   *   localDate.year shouldBe 1990
   *   localDate.monthNumber shouldBe 1
   *   localDate.dayOfMonth shouldBe 15
   *
   *   // Can convert back to BirthDate (if value still matches validation rules)
   *   val roundTrip = BirthDate.fromLocalDate(localDate).getOrThrow()
   *   roundTrip shouldBe birthDate
   * }
   * ```
   * <!--- KNIT example-core-BirthDate-06.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  fun toLocalDate(): LocalDate = value

  /**
   * Calculates the person's current age in years based on the system clock.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.core.BirthDate
   * import io.kotest.matchers.shouldBe
   * import kotlinx.datetime.LocalDate
   *
   * fun test() {
   *   val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()
   *
   *   // Using a fixed date for testing
   *   val fixedDate = LocalDate(2024, 1, 15)
   *   birthDate.getAgeAt(fixedDate) shouldBe 34
   *
   *   // Current age will depend on system clock
   *   val currentAge = birthDate.getAge()
   *   println("Current age: $currentAge")
   * }
   * ```
   * <!--- KNIT example-core-BirthDate-07.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  fun getAge(): Int = getAgeAt(
    Clock.System.now()
      .toLocalDateTime(TimeZone.currentSystemDefault())
      .date
  )

  /**
   * Calculates the person's age in years at a specific date.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.core.BirthDate
   * import io.kotest.matchers.shouldBe
   * import kotlinx.datetime.LocalDate
   *
   * fun test() {
   *   val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()
   *
   *   // Age before birthday
   *   birthDate.getAgeAt(LocalDate(2024, 1, 14)) shouldBe 33
   *
   *   // Age on birthday
   *   birthDate.getAgeAt(LocalDate(2024, 1, 15)) shouldBe 34
   *
   *   // Age after birthday
   *   birthDate.getAgeAt(LocalDate(2024, 1, 16)) shouldBe 34
   * }
   * ```
   * <!--- KNIT example-core-BirthDate-08.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  fun getAgeAt(date: LocalDate): Int =
    value.yearsUntil(date)

  companion object {
    private val datePatterns = listOf(
      DatePattern("\\d{4}-\\d{2}-\\d{2}".toRegex()) {
        it.split('-').map(String::toInt).let { (y, m, d) -> Triple(y, m, d) }
      },
      DatePattern("\\d{2}\\.\\d{2}\\.\\d{4}".toRegex()) {
        it.split('.').map(String::toInt).let { (d, m, y) -> Triple(y, m, d) }
      },
      DatePattern("\\d{4}/\\d{2}/\\d{2}".toRegex()) {
        it.split('/').map(String::toInt).let { (y, m, d) -> Triple(y, m, d) }
      },
      DatePattern("\\d{2}/\\d{2}/\\d{4}".toRegex()) {
        it.split('/').map(String::toInt).let { (d, m, y) -> Triple(y, m, d) }
      }
    )

    /**
     * Creates a [BirthDate] from a string in various formats.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.core.BirthDate
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   // ISO format works
     *   val iso = BirthDate.fromString("1990-12-31").getOrThrow()
     *   iso.toString() shouldBe "1990-12-31"
     *
     *   // European format works
     *   val european = BirthDate.fromString("31.12.1990").getOrThrow()
     *   european.toString() shouldBe "1990-12-31"
     *
     *   // Slash formats work
     *   val yearFirst = BirthDate.fromString("1990/12/31").getOrThrow()
     *   yearFirst.toString() shouldBe "1990-12-31"
     *
     *   val dayFirst = BirthDate.fromString("31/12/1990").getOrThrow()
     *   dayFirst.toString() shouldBe "1990-12-31"
     *
     *   // Invalid format fails
     *   BirthDate.fromString("invalid-date").isFailure shouldBe true
     * }
     * ```
     * <!--- KNIT example-core-BirthDate-09.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    @JvmSynthetic
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
     * import com.fortechteams.valuetypes.core.BirthDate
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   // Regular date works
     *   val regular = BirthDate.fromDate(1990, 1, 15)
     *   regular.isSuccess shouldBe true
     *
     *   // Leap year date works
     *   val leapYear = BirthDate.fromDate(2000, 2, 29)
     *   leapYear.isSuccess shouldBe true
     *
     *   // Future date fails
     *   val future = BirthDate.fromDate(2525, 1, 1)
     *   future.isFailure shouldBe true
     * }
     * ```
     * <!--- KNIT example-core-BirthDate-10.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    @JvmSynthetic
    fun fromDate(year: Int, month: Int, day: Int): Result<BirthDate> = runCatching {
      val date = try {
        LocalDate(year, month, day)
      } catch (e: Throwable) {
        throw InvalidBirthDateException("(year=$year,month=$month,day=$day)", e)
      }
      fromLocalDate(date).getOrThrow()
    }

    /**
     * Creates a [BirthDate] from a LocalDate instance.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.core.BirthDate
     * import io.kotest.matchers.shouldBe
     * import kotlinx.datetime.LocalDate
     *
     * fun test() {
     *   // Past date works
     *   val past = BirthDate.fromLocalDate(LocalDate(1990, 1, 15))
     *   past.isSuccess shouldBe true
     *
     *   // Future date fails
     *   val future = BirthDate.fromLocalDate(LocalDate(2525, 1, 1))
     *   future.isFailure shouldBe true
     * }
     * ```
     * <!--- KNIT example-core-BirthDate-11.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    @JvmSynthetic
    fun fromLocalDate(date: LocalDate): Result<BirthDate> {
      val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
      return when {
        date > today ->
          Result.failure(InvalidBirthDateException(date.toString(), "Cannot be in the future"))

        else ->
          Result.success(BirthDate(date))
      }
    }

    // Support for idiomatic Java access throwing exceptions instead of returning a Result response

    @JvmName("fromString")
    @JvmStatic
    fun fromStringOrThrow(date: String): BirthDate =
      fromString(date).getOrThrow()

    @JvmName("fromDate")
    @JvmStatic
    fun fromDateOrThrow(year: Int, month: Int, day: Int): BirthDate =
      fromDate(year, month, day).getOrThrow()

    private data class DatePattern(
      val regex: Regex,
      val parse: (String) -> Triple<Int, Int, Int>,
    )
  }
}
