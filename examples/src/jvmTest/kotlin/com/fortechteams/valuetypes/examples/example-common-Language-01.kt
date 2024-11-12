// This file was automatically generated from Language.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCommonLanguage01

import com.fortechteams.valuetypes.common.Language
import io.kotest.matchers.shouldBe

fun test() {
  val german = Language.GERMAN

  german.twoLetterCode shouldBe "de"
  german.threeLetterCode shouldBe "deu"
  german.nativeName shouldBe "Deutsch"
  german.englishName shouldBe "German"
  german.scriptDirection shouldBe Language.ScriptDirection.LTR

  val arabic = Language.ARABIC
  arabic.scriptDirection shouldBe Language.ScriptDirection.RTL
}
