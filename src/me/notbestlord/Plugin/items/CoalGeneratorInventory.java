package me.notbestlord.Plugin.items;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class CoalGeneratorInventory {
	
	public static Dictionary<UUID, Inventory> inventory = new Hashtable<>();
	public static Dictionary<UUID, Boolean> coalgeneratoractivness = new Hashtable<>();
	
	public static void createCoalGenerator(Player player) {
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.AQUA+"Coal Generator");
		inventory.put(player.getUniqueId(), inv);
		coalgeneratoractivness.put(player.getUniqueId(), false);
		
	}
}
