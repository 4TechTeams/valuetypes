package com.fortechteams.valuetypes.network.exception

class InvalidNameException(value: String, msg: String) :
  IllegalArgumentException("Invalid name: $value - $msg")
