package com.fortechteams.valuetypes.common

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class CountryTest {

  @Test
  fun shouldProvideCorrectNames() {
    // Test Switzerland (multilingual country)
    val switzerland = Country.SWITZERLAND
    switzerland.englishName shouldBe "Switzerland"
    switzerland.localLanguageName shouldBe "Schweiz"
    switzerland.alpha2Code shouldBe "CH"
    switzerland.alpha3Code shouldBe "CHE"
    switzerland.numericCode shouldBe 756

    // Test France (same name in English and local)
    val france = Country.FRANCE
    france.englishName shouldBe "France"
    france.localLanguageName shouldBe france.englishName
    france.alpha2Code shouldBe "FR"
    france.alpha3Code shouldBe "FRA"
    france.numericCode shouldBe 250

    // Test Germany (different names)
    val germany = Country.GERMANY
    germany.englishName shouldBe "Germany"
    germany.localLanguageName shouldBe "Deutschland"
    germany.alpha2Code shouldBe "DE"
    germany.alpha3Code shouldBe "DEU"
    germany.numericCode shouldBe 276
  }

  @Test
  fun shouldFindCountryByAlpha2Code() {
    // Exact match
    Country.fromAlpha2Code("CH") shouldBe Country.SWITZERLAND
    Country.fromAlpha2Code("FR") shouldBe Country.FRANCE
    Country.fromAlpha2Code("DE") shouldBe Country.GERMANY

    // Case insensitive
    Country.fromAlpha2Code("ch") shouldBe Country.SWITZERLAND
    Country.fromAlpha2Code("fr") shouldBe Country.FRANCE
    Country.fromAlpha2Code("de") shouldBe Country.GERMANY

    // Invalid codes
    Country.fromAlpha2Code("XX") shouldBe null
    Country.fromAlpha2Code("") shouldBe null
    Country.fromAlpha2Code("F") shouldBe null
    Country.fromAlpha2Code("FRA") shouldBe null
  }

  @Test
  fun shouldFindCountryByAlpha3Code() {
    // Exact match
    Country.fromAlpha3Code("CHE") shouldBe Country.SWITZERLAND
    Country.fromAlpha3Code("FRA") shouldBe Country.FRANCE
    Country.fromAlpha3Code("DEU") shouldBe Country.GERMANY

    // Case insensitive
    Country.fromAlpha3Code("che") shouldBe Country.SWITZERLAND
    Country.fromAlpha3Code("fra") shouldBe Country.FRANCE
    Country.fromAlpha3Code("deu") shouldBe Country.GERMANY

    // Invalid codes
    Country.fromAlpha3Code("XXX") shouldBe null
    Country.fromAlpha3Code("") shouldBe null
    Country.fromAlpha3Code("CH") shouldBe null
    Country.fromAlpha3Code("DEUT") shouldBe null
  }

  @Test
  fun shouldFindCountryFromAnyValidName() {
    // By English name
    Country.fromName("Switzerland") shouldBe Country.SWITZERLAND
    Country.fromName("Germany") shouldBe Country.GERMANY

    // By local name
    Country.fromName("Schweiz") shouldBe Country.SWITZERLAND
    Country.fromName("Deutschland") shouldBe Country.GERMANY

    // Same in both languages
    Country.fromName("France") shouldBe Country.FRANCE
  }

  @Test
  fun shouldHandleCaseInsensitiveNameMatching() {
    Country.fromName("SWITZERLAND") shouldBe Country.SWITZERLAND
    Country.fromName("switzerland") shouldBe Country.SWITZERLAND
    Country.fromName("SwItZeRlAnD") shouldBe Country.SWITZERLAND
    Country.fromName("DEUTSCHLAND") shouldBe Country.GERMANY
  }

  @Test
  fun shouldHandleWhitespaceInNames() {
    Country.fromName("  Switzerland  ") shouldBe Country.SWITZERLAND
    Country.fromName("\tFrance\n") shouldBe Country.FRANCE
    Country.fromName(" Deutschland ") shouldBe Country.GERMANY
  }

  @Test
  fun shouldReturnNullForInvalidNames() {
    val invalidInputs = listOf(
      "",
      " ",
      "InvalidCountry",
      "Sw",
      "Europa",
      "123"
    )

    invalidInputs.forEach { input ->
      Country.fromName(input) shouldBe null
    }
  }

  @Test
  fun shouldHaveUniqueAlpha2Codes() {
    val codes = Country.entries.map { it.alpha2Code }
    val uniqueCodes = codes.toSet()
    codes.size shouldBe uniqueCodes.size
  }

  @Test
  fun shouldHaveUniqueAlpha3Codes() {
    val codes = Country.entries.map { it.alpha3Code }
    val uniqueCodes = codes.toSet()
    codes.size shouldBe uniqueCodes.size
  }

  @Test
  fun shouldHaveUniqueNumericCodes() {
    val codes = Country.entries.map { it.numericCode }
    val uniqueCodes = codes.toSet()
    codes.size shouldBe uniqueCodes.size
  }

  @Test
  fun shouldHaveValidFormatForAllCodes() {
    Country.entries.forEach { country ->
      // Alpha-2 code should be exactly 2 uppercase letters
      country.alpha2Code.length shouldBe 2
      country.alpha2Code.matches(Regex("[A-Z]{2}")) shouldBe true

      // Alpha-3 code should be exactly 3 uppercase letters
      country.alpha3Code.length shouldBe 3
      country.alpha3Code.matches(Regex("[A-Z]{3}")) shouldBe true

      // Numeric code should be between 0 and 999
      (country.numericCode in 0..999) shouldBe true
    }
  }

  @Test
  fun shouldHaveAllUNMemberStates() {
    // As of 2024, there are 193 UN member states plus Vatican City and Palestine
    // Adjust this number based on your implementation's scope
    Country.entries.size shouldBe 195
  }
}