package com.fortechteams.dnsdsl

/**
 * Represents a Start of Authority (SOA) record, which must be present in every zone.
 * The SOA record specifies authoritative information about a DNS zone, including
 * contact information, serial number, and various timeouts.
 *
 * Example SOA record in zone file format:
 * ```
 * example.com. IN SOA ns1.example.com. hostmaster.example.com. (
 *              2024030501  ; serial
 *              86400      ; refresh (24 hours)
 *              7200       ; retry (2 hours)
 *              3600000    ; expire (1000 hours)
 *              3600       ; minimum TTL (1 hour)
 *              )
 * ```
 *
 * @property primaryNs Primary nameserver for this zone. Should be a fully qualified domain name, e.g.,
 *                     "ns1.example.com". Expected to be pre-normalized (no trailing dot).
 *
 * @property responsible Email address of the person responsible for this zone, with '@' replaced by '.',
 *                       e.g., "hostmaster.example.com". Expected to be pre-normalized (no trailing dot).
 *
 * @property serial Version number for this zone data, typically in format YYYYMMDDnn where nn
 *                  is a revision number (00-99). Must increase whenever zone data changes.
 *
 * @property refresh How often (in seconds) secondary nameservers should check for updates.
 *                   Default: 86400 (24 hours)
 *
 * @property retry How long (in seconds) to wait before retrying a failed zone transfer.
 *                 Default: 7200 (2 hours)
 *
 * @property expire How long (in seconds) a secondary nameserver should keep serving zone data
 *                  if it can't contact the primary nameserver. After this time, the secondary
 *                  will stop answering queries for the zone.
 *                  Default: 3600000 (about 42 days)
 *
 * @property minimumTtl Default TTL (in seconds) for negative caching (NXDOMAIN responses).
 *                      Also used as a default for records without explicit TTL.
 *                      Default: 3600 (1 hour)
 *
 * Note: All timing values (refresh, retry, expire, minimumTtl) are in seconds and should be
 * chosen carefully based on zone update frequency and reliability requirements.
 *
 * @see Zone
 * @see RFC 1035 Section 3.3.13
 */
data class SoaRecord(
  val primaryNs: String,
  val responsible: String,
  val serial: Long,
  val refresh: Int = 86400,
  val retry: Int = 7200,
  val expire: Int = 3600000,
  val minimumTtl: Int = 3600
)
