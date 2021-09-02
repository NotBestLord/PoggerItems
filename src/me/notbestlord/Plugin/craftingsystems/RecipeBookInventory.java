package me.notbestlord.Plugin.craftingsystems;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.commands.Recipes;
import me.notbestlord.Plugin.items.ItemManager;
import me.notbestlord.Plugin.items.MachineRecipes;

public class RecipeBookInventory {
	
	public static Hashtable<UUID, Inventory> inventory = new Hashtable<>();
	public static Hashtable<UUID, Inventory> subinventory = new Hashtable<>();
	public static Hashtable<UUID, Inventory> subsubinventory = new Hashtable<>();
	
	public static void createRecipeBook(Player player) {
		Inventory inv;
		inv = Bukkit.createInventory(null, 27, ChatColor.AQUA+"Recipes");
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
		inv.setItem(12, item1);
		inv.setItem(13, item1);
		inv.setItem(14, item1);
		inv.setItem(16, item1);
		inv.setItem(17, item1);
		inv.setItem(18, item1);
		inv.setItem(19, item1);
		inv.setItem(20, item1);
		inv.setItem(21, item1);
		inv.setItem(23, item1);
		inv.setItem(24, item1);
		inv.setItem(25, item1);
		inv.setItem(26, item1);
		
		item1.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED+"Close");
		item1.setItemMeta(meta);
		inv.setItem(22, item1);
		item1.setType(Material.IRON_INGOT);
		meta.setDisplayName(ChatColor.GREEN+"Ingredients");
		item1.setItemMeta(meta);
		inv.setItem(15, item1);
		item1.setType(Material.IRON_PICKAXE);
		meta.setDisplayName(ChatColor.BLUE+"Items");
		item1.setItemMeta(meta);
		inv.setItem(11, item1);
		item1.setType(Material.PISTON);
		meta.setDisplayName(ChatColor.AQUA+"Machines");
		item1.setItemMeta(meta);
		inv.setItem(13, item1);
		
