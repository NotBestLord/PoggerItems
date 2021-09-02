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

public class RecipeBookSubInvEvent implements Listener{
	
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null) {
			return;
		}
		if(!event.getClickedInventory().equals(RecipeBookInventory.subsubinventory.get(event.getWhoClicked().getUniqueId()))) {
			return;
		}
		event.setCancelled(true);
		if(event.getSlot() == 22) {
			event.getWhoClicked().closeInventory();
		}
		if(event.getClickedInventory().getItem(event.getSlot()) == null) {
			return;
		}
		
		if(!event.getClickedInventory().getItem(event.getSlot()).hasItemMeta()) {
			return;
		}
		
		Player player = (Player) event.getWhoClicked();
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
	}
}
