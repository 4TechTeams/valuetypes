package com.fortechteams.valuetypes.person.switzerland

/**
 * Represents Swiss cantons with their official codes and names in all national languages.
 *
 * <!--- TEST_NAME CantonKnitTest -->
 *
 * ## Example
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.person.switzerland.Canton
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   val zurich = Canton.ZH
 *
 *   zurich.code shouldBe "ZH"
 *   zurich.defaultName shouldBe "Zürich"      // German, as it's in the German-speaking region
 *   zurich.germanName shouldBe "Zürich"
 *   zurich.frenchName shouldBe "Zurich"
 *   zurich.italianName shouldBe "Zurigo"
 *   zurich.englishName shouldBe "Zurich"
 *
 *   val geneva = Canton.GE
 *   geneva.defaultName shouldBe "Genève"      // French, as it's in the French-speaking region
 * }
 * ```
 * <!--- KNIT example-Canton-01.kt -->
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
  ZH(
    "ZH",
    "Zürich",
    "Zürich",
    "Zurich",
    "Zurigo",
    "Zurich"
  ),
  BE(
    "BE",
    "Bern",
    "Bern",
    "Berne",
    "Berna",
    "Bern"
  ),
  LU(
    "LU",
    "Luzern",
    "Luzern",
    "Lucerne",
    "Lucerna",
    "Lucerne"
  ),
  UR(
    "UR",
    "Uri",
    "Uri",
    "Uri",
    "Uri",
    "Uri"
  ),
  SZ(
    "SZ",
    "Schwyz",
    "Schwyz",
    "Schwytz",
    "Svitto",
    "Schwyz"
  ),
  OW(
    "OW",
    "Obwalden",
    "Obwalden",
    "Obwald",
    "Obvaldo",
    "Obwalden"
  ),
  NW(
    "NW",
    "Nidwalden",
    "Nidwalden",
    "Nidwald",
    "Nidvaldo",
    "Nidwalden"
  ),
  GL(
    "GL",
    "Glarus",
    "Glarus",
    "Glaris",
    "Glarona",
    "Glarus"
  ),
  ZG(
    "ZG",
    "Zug",
    "Zug",
    "Zoug",
    "Zugo",
    "Zug"
  ),
  SO(
    "SO",
    "Solothurn",
    "Solothurn",
    "Soleure",
    "Soletta",
    "Solothurn"
  ),
  BS(
    "BS",
    "Basel",
    "Basel-Stadt",
    "Bâle-Ville",
    "Basilea Città",
    "Basel-City"
  ),
  BL(
    "BL",
    "Basel-Landschaft",
    "Basel-Landschaft",
    "Bâle-Campagne",
    "Basilea Campagna",
    "Basel-Country"
  ),
  SH(
    "SH",
    "Schaffhausen",
    "Schaffhausen",
    "Schaffhouse",
    "Sciaffusa",
    "Schaffhausen"
  ),
  AR(
    "AR",
    "Appenzell Ausserrhoden",
    "Appenzell Ausserrhoden",
    "Appenzell Rhodes-Extérieures",
    "Appenzello Esterno",
    "Appenzell Outer Rhodes"
  ),
  AI(
    "AI",
    "Appenzell Innerrhoden",
    "Appenzell Innerrhoden",
    "Appenzell Rhodes-Intérieures",
    "Appenzello Interno",
    "Appenzell Inner Rhodes"
  ),
  SG(
    "SG",
    "St. Gallen",
    "St. Gallen",
    "Saint-Gall",
    "San Gallo",
    "St. Gallen"
  ),
  GR(
    "GR",
    "Graubünden",
    "Graubünden",
    "Grisons",
    "Grigioni",
    "Graubünden"
  ),
  AG(
    "AG",
    "Aargau",
    "Aargau",
    "Argovie",
    "Argovia",
    "Aargau"
  ),
  TG(
    "TG",
    "Thurgau",
    "Thurgau",
    "Thurgovie",
    "Turgovia",
    "Thurgau"
  ),

  // French-speaking cantons
  GE(
    "GE",
    "Genève",
    "Genf",
    "Genève",
    "Ginevra",
    "Geneva"
  ),
  VD(
    "VD",
    "Vaud",
    "Waadt",
    "Vaud",
    "Vaud",
    "Vaud"
  ),
  NE(
    "NE",
    "Neuchâtel",
    "Neuenburg",
    "Neuchâtel",
    "Neuchâtel",
    "Neuchâtel"
  ),
  JU(
    "JU",
    "Jura",
    "Jura",
    "Jura",
    "Giura",
    "Jura"
  ),

  // Italian-speaking canton
  TI(
    "TI",
    "Ticino",
    "Tessin",
    "Tessin",
    "Ticino",
    "Ticino"
  ),

  // Bilingual cantons (German/French)
  FR(
    "FR",
    "Fribourg",
    "Freiburg",
    "Fribourg",
    "Friburgo",
    "Fribourg"
  ),
  VS(
    "VS",
    "Valais",
    "Wallis",
    "Valais",
    "Vallese",
    "Valais"
  );

  companion object {

    /**
     * Returns the canton for the given code, ignoring case.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.person.switzerland.Canton
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   Canton.fromCode("ZH") shouldBe Canton.ZH
     *   Canton.fromCode("zh") shouldBe Canton.ZH
     *   Canton.fromCode("invalid") shouldBe null
     * }
     * ```
     * <!--- KNIT example-Canton-02.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromCode(code: String): Canton? =
      entries.firstOrNull { it.code.equals(code, ignoreCase = true) }

    /**
     * Returns the canton matching any of its names (default, German, French, Italian, or English).
     * Matching is case-insensitive and ignores leading/trailing whitespace.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.person.switzerland.Canton
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   // Matches different name variants
     *   Canton.fromName("Zürich") shouldBe Canton.ZH
     *   Canton.fromName("Geneva") shouldBe Canton.GE
     *   Canton.fromName(" Bern ") shouldBe Canton.BE
     *   Canton.fromName("invalid") shouldBe null
     * }
     * ```
     * <!--- KNIT example-Canton-03.kt -->
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
