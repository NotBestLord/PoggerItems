package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FissionReactorInventory {
	
	public static Dictionary<UUID, Inventory> inventory = new Hashtable<>();
	public static Inventory inv;
	
	public static void createFissionReactor(Player player) {
		inv = Bukkit.createInventory(null, 54, ChatColor.AQUA+"Fission Reactor");
		ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = item1.getItemMeta();
		meta.setDisplayName(" ");
		item1.setItemMeta(meta);
		
		inv.setItem(0, item1);
		inv.setItem(1, item1);
		inv.setItem(2, item1);
		inv.setItem(3, item1);
		inv.setItem(4, item1);
		inv.setItem(5, item1);
		inv.setItem(6, item1);
		inv.setItem(7, item1);
		inv.setItem(8, item1);
		inv.setItem(9, item1);
		inv.setItem(10, item1);
		inv.setItem(11, item1);
		inv.setItem(12, item1);
		inv.setItem(14, item1);
		inv.setItem(15, item1);
		inv.setItem(16, item1);
		inv.setItem(17, item1);
		inv.setItem(18, item1);
		inv.setItem(20, item1);
		inv.setItem(21, item1);
		inv.setItem(23, item1);
		inv.setItem(24, item1);
		inv.setItem(26, item1);
		inv.setItem(27, item1);
		inv.setItem(28, item1);
		inv.setItem(29, item1);
		inv.setItem(30, item1);
		inv.setItem(31, item1);
		inv.setItem(32, item1);
		inv.setItem(33, item1);
		inv.setItem(34, item1);
		inv.setItem(35, item1);
		inv.setItem(36, item1);
		inv.setItem(37, item1);
		inv.setItem(43, item1);
		inv.setItem(44, item1);
		inv.setItem(45, item1);
		inv.setItem(46, item1);
		inv.setItem(47, item1);
		inv.setItem(48, item1);
		inv.setItem(50, item1);
		inv.setItem(51, item1);
		inv.setItem(52, item1);
		inv.setItem(53, item1);
		
		item1.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED+"Close");
		item1.setItemMeta(meta);
		inv.setItem(49, item1);
		
		item1.setType(Material.RED_STAINED_GLASS_PANE);
		meta.setDisplayName(ChatColor.WHITE+"20%");
		item1.setItemMeta(meta);
		inv.setItem(38, item1);
		meta.setDisplayName(ChatColor.WHITE+"40%");
		item1.setItemMeta(meta);
		inv.setItem(39, item1);
		meta.setDisplayName(ChatColor.WHITE+"60%");
		item1.setItemMeta(meta);
		inv.setItem(40, item1);
		meta.setDisplayName(ChatColor.WHITE+"80%");
		item1.setItemMeta(meta);
		inv.setItem(41, item1);
		meta.setDisplayName(ChatColor.WHITE+"100%");
		item1.setItemMeta(meta);
		inv.setItem(42, item1);
		
		item1.setType(Material.LIME_STAINED_GLASS_PANE);
		meta.setDisplayName(ChatColor.WHITE+"Use Fission Reactor");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.WHITE+"<- Empty Fuel Tank");
		lore.add(ChatColor.WHITE+"Byproducts ->");
		lore.add(ChatColor.WHITE+"Full Fuel Tank");
		meta.setLore(lore);
		item1.setItemMeta(meta);
		inv.setItem(13, item1);
		
		inventory.put(player.getUniqueId(), inv);
	}
	public static void resetProgressBar(Player player) {
		ItemStack item1 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		ItemMeta meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"20%");
		item1.setItemMeta(meta);
		inv.setItem(38, item1);
		meta.setDisplayName(ChatColor.WHITE+"40%");
		item1.setItemMeta(meta);
		inv.setItem(39, item1);
		meta.setDisplayName(ChatColor.WHITE+"60%");
		item1.setItemMeta(meta);
		inv.setItem(40, item1);
		meta.setDisplayName(ChatColor.WHITE+"80%");
		item1.setItemMeta(meta);
		inv.setItem(41, item1);
		meta.setDisplayName(ChatColor.WHITE+"100%");
		item1.setItemMeta(meta);
		inv.setItem(42, item1);
	}
	
}
