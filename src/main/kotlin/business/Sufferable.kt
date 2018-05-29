package business

import model.Element
import javax.swing.text.View


interface Sufferable :Element {
    val blood:Int
    fun notifyAttack(attack:Attackable):Array<Element>

}