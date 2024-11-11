package com.fortechteams.dnsdsl

// File: DnsDsl.kt


// Validation
sealed class DnsValidationError(val message: String) {
    data class DuplicateSOA(val domain: String) :
        DnsValidationError("Zone '$domain' cannot have multiple SOA records")

    data class MissingSOA(val domain: String) :
        DnsValidationError("Zone '$domain' must have exactly one SOA record")

    data class ConflictingRecords(val name: String, val types: List<String>) :
        DnsValidationError("Conflicting record types ${types.joinToString()} for name '$name'")

    data class InvalidHostname(val name: String) :
        DnsValidationError("Invalid hostname: $name")
}

data class ValidationResult(
    val errors: List<DnsValidationError> = emptyList()
) {
    val isValid: Boolean get() = errors.isEmpty()

    companion object {
        val SUCCESS = ValidationResult()
    }
}

// DSL Building blocks
@DslMarker
annotation class DnsDslMarker

@DnsDslMarker
class ZoneBuilder(private val domain: String) {
    private val records = mutableListOf<Record>()
    private var soaRecord: SoaRecord? = null

    fun soa(block: SoaBuilder.() -> Unit) {
        if (soaRecord != null) {
            throw IllegalStateException("SOA record already defined for zone $domain")
        }
        val builder = SoaBuilder()
        builder.block()
        soaRecord = builder.build()
    }

    // Helper method to check if a name already has a CNAME or other records
    private fun validateNewRecord(name: String, isCname: Boolean) {
        val existingRecords = records.filter { it.name == name }
        val hasCname = existingRecords.any { it is Record.CnameRecord }

        if (isCname && existingRecords.isNotEmpty()) {
            throw IllegalStateException("Cannot add CNAME record for '$name': other records exist")
        }

        if (!isCname && hasCname) {
            throw IllegalStateException("Cannot add record for '$name': CNAME record exists")
        }
    }

    fun a(name: String, block: ARecordBuilder.() -> Unit) {
        validateNewRecord(name, isCname = false)
        val builder = ARecordBuilder(name)
        builder.block()
        records.add(builder.build())
    }

    fun aaaa(name: String, block: AaaaRecordBuilder.() -> Unit) {
        validateNewRecord(name, isCname = false)
        val builder = AaaaRecordBuilder(name)
        builder.block()
        records.add(builder.build())
    }

    fun mx(name: String, block: MxRecordBuilder.() -> Unit) {
        validateNewRecord(name, isCname = false)
        val builder = MxRecordBuilder(name)
        builder.block()
        records.add(builder.build())
    }

    fun cname(name: String, block: CnameRecordBuilder.() -> Unit) {
        validateNewRecord(name, isCname = true)
        val builder = CnameRecordBuilder(name)
        builder.block()
        records.add(builder.build())
    }

    fun txt(name: String, block: TxtRecordBuilder.() -> Unit) {
        validateNewRecord(name, isCname = false)
        val builder = TxtRecordBuilder(name)
        builder.block()
        records.add(builder.build())
    }

    internal fun build(): DnsZone {
        // Ensure SOA exists
        val soa = soaRecord ?: throw IllegalStateException("Zone $domain must have an SOA record")
        return DnsZone(domain, records.toList(), soa)
    }
}

@DnsDslMarker
class SoaBuilder {
    private lateinit var primaryNs: String
    private lateinit var responsible: String
    private var serial: Long = generateSerial()
    private var refresh: Int = 86400
    private var retry: Int = 7200
    private var expire: Int = 3600000
    private var minimumTtl: Int = 3600

    fun primaryNs(value: String) { primaryNs = value }
    fun responsible(value: String) { responsible = value.replace('@', '.') }
    fun serial(value: Long) { serial = value }
    fun refresh(value: Int) { refresh = value }
    fun retry(value: Int) { retry = value }
    fun expire(value: Int) { expire = value }
    fun minimumTtl(value: Int) { minimumTtl = value }

    private fun generateSerial(): Long {
        val format = java.text.SimpleDateFormat("yyyyMMdd")
        return format.format(java.util.Date()).toLong() * 100
    }

    internal fun build() = SoaRecord(
        primaryNs = primaryNs,
        responsible = responsible,
        serial = serial,
        refresh = refresh,
        retry = retry,
        expire = expire,
        minimumTtl = minimumTtl
    )
}

@DnsDslMarker
class ARecordBuilder(private val name: String) {
    private var ttl: Int = 3600
    private lateinit var ipv4Address: String

    fun ttl(value: Int) { ttl = value }
    fun address(value: String) { ipv4Address = value }

    internal fun build() = Record.ARecord(name, ttl, ipv4Address)
}

@DnsDslMarker
class AaaaRecordBuilder(private val name: String) {
    private var ttl: Int = 3600
    private lateinit var ipv6Address: String

    fun ttl(value: Int) { ttl = value }
    fun address(value: String) { ipv6Address = value }

    internal fun build() = Record.AaaaRecord(name, ttl, ipv6Address)
}

@DnsDslMarker
class MxRecordBuilder(private val name: String) {
    private var ttl: Int = 3600
    private var priority: Int = 10
    private lateinit var mailServer: String

    fun ttl(value: Int) { ttl = value }
    fun priority(value: Int) { priority = value }
    fun server(value: String) { mailServer = value }

    internal fun build() = Record.MxRecord(name, ttl, priority, mailServer)
}

@DnsDslMarker
class CnameRecordBuilder(private val name: String) {
    private var ttl: Int = 3600
    private lateinit var canonicalName: String

    fun ttl(value: Int) { ttl = value }
    fun alias(value: String) { canonicalName = value }

    internal fun build() = Record.CnameRecord(name, ttl, canonicalName)
}

@DnsDslMarker
class TxtRecordBuilder(private val name: String) {
    private var ttl: Int = 3600
    private val textParts = mutableListOf<String>()

    fun ttl(value: Int) { ttl = value }
    fun value(text: String) { textParts.add(text) }
    fun values(vararg texts: String) { textParts.addAll(texts) }

    internal fun build() = Record.TxtRecord(name, ttl, textParts.toList())
}

// Top level DSL function
fun dnsZone(domain: String, block: ZoneBuilder.() -> Unit): Result<DnsZone> {
    return try {
        val builder = ZoneBuilder(domain)
        builder.block()
        val zone = builder.build()

        // Validate the built zone
        val validation = zone.validate()
        if (validation.isValid) {
            Result.success(zone)
        } else {
            Result.failure(IllegalStateException(
                validation.errors.joinToString("\n") { it.message }
            ))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}