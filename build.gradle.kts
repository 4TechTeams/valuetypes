buildscript {
  repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
    mavenLocal()
  }

  allprojects {
    group = "com.fortechteams.valuetypes"
    version = rootProject
      .file("version.txt")
      .readText()
      .trim()
  }
}

plugins {
  base
  alias(libs.plugins.kotlinMultiplatform).apply(false)
  alias(libs.plugins.kotestMultiplatform).apply(false)
  alias(libs.plugins.sonarqube)
  alias(libs.plugins.kotlinxKover)
  id("convention.docs-root")
}

dependencies {
  kover(project(":core"))
  kover(project(":switzerland"))
  kover(project(":network"))

  dokka(project(":core"))
  dokka(project(":switzerland"))
  dokka(project(":network"))
}

rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin> {
  rootProject.the<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension>().ignoreScripts = false
}

sonar {
  properties {
    property("sonar.projectKey", "4TechTeams_valuetypes")
    property("sonar.organization", "4techteams")
    property("sonar.host.url", "https://sonarcloud.io")
    property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/kover/report.xml")
  }
}
