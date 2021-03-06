package model

import business.BlockAble
import org.itheima.kotlin.game.core.Painter

class Steel(override val x: Int, override val y: Int) :BlockAble {
    override val level: Int = 2
    override var width: Int = Config.blocksize

    override var height: Int = Config.blocksize

    override fun draw() {
        Painter.drawImage("img/steel.gif",x,y)
    }
}