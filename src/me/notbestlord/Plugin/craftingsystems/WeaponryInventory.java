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

public class WeaponryInventory {
	
	public static Hashtable<Location, Inventory> inventory = new Hashtable<>();
	public static HashMap<String, ArrayList> RecipeList = new HashMap<>();
	
	public static void createMainInventory(Location location, int page) {
		Inventory inv;
		inv = Bukkit.createInventory(null, 54, ChatColor.AQUA+"Weaponry");
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
		
		Collection<ArrayList> set = RecipeList.values();
		ArrayList[] set2 = new ArrayList[set.size()];
		for(int i=0; i<set.size(); i++) {
			set2[i] = new  ArrayList<>((ArrayList)set.toArray()[i]);
		}
		ItemStack[] Recipes = new ItemStack[set.size()];
		for(int i=0; i<set2.length; i++) {
			Recipes[i] = new ItemStack((ItemStack) set2[i].get(set2[i].size()-1));
			set2[i].remove(set2[i].size()-1);
			List<String> lore = Recipes[i].getItemMeta().getLore();
			lore.add(ChatColor.YELLOW+"Items Required");
			for(int j=0;j<set2[i].size(); j+=2) {
				lore.add(ChatColor.WHITE+" "+((ItemStack)set2[i].get(j)).getItemMeta().getDisplayName()+ChatColor.GRAY+" x"+set2[i].get(j+1));
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
		
		inventory.put(location, inv);
	}
	
	public static Inventory createSubinventory(Player player, String inputID) {
		Inventory inv;
		inv = Bukkit.createInventory(null, 54, ChatColor.AQUA+"weaponry");
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
		
		ArrayList array = new ArrayList(RecipeList.get(inputID));
		ArrayList<ItemStack> inputs = new ArrayList<ItemStack>();
		ItemStack output = new ItemStack((ItemStack)array.get(array.size()-1));
		array.remove(array.size()-1);
		for(int i=0; i<array.size(); i+=2) {
			ItemStack temp = new ItemStack((ItemStack)array.get(i));
			int amount = (int)array.get(i+1);
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
	
	
	@SuppressWarnings("unchecked")
	public static void addAllRecipes() {
		ArrayList array = new ArrayList<>();
		ItemStack item = new ItemStack(Material.GUNPOWDER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Gunpowder");
		item.setItemMeta(meta);
		array.add(new ItemStack(item));
		array.add(64);
		array.add(IngredientManager.PerfectSteelIngot);
		array.add(4);
		array.add(ItemManager.Pistol);
		RecipeList.put("pistol", new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.GUNPOWDER);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Gunpowder");
		item.setItemMeta(meta);
		array.add(new ItemStack(item));
		array.add(4);
		array.add(IngredientManager.CondensedCopperIngot);
		array.add(1);
		array.add(ItemManager.GunAmmo[0]);
		RecipeList.put("9mmAmmo", new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.GUNPOWDER);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Gunpowder");
		item.setItemMeta(meta);
		array.add(new ItemStack(item));
		array.add(8);
		array.add(IngredientManager.CondensedIronIngot);
		array.add(1);
		array.add(ItemManager.GunAmmo[1]);
		RecipeList.put("revolverAmmo", new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.GUNPOWDER);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Gunpowder");
		item.setItemMeta(meta);
		array.add(new ItemStack(item));
		array.add(8);
		array.add(IngredientManager.SteelIngot);
		array.add(1);
		array.add(ItemManager.GunAmmo[2]);
		RecipeList.put("sniperAmmo", new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.GUNPOWDER);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Gunpowder");
		item.setItemMeta(meta);
		array.add(new ItemStack(item));
		array.add(8);
		array.add(IngredientManager.CondensedIronIngot);
		array.add(1);
		array.add(IngredientManager.SteelIngot);
		array.add(1);
		array.add(ItemManager.GunAmmo[3]);
		RecipeList.put("rifleAmmo", new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.GUNPOWDER);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Gunpowder");
		item.setItemMeta(meta);
		array.add(new ItemStack(item));
		array.add(64);
		array.add(ItemManager.Pistol);
		array.add(1);
		array.add(IngredientManager.SteelIngot);
		array.add(4);
		array.add(ItemManager.Uzi);
		RecipeList.put("uzi", new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.GUNPOWDER);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Gunpowder");
		item.setItemMeta(meta);
		array.add(new ItemStack(item));
		array.add(64);
		array.add(ItemManager.Pistol);
		array.add(1);
		array.add(IngredientManager.SteelIngot);
		array.add(8);
		array.add(ItemManager.Revolver);
		RecipeList.put("revolver", new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.GUNPOWDER);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Gunpowder");
		item.setItemMeta(meta);
		array.add(new ItemStack(item));
		array.add(64);
		array.add(IngredientManager.SteelIngot);
		array.add(12);
		array.add(ItemManager.BarrettM82);
		RecipeList.put("BarrettM82", new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.DIAMOND);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Diamond");
		item.setItemMeta(meta);
		array.add(new ItemStack(item));
		array.add(2);
		array.add(IngredientManager.SteelIngot);
		array.add(8);
		array.add(IngredientManager.AncientFur);
		array.add(24);
		array.add(ItemManager.SniperSet[0]);
		RecipeList.put("sniperboots", new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.DIAMOND);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Diamond");
		item.setItemMeta(meta);
		array.add(new ItemStack(item));
		array.add(2);
		array.add(IngredientManager.SteelIngot);
		array.add(14);
		array.add(IngredientManager.AncientFur);
		array.add(24);
		array.add(ItemManager.SniperSet[1]);
		RecipeList.put("sniperleggings", new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.DIAMOND);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Diamond");
		item.setItemMeta(meta);
		array.add(new ItemStack(item));
		array.add(2);
		array.add(IngredientManager.SteelIngot);
		array.add(16);
		array.add(IngredientManager.AncientFur);
		array.add(24);
		array.add(ItemManager.SniperSet[2]);
		RecipeList.put("sniperchestplate", new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.DIAMOND);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Diamond");
		item.setItemMeta(meta);
		array.add(new ItemStack(item));
		array.add(2);
		array.add(IngredientManager.SteelIngot);
		array.add(10);
		array.add(IngredientManager.AncientFur);
		array.add(24);
		array.add(ItemManager.SniperSet[3]);
		RecipeList.put("sniperhelmet", new ArrayList(array));
		array.clear();
		//
	}
}
