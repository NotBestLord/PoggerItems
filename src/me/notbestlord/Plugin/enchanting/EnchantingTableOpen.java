package me.notbestlord.Plugin.enchanting;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.notbestlord.Plugin.Main;

public class EnchantingTableOpen implements CommandExecutor{
	
	private Main plugin;
	
	public EnchantingTableOpen(Main plugin) {
		this.plugin=plugin;
		plugin.getCommand("enchantingtableopen").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Error");
			return true;
		}
		Player p = (Player) sender;
		
		if(p.hasPermission("EnchantingTableOpen.use")) {
			p.openInventory(EnchantingTableInventory.inventoryaccess.get(p.getUniqueId()));
			return true;
		}else {
			sender.sendMessage("No Permission");
		}
		return false;
	}

}
