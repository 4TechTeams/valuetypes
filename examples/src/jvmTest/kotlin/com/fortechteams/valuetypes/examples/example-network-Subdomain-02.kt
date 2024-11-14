// This file was automatically generated from Domain.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleNetworkSubdomain02

import com.fortechteams.valuetypes.network.Subdomain
import com.fortechteams.valuetypes.network.ApexDomain
import io.kotest.matchers.shouldBe

fun test() {
  // Single level subdomain
  val www = Subdomain(listOf("www"), "example", "com")
  www.parent() shouldBe ApexDomain("example", "com")

  // Multi-level subdomain
  val dev = Subdomain(listOf("dev", "www"), "example", "com")
  dev.parent().toString() shouldBe "www.example.com"
}
