// This file was automatically generated from BirthDate.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleBirthDate02

import com.fortechteams.valuetypes.person.BirthDate
import io.kotest.matchers.shouldBe

fun test() {
  val birthDate = BirthDate.fromString("1990-01-15").getOrThrow()
  birthDate.year shouldBe 1990
}
