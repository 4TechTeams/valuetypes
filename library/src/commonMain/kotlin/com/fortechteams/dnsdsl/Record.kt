@file:OptIn(ExperimentalUuidApi::class)

package com.fortechteams.dnsdsl

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Represents a DNS resource record as defined in RFC 1035.
 * Each record type contains the common fields (id, name, ttl) and type-specific data.
 *
 * All record types follow the same basic structure:
 * ```
 * [name] [ttl] IN [type] [type-specific-data]
 * ```
 */
sealed interface Record {

  /** Unique identifier for the record, used for internal reference and updates */
  val id: Uuid

  /**
   * The owner name of this DNS record.
   * Expected to be pre-normalized:
   * - "@" for zone apex
   * - Subdomain without trailing dot ("www")
   * - Or fully qualified name with trailing dot ("www.example.com.")
   */
  val name: String

  /**
   * Time To Live in seconds - how long resolvers should cache this record.
   * Default is typically 3600 (1 hour) if not specified.
   * Range: 0 to 2147483647 (maximum 32-bit unsigned integer)
   */
  val ttl: Int

  /**
   * Converts the record to a zone file format string according to RFC 1035.
   * Assumes name is already in the correct format.
   * @return String representation of the record in zone file format
   */
  fun toZoneFileString(): String

  /**
   * Address record that maps a hostname to an IPv4 address.
   * Example: www 3600 IN A 192.0.2.1
   *
   * @property id Unique identifier for this record
   * @property name Pre-normalized owner name for this A record
   * @property ttl Cache time in seconds
   * @property ipv4Address IPv4 address in dotted decimal format (e.g., "192.0.2.1")
   */
  data class ARecord(
    override val id: Uuid = Uuid.random(),
    override val name: String,
    override val ttl: Int = 3600,
    val ipv4Address: String
  ) : Record {
    override fun toZoneFileString(): String =
      "$name\t$ttl\tIN\tA\t$ipv4Address"
  }

  /**
   * Address record that maps a hostname to an IPv6 address.
   * Example: www 3600 IN AAAA 2001:db8::1
   *
   * @property id Unique identifier for this record
   * @property name Pre-normalized owner name for this AAAA record
   * @property ttl Cache time in seconds
   * @property ipv6Address IPv6 address in canonical format (e.g., "2001:db8::1")
   */
  data class AaaaRecord(
    override val id: Uuid = Uuid.random(),
    override val name: String,
    override val ttl: Int = 3600,
    val ipv6Address: String
  ) : Record {
    override fun toZoneFileString(): String =
      "$name\t$ttl\tIN\tAAAA\t$ipv6Address"
  }

  /**
   * Mail exchanger record, specifies the mail server responsible for accepting email messages.
   * Example: @ 3600 IN MX 10 mail.example.com.
   *
   * @property id Unique identifier for this record
   * @property name Pre-normalized owner name for this MX record
   * @property ttl Cache time in seconds
   * @property priority Priority of this mail server (lower numbers are higher priority)
   * @property mailServer Hostname of the mail server (expected to be normalized)
   */
  data class MxRecord(
    override val id: Uuid = Uuid.random(),
    override val name: String,
    override val ttl: Int = 3600,
    val priority: Int,
    val mailServer: String
  ) : Record {
    override fun toZoneFileString(): String =
      "$name\t$ttl\tIN\tMX\t$priority\t$mailServer"
  }

  /**
   * Canonical name record, creates an alias pointing to another domain name.
   * Example: www 3600 IN CNAME example.com.
   *
   * Note: CNAME records have several restrictions:
   * - A name with a CNAME record cannot have any other record types
   * - CNAME records cannot exist at a zone apex
   *
   * @property id Unique identifier for this record
   * @property name Pre-normalized alias name
   * @property ttl Cache time in seconds
   * @property canonicalName The canonical (target) hostname (expected to be normalized)
   */
  data class CnameRecord(
    override val id: Uuid = Uuid.random(),
    override val name: String,
    override val ttl: Int = 3600,
    val canonicalName: String
  ) : Record {
    override fun toZoneFileString(): String =
      "$name\t$ttl\tIN\tCNAME\t$canonicalName"
  }

  /**
   * Text record, holds machine-readable text data for various services.
   * Example: _acme-challenge 3600 IN TXT "validation-token-here"
   *
   * Common uses:
   * - SPF records for email validation
   * - Domain ownership verification
   * - DKIM keys
   *
   * Note: Each string in the text list is limited to 255 characters per DNS standards.
   * Longer TXT records must be split into multiple strings.
   *
   * @property id Unique identifier for this record
   * @property name Pre-normalized owner name for this TXT record
   * @property ttl Cache time in seconds
   * @property text List of text strings that make up the record
   */
  data class TxtRecord(
    override val id: Uuid = Uuid.random(),
    override val name: String,
    override val ttl: Int = 3600,
    val text: List<String>
  ) : Record {
    override fun toZoneFileString(): String =
      "$name\t$ttl\tIN\tTXT\t${
        text.joinToString(" ") {
          "\"${it.replace("\"", "\\\"")}\""
        }
      }"
  }
}
