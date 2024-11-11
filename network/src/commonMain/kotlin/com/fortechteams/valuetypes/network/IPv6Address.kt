package com.fortechteams.valuetypes.network

import kotlin.jvm.JvmInline

/**
 * Represents an IPv6 address as a value class wrapping two unsigned 64-bit integers.
 *
 * This implementation provides a memory-efficient way to handle IPv6 addresses while
 * ensuring type safety and offering convenient conversion methods. The address is stored
 * as two ULong values representing the higher and lower 64 bits of the address.
 *
 * Example usage:
 * ```kotlin
 * @Test
 * fun ipv6Address_examples() {
 *   // Create from string
 *   val addr = IPv6Address.fromString("2001:db8::1").getOrNull()
 *   println(addr.toString()) // Prints: 2001:db8::1
 *
 *   // Access 16-bit blocks
 *   println(addr.block1) // Prints: 0x2001
 *
 *   // Check if private address
 *   println(addr.isPrivate()) // Prints: false
 *
 *   // Use predefined constants
 *   println(IPv6Address.LOCALHOST) // Prints: ::1
 * }
 * ```
 *
 * The class provides several factory methods for creating IPv6 addresses:
 * - [fromString] for parsing string representations
 * - [fromBlocks] for creating from eight 16-bit integers
 * - [fromULongs] for creating from two 64-bit unsigned integers
 *
 * Common IP addresses are available as constants:
 * - [LOCALHOST] (::1)
 * - [UNSPECIFIED] (::)
 *
 * @property highBits The upper 64 bits of the IPv6 address
 * @property lowBits The lower 64 bits of the IPv6 address
 */
