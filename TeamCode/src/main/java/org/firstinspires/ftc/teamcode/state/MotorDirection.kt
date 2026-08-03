package org.firstinspires.ftc.teamcode.state

/*
    The way a motor spins: FORWARD, or REVERSE.
 */
enum class MotorDirection(val coefficient: Double) {
    FORWARD(1.0), REVERSE(-1.0);

    /**
     * Apply this [org.firstinspires.ftc.teamcode.state.MotorDirection] to a given `power`
     */
    fun apply(power: Double) = power * coefficient
}
