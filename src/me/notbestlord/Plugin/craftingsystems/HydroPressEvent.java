package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Score;

import com.google.common.util.concurrent.AbstractScheduledService.Scheduler;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.CoalGeneratorInventory;
import me.notbestlord.Plugin.items.ItemManager;
import net.md_5.bungee.api.ChatColor;

public class HydroPressEvent implements Listener{
	
	public static HashMap<ItemStack, ArrayList> Recipes = new HashMap<ItemStack, ArrayList>();
	
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(event.getInventory().equals(HydraulicPressInventory.inventory)) {
			if(event.getSlot() == event.getWhoClicked().getInventory().getHeldItemSlot()) {
				event.setCancelled(true);
			}
		}
		if(!event.getClickedInventory().equals(HydraulicPressInventory.inventory)) {
			return;
		}
		if(event.getSlot()!= 10 && event.getSlot() != 15) {
			event.setCancelled(true);
		}
		
		Player player = (Player)event.getWhoClicked();
		if(event.getSlot() == 17) {
			player.closeInventory();
		}
		if(event.getSlot() == 13) {
			if(event.getClickedInventory().getItem(10) == null) {
				return;
			}
			ItemStack key = new ItemStack(event.getClickedInventory().getItem(10));
			key.setAmount(1);
			if(Recipes.containsKey(key)) {
				ItemStack input = event.getClickedInventory().getItem(10);
				int inputAmount = input.getAmount();
				int requiredAmount = (int) Recipes.get(key).get(0);
				ItemStack result = new ItemStack((ItemStack) Recipes.get(key).get(1));
				if(event.getClickedInventory().getItem(15) != null) {
					player.sendMessage(ChatColor.RED+"Empty the output");
					return;
				}
				if(inputAmount < requiredAmount) {
					event.getWhoClicked().sendMessage(ChatColor.RED+"Not enough items");
					return;
				}
				inputAmount-=requiredAmount;
				//
				
				input.setAmount(inputAmount);
				player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
				event.getClickedInventory().setItem(15, result);
		    	event.getClickedInventory().setItem(10, input);
			}
		}
	}
	
	public static void addRecipe(ItemStack inputItem, int inputAmount, ItemStack Result) {
		ArrayList array = new ArrayList();
		array.add(inputAmount);
		array.add(Result);
		inputItem.setAmount(1);
		Recipes.put(inputItem, new ArrayList<>(array));
		array.clear();
	}
	
}
