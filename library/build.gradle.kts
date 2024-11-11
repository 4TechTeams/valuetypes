import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.kotlinMultiplatform)
  alias(libs.plugins.vanniktech.mavenPublish)
  alias(libs.plugins.kotest.multiplatform)
}

group = "io.github.kotlin"
version = "1.0.0"

kotlin {
  jvm()
  linuxX64()

  sourceSets {
    val commonMain by getting {
      dependencies {
        //put your multiplatform dependencies here
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
