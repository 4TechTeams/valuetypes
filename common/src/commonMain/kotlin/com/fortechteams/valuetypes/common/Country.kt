package com.fortechteams.valuetypes.common

/**
 * Represents a country with its official names, codes, and identifiers according to ISO 3166-1.
 *
 * <!--- TEST_NAME CountryKnitTest -->
 *
 * ## Example
 * ```kotlin
 * import com.fortechteams.valuetypes.common.Country
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   val switzerland = Country.SWITZERLAND
 *
 *   switzerland.englishName shouldBe "Switzerland"
 *   switzerland.localLanguageName shouldBe "Schweiz"
 *   switzerland.alpha2Code shouldBe "CH"
 *   switzerland.alpha3Code shouldBe "CHE"
 *   switzerland.numericCode shouldBe 756
 *
 *   val france = Country.FRANCE
 *   france.localLanguageName shouldBe france.englishName  // Same in French
 * }
 * ```
 * <!--- KNIT example-common-Country-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 *
 * @since 0.3.0
 */
enum class Country(
  /**
   * The country's official name in English.
   *
   * This is typically the name recognized internationally and used in English-language contexts and international
   * communications.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.common.Country
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   Country.GERMANY.englishName shouldBe "Germany"
   * }
   * ```
   * <!--- KNIT example-common-Country-02.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  val englishName: String,

  /**
   * The country's name in its primary local/official language.
   *
   * For countries with multiple official languages, this represents the name in the most widely used official language
   * or follows official government guidelines for primary representation.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.common.Country
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   Country.SWITZERLAND.localLanguageName shouldBe "Schweiz"  // German
   *   Country.FRANCE.localLanguageName shouldBe "France"        // Same in both languages
   * }
   * ```
   * <!--- KNIT example-common-Country-03.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  val localLanguageName: String,

  /**
   * The ISO 3166-1 alpha-2 code for the country.
   *
   * This is a two-letter country code defined in ISO 3166-1. These codes are widely used in international systems,
   * internet domains, and currency representations.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.common.Country
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val germany = Country.GERMANY
   *   germany.alpha2Code shouldBe "DE"
   *   germany.alpha2Code.length shouldBe 2
   * }
   * ```
   * <!--- KNIT example-common-Country-04.kt -->
   * <!--- TEST lines.isEmpty() -->
   *
   * @see [ISO 3166-1 alpha-2](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2)
   */
  val alpha2Code: String,

  /**
   * The ISO 3166-1 alpha-3 code for the country.
   *
   * This is a three-letter country code defined in ISO 3166-1. These codes are commonly used in situations where a more
   * memorable code is helpful, such as currency representations and international sports events.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.common.Country
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val france = Country.FRANCE
   *   france.alpha3Code shouldBe "FRA"
   *   france.alpha3Code.length shouldBe 3
   * }
   * ```
   * <!--- KNIT example-common-Country-05.kt -->
   * <!--- TEST lines.isEmpty() -->
   *
   * @see [ISO 3166-1 alpha-3](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3)
   */
  val alpha3Code: String,

  /**
   * The ISO 3166-1 numeric code for the country.
   *
   * This is a three-digit country code defined in ISO 3166-1. These codes are useful in systems where language
   * independence is required or in dealing with countries whose codes have changed over time.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.common.Country
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val switzerland = Country.SWITZERLAND
   *   switzerland.numericCode shouldBe 756
   *   switzerland.numericCode.toString().length shouldBe 3
   * }
   * ```
   * <!--- KNIT example-common-Country-06.kt -->
   * <!--- TEST lines.isEmpty() -->
   *
   * Note: Leading zeros should be preserved when displaying the code, though the property itself is stored as an Int.
   *
   * @see [ISO 3166-1 numeric](https://en.wikipedia.org/wiki/ISO_3166-1_numeric)
   */
  val numericCode: Int,
) {
  AFGHANISTAN(
    englishName = "Afghanistan",
    localLanguageName = "افغانستان",
    alpha2Code = "AF",
    alpha3Code = "AFG",
    numericCode = 4
  ),

  ALBANIA(
    englishName = "Albania",
    localLanguageName = "Shqipëria",
    alpha2Code = "AL",
    alpha3Code = "ALB",
    numericCode = 8
  ),

  ALGERIA(
    englishName = "Algeria",
    localLanguageName = "الجزائر",
    alpha2Code = "DZ",
    alpha3Code = "DZA",
    numericCode = 12
  ),

  ANDORRA(
    englishName = "Andorra",
    localLanguageName = "Andorra",
    alpha2Code = "AD",
    alpha3Code = "AND",
    numericCode = 20
  ),

  ANGOLA(
    englishName = "Angola",
    localLanguageName = "Angola",
    alpha2Code = "AO",
    alpha3Code = "AGO",
    numericCode = 24
  ),

  ANTIGUA_AND_BARBUDA(
    englishName = "Antigua and Barbuda",
    localLanguageName = "Antigua and Barbuda",
    alpha2Code = "AG",
    alpha3Code = "ATG",
    numericCode = 28
  ),

  ARGENTINA(
    englishName = "Argentina",
    localLanguageName = "Argentina",
    alpha2Code = "AR",
    alpha3Code = "ARG",
    numericCode = 32
  ),

  ARMENIA(
    englishName = "Armenia",
    localLanguageName = "Հայաստան",
    alpha2Code = "AM",
    alpha3Code = "ARM",
    numericCode = 51
  ),

  AUSTRALIA(
    englishName = "Australia",
    localLanguageName = "Australia",
    alpha2Code = "AU",
    alpha3Code = "AUS",
    numericCode = 36
  ),

  AUSTRIA(
    englishName = "Austria",
    localLanguageName = "Österreich",
    alpha2Code = "AT",
    alpha3Code = "AUT",
    numericCode = 40
  ),

  AZERBAIJAN(
    englishName = "Azerbaijan",
    localLanguageName = "Azərbaycan",
    alpha2Code = "AZ",
    alpha3Code = "AZE",
    numericCode = 31
  ),

  BAHAMAS(
    englishName = "Bahamas",
    localLanguageName = "Bahamas",
    alpha2Code = "BS",
    alpha3Code = "BHS",
    numericCode = 44
  ),

  BAHRAIN(
    englishName = "Bahrain",
    localLanguageName = "البحرين",
    alpha2Code = "BH",
    alpha3Code = "BHR",
    numericCode = 48
  ),

  BANGLADESH(
    englishName = "Bangladesh",
    localLanguageName = "বাংলাদেশ",
    alpha2Code = "BD",
    alpha3Code = "BGD",
    numericCode = 50
  ),

  BARBADOS(
    englishName = "Barbados",
    localLanguageName = "Barbados",
    alpha2Code = "BB",
    alpha3Code = "BRB",
    numericCode = 52
  ),

  BELARUS(
    englishName = "Belarus",
    localLanguageName = "Беларусь",
    alpha2Code = "BY",
    alpha3Code = "BLR",
    numericCode = 112
  ),

  BELGIUM(
    englishName = "Belgium",
    localLanguageName = "België",
    alpha2Code = "BE",
    alpha3Code = "BEL",
    numericCode = 56
  ),

  BELIZE(
    englishName = "Belize",
    localLanguageName = "Belize",
    alpha2Code = "BZ",
    alpha3Code = "BLZ",
    numericCode = 84
  ),

  BENIN(
    englishName = "Benin",
    localLanguageName = "Bénin",
    alpha2Code = "BJ",
    alpha3Code = "BEN",
    numericCode = 204
  ),

  BHUTAN(
    englishName = "Bhutan",
    localLanguageName = "འབྲུག་ཡུལ",
    alpha2Code = "BT",
    alpha3Code = "BTN",
    numericCode = 64
  ),

  BOLIVIA(
    englishName = "Bolivia",
    localLanguageName = "Bolivia",
    alpha2Code = "BO",
    alpha3Code = "BOL",
    numericCode = 68
  ),

  BOSNIA_AND_HERZEGOVINA(
    englishName = "Bosnia and Herzegovina",
    localLanguageName = "Bosna i Hercegovina",
    alpha2Code = "BA",
    alpha3Code = "BIH",
    numericCode = 70
  ),

  BOTSWANA(
    englishName = "Botswana",
    localLanguageName = "Botswana",
    alpha2Code = "BW",
    alpha3Code = "BWA",
    numericCode = 72
  ),

  BRAZIL(
    englishName = "Brazil",
    localLanguageName = "Brasil",
    alpha2Code = "BR",
    alpha3Code = "BRA",
    numericCode = 76
  ),

  BRUNEI(
    englishName = "Brunei",
    localLanguageName = "Brunei Darussalam",
    alpha2Code = "BN",
    alpha3Code = "BRN",
    numericCode = 96
  ),

  BULGARIA(
    englishName = "Bulgaria",
    localLanguageName = "България",
    alpha2Code = "BG",
    alpha3Code = "BGR",
    numericCode = 100
  ),

  BURKINA_FASO(
    englishName = "Burkina Faso",
    localLanguageName = "Burkina Faso",
    alpha2Code = "BF",
    alpha3Code = "BFA",
    numericCode = 854
  ),

  BURUNDI(
    englishName = "Burundi",
    localLanguageName = "Burundi",
    alpha2Code = "BI",
    alpha3Code = "BDI",
    numericCode = 108
  ),

  CAMBODIA(
    englishName = "Cambodia",
    localLanguageName = "កម្ពុជា",
    alpha2Code = "KH",
    alpha3Code = "KHM",
    numericCode = 116
  ),

  CAMEROON(
    englishName = "Cameroon",
    localLanguageName = "Cameroun",
    alpha2Code = "CM",
    alpha3Code = "CMR",
    numericCode = 120
  ),

  CANADA(
    englishName = "Canada",
    localLanguageName = "Canada",
    alpha2Code = "CA",
    alpha3Code = "CAN",
    numericCode = 124
  ),

  CAPE_VERDE(
    englishName = "Cape Verde",
    localLanguageName = "Cabo Verde",
    alpha2Code = "CV",
    alpha3Code = "CPV",
    numericCode = 132
  ),

  CENTRAL_AFRICAN_REPUBLIC(
    englishName = "Central African Republic",
    localLanguageName = "République centrafricaine",
    alpha2Code = "CF",
    alpha3Code = "CAF",
    numericCode = 140
  ),

  CHAD(
    englishName = "Chad",
    localLanguageName = "Tchad",
    alpha2Code = "TD",
    alpha3Code = "TCD",
    numericCode = 148
  ),

  CHILE(
    englishName = "Chile",
    localLanguageName = "Chile",
    alpha2Code = "CL",
    alpha3Code = "CHL",
    numericCode = 152
  ),

  CHINA(
    englishName = "China",
    localLanguageName = "中国",
    alpha2Code = "CN",
    alpha3Code = "CHN",
    numericCode = 156
  ),

  COLOMBIA(
    englishName = "Colombia",
    localLanguageName = "Colombia",
    alpha2Code = "CO",
    alpha3Code = "COL",
    numericCode = 170
  ),

  COMOROS(
    englishName = "Comoros",
    localLanguageName = "Komori",
    alpha2Code = "KM",
    alpha3Code = "COM",
    numericCode = 174
  ),

  CONGO(
    englishName = "Congo",
    localLanguageName = "Congo",
    alpha2Code = "CG",
    alpha3Code = "COG",
    numericCode = 178
  ),

  COSTA_RICA(
    englishName = "Costa Rica",
    localLanguageName = "Costa Rica",
    alpha2Code = "CR",
    alpha3Code = "CRI",
    numericCode = 188
  ),

  CROATIA(
    englishName = "Croatia",
    localLanguageName = "Hrvatska",
    alpha2Code = "HR",
    alpha3Code = "HRV",
    numericCode = 191
  ),

  CUBA(
    englishName = "Cuba",
    localLanguageName = "Cuba",
    alpha2Code = "CU",
    alpha3Code = "CUB",
    numericCode = 192
  ),

  CYPRUS(
    englishName = "Cyprus",
    localLanguageName = "Κύπρος",
    alpha2Code = "CY",
    alpha3Code = "CYP",
    numericCode = 196
  ),

  CZECH_REPUBLIC(
    englishName = "Czech Republic",
    localLanguageName = "Česká republika",
    alpha2Code = "CZ",
    alpha3Code = "CZE",
    numericCode = 203
  ),

  DENMARK(
    englishName = "Denmark",
    localLanguageName = "Danmark",
    alpha2Code = "DK",
    alpha3Code = "DNK",
    numericCode = 208
  ),

  DJIBOUTI(
    englishName = "Djibouti",
    localLanguageName = "Djibouti",
    alpha2Code = "DJ",
    alpha3Code = "DJI",
    numericCode = 262
  ),

  DOMINICA(
    englishName = "Dominica",
    localLanguageName = "Dominica",
    alpha2Code = "DM",
    alpha3Code = "DMA",
    numericCode = 212
  ),

  DOMINICAN_REPUBLIC(
    englishName = "Dominican Republic",
    localLanguageName = "República Dominicana",
    alpha2Code = "DO",
    alpha3Code = "DOM",
    numericCode = 214
  ),

  ECUADOR(
    englishName = "Ecuador",
    localLanguageName = "Ecuador",
    alpha2Code = "EC",
    alpha3Code = "ECU",
    numericCode = 218
  ),

  EGYPT(
    englishName = "Egypt",
    localLanguageName = "مصر",
    alpha2Code = "EG",
    alpha3Code = "EGY",
    numericCode = 818
  ),

  EL_SALVADOR(
    englishName = "El Salvador",
    localLanguageName = "El Salvador",
    alpha2Code = "SV",
    alpha3Code = "SLV",
    numericCode = 222
  ),

  EQUATORIAL_GUINEA(
    englishName = "Equatorial Guinea",
    localLanguageName = "Guinea Ecuatorial",
    alpha2Code = "GQ",
    alpha3Code = "GNQ",
    numericCode = 226
  ),

  ERITREA(
    englishName = "Eritrea",
    localLanguageName = "ኤርትራ",
    alpha2Code = "ER",
    alpha3Code = "ERI",
    numericCode = 232
  ),

  ESTONIA(
    englishName = "Estonia",
    localLanguageName = "Eesti",
    alpha2Code = "EE",
    alpha3Code = "EST",
    numericCode = 233
  ),

  ESWATINI(
    englishName = "Eswatini",
    localLanguageName = "Eswatini",
    alpha2Code = "SZ",
    alpha3Code = "SWZ",
    numericCode = 748
  ),

  ETHIOPIA(
    englishName = "Ethiopia",
    localLanguageName = "ኢትዮጵያ",
    alpha2Code = "ET",
    alpha3Code = "ETH",
    numericCode = 231
  ),

  FIJI(
    englishName = "Fiji",
    localLanguageName = "Fiji",
    alpha2Code = "FJ",
    alpha3Code = "FJI",
    numericCode = 242
  ),

  FINLAND(
    englishName = "Finland",
    localLanguageName = "Suomi",
    alpha2Code = "FI",
    alpha3Code = "FIN",
    numericCode = 246
  ),

  FRANCE(
    englishName = "France",
    localLanguageName = "France",
    alpha2Code = "FR",
    alpha3Code = "FRA",
    numericCode = 250
  ),

  GABON(
    englishName = "Gabon",
    localLanguageName = "Gabon",
    alpha2Code = "GA",
    alpha3Code = "GAB",
    numericCode = 266
  ),

  GAMBIA(
    englishName = "Gambia",
    localLanguageName = "The Gambia",
    alpha2Code = "GM",
    alpha3Code = "GMB",
    numericCode = 270
  ),

  GEORGIA(
    englishName = "Georgia",
    localLanguageName = "საქართველო",
    alpha2Code = "GE",
    alpha3Code = "GEO",
    numericCode = 268
  ),

  GERMANY(
    englishName = "Germany",
    localLanguageName = "Deutschland",
    alpha2Code = "DE",
    alpha3Code = "DEU",
    numericCode = 276
  ),

  GHANA(
    englishName = "Ghana",
    localLanguageName = "Ghana",
    alpha2Code = "GH",
    alpha3Code = "GHA",
    numericCode = 288
  ),

  GREECE(
    englishName = "Greece",
    localLanguageName = "Ελλάδα",
    alpha2Code = "GR",
    alpha3Code = "GRC",
    numericCode = 300
  ),

  GRENADA(
    englishName = "Grenada",
    localLanguageName = "Grenada",
    alpha2Code = "GD",
    alpha3Code = "GRD",
    numericCode = 308
  ),

  GUATEMALA(
    englishName = "Guatemala",
    localLanguageName = "Guatemala",
    alpha2Code = "GT",
    alpha3Code = "GTM",
    numericCode = 320
  ),

  GUINEA(
    englishName = "Guinea",
    localLanguageName = "Guinée",
    alpha2Code = "GN",
    alpha3Code = "GIN",
    numericCode = 324
  ),

  GUINEA_BISSAU(
    englishName = "Guinea-Bissau",
    localLanguageName = "Guiné-Bissau",
    alpha2Code = "GW",
    alpha3Code = "GNB",
    numericCode = 624
  ),

  GUYANA(
    englishName = "Guyana",
    localLanguageName = "Guyana",
    alpha2Code = "GY",
    alpha3Code = "GUY",
    numericCode = 328
  ),

  HAITI(
    englishName = "Haiti",
    localLanguageName = "Haïti",
    alpha2Code = "HT",
    alpha3Code = "HTI",
    numericCode = 332
  ),

  HONDURAS(
    englishName = "Honduras",
    localLanguageName = "Honduras",
    alpha2Code = "HN",
    alpha3Code = "HND",
    numericCode = 340
  ),

  HUNGARY(
    englishName = "Hungary",
    localLanguageName = "Magyarország",
    alpha2Code = "HU",
    alpha3Code = "HUN",
    numericCode = 348
  ),

  ICELAND(
    englishName = "Iceland",
    localLanguageName = "Ísland",
    alpha2Code = "IS",
    alpha3Code = "ISL",
    numericCode = 352
  ),

  INDIA(
    englishName = "India",
    localLanguageName = "भारत",
    alpha2Code = "IN",
    alpha3Code = "IND",
    numericCode = 356
  ),

  INDONESIA(
    englishName = "Indonesia",
    localLanguageName = "Indonesia",
    alpha2Code = "ID",
    alpha3Code = "IDN",
    numericCode = 360
  ),

  IRAN(
    englishName = "Iran",
    localLanguageName = "ایران",
    alpha2Code = "IR",
    alpha3Code = "IRN",
    numericCode = 364
  ),

  IRAQ(
    englishName = "Iraq",
    localLanguageName = "العراق",
    alpha2Code = "IQ",
    alpha3Code = "IRQ",
    numericCode = 368
  ),

  IRELAND(
    englishName = "Ireland",
    localLanguageName = "Éire",
    alpha2Code = "IE",
    alpha3Code = "IRL",
    numericCode = 372
  ),

  ISRAEL(
    englishName = "Israel",
    localLanguageName = "ישראל",
    alpha2Code = "IL",
    alpha3Code = "ISR",
    numericCode = 376
  ),

  ITALY(
    englishName = "Italy",
    localLanguageName = "Italia",
    alpha2Code = "IT",
    alpha3Code = "ITA",
    numericCode = 380
  ),

  JAMAICA(
    englishName = "Jamaica",
    localLanguageName = "Jamaica",
    alpha2Code = "JM",
    alpha3Code = "JAM",
    numericCode = 388
  ),

  JAPAN(
    englishName = "Japan",
    localLanguageName = "日本",
    alpha2Code = "JP",
    alpha3Code = "JPN",
    numericCode = 392
  ),

  JORDAN(
    englishName = "Jordan",
    localLanguageName = "الأردن",
    alpha2Code = "JO",
    alpha3Code = "JOR",
    numericCode = 400
  ),

  KAZAKHSTAN(
    englishName = "Kazakhstan",
    localLanguageName = "Қазақстан",
    alpha2Code = "KZ",
    alpha3Code = "KAZ",
    numericCode = 398
  ),

  KENYA(
    englishName = "Kenya",
    localLanguageName = "Kenya",
    alpha2Code = "KE",
    alpha3Code = "KEN",
    numericCode = 404
  ),

  KIRIBATI(
    englishName = "Kiribati",
    localLanguageName = "Kiribati",
    alpha2Code = "KI",
    alpha3Code = "KIR",
    numericCode = 296
  ),

  NORTH_KOREA(
    englishName = "North Korea",
    localLanguageName = "조선민주주의인민공화국",
    alpha2Code = "KP",
    alpha3Code = "PRK",
    numericCode = 408
  ),

  SOUTH_KOREA(
    englishName = "South Korea",
    localLanguageName = "대한민국",
    alpha2Code = "KR",
    alpha3Code = "KOR",
    numericCode = 410
  ),

  KUWAIT(
    englishName = "Kuwait",
    localLanguageName = "الكويت",
    alpha2Code = "KW",
    alpha3Code = "KWT",
    numericCode = 414
  ),

  KYRGYZSTAN(
    englishName = "Kyrgyzstan",
    localLanguageName = "Кыргызстан",
    alpha2Code = "KG",
    alpha3Code = "KGZ",
    numericCode = 417
  ),

  LAOS(
    englishName = "Laos",
    localLanguageName = "ລາວ",
    alpha2Code = "LA",
    alpha3Code = "LAO",
    numericCode = 418
  ),

  LATVIA(
    englishName = "Latvia",
    localLanguageName = "Latvija",
    alpha2Code = "LV",
    alpha3Code = "LVA",
    numericCode = 428
  ),

  LEBANON(
    englishName = "Lebanon",
    localLanguageName = "لبنان",
    alpha2Code = "LB",
    alpha3Code = "LBN",
    numericCode = 422
  ),

  LESOTHO(
    englishName = "Lesotho",
    localLanguageName = "Lesotho",
    alpha2Code = "LS",
    alpha3Code = "LSO",
    numericCode = 426
  ),

  LIBERIA(
    englishName = "Liberia",
    localLanguageName = "Liberia",
    alpha2Code = "LR",
    alpha3Code = "LBR",
    numericCode = 430
  ),

  LIBYA(
    englishName = "Libya",
    localLanguageName = "ليبيا",
    alpha2Code = "LY",
    alpha3Code = "LBY",
    numericCode = 434
  ),

  LIECHTENSTEIN(
    englishName = "Liechtenstein",
    localLanguageName = "Liechtenstein",
    alpha2Code = "LI",
    alpha3Code = "LIE",
    numericCode = 438
  ),

  LITHUANIA(
    englishName = "Lithuania",
    localLanguageName = "Lietuva",
    alpha2Code = "LT",
    alpha3Code = "LTU",
    numericCode = 440
  ),

  LUXEMBOURG(
    englishName = "Luxembourg",
    localLanguageName = "Lëtzebuerg",
    alpha2Code = "LU",
    alpha3Code = "LUX",
    numericCode = 442
  ),

  MACAO(
    englishName = "Macao",
    localLanguageName = "澳門",
    alpha2Code = "MO",
    alpha3Code = "MAC",
    numericCode = 446
  ),

  MADAGASCAR(
    englishName = "Madagascar",
    localLanguageName = "Madagasikara",
    alpha2Code = "MG",
    alpha3Code = "MDG",
    numericCode = 450
  ),

  MALAWI(
    englishName = "Malawi",
    localLanguageName = "Malawi",
    alpha2Code = "MW",
    alpha3Code = "MWI",
    numericCode = 454
  ),

  MALAYSIA(
    englishName = "Malaysia",
    localLanguageName = "Malaysia",
    alpha2Code = "MY",
    alpha3Code = "MYS",
    numericCode = 458
  ),

  MALDIVES(
    englishName = "Maldives",
    localLanguageName = "ދިވެހިރާއްޖެ",
    alpha2Code = "MV",
    alpha3Code = "MDV",
    numericCode = 462
  ),

  MALI(
    englishName = "Mali",
    localLanguageName = "Mali",
    alpha2Code = "ML",
    alpha3Code = "MLI",
    numericCode = 466
  ),

  MALTA(
    englishName = "Malta",
    localLanguageName = "Malta",
    alpha2Code = "MT",
    alpha3Code = "MLT",
    numericCode = 470
  ),

  MARSHALL_ISLANDS(
    englishName = "Marshall Islands",
    localLanguageName = "Marshall Islands",
    alpha2Code = "MH",
    alpha3Code = "MHL",
    numericCode = 584
  ),

  MAURITANIA(
    englishName = "Mauritania",
    localLanguageName = "موريتانيا",
    alpha2Code = "MR",
    alpha3Code = "MRT",
    numericCode = 478
  ),

  MAURITIUS(
    englishName = "Mauritius",
    localLanguageName = "Maurice",
    alpha2Code = "MU",
    alpha3Code = "MUS",
    numericCode = 480
  ),

  MEXICO(
    englishName = "Mexico",
    localLanguageName = "México",
    alpha2Code = "MX",
    alpha3Code = "MEX",
    numericCode = 484
  ),

  MICRONESIA(
    englishName = "Micronesia",
    localLanguageName = "Micronesia",
    alpha2Code = "FM",
    alpha3Code = "FSM",
    numericCode = 583
  ),

  MOLDOVA(
    englishName = "Moldova",
    localLanguageName = "Moldova",
    alpha2Code = "MD",
    alpha3Code = "MDA",
    numericCode = 498
  ),

  MONACO(
    englishName = "Monaco",
    localLanguageName = "Monaco",
    alpha2Code = "MC",
    alpha3Code = "MCO",
    numericCode = 492
  ),

  MONGOLIA(
    englishName = "Mongolia",
    localLanguageName = "Монгол",
    alpha2Code = "MN",
    alpha3Code = "MNG",
    numericCode = 496
  ),

  MONTENEGRO(
    englishName = "Montenegro",
    localLanguageName = "Црна Гора",
    alpha2Code = "ME",
    alpha3Code = "MNE",
    numericCode = 499
  ),

  MOROCCO(
    englishName = "Morocco",
    localLanguageName = "المغرب",
    alpha2Code = "MA",
    alpha3Code = "MAR",
    numericCode = 504
  ),

  MOZAMBIQUE(
    englishName = "Mozambique",
    localLanguageName = "Moçambique",
    alpha2Code = "MZ",
    alpha3Code = "MOZ",
    numericCode = 508
  ),

  MYANMAR(
    englishName = "Myanmar",
    localLanguageName = "မြန်မာ",
    alpha2Code = "MM",
    alpha3Code = "MMR",
    numericCode = 104
  ),

  NAMIBIA(
    englishName = "Namibia",
    localLanguageName = "Namibia",
    alpha2Code = "NA",
    alpha3Code = "NAM",
    numericCode = 516
  ),

  NAURU(
    englishName = "Nauru",
    localLanguageName = "Nauru",
    alpha2Code = "NR",
    alpha3Code = "NRU",
    numericCode = 520
  ),

  NEPAL(
    englishName = "Nepal",
    localLanguageName = "नेपाल",
    alpha2Code = "NP",
    alpha3Code = "NPL",
    numericCode = 524
  ),

  NETHERLANDS(
    englishName = "Netherlands",
    localLanguageName = "Nederland",
    alpha2Code = "NL",
    alpha3Code = "NLD",
    numericCode = 528
  ),

  NEW_ZEALAND(
    englishName = "New Zealand",
    localLanguageName = "New Zealand",
    alpha2Code = "NZ",
    alpha3Code = "NZL",
    numericCode = 554
  ),

  NICARAGUA(
    englishName = "Nicaragua",
    localLanguageName = "Nicaragua",
    alpha2Code = "NI",
    alpha3Code = "NIC",
    numericCode = 558
  ),

  NIGER(
    englishName = "Niger",
    localLanguageName = "Niger",
    alpha2Code = "NE",
    alpha3Code = "NER",
    numericCode = 562
  ),

  NIGERIA(
    englishName = "Nigeria",
    localLanguageName = "Nigeria",
    alpha2Code = "NG",
    alpha3Code = "NGA",
    numericCode = 566
  ),

  NORTH_MACEDONIA(
    englishName = "North Macedonia",
    localLanguageName = "Северна Македонија",
    alpha2Code = "MK",
    alpha3Code = "MKD",
    numericCode = 807
  ),

  NORWAY(
    englishName = "Norway",
    localLanguageName = "Norge",
    alpha2Code = "NO",
    alpha3Code = "NOR",
    numericCode = 578
  ),

  OMAN(
    englishName = "Oman",
    localLanguageName = "عمان",
    alpha2Code = "OM",
    alpha3Code = "OMN",
    numericCode = 512
  ),

  PAKISTAN(
    englishName = "Pakistan",
    localLanguageName = "پاکستان",
    alpha2Code = "PK",
    alpha3Code = "PAK",
    numericCode = 586
  ),

  PALESTINE(
    englishName = "Palestine",
    localLanguageName = "فلسطين",
    alpha2Code = "PS",
    alpha3Code = "PSE",
    numericCode = 275
  ),

  PALAU(
    englishName = "Palau",
    localLanguageName = "Palau",
    alpha2Code = "PW",
    alpha3Code = "PLW",
    numericCode = 585
  ),

  PANAMA(
    englishName = "Panama",
    localLanguageName = "Panamá",
    alpha2Code = "PA",
    alpha3Code = "PAN",
    numericCode = 591
  ),

  PAPUA_NEW_GUINEA(
    englishName = "Papua New Guinea",
    localLanguageName = "Papua New Guinea",
    alpha2Code = "PG",
    alpha3Code = "PNG",
    numericCode = 598
  ),

  PARAGUAY(
    englishName = "Paraguay",
    localLanguageName = "Paraguay",
    alpha2Code = "PY",
    alpha3Code = "PRY",
    numericCode = 600
  ),

  PERU(
    englishName = "Peru",
    localLanguageName = "Perú",
    alpha2Code = "PE",
    alpha3Code = "PER",
    numericCode = 604
  ),

  PHILIPPINES(
    englishName = "Philippines",
    localLanguageName = "Pilipinas",
    alpha2Code = "PH",
    alpha3Code = "PHL",
    numericCode = 608
  ),

  POLAND(
    englishName = "Poland",
    localLanguageName = "Polska",
    alpha2Code = "PL",
    alpha3Code = "POL",
    numericCode = 616
  ),

  PORTUGAL(
    englishName = "Portugal",
    localLanguageName = "Portugal",
    alpha2Code = "PT",
    alpha3Code = "PRT",
    numericCode = 620
  ),

  QATAR(
    englishName = "Qatar",
    localLanguageName = "قطر",
    alpha2Code = "QA",
    alpha3Code = "QAT",
    numericCode = 634
  ),

  ROMANIA(
    englishName = "Romania",
    localLanguageName = "România",
    alpha2Code = "RO",
    alpha3Code = "ROU",
    numericCode = 642
  ),

  RUSSIA(
    englishName = "Russia",
    localLanguageName = "Россия",
    alpha2Code = "RU",
    alpha3Code = "RUS",
    numericCode = 643
  ),

  RWANDA(
    englishName = "Rwanda",
    localLanguageName = "Rwanda",
    alpha2Code = "RW",
    alpha3Code = "RWA",
    numericCode = 646
  ),

  SAINT_KITTS_AND_NEVIS(
    englishName = "Saint Kitts and Nevis",
    localLanguageName = "Saint Kitts and Nevis",
    alpha2Code = "KN",
    alpha3Code = "KNA",
    numericCode = 659
  ),

  SAINT_LUCIA(
    englishName = "Saint Lucia",
    localLanguageName = "Saint Lucia",
    alpha2Code = "LC",
    alpha3Code = "LCA",
    numericCode = 662
  ),

  SAINT_VINCENT_AND_THE_GRENADINES(
    englishName = "Saint Vincent and the Grenadines",
    localLanguageName = "Saint Vincent and the Grenadines",
    alpha2Code = "VC",
    alpha3Code = "VCT",
    numericCode = 670
  ),

  SAMOA(
    englishName = "Samoa",
    localLanguageName = "Samoa",
    alpha2Code = "WS",
    alpha3Code = "WSM",
    numericCode = 882
  ),

  SAN_MARINO(
    englishName = "San Marino",
    localLanguageName = "San Marino",
    alpha2Code = "SM",
    alpha3Code = "SMR",
    numericCode = 674
  ),

  SAO_TOME_AND_PRINCIPE(
    englishName = "Sao Tome and Principe",
    localLanguageName = "São Tomé e Príncipe",
    alpha2Code = "ST",
    alpha3Code = "STP",
    numericCode = 678
  ),

  SAUDI_ARABIA(
    englishName = "Saudi Arabia",
    localLanguageName = "المملكة العربية السعودية",
    alpha2Code = "SA",
    alpha3Code = "SAU",
    numericCode = 682
  ),

  SENEGAL(
    englishName = "Senegal",
    localLanguageName = "Sénégal",
    alpha2Code = "SN",
    alpha3Code = "SEN",
    numericCode = 686
  ),

  SERBIA(
    englishName = "Serbia",
    localLanguageName = "Србија",
    alpha2Code = "RS",
    alpha3Code = "SRB",
    numericCode = 688
  ),

  SEYCHELLES(
    englishName = "Seychelles",
    localLanguageName = "Seychelles",
    alpha2Code = "SC",
    alpha3Code = "SYC",
    numericCode = 690
  ),

  SIERRA_LEONE(
    englishName = "Sierra Leone",
    localLanguageName = "Sierra Leone",
    alpha2Code = "SL",
    alpha3Code = "SLE",
    numericCode = 694
  ),

  SINGAPORE(
    englishName = "Singapore",
    localLanguageName = "Singapore",
    alpha2Code = "SG",
    alpha3Code = "SGP",
    numericCode = 702
  ),

  SLOVAKIA(
    englishName = "Slovakia",
    localLanguageName = "Slovensko",
    alpha2Code = "SK",
    alpha3Code = "SVK",
    numericCode = 703
  ),

  SLOVENIA(
    englishName = "Slovenia",
    localLanguageName = "Slovenija",
    alpha2Code = "SI",
    alpha3Code = "SVN",
    numericCode = 705
  ),

  SOLOMON_ISLANDS(
    englishName = "Solomon Islands",
    localLanguageName = "Solomon Islands",
    alpha2Code = "SB",
    alpha3Code = "SLB",
    numericCode = 90
  ),

  SOMALIA(
    englishName = "Somalia",
    localLanguageName = "Soomaaliya",
    alpha2Code = "SO",
    alpha3Code = "SOM",
    numericCode = 706
  ),

  SOUTH_AFRICA(
    englishName = "South Africa",
    localLanguageName = "South Africa",
    alpha2Code = "ZA",
    alpha3Code = "ZAF",
    numericCode = 710
  ),

  SOUTH_SUDAN(
    englishName = "South Sudan",
    localLanguageName = "South Sudan",
    alpha2Code = "SS",
    alpha3Code = "SSD",
    numericCode = 728
  ),

  SPAIN(
    englishName = "Spain",
    localLanguageName = "España",
    alpha2Code = "ES",
    alpha3Code = "ESP",
    numericCode = 724
  ),

  SRI_LANKA(
    englishName = "Sri Lanka",
    localLanguageName = "ශ්‍රී ලංකා",
    alpha2Code = "LK",
    alpha3Code = "LKA",
    numericCode = 144
  ),

  SUDAN(
    englishName = "Sudan",
    localLanguageName = "السودان",
    alpha2Code = "SD",
    alpha3Code = "SDN",
    numericCode = 729
  ),

  SURINAME(
    englishName = "Suriname",
    localLanguageName = "Suriname",
    alpha2Code = "SR",
    alpha3Code = "SUR",
    numericCode = 740
  ),

  SWEDEN(
    englishName = "Sweden",
    localLanguageName = "Sverige",
    alpha2Code = "SE",
    alpha3Code = "SWE",
    numericCode = 752
  ),

  SWITZERLAND(
    englishName = "Switzerland",
    localLanguageName = "Schweiz",
    alpha2Code = "CH",
    alpha3Code = "CHE",
    numericCode = 756
  ),

  SYRIA(
    englishName = "Syria",
    localLanguageName = "سوريا",
    alpha2Code = "SY",
    alpha3Code = "SYR",
    numericCode = 760
  ),

  TAIWAN(
    englishName = "Taiwan",
    localLanguageName = "臺灣",
    alpha2Code = "TW",
    alpha3Code = "TWN",
    numericCode = 158
  ),

  TAJIKISTAN(
    englishName = "Tajikistan",
    localLanguageName = "Тоҷикистон",
    alpha2Code = "TJ",
    alpha3Code = "TJK",
    numericCode = 762
  ),

  TANZANIA(
    englishName = "Tanzania",
    localLanguageName = "Tanzania",
    alpha2Code = "TZ",
    alpha3Code = "TZA",
    numericCode = 834
  ),

  THAILAND(
    englishName = "Thailand",
    localLanguageName = "ประเทศไทย",
    alpha2Code = "TH",
    alpha3Code = "THA",
    numericCode = 764
  ),

  TIMOR_LESTE(
    englishName = "Timor-Leste",
    localLanguageName = "Timor-Leste",
    alpha2Code = "TL",
    alpha3Code = "TLS",
    numericCode = 626
  ),

  TOGO(
    englishName = "Togo",
    localLanguageName = "Togo",
    alpha2Code = "TG",
    alpha3Code = "TGO",
    numericCode = 768
  ),

  TONGA(
    englishName = "Tonga",
    localLanguageName = "Tonga",
    alpha2Code = "TO",
    alpha3Code = "TON",
    numericCode = 776
  ),

  TRINIDAD_AND_TOBAGO(
    englishName = "Trinidad and Tobago",
    localLanguageName = "Trinidad and Tobago",
    alpha2Code = "TT",
    alpha3Code = "TTO",
    numericCode = 780
  ),

  TUNISIA(
    englishName = "Tunisia",
    localLanguageName = "تونس",
    alpha2Code = "TN",
    alpha3Code = "TUN",
    numericCode = 788
  ),

  TURKEY(
    englishName = "Turkey",
    localLanguageName = "Türkiye",
    alpha2Code = "TR",
    alpha3Code = "TUR",
    numericCode = 792
  ),

  TURKMENISTAN(
    englishName = "Turkmenistan",
    localLanguageName = "Türkmenistan",
    alpha2Code = "TM",
    alpha3Code = "TKM",
    numericCode = 795
  ),

  TUVALU(
    englishName = "Tuvalu",
    localLanguageName = "Tuvalu",
    alpha2Code = "TV",
    alpha3Code = "TUV",
    numericCode = 798
  ),

  UGANDA(
    englishName = "Uganda",
    localLanguageName = "Uganda",
    alpha2Code = "UG",
    alpha3Code = "UGA",
    numericCode = 800
  ),

  UKRAINE(
    englishName = "Ukraine",
    localLanguageName = "Україна",
    alpha2Code = "UA",
    alpha3Code = "UKR",
    numericCode = 804
  ),

  UNITED_ARAB_EMIRATES(
    englishName = "United Arab Emirates",
    localLanguageName = "الإمارات العربية المتحدة",
    alpha2Code = "AE",
    alpha3Code = "ARE",
    numericCode = 784
  ),

  UNITED_KINGDOM(
    englishName = "United Kingdom",
    localLanguageName = "United Kingdom",
    alpha2Code = "GB",
    alpha3Code = "GBR",
    numericCode = 826
  ),

  UNITED_STATES(
    englishName = "United States",
    localLanguageName = "United States",
    alpha2Code = "US",
    alpha3Code = "USA",
    numericCode = 840
  ),

  URUGUAY(
    englishName = "Uruguay",
    localLanguageName = "Uruguay",
    alpha2Code = "UY",
    alpha3Code = "URY",
    numericCode = 858
  ),

  UZBEKISTAN(
    englishName = "Uzbekistan",
    localLanguageName = "O'zbekiston",
    alpha2Code = "UZ",
    alpha3Code = "UZB",
    numericCode = 860
  ),

  VANUATU(
    englishName = "Vanuatu",
    localLanguageName = "Vanuatu",
    alpha2Code = "VU",
    alpha3Code = "VUT",
    numericCode = 548
  ),

  VATICAN_CITY(
    englishName = "Vatican City",
    localLanguageName = "Città del Vaticano",
    alpha2Code = "VA",
    alpha3Code = "VAT",
    numericCode = 336
  ),

  VENEZUELA(
    englishName = "Venezuela",
    localLanguageName = "Venezuela",
    alpha2Code = "VE",
    alpha3Code = "VEN",
    numericCode = 862
  ),

  VIETNAM(
    englishName = "Vietnam",
    localLanguageName = "Việt Nam",
    alpha2Code = "VN",
    alpha3Code = "VNM",
    numericCode = 704
  ),

  YEMEN(
    englishName = "Yemen",
    localLanguageName = "اليمن",
    alpha2Code = "YE",
    alpha3Code = "YEM",
    numericCode = 887
  ),

  ZAMBIA(
    englishName = "Zambia",
    localLanguageName = "Zambia",
    alpha2Code = "ZM",
    alpha3Code = "ZMB",
    numericCode = 894
  ),

  ZIMBABWE(
    englishName = "Zimbabwe",
    localLanguageName = "Zimbabwe",
    alpha2Code = "ZW",
    alpha3Code = "ZWE",
    numericCode = 716
  );

  companion object {
    /**
     * Returns the country for the given alpha-2 code, ignoring case.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.common.Country
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   Country.fromAlpha2Code("CH") shouldBe Country.SWITZERLAND
     *   Country.fromAlpha2Code("ch") shouldBe Country.SWITZERLAND
     *   Country.fromAlpha2Code("invalid") shouldBe null
     * }
     * ```
     * <!--- KNIT example-common-Country-07.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromAlpha2Code(code: String): Country? =
      entries.firstOrNull { it.alpha2Code.equals(code, ignoreCase = true) }

    /**
     * Returns the country for the given alpha-3 code, ignoring case.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.common.Country
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   Country.fromAlpha3Code("FRA") shouldBe Country.FRANCE
     *   Country.fromAlpha3Code("fra") shouldBe Country.FRANCE
     *   Country.fromAlpha3Code("invalid") shouldBe null
     * }
     * ```
     * <!--- KNIT example-common-Country-08.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromAlpha3Code(code: String): Country? =
      entries.firstOrNull { it.alpha3Code.equals(code, ignoreCase = true) }

    /**
     * Returns the country matching any of its names (English or local).
     * Matching is case-insensitive and ignores leading/trailing whitespace.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.common.Country
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   Country.fromName("Switzerland") shouldBe Country.SWITZERLAND
     *   Country.fromName("Schweiz") shouldBe Country.SWITZERLAND
     *   Country.fromName("Deutschland") shouldBe Country.GERMANY
     *   Country.fromName(" France ") shouldBe Country.FRANCE
     *   Country.fromName("invalid") shouldBe null
     * }
     * ```
     * <!--- KNIT example-common-Country-09.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromName(name: String): Country? {
      val normalizedName = name.trim().lowercase()
      return entries.firstOrNull { country ->
        listOf(
          country.englishName,
          country.localLanguageName,
        ).any { it.trim().lowercase() == normalizedName }
      }
    }
  }
}
