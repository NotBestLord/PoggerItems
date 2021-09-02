package me.notbestlord.Plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.ItemManager;

public class PortableSmelteryGive implements CommandExecutor{
	
	private Main plugin;
	
	public PortableSmelteryGive(Main plugin) {
		this.plugin=plugin;
		plugin.getCommand("portablesmelterygive").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Error");
			return true;
		}
		Player p = (Player) sender;
		
		if(p.hasPermission("PortableSmelteryGive.use")) {
			p.getInventory().addItem(ItemManager.Portablesmeltery);
			return true;
		}else {
			sender.sendMessage("No Permission");
		}
		return false;
	}

}
