package org.firstinspires.ftc.teamcode.subsystems

import com.pedropathing.follower.Follower
import com.pedropathing.geometry.BezierLine
import com.pedropathing.geometry.Pose
import com.pedropathing.ivy.Command
import com.pedropathing.ivy.commands.Commands
import com.pedropathing.ivy.pedro.PedroCommands
import com.pedropathing.paths.PathBuilder
import com.pedropathing.paths.PathChain
import com.pedropathing.paths.PathConstraints
import org.firstinspires.ftc.teamcode.config.BindingsConfig
import org.firstinspires.ftc.teamcode.config.DriveConfig
import org.firstinspires.ftc.teamcode.pedroPathing.Constants
import org.firstinspires.ftc.teamcode.state.managers.BindingManager
import org.firstinspires.ftc.teamcode.state.managers.HardwareManager

class Drive(
    hardwareManager: HardwareManager,
) {
    val follower: Follower = Constants.createFollower(hardwareManager.hardwareMap)

    val pose: Pose
        get() = follower.pose

    fun update() {
        follower.update()
    }

    fun setStartingPose(pose: Pose) {
        follower.setStartingPose(pose)
    }

    fun pathBuilder(): PathBuilder = follower.pathBuilder()

    fun pathBuilder(constraints: PathConstraints): PathBuilder = follower.pathBuilder(constraints)

    fun setPowers(
        forward: Double,
        strafe: Double,
        turn: Double,
        robotCentric: Boolean = true,
    ) {
        follower.setTeleOpDrive(forward, strafe, turn, robotCentric)
    }

    fun stop() {
        follower.startTeleopDrive(true)
        follower.setTeleOpDrive(0.0, 0.0, 0.0, true)
    }

    fun teleopDrive(
        bindings: BindingManager,
        robotCentric: Boolean = true,
    ): Command =
        Commands
            .infinite {
                if (!follower.isTeleopDrive) {
                    follower.startTeleopDrive(true)
                }
                val forward = bindings.readAnalog(BindingsConfig.driveY).toDouble() * DriveConfig.maxSpeed
                val strafe =
                    bindings.readAnalog(BindingsConfig.driveX).toDouble() *
                        DriveConfig.maxSpeed *
                        DriveConfig.strafeMultiplier
                val turn =
                    bindings.readAnalog(BindingsConfig.driveTurn).toDouble() *
                        DriveConfig.maxSpeed *
                        DriveConfig.turnMultiplier
                setPowers(forward, strafe, turn, robotCentric)
            }.setEnd { stop() }
            .requiring(this)

    fun stopCommand(): Command = Commands.instant { stop() }.requiring(this)

    fun follow(
        pathChain: PathChain,
        holdEnd: Boolean = false,
    ): Command = PedroCommands.follow(follower, pathChain, holdEnd).requiring(this)

    fun follow(
        pathChain: PathChain,
        holdEnd: Boolean,
        maxPower: Double,
    ): Command = PedroCommands.follow(follower, pathChain, holdEnd, maxPower).requiring(this)

    fun goTo(
        destination: Pose,
        holdEnd: Boolean = false,
    ): Command =
        Commands
            .lazy {
                val start = pose
                follow(
                    pathBuilder()
                        .addPath(BezierLine(start, destination))
                        .setLinearHeadingInterpolation(start.heading, destination.heading)
                        .build(),
                    holdEnd,
                )
            }.requiring(this)

    fun hold(): Command = PedroCommands.hold(follower).requiring(this)

    fun hold(pose: Pose): Command = PedroCommands.hold(follower, pose).requiring(this)

    fun hold(
        pose: Pose,
        constraints: PathConstraints,
    ): Command = PedroCommands.hold(follower, pose, constraints).requiring(this)

    fun turnTo(radians: Double): Command = PedroCommands.turnTo(follower, radians).requiring(this)

    fun turnTo(
        radians: Double,
        constraints: PathConstraints,
    ): Command = PedroCommands.turnTo(follower, radians, constraints).requiring(this)
}
