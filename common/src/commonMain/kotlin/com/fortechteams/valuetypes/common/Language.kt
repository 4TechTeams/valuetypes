package com.fortechteams.valuetypes.common

/**
 * Represents languages with their standardized codes, native names, and script directions.
 *
 * <!--- TEST_NAME CommonLanguageKnitTest -->
 *
 * ## Example
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.common.Language
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   val german = Language.GERMAN
 *
 *   german.twoLetterCode shouldBe "de"
 *   german.threeLetterCode shouldBe "deu"
 *   german.nativeName shouldBe "Deutsch"
 *   german.englishName shouldBe "German"
 *   german.scriptDirection shouldBe Language.ScriptDirection.LTR
 *
 *   val arabic = Language.ARABIC
 *   arabic.scriptDirection shouldBe Language.ScriptDirection.RTL
 * }
 * ```
 * <!--- KNIT example-common-Language-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 */
enum class Language(
  val twoLetterCode: String,
  val threeLetterCode: String,
  val nativeName: String,
  val englishName: String,
  val scriptDirection: ScriptDirection,
) {

  AFAR(
    twoLetterCode = "aa",
    threeLetterCode = "aar",
    nativeName = "Afaraf",
    englishName = "Afar",
    scriptDirection = ScriptDirection.LTR
  ),

  AFRIKAANS(
    twoLetterCode = "af",
    threeLetterCode = "afr",
    nativeName = "Afrikaans",
    englishName = "Afrikaans",
    scriptDirection = ScriptDirection.LTR
  ),

  AKAN(
    twoLetterCode = "ak",
    threeLetterCode = "aka",
    nativeName = "Akan",
    englishName = "Akan",
    scriptDirection = ScriptDirection.LTR
  ),

  ALBANIAN(
    twoLetterCode = "sq",
    threeLetterCode = "sqi",
    nativeName = "Shqip",
    englishName = "Albanian",
    scriptDirection = ScriptDirection.LTR
  ),

  AMHARIC(
    twoLetterCode = "am",
    threeLetterCode = "amh",
    nativeName = "አማርኛ",
    englishName = "Amharic",
    scriptDirection = ScriptDirection.LTR
  ),

  ARABIC(
    twoLetterCode = "ar",
    threeLetterCode = "ara",
    nativeName = "العربية",
    englishName = "Arabic",
    scriptDirection = ScriptDirection.RTL
  ),

  ARMENIAN(
    twoLetterCode = "hy",
    threeLetterCode = "hye",
    nativeName = "Հայերեն",
    englishName = "Armenian",
    scriptDirection = ScriptDirection.LTR
  ),

  ASSAMESE(
    twoLetterCode = "as",
    threeLetterCode = "asm",
    nativeName = "অসমীয়া",
    englishName = "Assamese",
    scriptDirection = ScriptDirection.LTR
  ),

  AYMARA(
    twoLetterCode = "ay",
    threeLetterCode = "aym",
    nativeName = "Aymar aru",
    englishName = "Aymara",
    scriptDirection = ScriptDirection.LTR
  ),

  AZERBAIJANI(
    twoLetterCode = "az",
    threeLetterCode = "aze",
    nativeName = "Azərbaycan dili",
    englishName = "Azerbaijani",
    scriptDirection = ScriptDirection.LTR
  ),

  BAMBARA(
    twoLetterCode = "bm",
    threeLetterCode = "bam",
    nativeName = "Bamanankan",
    englishName = "Bambara",
    scriptDirection = ScriptDirection.LTR
  ),

  BASHKIR(
    twoLetterCode = "ba",
    threeLetterCode = "bak",
    nativeName = "Башҡорт теле",
    englishName = "Bashkir",
    scriptDirection = ScriptDirection.LTR
  ),

  BASQUE(
    twoLetterCode = "eu",
    threeLetterCode = "eus",
    nativeName = "Euskara",
    englishName = "Basque",
    scriptDirection = ScriptDirection.LTR
  ),

  BELARUSIAN(
    twoLetterCode = "be",
    threeLetterCode = "bel",
    nativeName = "Беларуская",
    englishName = "Belarusian",
    scriptDirection = ScriptDirection.LTR
  ),

  BENGALI(
    twoLetterCode = "bn",
    threeLetterCode = "ben",
    nativeName = "বাংলা",
    englishName = "Bengali",
    scriptDirection = ScriptDirection.LTR
  ),

  BISLAMA(
    twoLetterCode = "bi",
    threeLetterCode = "bis",
    nativeName = "Bislama",
    englishName = "Bislama",
    scriptDirection = ScriptDirection.LTR
  ),

  BOSNIAN(
    twoLetterCode = "bs",
    threeLetterCode = "bos",
    nativeName = "Bosanski",
    englishName = "Bosnian",
    scriptDirection = ScriptDirection.LTR
  ),

  BRETON(
    twoLetterCode = "br",
    threeLetterCode = "bre",
    nativeName = "Brezhoneg",
    englishName = "Breton",
    scriptDirection = ScriptDirection.LTR
  ),

  BULGARIAN(
    twoLetterCode = "bg",
    threeLetterCode = "bul",
    nativeName = "Български",
    englishName = "Bulgarian",
    scriptDirection = ScriptDirection.LTR
  ),

  BURMESE(
    twoLetterCode = "my",
    threeLetterCode = "mya",
    nativeName = "ဗမာစာ",
    englishName = "Burmese",
    scriptDirection = ScriptDirection.LTR
  ),

  CATALAN(
    twoLetterCode = "ca",
    threeLetterCode = "cat",
    nativeName = "Català",
    englishName = "Catalan",
    scriptDirection = ScriptDirection.LTR
  ),

  CHAMORRO(
    twoLetterCode = "ch",
    threeLetterCode = "cha",
    nativeName = "Chamoru",
    englishName = "Chamorro",
    scriptDirection = ScriptDirection.LTR
  ),

  CHECHEN(
    twoLetterCode = "ce",
    threeLetterCode = "che",
    nativeName = "Нохчийн мотт",
    englishName = "Chechen",
    scriptDirection = ScriptDirection.LTR
  ),

  CHICHEWA(
    twoLetterCode = "ny",
    threeLetterCode = "nya",
    nativeName = "Chichewa",
    englishName = "Chichewa",
    scriptDirection = ScriptDirection.LTR
  ),

  CHINESE(
    twoLetterCode = "zh",
    threeLetterCode = "zho",
    nativeName = "中文",
    englishName = "Chinese",
    scriptDirection = ScriptDirection.LTR
  ),

  CHUVASH(
    twoLetterCode = "cv",
    threeLetterCode = "chv",
    nativeName = "Чӑваш чӗлхи",
    englishName = "Chuvash",
    scriptDirection = ScriptDirection.LTR
  ),

  CORNISH(
    twoLetterCode = "kw",
    threeLetterCode = "cor",
    nativeName = "Kernewek",
    englishName = "Cornish",
    scriptDirection = ScriptDirection.LTR
  ),

  CORSICAN(
    twoLetterCode = "co",
    threeLetterCode = "cos",
    nativeName = "Corsu",
    englishName = "Corsican",
    scriptDirection = ScriptDirection.LTR
  ),

  CREE(
    twoLetterCode = "cr",
    threeLetterCode = "cre",
    nativeName = "ᓀᐦᐃᔭᐍᐏᐣ",
    englishName = "Cree",
    scriptDirection = ScriptDirection.LTR
  ),

  CROATIAN(
    twoLetterCode = "hr",
    threeLetterCode = "hrv",
    nativeName = "Hrvatski",
    englishName = "Croatian",
    scriptDirection = ScriptDirection.LTR
  ),

  CZECH(
    twoLetterCode = "cs",
    threeLetterCode = "ces",
    nativeName = "Čeština",
    englishName = "Czech",
    scriptDirection = ScriptDirection.LTR
  ),

  DANISH(
    twoLetterCode = "da",
    threeLetterCode = "dan",
    nativeName = "Dansk",
    englishName = "Danish",
    scriptDirection = ScriptDirection.LTR
  ),

  DIVEHI(
    twoLetterCode = "dv",
    threeLetterCode = "div",
    nativeName = "ދިވެހި",
    englishName = "Divehi",
    scriptDirection = ScriptDirection.RTL
  ),

  DUTCH(
    twoLetterCode = "nl",
    threeLetterCode = "nld",
    nativeName = "Nederlands",
    englishName = "Dutch",
    scriptDirection = ScriptDirection.LTR
  ),

  DZONGKHA(
    twoLetterCode = "dz",
    threeLetterCode = "dzo",
    nativeName = "རྫོང་ཁ",
    englishName = "Dzongkha",
    scriptDirection = ScriptDirection.LTR
  ),

  ENGLISH(
    twoLetterCode = "en",
    threeLetterCode = "eng",
    nativeName = "English",
    englishName = "English",
    scriptDirection = ScriptDirection.LTR
  ),

  ESPERANTO(
    twoLetterCode = "eo",
    threeLetterCode = "epo",
    nativeName = "Esperanto",
    englishName = "Esperanto",
    scriptDirection = ScriptDirection.LTR
  ),

  ESTONIAN(
    twoLetterCode = "et",
    threeLetterCode = "est",
    nativeName = "Eesti",
    englishName = "Estonian",
    scriptDirection = ScriptDirection.LTR
  ),

  EWE(
    twoLetterCode = "ee",
    threeLetterCode = "ewe",
    nativeName = "Eʋegbe",
    englishName = "Ewe",
    scriptDirection = ScriptDirection.LTR
  ),

  FAROESE(
    twoLetterCode = "fo",
    threeLetterCode = "fao",
    nativeName = "Føroyskt",
    englishName = "Faroese",
    scriptDirection = ScriptDirection.LTR
  ),

  FIJIAN(
    twoLetterCode = "fj",
    threeLetterCode = "fij",
    nativeName = "Vosa Vakaviti",
    englishName = "Fijian",
    scriptDirection = ScriptDirection.LTR
  ),

  FINNISH(
    twoLetterCode = "fi",
    threeLetterCode = "fin",
    nativeName = "Suomi",
    englishName = "Finnish",
    scriptDirection = ScriptDirection.LTR
  ),

  FRENCH(
    twoLetterCode = "fr",
    threeLetterCode = "fra",
    nativeName = "Français",
    englishName = "French",
    scriptDirection = ScriptDirection.LTR
  ),

  FULAH(
    twoLetterCode = "ff",
    threeLetterCode = "ful",
    nativeName = "Fulfulde",
    englishName = "Fulah",
    scriptDirection = ScriptDirection.LTR
  ),

  GALICIAN(
    twoLetterCode = "gl",
    threeLetterCode = "glg",
    nativeName = "Galego",
    englishName = "Galician",
    scriptDirection = ScriptDirection.LTR
  ),

  GEORGIAN(
    twoLetterCode = "ka",
    threeLetterCode = "kat",
    nativeName = "ქართული",
    englishName = "Georgian",
    scriptDirection = ScriptDirection.LTR
  ),

  GERMAN(
    twoLetterCode = "de",
    threeLetterCode = "deu",
    nativeName = "Deutsch",
    englishName = "German",
    scriptDirection = ScriptDirection.LTR
  ),

  GREEK(
    twoLetterCode = "el",
    threeLetterCode = "ell",
    nativeName = "Ελληνικά",
    englishName = "Greek",
    scriptDirection = ScriptDirection.LTR
  ),

  GUARANI(
    twoLetterCode = "gn",
    threeLetterCode = "grn",
    nativeName = "Avañe'ẽ",
    englishName = "Guarani",
    scriptDirection = ScriptDirection.LTR
  ),

  GUJARATI(
    twoLetterCode = "gu",
    threeLetterCode = "guj",
    nativeName = "ગુજરાતી",
    englishName = "Gujarati",
    scriptDirection = ScriptDirection.LTR
  ),

  HAITIAN(
    twoLetterCode = "ht",
    threeLetterCode = "hat",
    nativeName = "Kreyòl ayisyen",
    englishName = "Haitian",
    scriptDirection = ScriptDirection.LTR
  ),

  HAUSA(
    twoLetterCode = "ha",
    threeLetterCode = "hau",
    nativeName = "هَوُسَ",
    englishName = "Hausa",
    scriptDirection = ScriptDirection.LTR
  ),

  HEBREW(
    twoLetterCode = "he",
    threeLetterCode = "heb",
    nativeName = "עברית",
    englishName = "Hebrew",
    scriptDirection = ScriptDirection.RTL
  ),

  HERERO(
    twoLetterCode = "hz",
    threeLetterCode = "her",
    nativeName = "Otjiherero",
    englishName = "Herero",
    scriptDirection = ScriptDirection.LTR
  ),

  HINDI(
    twoLetterCode = "hi",
    threeLetterCode = "hin",
    nativeName = "हिन्दी",
    englishName = "Hindi",
    scriptDirection = ScriptDirection.LTR
  ),

  HIRI_MOTU(
    twoLetterCode = "ho",
    threeLetterCode = "hmo",
    nativeName = "Hiri Motu",
    englishName = "Hiri Motu",
    scriptDirection = ScriptDirection.LTR
  ),

  HUNGARIAN(
    twoLetterCode = "hu",
    threeLetterCode = "hun",
    nativeName = "Magyar",
    englishName = "Hungarian",
    scriptDirection = ScriptDirection.LTR
  ),

  ICELANDIC(
    twoLetterCode = "is",
    threeLetterCode = "isl",
    nativeName = "Íslenska",
    englishName = "Icelandic",
    scriptDirection = ScriptDirection.LTR
  ),

  IDO(
    twoLetterCode = "io",
    threeLetterCode = "ido",
    nativeName = "Ido",
    englishName = "Ido",
    scriptDirection = ScriptDirection.LTR
  ),

  IGBO(
    twoLetterCode = "ig",
    threeLetterCode = "ibo",
    nativeName = "Igbo",
    englishName = "Igbo",
    scriptDirection = ScriptDirection.LTR
  ),

  INDONESIAN(
    twoLetterCode = "id",
    threeLetterCode = "ind",
    nativeName = "Bahasa Indonesia",
    englishName = "Indonesian",
    scriptDirection = ScriptDirection.LTR
  ),

  INTERLINGUA(
    twoLetterCode = "ia",
    threeLetterCode = "ina",
    nativeName = "Interlingua",
    englishName = "Interlingua",
    scriptDirection = ScriptDirection.LTR
  ),

  INTERLINGUE(
    twoLetterCode = "ie",
    threeLetterCode = "ile",
    nativeName = "Interlingue",
    englishName = "Interlingue",
    scriptDirection = ScriptDirection.LTR
  ),

  INUKTITUT(
    twoLetterCode = "iu",
    threeLetterCode = "iku",
    nativeName = "ᐃᓄᒃᑎᑐᑦ",
    englishName = "Inuktitut",
    scriptDirection = ScriptDirection.LTR
  ),

  INUPIAQ(
    twoLetterCode = "ik",
    threeLetterCode = "ipk",
    nativeName = "Iñupiaq",
    englishName = "Inupiaq",
    scriptDirection = ScriptDirection.LTR
  ),

  IRISH(
    twoLetterCode = "ga",
    threeLetterCode = "gle",
    nativeName = "Gaeilge",
    englishName = "Irish",
    scriptDirection = ScriptDirection.LTR
  ),

  ITALIAN(
    twoLetterCode = "it",
    threeLetterCode = "ita",
    nativeName = "Italiano",
    englishName = "Italian",
    scriptDirection = ScriptDirection.LTR
  ),

  JAPANESE(
    twoLetterCode = "ja",
    threeLetterCode = "jpn",
    nativeName = "日本語",
    englishName = "Japanese",
    scriptDirection = ScriptDirection.LTR
  ),

  JAVANESE(
    twoLetterCode = "jv",
    threeLetterCode = "jav",
    nativeName = "Basa Jawa",
    englishName = "Javanese",
    scriptDirection = ScriptDirection.LTR
  ),

  KALAALLISUT(
    twoLetterCode = "kl",
    threeLetterCode = "kal",
    nativeName = "Kalaallisut",
    englishName = "Kalaallisut",
    scriptDirection = ScriptDirection.LTR
  ),

  KANNADA(
    twoLetterCode = "kn",
    threeLetterCode = "kan",
    nativeName = "ಕನ್ನಡ",
    englishName = "Kannada",
    scriptDirection = ScriptDirection.LTR
  ),

  KANURI(
    twoLetterCode = "kr",
    threeLetterCode = "kau",
    nativeName = "Kanuri",
    englishName = "Kanuri",
    scriptDirection = ScriptDirection.LTR
  ),

  KASHMIRI(
    twoLetterCode = "ks",
    threeLetterCode = "kas",
    nativeName = "कश्मीरी",
    englishName = "Kashmiri",
    scriptDirection = ScriptDirection.LTR
  ),

  KAZAKH(
    twoLetterCode = "kk",
    threeLetterCode = "kaz",
    nativeName = "Қазақ тілі",
    englishName = "Kazakh",
    scriptDirection = ScriptDirection.LTR
  ),

  KHMER(
    twoLetterCode = "km",
    threeLetterCode = "khm",
    nativeName = "ខ្មែរ",
    englishName = "Khmer",
    scriptDirection = ScriptDirection.LTR
  ),

  KIKUYU(
    twoLetterCode = "ki",
    threeLetterCode = "kik",
    nativeName = "Gĩkũyũ",
    englishName = "Kikuyu",
    scriptDirection = ScriptDirection.LTR
  ),

  KINYARWANDA(
    twoLetterCode = "rw",
    threeLetterCode = "kin",
    nativeName = "Ikinyarwanda",
    englishName = "Kinyarwanda",
    scriptDirection = ScriptDirection.LTR
  ),

  KIRGHIZ(
    twoLetterCode = "ky",
    threeLetterCode = "kir",
    nativeName = "Кыргызча",
    englishName = "Kirghiz",
    scriptDirection = ScriptDirection.LTR
  ),

  KOMI(
    twoLetterCode = "kv",
    threeLetterCode = "kom",
    nativeName = "Коми кыв",
    englishName = "Komi",
    scriptDirection = ScriptDirection.LTR
  ),

  KONGO(
    twoLetterCode = "kg",
    threeLetterCode = "kon",
    nativeName = "Kikongo",
    englishName = "Kongo",
    scriptDirection = ScriptDirection.LTR
  ),

  KOREAN(
    twoLetterCode = "ko",
    threeLetterCode = "kor",
    nativeName = "한국어",
    englishName = "Korean",
    scriptDirection = ScriptDirection.LTR
  ),

  KURDISH(
    twoLetterCode = "ku",
    threeLetterCode = "kur",
    nativeName = "Kurdî",
    englishName = "Kurdish",
    scriptDirection = ScriptDirection.RTL
  ),

  KUANYAMA(
    twoLetterCode = "kj",
    threeLetterCode = "kua",
    nativeName = "Kuanyama",
    englishName = "Kuanyama",
    scriptDirection = ScriptDirection.LTR
  ),

  LAO(
    twoLetterCode = "lo",
    threeLetterCode = "lao",
    nativeName = "ພາສາລາວ",
    englishName = "Lao",
    scriptDirection = ScriptDirection.LTR
  ),

  LATIN(
    twoLetterCode = "la",
    threeLetterCode = "lat",
    nativeName = "Latina",
    englishName = "Latin",
    scriptDirection = ScriptDirection.LTR
  ),

  LATVIAN(
    twoLetterCode = "lv",
    threeLetterCode = "lav",
    nativeName = "Latviešu",
    englishName = "Latvian",
    scriptDirection = ScriptDirection.LTR
  ),

  LIMBURGISH(
    twoLetterCode = "li",
    threeLetterCode = "lim",
    nativeName = "Limburgs",
    englishName = "Limburgish",
    scriptDirection = ScriptDirection.LTR
  ),

  LINGALA(
    twoLetterCode = "ln",
    threeLetterCode = "lin",
    nativeName = "Lingála",
    englishName = "Lingala",
    scriptDirection = ScriptDirection.LTR
  ),

  LITHUANIAN(
    twoLetterCode = "lt",
    threeLetterCode = "lit",
    nativeName = "Lietuvių",
    englishName = "Lithuanian",
    scriptDirection = ScriptDirection.LTR
  ),

  LUBA_KATANGA(
    twoLetterCode = "lu",
    threeLetterCode = "lub",
    nativeName = "Kiluba",
    englishName = "Luba-Katanga",
    scriptDirection = ScriptDirection.LTR
  ),

  LUXEMBOURGISH(
    twoLetterCode = "lb",
    threeLetterCode = "ltz",
    nativeName = "Lëtzebuergesch",
    englishName = "Luxembourgish",
    scriptDirection = ScriptDirection.LTR
  ),

  MACEDONIAN(
    twoLetterCode = "mk",
    threeLetterCode = "mkd",
    nativeName = "Македонски",
    englishName = "Macedonian",
    scriptDirection = ScriptDirection.LTR
  ),

  MALAGASY(
    twoLetterCode = "mg",
    threeLetterCode = "mlg",
    nativeName = "Malagasy",
    englishName = "Malagasy",
    scriptDirection = ScriptDirection.LTR
  ),

  MALAY(
    twoLetterCode = "ms",
    threeLetterCode = "msa",
    nativeName = "Bahasa Melayu",
    englishName = "Malay",
    scriptDirection = ScriptDirection.LTR
  ),

  MALAYALAM(
    twoLetterCode = "ml",
    threeLetterCode = "mal",
    nativeName = "മലയാളം",
    englishName = "Malayalam",
    scriptDirection = ScriptDirection.LTR
  ),

  MALTESE(
    twoLetterCode = "mt",
    threeLetterCode = "mlt",
    nativeName = "Malti",
    englishName = "Maltese",
    scriptDirection = ScriptDirection.LTR
  ),

  MANX(
    twoLetterCode = "gv",
    threeLetterCode = "glv",
    nativeName = "Gaelg",
    englishName = "Manx",
    scriptDirection = ScriptDirection.LTR
  ),

  MAORI(
    twoLetterCode = "mi",
    threeLetterCode = "mri",
    nativeName = "Te Reo Māori",
    englishName = "Maori",
    scriptDirection = ScriptDirection.LTR
  ),

  MARATHI(
    twoLetterCode = "mr",
    threeLetterCode = "mar",
    nativeName = "मराठी",
    englishName = "Marathi",
    scriptDirection = ScriptDirection.LTR
  ),

  MARSHALLESE(
    twoLetterCode = "mh",
    threeLetterCode = "mah",
    nativeName = "Kajin M̧ajeļ",
    englishName = "Marshallese",
    scriptDirection = ScriptDirection.LTR
  ),

  MONGOLIAN(
    twoLetterCode = "mn",
    threeLetterCode = "mon",
    nativeName = "Монгол",
    englishName = "Mongolian",
    scriptDirection = ScriptDirection.LTR
  ),

  NAURU(
    twoLetterCode = "na",
    threeLetterCode = "nau",
    nativeName = "Dorerin Naoero",
    englishName = "Nauru",
    scriptDirection = ScriptDirection.LTR
  ),

  NAVAJO(
    twoLetterCode = "nv",
    threeLetterCode = "nav",
    nativeName = "Diné bizaad",
    englishName = "Navajo",
    scriptDirection = ScriptDirection.LTR
  ),

  NDONGA(
    twoLetterCode = "ng",
    threeLetterCode = "ndo",
    nativeName = "Owambo",
    englishName = "Ndonga",
    scriptDirection = ScriptDirection.LTR
  ),

  NEPALI(
    twoLetterCode = "ne",
    threeLetterCode = "nep",
    nativeName = "नेपाली",
    englishName = "Nepali",
    scriptDirection = ScriptDirection.LTR
  ),

  NORTHERN_SAMI(
    twoLetterCode = "se",
    threeLetterCode = "sme",
    nativeName = "Davvisámegiella",
    englishName = "Northern Sami",
    scriptDirection = ScriptDirection.LTR
  ),

  NORWEGIAN(
    twoLetterCode = "no",
    threeLetterCode = "nor",
    nativeName = "Norsk",
    englishName = "Norwegian",
    scriptDirection = ScriptDirection.LTR
  ),

  NORWEGIAN_BOKMAL(
    twoLetterCode = "nb",
    threeLetterCode = "nob",
    nativeName = "Norsk bokmål",
    englishName = "Norwegian Bokmål",
    scriptDirection = ScriptDirection.LTR
  ),

  NORWEGIAN_NYNORSK(
    twoLetterCode = "nn",
    threeLetterCode = "nno",
    nativeName = "Norsk nynorsk",
    englishName = "Norwegian Nynorsk",
    scriptDirection = ScriptDirection.LTR
  ),

  OCCITAN(
    twoLetterCode = "oc",
    threeLetterCode = "oci",
    nativeName = "Occitan",
    englishName = "Occitan",
    scriptDirection = ScriptDirection.LTR
  ),

  OJIBWA(
    twoLetterCode = "oj",
    threeLetterCode = "oji",
    nativeName = "ᐊᓂᔑᓈᐯᒧᐎᓐ",
    englishName = "Ojibwa",
    scriptDirection = ScriptDirection.LTR
  ),

  ORIYA(
    twoLetterCode = "or",
    threeLetterCode = "ori",
    nativeName = "ଓଡ଼ିଆ",
    englishName = "Oriya",
    scriptDirection = ScriptDirection.LTR
  ),

  OROMO(
    twoLetterCode = "om",
    threeLetterCode = "orm",
    nativeName = "Afaan Oromoo",
    englishName = "Oromo",
    scriptDirection = ScriptDirection.LTR
  ),

  OSSETIAN(
    twoLetterCode = "os",
    threeLetterCode = "oss",
    nativeName = "Ирон æвзаг",
    englishName = "Ossetian",
    scriptDirection = ScriptDirection.LTR
  ),

  PALI(
    twoLetterCode = "pi",
    threeLetterCode = "pli",
    nativeName = "पालि",
    englishName = "Pali",
    scriptDirection = ScriptDirection.LTR
  ),

  PASHTO(
    twoLetterCode = "ps",
    threeLetterCode = "pus",
    nativeName = "پښتو",
    englishName = "Pashto",
    scriptDirection = ScriptDirection.RTL
  ),

  PERSIAN(
    twoLetterCode = "fa",
    threeLetterCode = "fas",
    nativeName = "فارسی",
    englishName = "Persian",
    scriptDirection = ScriptDirection.RTL
  ),

  POLISH(
    twoLetterCode = "pl",
    threeLetterCode = "pol",
    nativeName = "Polski",
    englishName = "Polish",
    scriptDirection = ScriptDirection.LTR
  ),

  PORTUGUESE(
    twoLetterCode = "pt",
    threeLetterCode = "por",
    nativeName = "Português",
    englishName = "Portuguese",
    scriptDirection = ScriptDirection.LTR
  ),

  PUNJABI(
    twoLetterCode = "pa",
    threeLetterCode = "pan",
    nativeName = "ਪੰਜਾਬੀ",
    englishName = "Punjabi",
    scriptDirection = ScriptDirection.LTR
  ),

  QUECHUA(
    twoLetterCode = "qu",
    threeLetterCode = "que",
    nativeName = "Runa Simi",
    englishName = "Quechua",
    scriptDirection = ScriptDirection.LTR
  ),

  ROMANIAN(
    twoLetterCode = "ro",
    threeLetterCode = "ron",
    nativeName = "Română",
    englishName = "Romanian",
    scriptDirection = ScriptDirection.LTR
  ),

  ROMANSH(
    twoLetterCode = "rm",
    threeLetterCode = "roh",
    nativeName = "Rumantsch",
    englishName = "Romansh",
    scriptDirection = ScriptDirection.LTR
  ),

  RUNDI(
    twoLetterCode = "rn",
    threeLetterCode = "run",
    nativeName = "Ikirundi",
    englishName = "Rundi",
    scriptDirection = ScriptDirection.LTR
  ),

  RUSSIAN(
    twoLetterCode = "ru",
    threeLetterCode = "rus",
    nativeName = "Русский",
    englishName = "Russian",
    scriptDirection = ScriptDirection.LTR
  ),

  SAMOAN(
    twoLetterCode = "sm",
    threeLetterCode = "smo",
    nativeName = "Gagana fa'a Samoa",
    englishName = "Samoan",
    scriptDirection = ScriptDirection.LTR
  ),

  SANGO(
    twoLetterCode = "sg",
    threeLetterCode = "sag",
    nativeName = "Yângâ tî sängö",
    englishName = "Sango",
    scriptDirection = ScriptDirection.LTR
  ),

  SANSKRIT(
    twoLetterCode = "sa",
    threeLetterCode = "san",
    nativeName = "संस्कृतम्",
    englishName = "Sanskrit",
    scriptDirection = ScriptDirection.LTR
  ),

  SARDINIAN(
    twoLetterCode = "sc",
    threeLetterCode = "srd",
    nativeName = "Sardu",
    englishName = "Sardinian",
    scriptDirection = ScriptDirection.LTR
  ),

  SCOTTISH_GAELIC(
    twoLetterCode = "gd",
    threeLetterCode = "gla",
    nativeName = "Gàidhlig",
    englishName = "Scottish Gaelic",
    scriptDirection = ScriptDirection.LTR
  ),

  SERBIAN(
    twoLetterCode = "sr",
    threeLetterCode = "srp",
    nativeName = "Српски",
    englishName = "Serbian",
    scriptDirection = ScriptDirection.LTR
  ),

  SHONA(
    twoLetterCode = "sn",
    threeLetterCode = "sna",
    nativeName = "chiShona",
    englishName = "Shona",
    scriptDirection = ScriptDirection.LTR
  ),

  SINDHI(
    twoLetterCode = "sd",
    threeLetterCode = "snd",
    nativeName = "سنڌي",
    englishName = "Sindhi",
    scriptDirection = ScriptDirection.RTL
  ),

  SINHALA(
    twoLetterCode = "si",
    threeLetterCode = "sin",
    nativeName = "සිංහල",
    englishName = "Sinhala",
    scriptDirection = ScriptDirection.LTR
  ),

  SLOVAK(
    twoLetterCode = "sk",
    threeLetterCode = "slk",
    nativeName = "Slovenčina",
    englishName = "Slovak",
    scriptDirection = ScriptDirection.LTR
  ),

  SLOVENIAN(
    twoLetterCode = "sl",
    threeLetterCode = "slv",
    nativeName = "Slovenščina",
    englishName = "Slovenian",
    scriptDirection = ScriptDirection.LTR
  ),

  SOMALI(
    twoLetterCode = "so",
    threeLetterCode = "som",
    nativeName = "Soomaaliga",
    englishName = "Somali",
    scriptDirection = ScriptDirection.LTR
  ),

  SOUTHERN_NDEBELE(
    twoLetterCode = "nr",
    threeLetterCode = "nbl",
    nativeName = "isiNdebele",
    englishName = "Southern Ndebele",
    scriptDirection = ScriptDirection.LTR
  ),

  SOUTHERN_SOTHO(
    twoLetterCode = "st",
    threeLetterCode = "sot",
    nativeName = "Sesotho",
    englishName = "Southern Sotho",
    scriptDirection = ScriptDirection.LTR
  ),

  SPANISH(
    twoLetterCode = "es",
    threeLetterCode = "spa",
    nativeName = "Español",
    englishName = "Spanish",
    scriptDirection = ScriptDirection.LTR
  ),

  SUNDANESE(
    twoLetterCode = "su",
    threeLetterCode = "sun",
    nativeName = "Basa Sunda",
    englishName = "Sundanese",
    scriptDirection = ScriptDirection.LTR
  ),

  SWAHILI(
    twoLetterCode = "sw",
    threeLetterCode = "swa",
    nativeName = "Kiswahili",
    englishName = "Swahili",
    scriptDirection = ScriptDirection.LTR
  ),

  SWATI(
    twoLetterCode = "ss",
    threeLetterCode = "ssw",
    nativeName = "SiSwati",
    englishName = "Swati",
    scriptDirection = ScriptDirection.LTR
  ),

  SWEDISH(
    twoLetterCode = "sv",
    threeLetterCode = "swe",
    nativeName = "Svenska",
    englishName = "Swedish",
    scriptDirection = ScriptDirection.LTR
  ),

  TAGALOG(
    twoLetterCode = "tl",
    threeLetterCode = "tgl",
    nativeName = "Tagalog",
    englishName = "Tagalog",
    scriptDirection = ScriptDirection.LTR
  ),

  TAHITIAN(
    twoLetterCode = "ty",
    threeLetterCode = "tah",
    nativeName = "Reo Tahiti",
    englishName = "Tahitian",
    scriptDirection = ScriptDirection.LTR
  ),

  TAJIK(
    twoLetterCode = "tg",
    threeLetterCode = "tgk",
    nativeName = "Тоҷикӣ",
    englishName = "Tajik",
    scriptDirection = ScriptDirection.LTR
  ),

  TAMIL(
    twoLetterCode = "ta",
    threeLetterCode = "tam",
    nativeName = "தமிழ்",
    englishName = "Tamil",
    scriptDirection = ScriptDirection.LTR
  ),

  TATAR(
    twoLetterCode = "tt",
    threeLetterCode = "tat",
    nativeName = "Татарча",
    englishName = "Tatar",
    scriptDirection = ScriptDirection.LTR
  ),

  TELUGU(
    twoLetterCode = "te",
    threeLetterCode = "tel",
    nativeName = "తెలుగు",
    englishName = "Telugu",
    scriptDirection = ScriptDirection.LTR
  ),

  THAI(
    twoLetterCode = "th",
    threeLetterCode = "tha",
    nativeName = "ไทย",
    englishName = "Thai",
    scriptDirection = ScriptDirection.LTR
  ),

  TIBETAN(
    twoLetterCode = "bo",
    threeLetterCode = "bod",
    nativeName = "བོད་ཡིག",
    englishName = "Tibetan",
    scriptDirection = ScriptDirection.LTR
  ),

  TIGRINYA(
    twoLetterCode = "ti",
    threeLetterCode = "tir",
    nativeName = "ትግርኛ",
    englishName = "Tigrinya",
    scriptDirection = ScriptDirection.LTR
  ),

  TONGA(
    twoLetterCode = "to",
    threeLetterCode = "ton",
    nativeName = "Lea faka-Tonga",
    englishName = "Tonga",
    scriptDirection = ScriptDirection.LTR
  ),

  TSONGA(
    twoLetterCode = "ts",
    threeLetterCode = "tso",
    nativeName = "Xitsonga",
    englishName = "Tsonga",
    scriptDirection = ScriptDirection.LTR
  ),

  TSWANA(
    twoLetterCode = "tn",
    threeLetterCode = "tsn",
    nativeName = "Setswana",
    englishName = "Tswana",
    scriptDirection = ScriptDirection.LTR
  ),

  TURKISH(
    twoLetterCode = "tr",
    threeLetterCode = "tur",
    nativeName = "Türkçe",
    englishName = "Turkish",
    scriptDirection = ScriptDirection.LTR
  ),

  TURKMEN(
    twoLetterCode = "tk",
    threeLetterCode = "tuk",
    nativeName = "Türkmen",
    englishName = "Turkmen",
    scriptDirection = ScriptDirection.LTR
  ),

  TWI(
    twoLetterCode = "tw",
    threeLetterCode = "twi",
    nativeName = "Twi",
    englishName = "Twi",
    scriptDirection = ScriptDirection.LTR
  ),

  UIGHUR(
    twoLetterCode = "ug",
    threeLetterCode = "uig",
    nativeName = "ئۇيغۇرچە",
    englishName = "Uighur",
    scriptDirection = ScriptDirection.RTL
  ),

  UKRAINIAN(
    twoLetterCode = "uk",
    threeLetterCode = "ukr",
    nativeName = "Українська",
    englishName = "Ukrainian",
    scriptDirection = ScriptDirection.LTR
  ),

  URDU(
    twoLetterCode = "ur",
    threeLetterCode = "urd",
    nativeName = "اردو",
    englishName = "Urdu",
    scriptDirection = ScriptDirection.RTL
  ),

  UZBEK(
    twoLetterCode = "uz",
    threeLetterCode = "uzb",
    nativeName = "O'zbek",
    englishName = "Uzbek",
    scriptDirection = ScriptDirection.LTR
  ),

  VENDA(
    twoLetterCode = "ve",
    threeLetterCode = "ven",
    nativeName = "Tshivenḓa",
    englishName = "Venda",
    scriptDirection = ScriptDirection.LTR
  ),

  VIETNAMESE(
    twoLetterCode = "vi",
    threeLetterCode = "vie",
    nativeName = "Tiếng Việt",
    englishName = "Vietnamese",
    scriptDirection = ScriptDirection.LTR
  ),

  VOLAPUK(
    twoLetterCode = "vo",
    threeLetterCode = "vol",
    nativeName = "Volapük",
    englishName = "Volapük",
    scriptDirection = ScriptDirection.LTR
  ),

  WALLOON(
    twoLetterCode = "wa",
    threeLetterCode = "wln",
    nativeName = "Walon",
    englishName = "Walloon",
    scriptDirection = ScriptDirection.LTR
  ),

  WELSH(
    twoLetterCode = "cy",
    threeLetterCode = "cym",
    nativeName = "Cymraeg",
    englishName = "Welsh",
    scriptDirection = ScriptDirection.LTR
  ),

  WESTERN_FRISIAN(
    twoLetterCode = "fy",
    threeLetterCode = "fry",
    nativeName = "Frysk",
    englishName = "Western Frisian",
    scriptDirection = ScriptDirection.LTR
  ),

  WOLOF(
    twoLetterCode = "wo",
    threeLetterCode = "wol",
    nativeName = "Wollof",
    englishName = "Wolof",
    scriptDirection = ScriptDirection.LTR
  ),

  XHOSA(
    twoLetterCode = "xh",
    threeLetterCode = "xho",
    nativeName = "isiXhosa",
    englishName = "Xhosa",
    scriptDirection = ScriptDirection.LTR
  ),

  YIDDISH(
    twoLetterCode = "yi",
    threeLetterCode = "yid",
    nativeName = "ייִדיש",
    englishName = "Yiddish",
    scriptDirection = ScriptDirection.RTL
  ),

  YORUBA(
    twoLetterCode = "yo",
    threeLetterCode = "yor",
    nativeName = "Yorùbá",
    englishName = "Yoruba",
    scriptDirection = ScriptDirection.LTR
  ),

  ZHUANG(
    twoLetterCode = "za",
    threeLetterCode = "zha",
    nativeName = "Saɯ cueŋƅ",
    englishName = "Zhuang",
    scriptDirection = ScriptDirection.LTR
  ),

  ZULU(
    twoLetterCode = "zu",
    threeLetterCode = "zul",
    nativeName = "isiZulu",
    englishName = "Zulu",
    scriptDirection = ScriptDirection.LTR
  );

  /**
   * Represents the direction in which a language script is written.
   */
  enum class ScriptDirection {
    /** Left to Right script direction */
    LTR,

    /** Right to Left script direction */
    RTL
  }

  companion object {
    /**
     * Returns the language for the given ISO 639-1 two-letter code, ignoring case.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.common.Language
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   Language.fromTwoLetterCode("de") shouldBe Language.GERMAN
     *   Language.fromTwoLetterCode("DE") shouldBe Language.GERMAN
     *   Language.fromTwoLetterCode("xx") shouldBe null
     * }
     * ```
     * <!--- KNIT example-common-Language-02.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromTwoLetterCode(code: String): Language? = entries.find { it.twoLetterCode.equals(code, ignoreCase = true) }

    /**
     * Returns the language for the given ISO 639-2/T three-letter code, ignoring case.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.common.Language
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   Language.fromThreeLetterCode("ara") shouldBe Language.ARABIC
     *   Language.fromThreeLetterCode("ARA") shouldBe Language.ARABIC
     *   Language.fromThreeLetterCode("xxx") shouldBe null
     * }
     * ```
     * <!--- KNIT example-common-Language-03.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromThreeLetterCode(code: String): Language? =
      entries.find { it.threeLetterCode.equals(code, ignoreCase = true) }

    /**
     * Returns the language matching its English name. Matching is case-insensitive
     * and ignores leading/trailing whitespace.
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.common.Language
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   Language.fromEnglishName("German") shouldBe Language.GERMAN
     *   Language.fromEnglishName(" Arabic ") shouldBe Language.ARABIC
     *   Language.fromEnglishName("invalid") shouldBe null
     * }
     * ```
     * <!--- KNIT example-common-Language-04.kt -->
     * <!--- TEST lines.isEmpty() -->
     */
    fun fromEnglishName(name: String): Language? =
      entries.find { it.englishName.equals(name.trim(), ignoreCase = true) }
  }
}
