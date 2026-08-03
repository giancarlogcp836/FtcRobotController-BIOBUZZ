package org.firstinspires.ftc.teamcode.state.managers

import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.teamcode.exceptions.HardwareMissingException

/**
 * A wrapper around [HardwareMap] with our custom exceptions.
 */
class HardwareManager(
    val hardwareMap: HardwareMap,
) {
    /**
     * Attempt to get hardware by ID.
     * @throws HardwareMissingException if the hardware is not found
     */
    inline fun <reified T> getHardware(id: String): Result<T> {
        return try {
            val hardware =
                hardwareMap.tryGet(T::class.java, id)
                    ?: return Result.failure(HardwareMissingException(id))
            Result.success(hardware)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
