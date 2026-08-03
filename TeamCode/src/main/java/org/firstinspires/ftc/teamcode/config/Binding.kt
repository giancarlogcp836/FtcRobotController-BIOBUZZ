package org.firstinspires.ftc.teamcode.config

import org.firstinspires.ftc.teamcode.state.GamepadId

/**
 * A binary action on a gamepad, such as a button press
 */
enum class BinaryAction {
    CROSS, CIRCLE, TRIANGLE, SQUARE, DPAD_UP, DPAD_DOWN, DPAD_LEFT, DPAD_RIGHT, LEFT_BUMPER, RIGHT_BUMPER, LEFT_STICK_BUTTON, RIGHT_STICK_BUTTON
}

/**
 * An analog action on a gamepad, such as the position of a stick
 */
enum class AnalogAction {
    LEFT_STICK_X, LEFT_STICK_Y, RIGHT_STICK_X, RIGHT_STICK_Y, LEFT_TRIGGER, RIGHT_TRIGGER
}

data class BinaryBinding(val gamepad: GamepadId, val action: BinaryAction)
data class AnalogBinding(
    val gamepad: GamepadId,
    val action: AnalogAction,
    val deadZone: Float = 0f,
    val scale: Float = 1f
)