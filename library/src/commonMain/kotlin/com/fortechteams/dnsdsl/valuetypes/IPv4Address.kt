package com.fortechteams.dnsdsl.valuetypes

import kotlin.jvm.JvmInline

@JvmInline
value class IPv4Address private constructor(private val value: UInt) {
  fun octet1(): UByte = ((value shr 24) and 0xFFu).toUByte()
  fun octet2(): UByte = ((value shr 16) and 0xFFu).toUByte()
  fun octet3(): UByte = ((value shr 8) and 0xFFu).toUByte()
  fun octet4(): UByte = (value and 0xFFu).toUByte()

  override fun toString(): String =
    "${octet1()}.${octet2()}.${octet3()}.${octet4()}"

  fun toUInt(): UInt = value

  companion object {
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

    // UByte version for convenience
    fun fromOctets(a: UByte, b: UByte, c: UByte, d: UByte): Result<IPv4Address> =
      fromOctets(a.toInt(), b.toInt(), c.toInt(), d.toInt())

    fun fromUInt(value: UInt): Result<IPv4Address> =
      Result.success(IPv4Address(value))

    val LOCALHOST = fromOctets(127, 0, 0, 1).getOrNull()!!
    val ANY = fromOctets(0, 0, 0, 0).getOrNull()!!
    val BROADCAST = fromOctets(255, 255, 255, 255).getOrNull()!!
  }
}