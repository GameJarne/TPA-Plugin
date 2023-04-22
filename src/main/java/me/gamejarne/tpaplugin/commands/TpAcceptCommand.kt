package me.gamejarne.tpaplugin.commands

import me.gamejarne.tpaplugin.TpaManager
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TpAcceptCommand(_tpaManager: TpaManager) : CommandExecutor
{
    private val tpaManager: TpaManager = _tpaManager

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean
    {
        val player: Player? = sender as? Player

        if (player == null)
        {
            sender.sendMessage("${ChatColor.RED}Only players can execute this command!")
            return true
        }

        if (args.isEmpty())
        {
            player.sendMessage("${ChatColor.RED}You must enter whose tpa-request you want to accept!")
            return false
        }

        val from: Player? = Bukkit.getPlayer(args[0])

        if (from == null)
        {
            player.sendMessage("${ChatColor.RED}That player is offline or doesn't exist!")
            return true
        }

        tpaManager.acceptRequest(player, from)
        return true
    }
}