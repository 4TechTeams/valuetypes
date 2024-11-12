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
  ":switzerland",
  ":usa",
  ":library",
  ":person",
  ":network",
  ":examples"
)