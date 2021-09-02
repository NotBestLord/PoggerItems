package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.Hashtable;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RecipeBookSelectorEvent implements Listener{
	
	public static Hashtable<String, ArrayList> ItemRecipes = new Hashtable<String, ArrayList>();
	public static Hashtable<String, ArrayList> IngredientRecipes = new Hashtable<String, ArrayList>();
	public static Hashtable<String, ArrayList> MachineRecipes = new Hashtable<String, ArrayList>();
	
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null) {
			return;
		}
		if(!event.getClickedInventory().equals(RecipeBookInventory.inventory.get(event.getWhoClicked().getUniqueId()))) {
			return;
		}
		event.setCancelled(true);
		Player player = (Player) event.getWhoClicked();
		if(event.getSlot() == 22) {
			player.closeInventory();
		}
		if(event.getSlot() == 11) {
			//items
			RecipeBookInventory.createRecipeBookSubinventory(player, 0, 0);
			player.openInventory(RecipeBookInventory.subinventory.get(player.getUniqueId()));
			return;
		}
		if(event.getSlot() == 13) {
			//machines
			RecipeBookInventory.createRecipeBookSubinventory(player, 2 , 0);
			player.openInventory(RecipeBookInventory.subinventory.get(player.getUniqueId()));
			return;
		}
		if(event.getSlot() == 15) {
			//ingredients
			RecipeBookInventory.createRecipeBookSubinventory(player, 1 , 0);
			player.openInventory(RecipeBookInventory.subinventory.get(player.getUniqueId()));
			return;
		}
	}
	
	public static void addRecipe(String item, int itemType, ArrayList array) {
		// array:
		//0. ItemStack outputItem
		//1. crafter (reactor, infuser ,etc.)
		//2. shapedRecipe / inputItemStack
		//3. Secondary Output
		//4. Secondary Input
		//
		if(itemType == 0) {
			ItemRecipes.put(item, array);
		}else if(itemType == 1){
			IngredientRecipes.put(item, array);
		}else if(itemType == 2) {
			MachineRecipes.put(item, array);
		}
	}
}
