package com.fortechteams.valuetypes.network.exception

class InvalidSubnetMaskException(value: String, msg: String) :
  IllegalArgumentException("Invalid subnet mask: $value - $msg")
