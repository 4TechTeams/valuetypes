// This file was automatically generated from Domain.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleNetworkDomain02

import com.fortechteams.valuetypes.network.Domain
import io.kotest.matchers.shouldBe

fun test() {
  Domain.parse("www.example.com").toFQDN() shouldBe "www.example.com."
}
