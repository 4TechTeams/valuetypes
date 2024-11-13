// This file was automatically generated from Country.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCommonCountry04

import com.fortechteams.valuetypes.common.Country
import io.kotest.matchers.shouldBe

fun test() {
  val germany = Country.GERMANY
  germany.alpha2Code shouldBe "DE"
  germany.alpha2Code.length shouldBe 2
}
