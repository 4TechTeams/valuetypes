package com.fortechteams.valuetypes.network

import com.fortechteams.valuetypes.network.exception.InvalidSubnetMaskException
import kotlin.jvm.JvmInline

@JvmInline
value class SubnetMask private constructor(private val value: UInt) {

  fun octet1(): UByte = ((value shr 24) and 0xFFu).toUByte()
  fun octet2(): UByte = ((value shr 16) and 0xFFu).toUByte()
  fun octet3(): UByte = ((value shr 8) and 0xFFu).toUByte()
  fun octet4(): UByte = (value and 0xFFu).toUByte()

  override fun toString(): String =
    "${octet1()}.${octet2()}.${octet3()}.${octet4()}"

  fun toUInt(): UInt = value

  /**
   * Returns the number of continuous 1s from the left (CIDR notation)
   */
  fun prefixLength(): Int {
    var bits = 0
    var mask = value
    while (mask != 0u && (mask and 0x8000_0000u) != 0u) {
      bits++
      mask = mask shl 1
    }
    return bits
  }

  companion object {
    fun fromString(mask: String): Result<SubnetMask> =
      mask
        .split('.')
        .map { it.toIntOrNull() ?: -1 }
        .let { octets ->
          when {
            octets.size != 4 ->
              Result.failure(
                InvalidSubnetMaskException(
                  mask,
                  "Invalid format. Must have exactly 4 parts"
                )
              )

            else ->
              fromOctets(octets[0], octets[1], octets[2], octets[3])
          }
        }

    fun fromOctets(a: Int, b: Int, c: Int, d: Int): Result<SubnetMask> =
      if (listOf(a, b, c, d).any { it !in 0..255 }) {
        Result.failure(
          InvalidSubnetMaskException(
            listOf(a, b, c, d).map { it.toString() }.joinToString("."),
            "Each octet must be between 0 and 255"
          )
        )
      } else {
        val value = (a.toUInt() shl 24) or
          (b.toUInt() shl 16) or
          (c.toUInt() shl 8) or
          d.toUInt()

        if (!isValidSubnetMask(value)) {
          Result.failure(
            InvalidSubnetMaskException(
              listOf(a, b, c, d).map { it.toString() }.joinToString("."),
              "Found 1s after 0s. A valid mask must have all 1s before all 0s"
            )
          )
        } else {
          Result.success(SubnetMask(value))
        }
      }

    private fun isValidSubnetMask(value: UInt): Boolean =
      value
        .toBinaryString()
        .fold(Pair(true, false)) { (isValid, hasFoundZero), bit ->
          // Using Pair(isValid, hasFoundZero) for folding, so we don't need an intermediate sum type
          when {
            hasFoundZero && bit == '1' -> Pair(false, true)
            bit == '0' -> Pair(isValid, true)
            else -> Pair(isValid, hasFoundZero)
          }
        }
        .first // returns only isValid value

    // UByte version for convenience
    fun fromOctets(a: UByte, b: UByte, c: UByte, d: UByte): Result<SubnetMask> =
      fromOctets(a.toInt(), b.toInt(), c.toInt(), d.toInt())

    fun fromUInt(value: UInt): Result<SubnetMask> = fromOctets(
      ((value shr 24) and 0xFFu).toInt(),
      ((value shr 16) and 0xFFu).toInt(),
      ((value shr 8) and 0xFFu).toInt(),
      (value and 0xFFu).toInt()
    )

    /**
     * Creates a subnet mask from CIDR prefix length (e.g., /24 for 255.255.255.0)
     */
    fun fromPrefixLength(length: Int): Result<SubnetMask> {
      if (length !in 0..32) {
        return Result.failure(IllegalArgumentException("Prefix length must be between 0 and 32"))
      }

      val value = if (length == 0) {
        0u
      } else {
        0xFFFF_FFFFu shl (32 - length)
      }

      return Result.success(SubnetMask(value))
    }

    // Common subnet masks
    val CLASS_A = fromOctets(255, 0, 0, 0).getOrNull()!!
    val CLASS_B = fromOctets(255, 255, 0, 0).getOrNull()!!
    val CLASS_C = fromOctets(255, 255, 255, 0).getOrNull()!!
  }
}

/**
 * Transforms an UInt to a binary string
 *
 * ```kotlin
 * 4278190080u.toBinaryString() shouldBe "11111111000000000000000000000000"
 * ```
 */
fun UInt.toBinaryString(): String =
  this
    .toString(2)
    .padStart(32, '0')
