package com.fortechteams.valuetypes.personal

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import kotlin.test.Test

class LastNameTest {
  @Test
  fun `should create valid last name`() {
    // when
    val result = LastName.of("Smith")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "Smith"
  }

  @Test
  fun `should trim whitespace`() {
    // when
    val result = LastName.of("  Smith  ")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "Smith"
  }

  @Test
  fun `should reject blank name`() {
    // when
    val result = LastName.of("   ")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot be blank"
  }

  @Test
  fun `should reject empty name`() {
    // when
    val result = LastName.of("")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot be blank"
  }

  @Test
  fun `should reject name exceeding maximum length`() {
    // when
    val result = LastName.of("a".repeat(LastName.MAX_LENGTH + 1))

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot exceed ${LastName.MAX_LENGTH} characters"
  }

  @Test
  fun `should accept name at maximum length`() {
    // when
    val result = LastName.of("a".repeat(LastName.MAX_LENGTH))

    // then
    result.shouldBeSuccess()
  }

  @Test
  fun `should reject name containing numbers`() {
    // when
    val result = LastName.of("Smith2")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot contain numbers"
  }

  @Test
  fun `should reject name containing control characters`() {
    // when
    val result = LastName.of("Smith\u0000")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Can only contain printable characters"
  }

  @Test
  fun `should accept hyphenated names`() {
    // when
    val result = LastName.of("Smith-Jones")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "Smith-Jones"
  }

  @Test
  fun `should accept names with apostrophes`() {
    // when
    val result = LastName.of("O'Brien")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "O'Brien"
  }

  @Test
  fun `should accept compound names with spaces`() {
    // given
    val compoundNames = listOf(
      "van der Berg",
      "von Neumann",
      "de la Rosa",
      "van den Heuvel",
      "Le Blanc"
    )

    // then
    compoundNames.forEach { name ->
      shouldNotThrowAny {
        LastName.of(name).getOrThrow()
      }
    }
  }

  @Test
  fun `should accept international characters`() {
    // given
    val internationalNames = listOf(
      // German
      "Müller",
      // Spanish
      "García",
      // French
      "Béringer",
      // Swedish
      "Åkesson",
      // Danish
      "Østergård",
      // Czech
      "Škvorecký",
      // Danish
      "Nørregaard",
      // Danish
      "Søndergård",
      // Polish
      "Łukasiewicz",
      // Czech
      "Dvořák"
    )

    // then
    internationalNames.forEach { name ->
      shouldNotThrowAny {
        LastName.of(name).getOrThrow()
      }
    }
  }
}
