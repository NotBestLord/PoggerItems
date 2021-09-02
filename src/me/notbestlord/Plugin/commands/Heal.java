package me.notbestlord.Plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.ItemManager;

public class Heal implements CommandExecutor{
	
	private Main plugin;
	
	public Heal(Main plugin) {
		this.plugin=plugin;
		plugin.getCommand("heal").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Error");
			return true;
		}
		Player p = (Player) sender;
		
		if(p.hasPermission("Heal.use")) {
			p.setHealth(20);
			p.setFoodLevel(20);
			p.setSaturation(20.0f);
			return true;
		}else {
			sender.sendMessage("No Permission");
		}
		return false;
	}

}
