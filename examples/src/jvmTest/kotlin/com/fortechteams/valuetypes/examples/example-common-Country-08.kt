// This file was automatically generated from Country.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCommonCountry08

import com.fortechteams.valuetypes.common.Country
import io.kotest.matchers.shouldBe

fun test() {
  Country.fromAlpha2Code("CH") shouldBe Country.SWITZERLAND
  Country.fromAlpha2Code("ch") shouldBe Country.SWITZERLAND
  Country.fromAlpha2Code("invalid") shouldBe null
}
