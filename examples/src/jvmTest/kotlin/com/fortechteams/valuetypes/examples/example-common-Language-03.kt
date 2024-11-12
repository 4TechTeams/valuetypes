// This file was automatically generated from Language.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCommonLanguage03

import com.fortechteams.valuetypes.common.Language
import io.kotest.matchers.shouldBe

fun test() {
  Language.fromThreeLetterCode("ara") shouldBe Language.ARABIC
  Language.fromThreeLetterCode("ARA") shouldBe Language.ARABIC
  Language.fromThreeLetterCode("xxx") shouldBe null
}
