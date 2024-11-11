// This file was automatically generated from IPv4Address.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleIPv4Address11

import com.fortechteams.valuetypes.network.IPv4Address
import io.kotest.matchers.shouldBe

fun test() {
  // Valid octets
  IPv4Address.fromOctets(192, 168, 1, 1).isSuccess shouldBe true

  // Invalid octet values
  IPv4Address.fromOctets(192, 168, 1, 256).isFailure shouldBe true
}
