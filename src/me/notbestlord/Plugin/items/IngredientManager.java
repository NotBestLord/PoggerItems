package me.notbestlord.Plugin.items;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.craftingsystems.EnchantedWorkBenchInventory;
import me.notbestlord.Plugin.craftingsystems.FissionReactorEvent;
import me.notbestlord.Plugin.craftingsystems.HydroPressEvent;
import me.notbestlord.Plugin.craftingsystems.RecipeBookSelectorEvent;
import me.notbestlord.Plugin.dataManagment.Recipe.EnchantedWorkbenchRecipe;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.world.item.ItemStack.HideFlags;

public class IngredientManager {
	
	
	public static ItemStack CondensedGlowstone;
	public static ItemStack CondensedDiamond;
	public static ItemStack ModularCasing;
	public static ItemStack AdvancedModularCasing;
	public static ItemStack CondensedIronIngot;
	public static ItemStack CondensedGoldIngot;
	public static ItemStack CondensedCopperIngot;
	public static ItemStack CarbonIngot;
	public static ItemStack CondensedEmerald;
	public static ItemStack CondensedRedstone;
	public static ItemStack CondensedLapis;
	public static ItemStack CondensedAmethyst;
	public static ItemStack Plastic;
	public static ItemStack Rubber;
	public static ItemStack MechanicalElytra;
	public static ItemStack EmptyFuelCell;
	public static ItemStack AmethystFuelCell;
	public static ItemStack UraniumIngot;
	public static ItemStack UraniumFuelCell;
	public static ItemStack ThoriumIngot;
	public static ItemStack SteelIngot;
	public static ItemStack NeptuniumIngot;
	public static ItemStack OxygenTank;
	public static ItemStack Ectoplasm;
	public static ItemStack ParticleAccelerator;
	public static ItemStack NeptuniumFluxConductor;
	public static ItemStack SteelRod;
	public static ItemStack EnderOrb;
	public static ItemStack PlasticCup;
	public static ItemStack UltimateModularCasing;
	public static ItemStack CosmicLeather;
	public static ItemStack UndeadHide;
	public static ItemStack LostSoul;
	public static ItemStack SoulOfTheSea;
	public static ItemStack DismemberedDemonHead;
	public static ItemStack DemonicFlesh;
	public static ItemStack PureEnder;
	public static ItemStack PerfectSteelIngot;
	public static ItemStack PerfectDiamond;
	public static ItemStack AncientClaws;
	public static ItemStack AncientFur;
	public static ItemStack HoglinSkin;
	public static ItemStack HoglinTusk;
	public static ItemStack PiglinTusk;
	public static ItemStack RavagerSkin;
	public static ItemStack ScavangedSkeletalRemains;
	public static ItemStack ScavangedSkeletalRemainsCluster;
	public static ItemStack RefinedSkeletalRemains;
	public static ItemStack WaterBucket;
	
	public static void init() {
		createCondensedGlowstone();
		createCondensedDiamond();
		createCondensedIronIngot();
		createCondensedGoldIngot();
		createCondensedCopperIngot();
		createCondensedEmerald();
		createCondensedRedstone();
		createCondensedLapis();
		createPlastic();
		createRubber();
		createCarbonIngot();
		createSteelIngot();
		createModularCasing();
		createAdvancedModularCasing();
		createMechanicalElytra();
		createCondensedAmethyst();
		createUraniumIngot();
		createEmptyFuelCell();
		createAmethystFuelCell();
		createThoriumIngot();
		createUraniumFuelCell();
		createUraniumAmethystFuelCell();
		createNeptuniumIngot();
		createThoriumFuelCell();
		createOxygenTank();
		createEctoplasm();
		createParticleAccelerator();
		createNeptuniumFluxConductor();
		createSteelRod();
		createEnderOrb();
		createPlasticCup();
		createUltimateModularCasing();
		createCosmicLeather();
		createBossDrops();
		createWaterBucket();
	}
	
