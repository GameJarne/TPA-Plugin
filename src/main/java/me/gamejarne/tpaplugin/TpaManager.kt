package me.gamejarne.tpaplugin

import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.ComponentBuilder
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import kotlin.collections.HashMap
import java.util.UUID

class TpaManager
{
    public val requests = HashMap<UUID, TpaRequest>()

    public fun sendRequest(from: Player, to: Player)
    {
        if (from.uniqueId.equals(to.uniqueId))
        {
            from.sendMessage(ChatColor.RED + "You can't send a teleport request to yourself!")
            return
        }

        for (requestKey in requests.keys)
        {
            if (from.uniqueId.equals(requestKey))
            {
                requests.remove(requestKey)
            }
        }

        requests.put(from.uniqueId, TpaRequest(from, to))
        from.sendMessage("${ChatColor.GREEN}Sent tpa-request to ${ChatColor.AQUA}${to.name}")

        val accept: TextComponent = TextComponent("${ChatColor.GREEN}[Accept]")
        accept.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentBuilder("Accept the tpa-request.").create())
        accept.clickEvent = ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tpaccept ${from.name}")

        to.sendMessage("${ChatColor.AQUA}You have received a tpa-request from ${ChatColor.GOLD}${from.name}${ChatColor.AQUA}.")
        to.spigot().sendMessage(accept)
    }

    public fun acceptRequest(player: Player, from: Player)
    {
        val request: TpaRequest? = requests.get(from.uniqueId)

        if (request == null)
        {
            player.sendMessage("${ChatColor.RED}You don't have a tpa-request from that player!")
            return
        }

        val teleportTo: Player = request.getTo()
        from.teleport(teleportTo)

        from.sendMessage("${ChatColor.AQUA}${player.name} ${ChatColor.GREEN}has accepted your tpa-request!")

        requests.remove(from.uniqueId)
    }
}
