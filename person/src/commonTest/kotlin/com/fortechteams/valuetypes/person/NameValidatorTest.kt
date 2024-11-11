package com.fortechteams.valuetypes.person

import com.fortechteams.valuetypes.person.NameValidator.isPrintable
import com.fortechteams.valuetypes.person.NameValidator.validateName
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import kotlin.test.Test

class NameValidatorTest {

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

  @Test
  fun `validateName should apply all validation rules`() {
    // given
    val maxLength = 10

    // when & then
    validateName("Test-Name", maxLength).shouldBeSuccess()
    validateName("", maxLength).shouldBeFailure()
    validateName("   ", maxLength).shouldBeFailure()
    validateName("a".repeat(maxLength + 1), maxLength)
      .exceptionOrNull()?.message shouldContain "Cannot exceed 10 characters"
    validateName("Test123", maxLength)
      .exceptionOrNull()?.message shouldContain "Cannot contain numbers"
    validateName("Test\u0000", maxLength)
      .exceptionOrNull()?.message shouldContain "Can only contain printable characters"
  }
}
