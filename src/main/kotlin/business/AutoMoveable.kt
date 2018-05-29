package business

import model.Element


interface AutoMoveable: Element {
    fun automove()
    val speed:Int
}