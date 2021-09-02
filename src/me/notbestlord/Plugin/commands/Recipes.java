package me.notbestlord.Plugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.craftingsystems.RecipeBookInventory;
import me.notbestlord.Plugin.items.ItemManager;

public class Recipes implements CommandExecutor{
	
	private Main plugin;
	
	public Recipes(Main plugin) {
		this.plugin=plugin;
		plugin.getCommand("recipes").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Error");
			return true;
		}
		Player p = (Player) sender;
		
		RecipeBookInventory.createRecipeBook(p);
		p.openInventory(RecipeBookInventory.inventory.get(p.getUniqueId()));
		return false;
	}

}
