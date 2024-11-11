# ValueTypes

> This project is in an **early stage of development**, where structural and backwards-compatibility-breaking changes could 
> be made. 
> 
> Until **1.0.0**, all versions of this project should be considered beta pre-releases.

**A collection of useful, domain-specific and explicit predefined value-types for Kotlin & Java**

*Value types are immutable data objects that are compared and considered equal based on their values rather than their 
identity (reference). Additionally, they provide strong type-safety and domain-specific functions and helpers to build, 
use and transform their respoctive value. ValueTypes was inspired by [Kotools Types](https://github.com/kotools/types) 
and Scala's [Opaque Types](https://docs.scala-lang.org/scala3/book/types-opaque-types.html).*

## Benefits
**Type Safety**: Prevent invalid states and accidental mutations

**Clearer Domain Model**: Express domain concepts precisely (e.g., `FirstName`, `Age`, `IPv4Address`)

**Reduced Bugs**: Immutability eliminates entire classes of concurrency and state-related bugs

**Better Testing**: Simpler to test due to their immutable nature

**Performance**: Optimized by the compiler through Kotlin's value class feature

## Examples

Improved type-safety, compared to working with Strings:

```kotlin
val firstName: FirstName = FirstName.of("Jane")
val lastName: LastName = firstName // << compilation error
```

Additional, domain-specific checks:

```kotlin
// A LocalDate can be anything, a BirthDate must be in the past
IPv4Address.of("192.168.1.1").isSuccess // true, because it's a valid v4 IP
IPv4Address.of("192.168.1").isSuccess // false, just 3 octets
IPv4Address.of("300.168.1.1").isSuccess // false, because first octet is too big
IPv4Address.of("localhost").isSuccess // false, because hostname is not an IP
```

Useful helper functions & transformations:

```kotlin
val birthDate: Result<BirthDate> = BirthDate.of(1989, 09, 05)
birthDay.age // returns the age of the person, by comparing birth date to current year

val ip = IPv4Address.of("192.168.1.1")
ip.isPrivate // true, because 192.168.xxx.xxx is a private IP segment
```
