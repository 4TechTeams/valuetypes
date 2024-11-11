// This file was automatically generated from IPv4Address.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleIPv4Address10

import com.fortechteams.valuetypes.network.IPv4Address
import io.kotest.matchers.shouldBe

fun test() {
  // Successful parsing
  IPv4Address.fromString("192.168.1.1").isSuccess shouldBe true

  // Invalid formats
  IPv4Address.fromString("192.168.1").isFailure shouldBe true
  IPv4Address.fromString("192.168.1.256").isFailure shouldBe true
}
