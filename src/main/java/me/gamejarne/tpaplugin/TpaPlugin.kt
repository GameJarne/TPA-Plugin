package me.gamejarne.tpaplugin

import me.gamejarne.tpaplugin.commands.TpAcceptCommand
import me.gamejarne.tpaplugin.commands.TpaCommand
import org.bukkit.plugin.java.JavaPlugin

class TpaPlugin : JavaPlugin()
{
    private val tpaManager: TpaManager = TpaManager()

    override fun onEnable()
    {
        getCommand("tpa")?.setExecutor(TpaCommand(tpaManager))
        getCommand("tpaccept")?.setExecutor(TpAcceptCommand(tpaManager))
    }
}