package org.firstinspires.ftc.teamcode.exceptions

class HardwareMissingException(
    val hardwareId: String,
) : Exception("Hardware device not found: $hardwareId")