		inventory.put(player.getUniqueId(), inv);
	}
	
	public static void createRecipeBookSubinventory(Player player, int menuType, int page) {
		Inventory inv;
		inv = Bukkit.createInventory(null, 54, ChatColor.AQUA+"Recipes");
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
		Collection<ArrayList> set = RecipeBookSelectorEvent.ItemRecipes.values();
		if(menuType == 0) {
			set = RecipeBookSelectorEvent.ItemRecipes.values();
			
		}else if (menuType == 1){
			set = RecipeBookSelectorEvent.IngredientRecipes.values();
		}else if(menuType == 2) {
			set = RecipeBookSelectorEvent.MachineRecipes.values();
		}
		//
		ArrayList[] set2 = new ArrayList[set.size()];
		set.toArray(set2);
		ItemStack[] Recipes = new ItemStack[set.size()];
		for(int i=0; i<set2.length; i++) {
			Recipes[i] =(ItemStack) set2[i].get(0);
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
		subinventory.put(player.getUniqueId(), inv);
	}
	
	public static void showRecipe(Player player, ItemStack inputItem, ArrayList array) {
		Inventory inv;
		inv = Bukkit.createInventory(null, 27, ChatColor.AQUA+"Recipes");
		ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = item1.getItemMeta();
		meta.setDisplayName(" ");
		item1.setItemMeta(meta);
		for(int i=0; i<27; i++) {
			inv.setItem(i, item1);
		}
		item1.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED+"Close");
		item1.setItemMeta(meta);
		inv.setItem(22, item1);
		String Crafter = (String) array.get(1);
		if(Crafter.equals("CraftingTable") || Crafter.equals("MultiBlock")) {
			ShapedRecipe recipe = (ShapedRecipe) array.get(2);
			String[] recipeShape = recipe.getShape();
			Map<Character, ItemStack> ingredients = recipe.getIngredientMap();
			String row;
			Character sign;
			for(int i=0; i<3; i++) {
				row = recipeShape[i];
				for(int j = 0; j<3; j++) {
					sign = row.charAt(j);
					if(ingredients.containsKey(sign)) {
						ItemStack output = (ItemStack) ingredients.get(sign);
						if(i==0) {
							inv.setItem(j, output);
						}
						else if(i==1) {
							inv.setItem(j+9, output);
						}
						else if(i==2) {
							inv.setItem(j+18, output);
						}
					}
					
				}
				
			}

			inv.setItem(13, inputItem);
			if(Crafter.equals("MultiBlock")) {
				ItemStack item = new ItemStack(Material.COBBLESTONE);
				meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.WHITE+""+ChatColor.BOLD+"MultiBlock - place blocks in world");
				item.setItemMeta(meta);
				inv.setItem(4, item);
				if(array.size()>=4) {
					item = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
					meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.WHITE+""+ChatColor.BOLD+"Catalyst - "+ChatColor.RESET+""+ChatColor.WHITE+"right click base block of the multi block.");
					item.setItemMeta(meta);
					inv.setItem(7, item);
					inv.setItem(16, (ItemStack)array.get(3));
				}
			}
		}
		else if(Crafter.equals("HydraulicPress")) {
			ItemStack piston = new ItemStack(Material.PLAYER_HEAD, 1);
			ItemStack piston2 = new ItemStack(Material.PLAYER_HEAD, 1);
			SkullMeta pmeta = (SkullMeta) piston.getItemMeta();
			SkullMeta pmeta2 = (SkullMeta) piston2.getItemMeta();
			pmeta.setDisplayName(" ");
			pmeta2.setDisplayName(" ");
			GameProfile profile = new GameProfile(UUID.randomUUID(), null);
			profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ1Y2Y5MmJjNzllYzE5ZjQxMDY0NDFhZmZmZjE0MDZhMTM2NzAxMGRjYWZiMTk3ZGQ5NGNmY2ExYTZkZTBmYyJ9fX0"));
			Field profileField;
			try {
				profileField = pmeta.getClass().getDeclaredField("profile");
				profileField.setAccessible(true);
				profileField.set(pmeta, profile);
			}catch (Exception e) {
				
			}
			piston.setItemMeta(pmeta);
			GameProfile profile2 = new GameProfile(UUID.randomUUID(), null);
			Field profileField2;
			profile2.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjlhOWQ4OWE0ZDAyNTMzZDg2NGFjNGEyZDVkOWY2ZGIzOWM2ZjNmNDdhMjk1NDUzNTc2NTI5MjRmYmZkNDA4OCJ9fX0"));
			try {
				profileField2 = pmeta2.getClass().getDeclaredField("profile");
				profileField2.setAccessible(true);
				profileField2.set(pmeta2, profile2);
			}catch (Exception e) {
				
			}
			piston2.setItemMeta(pmeta2);
			inv.setItem(1, piston);
			inv.setItem(19, piston2);
			inv.setItem(10, (ItemStack)array.get(2));
			inv.setItem(13, inputItem);
			inv.setItem(4, ItemManager.HydraulicPress);
		}
		else if(Crafter.equals("Furnace")) {
			FurnaceRecipe recipe = (FurnaceRecipe) array.get(2);
			inv.setItem(10, recipe.getInput());
			ItemStack item2 = new ItemStack(Material.FURNACE);
			ItemMeta meta2 = item2.getItemMeta();
			meta2.setDisplayName(ChatColor.WHITE+"Smelt");
			item2.setItemMeta(meta2);
			inv.setItem(19, item2);
			inv.setItem(13, inputItem);
		}
		else if(Crafter.equals("FissionReactor")) {
			inv.setItem(4, ItemManager.FissionReactor);
			item1.setType(Material.BLUE_STAINED_GLASS_PANE);
			meta = item1.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE+"Secondary output");
			item1.setItemMeta(meta);
			inv.setItem(10, (ItemStack) array.get(2));
			inv.setItem(7, item1);
			meta.setDisplayName(ChatColor.WHITE+"Input");
			item1.setItemMeta(meta);
			inv.setItem(1, item1);
			inv.setItem(16, (ItemStack) array.get(3));
			inv.setItem(13, inputItem);
		}
		else if(Crafter.equals("MobDrop")) {
			inv.setItem(10, (ItemStack) array.get(2));
			inv.setItem(13, inputItem);
		}
		else if(Crafter.equals("LiquidMixer")) {
			inv.setItem(1, (ItemStack) array.get(2));
			inv.setItem(19, (ItemStack) array.get(4));
			inv.setItem(17, MachineRecipes.LiquidMixer);
			ItemStack in = new ItemStack((ItemStack)array.get(0));
			String name = ChatColor.WHITE+in.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "nameextention"), PersistentDataType.STRING);
			name+=in.getItemMeta().getDisplayName();
			ItemMeta metaIn = in.getItemMeta();
			metaIn.setDisplayName(name);
			in.setItemMeta(metaIn);
			inv.setItem(13, in);
		}
		else if(Crafter.equals("Solidifier")) {
			inv.setItem(10, (ItemStack) array.get(2));
			inv.setItem(17, MachineRecipes.Solidifier);
			inv.setItem(13, inputItem);
		}
		else if(Crafter.equals("Melter")) {
			inv.setItem(10, (ItemStack) array.get(2));
			inv.setItem(17, MachineRecipes.Melter);
			ItemStack in = new ItemStack((ItemStack)array.get(0));
			String name = ChatColor.WHITE+in.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "nameextention"), PersistentDataType.STRING);
			name+=in.getItemMeta().getDisplayName();
			ItemMeta metaIn = in.getItemMeta();
			metaIn.setDisplayName(name);
			in.setItemMeta(metaIn);
			inv.setItem(13, in);
		}
		else if(Crafter.equals("OilExtractor")) {
			inv.setItem(10, (ItemStack) array.get(2));
			inv.setItem(17, MachineRecipes.OilExtractor);
			inv.setItem(13, (ItemStack) array.get(0));
		}
		else if(Crafter.equals("OilProcessor")) {
			inv.setItem(10, (ItemStack) array.get(2));
			inv.setItem(17, MachineRecipes.OilProcessor);
			inv.setItem(13, (ItemStack) array.get(0));
		}
		else if(Crafter.equals("FluidInfuser")) {
			inv.setItem(1, (ItemStack) array.get(2));
			inv.setItem(19, (ItemStack) array.get(4));
			inv.setItem(17, MachineRecipes.FluidInfuser);
			inv.setItem(13, (ItemStack) array.get(0));
		}
		else if(Crafter.equals("IndustrialSaw")) {
			inv.setItem(13, inputItem);
			inv.setItem(10, (ItemStack) array.get(2));
			inv.setItem(17, MachineRecipes.IndustrialSaw);
		}
		subsubinventory.put(player.getUniqueId(), inv);
	}
}
