package me.notbestlord.Plugin.craftingsystems;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.libs.org.apache.maven.artifact.repository.metadata.Metadata;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Score;

import com.google.common.util.concurrent.AbstractScheduledService.Scheduler;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.CoalGeneratorInventory;
import me.notbestlord.Plugin.items.ItemManager;
import net.md_5.bungee.api.ChatColor;

public class InfuserEvent implements Listener{
	
	public static Hashtable<String, Hashtable<ItemStack, ArrayList>> Recipes = new Hashtable<String, Hashtable<ItemStack, ArrayList>>();
	
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(event.getInventory().equals(InfuserInventory.inventory.get(event.getWhoClicked().getUniqueId()))) {
			if(event.getSlot() == event.getWhoClicked().getInventory().getHeldItemSlot()) {
				event.setCancelled(true);
			}
		}
		if(!event.getClickedInventory().equals(InfuserInventory.inventory.get(event.getWhoClicked().getUniqueId()))) {
			return;
		}
		if(event.getSlot()!= 10 && event.getSlot() != 12 && event.getSlot() != 16) {
			event.setCancelled(true);
		}
		
		Player player = (Player)event.getWhoClicked();
		if(event.getSlot() == 17) {
			player.closeInventory();
		}
		if(event.getSlot() == 14) {
			if(event.getClickedInventory().getItem(10) == null || event.getClickedInventory().getItem(12) == null) {
				return;
			}
			if(event.getClickedInventory().getItem(10).getAmount() != 1) {
				return;
			}
			if(!event.getClickedInventory().getItem(10).hasItemMeta()) {
				return;
			}
			if(!event.getClickedInventory().getItem(10).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
				return;
			}
			String key = event.getClickedInventory().getItem(10).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING);
			if(Recipes.containsKey(event.getClickedInventory().getItem(10).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))) {
				ItemStack input = event.getClickedInventory().getItem(10);
				ItemStack inputMedium = event.getClickedInventory().getItem(12);
				ItemStack inputMedium1 = new ItemStack(inputMedium);
				if(!inputMedium1.hasItemMeta()) {
					return;
				}
				if(!inputMedium1.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)) {
					return;
				}
				String inputMediumID = inputMedium1.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING);
				String recipeMediumID = "";
				int j=0;
				Set<ItemStack> mediumSet = Recipes.get(key).keySet();
				ItemStack[] recipeMediumsLst = new ItemStack[mediumSet.size()];
				mediumSet.toArray(recipeMediumsLst);
				for(int i=0; i<recipeMediumsLst.length; i++) {
					if(recipeMediumsLst[i].getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING).equals(inputMediumID)){
						recipeMediumID = recipeMediumsLst[i].getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING);
						j=i;
						break;
					}
					if(!recipeMediumsLst[i].getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING).equals(inputMediumID) && i==recipeMediumsLst.length-1) {
						player.sendMessage(ChatColor.RED+"Incorrect infusing medium.");
						return;
					}
				}
				
				ItemStack inputMediumTrue = recipeMediumsLst[j];
				int inputMediumAmount = inputMedium.getAmount();
				int requiredAmount = (int) Recipes.get(key).get(inputMediumTrue).get(0);
				
				
				if(event.getClickedInventory().getItem(16) != null) {
					player.sendMessage(ChatColor.RED+"Empty the output");
					return;
				}
				if(inputMediumAmount < requiredAmount) {
					event.getWhoClicked().sendMessage(ChatColor.RED+"Not enough items");
					return;
				}
				inputMediumAmount-=requiredAmount;
				//
				
				inputMedium.setAmount(inputMediumAmount);
				player.playSound(player.getLocation(), Sound.ENTITY_WANDERING_TRADER_DRINK_POTION, 1, 1);
				if(Recipes.get(key).get(inputMediumTrue).get(2).getClass().equals(ItemStack.class)) {
					ItemStack result = new ItemStack((ItemStack) Recipes.get(key).get(inputMediumTrue).get(2));
					event.getClickedInventory().setItem(16, result);
				}else if(Recipes.get(key).get(inputMediumTrue).get(2).getClass().equals(String.class)) {
					String resultTag = (String) Recipes.get(key).get(inputMediumTrue).get(2);
					ItemStack result = new ItemStack((ItemStack) event.getClickedInventory().getItem(10));
					if(result.getType() == Material.PLAYER_HEAD && !(((String)Recipes.get(key).get(inputMediumTrue).get(1)).equals(""))) {
						SkullMeta meta = (SkullMeta) result.getItemMeta();
						GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
						profile.getProperties().put("textures", new Property("textures", (String)Recipes.get(key).get(inputMediumTrue).get(1)));
						Field profileField;
						try {
							profileField = meta.getClass().getDeclaredField("profile");
							profileField.setAccessible(true);
							profileField.set(meta, profile);
						}catch (Exception e) {
							
						}
						if(Recipes.get(key).get(inputMediumTrue).get(3).getClass().equals(String.class)) {
							meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), resultTag), PersistentDataType.STRING, (String) Recipes.get(key).get(inputMediumTrue).get(3));
						}else if(Recipes.get(key).get(inputMediumTrue).get(3).getClass().equals(int.class)){
							meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), resultTag), PersistentDataType.INTEGER, meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), resultTag), PersistentDataType.INTEGER) + (Integer) Recipes.get(key).get(inputMediumTrue).get(3));
						}
						
						if(Recipes.get(key).get(inputMediumTrue).get(4) != null) {
							List<String> lore = new ArrayList<>(meta.getLore());
							List<String> newlore = (List)Recipes.get(key).get(inputMediumTrue).get(5);
							int loredelete = lore.indexOf(Recipes.get(key).get(inputMediumTrue).get(4));
							int loresize = lore.size()-1;
							for(int i = loresize; i>=loredelete; i--) {
								lore.remove(i);
							}
							lore.add((String)Recipes.get(key).get(inputMediumTrue).get(4));
							lore.addAll(newlore);
							meta.setLore(lore);
						}
						result.setItemMeta(meta);
						event.getClickedInventory().setItem(16, result);
					}else {
						ItemMeta meta = (ItemMeta) result.getItemMeta();
						if(Recipes.get(key).get(inputMediumTrue).get(3).getClass().equals(String.class)) {
							meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), resultTag), PersistentDataType.STRING, (String) Recipes.get(key).get(inputMediumTrue).get(3));
						}else if(Recipes.get(key).get(inputMediumTrue).get(3) instanceof Integer){
							meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), resultTag), PersistentDataType.INTEGER, meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), resultTag), PersistentDataType.INTEGER) + (Integer) Recipes.get(key).get(inputMediumTrue).get(3));
						}
						if(Recipes.get(key).get(inputMediumTrue).get(4) != null) {
							List<String> lore = new ArrayList<>(meta.getLore());
							List<String> newlore = (List)Recipes.get(key).get(inputMediumTrue).get(5);
							int loredelete = lore.indexOf(Recipes.get(key).get(inputMediumTrue).get(4));
							int loresize = lore.size()-1;
							for(int i = loresize; i>=loredelete; i--) {
								lore.remove(i);
							}
							lore.add((String)Recipes.get(key).get(inputMediumTrue).get(4));
							lore.addAll(newlore);
							meta.setLore(lore);
						}
						result.setItemMeta(meta);
						event.getClickedInventory().setItem(16, result);
					}
				}
		    	event.getClickedInventory().setItem(12, inputMedium);
		    	event.getClickedInventory().setItem(10, null);
			}
		}
	}
	
	public static void initItem(String inputItem) {
		Hashtable<ItemStack, ArrayList> temp = new Hashtable<ItemStack, ArrayList>();
		Recipes.put(inputItem, new Hashtable<ItemStack, ArrayList>(temp));
		temp.clear();
	}
	public static void addRecipe(String inputItem, ItemStack inputMedium, ArrayList array) {
		Recipes.get(inputItem).put(inputMedium, new ArrayList<>(array));
		array.clear();
	}
	
}
