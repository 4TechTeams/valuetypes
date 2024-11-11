package com.fortechteams.valuetypes.network

import com.fortechteams.valuetypes.network.exception.InvalidSubnetMaskException
import kotlin.test.*

class SubnetMaskTest {
  @Test
  fun `test creation from valid string representations`() {
    val validMasks = listOf(
      // /32
      "255.255.255.255",
      // /24
      "255.255.255.0",
      // /16
      "255.255.0.0",
      // /8
      "255.0.0.0",
      // /0
      "0.0.0.0"
    )

    validMasks.forEach { mask ->
      val result = SubnetMask.fromString(mask)
      assertTrue(result.isSuccess, "Expected $mask to be valid")
      assertEquals(mask, result.getOrNull()?.toString())
    }
  }

  @Test
  fun `test invalid string formats`() {
    val invalidMasks = listOf(
      // Invalid octet value
      "256.255.255.255",
      // Too few octets
      "255.255.255",
      // Too many octets
      "255.255.255.255.0",
      // Invalid character
      "255.255.a.255",
      // Empty octet
      "255.255..255",
      // Leading dot
      ".255.255.255",
      // Trailing dot
      "255.255.255.",
    )

    invalidMasks.forEach { mask ->
      val result = SubnetMask.fromString(mask)
      assertTrue(result.isFailure, "Expected $mask to be invalid")
      assertTrue(result.exceptionOrNull() is InvalidSubnetMaskException)
    }
  }

  @Test
  fun `test invalid subnet patterns`() {
    // Test every possible gap pattern
    val testCases = listOf(
      "255.0.255.0",
      "255.255.0.255",
      "255.0.255.255",
      "240.255.0.0"
    )

    testCases.forEach { mask ->
      val result = SubnetMask.fromString(mask)
      assertTrue(
        result.isFailure,
        "Expected $mask to be invalid"
      )
      val exception = result.exceptionOrNull()
      assertNotNull(exception)
      assertTrue(
        exception is InvalidSubnetMaskException,
        "Expected InvalidSubnetMaskException for $mask, got ${exception::class.simpleName}"
      )
      assertTrue(
        exception.message?.contains("Found 1s after 0s") == true,
        "Expected error message about 1s after 0s for $mask"
      )
    }
  }

  @Test
  fun `test prefix length calculations`() {
    val maskToPrefixLength = mapOf(
      "255.255.255.255" to 32,
      "255.255.255.0" to 24,
      "255.255.0.0" to 16,
      "255.0.0.0" to 8,
      "0.0.0.0" to 0
    )

    maskToPrefixLength.forEach { (mask, expectedLength) ->
      val subnetMask = SubnetMask.fromString(mask).getOrNull()
        ?: fail("Failed to create mask from $mask")
      assertEquals(expectedLength, subnetMask.prefixLength())
    }
  }

  @Test
  fun `test creation from prefix length`() {
    val prefixToMask = mapOf(
      32 to "255.255.255.255",
      24 to "255.255.255.0",
      16 to "255.255.0.0",
      8 to "255.0.0.0",
      0 to "0.0.0.0"
    )

    prefixToMask.forEach { (prefix, expectedMask) ->
      val result = SubnetMask.fromPrefixLength(prefix)
      assertTrue(result.isSuccess)
      assertEquals(expectedMask, result.getOrNull()?.toString())
    }
  }

  @Test
  fun `test invalid prefix lengths`() {
    val invalidLengths = listOf(-1, 33, 64, -42)

    invalidLengths.forEach { length ->
      val result = SubnetMask.fromPrefixLength(length)
      assertTrue(result.isFailure)
      assertTrue(result.exceptionOrNull() is IllegalArgumentException)
    }
  }

  @Test
  fun `test octet extraction`() {
    val testCases = listOf(
      SubnetMask.fromString("255.255.255.0").getOrNull() to listOf(255u, 255u, 255u, 0u),
      SubnetMask.fromString("255.0.0.0").getOrNull() to listOf(255u, 0u, 0u, 0u),
      SubnetMask.fromString("0.0.0.0").getOrNull() to listOf(0u, 0u, 0u, 0u),
      SubnetMask.fromString("255.255.255.255").getOrNull() to listOf(255u, 255u, 255u, 255u),
    )

    testCases.forEach { (mask, expectedOctets) ->
      assertNotNull(mask, "Failed to create test mask")

      assertEquals(
        expectedOctets[0].toUByte(),
        mask.octet1(),
        "First octet mismatch for $mask"
      )
      assertEquals(
        expectedOctets[1].toUByte(),
        mask.octet2(),
        "Second octet mismatch for $mask"
      )
      assertEquals(
        expectedOctets[2].toUByte(),
        mask.octet3(),
        "Third octet mismatch for $mask"
      )
      assertEquals(
        expectedOctets[3].toUByte(),
        mask.octet4(),
        "Fourth octet mismatch for $mask"
      )
    }
  }

  @Test
  fun `test predefined class masks`() {
    assertEquals("255.0.0.0", SubnetMask.CLASS_A.toString())
    assertEquals("255.255.0.0", SubnetMask.CLASS_B.toString())
    assertEquals("255.255.255.0", SubnetMask.CLASS_C.toString())
  }

  @Test
  fun `test UInt conversion`() {
    val originalMask = "255.255.255.0"
    val mask = SubnetMask.fromString(originalMask).getOrNull()
      ?: fail("Failed to create test mask")

    val uintValue = mask.toUInt()
    val reconstructed = SubnetMask.fromUInt(uintValue)

    assertTrue(reconstructed.isSuccess)
    assertEquals(originalMask, reconstructed.getOrNull()?.toString())
  }

  @Test
  fun `test creation from octets`() {
    // Test with Int parameters
    val resultInt = SubnetMask.fromOctets(255, 255, 255, 0)
    assertTrue(resultInt.isSuccess)
    assertEquals("255.255.255.0", resultInt.getOrNull()?.toString())

    // Test with UByte parameters
    val resultUByte = SubnetMask.fromOctets(
      255.toUByte(),
      255.toUByte(),
      255.toUByte(),
      0.toUByte()
    )
    assertTrue(resultUByte.isSuccess)
    assertEquals("255.255.255.0", resultUByte.getOrNull()?.toString())
  }
}
