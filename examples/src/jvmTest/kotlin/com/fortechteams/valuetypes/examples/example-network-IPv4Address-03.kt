// This file was automatically generated from IPv4Address.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleNetworkIPv4Address03

import com.fortechteams.valuetypes.network.IPv4Address
import io.kotest.matchers.shouldBe

fun test() {
  val addr = IPv4Address.fromString("192.168.1.1").getOrThrow()
  addr.octet2 shouldBe 168u.toUByte()
}
