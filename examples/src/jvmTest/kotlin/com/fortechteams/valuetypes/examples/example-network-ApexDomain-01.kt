// This file was automatically generated from Domain.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleNetworkApexDomain01

import com.fortechteams.valuetypes.network.ApexDomain
import io.kotest.matchers.shouldBe

fun test() {
  val apex = ApexDomain("example", "com")
  apex.name shouldBe "example"
  apex.tld shouldBe "com"
  apex.toString() shouldBe "example.com"
  apex.toFQDN() shouldBe "example.com."
}
