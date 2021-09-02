package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;

public class VoidOreMinerInventory {
	
	public static Hashtable<Location, Inventory> inventory = new Hashtable<>();
	public static Inventory inv;
	
	public static void createVoidOreMiner(Location location) {
		inv = Bukkit.createInventory(null, 54, ChatColor.AQUA+"Void Ore Miner");
		ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = item1.getItemMeta();
		meta.setDisplayName(" ");
		item1.setItemMeta(meta);
		
		for(int i=0; i<54; i++) {
			inv.setItem(i, item1);
		}
		
		
		item1.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED+"Close");
		item1.setItemMeta(meta);
		inv.setItem(49, item1);
		
		item1.setType(Material.LIME_STAINED_GLASS_PANE);
		meta.setDisplayName(ChatColor.WHITE+"Activate Void Ore Miner");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"Produces random ores and resources every 30 seconds");
		lore.add(ChatColor.GRAY+"at the cost of 7500RF upon resource generation.");
		lore.add(ChatColor.BLUE+"<- Speed Upgrades");
		lore.add(ChatColor.BLUE+"Energy Upgrades ->");
		lore.add(ChatColor.BLUE+"\\/ Lens");
		meta.setLore(lore);
		lore.clear();
		item1.setItemMeta(meta);
		inv.setItem(13, item1);
		
		inv.setItem(11, null);
		inv.setItem(15, null);
		inv.setItem(17, null);
		inv.setItem(26, null);
		inv.setItem(35, null);
		inv.setItem(44, null);
		inv.setItem(31, null);
		
		item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(" ");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "x"), PersistentDataType.INTEGER, location.getBlockX());
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "y"), PersistentDataType.INTEGER, location.getBlockY());
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "z"), PersistentDataType.INTEGER, location.getBlockZ());
		item1.setItemMeta(meta);
		inv.setItem(0, item1);
		
		item1 = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Upgrade slots");
		item1.setItemMeta(meta);
		inv.setItem(8, item1);
		inv.setItem(53, item1);
		
		VoidOreMinerEvents.VoidOreMinerActivness.put(location, 0);
		inventory.put(location, inv);
	}
}
