plugins {
  id("convention.multiplatform")
  id("convention.docs")
}

kotlin {
  jvm()
  linuxX64()

  sourceSets {

    val commonTest by getting {
      dependencies {
        implementation(project(":common"))
        implementation(project(":core"))
        implementation(project(":switzerland"))
        implementation(project(":network"))
        implementation(libs.kotlin.test)
      }
    }

    val jvmTest by getting {
      dependencies {
        implementation(libs.kotest.runner.junit5)
        implementation(libs.kotlinx.knit.test)
      }
    }
  }
}
