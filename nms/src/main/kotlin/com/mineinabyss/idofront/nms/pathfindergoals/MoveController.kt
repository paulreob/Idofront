package com.mineinabyss.idofront.nms.pathfindergoals

import net.minecraft.world.entity.ai.control.ControllerMove

val ControllerMove.targetX
    get() = d()
val ControllerMove.targetY
    get() = e()
val ControllerMove.targetZ
    get() = f()
val ControllerMove.speed
    get() = c()

fun ControllerMove.moveTo(x: Double, y: Double, z: Double, speed: Double) = a(x, y, z, speed)
