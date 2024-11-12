package com.fortechteams.valuetypes.core

import io.kotest.matchers.shouldBe
import kotlinx.datetime.LocalDate
import kotlinx.datetime.todayIn
import kotlin.test.Test
import kotlin.test.assertNotNull

class BirthDateTest {

  @Test
  fun shouldCreateValidDateFromIsoString() {
    // Valid dates
    BirthDate.fromString("1990-01-15").isSuccess shouldBe true
    BirthDate.fromString("2000-12-31").isSuccess shouldBe true
    BirthDate.fromString("1900-01-01").isSuccess shouldBe true

    // Edge cases
    BirthDate.fromString("1900-01-01").getOrNull().toString() shouldBe "1900-01-01"
    BirthDate.fromString("2000-12-31").getOrNull().toString() shouldBe "2000-12-31"
  }

  @Test
  fun shouldCreateValidDateFromEuropeanFormatString() {
    // Valid dates
    BirthDate.fromString("15.01.1990").isSuccess shouldBe true
    BirthDate.fromString("31.12.2000").isSuccess shouldBe true

    // Verify correct parsing
    BirthDate.fromString("31.12.2000").getOrNull().toString() shouldBe "2000-12-31"
  }

  @Test
  fun shouldCreateValidDateFromSlashFormatStrings() {
    // YYYY/MM/DD format
    BirthDate.fromString("1990/01/15").isSuccess shouldBe true
    BirthDate.fromString("1990/01/15").getOrNull().toString() shouldBe "1990-01-15"

    // DD/MM/YYYY format
    BirthDate.fromString("15/01/1990").isSuccess shouldBe true
    BirthDate.fromString("15/01/1990").getOrNull().toString() shouldBe "1990-01-15"
  }

  @Test
  fun shouldFailForInvalidStringFormats() {
    // Invalid formats
    BirthDate.fromString("").isFailure shouldBe true
    BirthDate.fromString("1990-13-01").isFailure shouldBe true
    BirthDate.fromString("1990-01-32").isFailure shouldBe true
    BirthDate.fromString("1990/13/01").isFailure shouldBe true
    BirthDate.fromString("32.13.1990").isFailure shouldBe true

    // Incomplete dates
    BirthDate.fromString("1990-01").isFailure shouldBe true
    BirthDate.fromString("1990.01").isFailure shouldBe true

    // Invalid separators
    BirthDate.fromString("1990_01_01").isFailure shouldBe true
    BirthDate.fromString("1990 01 01").isFailure shouldBe true

    // Invalid characters
    BirthDate.fromString("199O-01-01").isFailure shouldBe true
    BirthDate.fromString("abcd-01-01").isFailure shouldBe true
  }

  @Test
  fun shouldFailForFutureDates() {
    val futureYear = kotlinx.datetime.Clock.System.todayIn(kotlinx.datetime.TimeZone.currentSystemDefault())
      .year + 1

    // Future dates in different formats
    BirthDate.fromString("$futureYear-01-01").isFailure shouldBe true
    BirthDate.fromString("01.01.$futureYear").isFailure shouldBe true
    BirthDate.fromString("$futureYear/01/01").isFailure shouldBe true

    // Future date using fromDate
    BirthDate.fromDate(futureYear, 1, 1).isFailure shouldBe true
  }

  @Test
  fun shouldCreateValidDateFromYearMonthDay() {
    // Valid cases
    val date = BirthDate.fromDate(1990, 1, 15).getOrNull()
    assertNotNull(date)
    date.toString() shouldBe "1990-01-15"

    // Edge cases for months
    BirthDate.fromDate(1990, 1, 1).isSuccess shouldBe true
    BirthDate.fromDate(1990, 12, 31).isSuccess shouldBe true
  }

  @Test
  fun shouldFailForInvalidComponentValues() {
    // Invalid months
    BirthDate.fromDate(1990, 0, 15).isFailure shouldBe true
    BirthDate.fromDate(1990, 13, 15).isFailure shouldBe true

    // Invalid days
    BirthDate.fromDate(1990, 1, 0).isFailure shouldBe true
    BirthDate.fromDate(1990, 1, 32).isFailure shouldBe true

    // Invalid day for specific month
    BirthDate.fromDate(1990, 2, 30).isFailure shouldBe true
    BirthDate.fromDate(1990, 4, 31).isFailure shouldBe true
  }

  @Test
  fun shouldCorrectlyHandleLeapYears() {
    // Valid leap year date
    BirthDate.fromDate(2000, 2, 29).isSuccess shouldBe true

    // Invalid leap year dates
    BirthDate.fromDate(1900, 2, 29).isFailure shouldBe true
    BirthDate.fromDate(2001, 2, 29).isFailure shouldBe true
  }

  @Test
  fun shouldCorrectlyCalculateAge() {
    val birthDate = BirthDate.fromDate(1990, 6, 15).getOrNull()
    assertNotNull(birthDate)

    // Test age calculation at specific dates
    birthDate.getAgeAt(LocalDate(2000, 6, 14)) shouldBe 9
    birthDate.getAgeAt(LocalDate(2000, 6, 15)) shouldBe 10
    birthDate.getAgeAt(LocalDate(2000, 6, 16)) shouldBe 10
  }

  @Test
  fun shouldCorrectlyExtractComponents() {
    val birthDate = BirthDate.fromDate(1990, 6, 15).getOrNull()
    assertNotNull(birthDate)

    birthDate.year shouldBe 1990
    birthDate.month shouldBe 6
    birthDate.day shouldBe 15
  }

  @Test
  fun shouldMaintainValueSemantics() {
    val date1 = BirthDate.fromDate(1990, 6, 15).getOrNull()
    val date2 = BirthDate.fromDate(1990, 6, 15).getOrNull()
    val date3 = BirthDate.fromDate(1990, 6, 16).getOrNull()

    assertNotNull(date1)
    assertNotNull(date2)
    assertNotNull(date3)

    // Same values should be equal
    date1 shouldBe date2

    // Different values should not be equal
    (date1 == date3) shouldBe false

    // Verify hashCode consistency
    date1.hashCode() shouldBe date2.hashCode()
  }

  @Test
  fun shouldHandleConversionToAndFromLocalDate() {
    val originalDate = LocalDate(1990, 6, 15)
    val birthDate = BirthDate.fromLocalDate(originalDate).getOrNull()
    assertNotNull(birthDate)

    val roundTrip = birthDate.toLocalDate()
    roundTrip shouldBe originalDate
  }

  @Test
  fun shouldHandleHistoricalDates() {
    // Test various historical dates
    BirthDate.fromString("1800-01-01").isSuccess shouldBe true
    BirthDate.fromString("1753-01-01").isSuccess shouldBe true // Gregorian calendar adoption in many countries

    // Verify correct parsing of historical dates
    BirthDate.fromString("1753-01-01").getOrNull().toString() shouldBe "1753-01-01"

    // Note: kotlinx.datetime supports dates from year 1 to year 999999
    BirthDate.fromString("0001-01-01").isSuccess shouldBe true
  }
}
