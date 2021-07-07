/*
 * Developed as part of the towfense project.
 * This file was last modified at 7/7/21, 3:14 AM.
 * Copyright 2020, see git repository at git.angm.xyz for authors and other info.
 * This file is under the GPL3 license. See LICENSE in the root directory of this repository for details.
 */

package xyz.angm.towfense.ecs

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.actions.Actions
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.utils.Align
import xyz.angm.rox.Engine
import xyz.angm.towfense.ecs.components.DisplayComponent
import xyz.angm.towfense.ecs.components.PathedComponent
import xyz.angm.towfense.ecs.components.PositionComponent
import xyz.angm.towfense.ecs.components.TurretComponent
import xyz.angm.towfense.graphics.actors.Turret
import xyz.angm.towfense.resources.Assets

fun createEnemy(engine: Engine) = engine.entity {
    with<PositionComponent>()
    with<PathedComponent>()
    with<DisplayComponent> {
        actor = Image(Assets.tex("entity/enemy"))
        actor.setSize(1f, 1f)
        actor.setOrigin(Align.center)
        actor.addAction(Actions.repeat(-1, Actions.rotateBy(360f, 1f)))
    }
}

fun createTurret(engine: Engine, pos: Vector2) = engine.entity {
    with<PositionComponent> { set(pos) }
    val turret = with<TurretComponent>()
    with<DisplayComponent> {
        actor = Turret(turret)
        actor.setSize(1f, 1f)
    }
}