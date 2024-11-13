package com.fortechteams.valuetypes.common

import io.kotest.assertions.withClue
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotContainDuplicates
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.string.beBlank
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
  fun testNoDuplicateIsoCodesExist() {
    val twoLetterCodes = Language.entries.filter { it.twoLetterCode !== null }.map { it.twoLetterCode }
    val threeLetterCodes = Language.entries.map { it.threeLetterCode }

    twoLetterCodes.shouldNotContainDuplicates()
    threeLetterCodes.shouldNotContainDuplicates()
  }

  @Test
  fun languageNameCompliance() {
    Language.entries.forEach { language ->
      withClue("For $language") {
        language.nativeName shouldNot beBlank()
        language.englishName shouldNot beBlank()
      }
    }
  }

  @Test
  fun languageCodeCompliance() {
    Language.entries.forEach { language ->
      withClue("For $language.twoLetterCode") {
        // Two-letter code should be exactly 2 characters (if not null)
        language.twoLetterCode?.let {
          it.length shouldBe 2
        }

        // Codes should be lowercase
        language.twoLetterCode?.let {
          it shouldBe it.lowercase()
        }
      }

      withClue("For $language.threeLetterCode") {
        // Three-letter code should be exactly 3 characters
        language.threeLetterCode.length shouldBe 3

        // Codes should be lowercase
        language.threeLetterCode shouldBe language.threeLetterCode.lowercase()
      }
    }
  }
}
