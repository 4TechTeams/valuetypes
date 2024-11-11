package com.fortechteams.valuetypes.person

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class GenericExtensionsTest {

  @Test
  fun `should identify printable characters correctly`() {
    // given
    val printableChars = listOf(
      // lowercase letter
      'a',
      // uppercase letter
      'Z',
      // accented character
      'é',
      // hyphen
      '-',
      // apostrophe
      '\'',
      // space
      ' ',
      // German eszett
      'ß',
      // Spanish n with tilde
      'ñ',
      // Danish o with stroke
      'ø',
      // Swedish a with ring
      'å'
    )

    val nonPrintableChars = listOf(
      // null
      '\u0000',
      // bell
      '\u0007',
      // escape
      '\u001B',
      // application program command
      '\u009F',
      // invalid unicode
      '\uFFFE'
    )

    // then
    printableChars.forEach { char ->
      char.isPrintable() shouldBe true
    }

    nonPrintableChars.forEach { char ->
      char.isPrintable() shouldBe false
    }
  }
}
