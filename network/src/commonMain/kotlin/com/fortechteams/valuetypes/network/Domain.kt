package com.fortechteams.valuetypes.network

import com.fortechteams.valuetypes.network.exception.InvalidDomainException
import kotlin.jvm.JvmInline

/**
 * Represents a domain name in the Domain Name System (DNS) hierarchy.
 *
 * <!--- TEST_NAME NetworkDomainKnitTest -->
 *
 * This sealed interface provides a type-safe representation of different domain types:
 * - Top Level Domain (TLD)
 * - Apex Domain (also known as root domain)
 * - Subdomain
 *
 * ## Example
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.network.Domain
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   // Parse different types of domains
 *   val tld = Domain.parse("com")
 *   val apex = Domain.parse("example.com")
 *   val subdomain = Domain.parse("www.example.com")
 *
 *   // Convert to FQDN (Fully Qualified Domain Name)
 *   tld.toFQDN() shouldBe "com."
 *   apex.toFQDN() shouldBe "example.com."
 *   subdomain.toFQDN() shouldBe "www.example.com."
 *
 *   // Access domain parts
 *   (subdomain as Subdomain).apexDomain.toString() shouldBe "example.com"
 * }
 * ```
 * <!--- KNIT example-network-Domain-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 *
 * @since 0.3.0
 */
sealed interface Domain {

  /**
   * List of labels that make up this domain, from most specific to least specific
   */
  val labels: List<String>

  /**
   * Returns the Fully Qualified Domain Name (FQDN) representation.
   *
   * An FQDN includes the trailing dot to indicate the root of the DNS hierarchy.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.network.Domain
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   Domain.parse("www.example.com").toFQDN() shouldBe "www.example.com."
   * }
   * ```
   * <!--- KNIT example-network-Domain-02.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  fun toFQDN(): String = "${labels.joinToString(".")}."

  companion object {
    /**
     * Parses a string into the appropriate [Domain] type.
     *
     * The type of domain returned depends on the number of parts:
     * - 1 part: [TopLevelDomain] (e.g., "com")
     * - 2 parts: [ApexDomain] (e.g., "example.com")
     * - 3+ parts: [Subdomain] (e.g., "www.example.com")
     *
     * ```kotlin
     * import com.fortechteams.valuetypes.network.Domain
     * import com.fortechteams.valuetypes.network.TopLevelDomain
     * import com.fortechteams.valuetypes.network.ApexDomain
     * import com.fortechteams.valuetypes.network.Subdomain
     * import io.kotest.matchers.shouldBe
     *
     * fun test() {
     *   Domain.parse("com") shouldBe TopLevelDomain("com")
     *   Domain.parse("example.com") shouldBe ApexDomain("example", "com")
     *   Domain.parse("www.example.com") shouldBe
     *     Subdomain(listOf("www"), "example", "com")
     *
     *   // Invalid domains throw InvalidDomainException
     *   runCatching { Domain.parse("invalid..com") }.isFailure shouldBe true
     * }
     * ```
     * <!--- KNIT example-network-Domain-03.kt -->
     * <!--- TEST lines.isEmpty() -->
     *
     * @throws InvalidDomainException if the domain format is invalid
     */
    fun parse(domain: String): Domain {
      val parts = domain.trimEnd('.').split('.')
      return when (parts.size) {
        0 -> throw InvalidDomainException(domain, "Empty domain")
        1 -> TopLevelDomain(parts[0])
        2 -> ApexDomain(parts[0], parts[1])
        else -> Subdomain(
          labels = parts.dropLast(2),
          apexName = parts[parts.lastIndex - 1],
          tld = parts.last()
        )
      }
    }
  }
}

/**
 * Represents a Top Level Domain (TLD) in the DNS hierarchy.
 *
 * A TLD is the rightmost part of a domain name, such as "com", "org", or "net".
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.network.TopLevelDomain
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   val tld = TopLevelDomain("com")
 *   tld.toString() shouldBe "com"
 *   tld.toFQDN() shouldBe "com."
 *   tld.labels shouldBe listOf("com")
 * }
 * ```
 * <!--- KNIT example-network-TopLevelDomain-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 */
