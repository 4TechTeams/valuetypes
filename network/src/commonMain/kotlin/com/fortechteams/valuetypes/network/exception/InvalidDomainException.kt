package com.fortechteams.valuetypes.network.exception

class InvalidDomainException(value: String, msg: String) :
  IllegalArgumentException("Invalid domain: $value - $msg")
