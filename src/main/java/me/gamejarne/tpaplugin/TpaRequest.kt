package me.gamejarne.tpaplugin

import org.bukkit.entity.Player

class TpaRequest(_from: Player, _to: Player)
{
    private val from : Player = _from
    private val to : Player = _to

    fun getFrom(): Player
    {
        return from
    }

    fun getTo(): Player
    {
        return to
    }
}