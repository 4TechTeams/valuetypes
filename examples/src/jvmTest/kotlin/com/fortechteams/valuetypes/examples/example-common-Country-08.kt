// This file was automatically generated from Country.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCommonCountry08

import com.fortechteams.valuetypes.common.Country
import io.kotest.matchers.shouldBe

fun test() {
  Country.fromAlpha3Code("FRA") shouldBe Country.FRANCE
  Country.fromAlpha3Code("fra") shouldBe Country.FRANCE
  Country.fromAlpha3Code("invalid") shouldBe null
}
