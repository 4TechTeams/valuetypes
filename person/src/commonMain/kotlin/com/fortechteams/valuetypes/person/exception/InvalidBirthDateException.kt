package com.fortechteams.valuetypes.person.exception

class InvalidBirthDateException(value: String, msg: String) :
  IllegalArgumentException("Invalid birthdate: $value - $msg")
