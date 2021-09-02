package me.notbestlord.Plugin.enchanting;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.EnchantingTable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.PluginAwareness.Flags;

import me.notbestlord.Plugin.Main;
import net.md_5.bungee.api.ChatColor;

public class EnchantingTableEvent implements Listener{
	
	private static Hashtable<String, Hashtable<String,ArrayList>> EnchantList = new Hashtable<>();
	
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory() == null)
			return;
		if(event.getView().getTitle().equals(ChatColor.AQUA+"Enchanting Table") && !event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
			
			if(event.getSlot() != 4) {
				event.setCancelled(true);
			}
			if(event.getSlot() == 49) {
				event.getWhoClicked().closeInventory();
			}
			if(event.getClickedInventory().getItem(4) == null) {
				int j;
				for(int i=0; i<28; i++) {
					j =i;
					if(i<7)
						j+=10;
					else if(i>6 && i<14)
						j+=12;
					else if(i>13 && i<21)
						j+=14;
					else if(i>20 && i<28)
						j+=16;
					EnchantingTableInventory.BlankSlot(j);
				}
				return;
			}
			if(event.getCurrentItem() == null) {
				return;
			}
			if(!event.getClickedInventory().getItem(4).hasItemMeta()) {
				return;
			}
			if(!event.getClickedInventory().getItem(4).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
				return;
			}
			
			ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK);
			ItemMeta enchantedBookMeta = enchantedBook.getItemMeta();
			String inputitemID = event.getClickedInventory().getItem(4).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING);
			if(EnchantList.containsKey(inputitemID)) {
				Set<String> EnchantSet = EnchantList.get(inputitemID).keySet();
				String[] EnchantArray = new String[EnchantSet.size()];
				EnchantSet.toArray(EnchantArray);
				int slot = 0;
				for(int i=0; i<EnchantArray.length; i++) {
					if(i<7)
						slot = i+10;
					else if(i>6 && i<14)
						slot = i+12;
					else if(i>13 && i<21)
						slot = i+14;
					else if(i>20 && i<28)
						slot = i+16;
					enchantedBookMeta.setDisplayName(EnchantArray[i]);
					enchantedBook.setItemMeta(enchantedBookMeta);
					event.getClickedInventory().setItem(slot, enchantedBook);
				}
				Hashtable<String,ArrayList> EnchantData = EnchantList.get(inputitemID);
				ItemStack itemClicked = event.getClickedInventory().getItem(event.getSlot());
				ItemMeta itemClickedMeta = itemClicked.getItemMeta();
				if(EnchantSet.contains(itemClickedMeta.getDisplayName())) {
					EnchantingTableInventory.createSubInventory((Player) event.getWhoClicked(), event.getClickedInventory().getItem(4), itemClickedMeta.getDisplayName(), EnchantData.get(itemClickedMeta.getDisplayName()));
					event.getWhoClicked().openInventory(EnchantingTableInventory.subInventoryaccess.get(event.getWhoClicked().getUniqueId()));
				}
			}
		}else if(event.getClickedInventory().equals(EnchantingTableInventory.subInventory)) {
			event.setCancelled(true);
			ItemStack itemClicked = event.getClickedInventory().getItem(event.getSlot());
			if(!itemClicked.hasItemMeta())
				return;
			if(!itemClicked.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "EnchantLevel"), PersistentDataType.INTEGER))
				return;
			int EnchantLevel = itemClicked.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "EnchantLevel"), PersistentDataType.INTEGER);
			String EnchantName = event.getClickedInventory().getItem(8).getItemMeta().getDisplayName();
			ItemStack itemOutput = event.getClickedInventory().getItem(0);
			ItemMeta itemOutputMeta = itemOutput.getItemMeta();
			Inventory outputInventory = EnchantingTableInventory.inventoryaccess.get(event.getWhoClicked().getUniqueId());
			//
			if(outputInventory.getItem(4).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), EnchantName), PersistentDataType.INTEGER) >= EnchantLevel) {
				event.getWhoClicked().sendMessage(ChatColor.RED+"This enchant is already on the item");
				return;
			}
			//
			int xpCost = event.getClickedInventory().getItem(8).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "xpCost"), PersistentDataType.INTEGER);
			Player player = (Player) event.getWhoClicked();
			if(player.getLevel() >=xpCost && player.getGameMode().equals(GameMode.SURVIVAL)) {
				player.setLevel(player.getLevel()-xpCost);
			}else if(!player.getGameMode().equals(GameMode.CREATIVE)){
				player.sendMessage(ChatColor.RED+"Not enough xp");
				return;
			}
			itemOutputMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), EnchantName), PersistentDataType.INTEGER, EnchantLevel);
			itemOutputMeta.addEnchant(Enchantment.PIERCING, 1, false);
			itemOutputMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			List<String> lore = new ArrayList<>();
			lore.addAll(itemOutputMeta.getLore());
			String ench = "";
			for(int i=0; i < EnchantLevel;i++) {
				ench = ChatColor.GRAY+EnchantName + " "+ (i+1);
				if(lore.contains(ench)) {
					lore.remove(lore.indexOf(ench));
					break;
				}
			}
			lore.add(0, ChatColor.GRAY+EnchantName+" "+EnchantLevel);
			
			itemOutputMeta.setLore(lore);
			itemOutput.setItemMeta(itemOutputMeta);
			
			outputInventory.setItem(4, itemOutput);
			event.getWhoClicked().openInventory(EnchantingTableInventory.inventoryaccess.get(event.getWhoClicked().getUniqueId()));
		}
		
	}
	
	public static void AddEnchant(ItemStack item, String Enchant, int level, int xpcost) {
		String id = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING);
		item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Enchant"+Enchant), PersistentDataType.INTEGER, 0);
		ArrayList array = new ArrayList<>();
		array.add(level);
		array.add(xpcost);
		EnchantList.get(id).put(Enchant, new ArrayList(array));
		array.clear();
	}
	public static void initItem(ItemStack item) {
		Hashtable<String, ArrayList> dictionary = new Hashtable<String, ArrayList>();
		String id = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING);
		EnchantList.put(id, new Hashtable<String, ArrayList>(dictionary));
	}
}
