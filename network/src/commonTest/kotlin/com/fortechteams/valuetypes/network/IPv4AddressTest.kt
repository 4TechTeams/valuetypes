package com.fortechteams.valuetypes.network

import io.kotest.matchers.shouldBe
import kotlin.test.Test
import kotlin.test.assertNotNull

class IPv4AddressTest {
  @Test
  fun `should create valid IP from string`() {
    // Valid IPs
    IPv4Address.fromString("192.168.1.1").isSuccess shouldBe true
    IPv4Address.fromString("0.0.0.0").isSuccess shouldBe true
    IPv4Address.fromString("255.255.255.255").isSuccess shouldBe true

    // Edge cases
    IPv4Address.fromString("0.0.0.0").getOrNull().toString() shouldBe "0.0.0.0"
    IPv4Address.fromString("255.255.255.255").getOrNull().toString() shouldBe "255.255.255.255"
  }

  @Test
  fun `should fail for invalid string formats`() {
    // Invalid formats
    IPv4Address.fromString("").isFailure shouldBe true
    IPv4Address.fromString("192.168.1").isFailure shouldBe true
    IPv4Address.fromString("192.168.1.1.1").isFailure shouldBe true
    IPv4Address.fromString("192.168.1.").isFailure shouldBe true
    IPv4Address.fromString(".192.168.1.1").isFailure shouldBe true
    IPv4Address.fromString("192.168.1.1.").isFailure shouldBe true

    // Invalid numbers
    IPv4Address.fromString("256.168.1.1").isFailure shouldBe true
    IPv4Address.fromString("192.268.1.1").isFailure shouldBe true
    IPv4Address.fromString("192.168.256.1").isFailure shouldBe true
    IPv4Address.fromString("192.168.1.256").isFailure shouldBe true

    // Negative numbers
    IPv4Address.fromString("-1.168.1.1").isFailure shouldBe true
    IPv4Address.fromString("192.-1.1.1").isFailure shouldBe true

    // Invalid characters
    IPv4Address.fromString("abc.168.1.1").isFailure shouldBe true
    IPv4Address.fromString("192.168.1.abc").isFailure shouldBe true
    IPv4Address.fromString("192.168.1.1a").isFailure shouldBe true
  }

  @Test
  fun `should create valid IP from octets using Int`() {
    // Valid cases
    val ip = IPv4Address.fromOctets(192, 168, 1, 1).getOrNull()
    assertNotNull(ip)
    ip.toString() shouldBe "192.168.1.1"

    // Edge cases
    IPv4Address.fromOctets(0, 0, 0, 0).getOrNull().toString() shouldBe "0.0.0.0"
    IPv4Address.fromOctets(255, 255, 255, 255).getOrNull().toString() shouldBe "255.255.255.255"
  }

  @Test
  fun `should fail for invalid octet values using Int`() {
    // Values too high
    IPv4Address.fromOctets(256, 168, 1, 1).isFailure shouldBe true
    IPv4Address.fromOctets(192, 256, 1, 1).isFailure shouldBe true

    // Negative values
    IPv4Address.fromOctets(-1, 168, 1, 1).isFailure shouldBe true
    IPv4Address.fromOctets(192, -1, 1, 1).isFailure shouldBe true
  }

  @Test
  fun `should create valid IP from octets using UByte`() {
    // Valid cases
    val ip = IPv4Address.fromOctets(192u, 168u, 1u, 1u).getOrNull()
    assertNotNull(ip)
    ip.toString() shouldBe "192.168.1.1"

    // Edge cases
    IPv4Address.fromOctets(0u, 0u, 0u, 0u).getOrNull().toString() shouldBe "0.0.0.0"
    IPv4Address.fromOctets(255u, 255u, 255u, 255u).getOrNull().toString() shouldBe "255.255.255.255"
  }

  @Test
  fun `should create valid IP from UInt`() {
    // Test conversion from UInt and back
    val originalIp = IPv4Address.fromOctets(192, 168, 1, 1).getOrNull()
    assertNotNull(originalIp)

    val uintValue = originalIp.toUInt()
    val recreatedIp = IPv4Address.fromUInt(uintValue).getOrNull()

    recreatedIp shouldBe originalIp
  }

  @Test
  fun `should correctly extract octets`() {
    val ip = IPv4Address.fromOctets(192, 168, 1, 1).getOrNull()
    assertNotNull(ip)

    ip.octet1 shouldBe 192u.toUByte()
    ip.octet2 shouldBe 168u.toUByte()
    ip.octet3 shouldBe 1u.toUByte()
    ip.octet4 shouldBe 1u.toUByte()
  }

  @Test
  fun `should handle well-known addresses`() {
    // Test constants
    IPv4Address.LOCALHOST.toString() shouldBe "127.0.0.1"
    IPv4Address.ANY.toString() shouldBe "0.0.0.0"
    IPv4Address.BROADCAST.toString() shouldBe "255.255.255.255"
  }

  @Test
  fun `should maintain value semantics`() {
    val ip1 = IPv4Address.fromOctets(192, 168, 1, 1).getOrNull()
    val ip2 = IPv4Address.fromOctets(192, 168, 1, 1).getOrNull()
    val ip3 = IPv4Address.fromOctets(192, 168, 1, 2).getOrNull()

    assertNotNull(ip1)
    assertNotNull(ip2)
    assertNotNull(ip3)

    // Same values should be equal
    ip1 shouldBe ip2

    // Different values should not be equal
    (ip1 == ip3) shouldBe false
  }

  @Test
  fun `should handle boundary value conversions`() {
    val maxIp = IPv4Address.fromUInt(UInt.MAX_VALUE).getOrNull()
    assertNotNull(maxIp)
    maxIp.toString() shouldBe "255.255.255.255"

    val minIp = IPv4Address.fromUInt(0u).getOrNull()
    assertNotNull(minIp)
    minIp.toString() shouldBe "0.0.0.0"
  }
}
