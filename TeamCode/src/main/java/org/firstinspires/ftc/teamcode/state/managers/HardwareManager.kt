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
     * Attempt to get hardware by its `id`.
     * @return [Result.success] with the hardware if found, or
     * [Result.failure] with the error.
     */
    inline fun <reified T> getHardware(id: String): Result<T> {
        return try {
            val hardware =
                hardwareMap.tryGet(T::class.java, id) ?: return Result.failure(
                    HardwareMissingException(id, T::class),
                )
            Result.success(hardware)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Get hardware by its `id`.
     * @throws HardwareMissingException if missing.
     */
    inline fun <reified T> requireHardware(id: String): T =
        hardwareMap.tryGet(T::class.java, id) ?: throw HardwareMissingException(id, T::class)
}
