// This file was automatically generated from Canton.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleCanton03

import com.fortechteams.valuetypes.person.switzerland.Canton
import io.kotest.matchers.shouldBe

fun test() {
  // Matches different name variants
  Canton.fromName("Zürich") shouldBe Canton.ZH
  Canton.fromName("Geneva") shouldBe Canton.GE
  Canton.fromName(" Bern ") shouldBe Canton.BE
  Canton.fromName("invalid") shouldBe null
}
