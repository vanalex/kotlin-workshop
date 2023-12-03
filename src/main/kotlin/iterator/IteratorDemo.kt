package iterator

@JvmInline
value class Name(val value: String)

data class Player(val name: Name)

class Team(val name: String,
           val players: MutableList<Player> = mutableListOf()) : Iterable<Player> {
    override fun iterator(): Iterator<Player> = players.iterator()

    fun addPlayers(vararg people: Player) = players.addAll(people)
    fun removePlayer(player: Player) {
        if (players.contains(player)) {
            players.remove(player)
        }
    }
}

fun main(){
    val warriors = Team("warriors")
    warriors.addPlayers(Player(Name("Curry")),
        Player(Name("Thompson")),
        Player(Name("Durant")),
        Player(Name("Green")),
        Player(Name("Cousins")))

    for (player in warriors.players) println()

    for (player in warriors) println(player)

}
