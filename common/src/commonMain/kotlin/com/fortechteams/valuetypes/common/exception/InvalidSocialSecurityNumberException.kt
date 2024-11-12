package com.fortechteams.valuetypes.common.exception

/**
 * Exception used when a social security number validation fails
 *
 * @property value The invalid social security number value
 * @property msg The detailed error message
 */
class InvalidSocialSecurityNumberException(value: String, msg: String, country: String) :
  IllegalArgumentException("""Invalid Social Security Number for $country: $value - $msg""")
