// This file was automatically generated from Country.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCommonCountry07

import com.fortechteams.valuetypes.common.Country
import com.fortechteams.valuetypes.common.Language
import io.kotest.matchers.shouldBe

fun test() {
  // Country with multiple official languages
  Country.SWITZERLAND.spokenLanguages shouldBe listOf(
    Language.GERMAN,
    Language.FRENCH,
    Language.ITALIAN,
    Language.ROMANSH
  )

  // Country with single official language
  Country.FRANCE.spokenLanguages shouldBe listOf(
    Language.FRENCH
  )
}
