package com.fortechteams.valuetypes.network

import kotlin.jvm.JvmInline

/**
 * Represents an IPv4 address as a value class wrapping an unsigned 32-bit integer.
 *
 * <!--- TEST_NAME IPv4AddressKnitTest -->
 *
 * This implementation provides a memory-efficient way to handle IPv4 addresses while
 * ensuring type safety and offering convenient conversion methods.
 *
 * Example usage:
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.network.IPv4Address
 * import com.fortechteams.valuetypes.network.NetworkClass
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   // Create from string
 *   val addr = IPv4Address.fromString("192.168.1.1").getOrThrow()
 *
 *   addr.toString() shouldBe "192.168.1.1"
 *
 *   // Access individual octets
 *   addr.octet1 shouldBe 192u.toUByte()
 *
 *   // Check network class
 *   addr.networkClass() shouldBe NetworkClass.C
 *
 *   // Check if private address
 *   addr.isPrivate() shouldBe true
 *
 *   // Use predefined constants
 *   IPv4Address.LOCALHOST.toString() shouldBe "127.0.0.1"
 * }
 * ```
 * <!--- KNIT example-IPv4Address-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 *
 * The class provides several factory methods for creating IPv4 addresses:
 * - [fromString] for parsing string representations
 * - [fromOctets] for creating from individual octets
 * - [fromUInt] for creating from an unsigned 32-bit integer
 *
 * Common IP addresses are available as constants:
 * - [LOCALHOST] (127.0.0.1)
 * - [ANY] (0.0.0.0)
 * - [BROADCAST] (255.255.255.255)
 *
 * @property value The internal unsigned 32-bit integer representation of the IP address
 */
