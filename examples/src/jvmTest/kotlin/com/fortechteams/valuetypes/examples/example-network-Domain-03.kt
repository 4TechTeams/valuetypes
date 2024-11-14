// This file was automatically generated from Domain.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleNetworkDomain03

import com.fortechteams.valuetypes.network.Domain
import com.fortechteams.valuetypes.network.TopLevelDomain
import com.fortechteams.valuetypes.network.ApexDomain
import com.fortechteams.valuetypes.network.Subdomain
import io.kotest.matchers.shouldBe

fun test() {
  Domain.parse("com") shouldBe TopLevelDomain("com")
  Domain.parse("example.com") shouldBe ApexDomain("example", "com")
  Domain.parse("www.example.com") shouldBe
    Subdomain(listOf("www"), "example", "com")

  // Invalid domains throw InvalidDomainException
  runCatching { Domain.parse("invalid..com") }.isFailure shouldBe true
}
