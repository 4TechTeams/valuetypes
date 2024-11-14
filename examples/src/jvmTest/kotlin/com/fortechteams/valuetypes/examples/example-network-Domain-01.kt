// This file was automatically generated from Domain.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleNetworkDomain01

import com.fortechteams.valuetypes.network.Domain
import com.fortechteams.valuetypes.network.Subdomain
import io.kotest.matchers.shouldBe

fun test() {
  // Parse different types of domains
  val tld = Domain.parse("com")
  val apex = Domain.parse("example.com")
  val subdomain = Domain.parse("www.example.com")

  // Convert to FQDN (Fully Qualified Domain Name)
  tld.toFQDN() shouldBe "com."
  apex.toFQDN() shouldBe "example.com."
  subdomain.toFQDN() shouldBe "www.example.com."

  // Access domain parts
  (subdomain as Subdomain).apexDomain.toString() shouldBe "example.com"
}
