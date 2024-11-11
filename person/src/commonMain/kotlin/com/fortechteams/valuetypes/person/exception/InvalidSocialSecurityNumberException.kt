package com.fortechteams.valuetypes.person.exception

/**
 * Exception used when a social security number validation fails
 *
 * @property value The invalid social security number value
 * @property msg The detailed error message
 */
class InvalidSocialSecurityNumberException(value: String, msg: String) :
  IllegalArgumentException("Invalid SSN: $value - $msg")
