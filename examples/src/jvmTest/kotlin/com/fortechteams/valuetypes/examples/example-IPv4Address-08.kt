// This file was automatically generated from IPv4Address.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleIPv4Address08

import com.fortechteams.valuetypes.network.IPv4Address
import io.kotest.matchers.shouldBe

fun test() {
  val examples = mapOf(
    "10.0.0.1" to true,
    "172.16.0.1" to true,
    "172.32.0.1" to false,
    "192.168.1.1" to true,
    "127.0.0.1" to true,
    "8.8.8.8" to false
  )

  examples.forEach { (ip, expectedPrivate) ->
    val addr = IPv4Address.fromString(ip).getOrThrow()
    addr.isPrivate() shouldBe expectedPrivate
  }
}
