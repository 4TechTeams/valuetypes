# Module person

Provides specific value types for personal data & attributes like names, birth-date, etc.

## Usage

**Gradle Kotlin DSL:**

```kotlin
dependencies {
  implementation("com.fortechteams.valuetypes:person:VERSION")
}
```

**Gradle Groovy DSL:**

```groovy
implementation group: 'com.fortechteams.valuetypes', name: 'person', version: 'VERSION'
```

**Maven:**

```xml
<dependency>
    <groupId>com.fortechteams.valuetypes</groupId>
    <artifactId>person</artifactId>
    <version>VERSION</version>
</dependency>
```

# Package com.fortechteams.valuetypes.person

Generic personal value types like `FirstName` / `LastName`, `BirthDate`, etc.

# Package com.fortechteams.valuetypes.person.exception

Value type specific exceptions, majorly used for validation

# Package com.fortechteams.valuetypes.person.switzerland

Country-specific personal types for **Switzerland**, e.g. the `SwissSocialSecurityNumber` a.k.a. AHV-Number

# Package com.fortechteams.valuetypes.person.usa

Country-specific personal types for the **United States of America (USA)**, e.g. the `AmericanSocialSecurityNumber`