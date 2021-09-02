package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SolidiferInventory {
	
	public static Hashtable<Location, Inventory> inventory = new Hashtable<>();
	public static Inventory inv;
	
	public static void createSolidifer(Location location) {
		inv = Bukkit.createInventory(null, 27, ChatColor.AQUA+"Solidifier");
		ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = item1.getItemMeta();
		meta.setDisplayName(" ");
		item1.setItemMeta(meta);
		
		inv.setItem(0, item1);
		inv.setItem(1, item1);
		inv.setItem(2, item1);
		inv.setItem(3, item1);
		inv.setItem(5, item1);
		inv.setItem(6, item1);
		inv.setItem(7, item1);
		inv.setItem(8, item1);
		inv.setItem(9, item1);
		inv.setItem(11, item1);
		inv.setItem(12, item1);
		inv.setItem(14, item1);
		inv.setItem(15, item1);
		inv.setItem(17, item1);
		inv.setItem(18, item1);
		inv.setItem(19, item1);
		inv.setItem(20, item1);
		inv.setItem(21, item1);
		inv.setItem(23, item1);
		inv.setItem(24, item1);
		inv.setItem(25, item1);
		inv.setItem(26, item1);
		
		item1.setType(Material.WHITE_STAINED_GLASS_PANE);
		inv.setItem(4, item1);
		
		item1.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED+"Close");
		item1.setItemMeta(meta);
		inv.setItem(22, item1);
		
		item1.setType(Material.LIME_STAINED_GLASS_PANE);
		meta.setDisplayName(ChatColor.WHITE+"Use Solidifer");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.WHITE+"<- Input tank");
		lore.add(ChatColor.WHITE+"Output ->");
		meta.setLore(lore);
		item1.setItemMeta(meta);
		inv.setItem(13, item1);
		
		
		
		inventory.put(location, inv);
	}
}
