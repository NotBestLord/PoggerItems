package me.notbestlord.Plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.notbestlord.Plugin.Main;

public class MaxRF implements CommandExecutor{
	
	private Main plugin;
	
	public MaxRF(Main plugin) {
		this.plugin=plugin;
		plugin.getCommand("maxrf").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Error");
			return true;
		}
		Player p = (Player) sender;
		
		if(p.hasPermission("MaxRF.use")){
			Main.RedstoneFluxManager.setRedstoneFlux(p, Main.RedstoneFluxManager.getMaxRedstoneFlux(p));
			return true;
		}else {
			sender.sendMessage("No Permission");
		}
		return false;
	}

}
