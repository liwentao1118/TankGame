package model

import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Painter
import org.itheima.kotlin.game.core.Window

class GameWindow : Window(title= "坦克大战",icon = "img/logo.jpg",width = config.gamewidth,height = config.gameheight) {
    override fun onCreate() {

    }

    override fun onDisplay() {
        Painter.drawImage("img/wall.gif",100,100)

    }

    override fun onKeyPressed(event: KeyEvent) {

    }

    override fun onRefresh() {

    }


}