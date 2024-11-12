pluginManagement {
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "valuetypes"

includeBuild("convention-plugins")

// libs
include(
  ":core",
  ":library",
  ":person",
  ":network",
  ":examples"
)