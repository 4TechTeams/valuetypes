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
  ":library",
  ":network",
  ":examples"
)