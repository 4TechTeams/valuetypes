package com.fortechteams.valuetypes.network

import com.fortechteams.valuetypes.network.exception.InvalidDomainException
import io.kotest.assertions.throwables.shouldNotThrow
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class DomainTest {
  @Test
  fun `parsing TLD should work for valid TLDs`() {
    val tld = Domain.parse("com")
    tld shouldBe TopLevelDomain("com")
    tld.labels shouldContainExactly listOf("com")
    tld.toFQDN() shouldBe "com."
  }

  @Test
  fun `parsing TLD should fail for invalid TLDs`() {
    shouldThrow<InvalidDomainException> {
      Domain.parse("c")
    }
    shouldThrow<InvalidDomainException> {
      Domain.parse("123")
    }
    shouldThrow<InvalidDomainException> {
      Domain.parse("com-")
    }
  }

  @Test
  fun `parsing apex domain should work for valid domains`() {
    val apex = Domain.parse("example.com")
    apex shouldBe ApexDomain("example", "com")

    with(apex as ApexDomain) {
      name shouldBe "example"
      tld shouldBe "com"
      labels shouldContainExactly listOf("example", "com")
      toFQDN() shouldBe "example.com."
    }
  }

  @Test
  fun `parsing apex domain should fail for invalid domains`() {
    val invalidApexDomains = listOf(
      "example-.com",
      "-example.com",
      "exam@ple.com",
      "example.c",
      "example.123"
    )

    invalidApexDomains.forEach { domain ->
      shouldThrow<InvalidDomainException> {
        Domain.parse(domain)
      }
    }
  }

  @Test
  fun `parsing subdomain should work for valid subdomains`() {
    val subdomain = Domain.parse("www.example.com")
    subdomain shouldBe Subdomain(listOf("www"), "example", "com")

    with(subdomain as Subdomain) {
      subdomainLabels shouldContainExactly listOf("www")
      apexName shouldBe "example"
      tld shouldBe "com"
      labels shouldContainExactly listOf("www", "example", "com")
      toFQDN() shouldBe "www.example.com."
      apexDomain shouldBe ApexDomain("example", "com")
    }
  }

  @Test
  fun `parsing multiple level subdomains should work`() {
    val subdomain = Domain.parse("dev.staging.test.example.com")
    subdomain shouldBe Subdomain(listOf("dev", "staging", "test"), "example", "com")

    with(subdomain as Subdomain) {
      subdomainLabels shouldContainExactly listOf("dev", "staging", "test")
      apexName shouldBe "example"
      tld shouldBe "com"
      labels shouldContainExactly listOf("dev", "staging", "test", "example", "com")
      toFQDN() shouldBe "dev.staging.test.example.com."
    }
  }

  @Test
  fun `subdomain hierarchy should be correct`() {
    val subdomain = Domain.parse("dev.staging.test.example.com") as Subdomain
    val hierarchy = subdomain.getSubdomainHierarchy()

    hierarchy.map { it.labels } shouldContainExactly listOf(
      listOf("dev", "staging", "test", "example", "com"),
      listOf("staging", "test", "example", "com"),
      listOf("test", "example", "com")
    )
  }

  @Test
  fun `parsing subdomain should fail for invalid subdomains`() {
    val invalidSubdomains = listOf(
      "www..example.com",
      ".www.example.com",
      "www-.example.com",
      "-www.example.com",
      "www.example-.com",
      "www.exam@ple.com",
      "www.example.c"
    )

    invalidSubdomains.forEach { domain ->
      shouldThrow<InvalidDomainException> {
        Domain.parse(domain)
      }
    }
  }

  @Test
  fun `domain length validation should work`() {
    val longLabel = "a".repeat(63)
    // Valid 63-character label
    Domain.parse("$longLabel.example.com")

    // Invalid - label too long
    shouldThrow<InvalidDomainException> {
      Domain.parse("${"a".repeat(64)}.example.com")
    }

    // Invalid - total length too long
    shouldThrow<InvalidDomainException> {
      val labels = List(10) { longLabel }
      Domain.parse("${labels.joinToString(".")}.example.com")
    }
  }

  @Test
  fun `domains should handle trailing dots correctly`() {
    val withDot = Domain.parse("example.com.")
    val withoutDot = Domain.parse("example.com")

    withDot shouldBe withoutDot
    withDot.toFQDN() shouldBe "example.com."
  }

  @Test
  fun `empty domain should throw exception`() {
    shouldThrow<InvalidDomainException> {
      Domain.parse("")
    }
    shouldThrow<InvalidDomainException> {
      Domain.parse(".")
    }
  }

  @Test
  fun `toString should work correctly for TLD`() {
    val tld = Domain.parse("com")
    tld.toString() shouldBe "com"
  }

  @Test
  fun `toString should work correctly for ApexDomain`() {
    val apex = Domain.parse("example.com")
    apex.toString() shouldBe "example.com"
  }

  @Test
  fun `toString should work correctly for Subdomain`() {
    val subdomain = Domain.parse("www.example.com")
    subdomain.toString() shouldBe "www.example.com"

    val multiLevel = Domain.parse("dev.staging.test.example.com")
    multiLevel.toString() shouldBe "dev.staging.test.example.com"
  }

  @Test
  fun `ApexDomain parent should return correct TLD`() {
    // Basic case
    val basic = ApexDomain("example", "com")
    basic.parent() shouldBe TopLevelDomain("com")
  }

  @Test
  fun `Subdomain parent should return correct parent domain`() {
    // Single level subdomain -> returns apex domain
    val www = Domain.parse("www.example.com") as Subdomain
    www.parent().toString() shouldBe "example.com"

    // Multi-level subdomain -> returns next subdomain level
    val dev = Domain.parse("dev.www.example.com") as Subdomain
    dev.parent().toString() shouldBe "www.example.com"

    // Deep hierarchy
    val deep = Domain.parse("a.b.c.d.example.com") as Subdomain
    deep.parent().toString() shouldBe "b.c.d.example.com"
  }

  @Test
  fun `destructuring should work in when expressions`() {
    fun describeDomain(domain: Domain) = when (domain) {
      is TopLevelDomain -> {
        val (value) = domain
        "TLD: $value"
      }
      is ApexDomain -> {
        val (name, tld) = domain
        "Apex Domain: $name in .$tld"
      }
      is Subdomain -> {
        val (subs, apex, tld) = domain
        "Subdomain: ${subs.joinToString(".")} of $apex.$tld"
      }
    }

    val examples = listOf(
      "com" to "TLD: com",
      "example.com" to "Apex Domain: example in .com",
      "www.example.com" to "Subdomain: www of example.com"
    )

    examples.forEach { (input, expected) ->
      describeDomain(Domain.parse(input)) shouldBe expected
    }
  }

  @Test
  fun `destructuring should work in lambda expressions`() {
    val domains = listOf(
      Domain.parse("www.example.com"),
      Domain.parse("dev.staging.example.com")
    )

    // Using destructuring in lambda
    val subdomainPaths = domains.filterIsInstance<Subdomain>()
      .map { (subs, apex, tld) ->
        "$apex.$tld has subdomains: ${subs.joinToString(".")}"
      }

    subdomainPaths shouldContainExactly listOf(
      "example.com has subdomains: www",
      "example.com has subdomains: dev.staging"
    )
  }

  @Test
  fun `validation should handle single character labels correctly`() {
    shouldNotThrow<Throwable> {
      Domain.parse("a.example.com")
    }
  }
}