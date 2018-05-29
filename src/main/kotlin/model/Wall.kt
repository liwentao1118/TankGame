package model

import business.Attackable
import business.BlockAble
import business.DistoryAble
import business.Sufferable
import org.itheima.kotlin.game.core.Painter
import javax.swing.text.View

class Wall(override var x :Int ,override var y:Int):BlockAble,Sufferable,DistoryAble{
    override fun needDistroy(): Boolean {
        return blood<=0
    }

    override var blood: Int=3

    override fun notifyAttack(attack: Attackable):Array<Element> {
        blood -=attack.attackpower
        return arrayOf(Blast(x,y))
    }

    override val level: Int = 2
    override var width: Int = Config.blocksize

    override var height: Int = Config.blocksize

    override fun draw(){
       Painter.drawImage("img/wall.gif",x,y)
    }
}