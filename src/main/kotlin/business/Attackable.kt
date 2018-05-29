package business

interface Attackable {
    val attackpower :Int

    fun checkAttack(suffer:Sufferable):Boolean

    fun nitifyAttack(suffer:Sufferable)
}