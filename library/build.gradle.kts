plugins {
  id("convention.multiplatform")
}

kotlin {
  jvm()
  linuxX64()

  sourceSets {
    val commonMain by getting {
      dependencies {
        // put your multiplatform dependencies here
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(libs.kotlin.test)
        implementation(libs.kotest.framework.engine)
        implementation(libs.kotest.assertions.core)
      }
    }
  }
}

tasks.withType<Test>().configureEach {
  useJUnitPlatform()
}
