package model

import business.*
import enums.Direction
import org.itheima.kotlin.game.core.Painter


class Bullet( var direction: Direction, block: (Int, Int) -> Pair<Int, Int>) :  AutoMoveable,DistoryAble ,Attackable{
    override val attackpower: Int = 1
    var needDistory = false

    override fun checkAttack(suffer: Sufferable): Boolean {
        if (y >= suffer.y + suffer.height || y + height <= suffer.y || suffer.x + suffer.width <= x || x + width <= suffer.x) {
            return false
        }
        return true
    }

    override fun nitifyAttack(suffer:Sufferable) {
        needDistory = true
    }

    override fun needDistroy(): Boolean{
        if (x <0 || y <0 || x > 13* Config.blocksize ||y>13*Config.blocksize)
            return true
        if(needDistory)
            return true
        return false
    }

    override fun automove() {
        when (direction) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }
    }

    override var speed: Int = 32
    override var x: Int = 0
    override var y: Int = 0


    override val level: Int = 2
    override var width: Int = 0
    override var height: Int = 0

    override fun draw() = Painter.drawImage(path, x, y)


    val path by lazy {
        when (direction) {
            Direction.UP -> "img/bullet_u.gif"
            Direction.DOWN -> "img/bullet_d.gif"
            Direction.LEFT -> "img/bullet_l.gif"
            Direction.RIGHT -> "img/bullet_r.gif"
        }
    }

    init {
        val sizes = Painter.size(path)
        width = sizes[0]
        height = sizes[1]
        val pair = block.invoke(width, height)
        x = pair.first
        y = pair.second


    }
}