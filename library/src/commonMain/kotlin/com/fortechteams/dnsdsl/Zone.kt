package com.fortechteams.dnsdsl

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/**
 * Represents a DNS Zone, which is a delegated portion of the DNS namespace.
 * A zone contains all the DNS records for a domain and its subdomains, except those delegated to child zones.
 *
 * Main components:
 * - One mandatory SOA (Start of Authority) record
 * - Optional DNS records (A, AAAA, MX, CNAME, TXT, etc.)
 *
 * Example zone content:
 * ```
 * example.com.    IN  SOA   ns1.example.com. admin.example.com. (
 *                          2024030501  ; serial
 *                          3600        ; refresh
 *                          1800        ; retry
 *                          604800      ; expire
 *                          86400 )     ; minimum TTL
 * example.com.    IN  A     192.0.2.1
 * www.example.com. IN  CNAME example.com.
 * ```
 *
 * @property id Unique identifier for this zone
 * @property domain The zone's root domain name (e.g., "example.com")
 * @property soa The zone's mandatory Start of Authority record
 * @property records List of DNS records in this zone (excluding the SOA record)
 *
 * @see SoaRecord
 * @see Record
 */
@OptIn(ExperimentalUuidApi::class)
data class Zone(
  /**
   * Unique identifier for the zone.
   * Used for internal reference and updates.
   */
  val id: Uuid = Uuid.random(),

  /**
   * The zone's root domain name (e.g., "example.com").
   * Expected to be normalized (lowercase, no trailing dot).
   */
  val domain: Domain,

  /**
   * The zone's mandatory Start of Authority record.
   * Contains authoritative information about the zone.
   * There must be exactly one SOA record per zone.
   */
  val soa: SoaRecord,

  /**
   * List of DNS records in this zone, excluding the SOA record.
   * May include A, AAAA, MX, CNAME, TXT, and other record types.
   * Records in this list should all be for the zone's domain or its subdomains.
   */
  val records: List<Record>
)