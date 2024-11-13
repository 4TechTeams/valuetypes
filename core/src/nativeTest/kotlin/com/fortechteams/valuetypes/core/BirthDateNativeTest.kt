package com.fortechteams.valuetypes.core

import com.fortechteams.valuetypes.core.exception.InvalidBirthDateException
import io.kotest.matchers.should
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.types.beInstanceOf
import kotlin.test.Test

class BirthDateNativeTest {

  @Test
  fun shouldWrapOriginalExceptionFromKotlinxDatetime() {
    val ex = BirthDate.fromDate(1990, 13, 30).exceptionOrNull()!!

    ex should beInstanceOf<InvalidBirthDateException>()
    ex.message shouldContain "month must be a number between 1 and 12, got 13"

    val ex2 = BirthDate.fromString("30.02.1989").exceptionOrNull()!!

    ex2 should beInstanceOf<InvalidBirthDateException>()
    ex2.message shouldContain "Invalid date 'FEBRUARY 30'"
  }
}
