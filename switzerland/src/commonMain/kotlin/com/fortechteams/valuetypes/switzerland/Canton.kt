package com.fortechteams.valuetypes.switzerland

import com.fortechteams.valuetypes.common.Country

/**
 * Represents Swiss cantons with their official codes and names in all national languages.
 *
 * <!--- TEST_NAME SwitzerlandCantonKnitTest -->
 *
 * ## Example
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.switzerland.Canton
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   val zurich = Canton.ZUERICH
 *
 *   zurich.code shouldBe "ZH"
 *   zurich.defaultName shouldBe "Zürich"      // German, as it's in the German-speaking region
 *   zurich.germanName shouldBe "Zürich"
 *   zurich.frenchName shouldBe "Zurich"
 *   zurich.italianName shouldBe "Zurigo"
 *   zurich.englishName shouldBe "Zurich"
 *
 *   val geneva = Canton.GENEVE
 *   geneva.defaultName shouldBe "Genève"      // French, as it's in the French-speaking region
 * }
 * ```
 * <!--- KNIT example-switzerland-Canton-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 */
enum class Canton(
  val code: String,
  val defaultName: String,
  val germanName: String,
  val frenchName: String,
  val italianName: String,
  val englishName: String
) {

  // German-speaking cantons
  ZUERICH(
    "ZH",
    "Zürich",
    "Zürich",
    "Zurich",
    "Zurigo",
    "Zurich"
  ),
  BERN(
    "BE",
    "Bern",
    "Bern",
    "Berne",
    "Berna",
    "Bern"
  ),
  LUZERN(
    "LU",
    "Luzern",
    "Luzern",
    "Lucerne",
    "Lucerna",
    "Lucerne"
  ),
  URI(
    "UR",
    "Uri",
    "Uri",
    "Uri",
    "Uri",
    "Uri"
  ),
  SCHWYZ(
    "SZ",
    "Schwyz",
    "Schwyz",
    "Schwytz",
    "Svitto",
    "Schwyz"
  ),
  OBWALDEN(
    "OW",
    "Obwalden",
    "Obwalden",
    "Obwald",
    "Obvaldo",
    "Obwalden"
  ),
  NIDWALDEN(
    "NW",
    "Nidwalden",
    "Nidwalden",
    "Nidwald",
    "Nidvaldo",
    "Nidwalden"
  ),
  GLARUS(
    "GL",
    "Glarus",
    "Glarus",
    "Glaris",
    "Glarona",
    "Glarus"
  ),
  ZUG(
    "ZG",
    "Zug",
    "Zug",
    "Zoug",
    "Zugo",
    "Zug"
  ),
  SOLOTHURN(
    "SO",
    "Solothurn",
    "Solothurn",
    "Soleure",
    "Soletta",
    "Solothurn"
  ),
  BASEL_STADT(
    "BS",
    "Basel",
    "Basel-Stadt",
    "Bâle-Ville",
    "Basilea Città",
    "Basel-City"
  ),
  BASEL_LANDSCHAFT(
    "BL",
    "Basel-Landschaft",
    "Basel-Landschaft",
    "Bâle-Campagne",
    "Basilea Campagna",
    "Basel-Country"
  ),
  SCHAFFHAUSEN(
    "SH",
    "Schaffhausen",
    "Schaffhausen",
    "Schaffhouse",
    "Sciaffusa",
    "Schaffhausen"
  ),
  APPENZELL_AUSSERRHODEN(
    "AR",
    "Appenzell Ausserrhoden",
    "Appenzell Ausserrhoden",
    "Appenzell Rhodes-Extérieures",
    "Appenzello Esterno",
    "Appenzell Outer Rhodes"
  ),
  APPENZELL_INNERRHODEN(
    "AI",
    "Appenzell Innerrhoden",
    "Appenzell Innerrhoden",
    "Appenzell Rhodes-Intérieures",
    "Appenzello Interno",
    "Appenzell Inner Rhodes"
  ),
  SANKT_GALLEN(
    "SG",
    "St. Gallen",
    "St. Gallen",
    "Saint-Gall",
    "San Gallo",
    "St. Gallen"
  ),
  GRAUBUENDEN(
    "GR",
    "Graubünden",
    "Graubünden",
    "Grisons",
    "Grigioni",
    "Graubünden"
  ),
  AARGAU(
    "AG",
    "Aargau",
    "Aargau",
    "Argovie",
    "Argovia",
    "Aargau"
  ),
  THURGAU(
    "TG",
    "Thurgau",
    "Thurgau",
    "Thurgovie",
    "Turgovia",
    "Thurgau"
  ),

  // French-speaking cantons
  GENEVE(
    "GE",
    "Genève",
    "Genf",
    "Genève",
    "Ginevra",
    "Geneva"
  ),
  VAUD(
    "VD",
    "Vaud",
    "Waadt",
    "Vaud",
    "Vaud",
    "Vaud"
  ),
  NEUCHATEL(
    "NE",
    "Neuchâtel",
    "Neuenburg",
    "Neuchâtel",
    "Neuchâtel",
    "Neuchâtel"
  ),
  JURA(
    "JU",
    "Jura",
    "Jura",
    "Jura",
    "Giura",
    "Jura"
  ),

  // Italian-speaking canton
  TICINO(
    "TI",
    "Ticino",
    "Tessin",
    "Tessin",
    "Ticino",
    "Ticino"
  ),

  // Bilingual cantons (German/French)
  FRIBOURG(
    "FR",
    "Fribourg",
    "Freiburg",
    "Fribourg",
    "Friburgo",
    "Fribourg"
  ),
  VALAIS(
    "VS",
    "Valais",
    "Wallis",
    "Valais",
    "Vallese",
    "Valais"
  );

  val codeIso31662 by lazy {
    "${Country.SWITZERLAND.alpha2Code}_$code"
  }

  companion object {

    /**
     * Returns the canton for the given code, ignoring case.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.switzerland.Canton
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   Canton.fromCode("ZH") shouldBe Canton.ZUERICH
     *   Canton.fromCode("zh") shouldBe Canton.ZUERICH
     *   Canton.fromCode("invalid") shouldBe null
     * }
     * ```
     * <!--- KNIT example-switzerland-Canton-02.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromCode(code: String): Canton? =
      entries.firstOrNull { it.code.equals(code, ignoreCase = true) }

    /**
     * Returns the canton matching any of its names (default, German, French, Italian, or English).
     * Matching is case-insensitive and ignores leading/trailing whitespace.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.switzerland.Canton
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   // Matches different name variants
     *   Canton.fromName("Zürich") shouldBe Canton.ZUERICH
     *   Canton.fromName("Geneva") shouldBe Canton.GENEVE
     *   Canton.fromName(" Bern ") shouldBe Canton.BERN
     *   Canton.fromName("invalid") shouldBe null
     * }
     * ```
     * <!--- KNIT example-switzerland-Canton-03.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromName(name: String): Canton? {
      val normalizedName = name.trim().lowercase()
      return entries.firstOrNull { canton ->
        listOf(
          canton.defaultName,
          canton.germanName,
          canton.frenchName,
          canton.italianName,
          canton.englishName
        ).any { it.trim().lowercase() == normalizedName }
      }
    }
  }
}
