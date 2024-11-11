package com.fortechteams.dnsdsl.valuetypes.exception

class InvalidSubnetMaskException(value: String, msg: String) :
  IllegalArgumentException("Invalid subnet mask: $value - $msg")