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
import me.notbestlord.Plugin.dataManagment.Recipe;
import me.notbestlord.Plugin.dataManagment.Recipe.EnchantedWorkbenchRecipe;
import me.notbestlord.Plugin.items.FoodManager;
import me.notbestlord.Plugin.items.IngredientManager;
import me.notbestlord.Plugin.items.ItemManager;

public class EnchantedWorkBenchInventory {
	
	public static Hashtable<Location, Inventory> inventory = new Hashtable<>();
	public static HashMap<String, Recipe.EnchantedWorkbenchRecipe> RecipeList = new HashMap<>();
	
	public static Inventory createMainInventory(Location location, int page) {
		Inventory inv;
		inv = Bukkit.createInventory(null, 54, ChatColor.AQUA+"Enchanted WorkBench");
		ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = item1.getItemMeta();
		meta.setDisplayName(" ");
		item1.setItemMeta(meta);
		
		for(int i=0; i<54; i++) {
			inv.setItem(i, item1);
		}
		
		item1.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED+"Close");
		item1.setItemMeta(meta);
		inv.setItem(49, item1);
		
		Collection<Recipe.EnchantedWorkbenchRecipe> set = RecipeList.values();
		Recipe.EnchantedWorkbenchRecipe[] set1 = new Recipe.EnchantedWorkbenchRecipe[set.size()];
		ArrayList<ItemStack> set2 = new ArrayList<>();
		ArrayList<ItemStack>[] set3 = new ArrayList[set.size()];
		ArrayList<Integer>[] set4 = new ArrayList[set.size()];
		for(int i=0; i<set.size(); i++) {
			set1[i] = (Recipe.EnchantedWorkbenchRecipe) set.toArray()[i];
		}
		for(int i=0; i<set.size(); i++) {
			set2.add(new ItemStack(set1[i].getResult()));
			set3[i] = new ArrayList<>(set1[i].getIngredients());
			set4[i] = new ArrayList<>(set1[i].getIngredientAmounts());
		}
		
