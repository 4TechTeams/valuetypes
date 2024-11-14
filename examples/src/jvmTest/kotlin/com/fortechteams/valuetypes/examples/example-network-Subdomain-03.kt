// This file was automatically generated from Domain.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleNetworkSubdomain03

import com.fortechteams.valuetypes.network.Subdomain
import io.kotest.matchers.shouldBe

fun test() {
  val domain = Subdomain(
    labels = listOf("dev", "staging"),
    apexName = "example",
    tld = "com"
  )

  val hierarchy = domain.getSubdomainHierarchy()
  hierarchy.map { it.toString() } shouldBe listOf(
    "dev.staging.example.com",
    "staging.example.com"
  )
}
