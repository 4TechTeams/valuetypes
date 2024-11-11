// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleBirthDate05

import com.fortechteams.valuetypes.person.BirthDate
import io.kotest.matchers.shouldBe

fun test() {
  val birthDate = BirthDate.fromString("15.01.1990").getOrThrow()
  birthDate.toString() shouldBe "1990-01-15"
}
