// This file was automatically generated from Country.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCommonCountry05

import com.fortechteams.valuetypes.common.Country
import io.kotest.matchers.shouldBe

fun test() {
  val france = Country.FRANCE
  france.alpha3Code shouldBe "FRA"
  france.alpha3Code.length shouldBe 3
}
