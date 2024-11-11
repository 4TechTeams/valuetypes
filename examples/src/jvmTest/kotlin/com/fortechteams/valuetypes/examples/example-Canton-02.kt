// This file was automatically generated from Canton.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCanton02

import com.fortechteams.valuetypes.person.switzerland.Canton
import io.kotest.matchers.shouldBe

fun test() {
  Canton.fromCode("ZH") shouldBe Canton.ZH
  Canton.fromCode("zh") shouldBe Canton.ZH
  Canton.fromCode("invalid") shouldBe null
}
