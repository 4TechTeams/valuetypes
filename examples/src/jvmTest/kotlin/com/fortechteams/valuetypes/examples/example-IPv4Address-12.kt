// This file was automatically generated from IPv4Address.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleIPv4Address12

import com.fortechteams.valuetypes.network.IPv4Address
import io.kotest.matchers.shouldBe

fun test() {
  val addr = IPv4Address.fromOctets(
    192.toUByte(),
    168.toUByte(),
    1.toUByte(),
    1.toUByte()
  )
  addr.isSuccess shouldBe true
  addr.getOrThrow().toString() shouldBe "192.168.1.1"
}
