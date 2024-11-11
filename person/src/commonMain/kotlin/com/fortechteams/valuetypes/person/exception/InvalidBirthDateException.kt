package com.fortechteams.valuetypes.network.exception

class InvalidBirthDateException(value: String, msg: String) :
  IllegalArgumentException("Invalid birthdate: $value - $msg")
