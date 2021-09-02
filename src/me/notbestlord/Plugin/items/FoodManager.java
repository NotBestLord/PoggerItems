package me.notbestlord.Plugin.items;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.craftingsystems.EnchantedWorkBenchInventory;
import me.notbestlord.Plugin.craftingsystems.RecipeBookSelectorEvent;
import me.notbestlord.Plugin.dataManagment.Recipe;
import me.notbestlord.Plugin.dataManagment.Recipe.EnchantedWorkbenchRecipe;
import me.notbestlord.Plugin.events.FoodEvent;
import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("unchecked")
public class FoodManager {
	//Ingredients
	public static Recipe.EnchantedWorkbenchRecipe cropBaseRecipe;
	//Vegetables:
	public static ItemStack TomatoSeed;
	public static ItemStack Tomato;
	public static ItemStack LettuceSeed;
	public static ItemStack Lettuce;
	public static ItemStack CornSeed;
	public static ItemStack Corn;
	public static ItemStack OnionSeed;
	public static ItemStack Onion;
	public static ItemStack BellPepperSeed;
	public static ItemStack BellPepper;
	public static ItemStack GarlicSeed;
	public static ItemStack Garlic;
	public static ItemStack AvocadoSeed;
	public static ItemStack Avocado;
	//
	//Fruit:
	public static ItemStack StrawberrySeed;
	public static ItemStack Strawberry;
	public static ItemStack OrangeSeed;
	public static ItemStack Orange;
	public static ItemStack GrapesSeed;
	public static ItemStack Grapes;
	public static ItemStack CherrySeed;
	public static ItemStack Cherry;
	public static ItemStack MangoSeed;
	public static ItemStack Mango;
	public static ItemStack LemonSeed;
	public static ItemStack Lemon;
	public static ItemStack LimeSeed;
	public static ItemStack Lime;
	public static ItemStack PeachSeed;
	public static ItemStack Peach;
	public static ItemStack KiwiSeed;
	public static ItemStack Kiwi;
	public static ItemStack SlimeFruitSeed;
	public static ItemStack SlimeFruit;
	public static ItemStack OilFruitSeed;
	public static ItemStack OilFruit;
	public static List<ItemStack> FruitList = new ArrayList<>();
	//
	//Else:
	public static ItemStack RiceSeed;
	public static ItemStack Rice;
	
	//Food:
	public static ItemStack Hamburger;
	public static ItemStack Marshmallow;
	public static ItemStack Chocolate;
	public static ItemStack Smore;
	public static ItemStack DoubleSmore;
	public static ItemStack QuadSmore;
	public static ItemStack FrenchFries;
	public static ItemStack Nuggets;
	public static ItemStack SweetBerrySoda;
	public static ItemStack UltimateHappyMeal;
	public static ItemStack CookedRice;
	public static ItemStack Cheese;
	public static ItemStack FruitSalad;
	public static ItemStack CookedEgg;
	public static ItemStack EggSushi;
	public static ItemStack SalmonSushi;
	public static ItemStack Sushi;
	
	//Robotic Food
	public static ItemStack CoalBall;
	public static ItemStack CharcoalBall;
	public static ItemStack CopperBall;
	public static ItemStack IronBall;
	public static ItemStack GoldBall;
	public static ItemStack SteelBall;
	public static ItemStack NutsAndBoltsCereal;
	public static ItemStack IronBread;
	public static ItemStack CharNCoalSandwich;
	
	
	
	/*
	foods:
	- pizza
	- hotdog
	- tomato sauce
	- juices
	- drinks in general
	- coffee
	- ice cream
	- popsickle
	
	 */
	
	public static void init() {
		//Ingredients:
		createCropBaseRecipe();
		//Vegetables:
		createTomato();
		createLettuce();
		createCorn();
		createOnion();
		createBellPepper();
		createGarlic();
		createAvocado();
		
		//
		//Fruit:
		createStrawberry();
		createOrange();
		createGrapes();
		createCherry();
		createMango();
		createLemon();
		createLime();
		createPeach();
		createKiwi();
		createSlimeFruit();
		createOilFruit();
		
		//
		//Else:
		createRice();
		createCookedRice();
		
		//
		//Food:
		createHamburger();
		createMarshmallow();
		createChocolate();
		createSmore();
		createDoubleSmore();
		createQuadSmore();
		createFrenchFries();
		createNuggets();
		createSweetBerrySoda();
		createUltimateHappyMeal();
		createCheese();
		createFruitSalad();
		createCookedEgg();
		createEggSushi();
		createSalmonSushi();
		createSushi();
		
		//
		//
		//Metallic:
		createCharcoalBall();
		createCoalBall();
		createCopperBall();
		createIronBall();
		createGoldBall();
		createSteelBall();
		createNutsNBoltsCereal();
		createIronBread();
		createCharNCoalSandwich();
	}
	
