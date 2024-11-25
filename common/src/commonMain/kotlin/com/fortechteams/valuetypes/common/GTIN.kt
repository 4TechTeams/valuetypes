package com.fortechteams.valuetypes.common

import kotlin.jvm.JvmInline

/**
 * Represents a Global Trade Item Number (GTIN).
 *
 * Sealed interface providing type-safe representations for different GTIN formats:
 * - GTIN-8: 8 digits, used for small retail products
 * - GTIN-12: 12 digits, commonly known as UPC
 * - GTIN-13: 13 digits, commonly known as EAN
 * - GTIN-14: 14 digits, used for trade items
 *
 * ```kotlin
 * fun test() {
 *   // Parse different GTIN formats
 *   val upc = GTIN.parse("042100005264").getOrThrow() as GTIN12
 *   val ean = GTIN.parse("5901234123457").getOrThrow() as GTIN13
 *
 *   // Type-safe handling
 *   when (upc) {
 *     is GTIN8 -> println("Small retail product")
 *     is GTIN12 -> println("UPC product")
 *     is GTIN13 -> println("EAN product")
 *     is GTIN14 -> println("Trade item")
 *   }
 * }
 * ```
 */
sealed interface GTIN {
  /** The raw digit string without leading zeros */
  val value: String

  /** Validates the check digit */
  fun isValid(): Boolean

  /** Returns the GTIN-14 padded form */
  fun toGTIN14(): String = value.padStart(14, '0')

  companion object {
    /**
     * Parses a string into the appropriate GTIN type.
     *
     * ```kotlin
     * fun test() {
     *   val results = listOf(
     *     "12345670",        // GTIN-8
     *     "042100005264",    // GTIN-12
     *     "5901234123457",   // GTIN-13
     *     "15901234123454"   // GTIN-14
     *   ).map { GTIN.parse(it) }
     *
     *   results.all { it.isSuccess } shouldBe true
     * }
     * ```
     */
    fun parse(gtin: String): Result<GTIN> = runCatching {
      val cleaned = gtin.filter { it.isDigit() }

      when (cleaned.length) {
        8 -> GTIN8(cleaned)
        12 -> GTIN12(cleaned)
        13 -> GTIN13(cleaned)
        14 -> GTIN14(cleaned)
        else -> throw InvalidGTINException(
          gtin,
          "GTIN must be 8, 12, 13, or 14 digits long"
        )
      }
    }

    /** Calculates the check digit using the standard GTIN algorithm */
    private fun calculateCheckDigit(digits: String): Int {
      val values = digits.map { it.digitToInt() }
      val sum = values.mapIndexed { index, digit ->
        digit * if (index % 2 == 0) 3 else 1
      }.sum()
      return (10 - (sum % 10)) % 10
    }
  }
}