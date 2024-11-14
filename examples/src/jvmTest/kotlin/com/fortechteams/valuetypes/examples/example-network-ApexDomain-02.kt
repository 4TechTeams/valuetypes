// This file was automatically generated from Domain.kt by Knit tool. Do not edit.
package com.fotechteams.valuetypes.examples.exampleNetworkApexDomain02

import com.fortechteams.valuetypes.network.ApexDomain
import com.fortechteams.valuetypes.network.TopLevelDomain
import io.kotest.matchers.shouldBe

fun test() {
  val apex = ApexDomain("example", "com")
  val parent = apex.parent()
  parent shouldBe TopLevelDomain("com")
}
