plugins {
  id("convention.multiplatform")
  id("convention.docs")
  id("convention.maven-publish")
  id("convention.code-quality")
}

kotlin {
  jvm()
  linuxX64()
  js {
    browser()
    nodejs()
  }

  sourceSets {

    commonMain {
      dependencies {
        api(project(":common"))
      }
    }

    commonTest {
      dependencies {
        implementation(libs.kotlin.test)
        implementation(libs.kotest.assertions.core)
        implementation(libs.kotest.framework.engine)
        implementation(libs.kotest.framework.datatest)
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }
  }
}
