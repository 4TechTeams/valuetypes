// This file was automatically generated from Canton.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCanton01

import com.fortechteams.valuetypes.person.switzerland.Canton
import io.kotest.matchers.shouldBe

fun test() {
  val zurich = Canton.ZH

  zurich.code shouldBe "ZH"
  zurich.defaultName shouldBe "Zürich"      // German, as it's in the German-speaking region
  zurich.germanName shouldBe "Zürich"
  zurich.frenchName shouldBe "Zurich"
  zurich.italianName shouldBe "Zurigo"
  zurich.englishName shouldBe "Zurich"

  val geneva = Canton.GE
  geneva.defaultName shouldBe "Genève"      // French, as it's in the French-speaking region
}