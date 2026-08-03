package org.firstinspires.ftc.teamcode.opmode

import com.bylazar.telemetry.PanelsTelemetry
import com.bylazar.telemetry.TelemetryManager
import com.pedropathing.ivy.Scheduler
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import org.firstinspires.ftc.teamcode.state.managers.BindingManager
import org.firstinspires.ftc.teamcode.state.Gamepads
import org.firstinspires.ftc.teamcode.state.managers.HardwareManager

/**
 * Base OpMode that owns the Ivy [Scheduler] lifecycle and shared gamepad bindings.
 */
abstract class CommandOpMode : OpMode() {
    protected lateinit var gamepads: Gamepads
        private set

    protected lateinit var hardware: HardwareManager
        private set

    protected lateinit var bindings: BindingManager
        private set

    protected lateinit var panelsTelemetry: TelemetryManager
        private set

    final override fun init() {
        Scheduler.reset()
        gamepads = Gamepads(gamepad1, gamepad2)
        hardware = HardwareManager(hardwareMap)
        panelsTelemetry = PanelsTelemetry.telemetry
        bindings = BindingManager(gamepads)
        onInit()
    }

    final override fun loop() {
        onLoop()
        Scheduler.execute()
    }

    final override fun stop() {
        onStop()
        Scheduler.reset()
    }

    protected abstract fun onInit()

    protected open fun onLoop() {}

    protected open fun onStop() {}
}
