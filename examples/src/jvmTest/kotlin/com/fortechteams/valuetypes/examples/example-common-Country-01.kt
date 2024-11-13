// This file was automatically generated from Country.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCommonCountry01

import com.fortechteams.valuetypes.common.Country
import io.kotest.matchers.shouldBe

fun test() {
  val switzerland = Country.SWITZERLAND

  switzerland.englishName shouldBe "Switzerland"
  switzerland.localLanguageName shouldBe "Schweiz"
  switzerland.alpha2Code shouldBe "CH"
  switzerland.alpha3Code shouldBe "CHE"
  switzerland.numericCode shouldBe 756

  val france = Country.FRANCE
  france.localLanguageName shouldBe france.englishName  // Same in French
}
