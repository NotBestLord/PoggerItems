package me.notbestlord.Plugin.enchanting;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;
import net.md_5.bungee.api.ChatColor;

public class EnchantingTableInventory {
	
	public static Inventory inventory;
	public static Dictionary<UUID, Inventory> inventoryaccess = new Hashtable<>();
	
	public static Inventory subInventory;
	public static Dictionary<UUID, Inventory> subInventoryaccess = new Hashtable<>();
	
	public static void init(Player player) {
		createInventory(player);
	}
	
	
	public static void createInventory(Player player){
		inventory = Bukkit.createInventory(null, 54, ChatColor.AQUA+"Enchanting Table");
		
		ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		item.setItemMeta(meta);
		
		//Blanks
		inventory.setItem(1, item);
		inventory.setItem(2, item);
		inventory.setItem(3, item);
		inventory.setItem(5, item);
		inventory.setItem(6, item);
		inventory.setItem(7, item);
		inventory.setItem(8, item);
		inventory.setItem(9, item);
		inventory.setItem(10, item);
		inventory.setItem(11, item);
		inventory.setItem(12, item);
		inventory.setItem(13, item);
		inventory.setItem(14, item);
		inventory.setItem(15, item);
		inventory.setItem(16, item);
		inventory.setItem(17, item);
		inventory.setItem(18, item);
		inventory.setItem(19, item);
		inventory.setItem(20, item);
		inventory.setItem(21, item);
		inventory.setItem(22, item);
		inventory.setItem(23, item);
		inventory.setItem(24, item);
		inventory.setItem(25, item);
		inventory.setItem(26, item);
		inventory.setItem(27, item);
		inventory.setItem(28, item);
		inventory.setItem(29, item);
		inventory.setItem(30, item);
		inventory.setItem(31, item);
		inventory.setItem(32, item);
		inventory.setItem(33, item);
		inventory.setItem(34, item);
		inventory.setItem(35, item);
		inventory.setItem(36, item);
		inventory.setItem(37, item);
		inventory.setItem(38, item);
		inventory.setItem(39, item);
		inventory.setItem(40, item);
		inventory.setItem(41, item);
		inventory.setItem(42, item);
		inventory.setItem(43, item);
		inventory.setItem(44, item);
		inventory.setItem(45, item);
		inventory.setItem(46, item);
		inventory.setItem(47, item);
		inventory.setItem(48, item);
		inventory.setItem(50, item);
		inventory.setItem(51, item);
		inventory.setItem(52, item);
		inventory.setItem(53, item);
		
		item.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED+"Close");
		item.setItemMeta(meta);
		inventory.setItem(49, item);
		
		item.setType(Material.LIME_STAINED_GLASS_PANE);
		meta.setDisplayName(ChatColor.GREEN+"Refresh");
		item.setItemMeta(meta);
		inventory.setItem(0, item);
		
		inventoryaccess.put(player.getUniqueId(), inventory);
	}
	public static void createSubInventory(Player player, ItemStack inputItem ,String enchant, ArrayList array) {
		subInventory = Bukkit.createInventory(null, 27, "§3Enchanting Table");
		
		ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(" ");
		item.setItemMeta(meta);
		
		subInventory.setItem(0, inputItem);
		subInventory.setItem(1, item);
		subInventory.setItem(2, item);
		subInventory.setItem(3, item);
		subInventory.setItem(4, item);
		subInventory.setItem(5, item);
		subInventory.setItem(6, item);
		subInventory.setItem(7, item);
		subInventory.setItem(9, item);
		subInventory.setItem(10, item);
		subInventory.setItem(11, item);
		subInventory.setItem(12, item);
		subInventory.setItem(13, item);
		subInventory.setItem(14, item);
		subInventory.setItem(15, item);
		subInventory.setItem(16, item);
		subInventory.setItem(17, item);
		subInventory.setItem(18, item);
		subInventory.setItem(19, item);
		subInventory.setItem(20, item);
		subInventory.setItem(21, item);
		subInventory.setItem(22, item);
		subInventory.setItem(23, item);
		subInventory.setItem(24, item);
		subInventory.setItem(25, item);
		subInventory.setItem(26, item);
		
		int level = (Integer) array.get(0);
		int cost = (Integer) array.get(1);
		ArrayList<Integer> slots = new ArrayList<Integer>();
		
		if(level == 1) {
			slots.add(13);
		}else if(level == 2) {
			slots.add(12);
			slots.add(14);
		}else if(level == 3) {
			slots.add(11);
			slots.add(13);
			slots.add(15);
		}else if(level == 4) {
			slots.add(11);
			slots.add(12);
			slots.add(14);
			slots.add(15);
		}else if(level == 5) {
			slots.add(11);
			slots.add(12);
			slots.add(13);
			slots.add(14);
			slots.add(15);
		}
		item.setType(Material.ENCHANTED_BOOK);
		for(int i=0; i<slots.size(); i++) {
			meta.setDisplayName(ChatColor.YELLOW + enchant+" "+(i+1));
			meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "EnchantLevel"), PersistentDataType.INTEGER, i+1);
			List<String> lore = new ArrayList<>();
			lore.add(ChatColor.LIGHT_PURPLE+"Xp cost: "+cost*(i+1));
			meta.setLore(lore);
			item.setItemMeta(meta);
			subInventory.setItem(slots.get(i), item);
		}
		
		ItemStack book = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta bookMeta = book.getItemMeta();
		bookMeta.setDisplayName(enchant);
		bookMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "xpCost"), PersistentDataType.INTEGER, cost);
		book.setItemMeta(bookMeta);
		subInventory.setItem(8, book);
		subInventoryaccess.put(player.getUniqueId(), subInventory);
	}
	public static void BlankSlot(int slot) {
		ItemStack Blank = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta BlankMeta = Blank.getItemMeta();
		BlankMeta.setDisplayName(" ");
		Blank.setItemMeta(BlankMeta);
		inventory.setItem(slot, Blank);
	}
	
}
