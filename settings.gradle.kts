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
  ":common",
  ":core",
  ":switzerland",
  ":usa",
  ":library",
  ":person",
  ":network",
  ":examples"
)