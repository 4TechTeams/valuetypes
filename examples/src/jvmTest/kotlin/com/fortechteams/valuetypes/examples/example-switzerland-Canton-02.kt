// This file was automatically generated from Canton.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleSwitzerlandCanton02

import com.fortechteams.valuetypes.switzerland.Canton
import io.kotest.matchers.shouldBe

fun test() {
  Canton.fromCode("ZH") shouldBe Canton.ZUERICH
  Canton.fromCode("zh") shouldBe Canton.ZUERICH
  Canton.fromCode("invalid") shouldBe null
}
