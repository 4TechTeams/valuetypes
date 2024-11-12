// This file was automatically generated from IPv4Address.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleNetworkIPv4Address07

import com.fortechteams.valuetypes.network.IPv4Address
import io.kotest.matchers.shouldBe

fun test() {
  val addr = IPv4Address.fromString("192.168.1.1").getOrThrow()
  val expected = (192u shl 24) or (168u shl 16) or (1u shl 8) or 1u
  addr.toUInt() shouldBe expected
}
