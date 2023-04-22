package me.gamejarne.tpaplugin.commands

import me.gamejarne.tpaplugin.TpaManager
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TpaCommand(_tpaManager: TpaManager) : CommandExecutor
{
    private val tpaManager: TpaManager = _tpaManager

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        val player: Player? = sender as? Player

        if (player == null)
        {
            sender.sendMessage("${ChatColor.RED}Only players can execute this command.")
            return true
        }

        if (args.isEmpty())
        {
            return false
        }

        val to: Player? = Bukkit.getPlayer(args[0])

        if (to == null)
        {
            player.sendMessage("${ChatColor.RED}This player is not online!")
            return true
        }

        tpaManager.sendRequest(player, to)

        return true
    }

}