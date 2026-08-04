package org.firstinspires.ftc.teamcode.pedroPathing

import com.bylazar.configurables.annotations.Configurable
import com.pedropathing.follower.Follower
import com.pedropathing.follower.FollowerConstants
import com.pedropathing.ftc.FollowerBuilder
import com.pedropathing.ftc.drivetrains.MecanumConstants
import com.pedropathing.ftc.localization.constants.PinpointConstants
import com.pedropathing.paths.PathConstraints
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit
import org.firstinspires.ftc.teamcode.config.DriveConfig

@Configurable
object Constants {
    @JvmField
    var followerConstants: FollowerConstants = FollowerConstants().mass(5.0)

    @JvmField
    var pathConstraints: PathConstraints = PathConstraints(0.99, 100.0, 1.0, 1.0)

    @JvmField
    var maxPower: Double = 1.0

    @JvmField
    var localizerConstants: PinpointConstants =
        PinpointConstants()
            .forwardPodY(0.0)
            .strafePodX(0.0)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)

    @JvmStatic
    fun driveConstants(): MecanumConstants =
        MecanumConstants()
            .maxPower(maxPower)
            .leftFrontMotorName(DriveConfig.leftFrontName)
            .leftRearMotorName(DriveConfig.leftRearName)
            .rightFrontMotorName(DriveConfig.rightFrontName)
            .rightRearMotorName(DriveConfig.rightRearName)
            .leftFrontMotorDirection(DriveConfig.leftFrontDirection.toDcMotorDirection())
            .leftRearMotorDirection(DriveConfig.leftRearDirection.toDcMotorDirection())
            .rightFrontMotorDirection(DriveConfig.rightFrontDirection.toDcMotorDirection())
            .rightRearMotorDirection(DriveConfig.rightRearDirection.toDcMotorDirection())

    @JvmStatic
    fun createFollower(hardwareMap: HardwareMap): Follower =
        FollowerBuilder(followerConstants, hardwareMap)
            .pathConstraints(pathConstraints)
            .mecanumDrivetrain(driveConstants())
            .pinpointLocalizer(localizerConstants)
            .build()
}
