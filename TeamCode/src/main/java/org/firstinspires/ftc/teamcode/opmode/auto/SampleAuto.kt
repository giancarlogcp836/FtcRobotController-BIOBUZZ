package org.firstinspires.ftc.teamcode.opmode.auto

import com.pedropathing.geometry.BezierCurve
import com.pedropathing.geometry.BezierLine
import com.pedropathing.geometry.Pose
import com.pedropathing.ivy.Command
import com.pedropathing.ivy.groups.Groups
import com.pedropathing.paths.PathChain
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.opmode.PedroCommandOpMode

@Autonomous(name = "Sample Auto", group = "Samples")
class SampleAuto : PedroCommandOpMode() {
    private val startPose = Pose(22.0, 122.0, Math.toRadians(324.0))
    private val scorePose = Pose(60.0, 84.0, Math.toRadians(135.0))
    private val pickup1Pose = Pose(17.0, 84.0, Math.toRadians(180.0))
    private val pickup2Pose = Pose(12.0, 60.0, Math.toRadians(180.0))
    private val pickup3Pose = Pose(12.0, 36.0, Math.toRadians(180.0))
    private val endPose = Pose(60.0, 105.0)

    private lateinit var scorePreload: PathChain
    private lateinit var grabPickup1: PathChain
    private lateinit var scorePickup1: PathChain
    private lateinit var grabPickup2: PathChain
    private lateinit var scorePickup2: PathChain
    private lateinit var grabPickup3: PathChain
    private lateinit var scorePickup3: PathChain
    private lateinit var leave: PathChain

    private lateinit var autoRoutine: Command

    override fun onPedroInit() {
        buildPaths()
        drive.setStartingPose(startPose)
        autoRoutine = buildAutoRoutine()
    }

    override fun onPedroStart() {
        autoRoutine.schedule()
    }

    override fun onPedroLoop() {
        val pose = drive.pose
        panelsTelemetry.debug("x: ${pose.x}")
        panelsTelemetry.debug("y: ${pose.y}")
        panelsTelemetry.debug("heading: ${pose.heading}")
        panelsTelemetry.update(telemetry)
    }

    private fun buildPaths() {
        scorePreload =
            drive
                .pathBuilder()
                .addPath(BezierLine(startPose, scorePose))
                .setLinearHeadingInterpolation(startPose.heading, scorePose.heading)
                .build()

        grabPickup1 =
            drive
                .pathBuilder()
                .addPath(BezierLine(scorePose, pickup1Pose))
                .setLinearHeadingInterpolation(scorePose.heading, pickup1Pose.heading)
                .build()

        scorePickup1 =
            drive
                .pathBuilder()
                .addPath(BezierLine(pickup1Pose, scorePose))
                .setLinearHeadingInterpolation(pickup1Pose.heading, scorePose.heading)
                .build()

        grabPickup2 =
            drive
                .pathBuilder()
                .addPath(BezierCurve(scorePose, Pose(60.0, 54.0), pickup2Pose))
                .setLinearHeadingInterpolation(scorePose.heading, pickup2Pose.heading)
                .build()

        scorePickup2 =
            drive
                .pathBuilder()
                .addPath(BezierCurve(pickup2Pose, Pose(60.0, 54.0), scorePose))
                .setLinearHeadingInterpolation(pickup2Pose.heading, scorePose.heading)
                .build()

        grabPickup3 =
            drive
                .pathBuilder()
                .addPath(BezierCurve(scorePose, Pose(60.0, 30.0), pickup3Pose))
                .setLinearHeadingInterpolation(scorePose.heading, pickup3Pose.heading)
                .build()

        scorePickup3 =
            drive
                .pathBuilder()
                .addPath(BezierCurve(pickup3Pose, Pose(60.0, 30.0), scorePose))
                .setLinearHeadingInterpolation(pickup3Pose.heading, scorePose.heading)
                .build()

        leave =
            drive
                .pathBuilder()
                .addPath(BezierLine(scorePose, endPose))
                .setConstantHeadingInterpolation(scorePose.heading)
                .build()
    }

    private fun buildAutoRoutine(): Command =
        Groups.sequential(
            drive.follow(scorePreload),
            drive.follow(grabPickup1, holdEnd = true),
            drive.follow(scorePickup1, holdEnd = true),
            drive.follow(grabPickup2, holdEnd = true),
            drive.follow(scorePickup2, holdEnd = true),
            drive.follow(grabPickup3, holdEnd = true),
            drive.follow(scorePickup3, holdEnd = true),
            drive.follow(leave, holdEnd = true),
        )
}
