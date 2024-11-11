package com.fortechteams.valuetypes.personal

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import kotlin.test.Test

class FirstNameTest {
  @Test
  fun `should create valid first name`() {
    // when
    val result = FirstName.of("John")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "John"
  }

  @Test
  fun `should trim whitespace`() {
    // when
    val result = FirstName.of("  John  ")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "John"
  }

  @Test
  fun `should reject blank name`() {
    // when
    val result = FirstName.of("   ")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot be blank"
  }

  @Test
  fun `should reject empty name`() {
    // when
    val result = FirstName.of("")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot be blank"
  }

  @Test
  fun `should reject name exceeding maximum length`() {
    // when
    val result = FirstName.of("a".repeat(FirstName.MAX_LENGTH + 1))

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot exceed ${FirstName.MAX_LENGTH} characters"
  }

  @Test
  fun `should accept name at maximum length`() {
    // when
    val result = FirstName.of("a".repeat(FirstName.MAX_LENGTH))

    // then
    result.shouldBeSuccess()
  }

  @Test
  fun `should reject name containing numbers`() {
    // when
    val result = FirstName.of("John123")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot contain numbers"
  }

  @Test
  fun `should reject name containing control characters`() {
    // when
    val result = FirstName.of("John\u0000")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Can only contain printable characters"
  }

  @Test
  fun `should accept hyphenated names`() {
    // when
    val result = FirstName.of("Mary-Jane")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "Mary-Jane"
  }

  @Test
  fun `should accept names with apostrophes`() {
    // when
    val result = FirstName.of("O'Connor")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "O'Connor"
  }

  @Test
  fun `should accept international characters`() {
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
