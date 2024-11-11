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
 * ## Example
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
 * ## See Also
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
   * ```kotlin
   * import com.fortechteams.valuetypes.network.IPv4Address
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val addr = IPv4Address.fromString("192.168.1.1").getOrThrow()
   *   addr.octet1 shouldBe 192u.toUByte()
   * }
   * ```
   * <!--- KNIT example-IPv4Address-02.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  val octet1: UByte get() = ((value shr 24) and 0xFFu).toUByte()

  /**
   * The second octet of the IP address.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.network.IPv4Address
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val addr = IPv4Address.fromString("192.168.1.1").getOrThrow()
   *   addr.octet2 shouldBe 168u.toUByte()
   * }
   * ```
   * <!--- KNIT example-IPv4Address-03.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  val octet2: UByte get() = ((value shr 16) and 0xFFu).toUByte()

  /**
   * The third octet of the IP address.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.network.IPv4Address
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val addr = IPv4Address.fromString("192.168.1.1").getOrThrow()
   *   addr.octet3 shouldBe 1u.toUByte()
   * }
   * ```
   * <!--- KNIT example-IPv4Address-04.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  val octet3: UByte get() = ((value shr 8) and 0xFFu).toUByte()

  /**
   * The fourth octet of the IP address (least significant byte).
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.network.IPv4Address
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val addr = IPv4Address.fromString("192.168.1.1").getOrThrow()
   *   addr.octet4 shouldBe 1u.toUByte()
   * }
   * ```
   * <!--- KNIT example-IPv4Address-05.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  val octet4: UByte get() = (value and 0xFFu).toUByte()

  /**
   * Returns the string representation of the IP address in dotted decimal notation.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.network.IPv4Address
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val addr = IPv4Address.fromString("192.168.1.1").getOrThrow()
   *   addr.toString() shouldBe "192.168.1.1"
   * }
   * ```
   * <!--- KNIT example-IPv4Address-06.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  override fun toString(): String = "$octet1.$octet2.$octet3.$octet4"

  /**
   * Returns the unsigned 32-bit integer representation of the IP address.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.network.IPv4Address
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val addr = IPv4Address.fromString("192.168.1.1").getOrThrow()
   *   val expected = (192u shl 24) or (168u shl 16) or (1u shl 8) or 1u
   *   addr.toUInt() shouldBe expected
   * }
   * ```
   * <!--- KNIT example-IPv4Address-07.kt -->
   * <!--- TEST lines.isEmpty() -->
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
   * ```kotlin
   * import com.fortechteams.valuetypes.network.IPv4Address
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
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
   *     val addr = IPv4Address.fromString(ip).getOrThrow()
   *     addr.isPrivate() shouldBe expectedPrivate
   *   }
   * }
   * ```
   * <!--- KNIT example-IPv4Address-08.kt -->
   * <!--- TEST lines.isEmpty() -->
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
   * ```kotlin
   * import com.fortechteams.valuetypes.network.IPv4Address
   * import com.fortechteams.valuetypes.network.NetworkClass
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val examples = mapOf(
   *     "10.0.0.1" to NetworkClass.A,
   *     "172.16.0.1" to NetworkClass.B,
   *     "192.168.1.1" to NetworkClass.C,
   *     "224.0.0.1" to NetworkClass.D,
   *     "240.0.0.1" to NetworkClass.E
   *   )
   *
   *   examples.forEach { (ip, expectedClass) ->
   *     val addr = IPv4Address.fromString(ip).getOrThrow()
   *     addr.networkClass() shouldBe expectedClass
   *   }
   * }
   * ```
   * <!--- KNIT example-IPv4Address-09.kt -->
   * <!--- TEST lines.isEmpty() -->
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
     * ```kotlin
     * import com.fortechteams.valuetypes.network.IPv4Address
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   // Successful parsing
     *   IPv4Address.fromString("192.168.1.1").isSuccess shouldBe true
     *
     *   // Invalid formats
     *   IPv4Address.fromString("192.168.1").isFailure shouldBe true
     *   IPv4Address.fromString("192.168.1.256").isFailure shouldBe true
     * }
     * ```
     * <!--- KNIT example-IPv4Address-10.kt -->
     * <!--- TEST lines.isEmpty() -->
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
     * ```kotlin
     * import com.fortechteams.valuetypes.network.IPv4Address
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   // Valid octets
     *   IPv4Address.fromOctets(192, 168, 1, 1).isSuccess shouldBe true
     *
     *   // Invalid octet values
     *   IPv4Address.fromOctets(192, 168, 1, 256).isFailure shouldBe true
     * }
     * ```
     * <!--- KNIT example-IPv4Address-11.kt -->
     * <!--- TEST lines.isEmpty() -->
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
     * ```kotlin
     * import com.fortechteams.valuetypes.network.IPv4Address
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   val addr = IPv4Address.fromOctets(
     *     192.toUByte(),
     *     168.toUByte(),
     *     1.toUByte(),
     *     1.toUByte()
     *   )
     *   addr.isSuccess shouldBe true
     *   addr.getOrThrow().toString() shouldBe "192.168.1.1"
     * }
     * ```
     * <!--- KNIT example-IPv4Address-12.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromOctets(a: UByte, b: UByte, c: UByte, d: UByte): Result<IPv4Address> =
      fromOctets(a.toInt(), b.toInt(), c.toInt(), d.toInt())

    /**
     * Creates an [IPv4Address] from an unsigned 32-bit integer.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.network.IPv4Address
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   val value = (192u shl 24) or (168u shl 16) or (1u shl 8) or 1u
     *   val addr = IPv4Address.fromUInt(value)
     *   addr.isSuccess shouldBe true
     *   addr.getOrThrow().toString() shouldBe "192.168.1.1"
     * }
     * ```
     * <!--- KNIT example-IPv4Address-13.kt -->
     * <!--- TEST lines.isEmpty() -->
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
