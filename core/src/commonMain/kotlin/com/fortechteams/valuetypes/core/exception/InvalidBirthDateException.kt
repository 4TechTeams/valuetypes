package com.fortechteams.valuetypes.core.exception

/**
 * Exception thrown when attempting to create an invalid [BirthDate].
 *
 * <!--- TEST_NAME CoreInvalidBirthDateExceptionKnitTest -->
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.core.BirthDate
 * import com.fortechteams.valuetypes.core.exception.InvalidBirthDateException
 * import io.kotest.matchers.should
 * import io.kotest.matchers.types.beInstanceOf
 *
 * fun test() {
 *   // Future date
 *   val futureResult = BirthDate.fromString("2525-01-01")
 *   futureResult.exceptionOrNull() should beInstanceOf<InvalidBirthDateException>()
 *
 *   // Invalid format
 *   val invalidResult = BirthDate.fromString("not-a-date")
 *   invalidResult.exceptionOrNull() should beInstanceOf<InvalidBirthDateException>()
 *
 *   // Invalid date
 *   val invalidDateResult = BirthDate.fromDate(2000, 13, 1)
 *   invalidDateResult.exceptionOrNull() should beInstanceOf<InvalidBirthDateException>()
 * }
 * ```
 * <!--- KNIT example-core-InvalidBirthDateException-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 *
 * @property value The invalid value that caused the exception
 * @property msg A description of why the value is invalid
 */
class InvalidBirthDateException(value: String, msg: String, cause: Throwable? = null) :
  IllegalArgumentException("Invalid birthdate: $value - $msg", cause) {

  constructor(value: String, e: Throwable) : this(
    value,
    e.message ?: "Exception was thrown during BirthDate creation",
    cause = e.cause
  )
}