	private static void createCondensedDiamond() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Condensed Diamond");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "condenseddiamond");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "condenseddiamond");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTY0ZjI1Y2ZmZjc1NGYyODdhOTgzOGQ4ZWZlMDM5OTgwNzNjMjJkZjdhOWQzMDI1YzQyNWUzZWQ3ZmY1MmMyMCJ9fX0===="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CondensedDiamond=item;
		HydroPressEvent.addRecipe(new ItemStack(Material.DIAMOND_BLOCK), 2, item);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("HydraulicPress");
		array.add(new ItemStack(Material.DIAMOND_BLOCK,2));
		RecipeBookSelectorEvent.addRecipe("condenseddiamond", 1, new ArrayList(array));
		array.clear();
	}
	private static void createCondensedGlowstone() {
		ItemStack item = new ItemStack(Material.GLOWSTONE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§2Condensed Glowstone");
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "condensedglowstone");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "condensedglowstone");
		item.setItemMeta(meta);
		CondensedGlowstone=item;
		HydroPressEvent.addRecipe(new ItemStack(Material.GLOWSTONE), 16, item);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("HydraulicPress");
		array.add(new ItemStack(Material.GLOWSTONE, 16));
		RecipeBookSelectorEvent.addRecipe("condensedglowstone", 1, new ArrayList(array));
		array.clear();
	}
	private static void createModularCasing() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Modular Casing");
		List<String> lore = new ArrayList<>();
		lore.add("§7Machine Casing forged from Iron.");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "modularcasing");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "casing1");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGYxNGYzMTc5Yjg2ZjY5YjNlZmE3NDcyZGFjYWViMjMzOWY2MjkwZDJkODE3MzYyNzkzMzQ4YWJkOThlMDIxIn19fQ==="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		ModularCasing=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("modularcasing"), item);
		sr.shape("xyx",
				 "y y",
				 "xyx");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(CondensedIronIngot));
		sr.setIngredient('y', Material.GLASS);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("modularcasing", 1, new ArrayList(array));
		array.clear();
	}
	private static void createAdvancedModularCasing() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Advanced Modular Casing");
		List<String> lore = new ArrayList<>();
		lore.add("§7Modular casing upgraded using steel");
		lore.add("§7and condensed gold.");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "advancedmodularcasing");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "casing2");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQ5ZGJmYWI4NmFmMzk5Y2FiOGM4NTMyMDM5NmRhY2Y4NTY5ZTkyZjg5NTc0YjYzNjI2Nzg3YTM0NGMxN2U0ZiJ9fX0=="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		AdvancedModularCasing=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("advancedmodularcasing"), item);
		sr.shape("z z",
				 "xyx",
				 "z z");
		;
		sr.setIngredient('x', new RecipeChoice.ExactChoice(CondensedGoldIngot));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(ModularCasing));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(SteelIngot));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("advancedmodularcasing", 1, new ArrayList(array));
		array.clear();
	}
	private static void createCondensedIronIngot() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Condensed Iron Ingot");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "condensedironingot");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "condensedironingot");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTAyZjRhOWQxODJjYzYyNTkyNWU1ZGVhN2UyOWQxM2E5NDhhZDY0ZmY0Mjg3MTc4YWYyM2EwNDI4ZTFmYTU0ZSJ9fX0=="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CondensedIronIngot=item;
		HydroPressEvent.addRecipe(new ItemStack(Material.IRON_INGOT), 4, item);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("HydraulicPress");
		array.add(new ItemStack(Material.IRON_INGOT, 4));
		RecipeBookSelectorEvent.addRecipe("condensedironingot", 1, new ArrayList(array));
		array.clear();
	}
	private static void createCondensedGoldIngot() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Condensed Gold Ingot");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "condensedgoldingot");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "condensedgoldingot");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTExNWYyMTk0ZmZlMjJlMTJiMjA0N2QwOTI4OGQ4ZWM3Mjk0ZDRmOGM3ODRiNGNjNzViMThmZjQ1MjUxNjk1MSJ9fX0==="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CondensedGoldIngot=item;
		HydroPressEvent.addRecipe(new ItemStack(Material.GOLD_INGOT), 8, item);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("HydraulicPress");
		array.add(new ItemStack(Material.GOLD_INGOT, 8));
		RecipeBookSelectorEvent.addRecipe("condensedgoldingot", 1, new ArrayList(array));
		array.clear();
	}
	private static void createCondensedCopperIngot() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Condensed Copper Ingot");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "condensedcopperingot");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "condensedcopperingot");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGI4NTJlZTkwYjgzZDkyOTM5NjBmYTY0NGMyNTZkNjU1ODJjYjk5YWFlYjU5MzAzZjdlYzM1NWFiZTNmZjQwNiJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CondensedCopperIngot=item;
		HydroPressEvent.addRecipe(new ItemStack(Material.COPPER_INGOT), 12, item);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("HydraulicPress");
		array.add(new ItemStack(Material.COPPER_INGOT, 12));
		RecipeBookSelectorEvent.addRecipe("condensedcopperingot", 1, new ArrayList(array));
		array.clear();
	}
	private static void createCarbonIngot() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Carbon Ingot");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "carboningot");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "carbon");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTlmMjMzMGFkMjg0NmVjNmM4ZTFjZTAyNTg5ZjA3ZjJmNzU5N2I1YTk3NzU5OWI2ZGI3YzM0NGNhNDRhMTI3NiJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CarbonIngot=item;
		HydroPressEvent.addRecipe(new ItemStack(Material.COAL), 16, item);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("HydraulicPress");
		array.add(new ItemStack(Material.COAL, 16));
		RecipeBookSelectorEvent.addRecipe("carboningot", 1, new ArrayList(array));
		array.clear();
	}
	private static void createCondensedEmerald() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Condensed Emerald");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "condensedemerald");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "condensedemerald");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODkyNmMxZjJjM2MxNGQwODZjNDBjZmMyMzVmZTkzODY5NGY0YTUxMDY3YWRhNDcyNmI0ODZlYTFjODdiMDNlMiJ9fX0="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CondensedEmerald=item;
		HydroPressEvent.addRecipe(new ItemStack(Material.EMERALD), 6, item);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("HydraulicPress");
		array.add(new ItemStack(Material.EMERALD, 6));
		RecipeBookSelectorEvent.addRecipe("condensedemerald", 1, new ArrayList(array));
		array.clear();
	}
	private static void createCondensedRedstone() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Condensed Redstone");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "condensedredstone");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "condensedredstone");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmFiZWMyNDBhNGNiNTFjYWRjMTg1YTY2MDcyYTJhZWQ3ZjU2MTEyNjk2ZjI0NjU3M2E1MmVlN2RmNGMzMiJ9fX0="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CondensedRedstone=item;
		HydroPressEvent.addRecipe(new ItemStack(Material.REDSTONE_BLOCK), 4, item);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("HydraulicPress");
		array.add(new ItemStack(Material.REDSTONE_BLOCK, 4));
		RecipeBookSelectorEvent.addRecipe("condensedredstone", 1, new ArrayList(array));
		array.clear();
	}
	private static void createCondensedLapis() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Condensed Lapis");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "condensedlapis");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "condensedlapis");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWJhMzM4MzM0ZWM0YzA0YTMwNGEwODNhMGEwNTY5NWQ1MDM4ZWVmNmE1ZmFkZjBmZGQ2YjZlMWQ5YzM0MDIzNSJ9fX0====="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CondensedLapis=item;
		HydroPressEvent.addRecipe(new ItemStack(Material.LAPIS_BLOCK), 2, item);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("HydraulicPress");
		array.add(new ItemStack(Material.LAPIS_BLOCK, 2));
		RecipeBookSelectorEvent.addRecipe("condensedlapis", 1, new ArrayList(array));
		array.clear();
	}
	private static void createMechanicalElytra() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Mechanical Elytra");
		List<String> lore = new ArrayList<>();
		lore.add("§7An elytra coated in metal.");
		lore.add("§7Due to its high weight it can't be");
		lore.add("§7used to fly or even glide by it's own.");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "mechanicalelytra");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "exosuitinfuse1");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDQzMDJiODcyNTc1MWMxOGFhMWVlYThhYzY1MDQ1MjExZWU5MDhiN2FjYzEyMjllOTJlNDBjZmZlODhlNDFiIn19fQ====="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		MechanicalElytra=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("mechanicalelytra"), item);
		sr.shape("zyz",
				 "zxz",
				 "z z");
		sr.setIngredient('x', Material.ELYTRA);
		sr.setIngredient('y', new RecipeChoice.ExactChoice(AdvancedModularCasing));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(SteelIngot));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("mechanicalelytra", 1, new ArrayList(array));
		array.clear();
	}
	private static void createCondensedAmethyst() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Condensed Amethyst");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "condensedamethyst");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "condensedamethyst");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGVhZGE4NWMxN2FlNTg2MzdkYWUyYjg2ZjNmNjk2NmE3NzM1MmFiZjIxNGJjNDQ5M2U2NWNmNjcxYzI4YTIwNCJ9fX0======"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CondensedAmethyst=item;
		HydroPressEvent.addRecipe(new ItemStack(Material.AMETHYST_SHARD), 2, item);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("HydraulicPress");
		array.add(new ItemStack(Material.AMETHYST_SHARD, 2));
		RecipeBookSelectorEvent.addRecipe("condensedamethyst", 1, new ArrayList(array));
		array.clear();
	}
	private static void createPlastic() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Plastic");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "plastic");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "oilplastic");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWQyOTMwYzhiMGU2M2QxY2E2YTBhMDU4YTgzNjdmNGY2MDg4MTlmOTY4YmJhYWI0ZjAzY2YwMTNlNjJmZmMwMSJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Plastic=item;
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("OilProcessor");
		ItemStack item2 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.GRAY+"500mb of Crude oil");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "crudeoil");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidoil");
		item2.setItemMeta(meta2);
		array.add(new ItemStack(item2));
		RecipeBookSelectorEvent.addRecipe("plastic", 1, new ArrayList(array));
		array.clear();
		//
		meta2.setDisplayName(ChatColor.GRAY+"Crude oil");
		item2.setItemMeta(meta2);
		array.add(new ItemStack(item2));
		array.add("OilExtractor");
		item2 = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
		meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.WHITE+"Produced at a rate of 10mb");
		lore.add(ChatColor.WHITE+"per second in the multiblock.");
		meta2.setLore(lore);
		lore.clear();
		item2.setItemMeta(meta2);
		array.add(item2);
		RecipeBookSelectorEvent.addRecipe("crudeoil", 1, new ArrayList(array));
		array.clear();
	}
	private static void createRubber() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Rubber");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "rubber");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "oilrubber");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjg0OWM4NTYzYjQ5YzVjZDY1MDY2Yjg2N2U5OWVkYjI4NWRhOTVmMGZkMjEwZTIzZmEzNWI1YzVjNTg5MjEwNyJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Rubber=item;
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("OilProcessor");
		ItemStack item2 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.GRAY+"250mb of Crude oil");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "crudeoil");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidoil");
		item2.setItemMeta(meta2);
		array.add(item2);
		RecipeBookSelectorEvent.addRecipe("rubber", 1, new ArrayList(array));
		array.clear();
	}
	private static void createEmptyFuelCell() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Empty Fuel Cell");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "emptyfuelcell");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "fuelcellempty");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjkxYjdiMjE3MjVmMTQ2ZDI5YzE5MmI3NDVkNzlkMjI2MDMyNjdjN2FkODkzYmFkZWI2NTQ2ZTc0NjYwMDA2MCJ9fX0========"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		EmptyFuelCell=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("emptyfuelcell"), item);
		sr.shape("xzx",
				 "y y",
				 "xzx");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(SteelIngot));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(Plastic));
		sr.setIngredient('y', Material.GLASS);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("emptyfuelcell", 1, new ArrayList(array));
		array.clear();
	}
	private static void createAmethystFuelCell() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Amethyst Fuel Cell");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY + "A very basic fuel cell");
		lore.add(ChatColor.GRAY + "which depletes after 1 minute");
		lore.add(ChatColor.GRAY + "producing 50 RF per second and");
		lore.add(ChatColor.GRAY + "2 ingot of uranium as a byproduct.");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "amethystfuelcell");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "fuelcellamethyst");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODE3MTFmMTM5ZDNiY2E1NzdlYTdjZmMxYjhkODQ1OTRkYjg3ZGQwNjZkNmYwMGY3M2E5YWUzM2JjOWMyYWVkMSJ9fX0========"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		AmethystFuelCell=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("amethystfuelcell"), item);
		sr.shape(" y ",
				 "yxy",
				 " y ");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(EmptyFuelCell));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(CondensedAmethyst));
		Bukkit.getServer().addRecipe(sr);
		FissionReactorEvent.addFuel("amethystfuelcell", UraniumIngot, 60, 2, 50);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("amethystfuelcell", 1, new ArrayList(array));
		array.clear();
		ItemStack temp = new ItemStack(UraniumIngot);
		temp.setAmount(2);
		array.add(temp);
		array.add("FissionReactor");
		array.add(item);
		array.add(EmptyFuelCell);
		RecipeBookSelectorEvent.addRecipe("uraniumingot", 1, new ArrayList(array));
		array.clear();
	}
	private static void createUraniumIngot() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Uranium Ingot");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "uraniumingot");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "radioactiveingoturanium");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTAyZjNkNTVhMjI0NWZkODMyOTJlZGU3MTA2NmFhOTYwMDJiNWU4N2VkZGU0ZTkzYjFhOTYwYTJkODE1ZTA0ZCJ9fX0========="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		UraniumIngot=item;
	}
	private static void createUraniumFuelCell() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Uranium Fuel Cell");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY + "A more advanced fuel cell");
		lore.add(ChatColor.GRAY + "which depletes after 10 minutes");
		lore.add(ChatColor.GRAY + "producing 200 RF per second and");
		lore.add(ChatColor.GRAY + "1 ingot of thorium as a byproduct.");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "uraniumfuelcell");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "fuelcelluranium");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjExNzA5NjRjZTBkYThmZWNiZTlhY2M5NWY0OTFkZjIxMjc3YmU3N2ZlMWE0ZWYwODE4ODdkMTZmNjNmNzlkZCJ9fX0========="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		UraniumFuelCell=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("uraniumfuelcell"), item);
		sr.shape(" y ",
				 "yxy",
				 " y ");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(EmptyFuelCell));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(UraniumIngot));
		Bukkit.getServer().addRecipe(sr);
		FissionReactorEvent.addFuel("uraniumfuelcell", ThoriumIngot, 600, 1, 200);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("uraniumfuelcell", 1, new ArrayList(array));
		array.clear();
		array.add(ThoriumIngot);
		array.add("FissionReactor");
		array.add(item);
		array.add(EmptyFuelCell);
		RecipeBookSelectorEvent.addRecipe("thoriumingot", 1, new ArrayList(array));
		array.clear();
	}
	private static void createThoriumIngot() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Thorium Ingot");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "thoriumingot");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "radioactiveingotthorium");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzBlMWRhNTg2OTU0MjFkNDU1OGI3NzIyYmQ5OWU4ZjU3MGFmNDcyYWIxMDdmNTQxOTM4ZGI1MDRkNjQ3MmQwYiJ9fX0=========="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		ThoriumIngot=item;
	}
	private static void createSteelIngot() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Steel Ingot");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "steelingot");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "ingotsteel");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE2ZTUwM2UyODE0ODI3OGQ3NDA2ZWI5YzUyZGMwMDQ4NDYwYTcyYzVkMDlkNzQwNjA0NGExYmY5NGJjNDRhNCJ9fX0==========="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		SteelIngot=item;
	}
	private static void createUraniumAmethystFuelCell() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Uranium-Amethyst Fuel Cell");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY + "A much faster burning fuel");
		lore.add(ChatColor.GRAY + "which depletes after 5 minutes");
		lore.add(ChatColor.GRAY + "producing 20 RF per second and");
		lore.add(ChatColor.GRAY + "1 ingot of thorium as a byproduct.");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "uraniumamethystfuelcell");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "fuelcelluraniumamethyst");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjExNzA5NjRjZTBkYThmZWNiZTlhY2M5NWY0OTFkZjIxMjc3YmU3N2ZlMWE0ZWYwODE4ODdkMTZmNjNmNzlkZCJ9fX0========="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		UraniumFuelCell=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("uraniumamethystfuelcell"), item);
		sr.shape(" z ",
				 "yxy",
				 " z ");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(EmptyFuelCell));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(UraniumIngot));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(CondensedAmethyst));
		Bukkit.getServer().addRecipe(sr);
		FissionReactorEvent.addFuel("uraniumamethystfuelcell", ThoriumIngot, 300, 1, 20);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("uraniumamethystfuelcell", 1, new ArrayList(array));
		array.clear();
	}
	private static void createNeptuniumIngot() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Neptunium Ingot");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "neptuniumingot");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "radioactiveingotneptunium");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDA2NDM2Y2NjYjQ1M2UyZGEzOWZkNjkwYmY0ZDAyODc0NjVjYWIwOWUwMzQyMzM3MzliMjUwNmE2MzhiNTA2YiJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		NeptuniumIngot=item;
	}
	private static void createThoriumFuelCell() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Thorium Fuel Cell");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY + "A highly advanced fuel cell");
		lore.add(ChatColor.GRAY + "which depletes after 30 minutes");
		lore.add(ChatColor.GRAY + "producing 300 RF per second and");
		lore.add(ChatColor.GRAY + "1 ingot of Neptunium as a byproduct.");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "thoriumfuelcell");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "fuelcellthorium");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzYyYjRmNTdkNDZiOGI3NTVhNzAzNTQwYmRkNTA0MDAzZmQ1M2Q0NDljNWVjNGY0NTBmYWE5NmFlMWIzZTdmZiJ9fX0=========="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		UraniumFuelCell=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("thoriumfuelcell"), item);
		sr.shape(" y ",
				 "yxy",
				 " y ");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(EmptyFuelCell));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(ThoriumIngot));
		Bukkit.getServer().addRecipe(sr);
		FissionReactorEvent.addFuel("thoriumfuelcell", NeptuniumIngot, 1800, 1, 300);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("thoriumfuelcell", 1, new ArrayList(array));
		array.clear();
		array.add(NeptuniumIngot);
		array.add("FissionReactor");
		array.add(item);
		array.add(EmptyFuelCell);
		RecipeBookSelectorEvent.addRecipe("neptuniumingot", 1, new ArrayList(array));
		array.clear();
	}
	private static void createOxygenTank() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Oxygen Tank");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "oxygentank");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "exosuitinfuse2");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTc1YjU4NmQyOWFhNGY2MzAwNjcwN2ZiZTk0YTMzYzg5YzIyNWY0OWIzYjQ0Y2IxODE3Mzg2NTZjOTQyZTI3NyJ9fX0========="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		OxygenTank=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("oxygentank"), item);
		sr.shape("xzx",
				 "xzx",
				 "xzx");
		sr.setIngredient('z', new RecipeChoice.ExactChoice(EmptyFuelCell));
		sr.setIngredient('x', Material.GLASS);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("oxygentank", 1, new ArrayList(array));
		array.clear();
	}
	private static void createEctoplasm() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Ectoplasm");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "ectoplasm");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "mobdrop1");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjhkMjE4MzY0MDIxOGFiMzMwYWM1NmQyYWFiN2UyOWE5NzkwYTU0NWY2OTE2MTllMzg1NzhlYTRhNjlhZTBiNiJ9fX0=========="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Ectoplasm=item;
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("MobDrop");
		ItemStack in = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta inMeta = (SkullMeta) in.getItemMeta();
		inMeta.setDisplayName(ChatColor.GOLD+"Mob drop from ANY undead mob.");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTZmYzg1NGJiODRjZjRiNzY5NzI5Nzk3M2UwMmI3OWJjMTA2OTg0NjBiNTFhNjM5YzYwZTVlNDE3NzM0ZTExIn19fQ============"));
		try {
			profileField = inMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(inMeta, profile);
		}catch (Exception e) {
			
		}
		in.setItemMeta(inMeta);
		array.add(in);
		RecipeBookSelectorEvent.addRecipe("ectoplasm", 1, new ArrayList(array));
		array.clear();
	}
	private static void createParticleAccelerator() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Particle Accelerator");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "particleaccelerator");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "exosuitinfuse3");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTM5YWMyNTdmNGUwNGFkYzZhYjNlNDZmZDNiMjg3NDllODY2ZmFhOWNlNmExZDZkNTI2ZTBlMzg2NGQ5MjIyIn19fQ==========="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		ParticleAccelerator=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("particleaccelerator"), item);
		sr.shape("xyx",
				 "xzx",
				 "xyx");
		sr.setIngredient('z', new RecipeChoice.ExactChoice(AdvancedModularCasing));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(ThoriumIngot));
		sr.setIngredient('x', new RecipeChoice.ExactChoice(SteelIngot));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("particleaccelerator", 1, new ArrayList(array));
		array.clear();
	}
	private static void createNeptuniumFluxConductor() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Neptunium Flux Conductor");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "neptuniumfluxconductor");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "casing3");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTE2YjY4Mzc2YzE4MWM5YTczNDNmZWUzNjk3ZmFhY2VjMzUxMjlmYjY0ZGU1OTE0YmRiZjg2OWM2NTJjIn19fQ"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		NeptuniumFluxConductor=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("neptuniumfluxconductor"), item);
		sr.shape("xyx",
				 "xzx",
				 "xyx");
		sr.setIngredient('z', new RecipeChoice.ExactChoice(NeptuniumIngot));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(CondensedRedstone));
		sr.setIngredient('x', new RecipeChoice.ExactChoice(SteelIngot));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("neptuniumfluxconductor", 1, new ArrayList(array));
		array.clear();
	}
	private static void createSteelRod() {
		ItemStack item = new ItemStack(Material.STICK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§2Steel Rod");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "steelrod");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "miscsteelrod");
		meta.addEnchant(Enchantment.DEPTH_STRIDER, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		SteelRod=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("steelrod"), item);
		sr.shape("xyx",
				 "xyx",
				 "xyx");
		sr.setIngredient('y', new RecipeChoice.ExactChoice(SteelIngot));
		sr.setIngredient('x', new RecipeChoice.ExactChoice(CondensedRedstone));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("steelrod", 1, new ArrayList(array));
		array.clear();
	}
	private static void createEnderOrb() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Ender Orb");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "enderorb");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "mobdrop2");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTZhNzY2MzFjMjNlZTJhNzAwZjMzZGNmYTY1Yzc0ZGQzMGQwZjE1N2YzNTE1ZGIyMjU3Y2FkNjhlMmRjN2MzYSJ9fX0==========="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		EnderOrb=item;
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("MobDrop");
		ItemStack in = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta inMeta = (SkullMeta) in.getItemMeta();
		inMeta.setDisplayName(ChatColor.GOLD+"Mob drop from Enderman.");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTZjMGIzNmQ1M2ZmZjY5YTQ5YzdkNmYzOTMyZjJiMGZlOTQ4ZTAzMjIyNmQ1ZTgwNDVlYzU4NDA4YTM2ZTk1MSJ9fX0============="));
		try {
			profileField = inMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(inMeta, profile);
		}catch (Exception e) {
			
		}
		in.setItemMeta(inMeta);
		array.add(in);
		RecipeBookSelectorEvent.addRecipe("enderorb", 1, new ArrayList(array));
		array.clear();
	}
	private static void createPlasticCup() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Plastic Cup");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "plasticcup");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "oilplasticcup");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJiMDkyOGRkNzAzZDA2YmFiNzlmYmEzYmEzMzExMDhlMzBiOWM5ZTZmZjc3YzNlN2M0YTk3YTg1ZmIzMTVlYiJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		PlasticCup=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("plasticcup"), item);
		sr.shape("y y",
				 "y y",
				 "yyy");
		sr.setIngredient('y', new RecipeChoice.ExactChoice(Plastic));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("plasticcup", 1, new ArrayList(array));
		array.clear();
	}
	private static void createUltimateModularCasing() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Ultimate Modular Casing");
		List<String> lore = new ArrayList<>();
		lore.add("§7Modular casing taken to the next level.");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "ultimatemodularcasing");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "casing4");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE3YjNiMTZlNWQwYzRjZmQyMWM0ZWI5MTMzZTk2OWFhZDdjYzczMDNjY2RmMzE3NTEyZTI2YTQ4NzliNTEifX19"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		UltimateModularCasing=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("ultimatemodularcasing"), item);
		sr.shape("zyz",
				 "xwx",
				 "zyz");
		;
		sr.setIngredient('x', Material.NETHERITE_INGOT);
		sr.setIngredient('y', new RecipeChoice.ExactChoice(AdvancedModularCasing));
		sr.setIngredient('w', new RecipeChoice.ExactChoice(NeptuniumFluxConductor));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(Plastic));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("ultimatemodularcasing", 1, new ArrayList(array));
		array.clear();
	}
	private static void createWaterBucket() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§2Water");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "waterbucket");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "waterbucket");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjQ2NDYzMWU1NGNkZTcyZGRhNTU5ZGFmYjEzODlhYTNkYzJlMmMzOGI3OGM2NGQxZDUxZGMyMDcyNmEwYTg3NyJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		WaterBucket=item;
		item.setAmount(4);
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("waterbucket"), item);
		sr.shape("   ",
				 " x ",
				 "   ");
		sr.setIngredient('x', Material.WATER_BUCKET);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("waterbucket", 1, new ArrayList(array));
		array.clear();
	}
	private static void createCosmicLeather() {
		ItemStack item = new ItemStack(Material.LEATHER, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#b71ba6")+"Cosmic Leather");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "cosmicleather");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialcosmicleather");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		CosmicLeather=item;
		EnchantedWorkbenchRecipe recipe = new EnchantedWorkbenchRecipe("cosmicleather", item);
		recipe.addIngredient(Material.LEATHER, 32);
		recipe.addIngredient(AncientFur, 12);
		recipe.addIngredient(RavagerSkin, 64);
		recipe.addIngredient(HoglinSkin, 32);
		recipe.addIngredient(UndeadHide, 64);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
	}
	private static void createBossDrops() {
		ItemStack item = new ItemStack(Material.RABBIT_HIDE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#e1c964")+"Undead Hide");
		List<String> lore = new ArrayList<>();
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "undeadhide");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialundeadhide");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		UndeadHide=item;
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#e1c964")+"Lost Soul");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "lostsoul");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materiallostsoul");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTk2YTRlMjg1ZGM3ZGExMWRhY2I4N2Y3NDU5NTFhNzljOWZlMjc2NjEzNTFlOWM2ZjY3NDUyZjRhNTFlNzQ5NSJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		LostSoul=item;
		//
		/*
		public static ItemStack PerfectSteelIngot;
		public static ItemStack PerfectDiamond;
		 */
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#0032b6")+"Soul Of The Sea");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "soulofthesea");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialsoulofthesea");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTExM2VlNjEwODQxZGVkMjE1YWNkMmI0Y2FhZWVmODdkZmQ2ZTNkNDc2OGU3YWI0ZTE5ZWI3NmIzZDgxMjFjZiJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		SoulOfTheSea=item;
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#d50a00")+"Dismembered Demon Head");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "dismembereddemonhead");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialdismembereddemonhead");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzJiNzIwZDFkODUzNjRhZThhNDZlZGFhNzQ5YTE1NGUyN2VhZWQ3YjE4N2U0MmMwZmU1ZGIwNmI3MGVlY2U5YiJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		DismemberedDemonHead=item;
		//
		item = new ItemStack(Material.ROTTEN_FLESH, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#b52f4c")+"Demonic Flesh");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "demonicflesh");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialdemonicflesh");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		DemonicFlesh=item;
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#ff23b0")+"Pure Ender");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "pureender");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialpureender");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzNhOGU0MDJkYWQxYjdkYWQ5YWFlNmY0MDE1OTMyMTgzNDI5Y2U4N2JiYmVjZWQzMTE5MDI2ZjgyOTYzMzZjMiJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		PureEnder=item;
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#9aa39c")+"Perfect Steel Ingot");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "perfectsteelingot");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialperfectsteelingot");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE2ZTUwM2UyODE0ODI3OGQ3NDA2ZWI5YzUyZGMwMDQ4NDYwYTcyYzVkMDlkNzQwNjA0NGExYmY5NGJjNDRhNCJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		PerfectSteelIngot=item;
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#5bc2ed")+"Perfect Diamond");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "perfectdiamond");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialperfectdiamond");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2E0MzgxYTVhYjRhY2M2ZGU4ZDRlZWU3NjllYmE2YmFkYjJmMmI0ZTkzNDJhY2FlMjc1MDI5NWU2MDYwNGE1NyJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		PerfectDiamond=item;
		//
		item = new ItemStack(Material.BONE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#e1e1e1")+"Ancient Claw");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "ancientclaw");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialancientclaw");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		AncientClaws=item;
		//
		item = new ItemStack(Material.GRAY_WOOL, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#b4cccc")+"Ancient Fur");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "ancientfur");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialancientfur");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		AncientFur=item;
		//
		item = new ItemStack(Material.LEATHER, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#e1976d")+"Hoglin Skin");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "hoglinskin");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialhoglinskin");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		HoglinSkin=item;
		//
		item = new ItemStack(Material.BONE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#f7f0a3")+"Hoglin Tusk");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "hoglintusk");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialhoglintusk");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		HoglinTusk=item;
		//
		item = new ItemStack(Material.BONE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#f7f0a3")+"Piglin Tusk");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "piglintusk");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialpiglintusk");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		PiglinTusk=item;
		//
		item = new ItemStack(Material.LEATHER, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#78a3ce")+"Ravager Skin");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "ravagerskin");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialravagerskin");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		RavagerSkin=item;
		//
		item = new ItemStack(Material.BONE_MEAL, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#eaeaea")+"Scavanged Skeletal Remains");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "scavangedskeletalremains");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialscavangedskeletalremains");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		ScavangedSkeletalRemains=item;
		//
		item = new ItemStack(Material.BONE_BLOCK, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#eaeaf0")+"Scavanged Skeletal Remains Cluster");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "scavangedskeletalremainscluster");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialscavangedskeletalremainscluster");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("scavangedskeletalremainscluster"), item);
		sr.shape("bbb",
				 "bbb", 
				 "bbb");
		sr.setIngredient('b', new RecipeChoice.ExactChoice(ScavangedSkeletalRemains));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("scavangedskeletalremainscluster", 1, new ArrayList(array));
		array.clear();
		ScavangedSkeletalRemainsCluster=item;
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#eaeaf5")+"Refined Skeletal Remains");
		lore.add("§8Crafting Ingredient");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "refinedskeletalremains");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "materialrefinedskeletalremains");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjhhNzljNTEyY2ZmMWE5ZjQ3MzE5ZmQ3N2VmZDA2ZjY3M2E3NzJhYTk3NTk3NDM2YjAxNjBjNDA5YzhhMmU2MCJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		RefinedSkeletalRemains=item;
		EnchantedWorkbenchRecipe recipe = new EnchantedWorkbenchRecipe("refinedskeletalremains", item);
		recipe.addIngredient(ScavangedSkeletalRemainsCluster, 4);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
	}
}
