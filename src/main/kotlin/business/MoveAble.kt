package business

import enums.Direction

import model.Element


interface MoveAble : Element {
    var direction:Direction
    var speed:Int
    var baddirection:Direction?
    var badblock :BlockAble?

    fun notifyCollision(baddirection:Direction?,badblock:BlockAble?){
        this.baddirection = baddirection
        this.badblock = badblock
    }

    fun willCollision(block:BlockAble):Direction?{

        var x = this.x
        var y = this.y
        when(direction){
            Direction.UP->y-=speed
            Direction.DOWN->y+=speed
            Direction.LEFT->x-=speed
            Direction.RIGHT->x+=speed
        }
        if(y >= block.y + block.height || y + height <= block.y || block.x + block.width <= x || x + width <= block.x){
            if(x < 0||x > 12 * Config.blocksize||y < 0||y > 12 * Config.blocksize){
                return direction
            }

            return null
        }

        return direction

    }
}