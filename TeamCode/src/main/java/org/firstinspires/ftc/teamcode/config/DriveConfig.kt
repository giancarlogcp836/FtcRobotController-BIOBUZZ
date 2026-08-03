package org.firstinspires.ftc.teamcode.config

import com.bylazar.configurables.annotations.Configurable
import org.firstinspires.ftc.teamcode.state.MotorDirection

@Configurable
object DriveConfig {
    @JvmField
    var maxSpeed: Double = 1.0

    @JvmField
    var strafeMultiplier: Double = 1.0

    @JvmField
    var turnMultiplier: Double = 0.8

    @JvmField
    var leftFrontName: String = "lf"

    @JvmField
    var leftRearName: String = "lr"

    @JvmField
    var rightFrontName: String = "rf"

    @JvmField
    var rightRearName: String = "rr"

    @JvmField
    var leftFrontDirection: MotorDirection = MotorDirection.REVERSE

    @JvmField
    var leftRearDirection: MotorDirection = MotorDirection.REVERSE

    @JvmField
    var rightFrontDirection: MotorDirection = MotorDirection.FORWARD

    @JvmField
    var rightRearDirection: MotorDirection = MotorDirection.FORWARD
}
