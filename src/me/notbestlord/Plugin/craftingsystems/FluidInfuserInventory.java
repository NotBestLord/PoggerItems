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

public class FluidInfuserInventory {
	
	public static Hashtable<Location, Inventory> inventory = new Hashtable<>();
	public static Inventory inv;
	
	public static void createFluidInfuser(Location location) {
		inv = Bukkit.createInventory(null, 27, ChatColor.AQUA+"Fluid Infuser");
		ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = item1.getItemMeta();
		meta.setDisplayName(" ");
		item1.setItemMeta(meta);
		
		for(int i=0; i<27; i++) {
			inv.setItem(i, item1);
		}
		
		item1.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED+"Close");
		item1.setItemMeta(meta);
		inv.setItem(22, item1);
		
		item1.setType(Material.WHITE_STAINED_GLASS_PANE);
		meta.setDisplayName(" ");
		item1.setItemMeta(meta);
		inv.setItem(4, item1);
		
		item1.setType(Material.LIME_STAINED_GLASS_PANE);
		meta.setDisplayName(ChatColor.WHITE+"Use Fluid Infuser");
		item1.setItemMeta(meta);
		inv.setItem(13, item1);
		
		item1.setType(Material.BLUE_STAINED_GLASS_PANE);
		meta.setDisplayName(ChatColor.WHITE+"/\\ Item infused");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.WHITE+"Liquid infused \\/");
		meta.setLore(lore);
		lore.clear();
		item1.setItemMeta(meta);
		inv.setItem(10, item1);
		
		inv.setItem(1, null);
		inv.setItem(16, null);
		inv.setItem(19, null);
		
		inventory.put(location, inv);
	}
}