@JvmInline
value class IPv4Address private constructor(private val value: UInt) {

  /**
   * The first octet of the IP address (most significant byte).
   *
   * Example:
   * ```kotlin
   * @Test
   * fun octet1_example() {
   *   val addr = IPv4Address.fromString("192.168.1.1").getOrNull()!!
   *   assertEquals(192.toUByte(), addr.octet1)
   * }
   * ```
   */
  val octet1: UByte get() = ((value shr 24) and 0xFFu).toUByte()

  /**
   * The second octet of the IP address.
   *
   * Example:
   * ```kotlin
   * @Test
   * fun octet2_example() {
   *   val addr = IPv4Address.fromString("192.168.1.1").getOrNull()!!
   *   assertEquals(168.toUByte(), addr.octet2)
   * }
   * ```
   */
  val octet2: UByte get() = ((value shr 16) and 0xFFu).toUByte()

  /**
   * The third octet of the IP address.
   *
   * Example:
   * ```kotlin
   * @Test
   * fun octet3_example() {
   *   val addr = IPv4Address.fromString("192.168.1.1").getOrNull()!!
   *   assertEquals(1.toUByte(), addr.octet3)
   * }
   * ```
   */
  val octet3: UByte get() = ((value shr 8) and 0xFFu).toUByte()

  /**
   * The fourth octet of the IP address (least significant byte).
   *
   * Example:
   * ```kotlin
   * @Test
   * fun octet4_example() {
   *   val addr = IPv4Address.fromString("192.168.1.1").getOrNull()!!
   *   assertEquals(1.toUByte(), addr.octet4)
   * }
   * ```
   */
  val octet4: UByte get() = (value and 0xFFu).toUByte()

  /**
   * Returns the string representation of the IP address in dotted decimal notation.
   *
   * Example:
   * ```kotlin
   * @Test
   * fun toString_example() {
   *   val addr = IPv4Address.fromString("192.168.1.1").getOrNull()!!
   *   assertEquals("192.168.1.1", addr.toString())
   * }
   * ```
   *
   * @return The IP address in format "xxx.xxx.xxx.xxx"
   */
  override fun toString(): String =
    "$octet1.$octet2.$octet3.$octet4"

  /**
   * Returns the unsigned 32-bit integer representation of the IP address.
   *
   * Example:
   * ```kotlin
   * @Test
   * fun toUInt_example() {
   *   val addr = IPv4Address.fromString("192.168.1.1").getOrNull()!!
   *   val expected = (192u shl 24) or (168u shl 16) or (1u shl 8) or 1u
   *   assertEquals(expected, addr.toUInt())
   * }
   * ```
   *
   * @return The IP address as an unsigned 32-bit integer
   */
  fun toUInt(): UInt = value

  /**
   * Determines whether this IP address is in a private network range.
   *
   * Private IP ranges are:
   * - 10.0.0.0 to 10.255.255.255 (Class A)
   * - 172.16.0.0 to 172.31.255.255 (Class B)
   * - 192.168.0.0 to 192.168.255.255 (Class C)
   * - 127.0.0.0 to 127.255.255.255 (Loopback)
   *
   * Example:
   * ```kotlin
   * @Test
   * fun isPrivate_example() {
   *   val examples = mapOf(
   *     "10.0.0.1" to true,
   *     "172.16.0.1" to true,
   *     "172.32.0.1" to false,
   *     "192.168.1.1" to true,
   *     "127.0.0.1" to true,
   *     "8.8.8.8" to false
   *   )
   *
   *   examples.forEach { (ip, expectedPrivate) ->
   *     val addr = IPv4Address.fromString(ip).getOrNull()!!
   *     assertEquals(expectedPrivate, addr.isPrivate())
   *   }
   * }
   * ```
   *
   * @return true if the IP address is in a private range, false otherwise
   */
  fun isPrivate(): Boolean = when {
    // Class A private network (10.0.0.0 to 10.255.255.255)
    octet1.toInt() == 10 -> true

    // Class B private networks (172.16.0.0 to 172.31.255.255)
    octet1.toInt() == 172 && octet2.toInt() in 16..31 -> true

    // Class C private networks (192.168.0.0 to 192.168.255.255)
    octet1.toInt() == 192 && octet2.toInt() == 168 -> true

    // Loopback addresses (127.0.0.0 to 127.255.255.255)
    octet1.toInt() == 127 -> true

    else -> false
  }

  /**
   * Determines the network class of this IP address according to classful networking rules.
   *
   * The network class is determined by the first octet:
   * - Class A: 0-127
   * - Class B: 128-191
   * - Class C: 192-223
   * - Class D: 224-239 (Multicast)
   * - Class E: 240-255 (Reserved)
   *
   * Example:
   * ```kotlin
   * @Test
   * fun networkClass_example() {
   *   val examples = mapOf(
   *     "10.0.0.1" to NetworkClass.A,
   *     "172.16.0.1" to NetworkClass.B,
   *     "192.168.1.1" to NetworkClass.C,
   *     "224.0.0.1" to NetworkClass.D,
   *     "240.0.0.1" to NetworkClass.E
   *   )
   *
   *   examples.forEach { (ip, expectedClass) ->
   *     val addr = IPv4Address.fromString(ip).getOrNull()!!
   *     assertEquals(expectedClass, addr.networkClass())
   *   }
   * }
   * ```
   *
   * @return The [NetworkClass] of this IP address
   */
  fun networkClass(): NetworkClass = when (octet1.toInt()) {
    in 0..127 -> NetworkClass.A
    in 128..191 -> NetworkClass.B
    in 192..223 -> NetworkClass.C
    in 224..239 -> NetworkClass.D
    else -> NetworkClass.E
  }

  companion object {
    /**
     * Creates an [IPv4Address] from a string in dotted decimal notation.
     *
     * Example:
     * ```kotlin
     * @Test
     * fun fromString_example() {
     *   // Successful parsing
     *   val valid = IPv4Address.fromString("192.168.1.1")
     *   assert(valid.isSuccess)
     *
     *   // Invalid formats
     *   val invalidFormat = IPv4Address.fromString("192.168.1")
     *   assert(invalidFormat.isFailure)
     *
     *   val invalidOctet = IPv4Address.fromString("192.168.1.256")
     *   assert(invalidOctet.isFailure)
     * }
     * ```
     *
     * @param ip The IP address string in format "xxx.xxx.xxx.xxx"
     * @return A [Result] containing the [IPv4Address] if parsing was successful,
     *         or a failure with [IllegalArgumentException] if the format is invalid
     */
    fun fromString(ip: String): Result<IPv4Address> =
      ip
        .split('.')
        .map { it.toIntOrNull() ?: -1 }
        .let { octets ->
          when {
            octets.size != 4 ->
              Result.failure(IllegalArgumentException("Invalid IP address format: must have exactly 4 parts"))
            else ->
              fromOctets(octets[0], octets[1], octets[2], octets[3])
          }
        }

    /**
     * Creates an [IPv4Address] from four integer octets.
     *
     * Example:
     * ```kotlin
     * @Test
     * fun fromOctets_example() {
     *   // Valid octets
     *   val valid = IPv4Address.fromOctets(192, 168, 1, 1)
     *   assert(valid.isSuccess)
     *
     *   // Invalid octet values
     *   val invalid = IPv4Address.fromOctets(192, 168, 1, 256)
     *   assert(invalid.isFailure)
     * }
     * ```
     *
     * @param a First octet (0-255)
     * @param b Second octet (0-255)
     * @param c Third octet (0-255)
     * @param d Fourth octet (0-255)
     * @return A [Result] containing the [IPv4Address] if all octets are valid,
     *         or a failure with [IllegalArgumentException] if any octet is out of range
     */
    fun fromOctets(a: Int, b: Int, c: Int, d: Int): Result<IPv4Address> = runCatching {
      if (listOf(a, b, c, d).any { it !in 0..255 }) {
        return Result.failure(IllegalArgumentException("Each octet must be between 0 and 255"))
      }

      val value = (a.toUInt() shl 24) or
        (b.toUInt() shl 16) or
        (c.toUInt() shl 8) or
        d.toUInt()

      IPv4Address(value)
    }

    /**
     * Creates an [IPv4Address] from four unsigned byte octets.
     *
     * Example:
     * ```kotlin
     * @Test
     * fun fromUByteOctets_example() {
     *   val addr = IPv4Address.fromOctets(
     *     192.toUByte(),
     *     168.toUByte(),
     *     1.toUByte(),
     *     1.toUByte()
     *   )
     *   assert(addr.isSuccess)
     *   assertEquals("192.168.1.1", addr.getOrNull()!!.toString())
     * }
     * ```
     *
     * @param a First octet
     * @param b Second octet
     * @param c Third octet
     * @param d Fourth octet
     * @return A [Result] containing the [IPv4Address]
     */
    fun fromOctets(a: UByte, b: UByte, c: UByte, d: UByte): Result<IPv4Address> =
      fromOctets(a.toInt(), b.toInt(), c.toInt(), d.toInt())

    /**
     * Creates an [IPv4Address] from an unsigned 32-bit integer.
     *
     * Example:
     * ```kotlin
     * @Test
     * fun fromUInt_example() {
     *   val value = (192u shl 24) or (168u shl 16) or (1u shl 8) or 1u
     *   val addr = IPv4Address.fromUInt(value)
     *   assert(addr.isSuccess)
     *   assertEquals("192.168.1.1", addr.getOrNull()!!.toString())
     * }
     * ```
     *
     * @param value The unsigned 32-bit integer representation of the IP address
     * @return A [Result] containing the [IPv4Address]
     */
    fun fromUInt(value: UInt): Result<IPv4Address> =
      Result.success(IPv4Address(value))

    /**
     * The localhost IP address (127.0.0.1)
     */
    val LOCALHOST = fromOctets(127, 0, 0, 1).getOrNull()!!

    /**
     * The "any" IP address (0.0.0.0)
     */
    val ANY = fromOctets(0, 0, 0, 0).getOrNull()!!

    /**
     * The broadcast IP address (255.255.255.255)
     */
    val BROADCAST = fromOctets(255, 255, 255, 255).getOrNull()!!
  }
}
