/*
 * Developed as part of the towfense project.
 * This file was last modified at 7/7/21, 2:42 AM.
 * Copyright 2020, see git repository at git.angm.xyz for authors and other info.
 * This file is under the GPL3 license. See LICENSE in the root directory of this repository for details.
 */

package xyz.angm.towfense.ecs.systems

import xyz.angm.rox.Entity
import xyz.angm.rox.Family
import xyz.angm.rox.systems.IteratingSystem
import xyz.angm.towfense.ecs.components.PathedComponent
import xyz.angm.towfense.ecs.components.PositionComponent
import xyz.angm.towfense.ecs.pathed
import xyz.angm.towfense.ecs.position
import xyz.angm.towfense.level.DIR
import xyz.angm.towfense.level.Direction
import xyz.angm.towfense.level.LEN
import xyz.angm.towfense.level.Path
import java.lang.Integer.min

private const val SPEED = 3f

class PathMovementSystem(private val path: Path) : IteratingSystem(Family.allOf(PathedComponent::class, PositionComponent::class)) {

    override fun process(entity: Entity, delta: Float) {
        val posC = entity[position]
        val pathC = entity[pathed]
        pathC.distTravelled += delta * SPEED

        val segment = path.segments[pathC.segment]
        Direction.add(posC, segment[DIR], delta * SPEED)

        if (pathC.distTravelled >= (segment[LEN] + 1)) {
            pathC.segment++
            pathC.segment = min(path.segments.size - 1, pathC.segment)
            pathC.distTravelled = 0f
        }
    }
}