// This file was automatically generated from IPv4Address.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleNetworkIPv4Address09

import com.fortechteams.valuetypes.network.IPv4Address
import com.fortechteams.valuetypes.network.NetworkClass
import io.kotest.matchers.shouldBe

fun test() {
  val examples = mapOf(
    "10.0.0.1" to NetworkClass.A,
    "172.16.0.1" to NetworkClass.B,
    "192.168.1.1" to NetworkClass.C,
    "224.0.0.1" to NetworkClass.D,
    "240.0.0.1" to NetworkClass.E
  )

  examples.forEach { (ip, expectedClass) ->
    val addr = IPv4Address.fromString(ip).getOrThrow()
    addr.networkClass() shouldBe expectedClass
  }
}
