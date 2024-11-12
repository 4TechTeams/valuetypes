package com.fortechteams.valuetypes.common

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotContainDuplicates
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class LanguageTest {

  @Test
  fun testBasicLanguageProperties() {
    val german = Language.GERMAN

    german.twoLetterCode shouldBe "de"
    german.threeLetterCode shouldBe "deu"
    german.nativeName shouldBe "Deutsch"
    german.englishName shouldBe "German"
    german.scriptDirection shouldBe Language.ScriptDirection.LTR
  }

  @Test
  fun testFindingLanguageByTwoLetterCode() {
    Language.fromTwoLetterCode("en") shouldBe Language.ENGLISH
    Language.fromTwoLetterCode("EN") shouldBe Language.ENGLISH
    Language.fromTwoLetterCode("xx") shouldBe null
  }

  @Test
  fun testFindingLanguageByThreeLetterCode() {
    Language.fromThreeLetterCode("deu") shouldBe Language.GERMAN
    Language.fromThreeLetterCode("DEU") shouldBe Language.GERMAN
    Language.fromThreeLetterCode("xyz") shouldBe null
  }

  @Test
  fun testFindingLanguageByEnglishName() {
    Language.fromEnglishName("Spanish") shouldBe Language.SPANISH
    Language.fromEnglishName("SPANISH") shouldBe Language.SPANISH
    Language.fromEnglishName(" Spanish ") shouldBe Language.SPANISH
    Language.fromEnglishName("NonexistentLanguage") shouldBe null
  }

  @Test
  fun testAllRtlLanguagesAreProperlyMarked() {
    val rtlLanguages = Language.entries.filter {
      it.scriptDirection == Language.ScriptDirection.RTL
    }

    rtlLanguages shouldContainAll listOf(
      Language.ARABIC,
      Language.DIVEHI,
      Language.HEBREW,
      Language.PERSIAN,
      Language.URDU,
      Language.YIDDISH,
      Language.KURDISH,
      Language.SINDHI,
      Language.UIGHUR,
      Language.DIVEHI
    )

    // Verify we haven't missed any RTL languages in our test
    rtlLanguages shouldHaveSize 10
  }

  @Test
  fun testAllLanguagesHaveValidIsoCodes() {
    Language.entries.forEach { language ->
      // Two-letter code should be exactly 2 characters
      language.twoLetterCode.length shouldBe 2

      // Three-letter code should be exactly 3 characters
      language.threeLetterCode.length shouldBe 3

      // Codes should be lowercase
      language.twoLetterCode shouldBe language.twoLetterCode.lowercase()
      language.threeLetterCode shouldBe language.threeLetterCode.lowercase()
    }
  }

  @Test
  fun testAllLanguagesHaveNonEmptyNames() {
    Language.entries.forEach { language ->
      language.nativeName.isNotBlank() shouldBe true
      language.englishName.isNotBlank() shouldBe true
    }
  }

  @Test
  fun testNoDuplicateIsoCodesExist() {
    val twoLetterCodes = Language.entries.map { it.twoLetterCode }
    val threeLetterCodes = Language.entries.map { it.threeLetterCode }

    twoLetterCodes.shouldNotContainDuplicates()
    threeLetterCodes.shouldNotContainDuplicates()
  }
}
