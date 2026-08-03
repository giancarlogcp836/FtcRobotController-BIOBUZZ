package org.firstinspires.ftc.teamcode.opmode

import com.pedropathing.follower.Follower
import com.pedropathing.ivy.Scheduler
import org.firstinspires.ftc.teamcode.pedroPathing.Constants

abstract class PedroCommandOpMode : CommandOpMode() {
    protected lateinit var follower: Follower
        private set

    final override fun onInit() {
        follower = Constants.createFollower(hardwareMap)
        onPedroInit()
    }

    final override fun onLoop() {
        follower.update()
        onPedroLoop()
    }

    protected abstract fun onPedroInit()

    protected open fun onPedroLoop() {}

    override fun start() {
        onPedroStart()
    }

    protected open fun onPedroStart() {}

    override fun onStop() {
        Scheduler.reset()
    }
}
