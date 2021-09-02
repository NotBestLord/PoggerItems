package me.notbestlord.Plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.craftingsystems.SpeciesMenu;
import me.notbestlord.Plugin.items.ItemManager;
import net.md_5.bungee.api.ChatColor;

public class Species implements CommandExecutor{
	
	private Main plugin;
	
	public Species(Main plugin) {
		this.plugin=plugin;
		plugin.getCommand("species").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Error");
			return true;
		}
		Player player = (Player) sender;
		
		if (args.length == 0) {
			player.sendMessage(ChatColor.GOLD+"/Species Choose"+ChatColor.WHITE+" - choose species, only works once");
			player.sendMessage(ChatColor.GOLD+"/Species Info "+ChatColor.WHITE+"- show info about player's current species");
			if(player.hasPermission("Species.Use")) {
				player.sendMessage(ChatColor.GOLD+"/Species Remove "+ChatColor.WHITE+"- remove player species");
				player.sendMessage(ChatColor.GOLD+"/Species Change "+ChatColor.WHITE+"- change species, admin command");
			}
			return true;
		}
		switch (args[0].toLowerCase()) {
		case "choose":
			if(player.hasMetadata("Species")) {
				player.sendMessage(ChatColor.RED+player.getName()+" already has a species.");
			}
			else {
				SpeciesMenu.openMenu(player);
			}
			break;
		case "info":
			if(player.hasMetadata("Species")) {
				String Species = player.getMetadata("Species").get(0).asString();
				switch (Species) {
				case "Human":
					player.sendMessage(ChatColor.WHITE+"You are a Human:");
					for(String st : SpeciesMenu.openMenu(null).getItem(4).getItemMeta().getLore()) {
						player.sendMessage(ChatColor.WHITE+" "+st);
					}
					break;
				case "Florian":
					player.sendMessage(ChatColor.GREEN+"You are a Florian:");
					for(String st : SpeciesMenu.openMenu(null).getItem(9).getItemMeta().getLore()) {
						player.sendMessage(ChatColor.WHITE+" "+st);
					}
					break;
				case "Ape":
					player.sendMessage(ChatColor.LIGHT_PURPLE+"You are an Ape:");
					for(String st : SpeciesMenu.openMenu(null).getItem(10).getItemMeta().getLore()) {
						player.sendMessage(ChatColor.WHITE+" "+st);
					}
					break;
				case "Automaton":
					player.sendMessage(ChatColor.DARK_GRAY+"You are an Automaton:");
					for(String st : SpeciesMenu.openMenu(null).getItem(11).getItemMeta().getLore()) {
						player.sendMessage(ChatColor.WHITE+" "+st);
					}
					break;
				case "Arachnid":
					player.sendMessage(ChatColor.RED+"You are an Arachnid:");
					for(String st : SpeciesMenu.openMenu(null).getItem(12).getItemMeta().getLore()) {
						player.sendMessage(ChatColor.WHITE+" "+st);
					}
					break;
				case "Avian":
					player.sendMessage(ChatColor.GOLD+"You are an Avian:");
					for(String st : SpeciesMenu.openMenu(null).getItem(13).getItemMeta().getLore()) {
						player.sendMessage(ChatColor.WHITE+" "+st);
					}
					break;
				case "Pigman":
					player.sendMessage(ChatColor.RED+"You are a Pigman:");
					for(String st : SpeciesMenu.openMenu(null).getItem(14).getItemMeta().getLore()) {
						player.sendMessage(ChatColor.WHITE+" "+st);
					}
					break;
				case "Lunarian":
					player.sendMessage(ChatColor.RED+"You are a Lunarian:");
					for(String st : SpeciesMenu.openMenu(null).getItem(15).getItemMeta().getLore()) {
						player.sendMessage(ChatColor.WHITE+" "+st);
					}
					break;
				case "Gasumian":
					player.sendMessage(ChatColor.DARK_PURPLE+"You are a Gasumian:");
					for(String st : SpeciesMenu.openMenu(null).getItem(16).getItemMeta().getLore()) {
						player.sendMessage(ChatColor.WHITE+" "+st);
					}
					break;
				case "Hylotel":
					player.sendMessage(ChatColor.BLUE+"You are a Hylotel:");
					for(String st : SpeciesMenu.openMenu(null).getItem(17).getItemMeta().getLore()) {
						player.sendMessage(ChatColor.WHITE+" "+st);
					}
					break;
					
				default:
					break;
				}
			}
			else {
				player.sendMessage(ChatColor.RED+"You have no species.");
			}
			break;
		case "remove":
			if(player.hasPermission("Species.Use")) {
				if(player.hasMetadata("Species")) {
					player.sendMessage(ChatColor.WHITE+"You are no longer "+player.getMetadata("Species").get(0).asString()+".");
					player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
					player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(0);
					player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0);
					player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(4);
					player.setAllowFlight(false);
					player.removeMetadata("Species", Main.getMain());
					if(player.hasMetadata("SpeciesRunnable")) {
						Bukkit.getScheduler().cancelTask(player.getMetadata("SpeciesRunnable").get(0).asInt());
						player.removeMetadata("SpeciesRunnable", Main.getMain());
					}
				}
				else {
					player.sendMessage(ChatColor.RED+"You have no species.");
				}
				break;
			}
			break;
		case "change":
			if(player.hasPermission("Species.Use")) {
				SpeciesMenu.openMenu(player);
			}
			break;
		default:
			player.sendMessage(ChatColor.GOLD+"/Species Choose"+ChatColor.WHITE+" - choose species, only works once");
			player.sendMessage(ChatColor.GOLD+"/Species Info "+ChatColor.WHITE+"- show info about player's current species");
			player.sendMessage(ChatColor.GOLD+"/Species Remove "+ChatColor.WHITE+"- remove player species");
			if(player.hasPermission("Species.Use")) {
				player.sendMessage(ChatColor.GOLD+"/Species Change "+ChatColor.WHITE+"- change species, admin command");
			}
			break;
		}
		return false;
	}

}
