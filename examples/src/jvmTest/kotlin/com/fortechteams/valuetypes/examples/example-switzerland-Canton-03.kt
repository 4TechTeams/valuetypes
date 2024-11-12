// This file was automatically generated from Canton.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleSwitzerlandCanton03

import com.fortechteams.valuetypes.switzerland.Canton
import io.kotest.matchers.shouldBe

fun test() {
  // Matches different name variants
  Canton.fromName("Zürich") shouldBe Canton.ZUERICH
  Canton.fromName("Geneva") shouldBe Canton.GENEVE
  Canton.fromName(" Bern ") shouldBe Canton.BERN
  Canton.fromName("invalid") shouldBe null
}
