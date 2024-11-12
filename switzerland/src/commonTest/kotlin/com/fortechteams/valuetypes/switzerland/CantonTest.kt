package com.fortechteams.valuetypes.switzerland

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class CantonTest {

  @Test
  fun shouldProvideCorrectDefaultNamesBasedOnLanguageRegion() {
    // German-speaking cantons
    Canton.ZUERICH.defaultName shouldBe "Zürich"
    Canton.BERN.defaultName shouldBe "Bern"
    Canton.BASEL_STADT.defaultName shouldBe "Basel"

    // French-speaking cantons
    Canton.GENEVE.defaultName shouldBe "Genève"
    Canton.VAUD.defaultName shouldBe "Vaud"
    Canton.NEUCHATEL.defaultName shouldBe "Neuchâtel"

    // Italian-speaking canton
    Canton.TICINO.defaultName shouldBe "Ticino"

    // Bilingual cantons (using predominant language)
    Canton.FRIBOURG.defaultName shouldBe "Fribourg"
    Canton.VALAIS.defaultName shouldBe "Valais"
  }

  @Test
  fun shouldProvideCorrectNamesForEachLanguage() {
    // Test Zurich (German)
    val zurich = Canton.ZUERICH
    zurich.code shouldBe "ZH"
    zurich.defaultName shouldBe "Zürich"
    zurich.germanName shouldBe "Zürich"
    zurich.frenchName shouldBe "Zurich"
    zurich.italianName shouldBe "Zurigo"
    zurich.englishName shouldBe "Zurich"

    // Test Geneva (French)
    val geneva = Canton.GENEVE
    geneva.code shouldBe "GE"
    geneva.defaultName shouldBe "Genève"
    geneva.germanName shouldBe "Genf"
    geneva.frenchName shouldBe "Genève"
    geneva.italianName shouldBe "Ginevra"
    geneva.englishName shouldBe "Geneva"

    // Test Ticino (Italian)
    val ticino = Canton.TICINO
    ticino.code shouldBe "TI"
    ticino.defaultName shouldBe "Ticino"
    ticino.germanName shouldBe "Tessin"
    ticino.frenchName shouldBe "Tessin"
    ticino.italianName shouldBe "Ticino"
    ticino.englishName shouldBe "Ticino"
  }

  @Test
  fun shouldFindCantonByCode() {
    // Exact match
    Canton.fromCode("ZH") shouldBe Canton.ZUERICH
    Canton.fromCode("GE") shouldBe Canton.GENEVE
    Canton.fromCode("TI") shouldBe Canton.TICINO

    // Case insensitive
    Canton.fromCode("zh") shouldBe Canton.ZUERICH
    Canton.fromCode("ge") shouldBe Canton.GENEVE
    Canton.fromCode("ti") shouldBe Canton.TICINO

    // Invalid codes
    Canton.fromCode("XX") shouldBe null
    Canton.fromCode("") shouldBe null
    Canton.fromCode("Z") shouldBe null
    Canton.fromCode("ZHH") shouldBe null
  }

  @Test
  fun shouldFindCantonFromAnyValidName() {
    // By default name
    Canton.fromName("Zürich") shouldBe Canton.ZUERICH
    Canton.fromName("Genève") shouldBe Canton.GENEVE

    // By German name
    Canton.fromName("Genf") shouldBe Canton.GENEVE

    // By French name
    Canton.fromName("Berne") shouldBe Canton.BERN

    // By Italian name
    Canton.fromName("Zurigo") shouldBe Canton.ZUERICH

    // By English name
    Canton.fromName("Geneva") shouldBe Canton.GENEVE
  }

  @Test
  fun shouldHandleCaseInsensitiveNameMatching() {
    Canton.fromName("ZÜRICH") shouldBe Canton.ZUERICH
    Canton.fromName("zürich") shouldBe Canton.ZUERICH
    Canton.fromName("ZüRiCh") shouldBe Canton.ZUERICH
  }

  @Test
  fun shouldHandleWhitespaceInNames() {
    Canton.fromName("  Zürich  ") shouldBe Canton.ZUERICH
    Canton.fromName("\tBern\n") shouldBe Canton.BERN
    Canton.fromName(" Geneva ") shouldBe Canton.GENEVE
  }

  @Test
  fun shouldReturnNullForInvalidNames() {
    val invalidInputs = listOf(
      "",
      " ",
      "InvalidCanton",
      "Z",
      "Switzerland",
      "123"
    )

    invalidInputs.forEach { input ->
      Canton.fromName(input) shouldBe null
    }
  }

  @Test
  fun shouldHaveUniqueCodes() {
    val codes = Canton.entries.map { it.code }
    val uniqueCodes = codes.toSet()
    codes.size shouldBe uniqueCodes.size
  }

  @Test
  fun shouldHaveAllTwentySixCantons() {
    Canton.entries.size shouldBe 26
  }
}
