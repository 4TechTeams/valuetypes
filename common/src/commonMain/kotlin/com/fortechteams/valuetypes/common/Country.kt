package com.fortechteams.valuetypes.common

/**
 * Represents a country with its official names in English and local language(s).
 *
 * This interface provides a standardized way to represent countries and their names,
 * ensuring consistent access to both international (English) and local representations.
 *
 *
 * ## Example
 * ```kotlin
 * class Switzerland : Country {
 *   override val englishName: String = "Switzerland"
 *   override val localLanguageName: String = "Schweiz" // German as primary language
 * }
 *
 * class France : Country {
 *   override val englishName: String = "France"
 *   override val localLanguageName: String = "France" // French
 * }
 * ```
 *
 * Note: For countries with multiple official languages, [localLanguageName] typically
 * represents the name in the most widely used official language or follows official
 * government guidelines for primary representation.
 */
interface Country {

  /**
   * The country's official name in English.
   *
   * This is typically the name recognized internationally and used in
   * English-language contexts and international communications.
   */
  val englishName: String

  /**
   * The country's name in its primary local/official language.
   *
   * For countries with multiple official languages, this represents the name
   * in the most widely used official language or follows official government
   * guidelines for primary representation.
   *
   * Examples:
   * - Switzerland → "Schweiz" (German)
   * - Belgium → "België" (Dutch/Flemish)
   * - Luxembourg → "Lëtzebuerg" (Luxembourgish)
   */
  val localLanguageName: String
}
