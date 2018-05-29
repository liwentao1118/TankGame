package model

import business.DistoryAble
import org.itheima.kotlin.game.core.Painter

class Blast(override var x:Int,override var y:Int):DistoryAble {
    override fun needDistroy(): Boolean {
       return index >=blastlist.size
    }

    override fun draw() {
        //如果角标为集合遍历完了的时候角标重新置为0,防止角标越界
        index = index%blastlist.size
        var imgPath = blastlist[index]
        Painter.drawImage(imgPath,x,y)
        index++
    }

    override val level: Int =2
    override var width: Int = Config.blocksize
    override var height: Int =Config.blocksize
    val blastlist = ArrayList<String>()
    var index = 0
    init {
        (1..32).forEach {
            blastlist.add("img/blast_${it}.png")
        }
    }

}