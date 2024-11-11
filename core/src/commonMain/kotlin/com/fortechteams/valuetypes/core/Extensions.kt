package com.fortechteams.valuetypes.core

/**
 * Indicated if a character is printable
 */
fun Char.isPrintable(): Boolean =
  this.isWhitespace() || this.category.let { category ->
    category != CharCategory.CONTROL &&
      category != CharCategory.FORMAT &&
      category != CharCategory.PRIVATE_USE &&
      category != CharCategory.SURROGATE &&
      category != CharCategory.UNASSIGNED
  }
