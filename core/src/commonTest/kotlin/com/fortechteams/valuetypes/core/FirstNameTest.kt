package com.fortechteams.valuetypes.core

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import kotlin.test.Test

class FirstNameTest {
  @Test
  fun shouldCreateValidFirstName() {
    // when
    val result = FirstName.of("John")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "John"
  }

  @Test
  fun shouldTrimWhitespace() {
    // when
    val result = FirstName.of("  John  ")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "John"
  }

  @Test
  fun shouldRejectBlankName() {
    // when
    val result = FirstName.of("   ")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot be blank"
  }

  @Test
  fun shouldRejectEmptyName() {
    // when
    val result = FirstName.of("")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot be blank"
  }

  @Test
  fun shouldRejectNameExceedingMaximumLength() {
    // when
    val result = FirstName.of("a".repeat(Name.MAX_LENGTH + 1))

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot exceed ${Name.MAX_LENGTH} characters"
  }

  @Test
  fun shouldAcceptNameAtMaximumLength() {
    // when
    val result = FirstName.of("a".repeat(Name.MAX_LENGTH))

    // then
    result.shouldBeSuccess()
  }

  @Test
  fun shouldRejectNameContainingNumbers() {
    // when
    val result = FirstName.of("John123")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot contain numbers"
  }

  @Test
  fun shouldRejectNameContainingControlCharacters() {
    // when
    val result = FirstName.of("John\u0000")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Can only contain printable characters"
  }

  @Test
  fun shouldAcceptHyphenatedNames() {
    // when
    val result = FirstName.of("Mary-Jane")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "Mary-Jane"
  }

  @Test
  fun shouldAcceptNamesWithApostrophes() {
    // when
    val result = FirstName.of("O'Connor")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "O'Connor"
  }

  @Test
  fun shouldAcceptInternationalCharacters() {
    // given
    val internationalNames = listOf(
      // Spanish
      "José",
      // French
      "François",
      // Swedish
      "Märta",
      // Danish
      "Søren",
      // Norwegian
      "Måns",
      // German
      "Björn",
      // English
      "Zoë",
      // French
      "Renée",
      // Spanish
      "María",
      // French
      "André"
    )

    // then
    internationalNames.forEach { name ->
      shouldNotThrowAny {
        FirstName.of(name).getOrThrow()
      }
    }
  }
}
