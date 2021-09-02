package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;
import net.md_5.bungee.api.ChatColor;

public class RecipeBookEvent implements Listener{
	
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null) {
			return;
		}
		if(!event.getClickedInventory().equals(RecipeBookInventory.subinventory.get(event.getWhoClicked().getUniqueId()))) {
			return;
		}
		event.setCancelled(true);
		Player player = (Player) event.getWhoClicked();
		if(event.getSlot() == 49) {
			player.closeInventory();
		}
		if(event.getInventory().getItem(event.getSlot()) == null) {
			return;
		}
		if(!event.getInventory().getItem(event.getSlot()).hasItemMeta()) {
			return;
		}
		ItemStack clickedItem = event.getClickedInventory().getItem(event.getSlot());
		if(event.getInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
			if(RecipeBookSelectorEvent.ItemRecipes.containsKey(clickedItem.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))) {
				RecipeBookInventory.showRecipe(player, clickedItem, RecipeBookSelectorEvent.ItemRecipes.get(clickedItem.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)));
				player.openInventory(RecipeBookInventory.subsubinventory.get(player.getUniqueId()));
			}
		}
		else if(event.getInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)) {
			if(RecipeBookSelectorEvent.IngredientRecipes.containsKey(clickedItem.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING))) {
				RecipeBookInventory.showRecipe(player, clickedItem, RecipeBookSelectorEvent.IngredientRecipes.get(clickedItem.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)));
				player.openInventory(RecipeBookInventory.subsubinventory.get(player.getUniqueId()));
			}
		}
		else if(event.getInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING)) {
			if(RecipeBookSelectorEvent.MachineRecipes.containsKey(clickedItem.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING))) {
				RecipeBookInventory.showRecipe(player, clickedItem, RecipeBookSelectorEvent.MachineRecipes.get(clickedItem.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING)));
				player.openInventory(RecipeBookInventory.subsubinventory.get(player.getUniqueId()));
			}
		}
		if(event.getClickedInventory().getItem(10).hasItemMeta()) {
			if(event.getClickedInventory().getItem(10).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING)) {
				if(RecipeBookSelectorEvent.MachineRecipes.containsKey(event.getInventory().getItem(10).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING))) {
					if(clickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"Page Down")) {
						RecipeBookInventory.createRecipeBookSubinventory(player, 2, RecipeBookInventory.subinventory.get(player.getUniqueId()).getItem(0).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER)+1);
						player.openInventory(RecipeBookInventory.subinventory.get(player.getUniqueId()));
					}
					else if(clickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"Page Up")) {
						RecipeBookInventory.createRecipeBookSubinventory(player, 2, RecipeBookInventory.subinventory.get(player.getUniqueId()).getItem(0).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER)-1);
						player.openInventory(RecipeBookInventory.subinventory.get(player.getUniqueId()));
					}
				}
			}
			else if(event.getClickedInventory().getItem(10).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
				if(RecipeBookSelectorEvent.ItemRecipes.containsKey(event.getInventory().getItem(10).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))) {
					if(clickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"Page Down")) {
						RecipeBookInventory.createRecipeBookSubinventory(player, 0, RecipeBookInventory.subinventory.get(player.getUniqueId()).getItem(0).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER)+1);
						player.openInventory(RecipeBookInventory.subinventory.get(player.getUniqueId()));
					}
					else if(clickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"Page Up")) {
						RecipeBookInventory.createRecipeBookSubinventory(player, 0, RecipeBookInventory.subinventory.get(player.getUniqueId()).getItem(0).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER)-1);
						player.openInventory(RecipeBookInventory.subinventory.get(player.getUniqueId()));
					}
				}
			}
			else if(event.getClickedInventory().getItem(10).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)) {
				if(RecipeBookSelectorEvent.IngredientRecipes.containsKey(event.getClickedInventory().getItem(10).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING))) {
					if(clickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"Page Down")) {
						RecipeBookInventory.createRecipeBookSubinventory(player, 1, RecipeBookInventory.subinventory.get(player.getUniqueId()).getItem(0).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER)+1);
						player.openInventory(RecipeBookInventory.subinventory.get(player.getUniqueId()));
					}
					else if(clickedItem.getItemMeta().getDisplayName().equals(ChatColor.WHITE+"Page Up")) {
						RecipeBookInventory.createRecipeBookSubinventory(player, 1, RecipeBookInventory.subinventory.get(player.getUniqueId()).getItem(0).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER)-1);
						player.openInventory(RecipeBookInventory.subinventory.get(player.getUniqueId()));
					}
				}
			}
			
		}
	}
}
