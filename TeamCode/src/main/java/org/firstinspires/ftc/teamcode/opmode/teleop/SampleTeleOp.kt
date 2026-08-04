package org.firstinspires.ftc.teamcode.opmode.teleop

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.config.DriveConfig
import org.firstinspires.ftc.teamcode.opmode.CommandOpMode
import org.firstinspires.ftc.teamcode.subsystems.Drive

@TeleOp(name = "Sample TeleOp", group = "Samples")
class SampleTeleOp : CommandOpMode() {
    private lateinit var drive: Drive

    override fun onInit() {
        drive = Drive(hardware)

        drive.teleopDrive(bindings).schedule()
    }

    override fun onLoop() {
        panelsTelemetry.debug("drive.maxSpeed: ${DriveConfig.maxSpeed}")
        panelsTelemetry.update(telemetry)
    }
}
