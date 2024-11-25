package com.fortechteams.valuetypes.common.exception

class InvalidGTINException(value: String, msg: String) :
  IllegalArgumentException("Invalid GTIN: $value - $msg")