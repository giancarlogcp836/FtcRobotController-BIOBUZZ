package org.firstinspires.ftc.teamcode.exceptions

import kotlin.reflect.KClass

class HardwareMissingException(
    id: String,
    type: KClass<*>,
) : Exception("Hardware $id (${type.simpleName}) was not found!")
