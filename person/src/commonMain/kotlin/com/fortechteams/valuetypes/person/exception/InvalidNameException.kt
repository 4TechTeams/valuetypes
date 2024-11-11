package com.fortechteams.valuetypes.person.exception

class InvalidNameException(value: String, msg: String) :
  IllegalArgumentException("Invalid name: $value - $msg")
