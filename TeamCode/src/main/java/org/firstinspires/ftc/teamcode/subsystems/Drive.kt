package org.firstinspires.ftc.teamcode.subsystems

import com.pedropathing.ivy.Command
import com.pedropathing.ivy.commands.Commands
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.config.BindingsConfig
import org.firstinspires.ftc.teamcode.config.DriveConfig
import org.firstinspires.ftc.teamcode.state.BindingManager
import kotlin.math.abs
import kotlin.math.max

/**
 * Ivy-owned drive subsystem. Tunable values live in [DriveConfig] / [BindingsConfig].
 */
class Drive(
    hardwareMap: HardwareMap,
) {
    private val leftFront: DcMotorEx = hardwareMap.get(DcMotorEx::class.java, DriveConfig.leftFrontName)
    private val leftRear: DcMotorEx = hardwareMap.get(DcMotorEx::class.java, DriveConfig.leftRearName)
    private val rightFront: DcMotorEx = hardwareMap.get(DcMotorEx::class.java, DriveConfig.rightFrontName)
    private val rightRear: DcMotorEx = hardwareMap.get(DcMotorEx::class.java, DriveConfig.rightRearName)

    init {
        listOf(leftFront, leftRear, rightFront, rightRear).forEach { motor ->
            motor.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
            motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        }
        applyDirections()
    }

    private fun applyDirections() {
        leftFront.direction = DriveConfig.leftFrontDirection.toDcMotorDirection()
        leftRear.direction = DriveConfig.leftRearDirection.toDcMotorDirection()
        rightFront.direction = DriveConfig.rightFrontDirection.toDcMotorDirection()
        rightRear.direction = DriveConfig.rightRearDirection.toDcMotorDirection()
    }

    fun setPowers(
        forward: Double,
        strafe: Double,
        turn: Double,
    ) {
        applyDirections()

        val fl = forward + strafe + turn
        val fr = forward - strafe - turn
        val bl = forward - strafe + turn
        val br = forward + strafe - turn

        val max = max(1.0, max(abs(fl), max(abs(fr), max(abs(bl), abs(br)))))
        leftFront.power = fl / max
        rightFront.power = fr / max
        leftRear.power = bl / max
        rightRear.power = br / max
    }

    fun stop() {
        setPowers(0.0, 0.0, 0.0)
    }

    /**
     * Continuous teleop field-relative-style mecanum drive from [BindingsConfig] sticks.
     * Speeds are read live from [DriveConfig] each loop.
     */
    fun teleopDrive(bindings: BindingManager): Command =
        Commands
            .infinite {
                val forward = bindings.readAnalog(BindingsConfig.driveY).toDouble() * DriveConfig.maxSpeed
                val strafe =
                    bindings.readAnalog(BindingsConfig.driveX).toDouble() *
                        DriveConfig.maxSpeed *
                        DriveConfig.strafeMultiplier
                val turn =
                    bindings.readAnalog(BindingsConfig.driveTurn).toDouble() *
                        DriveConfig.maxSpeed *
                        DriveConfig.turnMultiplier
                setPowers(forward, strafe, turn)
            }.setEnd { stop() }
            .requiring(this)

    fun stopCommand(): Command = Commands.instant { stop() }.requiring(this)
}
