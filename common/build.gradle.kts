plugins {
  id("convention.multiplatform")
  id("convention.docs")
  id("convention.maven-publish")
  id("convention.code-quality")
}

kotlin {
  jvm()
  linuxX64()

  sourceSets {

    commonTest {
      dependencies {
        implementation(libs.kotlin.test)
        implementation(libs.kotest.assertions.core)
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }
  }
}
