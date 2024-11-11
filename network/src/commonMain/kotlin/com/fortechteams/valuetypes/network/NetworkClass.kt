package com.fortechteams.valuetypes.network

enum class NetworkClass {
  A, // 0.0.0.0 to 127.255.255.255
  B, // 128.0.0.0 to 191.255.255.255
  C, // 192.0.0.0 to 223.255.255.255
  D, // 224.0.0.0 to 239.255.255.255
  E; // 240.0.0.0 to 255.255.255.255

  /**
   * Returns the standard subnet mask for this network class.
   * Returns null for Classes D and E as they don't have standard subnet masks.
   */
  fun subnetMask(): SubnetMask? = when (this) {
    A -> SubnetMask.CLASS_A
    B -> SubnetMask.CLASS_B
    C -> SubnetMask.CLASS_C
    D, E -> null
  }
}
