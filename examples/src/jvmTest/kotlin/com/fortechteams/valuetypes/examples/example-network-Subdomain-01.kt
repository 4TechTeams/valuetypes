// This file was automatically generated from Domain.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleNetworkSubdomain01

import com.fortechteams.valuetypes.network.Subdomain
import io.kotest.matchers.shouldBe

fun test() {
  val sub = Subdomain(listOf("www"), "example", "com")

  // Access individual parts
  sub.subdomainLabels shouldBe listOf("www")
  sub.apexName shouldBe "example"
  sub.tld shouldBe "com"

  // Get the apex domain
  sub.apexDomain.toString() shouldBe "example.com"

  // Get full hierarchy
  val complex = Subdomain(listOf("dev", "staging"), "example", "com")
  complex.getSubdomainHierarchy().map { it.toString() } shouldBe listOf(
    "dev.staging.example.com",
    "staging.example.com"
  )
}
