package com.fortechteams.valuetypes.core.exception

class InvalidBirthDateException(value: String, msg: String) :
  IllegalArgumentException("Invalid birthdate: $value - $msg")
