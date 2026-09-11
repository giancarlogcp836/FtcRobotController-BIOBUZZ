package org.firstinspires.ftc.teamcode.config

import com.bylazar.configurables.annotations.Configurable
import org.firstinspires.ftc.teamcode.state.MotorDirection
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Configurable
object DriveConfig {
    @JvmField
    var maxSpeed: Double = 1.0

    @JvmField
    var strafeMultiplier: Double = 1.0

    @JvmField
    var turnMultiplier: Double = 0.8

    @JvmField
    var preciseMultiplier: Double = 0.25

    var preciseToggleRumbleDuration: Duration = 500.milliseconds
    var preciseToggleDebounce: Duration = 500.milliseconds

    @JvmField
    var frontLeftName: String = "lf"

    @JvmField
    var backLeftName: String = "lr"

    @JvmField
    var frontRightName: String = "rf"

    @JvmField
    var backRightName: String = "rr"

    @JvmField
    var frontLeftDirection: MotorDirection = MotorDirection.REVERSE

    @JvmField
    var backLeftDirection: MotorDirection = MotorDirection.REVERSE

    @JvmField
    var frontRightDirection: MotorDirection = MotorDirection.FORWARD

    @JvmField
    var rightRearDirection: MotorDirection = MotorDirection.FORWARD
}
