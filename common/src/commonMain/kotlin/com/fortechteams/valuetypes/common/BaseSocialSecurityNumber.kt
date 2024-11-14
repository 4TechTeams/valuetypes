package com.fortechteams.valuetypes.common

/**
 * Base class for country-specific social security numbers
 *
 * Currently, this serves a purely semantic purpose, as SSNs are really different in each country. However, there
 * should be a common denominator for SSN beyond `String`, so they all implement this interface.
 */
interface BaseSocialSecurityNumber {

  /**
   * The string representation of the social security number.
   */
  val value: String
}
