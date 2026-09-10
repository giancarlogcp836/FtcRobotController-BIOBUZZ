package org.firstinspires.ftc.teamcode.opmode.teleop

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.config.DriveConfig
import org.firstinspires.ftc.teamcode.opmode.PedroCommandOpMode

@TeleOp(name = "Sample TeleOp", group = "Samples")
class SampleTeleOp : PedroCommandOpMode() {
    override fun onPedroInit() {
        drive.teleopDrive(bindings).schedule()
    }

    override fun onPedroLoop() {
        val pose = drive.pose
        panelsTelemetry.debug("drive.maxSpeed: ${DriveConfig.maxSpeed}")
        panelsTelemetry.debug("x: ${pose.x()}")
        panelsTelemetry.debug("y: ${pose.y()}")
        panelsTelemetry.debug("heading: ${pose.heading()}")
        panelsTelemetry.update(telemetry)
    }
}
