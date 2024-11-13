// This file was automatically generated from Country.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCommonCountry03

import com.fortechteams.valuetypes.common.Country
import io.kotest.matchers.shouldBe

fun test() {
  Country.SWITZERLAND.localLanguageName shouldBe "Schweiz"  // German
  Country.FRANCE.localLanguageName shouldBe "France"        // Same in both languages
}
