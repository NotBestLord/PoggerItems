package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.IngredientManager;
import me.notbestlord.Plugin.items.ItemManager;

public class AutomaticGardenInventory {
	
	public static Hashtable<Location, Inventory> inventory = new Hashtable<>();
	
	public static void createMainInventory(Location location, Location waterLocation) {
		Inventory inv;
		inv = Bukkit.createInventory(null, 27, ChatColor.AQUA+"Automatic Garden");
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
		meta.setDisplayName(ChatColor.WHITE+"Activate Automatic Garden");
		item1.setItemMeta(meta);
		inv.setItem(13, item1);
		
		inv.setItem(1, new ItemStack(Material.AIR));
		inv.setItem(19, new ItemStack(Material.AIR));

		item1.setType(Material.BLUE_STAINED_GLASS_PANE);
		meta.setDisplayName(ChatColor.WHITE+"/\\ Seed");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.WHITE+"Ground Block \\/");
		meta.setLore(lore);
		item1.setItemMeta(meta);
		inv.setItem(10, item1);
		
		item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(" ");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "WaterLocationX"), PersistentDataType.INTEGER, waterLocation.getBlockX());
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "WaterLocationY"), PersistentDataType.INTEGER, waterLocation.getBlockY());
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "WaterLocationZ"), PersistentDataType.INTEGER, waterLocation.getBlockZ());
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "locationWorld"), PersistentDataType.STRING, location.getWorld().getName());
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "locationX"), PersistentDataType.INTEGER, location.getBlockX());
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "locationY"), PersistentDataType.INTEGER, location.getBlockY());
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "locationZ"), PersistentDataType.INTEGER, location.getBlockZ());
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "runnableTimer"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "recipeID"), PersistentDataType.STRING, "");
		item1.setItemMeta(meta);
		inv.setItem(0, item1);
		
		item1 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE+"Water - 0mb");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "waterMB"), PersistentDataType.INTEGER, 0);
		item1.setItemMeta(meta);
		inv.setItem(16, item1);
		
		inventory.put(location, inv);
	}
}
