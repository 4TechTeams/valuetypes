package com.fortechteams.valuetypes.core

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.matchers.result.shouldBeFailure
import io.kotest.matchers.result.shouldBeSuccess
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import kotlin.test.Test

class LastNameTest {

  @Test
  fun shouldCreateValidLastName() {
    // when
    val result = LastName.of("Smith")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "Smith"
  }

  @Test
  fun shouldTrimWhitespace() {
    // when
    val result = LastName.of("  Smith  ")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "Smith"
  }

  @Test
  fun shouldRejectBlankName() {
    // when
    val result = LastName.of("   ")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot be blank"
  }

  @Test
  fun shouldRejectEmptyName() {
    // when
    val result = LastName.of("")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot be blank"
  }

  @Test
  fun shouldRejectNameExceedingMaximumLength() {
    // when
    val result = LastName.of("a".repeat(Name.MAX_LENGTH + 1))

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot exceed ${Name.MAX_LENGTH} characters"
  }

  @Test
  fun shouldAcceptNameAtMaximumLength() {
    // when
    val result = LastName.of("a".repeat(Name.MAX_LENGTH))

    // then
    result.shouldBeSuccess()
  }

  @Test
  fun shouldRejectNameContainingNumbers() {
    // when
    val result = LastName.of("Smith2")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Cannot contain numbers"
  }

  @Test
  fun shouldRejectNameContainingControlCharacters() {
    // when
    val result = LastName.of("Smith\u0000")

    // then
    result.shouldBeFailure()
    result.exceptionOrNull()?.message shouldContain "Can only contain printable characters"
  }

  @Test
  fun shouldAcceptHyphenatedNames() {
    // when
    val result = LastName.of("Smith-Jones")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "Smith-Jones"
  }

  @Test
  fun shouldAcceptNamesWithApostrophes() {
    // when
    val result = LastName.of("O'Brien")

    // then
    result.shouldBeSuccess()
    result.getOrThrow().toString() shouldBe "O'Brien"
  }

  @Test
  fun shouldAcceptCompoundNamesWithSpaces() {
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
  fun shouldAcceptInternationalCharacters() {
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
