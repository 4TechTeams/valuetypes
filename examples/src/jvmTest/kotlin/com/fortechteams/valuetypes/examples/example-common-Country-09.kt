// This file was automatically generated from Country.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCommonCountry09

import com.fortechteams.valuetypes.common.Country
import io.kotest.matchers.shouldBe

fun test() {
  Country.fromName("Switzerland") shouldBe Country.SWITZERLAND
  Country.fromName("Schweiz") shouldBe Country.SWITZERLAND
  Country.fromName("Deutschland") shouldBe Country.GERMANY
  Country.fromName(" France ") shouldBe Country.FRANCE
  Country.fromName("invalid") shouldBe null
}
