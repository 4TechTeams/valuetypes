package com.fortechteams.valuetypes.common

/**
 * Base class for country-specific social security numbers
 *
 * CUrrently, this serves a purely semantical purpose, as SSNs are really different in each country. However, there
 * should be a common demonimator for SSN beyound `String`, so they all implement this interface.
 */
interface BaseSocialSecurityNumber {

  /**
   * The string representation of the social security number.
   */
  val value: String
}
