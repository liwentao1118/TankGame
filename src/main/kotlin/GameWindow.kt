import business.*
import enums.Direction
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import model.*
import org.itheima.kotlin.game.core.Window
import java.io.File
import java.util.concurrent.CopyOnWriteArrayList


///Users/liwentao/IdeaProjects/Tank1
class GameWindow : Window(title = "坦克大战", icon = "img/logo.jpg", width = Config.gamewidth, height = Config.gameheight) {
    val list = CopyOnWriteArrayList<Element>()
    lateinit var tank: Tank
    override fun onCreate() {
        //获取地图路径
        val path = javaClass.getResource("map/map.txt").path
        val file = File(path)
        val lines = file.readLines()
        lines.forEachIndexed { line, str ->
            str.forEachIndexed { col, e ->
                when (e) {
                    '砖' -> {
                        val wall = Wall(col * Config.blocksize, line * Config.blocksize)
                        list.add(wall)
                    }
                    '铁' -> {
                        val steel = Steel(col * Config.blocksize, line * Config.blocksize)
                        list.add(steel)

                    }
                    '水' -> {
                        val water = Water(col * Config.blocksize, line * Config.blocksize)
                        list.add(water)

                    }
                    '草' -> {
                        val grass = Grass(col * Config.blocksize, line * Config.blocksize)
                        list.add(grass)

                    }
                    '我' -> {
                        tank = Tank(col * Config.blocksize, line * Config.blocksize)
                        list.add(tank)
                    }
                }
            }
        }
        list.sortBy {
            it.level
        }

    }

    override fun onDisplay() {
        list.forEach {
            it.draw()
        }
    }

    override fun onKeyPressed(event: KeyEvent) {
        when (event.code) {
            KeyCode.W -> tank.move(Direction.UP)
            KeyCode.S -> tank.move(Direction.DOWN)
            KeyCode.A -> tank.move(Direction.LEFT)
            KeyCode.D -> tank.move(Direction.RIGHT)
            KeyCode.SPACE-> {
                val bullet:Bullet = tank.shot()
                list.add(bullet)
            }
        }
    }

    override fun onRefresh() {
        //销毁检测
        val distorylist = list.filter { it is DistoryAble }
        distorylist.forEach {
            it as DistoryAble
            if(it.needDistroy())
                list.remove(it)
            println(list.size)
        }
        //移动和碰撞检测
        val blocklist = list.filter { it is BlockAble }
        val movelist = list.filter { it is MoveAble }
        for (move in movelist){
            move as MoveAble
            var badblock:BlockAble? = null
            var baddirection:Direction? = null
           for (block in blocklist) {
                block as BlockAble
                val direction = move.willCollision(block)
                if(direction!=null){
                    badblock = block
                    baddirection = direction
                    break
                }

            }
           move.notifyCollision(baddirection,badblock)

        }
        //自主移动的方法实现
        val autoMovelist = list.filter { it is AutoMoveable }
        autoMovelist.forEach {
            it as AutoMoveable
            it.automove()
        }
        //攻击和被攻击检测
        val sufferList = list.filter { it is Sufferable }
        val attackList = list.filter { it is Attackable }
        for (attack in attackList){
            attack as Attackable
            for (suffer in sufferList){
                suffer as Sufferable
                val result = attack.checkAttack(suffer)
                if(result){
                    attack.nitifyAttack(suffer)
                    val blast = suffer.notifyAttack(attack)
                    blast.let { list.addAll(it) }
                }
            }
        }
        //销毁检测
        val distroyCheckList = list.filter { it is DistoryAble }
        distroyCheckList.forEach {
            it as DistoryAble
            if(it.needDistroy()){
                list.remove(it)
            }
        }

    }









}