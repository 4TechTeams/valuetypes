package com.fortechteams.valuetypes.person.switzerland

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class CantonTest {

  @Test
  fun `should provide correct default names based on language region`() {
    // German-speaking cantons
    Canton.ZH.defaultName shouldBe "Zürich"
    Canton.BE.defaultName shouldBe "Bern"
    Canton.BS.defaultName shouldBe "Basel"

    // French-speaking cantons
    Canton.GE.defaultName shouldBe "Genève"
    Canton.VD.defaultName shouldBe "Vaud"
    Canton.NE.defaultName shouldBe "Neuchâtel"

    // Italian-speaking canton
    Canton.TI.defaultName shouldBe "Ticino"

    // Bilingual cantons (using predominant language)
    Canton.FR.defaultName shouldBe "Fribourg"
    Canton.VS.defaultName shouldBe "Valais"
  }

  @Test
  fun `should provide correct names for each language`() {
    // Test Zurich (German)
    val zurich = Canton.ZH
    zurich.code shouldBe "ZH"
    zurich.defaultName shouldBe "Zürich"
    zurich.germanName shouldBe "Zürich"
    zurich.frenchName shouldBe "Zurich"
    zurich.italianName shouldBe "Zurigo"
    zurich.englishName shouldBe "Zurich"

    // Test Geneva (French)
    val geneva = Canton.GE
    geneva.code shouldBe "GE"
    geneva.defaultName shouldBe "Genève"
    geneva.germanName shouldBe "Genf"
    geneva.frenchName shouldBe "Genève"
    geneva.italianName shouldBe "Ginevra"
    geneva.englishName shouldBe "Geneva"

    // Test Ticino (Italian)
    val ticino = Canton.TI
    ticino.code shouldBe "TI"
    ticino.defaultName shouldBe "Ticino"
    ticino.germanName shouldBe "Tessin"
    ticino.frenchName shouldBe "Tessin"
    ticino.italianName shouldBe "Ticino"
    ticino.englishName shouldBe "Ticino"
  }

  @Test
  fun `should find canton by code`() {
    // Exact match
    Canton.fromCode("ZH") shouldBe Canton.ZH
    Canton.fromCode("GE") shouldBe Canton.GE
    Canton.fromCode("TI") shouldBe Canton.TI

    // Case insensitive
    Canton.fromCode("zh") shouldBe Canton.ZH
    Canton.fromCode("ge") shouldBe Canton.GE
    Canton.fromCode("ti") shouldBe Canton.TI

    // Invalid codes
    Canton.fromCode("XX") shouldBe null
    Canton.fromCode("") shouldBe null
    Canton.fromCode("Z") shouldBe null
    Canton.fromCode("ZHH") shouldBe null
  }

  @Test
  fun `should find canton from any valid name`() {
    // By default name
    Canton.fromName("Zürich") shouldBe Canton.ZH
    Canton.fromName("Genève") shouldBe Canton.GE

    // By German name
    Canton.fromName("Genf") shouldBe Canton.GE

    // By French name
    Canton.fromName("Berne") shouldBe Canton.BE

    // By Italian name
    Canton.fromName("Zurigo") shouldBe Canton.ZH

    // By English name
    Canton.fromName("Geneva") shouldBe Canton.GE
  }

  @Test
  fun `should handle case-insensitive name matching`() {
    Canton.fromName("ZÜRICH") shouldBe Canton.ZH
    Canton.fromName("zürich") shouldBe Canton.ZH
    Canton.fromName("ZüRiCh") shouldBe Canton.ZH
  }

  @Test
  fun `should handle whitespace in names`() {
    Canton.fromName("  Zürich  ") shouldBe Canton.ZH
    Canton.fromName("\tBern\n") shouldBe Canton.BE
    Canton.fromName(" Geneva ") shouldBe Canton.GE
  }

  @Test
  fun `should return null for invalid names`() {
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
  fun `should have unique codes`() {
    val codes = Canton.entries.map { it.code }
    val uniqueCodes = codes.toSet()
    codes.size shouldBe uniqueCodes.size
  }

  @Test
  fun `should have all 26 cantons`() {
    Canton.entries.size shouldBe 26
  }
}
