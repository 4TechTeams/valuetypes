// This file was automatically generated from IPv4Address.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleIPv4Address13

import com.fortechteams.valuetypes.network.IPv4Address
import io.kotest.matchers.shouldBe

fun test() {
  val value = (192u shl 24) or (168u shl 16) or (1u shl 8) or 1u
  val addr = IPv4Address.fromUInt(value)
  addr.isSuccess shouldBe true
  addr.getOrThrow().toString() shouldBe "192.168.1.1"
}
