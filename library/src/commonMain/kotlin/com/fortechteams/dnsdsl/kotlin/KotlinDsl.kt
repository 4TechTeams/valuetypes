package com.fortechteams.dnsdsl.kotlin

import com.fortechteams.dnsdsl.Domain
import com.fortechteams.dnsdsl.Record
import com.fortechteams.dnsdsl.SoaRecord
import com.fortechteams.dnsdsl.Zone
import kotlin.uuid.Uuid

@DslMarker
annotation class DnsDslMarker

/**
 * Creates a new DNS zone with the given domain and configuration.
 */
fun dnsZone(domain: String, block: ZoneBuilder.() -> Unit): Zone =
  ZoneBuilder(Domain.of(domain)).apply(block).build()

@DnsDslMarker
class ZoneBuilder(private val domain: Domain) {
  private var soaRecord: SoaRecord? = null
  private val records = mutableListOf<Record>()

  fun soa(block: SoaBuilder.() -> Unit) {
    check(soaRecord == null) { "SOA record already defined for zone ${domain.value}" }
    soaRecord = SoaBuilder().apply(block).build()
  }

  fun a(name: String, block: ARecordBuilder.() -> Unit) {
    records += ARecordBuilder(name).apply(block).build()
  }

  fun aaaa(name: String, block: AaaaRecordBuilder.() -> Unit) {
    records += AaaaRecordBuilder(name).apply(block).build()
  }

  fun mx(name: String, block: MxRecordBuilder.() -> Unit) {
    records += MxRecordBuilder(name).apply(block).build()
  }

  fun cname(name: String, block: CnameRecordBuilder.() -> Unit) {
    records += CnameRecordBuilder(name).apply(block).build()
  }

  fun txt(name: String, block: TxtRecordBuilder.() -> Unit) {
    records += TxtRecordBuilder(name).apply(block).build()
  }

  fun build(): Zone = Zone(
    domain = domain,
    soa = requireNotNull(soaRecord) { "Zone ${domain.value} must have an SOA record" },
    records = records.toList()
  )
}

@DnsDslMarker
class SoaBuilder {
  var primaryNs: String? = null
  var responsible: String? = null
  var serial: Long = generateSerial()
  var refresh: Int = 86400
  var retry: Int = 7200
  var expire: Int = 3600000
  var minimumTtl: Int = 3600

  private fun generateSerial() =
    java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.BASIC_ISO_DATE).toLong() * 100

  fun build(): SoaRecord = SoaRecord(
    primaryNs = requireNotNull(primaryNs) { "Primary nameserver must be specified" },
    responsible = requireNotNull(responsible) { "Responsible person must be specified" }.replace('@', '.'),
    serial = serial,
    refresh = refresh,
    retry = retry,
    expire = expire,
    minimumTtl = minimumTtl
  )
}

@DslMarker
sealed class RecordBuilder(protected val name: String) {
  var ttl: Int = 3600
  var id: Uuid = Uuid.random()
}

class ARecordBuilder(name: String) : RecordBuilder(name) {
  var address: String? = null

  fun build(): Record.ARecord = Record.ARecord(
    id = id,
    name = name,
    ttl = ttl,
    ipv4Address = requireNotNull(address) { "IPv4 address must be specified" }
  )
}

class AaaaRecordBuilder(name: String) : RecordBuilder(name) {
  var address: String? = null

  fun build(): Record.AaaaRecord = Record.AaaaRecord(
    id = id,
    name = name,
    ttl = ttl,
    ipv6Address = requireNotNull(address) { "IPv6 address must be specified" }
  )
}

class MxRecordBuilder(name: String) : RecordBuilder(name) {
  var priority: Int = 10
  var server: String? = null

  fun build(): Record.MxRecord = Record.MxRecord(
    id = id,
    name = name,
    ttl = ttl,
    priority = priority,
    mailServer = requireNotNull(server) { "Mail server must be specified" }
  )
}

class CnameRecordBuilder(name: String) : RecordBuilder(name) {
  var alias: String? = null

  fun build(): Record.CnameRecord = Record.CnameRecord(
    id = id,
    name = name,
    ttl = ttl,
    canonicalName = requireNotNull(alias) { "Canonical name must be specified" }
  )
}

class TxtRecordBuilder(name: String) : RecordBuilder(name) {
  private val text = mutableListOf<String>()

  // Operator functions for more natural syntax
  operator fun String.unaryPlus() {
    text += this
  }

  // Alternative methods for different use cases
  fun add(vararg values: String) {
    text += values
  }

  fun build(): Record.TxtRecord = Record.TxtRecord(
    id = id,
    name = name,
    ttl = ttl,
    text = text.toList()
  )
}