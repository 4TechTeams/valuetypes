package com.fortechteams.valuetypes.network

import kotlin.jvm.JvmInline

@JvmInline
value class Domain private constructor(val value: String) {

  /**
   * Returns the domain name with a trailing dot (canonical form in zone files)
   */
  fun toZoneString(): String = "$value."

  /**
   * Returns all parent domains, from immediate parent to root.
   * Example: for "www.example.com" returns ["example.com", "com"]
   */
  fun parentDomains(): List<Domain> {
    val parts = value.split('.')
    return (1..parts.lastIndex).map { i ->
      unsafe(parts.drop(i).joinToString("."))
    }
  }

  override fun toString(): String = value

  companion object {
    // Maximum lengths defined in RFC 1035
    private const val MAX_LABEL_LENGTH = 63
    private const val MAX_DOMAIN_LENGTH = 253

    private val LABEL_REGEX = """[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?""".toRegex()

    /**
     * Creates a validated Domain instance.
     * @throws IllegalArgumentException if the domain name is invalid
     */
    fun of(value: String): Domain {
      // Remove trailing dot if present
      val normalized = value.removeSuffix(".").lowercase()

      require(normalized.isNotEmpty()) { "Domain name cannot be empty" }
      require(normalized.length <= MAX_DOMAIN_LENGTH) {
        "Domain name cannot exceed $MAX_DOMAIN_LENGTH characters"
      }

      val labels = normalized.split('.')

      require(labels.isNotEmpty()) { "Domain name must have at least one label" }

      labels.forEach { label ->
        require(label.isNotEmpty()) { "Empty label in domain name" }
        require(label.length <= MAX_LABEL_LENGTH) {
          "Label '$label' exceeds $MAX_LABEL_LENGTH characters"
        }
        require(label.matches(LABEL_REGEX)) {
          "Invalid label '$label' in domain name"
        }
      }

      return Domain(normalized)
    }

    /**
     * Creates a Domain instance without validation.
     * Should only be used when the input is guaranteed to be valid.
     */
    internal fun unsafe(value: String): Domain = Domain(value.removeSuffix(".").lowercase())
  }
}
