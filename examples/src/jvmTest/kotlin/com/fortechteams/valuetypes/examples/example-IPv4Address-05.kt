// This file was automatically generated from IPv4Address.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleIPv4Address05

import com.fortechteams.valuetypes.network.IPv4Address
import io.kotest.matchers.shouldBe

fun test() {
  val addr = IPv4Address.fromString("192.168.1.1").getOrThrow()
  addr.octet4 shouldBe 1u.toUByte()
}
