package model

import business.BlockAble
import business.MoveAble
import enums.Direction
import org.itheima.kotlin.game.core.Painter

class Tank(override var x: Int, override var y: Int) : MoveAble {
    override var baddirection: Direction? = null
    override var badblock: BlockAble? = null
    override var width: Int = Config.blocksize

    override var height: Int = Config.blocksize

    override val level: Int = 1
    override var direction: Direction = Direction.UP
    override var speed :Int = 16
    override fun notifyCollision(baddirection: Direction?, badblock: BlockAble?) {
        this.badblock = badblock
        this.baddirection = baddirection
    }

    fun move(dir: Direction) {
        if (direction != dir) {
            this.direction = dir
            return
        }

        if (direction == baddirection) return

        when (direction) {
            Direction.UP -> y -= speed
            Direction.DOWN -> y += speed
            Direction.LEFT -> x -= speed
            Direction.RIGHT -> x += speed
        }

    }

    override fun draw() {
        when (direction) {
            Direction.UP -> Painter.drawImage("img/tank_u.gif", x, y)
            Direction.DOWN -> Painter.drawImage("img/tank_d.gif", x, y)
            Direction.LEFT -> Painter.drawImage("img/tank_l.gif", x, y)
            Direction.RIGHT -> Painter.drawImage("img/tank_r.gif", x, y)

        }

    }
    fun shot():Bullet{
        var bulletX = 0
        var bulletY = 0
        return Bullet(direction){bulletWidth,bulletHeight->
            when(direction){
                Direction.UP -> {
                    bulletX = x + (width - bulletWidth) / 2
                    bulletY = y - bulletHeight / 2
                }
                Direction.DOWN -> {
                    bulletX = x + (width - bulletWidth) / 2
                    bulletY = y + height - bulletHeight / 2
                }
                Direction.LEFT -> {
                    bulletX = x - bulletWidth / 2
                    bulletY = y + (height - bulletHeight) / 2

                }
                Direction.RIGHT -> {
                    bulletX = x + width - bulletWidth / 2
                    bulletY = y + (height - bulletHeight) / 2
                }
            }
            bulletX to bulletY
        }
    }
}