		ItemStack[] Recipes = new ItemStack[set.size()];
		for(int i=0; i<set.size(); i++) {
			Recipes[i] = set2.get(i);
			List<String> lore = Recipes[i].getItemMeta().getLore();
			lore.add(ChatColor.YELLOW+"Items Required");
			for(int j=0;j<set3[i].size(); j++) {
				lore.add(ChatColor.WHITE+" "+set3[i].get(j).getItemMeta().getDisplayName()+ChatColor.GRAY+" x"+set4[i].get(j));
			}
			ItemMeta m = Recipes[i].getItemMeta();
			m.setLore(lore);
			Recipes[i].setItemMeta(m);
		}
		String[] RecipesNames = new String[Recipes.length];
			for(int i=0; i<Recipes.length;i++) {
			RecipesNames[i] = Recipes[i].getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING);
		}
		Arrays.sort(RecipesNames);
		ItemStack[] RecipesPostSort = new ItemStack[Recipes.length];
		for(int i=0; i<Recipes.length;i++) {
			int k = 0;
			for(int j = 0; j<Recipes.length; j++) {
				if(Recipes[j].getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING).equals(RecipesNames[i])) {
					k=j;
					break;
				}
			}
			RecipesPostSort[i] = Recipes[k];
		}
		int j = 0;
		if(page!=0) {
			j = 28*page;
		}
		for(int i = 10; i<44; i++) {
			
			if(i>16 && i<19) {
				
			}else if(i>25 && i<28) {
				
			}else if(i>34 && i<37) {
				
			}else{
				inv.setItem(i, RecipesPostSort[j]);
				if(RecipesPostSort[j].equals(RecipesPostSort[RecipesPostSort.length-1])) {
					inv.setItem(i, RecipesPostSort[j]);
					break;
				}else {
					j++;
				}
			}
		}
		if(RecipesPostSort.length>28) {
			int pages;
			if(RecipesPostSort.length % 28 == 0) {
				pages = RecipesPostSort.length/28;
			}else {
				pages = RecipesPostSort.length/28+1;
			}
			pages--;
			if(page != 0) {
				item1.setType(Material.ARROW);
				meta.setDisplayName(ChatColor.WHITE+"Page Up");
				item1.setItemMeta(meta);
				inv.setItem(26, item1);
			}
			if(page != pages) {
				item1.setType(Material.ARROW);
				meta.setDisplayName(ChatColor.WHITE+"Page Down");
				item1.setItemMeta(meta);
				inv.setItem(35, item1);
			}
		}
		//
		ItemStack item2 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta2 = item2.getItemMeta();
		meta2.setDisplayName(" ");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER, page);
		item2.setItemMeta(meta2);
		inv.setItem(0, item2);
		if(location != null) {
			inventory.put(location, inv);
			return null;
		}else {
			return inv;
		}
	}
	
	public static Inventory createSubinventory(Player player, String inputID) {
		Inventory inv;
		inv = Bukkit.createInventory(null, 54, ChatColor.AQUA+"Enchanted Workbench");
		ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = item1.getItemMeta();
		meta.setDisplayName(" ");
		item1.setItemMeta(meta);
		
		for(int i=0; i<54; i++) {
			inv.setItem(i, item1);
		}
		
		item1.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED+"Close");
		item1.setItemMeta(meta);
		inv.setItem(49, item1);
		
		item1.setType(Material.BLUE_STAINED_GLASS_PANE);
		meta.setDisplayName(ChatColor.WHITE+"Items Required");
		item1.setItemMeta(meta);
		inv.setItem(1, item1);
		inv.setItem(2, item1);
		inv.setItem(6, item1);
		inv.setItem(7, item1);
		inv.setItem(46, item1);
		inv.setItem(47, item1);
		inv.setItem(51, item1);
		inv.setItem(52, item1);
		
		item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(" ");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "RecipeID"), PersistentDataType.STRING, inputID);
		item1.setItemMeta(meta);
		inv.setItem(0, item1);
		
		Recipe.EnchantedWorkbenchRecipe recipe = RecipeList.get(inputID).clone();
		ArrayList<ItemStack> recipeInputs = new ArrayList<ItemStack>(recipe.getIngredients());
		ArrayList<ItemStack> inputs = new ArrayList<ItemStack>();
		ArrayList<Integer> amounts = new ArrayList<Integer>(recipe.getIngredientAmounts());
		ItemStack output = recipe.getResult();
		for(int i=0; i<recipeInputs.size(); i++) {
			ItemStack temp = new ItemStack(recipeInputs.get(i));
			int amount = amounts.get(i);
			if(amount>64) {
				int a = amount;
				for(int j=0; j<a/64+1; j++) {
					if(a%64 == 0 && j == 0) {
						j++;
					}
					if(amount>64) {
						temp.setAmount(64);
						inputs.add(new ItemStack(temp));
						amount-=64;
					}
					else {
						temp.setAmount(amount);
						inputs.add(new ItemStack(temp));
					}
				}
			}
			else {
				temp.setAmount(amount);
				inputs.add(new ItemStack(temp));
			}
		}
		
		inv.setItem(13, output);
		int t = 0;
		for(int i=10; i<44; i++) {
			if(i>11 && i<15) {
			}
			else if(i>16 && i<19) {
			}
			else if(i>20 && i<24) {
			}
			else if(i>25 && i<28) {
			}
			else if(i>29 && i<33) {
			}
			else if(i>34 && i<37) {
			}
			else if(i>38 && i<42) {
			}
			else {
				inv.setItem(i, inputs.get(t));
				t++;
				if(t == inputs.size()) {
					break;
				}
			}
		}
		//
		boolean DoesNotHaveItems = false;
		for(int i=0; i<inputs.size(); i++) {
			if(DoesNotHaveItems) {
				break;
			}
			ItemStack currentItem = inputs.get(i);
			int currentItemAmount = currentItem.getAmount();
			if(currentItem.hasItemMeta()) {
				if(currentItem.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
					String id = currentItem.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING);
					for(int j=0; j<player.getInventory().getSize(); j++) {
						ItemStack a = player.getInventory().getItem(j);
						if(a != null) {
							if(a.hasItemMeta()) {
								if(a.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
									if(a.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals(id)) {
										if(a.getAmount() >= currentItemAmount) {
											break;
										}
										else {
											currentItemAmount-=a.getAmount();
										}
									}
								}
							}
						}
						if(j == player.getInventory().getSize()-1) {
							DoesNotHaveItems=true;
						}
					}
				}
				else if(currentItem.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)) {
					String id = currentItem.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING);
					for(int j=0; j<player.getInventory().getSize(); j++) {
						ItemStack a = player.getInventory().getItem(j);
						if(a != null) {
							if(a.hasItemMeta()) {
								if(a.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)) {
									if(a.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING).equals(id)) {
										if(a.getAmount() >= currentItemAmount) {
											break;
										}
										else {
											currentItemAmount-=a.getAmount();
										}
										
									}
								}
							}
						}
						if(j == player.getInventory().getSize()-1) {
							DoesNotHaveItems=true;
						}
					}
				}
				else {
					for(int j=0; j<player.getInventory().getSize(); j++) {
						ItemStack a = player.getInventory().getItem(j);
						if(a != null) {
							if(a.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)){
							}
							else if(a.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)){
							}
							else if(a.getType() == currentItem.getType()) {
								if(a.getAmount() >= currentItemAmount) {
									break;
								}
								else {
									currentItemAmount-=a.getAmount();
								}
							}
						}
						if(j == player.getInventory().getSize()-1) {
							DoesNotHaveItems=true;
						}
					}
				}
			}
			else {
				for(int j=0; j<player.getInventory().getSize(); j++) {
					ItemStack a = player.getInventory().getItem(j);
					if(a != null) {
						if(a.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)){
						}
						else if(a.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)){
						}
						else if(a.getType() == currentItem.getType()) {
							if(a.getAmount() >= currentItemAmount) {
								break;
							}
							else {
								currentItemAmount-=a.getAmount();
							}
						}
					}
					if(j == player.getInventory().getSize()-1) {
						DoesNotHaveItems=true;
					}
				}
			}
		}
		if(!DoesNotHaveItems) {
			item1 = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
			meta = item1.getItemMeta();
			meta.setDisplayName(ChatColor.GREEN+"Craft");
			item1.setItemMeta(meta);
			inv.setItem(31, item1);
		}
		else {
			item1 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
			meta = item1.getItemMeta();
			meta.setDisplayName(ChatColor.RED+"Not Enough Materials");
			item1.setItemMeta(meta);
			inv.setItem(31, item1);
		}
		
		//
		return inv;
	}
}
