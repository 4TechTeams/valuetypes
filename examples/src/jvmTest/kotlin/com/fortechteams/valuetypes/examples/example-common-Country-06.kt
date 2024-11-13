// This file was automatically generated from Country.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCommonCountry06

import com.fortechteams.valuetypes.common.Country
import io.kotest.matchers.shouldBe

fun test() {
  val switzerland = Country.SWITZERLAND
  switzerland.numericCode shouldBe 756
  switzerland.numericCode.toString().length shouldBe 3
}
