package model

import org.itheima.kotlin.game.core.Painter

class Grass(override val x: Int, override val y: Int) :Element {
    override val level: Int = 2
    override var width: Int = Config.blocksize

    override var height: Int = Config.blocksize

    override fun draw() {
        Painter.drawImage("img/grass.gif",x,y)
    }
}