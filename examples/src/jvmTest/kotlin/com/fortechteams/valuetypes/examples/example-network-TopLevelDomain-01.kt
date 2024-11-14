// This file was automatically generated from Domain.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleNetworkTopLevelDomain01

import com.fortechteams.valuetypes.network.TopLevelDomain
import io.kotest.matchers.shouldBe

fun test() {
  val tld = TopLevelDomain("com")
  tld.toString() shouldBe "com"
  tld.toFQDN() shouldBe "com."
  tld.labels shouldBe listOf("com")
}
