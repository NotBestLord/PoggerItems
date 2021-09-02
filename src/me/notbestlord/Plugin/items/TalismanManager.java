package me.notbestlord.Plugin.items;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.craftingsystems.InfuserEvent;
import me.notbestlord.Plugin.craftingsystems.RecipeBookSelectorEvent;

public class TalismanManager {
	
	public static ItemStack SmallBattery;
	public static ItemStack MediumBattery;
	public static ItemStack LargeBattery;
	public static ItemStack XtraLargeBattery;
	public static ItemStack SUPERBattery;
	public static ItemStack GargantuanBattery;
	public static ItemStack SmallSolarPanel;
	public static ItemStack MediumSolarPanel;
	public static ItemStack LargeSolarPanel;
	public static ItemStack SmallSolarArray;
	
	public static void init() {
		createSmallBattery();
		createMediumBattery();
		createLargeBattery();
		createXtraLargeBattery();
		createSUPERBattery();
		createGargantuanBattery();
		createSmallSolarPanel();
		createMediumSolarPanel();
		createLargeSolarPanel();
		createSmallSolarArray();
	}
	
	private static void createSmallBattery() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Small Battery");
		List<String> lore = new ArrayList<>();
		lore.add("§7Can hold up to 1,000 Redstone Flux");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "smallbattery");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "smallbattery");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "battery1");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDgwZTJjMzNjNGM4MzI0NDUyYWNjZjkyMzU2NjllNjVmMDBkMTkyNmNjNTMzMTQ1MTkyNWNhMjZlNmFhNzIxIn19fQ==="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		SmallBattery=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("smallbattery"), item);
		sr.shape("yxy",
				 "yzy", 
				 "yxy");
		sr.setIngredient('x', Material.REDSTONE);
		sr.setIngredient('y', Material.COPPER_INGOT);
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.ModularCasing));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("smallbattery", 0, new ArrayList(array));
		array.clear();
	}
	
	private static void createMediumBattery() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Medium Battery");
		List<String> lore = new ArrayList<>();
		lore.add("§7Can hold up to 10,000 Redstone Flux");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "mediumbattery");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "mediumbattery");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "battery2");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjIxNjcxN2YxZGE5NGY5OWJlODI0MmE5MzFlMzliYmYzMjI1MWRlZGY0NmNkMjA3M2ZmYTg4OTY5ZDc0Zjk2MyJ9fX0===="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		MediumBattery=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("mediumbattery"), item);
		sr.shape("zyz",
				 "zxz", 
				 "zyz");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(SmallBattery));
		sr.setIngredient('y', Material.REDSTONE_BLOCK);
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.CondensedIronIngot));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("mediumbattery", 0, new ArrayList(array));
		array.clear();
	}
	private static void createLargeBattery() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Large Battery");
		List<String> lore = new ArrayList<>();
		lore.add("§7Can hold up to 100,000 Redstone Flux");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "largebattery");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "largebattery");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "battery3");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODUzYjRjYTdmZTlhOWUyOTlkMzc3ZWNlNGMwMzdlMWNkMDA5YTBiNTcyZDUzYzg0NjlmMGNlMGZmZTNiYThhMiJ9fX0====="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		LargeBattery=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("largebattery"), item);
		sr.shape("yzy",
				 "yxy", 
				 "yzy");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(MediumBattery));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.CondensedRedstone));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("largebattery", 0, new ArrayList(array));
		array.clear();
		
	}
	private static void createXtraLargeBattery() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Xtra Large Battery");
		List<String> lore = new ArrayList<>();
		lore.add("§7Can hold up to 1,000,000 Redstone Flux");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "xtralargebattery");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "xtralargebattery");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "battery4");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTlmOTE2ZGRkYTdkMmJjNTNkYTM2NDYxYzVmNDE4ODE2NDRhMzA0Y2NiMWFjZWU0NDk0ZDU1MWE1OTA2NjhjOCJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		XtraLargeBattery=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("xtralargebattery"), item);
		sr.shape("yzy",
				 "yxy", 
				 "yzy");
		sr.setIngredient('z', new RecipeChoice.ExactChoice(LargeBattery));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.Plastic));
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("xtralargebattery", 0, new ArrayList(array));
		array.clear();
		
	}
	private static void createSUPERBattery() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9SUPER Battery");
		List<String> lore = new ArrayList<>();
		lore.add("§7SUPER!");
		lore.add("§7Can hold up to 2,500,000 Redstone Flux");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "superbattery");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "superbattery");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "battery5");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2NjZjNhYThlOGE3YjRjMGVkYjlmMDIwNTQyZDBlYWEyMGY5MWFkOWRjZTg4MzEyNzE4ODNiMTZjMGM2YTE5MSJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		SUPERBattery=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("superbattery"), item);
		sr.shape("yzy",
				 "yxy", 
				 "yzy");
		sr.setIngredient('z', new RecipeChoice.ExactChoice(XtraLargeBattery));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.ThoriumIngot));
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("superbattery", 0, new ArrayList(array));
		array.clear();
		
	}
	private static void createGargantuanBattery() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Gargantuan Battery");
		List<String> lore = new ArrayList<>();
		lore.add("§7Can be infused with any type of battery");
		lore.add("§7for up to 10,000,000 Redstone Flux Storage.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "gargantuanbattery");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "batteries"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "battery6");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjRmMjFjZjVjMjM0ZmM5NmRiOTBhMGEzMTFkNmZiZTEyZjg3ODliN2ZhODE1NTcxNjc1N2ZkNTE2YjE4MTEifX19====="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		GargantuanBattery=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("gargantuanbattery"), item);
		sr.shape("yzy",
				 "yxy", 
				 "yzy");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.UltimateModularCasing));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.NeptuniumFluxConductor));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.Plastic));
		Bukkit.getServer().addRecipe(sr);
		InfuserEvent.initItem("gargantuanbattery");
		ArrayList array = new ArrayList<>();
		array.add(1);
		array.add("");
		array.add("batteries");
		array.add(1);
		array.add(null);
		array.add(null);
		InfuserEvent.addRecipe("gargantuanbattery", TalismanManager.LargeBattery, array);
		array.clear();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("gargantuanbattery", 0, new ArrayList(array));
		array.clear();
	}
	private static void createSmallSolarPanel() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Small Solar Panel");
		List<String> lore = new ArrayList<>();
		lore.add("§7produces 2 RF per second during the day");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "smallsolarpanel");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "solarpanel1");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzVkZDM3ZjcyOWZjODgxMzNlMzE0YTU1MjIwNGMwZmEyYzAxNjg0MjhiMzUzZjk1N2JmMTVmZjI0Yjc3MDdlMCJ9fX0======"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		SmallSolarPanel=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("smallsolarpanel"), item);
		sr.shape("xxx",
				 "yzy", 
				 "yyy");
		sr.setIngredient('x', Material.LAPIS_BLOCK);
		sr.setIngredient('y', Material.IRON_INGOT);
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.ModularCasing));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("smallsolarpanel", 0, new ArrayList(array));
		array.clear();
		
	}
	private static void createMediumSolarPanel() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Medium Solar Panel");
		List<String> lore = new ArrayList<>();
		lore.add("§7produces 50 RF per second during the day");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "mediumsolarpanel");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "solarpanel2");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTdmNGMwMDM1NmQxYWRkYjg1YjQ1YmE1MzUyOTkyZDNlY2MwYzlkMTFmZWI5MDQxNDgyZjg1MzFmZDI3ZDAxNCJ9fX0======="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		MediumSolarPanel=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("mediumsolarpanel"), item);
		sr.shape("xxx",
				 "yzy", 
				 "yyy");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.CondensedLapis));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.CondensedIronIngot));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(SmallSolarPanel));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("mediumsolarpanel", 0, new ArrayList(array));
		array.clear();
	}
	private static void createLargeSolarPanel() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Large Solar Panel");
		List<String> lore = new ArrayList<>();
		lore.add("§7produces 150 RF per second during the day");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "largesolarpanel");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "solarpanel3");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjVkZGY2YWYyZDYyNzFkOGZkZmFkYmRjNTRmYWFhZDVhNjhkN2I4YWMyMGUxNjM4ODNmYzM4ZDc2MzM2ZWE2In19fQ========="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		LargeSolarPanel=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("largesolarpanel"), item);
		sr.shape("xxx",
				 "yzy", 
				 "yyy");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.CondensedDiamond));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(MediumSolarPanel));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("largesolarpanel", 0, new ArrayList(array));
		array.clear();
	}
	private static void createSmallSolarArray() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Small Solar Array");
		List<String> lore = new ArrayList<>();
		lore.add("§7produces 250 RF per second during the day");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "smallsolararray");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "solarpanel4");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjI0YWQyNjIwOWZhMDJmNTU5ZWY2YWE4NjNlZTliYThmM2JlZjBhMDJmMWU5Y2ZmOGZkYzA5MTk2NDAyZmI2ZiJ9fX0=========="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		SmallSolarArray=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("smallsolararray"), item);
		sr.shape("xxx",
				 "zwz", 
				 "yyy");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.Plastic));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(LargeSolarPanel));
		sr.setIngredient('w', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("smallsolararray", 0, new ArrayList(array));
		array.clear();
	}
}