	/*
	lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
	lore.add(ChatColor.of("#ff8b8e")+"20 Saturation");
	lore.add(ChatColor.of("#ff8b8e")+"20 Hunger");
	lore.add(ChatColor.of("#af0004")+"Special Buff: "+ChatColor.of("#ff8b8e")+"Speed I for 60 Seconds");
	lore.add(ChatColor.of("#af0004")+"Special Buff: "+ChatColor.of("#ff8b8e")+"Jump Boost II for 60 Seconds");
	lore.add(ChatColor.of("#af0004")+"Special Buff: "+ChatColor.of("#ff8b8e")+"Regeneration I for 10 Seconds");
	 */
	
	private static void createCropBaseRecipe() {
		cropBaseRecipe = new EnchantedWorkbenchRecipe("", new ItemStack(Material.AIR));
		cropBaseRecipe.addIngredient(Material.WHEAT_SEEDS, 16);
		cropBaseRecipe.addIngredient(Material.POTATO, 16);
		cropBaseRecipe.addIngredient(Material.CARROT, 16);
		cropBaseRecipe.addIngredient(Material.BEETROOT_SEEDS, 16);
	}
	
	private static void createTomato() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#00c200")+"Tomato Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "tomatoseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop000");
		item.setItemMeta(meta);
		TomatoSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("tomatoseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("tomatoseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Tomato");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"1 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "tomato");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop000");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2FiZmQwODc5ODFhNmZhNGE2MjE3NzcyMDA3NDQyMWRiMjg1OWY4YzFlZWY5OTI3MjdkYzlhZTA4NzEyODBiOSJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Tomato=item;
		FoodEvent.registerFood("tomato", "plant", 1, 2, null, false);
	}
	//
	private static void createLettuce() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#00c200")+"Lettuce Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "lettuceseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop001");
		item.setItemMeta(meta);
		LettuceSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("lettuceseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("lettuceseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Lettuce");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"1 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "lettuce");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop001");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTk3OWZlN2IzMmZiN2ZjOTc0NmUxNTc0NzFmOTc1ZjMxYWZjYjU4ZmQ4YjVhY2I4YmY1M2VjMzUwZjRkNWMyIn19fQ"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Lettuce=item;
		FoodEvent.registerFood("lettuce", "plant", 1, 2, null, false);
	}
	//
	private static void createCorn() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#00c200")+"Corn Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "cornseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop002");
		item.setItemMeta(meta);
		CornSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("cornseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("cornseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Corn");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"1 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "corn");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop002");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDM5MWRmZmJlYTJmYzNmMmFkNzhhNjIzZjQ5YmY3ZTExMjE2OTQxMTJjMzc1OWZlZWQ0MTU2ZmMyYmE0NmMwIn19fQ"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Corn=item;
		FoodEvent.registerFood("corn", "plant", 1, 2, null, false);
	}
	//
	private static void createOnion() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#00c200")+"Onion Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "onionseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop003");
		item.setItemMeta(meta);
		OnionSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("onionseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("onionseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Onion");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"1 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "onion");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop003");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTZlY2M0NmRjM2RjODVmY2Q1NzE5ODE3NmVlODQxZjFhMDQxYjE1ZjczZWNiMTlmZGU2MmVlNDMxNWM0YTYifX19"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Onion=item;
		FoodEvent.registerFood("onion", "plant", 1, 2, null, false);
	}
	//
	private static void createBellPepper() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#00c200")+"Bell Pepper Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "bellpepperseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop004");
		item.setItemMeta(meta);
		BellPepperSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("bellpepperseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("bellpepperseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Bell Pepper");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"1 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "bellpepper");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop004");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjVmNzgxMDQxNGEyY2VlMmJjMWRlMTJlY2VmN2E0Yzg5ZmM5YjM4ZTlkMDQxNGE5MDk5MTI0MWE1ODYzNzA1ZiJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		BellPepper=item;
		FoodEvent.registerFood("bellpepper", "plant", 1, 2, null, false);
	}
	//
	private static void createGarlic() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#00c200")+"Garlic Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "garlicseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop005");
		item.setItemMeta(meta);
		GarlicSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("garlicseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("garlicseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Garlic");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"1 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "garlic");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop005");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzA1MmQ5YzExODQ4ZWJjYzlmODM0MDMzMjU3N2JmMWQyMmI2NDNjMzRjNmFhOTFmZTRjMTZkNWE3M2Y2ZDgifX19"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Garlic=item;
		FoodEvent.registerFood("garlic", "plant", 1, 2, null, false);
	}
	//
	private static void createAvocado() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#00c200")+"Avocado Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "avocadoseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop006");
		item.setItemMeta(meta);
		AvocadoSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("avocadoseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("avocadoseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Avocado");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"1 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "avocado");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop006");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjk2ODVhMzlkYTFkMzkxNzVmN2FhNmQzOGIwMzEzOGRjYjFjOTA3MGEwYzU5ZDNhMTMxMzFiMWE2NDIyYzI2NiJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Avocado=item;
		FoodEvent.registerFood("avocado", "plant", 1, 2, null, false);
	}
	//
	private static void createRice() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#00b100")+"Rice Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "riceseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop100");
		item.setItemMeta(meta);
		RiceSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("riceseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("riceseed", recipe);
		//
		item = new ItemStack(Material.SUGAR, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Rice");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.GRAY+"Can be Cooked");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "rice");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop100");
		item.setItemMeta(meta);
		Rice=item;
		FoodEvent.registerFood("rice", "plant", 1, 2, null, false);
	}
	//
	private static void createStrawberry() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#06ff06")+"Strawberry Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "strawberryseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop200");
		item.setItemMeta(meta);
		StrawberrySeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("strawberryseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("strawberryseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Strawberry");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"1 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "strawberry");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop200");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWM5YTgzMTVmZGMzMDdiODMxNTkxOGVlYWU3ZGQ4NDI2OTEwNGIzZDliZDc3OWZjMmJhNzc5NTE1YjgwMjE0ZCJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Strawberry=item;
		FruitList.add(item);
		FoodEvent.registerFood("strawberry", "plant", 1, 2, null, false);
	}
	//
	private static void createOrange() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#06ff06")+"Orange Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "orangeseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop201");
		item.setItemMeta(meta);
		OrangeSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("orangeseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("orangeseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Orange");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"1 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "orange");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop201");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWZkMGUzMzBhNjg4ZDhiYjk1MTliZWZlMWJmYzM0MzM3YjM3MWFjNzUxNTAyMTZmZGQwMzk1OWViN2I0NCJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Orange=item;
		FruitList.add(item);
		FoodEvent.registerFood("orange", "plant", 1, 2, null, false);
	}
	//
	private static void createGrapes() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#06ff06")+"Grapes Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "grapesseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop202");
		item.setItemMeta(meta);
		GrapesSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("grapesseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("grapesseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Grapes");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"3 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "grapes");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop202");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGNkY2YzOGE4NDM4ZWQzYTU0N2Y4ZDViNDdlMDgwMTU1OWM1OTVmMGUyNmM0NTY1NmE3NmI1YmY4YTU2ZiJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Grapes=item;
		FruitList.add(item);
		FoodEvent.registerFood("grapes", "plant", 1, 2, null, false);
	}
	//
	private static void createCherry() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#06ff06")+"Cherry Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "cherryseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop203");
		item.setItemMeta(meta);
		CherrySeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("cherryseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("cherryseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Cherry");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"1 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "cherry");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop203");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTdjZTcyNWFhZDU5YTVhNjY2YWEwZjBlNzg3YTc3ZDhhYmI2NmE0YWZjYjc4YzE2OWJiODFjYmVjYjJmZCJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Cherry=item;
		FruitList.add(item);
		FoodEvent.registerFood("cherry", "plant", 1, 2, null, false);
	}
	//
	private static void createMango() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#06ff06")+"Mango Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "mangoseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop204");
		item.setItemMeta(meta);
		MangoSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("mangoseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("mangoseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Mango");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"1 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "mango");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop204");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTk1MzM4OGNjZDZmZjY0ZmMxOTUwZGQ0Y2VjNGFkZWFhMzQ4YWFhYmNjNTMyODI4ZTcxNDRmNTA5ZjIzYyJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Mango=item;
		FruitList.add(item);
		FoodEvent.registerFood("mango", "plant", 1, 2, null, false);
	}
	//
	private static void createLemon() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#06ff06")+"Lemon Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "lemonseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop205");
		item.setItemMeta(meta);
		LemonSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("lemonseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("lemonseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Lemon");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"1 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "lemon");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop205");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDM3OGI1ODJkMTljY2M1NWIwMjNlYjgyZWRhMjcxYmFjNDc0NGZhMjAwNmNmNWUxOTAyNDZlMmI0ZDVkIn19fQ"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Lemon=item;
		FruitList.add(item);
		FoodEvent.registerFood("lemon", "plant", 1, 2, null, false);
	}
	//
	private static void createLime() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#06ff06")+"Lime Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "limeseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop206");
		item.setItemMeta(meta);
		LimeSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("limeseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("limeseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Lime");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"1 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "lime");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop206");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2YyNGI3MTM1Nzg5ZmU3OTlkZjM0NTk0ZDY4MDVmNTExMmJlZTYyMzI2MDViYTZkZTIxNTE4NmFkOTQifX19"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Lime=item;
		FruitList.add(item);
		FoodEvent.registerFood("lime", "plant", 1, 2, null, false);
	}
	//
	private static void createPeach() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#06ff06")+"Peach Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "peachseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop207");
		item.setItemMeta(meta);
		PeachSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("peachseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("peachseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Peach");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"1 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "peach");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop207");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzg0MDRjZmU3YWQxMTY2ODFiNDg1MGI4OTk5OGFjZjAzY2ViZTQ2ODVjYzc2NjM4OTMwNjUyZGM0YWZjMzc2MiJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Peach=item;
		FruitList.add(item);
		FoodEvent.registerFood("peach", "plant", 1, 2, null, false);
	}
	//
	private static void createKiwi() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#06ff06")+"Kiwi Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "kiwiseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop208");
		item.setItemMeta(meta);
		KiwiSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("kiwiseed");
		recipe.setResult(item);
		EnchantedWorkBenchInventory.RecipeList.put("kiwiseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Kiwi");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"1 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "kiwi");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop208");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGNjMThlYzQ2NDlmMDdkNWEzOGE1ODNkOTI3MWZkODNhNmYzNzMxODc1OGU0NmVhODdmYzJiMmQxYWZjMmQ5In19fQ"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Kiwi=item;
		FruitList.add(item);
		FoodEvent.registerFood("kiwi", "plant", 1, 2, null, false);
	}
	//
	private static void createSlimeFruit() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#06ff06")+"Slime Fruit Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "slimefruitseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop209");
		item.setItemMeta(meta);
		SlimeFruitSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("slimefruitseed");
		recipe.setResult(item);
		recipe.addIngredient(Material.SLIME_BALL, 128);
		EnchantedWorkBenchInventory.RecipeList.put("slimefruitseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Slime Fruit");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"6 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"4 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "slimefruit");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop209");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmFiOTkyMzVlNTI3YzRjZTM1ZDMzY2NjMWExNDRkMjgxMjg4OGFhODRmMGQ2OWFhMTllNTQyOGU1MDdhMDFhMyJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		SlimeFruit=item;
		FruitList.add(item);
		FoodEvent.registerFood("slimefruit", "plant", 6, 4, null, false);
	}
	//
	private static void createOilFruit() {
		ItemStack item = new ItemStack(Material.WHEAT_SEEDS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#06ff06")+"Oil Fruit Seed");
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "oilfruitseed");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "zcrop210");
		item.setItemMeta(meta);
		OilFruitSeed=item;
		EnchantedWorkbenchRecipe recipe = cropBaseRecipe.clone();
		recipe.setRecipeID("oilfruitseed");
		recipe.setResult(item);
		recipe.addIngredient(Material.COAL, 128);
		recipe.addIngredient(Material.CHARCOAL, 128);
		EnchantedWorkBenchInventory.RecipeList.put("oilfruitseed", recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GREEN+"Oil Fruit");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Metallic.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"10 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"5 Hunger");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "oilfruit");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "crop210");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2ZkZWNhNmY0YTZkY2NhYTQwMTk1YmExMzU3MDUyODQwM2ExYjlkMmQ5ZjMyOTM3M2YyZGU5Y2JkOTA5NjI3MiJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		OilFruit=item;
		FruitList.add(item);
		FoodEvent.registerFood("oilfruit", "metal", 10, 5, null, false);
	}
	//
	
	//
	//
	//
	//
	//
	private static void createHamburger() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Hamburger");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Meat.");
		lore.add(ChatColor.of("#ff8b8e")+"14.4 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"10 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "hamburger");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food01");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDgzM2NlM2FkOWU2Mzg2OGM1YzhlMWM3MzEwYTk3NjdmY2FhZGVjNmFiYzkxZjhiZTcyY2ZhMmVjMWY5In19fQ==="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Hamburger=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("hamburger"), item);
		sr.shape("xxx",
				 "zyw",
				 "xxx");
		sr.setIngredient('x', Material.BREAD);
		sr.setIngredient('y', Material.COOKED_BEEF);
		sr.setIngredient('z', Material.GOLDEN_CARROT);
		sr.setIngredient('w', Material.BAKED_POTATO);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("hamburger", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("hamburger", "meat", 14.4f, 10, null, false);
	}
	//
	private static void createMarshmallow() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Marshmallow");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Other.");
		lore.add(ChatColor.of("#ff8b8e")+"8 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"3 Hunger"); 
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "marshmallow");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food02");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTQ3ZGU4OTE0YWMyY2I5NzVmODU1ODdlMzNiZTk2N2E4MWVlNzUxYjQ1MzJhOWJmMmVhOWZiODVmMmQ3NTliZSJ9fX0============"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Marshmallow=item;
		FoodEvent.registerFood("marshmallow", "other", 8f, 3, null, false);
	}
	//
	private static void createChocolate() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Chocolate");
		List<String> lore = new ArrayList<>();
		lore.add("§7CHOCOOOOLLLLAAATE!!!!!!");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Other.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"4 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "chocolate");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food03");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmFjZmM3ODY0NjllNjM2YmY0ZWFhYzJlZDQ5ZDlhNmM1MjEyYWY2ZDkwNzFkOTYxOTQ0YmU4YTkzNWY0NzhlIn19fQ"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Chocolate=item;
		FoodEvent.registerFood("chocolate", "other", 4f, 2, null, false);
	}
	//
	private static void createSmore() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Smore");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Other.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"8 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"3 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "smore");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food04");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQzYzcwNWQ0NGE4N2EwZTc5MmE5MDdhNTMzOTE3N2FkZmNhNWNhYTBmZTcwZjRhOTA4NWViZGY0ZDNjYzZiNSJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Smore=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("smore"), item);
		sr.shape(" x ",
				 "zyz",
				 " x ");
		sr.setIngredient('x', Material.BREAD);
		sr.setIngredient('y', new RecipeChoice.ExactChoice(Chocolate));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(Marshmallow));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("smore", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("smore", "other", 8f, 3, null, false);
	}
	//
	private static void createDoubleSmore() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2More Smore Double Smingot");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Other.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"12 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"5 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "doublesmore");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food05");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQzYzcwNWQ0NGE4N2EwZTc5MmE5MDdhNTMzOTE3N2FkZmNhNWNhYTBmZTcwZjRhOTA4NWViZGY0ZDNjYzZiNSJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		DoubleSmore=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("doublesmore"), item);
		sr.shape(" x ",
				 "zyz",
				 " x ");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(Smore));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(Chocolate));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(Marshmallow));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("doublesmore", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("doublesmore", "other", 12f, 5, null, false);
	}
	//
	private static void createQuadSmore() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Four Smore Quad Smingot");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Other.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"18 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"8 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "quadsmore");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food06");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzQzYzcwNWQ0NGE4N2EwZTc5MmE5MDdhNTMzOTE3N2FkZmNhNWNhYTBmZTcwZjRhOTA4NWViZGY0ZDNjYzZiNSJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		QuadSmore=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("quadsmore"), item);
		sr.shape(" x ",
				 "zyz",
				 " x ");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(DoubleSmore));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(Chocolate));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(Marshmallow));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("quadsmore", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("quadsmore", "other", 18f, 8, null, false);
	}
	//
	private static void createFrenchFries() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2French Fries");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"20 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"2 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "frenchfries");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food07");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYThmYTU2Mjg1Njk1OWRmZWZiYzEzMjhjZGZkOWQ0MzBiNjVhZjdmMjRjZjMyNjQwMDc2NzMwNWIzNGM1YjJlNSJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		FrenchFries=item;
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("FluidInfuser");
		ItemStack item2 = new ItemStack(Material.BAKED_POTATO, 64);
		array.add(item2);
		ItemStack item3 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta meta3 = item3.getItemMeta();
		meta3.setDisplayName(ChatColor.GRAY+"2500mb of Crude oil");
		lore.add("§8Liquid");
		meta3.setLore(lore);
		lore.clear();
		meta3.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "crudeoil");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "oil");
		item3.setItemMeta(meta3);
		array.add(null);
		array.add(item3);
		RecipeBookSelectorEvent.addRecipe("frenchfries", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("frenchfries", "plant", 20f, 2, null, false);
	}
	//
	private static void createNuggets() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Nuggets");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Meat.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"14.4 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"8 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "nuggets");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food08");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTFlYzQ1Y2VmNzBlODQ2ZmY0OGVhMTc0YjYwZGM2YWQyZTJhYjdhN2NjZjdlZmYzMmU5MTRjNTNkNWYzZmM4NyJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Nuggets=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("nuggets"), item);
		sr.shape("xxx",
				 "yxy",
				 "yyy");
		sr.setIngredient('x', Material.COOKED_CHICKEN);
		sr.setIngredient('y', Material.PAPER);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("nuggets", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("nuggets", "meat", 14.4f, 8, null, false);
	}
	//
	private static void createSweetBerrySoda() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Sweet Berry Soda");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Other.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"2 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"20 Hunger");
		lore.add(ChatColor.of("#af0004")+"Special Buff: "+ChatColor.of("#ff8b8e")+"Speed I for 60 Seconds");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "sweetberrysoda");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food09");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjBkYmI5Nzc0NzAxZjMwMjQ1YjQxOGMzZWFiZTJiMmVmOWQwMjJjZTdmYWY5MTgzZDAzNWQxMjRkOWRiODVmMCJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		SweetBerrySoda=item;
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("FluidInfuser");
		array.add(IngredientManager.PlasticCup);
		ItemStack item3 = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
		ItemMeta meta3 = item3.getItemMeta();
		meta3.setDisplayName(ChatColor.RED+"4000mb of Carbonated sweet berry juice");
		lore.add("§8Liquid");
		meta3.setLore(lore);
		lore.clear();
		meta3.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "carbonatedsweetberryjuice");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "cswj");
		item3.setItemMeta(meta3);
		array.add(null);
		array.add(item3);
		RecipeBookSelectorEvent.addRecipe("sweetberrysoda", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("sweetberrysoda", "drink", 2f, 20, null, true);
		FoodEvent.addPotionEffect("sweetberrysoda", new PotionEffect(PotionEffectType.SPEED, 1200, 0, true));
	}
	//
	private static void createUltimateHappyMeal() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Ultimate Happy Meal");
		List<String> lore = new ArrayList<>();
		lore.add("§7Its more ultimate then any other happy meal.");
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant & Meat.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"20 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"20 Hunger");
		lore.add(ChatColor.of("#af0004")+"Special Buff: "+ChatColor.of("#ff8b8e")+"Speed I for 60 Seconds");
		lore.add(ChatColor.of("#af0004")+"Special Buff: "+ChatColor.of("#ff8b8e")+"Jump Boost II for 60 Seconds");
		lore.add(ChatColor.of("#af0004")+"Special Buff: "+ChatColor.of("#ff8b8e")+"Regeneration I for 10 Seconds");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "ultimatehappymeal");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food10");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWY0OTg0MjUxYTFiM2Q2MmRjYmNkNjk0YjE0M2UyN2QxODUwNzMzZjQyNjA4N2I4NjBhNDU0NTNhODhiMTQ4MSJ9fX0="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		UltimateHappyMeal=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("ultimatehappymeal"), item);
		sr.shape("pxp",
				 "zyw",
				 "prp");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(Hamburger));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(Nuggets));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(QuadSmore));
		sr.setIngredient('w', new RecipeChoice.ExactChoice(SweetBerrySoda));
		sr.setIngredient('r', new RecipeChoice.ExactChoice(FrenchFries));
		sr.setIngredient('p', Material.PAPER);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("ultimatehappymeal", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("ultimatehappymeal", "meat", 20f, 20, null, false);
		FoodEvent.addFoodType("ultimatehappymeal", "plant");
		FoodEvent.addPotionEffect("ultimatehappymeal", new PotionEffect(PotionEffectType.SPEED, 1200, 0, true));
		FoodEvent.addPotionEffect("ultimatehappymeal", new PotionEffect(PotionEffectType.JUMP, 1200, 1, true));
		FoodEvent.addPotionEffect("ultimatehappymeal", new PotionEffect(PotionEffectType.REGENERATION, 200, 0, true));
	}
	//
	private static void createCookedRice() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Cooked Rice");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"10 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"8 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "cookedrice");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food12");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2I3MGYyZmI1ZWJmNDlmNzlmZjNlODczNjE2ODYzYWU1ZDM2MmZiYmZjMzFhZWYyZGZiOTNkNmUxN2RiZjIifX19"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CookedRice=item;
		FurnaceRecipe sr = new FurnaceRecipe(NamespacedKey.minecraft("cookedrice"), item, Material.SUGAR, 1f, 100);
		sr.setInputChoice(new RecipeChoice.ExactChoice(FoodManager.Rice));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("Furnace");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("cookedrice", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("cookedrice", "plant", 10f, 8, null, false);
	}
	//
	private static void createCheese() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Cheese");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant & Meat.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"5.4 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"3 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "cheese");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food13");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTU1ZDYxMWE4NzhlODIxMjMxNzQ5YjI5NjU3MDhjYWQ5NDI2NTA2NzJkYjA5ZTI2ODQ3YTg4ZTJmYWMyOTQ2In19fQ"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Cheese=item;
		FurnaceRecipe sr = new FurnaceRecipe(NamespacedKey.minecraft("cheese"), item, Material.SUGAR, 1f, 100);
		sr.setInput(Material.MILK_BUCKET);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("Furnace");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("cheese", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("cheese", "plant", 5.4f, 3, null, false);
		FoodEvent.addFoodType("cheese", "meat");
	}
	//
	private static void createFruitSalad() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Fruit Salad");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"8 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"6 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "fruitsalad");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food14");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2ZiZTMwZWYyN2I0ZDk3NGI0ODFmYTU0ZTllODlhMTcyNWI0NjE4NTU4MjYwNTQ5NzRmNjZhY2FmYmY0ZSJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Cheese=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("fruitsalad"), item);
		sr.shape("xxx",
				 "xxx",
				 " y ");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(FruitList));
		sr.setIngredient('y', Material.BOWL);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("fruitsalad", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("fruitsalad", "plant", 8f, 6, null, false);
	}
	//
	private static void createCookedEgg() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Cooked Egg");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant & Meat.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"4 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"4 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "cookedegg");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food15");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTkyYTFiMTExMzI0ZmYyZWMxYzE1MDMzMDQ4NjkwODE3NmVlYjM1YmM3NzU0MzgzN2UyZjMzYWNmN2QwMDdmMSJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CookedEgg=item;
		FurnaceRecipe sr = new FurnaceRecipe(NamespacedKey.minecraft("cookedegg"), item, Material.SUGAR, 1f, 100);
		sr.setInputChoice(new RecipeChoice.MaterialChoice(Arrays.asList(Material.EGG, Material.TURTLE_EGG)));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("Furnace");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("cookedegg", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("cookedegg", "plant", 4f, 4, null, false);
		FoodEvent.addFoodType("cookedegg", "meat");
	}
	//
	private static void createEggSushi() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Egg Sushi");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant & Meat.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"12 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"10 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "eggsushi");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food16");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2I4MmQzYzllZWRjNzE4YzA3NTE5MjU0Zjc5MjFhNTlmZjNhNmYyNDU5Mzk2NjVjOWNiMDE3MTEyY2U2NzAifX19"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		EggSushi=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("eggsushi"), item);
		sr.shape(" c ",
				 "cac",
				 "cbc");
		sr.setIngredient('a', new RecipeChoice.ExactChoice(CookedEgg));
		sr.setIngredient('b', new RecipeChoice.ExactChoice(CookedRice));
		sr.setIngredient('c', Material.DRIED_KELP);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("eggsushi", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("eggsushi", "plant", 12f, 10, null, false);
		FoodEvent.addFoodType("eggsushi", "meat");
	}
	//
	private static void createSalmonSushi() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Salmon Sushi");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant, Meat and Fish.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"6 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"7 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "salmonsushi");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food17");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjNiZjhmY2EyYWYzNTkyYzU1NzRiMTNlM2JjZjYxZTJmYWU4Mjk3ODg1MzVmMGRkZWFhN2EyZTQ1YjZiYTQifX19"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		SalmonSushi=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("salmonsushi"), item);
		sr.shape("   ",
				 " b ",
				 " a ");
		sr.setIngredient('a', new RecipeChoice.ExactChoice(CookedRice));
		sr.setIngredient('b', Material.COOKED_SALMON);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("salmonsushi", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("salmonsushi", "plant", 6f, 7, null, false);
		FoodEvent.addFoodType("salmonsushi", "meat");
		FoodEvent.addFoodType("salmonsushi", "fish");
	}
	//
	private static void createSushi() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Sushi");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Plant, Meat and Fish.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"15 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"10 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "sushi");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food18");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTBlYzU4NWZhMzA0NGNjYmM5NDQ0MWM4YTc5YjRiOGMyM2VhYTZkZGI0ZDUxOTk0OTgyNmM1MWVmMzM1MDU5In19fQ"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Sushi=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("sushi"), item);
		sr.shape(" a ",
				 "cbc",
				 "ddd");
		sr.setIngredient('a', new RecipeChoice.ExactChoice(Avocado));
		sr.setIngredient('b', new RecipeChoice.ExactChoice(CookedRice));
		sr.setIngredient('c', Material.COOKED_SALMON);
		sr.setIngredient('d', Material.DRIED_KELP);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("sushi", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("sushi", "plant", 15f, 10, null, false);
		FoodEvent.addFoodType("sushi", "meat");
		FoodEvent.addFoodType("sushi", "fish");
	}
	//
	//
	//
	//
	//
	//
	//
	//
	//
	private static void createCoalBall() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#adadad")+"Coal Ball");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Metallic.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"5 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"3 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "coalball");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food19");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWU4ZTY3MGE3OWI2MTYzMzRiYzFmYjkxMzkxMzJmYjM1YzU3ZGRhNjk3NWFmOWNlNDgzMzNlOTRhYTZjOTU3MyJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CoalBall=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("coalball"), item);
		sr.shape(" c ",
				 "csc",
				 " c ");
		sr.setIngredient('c', Material.COAL);
		sr.setIngredient('s', Material.COBBLESTONE);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("coalball", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("coalball", "metal", 5f, 3, null, false);
	}
	//
	private static void createCharcoalBall() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#adadad")+"Charcoal Ball");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Metallic.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"5 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"3 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "charcoalball");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food20");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmRhN2RmMGM2NzYxNjMwNWNlZTI5ZTRlYTJlMzQwNjE1NDYzNjY5NWVmZWY5ZmY3MzVlZmQwMDc0YjAwMjg0YSJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CharcoalBall=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("charcoalball"), item);
		sr.shape(" c ",
				 "csc",
				 " c ");
		sr.setIngredient('c', Material.CHARCOAL);
		sr.setIngredient('s', Material.COBBLESTONE);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("charcoalball", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("charcoalball", "metal", 5f, 3, null, false);
	}
	//
	private static void createCopperBall() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#adadad")+"Copper Ball");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Metallic.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"6 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"4 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "copperball");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food20");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODc2NDA2MGI4MThlNGE2NzA2NzEzYzg1MWRmNTJhZmZiNzAwODM3MDE5Y2JkNzcxMjZlYTE1NGU3MGNkNjcxYyJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CopperBall=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("copperball"), item);
		sr.shape(" c ",
				 "csc",
				 " c ");
		sr.setIngredient('c', Material.COPPER_INGOT);
		sr.setIngredient('s', Material.COAL);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("copperball", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("copperball", "metal", 6f, 4, null, false);
	}
	//
	private static void createIronBall() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#adadad")+"Iron Ball");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Metallic.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"7.5 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"5 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "ironball");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food21");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDBmOGRmYTVlZmM3NTYzMGNlMGRmNDBhNDliOGY1OWJjMjIyMTRkZTk3ZTNmYjQ0YjNjNTZlOGE5YzhhNTZiNiJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		IronBall=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("ironball"), item);
		sr.shape(" c ",
				 "csc",
				 " c ");
		sr.setIngredient('c', Material.IRON_INGOT);
		sr.setIngredient('s', Material.COPPER_INGOT);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("ironball", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("ironball", "metal", 7.5f, 5, null, false);
	}
	//
	private static void createGoldBall() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#adadad")+"Gold Ball");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Metallic.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"8 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"6 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "goldball");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food22");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTUxNTk5ZjY2ZTgzZGE1NTVjZjliOGI3ZTVhMzc5ZDBkZWFiMjFjMmVlZTkwOWQxODM3MzIzZGIwODkzYmYzOCJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		GoldBall=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("goldball"), item);
		sr.shape(" c ",
				 "csc",
				 " c ");
		sr.setIngredient('c', Material.GOLD_INGOT);
		sr.setIngredient('s', Material.IRON_INGOT);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("goldball", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("goldball", "metal", 8f, 6, null, false);
	}
	//
	private static void createSteelBall() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#adadad")+"Steel Ball");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Metallic.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"10 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"7 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "steelball");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food23");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzA0YmFhYmY3ZWY4NTQ4MjVhYWUxOTkyZTRhNzVmZjcyODZlZDE2NTRkOGYxYTA4OTUyZTdiODY2OWNmNjkyZCJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		SteelBall=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("steelball"), item);
		sr.shape(" c ",
				 "csc",
				 " c ");
		sr.setIngredient('c', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		sr.setIngredient('s', Material.GOLD_INGOT);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("steelball", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("steelball", "metal", 10f, 7, null, false);
	}
	//
	private static void createNutsNBoltsCereal() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#adadad")+"Nuts N Bolts Cereal");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Metallic.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"12 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"8 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "nutsnboltscereal");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food24");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTNmMmE3NzY5NDYzMjljZDAzODM2ZWRiNTA0NDNiZTRiZWJiM2QwNzJhYTA3YWQ2NmE4NDMzNzM1NDQ3OTVlIn19fQ"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		NutsAndBoltsCereal=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("nutsnboltscereal"), item);
		sr.shape("   ",
				 "aaa",
				 " b ");
		sr.setIngredient('a', new RecipeChoice.ExactChoice(Arrays.asList(CharcoalBall, CoalBall, CopperBall, GoldBall, IronBall, SteelBall)));
		sr.setIngredient('b', Material.BOWL);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("nutsnboltscereal", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("nutsnboltscereal", "metal", 12f, 8, null, false);
	}
	//
	private static void createIronBread() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#adadad")+"Iron Bread");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Metallic.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"7.5 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"6 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "ironbread");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food25");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGE2MmJjYmVkY2UzZGVlMDFhMmZmOWFkZTIzOWYxNmI4YTFjYjUyYjdiZTdkYzllMDM5ZmViMWM2MjNhMDdmZiJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		IronBread=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("ironbread"), item);
		sr.shape(" a ",
				 " b ",
				 " a ");
		sr.setIngredient('a', Material.IRON_INGOT);
		sr.setIngredient('b', Material.BREAD);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("ironbread", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("ironbread", "metal", 7.5f, 6, null, false);
	}
	//
	private static void createCharNCoalSandwich() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#adadad")+"Char & Coal Sandwich");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.of("#ff8b8e")+"Food Type: Metallic.");
		lore.add(ChatColor.RED+""+ChatColor.BOLD+"Restores:");
		lore.add(ChatColor.of("#ff8b8e")+"15 Saturation");
		lore.add(ChatColor.of("#ff8b8e")+"8 Hunger");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "charncoalsandwich");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "food26");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWQwZGE1YmIyYTIzOTk2M2U4MWFmMDI5NWE1NzIwZTE5NzQ0ZThhMjIyMTA0ODZmMWNlZTI1NmZhMTdiNDc0NCJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CharNCoalSandwich=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("charncoalsandwich"), item);
		sr.shape(" a ",
				 "bcb",
				 " a ");
		sr.setIngredient('a', new RecipeChoice.ExactChoice(IronBread));
		sr.setIngredient('b', new RecipeChoice.ExactChoice(CharcoalBall));
		sr.setIngredient('c', new RecipeChoice.ExactChoice(CoalBall));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("charncoalsandwich", 1, new ArrayList(array));
		array.clear();
		FoodEvent.registerFood("charncoalsandwich", "metal", 15f, 8, null, false);
	}
	//
}
