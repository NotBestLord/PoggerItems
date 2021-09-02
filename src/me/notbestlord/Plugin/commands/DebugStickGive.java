package me.notbestlord.Plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.ItemManager;

public class DebugStickGive implements CommandExecutor{
	
	private Main plugin;
	
	public DebugStickGive(Main plugin) {
		this.plugin=plugin;
		plugin.getCommand("debugstickgive").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Error");
			return true;
		}
		Player p = (Player) sender;
		
		if(p.hasPermission("DebugStickGive.use")) {
			p.getInventory().addItem(ItemManager.DebugStick);
			return true;
		}else {
			sender.sendMessage("No Permission");
		}
		return false;
	}

}
