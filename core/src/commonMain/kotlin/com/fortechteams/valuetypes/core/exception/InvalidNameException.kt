package com.fortechteams.valuetypes.core.exception

class InvalidNameException(value: String, msg: String) :
  IllegalArgumentException("Invalid name: $value - $msg")
