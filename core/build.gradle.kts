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
        api(libs.kotlinx.datetime)
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

    jvmTest {
      dependencies {
        implementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
        implementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
        implementation(libs.junit.jupiter.params)
      }
    }
  }
}
