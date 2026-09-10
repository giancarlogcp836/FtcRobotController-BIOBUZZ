package org.firstinspires.ftc.teamcode.subsystems

import com.pedropathing.drivetrain.DrivePowers
import com.pedropathing.follower.ManualDrive
import com.pedropathing.ivy.Command
import com.pedropathing.ivy.commands.Commands
import com.pedropathing.math.Pose
import org.firstinspires.ftc.teamcode.config.BindingsConfig
import org.firstinspires.ftc.teamcode.config.DriveConfig
import org.firstinspires.ftc.teamcode.pedroPathing.Constants
import org.firstinspires.ftc.teamcode.state.managers.BindingManager
import org.firstinspires.ftc.teamcode.state.managers.HardwareManager

class Drive(
    hardwareManager: HardwareManager,
) {
    val follower = Constants.createFollower(hardwareManager.hardwareMap)

    val pose: Pose get() = follower.pose()

    fun update() {
        follower.update()
    }

    fun setPowers(powers: DrivePowers) {
        follower.manual(powers)
    }

    fun setPowers(
        forward: Double,
        strafe: Double,
        turn: Double,
    ) {
        follower.manual(forward, strafe, turn)
    }

    fun setPowers(
        forward: Double,
        strafe: Double,
        turn: Double,
        robotCentric: Boolean = false,
    ) {
        if (robotCentric) {
            follower.manual(ManualDrive.fieldCentric(forward, strafe, turn, pose.heading()))
        } else {
            follower.manual(forward, strafe, turn)
        }
    }

    fun stop() {
        setPowers(DrivePowers.zero())
        follower.stop()
    }

    fun teleopDrive(
        bindings: BindingManager,
        robotCentric: Boolean = true,
    ): Command =
        Commands
            .infinite {
                val forward = bindings.readAnalog(BindingsConfig.driveY).toDouble() * DriveConfig.maxSpeed
                val strafe =
                    bindings
                        .readAnalog(BindingsConfig.driveX)
                        .toDouble() * DriveConfig.maxSpeed * DriveConfig.strafeMultiplier
                val turn =
                    bindings
                        .readAnalog(BindingsConfig.driveTurn)
                        .toDouble() * DriveConfig.maxSpeed * DriveConfig.turnMultiplier

                setPowers(forward, strafe, turn, robotCentric)
            }.setEnd { stop() }
            .requiring(this)
}
