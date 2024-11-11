import com.fortechteams.dnsdsl.valuetypes.IPv4Address
import com.fortechteams.dnsdsl.valuetypes.NetworkClass
import com.fortechteams.dnsdsl.valuetypes.SubnetMask
import io.kotest.matchers.shouldBe
import kotlin.test.Test
import kotlin.test.assertNotNull

class IPv4NetworkIntegrationTest {

  @Test
  fun `should determine correct network class and subnet mask for class A`() {
    val testCases = listOf(
      "0.0.0.0",
      "10.0.0.1",
      "127.255.255.255"
    )

    testCases.forEach { ip ->
      val address = IPv4Address.fromString(ip).getOrNull()
      assertNotNull(address)

      val networkClass = address.networkClass()
      networkClass shouldBe NetworkClass.A

      val subnetMask = networkClass.subnetMask()
      assertNotNull(subnetMask)
      subnetMask.toString() shouldBe "255.0.0.0"
    }
  }

  @Test
  fun `should determine correct network class and subnet mask for class B`() {
    val testCases = listOf(
      "128.0.0.0",
      "172.16.0.1",
      "191.255.255.255"
    )

    testCases.forEach { ip ->
      val address = IPv4Address.fromString(ip).getOrNull()
      assertNotNull(address)

      val networkClass = address.networkClass()
      networkClass shouldBe NetworkClass.B

      val subnetMask = networkClass.subnetMask()
      assertNotNull(subnetMask)
      subnetMask.toString() shouldBe "255.255.0.0"
    }
  }

  @Test
  fun `should determine correct network class and subnet mask for class C`() {
    val testCases = listOf(
      "192.0.0.0",
      "192.168.1.1",
      "223.255.255.255"
    )

    testCases.forEach { ip ->
      val address = IPv4Address.fromString(ip).getOrNull()
      assertNotNull(address)

      val networkClass = address.networkClass()
      networkClass shouldBe NetworkClass.C

      val subnetMask = networkClass.subnetMask()
      assertNotNull(subnetMask)
      subnetMask.toString() shouldBe "255.255.255.0"
    }
  }

  @Test
  fun `should determine correct network class and no subnet mask for class D`() {
    val testCases = listOf(
      "224.0.0.0",
      "239.255.255.255"
    )

    testCases.forEach { ip ->
      val address = IPv4Address.fromString(ip).getOrNull()
      assertNotNull(address)

      val networkClass = address.networkClass()
      networkClass shouldBe NetworkClass.D

      val subnetMask = networkClass.subnetMask()
      subnetMask shouldBe null
    }
  }

  @Test
  fun `should determine correct network class and no subnet mask for class E`() {
    val testCases = listOf(
      "240.0.0.0",
      "255.255.255.255"
    )

    testCases.forEach { ip ->
      val address = IPv4Address.fromString(ip).getOrNull()
      assertNotNull(address)

      val networkClass = address.networkClass()
      networkClass shouldBe NetworkClass.E

      val subnetMask = networkClass.subnetMask()
      subnetMask shouldBe null
    }
  }

  @Test
  fun `should handle well-known addresses correctly`() {
    // Test LOCALHOST (127.0.0.1)
    IPv4Address.LOCALHOST.networkClass() shouldBe NetworkClass.A
    IPv4Address.LOCALHOST.networkClass().subnetMask() shouldBe SubnetMask.CLASS_A

    // Test ANY (0.0.0.0)
    IPv4Address.ANY.networkClass() shouldBe NetworkClass.A
    IPv4Address.ANY.networkClass().subnetMask() shouldBe SubnetMask.CLASS_A

    // Test BROADCAST (255.255.255.255)
    IPv4Address.BROADCAST.networkClass() shouldBe NetworkClass.E
    IPv4Address.BROADCAST.networkClass().subnetMask() shouldBe null
  }
}