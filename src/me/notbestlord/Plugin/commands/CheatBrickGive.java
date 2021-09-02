package me.notbestlord.Plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.ItemManager;

public class CheatBrickGive implements CommandExecutor{
	
	private Main plugin;
	
	public CheatBrickGive(Main plugin) {
		this.plugin=plugin;
		plugin.getCommand("cheatbrickgive").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Error");
			return true;
		}
		Player p = (Player) sender;
		
		if(p.hasPermission("CheatBrickGive.use")) {
			p.getInventory().addItem(ItemManager.CheatBrick);
			return true;
		}else {
			sender.sendMessage("No Permission");
		}
		return false;
	}

}
