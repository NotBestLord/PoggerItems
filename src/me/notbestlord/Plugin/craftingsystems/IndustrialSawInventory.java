package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class IndustrialSawInventory {
	
	public static HashMap<Location, Inventory> inventory = new HashMap<>();
	public static Inventory inv;
	
	public static void createIndustrialSaw(Location location) {
		inv = Bukkit.createInventory(null, 27, ChatColor.AQUA+"Industrial Saw");
		ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = item1.getItemMeta();
		meta.setDisplayName(" ");
		item1.setItemMeta(meta);
		
		for(int i = 0; i<27; i++) {
			inv.setItem(i, item1);
		}
		
		inv.setItem(10, new ItemStack(Material.AIR));
		inv.setItem(16, new ItemStack(Material.AIR));
		

		item1.setType(Material.WHITE_STAINED_GLASS_PANE);
		inv.setItem(4, item1);
		
		item1.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED+"Close");
		item1.setItemMeta(meta);
		inv.setItem(22, item1);
		
		item1.setType(Material.LIME_STAINED_GLASS_PANE);
		meta.setDisplayName(ChatColor.WHITE+"Use Industrial Saw");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.WHITE+"<- Input");
		lore.add(ChatColor.WHITE+"Output ->");
		meta.setLore(lore);
		lore.clear();
		item1.setItemMeta(meta);
		inv.setItem(13, item1);
		
		item1 = new ItemStack(Material.STONECUTTER);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Industrial Saw");
		
		inventory.put(location, inv);
	}
}
