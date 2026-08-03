package org.firstinspires.ftc.teamcode.config

import com.bylazar.configurables.annotations.Configurable
import org.firstinspires.ftc.teamcode.state.GamepadId

/**
 * Panels-owned default gamepad bindings for teleop.
 */
@Configurable
object BindingsConfig {
    @JvmField
    var driveX: AnalogBinding =
        AnalogBinding(
            gamepad = GamepadId.ONE,
            action = AnalogAction.LEFT_STICK_X,
            deadZone = 0.05f,
            scale = 1f,
        )

    @JvmField
    var driveY: AnalogBinding =
        AnalogBinding(
            gamepad = GamepadId.ONE,
            action = AnalogAction.LEFT_STICK_Y,
            deadZone = 0.05f,
            scale = 1f,
        )

    @JvmField
    var driveTurn: AnalogBinding =
        AnalogBinding(
            gamepad = GamepadId.ONE,
            action = AnalogAction.RIGHT_STICK_X,
            deadZone = 0.05f,
            scale = 1f,
        )

    @JvmField
    var armManual: AnalogBinding =
        AnalogBinding(
            gamepad = GamepadId.TWO,
            action = AnalogAction.LEFT_STICK_Y,
            deadZone = 0.1f,
            scale = 1f,
        )

    @JvmField
    var armScore: BinaryBinding =
        BinaryBinding(
            gamepad = GamepadId.TWO,
            action = BinaryAction.TRIANGLE,
        )

    @JvmField
    var armStow: BinaryBinding =
        BinaryBinding(
            gamepad = GamepadId.TWO,
            action = BinaryAction.CROSS,
        )
}
