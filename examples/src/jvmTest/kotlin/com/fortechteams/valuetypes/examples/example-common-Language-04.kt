// This file was automatically generated from Language.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCommonLanguage04

import com.fortechteams.valuetypes.common.Language
import io.kotest.matchers.shouldBe

fun test() {
  Language.fromEnglishName("German") shouldBe Language.GERMAN
  Language.fromEnglishName(" Arabic ") shouldBe Language.ARABIC
  Language.fromEnglishName("invalid") shouldBe null
}