@JvmInline
value class IPv6Address private constructor(
  private val bits: Pair<ULong, ULong>
) {
  private val highBits: ULong get() = bits.first
  private val lowBits: ULong get() = bits.second

  /**
   * First 16-bit block of the IPv6 address (bits 127-112).
   *
   * Example:
   * ```kotlin
   * @Test
   * fun block1_example() {
   *   val addr = IPv6Address.fromString("2001:db8::1").getOrNull()!!
   *   assertEquals(0x2001u, addr.block1)
   * }
   * ```
   */
  val block1: UShort get() = ((highBits shr 48) and 0xFFFFu).toUShort()

  /**
   * Second 16-bit block of the IPv6 address (bits 111-96).
   */
  val block2: UShort get() = ((highBits shr 32) and 0xFFFFu).toUShort()

  /**
   * Third 16-bit block of the IPv6 address (bits 95-80).
   */
  val block3: UShort get() = ((highBits shr 16) and 0xFFFFu).toUShort()

  /**
   * Fourth 16-bit block of the IPv6 address (bits 79-64).
   */
  val block4: UShort get() = (highBits and 0xFFFFu).toUShort()

  /**
   * Fifth 16-bit block of the IPv6 address (bits 63-48).
   */
  val block5: UShort get() = ((lowBits shr 48) and 0xFFFFu).toUShort()

  /**
   * Sixth 16-bit block of the IPv6 address (bits 47-32).
   */
  val block6: UShort get() = ((lowBits shr 32) and 0xFFFFu).toUShort()

  /**
   * Seventh 16-bit block of the IPv6 address (bits 31-16).
   */
  val block7: UShort get() = ((lowBits shr 16) and 0xFFFFu).toUShort()

  /**
   * Eighth 16-bit block of the IPv6 address (bits 15-0).
   */
  val block8: UShort get() = (lowBits and 0xFFFFu).toUShort()

  /**
   * Returns the string representation of the IPv6 address using standard notation,
   * including compression of zero blocks using ::
   *
   * Example:
   * ```kotlin
   * @Test
   * fun toString_examples() {
   *   val examples = mapOf(
   *     "2001:db8::1" to "2001:db8::1",
   *     "2001:db8:0:0:0:0:0:1" to "2001:db8::1",
   *     "::1" to "::1",
   *     "::" to "::"
   *   )
   *
   *   examples.forEach { (input, expected) ->
   *     val addr = IPv6Address.fromString(input).getOrNull()!!
   *     assertEquals(expected, addr.toString())
   *   }
   * }
   * ```
   *
   * @return The compressed IPv6 address string
   */
  override fun toString(): String {
    // Convert blocks to hex strings
    val blocks = listOf(block1, block2, block3, block4, block5, block6, block7, block8)
      .map { it.toString(16) }

    // Find longest sequence of zeros for compression
    var longestZeroStart = -1
    var longestZeroLength = 0
    var currentZeroStart = -1
    var currentZeroLength = 0

    blocks.forEachIndexed { index, block ->
      if (block == "0") {
        if (currentZeroStart == -1) {
          currentZeroStart = index
        }
        currentZeroLength++
        if (currentZeroLength > longestZeroLength) {
          longestZeroStart = currentZeroStart
          longestZeroLength = currentZeroLength
        }
      } else {
        currentZeroStart = -1
        currentZeroLength = 0
      }
    }

    // Build compressed string
    return buildString {
      if (longestZeroLength > 1) {
        // Add blocks before compression
        for (i in 0 until longestZeroStart) {
          if (i > 0) append(":")
          append(blocks[i])
        }
        append("::")
        // Add blocks after compression
        for (i in (longestZeroStart + longestZeroLength)..7) {
          append(blocks[i])
          if (i < 7) append(":")
        }
      } else {
        // No compression needed
        blocks.forEachIndexed { index, block ->
          if (index > 0) append(":")
          append(block)
        }
      }
    }
  }

  /**
   * Returns the high and low 64-bit values of this IPv6 address.
   *
   * Example:
   * ```kotlin
   * @Test
   * fun toULongs_example() {
   *   val addr = IPv6Address.fromString("2001:db8::1").getOrNull()!!
   *   val (high, low) = addr.toULongs()
   *   assertEquals(0x2001_0db8_0000_0000uL, high)
   *   assertEquals(0x0000_0000_0000_0001uL, low)
   * }
   * ```
   *
   * @return A Pair of ULong values representing the high and low 64 bits
   */
  fun toULongs(): Pair<ULong, ULong> = bits

  /**
   * Determines whether this IP address is in a private network range.
   *
   * Private IPv6 ranges are:
   * - fc00::/7 (Unique Local Addresses)
   * - fe80::/10 (Link-Local Addresses)
   *
   * Example:
   * ```kotlin
   * @Test
   * fun isPrivate_example() {
   *   val examples = mapOf(
   *     "fc00::" to true,
   *     "fd00::" to true,
   *     "fe80::" to true,
   *     "2001:db8::" to false,
   *     "::1" to true
   *   )
   *
   *   examples.forEach { (ip, expectedPrivate) ->
   *     val addr = IPv6Address.fromString(ip).getOrNull()!!
   *     assertEquals(expectedPrivate, addr.isPrivate())
   *   }
   * }
   * ```
   *
   * @return true if the IP address is in a private range, false otherwise
   */
  fun isPrivate(): Boolean {
    // Check for loopback (::1)
    if (highBits == 0uL && lowBits == 1uL) return true

    // Get first 7 bits for ULA check (fc00::/7)
    val firstByte = (highBits shr 56).toUByte()
    if ((firstByte and 0xfeu.toUByte()) == 0xfcu.toUByte()) return true

    // Get first 10 bits for link-local check (fe80::/10)
    if ((firstByte == 0xfeu.toUByte()) &&
      ((highBits shr 54) and 0x03uL) == 0x02uL
    ) {
      return true
    }

    return false
  }

  companion object {
    /**
     * Creates an [IPv6Address] from a string in standard IPv6 notation.
     *
     * Example:
     * ```kotlin
     * @Test
     * fun fromString_example() {
     *   // Successful parsing
     *   val valid = IPv6Address.fromString("2001:db8::1")
     *   assert(valid.isSuccess)
     *
     *   // Invalid formats
     *   val invalidFormat = IPv6Address.fromString("2001:db8")
     *   assert(invalidFormat.isFailure)
     *
     *   val invalidBlock = IPv6Address.fromString("2001:db8::1::2")
     *   assert(invalidBlock.isFailure)
     * }
     * ```
     *
     * @param ip The IPv6 address string
     * @return A [Result] containing the [IPv6Address] if parsing was successful,
     *         or a failure with [IllegalArgumentException] if the format is invalid
     */
    fun fromString(ip: String): Result<IPv6Address> {
      // Split on :: to handle compressed notation
      val parts = ip.split("::", limit = 2)
      if (parts.size > 2) {
        return Result.failure(IllegalArgumentException("Invalid IPv6 address: multiple :: found"))
      }

      // Split each part into blocks
      val leftBlocks = if (parts[0].isEmpty()) {
        emptyList()
      } else {
        parts[0].split(":")
      }
      val rightBlocks = if (parts.size == 1 || parts[1].isEmpty()) {
        emptyList()
      } else {
        parts[1].split(":")
      }

      // Validate total number of blocks
      if (leftBlocks.size + rightBlocks.size > 8) {
        return Result.failure(IllegalArgumentException("Invalid IPv6 address: too many blocks"))
      }

      // Calculate number of zero blocks needed
      val zeroBlocks = 8 - (leftBlocks.size + rightBlocks.size)
      if (parts.size == 2 && zeroBlocks < 0) {
        return Result.failure(IllegalArgumentException("Invalid IPv6 address: incorrect block count"))
      }

      // Build complete list of blocks
      val blocks = ArrayList<UShort>(8)

      // Add left blocks
      for (block in leftBlocks) {
        blocks.add(block.toUShort(16))
      }

      // Add zero blocks
      repeat(zeroBlocks) {
        blocks.add(0u)
      }

      // Add right blocks
      for (block in rightBlocks) {
        blocks.add(block.toUShort(16))
      }

      return fromBlocks(
        blocks[0],
        blocks[1],
        blocks[2],
        blocks[3],
        blocks[4],
        blocks[5],
        blocks[6],
        blocks[7]
      )
    }

    /**
     * Creates an [IPv6Address] from eight 16-bit unsigned integers.
     *
     * Example:
     * ```kotlin
     * @Test
     * fun fromBlocks_example() {
     *   val addr = IPv6Address.fromBlocks(
     *     0x2001u, 0x0db8u, 0u, 0u, 0u, 0u, 0u, 1u
     *   )
     *   assertEquals("2001:db8::1", addr.getOrNull()!!.toString())
     * }
     * ```
     *
     * @param block1 First 16-bit block
     * @param block2 Second 16-bit block
     * @param block3 Third 16-bit block
     * @param block4 Fourth 16-bit block
     * @param block5 Fifth 16-bit block
     * @param block6 Sixth 16-bit block
     * @param block7 Seventh 16-bit block
     * @param block8 Eighth 16-bit block
     * @return A [Result] containing the [IPv6Address]
     */
    fun fromBlocks(
      block1: UShort,
      block2: UShort,
      block3: UShort,
      block4: UShort,
      block5: UShort,
      block6: UShort,
      block7: UShort,
      block8: UShort
    ): Result<IPv6Address> = runCatching {
      val highBits = (block1.toULong() shl 48) or
        (block2.toULong() shl 32) or
        (block3.toULong() shl 16) or
        block4.toULong()

      val lowBits = (block5.toULong() shl 48) or
        (block6.toULong() shl 32) or
        (block7.toULong() shl 16) or
        block8.toULong()

      IPv6Address(Pair(highBits, lowBits))
    }

    /**
     * Creates an [IPv6Address] from two unsigned 64-bit integers.
     *
     * Example:
     * ```kotlin
     * @Test
     * fun fromULongs_example() {
     *   val addr = IPv6Address.fromULongs(
     *     0x2001_0db8_0000_0000uL,
     *     0x0000_0000_0000_0001uL
     *   )
     *   assertEquals("2001:db8::1", addr.getOrNull()!!.toString())
     * }
     * ```
     *
     * @param highBits The upper 64 bits
     * @param lowBits The lower 64 bits
     * @return A [Result] containing the [IPv6Address]
     */
    fun fromULongs(highBits: ULong, lowBits: ULong): Result<IPv6Address> =
      Result.success(IPv6Address(Pair(highBits, lowBits)))

    /**
     * The localhost IPv6 address (::1)
     */
    val LOCALHOST = fromULongs(0uL, 1uL).getOrNull()!!

    /**
     * The unspecified IPv6 address (::)
     */
    val UNSPECIFIED = fromULongs(0uL, 0uL).getOrNull()!!
  }
}
