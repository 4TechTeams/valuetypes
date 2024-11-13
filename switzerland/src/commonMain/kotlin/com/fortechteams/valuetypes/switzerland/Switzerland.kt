package com.fortechteams.valuetypes.switzerland

import com.fortechteams.valuetypes.common.Country

@Deprecated(
  message = "Deprecated since 0.3.0. Use com.fortechteams.valuetypes.common.Country.SWITZERLAND instead.",
  level = DeprecationLevel.WARNING
)
object Switzerland {
  val englishName: String = Country.SWITZERLAND.englishName
  val localLanguageName: String = Country.SWITZERLAND.localLanguageName
  val alpha2Code: String = Country.SWITZERLAND.alpha2Code
  val alpha3Code: String = Country.SWITZERLAND.alpha3Code
  val numericCode: Int = Country.SWITZERLAND.numericCode
}
