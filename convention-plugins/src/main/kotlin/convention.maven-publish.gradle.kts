import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost

plugins {
  id("com.vanniktech.maven.publish")
}

// hack to get version, as project.version does not exist at this stage
val version = rootProject.version.toString()

configure<MavenPublishBaseExtension> {
  publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
  signAllPublications()
  coordinates(group.toString(), project.name, version)
  pom {
    name.set("ValueTypes")
    description.set("A collection of useful, domain-specific and explicit predefined value-types for Kotlin & Java")
    inceptionYear.set("2024")
    url.set("https://valuetypes.4techteams.com")
    licenses {
      license {
        name.set("Apache License, Version 2.0")
        url.set("https://opensource.org/license/apache-2-0")
      }
    }
    developers {
      developer {
        id.set("frne")
        name.set("Frank Neff")
        url.set("https://frankneff.com")
      }
      developer {
        id.set("4techteams-contributors")
        name.set("4TechTeams Contributors")
        url.set("https://4techteams.com")
      }
    }
    issueManagement {
      system.set("GitHub Issues")
      url.set("https://github.com/4TechTeams/valuetypes/issues")
    }
    scm {
      url.set("https://github.com/4TechTeams/valuetypes")
    }
  }
}