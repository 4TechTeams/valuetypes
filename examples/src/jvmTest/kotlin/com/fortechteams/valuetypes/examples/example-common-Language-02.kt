// This file was automatically generated from Language.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCommonLanguage02

import com.fortechteams.valuetypes.common.Language
import io.kotest.matchers.shouldBe

fun test() {
  Language.fromTwoLetterCode("de") shouldBe Language.GERMAN
  Language.fromTwoLetterCode("DE") shouldBe Language.GERMAN
  Language.fromTwoLetterCode("xx") shouldBe null
}