@JvmInline
value class TopLevelDomain(private val value: String) : Domain {
  init {
    if (!value.matches(Regex("^[a-zA-Z]{2,}$"))) {
      throw InvalidDomainException(value, "TLD must contain only letters and be at least 2 characters long")
    }
  }

  override val labels: List<String> get() = listOf(value)

  override fun toString(): String = labels.joinToString(".")

  operator fun component1(): String = value
}

/**
 * Represents an Apex Domain (also known as root domain) in the DNS hierarchy.
 *
 * An Apex Domain consists of a domain name and a TLD, such as "example.com".
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.network.ApexDomain
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   val apex = ApexDomain("example", "com")
 *   apex.name shouldBe "example"
 *   apex.tld shouldBe "com"
 *   apex.toString() shouldBe "example.com"
 *   apex.toFQDN() shouldBe "example.com."
 * }
 * ```
 * <!--- KNIT example-network-ApexDomain-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 */
@JvmInline
value class ApexDomain(private val parts: Pair<String, String>) : Domain {

  constructor(name: String, tld: String) :
    this(validateParts(name, tld))

  /** The domain name part (e.g., "example" in "example.com") */
  val name: String get() = parts.first

  /** The TLD part (e.g., "com" in "example.com") */
  val tld: String get() = parts.second

  override val labels: List<String> get() = listOf(name, tld)

  override fun toString(): String = labels.joinToString(".")

  operator fun component1(): String = name
  operator fun component2(): String = tld

  /**
   * Returns the parent domain (TLD) of this apex domain.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.network.ApexDomain
   * import com.fortechteams.valuetypes.network.TopLevelDomain
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val apex = ApexDomain("example", "com")
   *   val parent = apex.parent()
   *   parent shouldBe TopLevelDomain("com")
   * }
   * ```
   * <!--- KNIT example-network-ApexDomain-02.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  fun parent(): TopLevelDomain = TopLevelDomain(tld)

  companion object {
    private fun validateParts(name: String, tld: String): Pair<String, String> {
      if (!name.isValidLabel()) {
        throw InvalidDomainException(name, "Invalid domain name format")
      }
      if (!tld.matches(Regex("^[a-zA-Z]{2,}$"))) {
        throw InvalidDomainException(tld, "Invalid TLD format")
      }
      return name to tld
    }
  }
}

/**
 * Represents a Subdomain in the DNS hierarchy.
 *
 * A Subdomain consists of one or more labels followed by an apex domain,
 * such as "www.example.com" or "dev.staging.example.com".
 *
 * ```kotlin
 * import com.fortechteams.valuetypes.network.Subdomain
 * import io.kotest.matchers.shouldBe
 *
 * fun test() {
 *   val sub = Subdomain(listOf("www"), "example", "com")
 *
 *   // Access individual parts
 *   sub.subdomainLabels shouldBe listOf("www")
 *   sub.apexName shouldBe "example"
 *   sub.tld shouldBe "com"
 *
 *   // Get the apex domain
 *   sub.apexDomain.toString() shouldBe "example.com"
 *
 *   // Get full hierarchy
 *   val complex = Subdomain(listOf("dev", "staging"), "example", "com")
 *   complex.getSubdomainHierarchy().map { it.toString() } shouldBe listOf(
 *     "dev.staging.example.com",
 *     "staging.example.com"
 *   )
 * }
 * ```
 * <!--- KNIT example-network-Subdomain-01.kt -->
 * <!--- TEST lines.isEmpty() -->
 */
@JvmInline
value class Subdomain(private val parts: Triple<List<String>, String, String>) : Domain {
  constructor(
    labels: List<String>,
    apexName: String,
    tld: String
  ) : this(validateParts(labels, apexName, tld))

  /** The subdomain labels (e.g., ["www"] in "www.example.com") */
  val subdomainLabels: List<String> get() = parts.first

  /** The apex domain name (e.g., "example" in "www.example.com") */
  val apexName: String get() = parts.second

  /** The TLD (e.g., "com" in "www.example.com") */
  val tld: String get() = parts.third

  /** The apex domain object (e.g., "example.com" in "www.example.com") */
  val apexDomain: ApexDomain get() = ApexDomain(apexName, tld)

  override val labels: List<String> get() = subdomainLabels + apexName + tld

  override fun toString(): String = labels.joinToString(".")

  operator fun component1(): List<String> = subdomainLabels
  operator fun component2(): String = apexName
  operator fun component3(): String = tld

  /**
   * Returns the parent domain of this subdomain.
   *
   * For a single-level subdomain (e.g., "www.example.com"), returns the apex domain.
   * For multi-level subdomains (e.g., "dev.www.example.com"), returns the next subdomain level.
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.network.Subdomain
   * import com.fortechteams.valuetypes.network.ApexDomain
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   // Single level subdomain
   *   val www = Subdomain(listOf("www"), "example", "com")
   *   www.parent() shouldBe ApexDomain("example", "com")
   *
   *   // Multi-level subdomain
   *   val dev = Subdomain(listOf("dev", "www"), "example", "com")
   *   dev.parent().toString() shouldBe "www.example.com"
   * }
   * ```
   * <!--- KNIT example-network-Subdomain-03.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  fun parent(): Domain = when {
    subdomainLabels.size == 1 -> apexDomain
    else -> Subdomain(
      labels = subdomainLabels.drop(1),
      apexName = apexName,
      tld = tld
    )
  }

  /**
   * Returns all possible subdomains in the hierarchy from most specific to least specific.
   *
   * For example, for "dev.staging.example.com", returns:
   * - dev.staging.example.com
   * - staging.example.com
   *
   * ```kotlin
   * import com.fortechteams.valuetypes.network.Subdomain
   * import io.kotest.matchers.shouldBe
   *
   * fun test() {
   *   val domain = Subdomain(
   *     labels = listOf("dev", "staging"),
   *     apexName = "example",
   *     tld = "com"
   *   )
   *
   *   val hierarchy = domain.getSubdomainHierarchy()
   *   hierarchy.map { it.toString() } shouldBe listOf(
   *     "dev.staging.example.com",
   *     "staging.example.com"
   *   )
   * }
   * ```
   * <!--- KNIT example-network-Subdomain-02.kt -->
   * <!--- TEST lines.isEmpty() -->
   */
  fun getSubdomainHierarchy(): List<Subdomain> {
    return subdomainLabels
      .indices
      .map { i ->
        Subdomain(
          labels = subdomainLabels.drop(i),
          apexName = apexName,
          tld = tld
        )
      }
  }

  companion object {
    private fun validateParts(
      labels: List<String>,
      apexName: String,
      tld: String
    ): Triple<List<String>, String, String> {
      if (labels.isEmpty()) {
        throw InvalidDomainException(
          labels.joinToString("."),
          "Subdomain must have at least one label"
        )
      }

      labels.forEach { label ->
        if (!label.isValidLabel()) {
          throw InvalidDomainException(label, "Invalid subdomain label format")
        }
      }

      if (!apexName.isValidLabel()) {
        throw InvalidDomainException(apexName, "Invalid apex domain name format")
      }

      if (!tld.matches(Regex("^[a-zA-Z]{2,}$"))) {
        throw InvalidDomainException(tld, "Invalid TLD format")
      }

      val fullDomain = (labels + apexName + tld).joinToString(".")
      if (fullDomain.length > 253) {
        throw InvalidDomainException(
          fullDomain,
          "Domain length exceeds 253 characters"
        )
      }

      return Triple(labels, apexName, tld)
    }
  }
}

/**
 * Validates a domain name label according to RFC 1035.
 *
 * Rules:
 * - Must start with a letter
 * - Must end with a letter or digit
 * - Can contain letters, digits, and hyphens
 * - Cannot contain consecutive hyphens
 * - Length must be between 1 and 63 characters
 *
 * ```kotlin
 * fun test() {
 *   // Valid labels
 *   "a".isValidLabel() shouldBe true
 *   "z".isValidLabel() shouldBe true
 *   "ax".isValidLabel() shouldBe true
 *   "a-b".isValidLabel() shouldBe true
 *   "a".repeat(63).isValidLabel() shouldBe true
 *
 *   // Invalid labels
 *   "".isValidLabel() shouldBe false
 *   "1".isValidLabel() shouldBe false
 *   "-a".isValidLabel() shouldBe false
 *   "a-".isValidLabel() shouldBe false
 *   "a--b".isValidLabel() shouldBe false
 *   "a".repeat(64).isValidLabel() shouldBe false
 * }
 * ```
 */
private fun String.isValidLabel(): Boolean =
  matches(Regex("^[a-zA-Z]([a-zA-Z0-9-]*[a-zA-Z0-9])?$")) &&
    !contains("--") &&
    length <= 63