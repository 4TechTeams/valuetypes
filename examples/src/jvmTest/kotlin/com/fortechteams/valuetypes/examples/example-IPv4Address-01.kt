// This file was automatically generated from IPv4Address.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleIPv4Address01

import com.fortechteams.valuetypes.network.IPv4Address
import com.fortechteams.valuetypes.network.NetworkClass
import io.kotest.matchers.shouldBe

fun test() {
  // Create from string
  val addr = IPv4Address.fromString("192.168.1.1").getOrThrow()

  addr.toString() shouldBe "192.168.1.1"

  // Access individual octets
  addr.octet1 shouldBe 192u.toUByte()

  // Check network class
  addr.networkClass() shouldBe NetworkClass.C

  // Check if private address
  addr.isPrivate() shouldBe true

  // Use predefined constants
  IPv4Address.LOCALHOST.toString() shouldBe "127.0.0.1"
}
