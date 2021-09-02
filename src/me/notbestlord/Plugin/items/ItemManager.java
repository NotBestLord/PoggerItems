package me.notbestlord.Plugin.items;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.persistence.PersistentDataType;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.craftingsystems.EnchantedWorkBenchInventory;
import me.notbestlord.Plugin.craftingsystems.InfuserEvent;
import me.notbestlord.Plugin.craftingsystems.RecipeBookSelectorEvent;
import me.notbestlord.Plugin.dataManagment.Recipe.EnchantedWorkbenchRecipe;
import me.notbestlord.Plugin.enchanting.EnchantingTableEvent;
import net.md_5.bungee.api.ChatColor;

public class ItemManager {
	
	public static ItemStack CheatBrick;
	public static ItemStack DebugStick;
	public static ItemStack SpyBow;
	public static ItemStack ReturnScroll;
	public static ItemStack Warp;
	public static ItemStack PortableWorkbench;
	public static ItemStack PegasusSpawnEgg;
	public static ItemStack Portablesmeltery;
	public static ItemStack ShortBow;
	public static ItemStack HydraulicPress;
	public static ItemStack PortableEnderChest;
	public static ItemStack CoalGenerator;
	public static ItemStack MiniatureEnchantingTable;
	public static ItemStack[] ExoSuit = new ItemStack[4];
	public static ItemStack Infuser;
	public static ItemStack FissionReactor;
	public static ItemStack CopperTank;
	public static ItemStack IronTank;
	public static ItemStack SteelTank;
	public static ItemStack IronMultiTool;
	public static ItemStack SteelMultiTool;	
	public static ItemStack DiamondMultiTool;
	public static ItemStack MultiBlockHammer;
	public static ItemStack RadiatedMultiBlockHammer;
	public static ItemStack SpeedUpgrade;
	public static ItemStack EnergyUpgrade;
	public static ItemStack[] VoidOreMinerUpgrades = new ItemStack[4];
	public static ItemStack[] VoidOreMinerLenses = new ItemStack[10];
	public static ItemStack[] Drone = new ItemStack[5];
	public static ItemStack StopWatch;
	public static ItemStack Box;
	public static ItemStack[] CopperArmor = new ItemStack[4];
	public static ItemStack[] SteelArmor = new ItemStack[4];
	public static ItemStack[] BlazingArmor = new ItemStack[4];
	public static ItemStack[] WitheringArmor = new ItemStack[4];
	public static ItemStack[] BossSummoningItems = new ItemStack[12];
	public static ItemStack ReinforcedTrident;
	public static ItemStack[] GiantArmor = new ItemStack[4];
	public static ItemStack WornOutGiantGloves;
	public static ItemStack GiantGloves;
	public static ItemStack GiantGlovesS;
	public static ItemStack SatanBlade;
	public static ItemStack HellKatana;
	public static ItemStack UndeadRobe;
	public static ItemStack BasicRobe;
	public static ItemStack[] MutatedRobes = new ItemStack[8];
	public static ItemStack ChillyStaff;
	public static ItemStack BruteAxe;
	public static ItemStack RavagerHead;
	public static ItemStack RainBow;
	public static ItemStack RainbowBow;
	public static ItemStack IndustrialSawCatalyst;
	public static ItemStack EnchantedWorkbenchCatalyst;
	public static ItemStack WeaponryCatalyst;
	public static ItemStack AutomaticGardenCatalyst;
	public static ItemStack Pistol;
	public static ItemStack Uzi;
	public static ItemStack Revolver;
	public static ItemStack BarrettM82;
	public static ItemStack[] GunAmmo = new ItemStack[4];
	public static ItemStack[] SniperSet = new ItemStack[4];
	public static ItemStack SexPistols;
	
	public static void init() {
		createCheatBrick();
		createDebugStick();
		createSpyBow();
		createReturnScroll();
		createWarp();
		createPortableWorkbench();
		createPegasusSpawnEgg();
		createPortablesmeltery();
		createShortBow();
		createHydraulicPress();
		createPortableEnderchest();
		createCoalGenerator();
		createMiniatureEnchantingTable();
		createExoSuit();
		createInfuser();
		createFissionReactor();
		createCopperTank();
		createIronTank();
		createSteelTank();
		createIronMultiTool();
		createSteelMultiTool();	
		createDiamondMultiTool();
		createMultiBlockHammer();
		createRadiatedMultiBlockHammer();
		createSpeedUpgrade();
		createEnergyUpgrade();
		createVoidOreMinerUpgrades();
		createLenses();
		createDrone();
		createStopWatch();
		createBox();
		createBossSummoningItems();
		createReinforcedTrident();
		createGiantArmor();
		createWornoutGiantGloves();
		createGiantGloves();
		createGiantGlovesS();
		createSatanBlade();
		createHellKatana();
		createUndeadRobe();
		createBasicRobe();
		createMutatedRobes();
		createChillyStaff();
		createBruteAxe();
		createRavagerHead();
		createRainBow();
		createRainbowBow();
		createIndustrialSawCatalyst();
		createEnchantedWorkbenchCatalyst();
		createWeaponryCatalyst();
		createAutomaticGardenCatalyst();
		createPistol();
		createUzi();
		createRevolver();
		createBarrettM82();
		createSexPistols();
		createGunAmmo();
		createSniperSet();
	}
	
	public static boolean doesContainItem(Player player, ItemStack item) {
		Inventory inv = player.getInventory();
		for(int i=0; i<inv.getSize();i++) {
			if(inv.getItem(i) != null && inv.getItem(i).hasItemMeta()) {
				if(inv.getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
					String id = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING);
					if(inv.getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals(id)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public static boolean doesContainItemID(Player player, String id) {
		Inventory inv = player.getInventory();
		for(int i=0; i<inv.getSize();i++) {
			if(inv.getItem(i) != null && inv.getItem(i).hasItemMeta()) {
				if(inv.getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
					if(inv.getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals(id)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	private static void createCheatBrick() {
		ItemStack item = new ItemStack(Material.STICK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§6Cheat Stick");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		List<String> lore = new ArrayList<>();
		lore.add("§6Cheats ACTIVATED");
		meta.setLore(lore);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "cheatbrick");
		item.setItemMeta(meta);
		CheatBrick=item;
	}
	private static void createDebugStick() {
		ItemStack item = new ItemStack(Material.STICK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"Debug Stick");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "debugstick");
		item.setItemMeta(meta);
		DebugStick=item;
	}
	private static void createSpyBow() {
		ItemStack item = new ItemStack(Material.SPYGLASS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§4Scoped Bow");
		List<String> lore = new ArrayList<>();
		lore.add("§dSneak - Shoot");
		lore.add("§dRight Click - Scope");
		lore.add("§dRequires arrows in your inventory");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "spybow");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Infinite"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Arrow_speed"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "miscspybow");
		item.setItemMeta(meta);
		SpyBow=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("spybow"), item);
		sr.shape(" x ",
				 " w ", 
				 " y ");
		sr.setIngredient('x', Material.SPYGLASS);
		sr.setIngredient('y', Material.BOW);
		sr.setIngredient('w', new RecipeChoice.ExactChoice(IngredientManager.ModularCasing));
		Bukkit.getServer().addRecipe(sr);
		EnchantingTableEvent.initItem(item);
		EnchantingTableEvent.AddEnchant(item, "Arrow_speed", 5, 6);
		EnchantingTableEvent.AddEnchant(item, "Infinite", 1, 20);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("spybow", 0, new ArrayList(array));
		array.clear();
	}
	
	private static void createReturnScroll() {
		ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§fReturn Scroll");
		List<String> lore = new ArrayList<>();
		lore.add("Returns you to your spawn location");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "returnscroll");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "miscreturnscroll");
		item.setItemMeta(meta);
		ReturnScroll=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("returnscroll"), item);
		sr.shape("  y",
				 " x ", 
				 "y  ");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.CondensedGlowstone));
		sr.setIngredient('y', Material.PAPER);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("returnscroll", 0, new ArrayList(array));
		array.clear();
	}
	private static void createWarp() {
		ItemStack item = new ItemStack(Material.BLAZE_ROD, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§bWarp Wand");
		List<String> lore = new ArrayList<>();
		lore.add("§dRight Click - Warp (RF cost: 50)");
		lore.add("§dTeleports you 5 blocks forward");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "warpwand");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Range"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "miscwarpwand");
		item.setItemMeta(meta);
		Warp=item;
		
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("warp"), item);
		sr.shape("zwz",
				 " y ", 
				 "zwz");
		sr.setIngredient('y', Material.ENDER_PEARL);
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.CondensedGlowstone));
		sr.setIngredient('w', new RecipeChoice.ExactChoice(IngredientManager.ModularCasing));
		Bukkit.getServer().addRecipe(sr);
		EnchantingTableEvent.initItem(item);
		EnchantingTableEvent.AddEnchant(item, "Range", 3, 15);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("warpwand", 0, new ArrayList(array));
		array.clear();
		
	}
	private static void createPortableWorkbench() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Portable Workbench");
		List<String> lore = new ArrayList<>();
		lore.add("§dRight Click - Open Workbench");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "portableworkbench");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "miscportableworkbench");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmNkYzBmZWI3MDAxZTJjMTBmZDUwNjZlNTAxYjg3ZTNkNjQ3OTMwOTJiODVhNTBjODU2ZDk2MmY4YmU5MmM3OCJ9fX0="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		PortableWorkbench=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("portableworkbench"), item);
		sr.shape("yyy",
				 "yxy", 
				 "yyy");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		sr.setIngredient('y', Material.CRAFTING_TABLE);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("portableworkbench", 0, new ArrayList(array));
		array.clear();
	}
	private static void createPegasusSpawnEgg() {
		ItemStack item = new ItemStack(Material.TURTLE_EGG, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§9Pegasus Egg");
		List<String> lore = new ArrayList<>();
		lore.add("§dRight Click - Spawns a Pegasus");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "pegasusspawnegg");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "miscpegasusspawnegg");
		item.setItemMeta(meta);
		PegasusSpawnEgg=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("pegasusspawnegg"), item);
		sr.shape("wzx",
				 "zyz", 
				 "xzw");
		sr.setIngredient('x', Material.LEATHER);
		sr.setIngredient('y', Material.EGG);
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.CondensedGlowstone));
		sr.setIngredient('w', Material.FEATHER);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("pegasusspawnegg", 0, new ArrayList(array));
		array.clear();
	}
	private static void createPortablesmeltery() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Remote Furnace Interface");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"Right click on a furnace to save it's location");
		lore.add(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"to the Interface. Right click to open");
		lore.add(ChatColor.DARK_PURPLE+""+ChatColor.BOLD+"the furnace saved from any location.");
		lore.add(ChatColor.GRAY+"Current Furnace - Dimension: none");
		lore.add(ChatColor.GRAY+"Coordinates: {none, none, none}.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "portablesmeltery");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "miscportablesmeltery");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDE3YjhiNDNmOGM0YjVjZmViOTE5YzlmOGZlOTNmMjZjZWI2ZDJiMTMzYzJhYjFlYjMzOWJkNjYyMWZkMzA5YyJ9fX0=="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Portablesmeltery=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("portablesmeltery"), item);
		sr.shape("yzy",
				 "yxy", 
				 "yzy");
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.NeptuniumFluxConductor));
		sr.setIngredient('y', Material.FURNACE);
		Bukkit.getServer().addRecipe(sr);
	}
	private static void createShortBow() {
		ItemStack item = new ItemStack(Material.BOW, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§4Short Bow");
		List<String> lore = new ArrayList<>();
		lore.add("§dLeft Click - Shoots Instantly");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "shortbow");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "miscshortbow");
		item.setItemMeta(meta);
		ShortBow=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("shortbow"), item);
		sr.shape("xaa",
				 " ys", 
				 "xaa");
		sr.setIngredient('s', Material.STRING);
		sr.setIngredient('a', Material.STICK);
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.ModularCasing));
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.CondensedGlowstone));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("shortbow", 0, new ArrayList(array));
		array.clear();
	}
	private static void createHydraulicPress() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Hydraulic Press");
		List<String> lore = new ArrayList<>();
		lore.add("§7Condenses materials into more refined versions");
		lore.add("§7which are more compatible with complex recipes");
		lore.add("§dRight Click - Open Hydraulic Press");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING, "hydraulicpress");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0); 
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "stage1hydraulicpress");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTQ4MzM3ZjdlZGUxNWMzYjJmOGRjNmE2M2JkOTI4NzRjZGY3NGVjODYyYjQxMThjN2UzNTU1OWNlOGI0ZCJ9fX0==="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		HydraulicPress=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("hydraulicpress"), item);
		sr.shape("yxy",
				 "y y", 
				 "yxy");
		sr.setIngredient('x', Material.PISTON);
		sr.setIngredient('y', Material.IRON_BLOCK);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("hydraulicpress", 2, new ArrayList(array));
		array.clear();
	}
	private static void createPortableEnderchest() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Portable Ender chest");
		List<String> lore = new ArrayList<>();
		lore.add("§dRight Click - Open Ender chest");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "portableenderchest");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "miscportableenderchest");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTZjYzQ4NmMyYmUxY2I5ZGZjYjJlNTNkZDlhM2U5YTg4M2JmYWRiMjdjYjk1NmYxODk2ZDYwMmI0MDY3In19fQ==="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		PortableEnderChest=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("portableenderchest"), item);
		sr.shape("   ",
				 " x ", 
				 " y ");
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		sr.setIngredient('x', Material.ENDER_CHEST);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("portableenderchest", 0, new ArrayList(array));
		array.clear();
	}
	private static void createCoalGenerator() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Coal Generator");
		List<String> lore = new ArrayList<>();
		lore.add("§7A simple old-school coal generator.");
		lore.add("§7Burns 1 coal every 5 seconds and");
		lore.add("§7produces 2 Redstone-Flux every second.");
		lore.add("§dRight Click - Open Coal Generator Fuel Tank");
		lore.add("§dLeft Click - Activate Coal Generator");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "coalgenerator");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "misccoalgenerator");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTM4ZDY3ZDdiODViODlhODlhNWFkNGIxNjhlYzY3ZGJkZGIxZTU4YzY0OGFjYjFkMmQ2MDJjZGUzZDlmYjgyIn19fQ====="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CoalGenerator=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("coalgenerator"), item);
		sr.shape(" z ",
				 " x ",
				 "yyy");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.ModularCasing));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.CarbonIngot));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(TalismanManager.SmallBattery));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("coalgenerator", 0, new ArrayList(array));
		array.clear();
	}
	private static void createMiniatureEnchantingTable() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Miniature Enchanting Table");
		List<String> lore = new ArrayList<>();
		lore.add("§7The unique enchanting power emanating");
		lore.add("§7from a normal enchanting table warped");
		lore.add("§7when it was sized down on the molecular");
		lore.add("§7level. Due to this it is capable of creating");
		lore.add("§7never seen before enchantments.");
		lore.add("§dRight Click - Open Miniature Enchanting Table");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "miniatureenchantingtable");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "miscminiatureenchantingtable");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTc2MmExNWIwNDY5MmEyZTRiM2ZiMzY2M2JkNGI3ODQzNGRjZTE3MzJiOGViMWM3YTlmN2MwZmJmNmYifX19==="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		MiniatureEnchantingTable=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("miniatureenchantingtable"), item);
		sr.shape("   ",
				 "zyz", 
				 "wxw");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		sr.setIngredient('y', Material.ENCHANTING_TABLE);
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.CondensedDiamond));
		sr.setIngredient('w', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("miniatureenchantingtable", 0, new ArrayList(array));
		array.clear();
	}
	private static void createExoSuit() {
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
		bootsMeta.setColor(Color.WHITE);
		bootsMeta.setDisplayName(ChatColor.WHITE+"ExoSuit Boots");
		List<String> lore = new ArrayList<>();
		lore.add("§7A piece of the ExoSuit set.");
		lore.add("§7Full Set Bonus - the suit will");
		lore.add("§7initiate and it's abillity, ");
		lore.add("§7determined by the headsets type,");
		lore.add("§7will activate.");
		lore.add(ChatColor.BLUE+"The suit requires more then 50% of your");
		lore.add(ChatColor.BLUE+"battery to be charged in order to function");
		bootsMeta.setLore(lore);
		bootsMeta.setUnbreakable(true);
		bootsMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		bootsMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "exosuitboots");
		bootsMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "SetID"), PersistentDataType.STRING, "exosuit");
		bootsMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "exosuit4");
		bootsMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		bootsMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		bootsMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
		bootsMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackresistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		bootsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 3, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		bootsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		boots.setItemMeta(bootsMeta);
		ExoSuit[0]=boots;
		ShapedRecipe bootssr = new ShapedRecipe(NamespacedKey.minecraft("exosuitboots"), boots);
		bootssr.shape("y y",
				 	  "xwx", 
				 	  "z z");
		bootssr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		bootssr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		bootssr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.NeptuniumIngot));
		bootssr.setIngredient('w', Material.NETHERITE_BOOTS);
		Bukkit.getServer().addRecipe(bootssr);
		ArrayList array = new ArrayList<>();
		array.add(boots);
		array.add("CraftingTable");
		array.add(bootssr);
		RecipeBookSelectorEvent.addRecipe("exosuitboots", 0, new ArrayList(array));
		array.clear();
		ItemStack leggings = new ItemStack(boots);
		leggings.setType(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
		leggingsMeta.setDisplayName(ChatColor.WHITE+"ExoSuit Leggings");
		leggingsMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "exosuitleggings");
		leggingsMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "SetID"), PersistentDataType.STRING, "exosuit");
		leggingsMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "exosuit3");
		leggingsMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		leggingsMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		leggingsMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
		leggingsMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackresistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		leggingsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 3, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		leggingsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 6, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		leggings.setItemMeta(leggingsMeta);
		ExoSuit[1]=leggings;
		ShapedRecipe leggingssr = new ShapedRecipe(NamespacedKey.minecraft("exosuitleggings"), leggings);
		leggingssr.shape("xyx",
				 	  	 "xwx", 
				 	  	 "z z");
		leggingssr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		leggingssr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		leggingssr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.NeptuniumIngot));
		leggingssr.setIngredient('w', Material.NETHERITE_LEGGINGS);
		Bukkit.getServer().addRecipe(leggingssr);
		array.add(leggings);
		array.add("CraftingTable");
		array.add(leggingssr);
		RecipeBookSelectorEvent.addRecipe("exosuitleggings", 0, new ArrayList(array));
		array.clear();
		ItemStack chestplate = new ItemStack(boots);
		LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
		chestplate.setType(Material.LEATHER_CHESTPLATE);
		chestplateMeta.setDisplayName(ChatColor.WHITE+"ExoSuit Chestplate");
		chestplateMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "exosuitchestplate");
		chestplateMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "SetID"), PersistentDataType.STRING, "exosuit");
		chestplateMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "exosuit2");
		chestplateMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		chestplateMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		chestplateMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
		chestplateMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackresistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		chestplateMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		chestplateMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		chestplate.setItemMeta(chestplateMeta);
		ExoSuit[2]=chestplate;
		ShapedRecipe chestplatesr = new ShapedRecipe(NamespacedKey.minecraft("exosuitchestplate"), chestplate);
		chestplatesr.shape("x x",
				 	  	   "ywy", 
				 	  	   "yzy");
		chestplatesr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		chestplatesr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		chestplatesr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.NeptuniumIngot));
		chestplatesr.setIngredient('w', Material.NETHERITE_CHESTPLATE);
		Bukkit.getServer().addRecipe(chestplatesr);
		array.add(chestplate);
		array.add("CraftingTable");
		array.add(chestplatesr);
		RecipeBookSelectorEvent.addRecipe("exosuitchestplate", 0, new ArrayList(array));
		array.clear();
		ItemStack headset = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta headsetMeta = (SkullMeta) headset.getItemMeta();
		headsetMeta.setDisplayName(ChatColor.WHITE+"ExoSuit Headset");
		lore.add(ChatColor.YELLOW+""+ChatColor.UNDERLINE+"Exo-Suit Type:");
		lore.add(ChatColor.YELLOW+"Default - No Abillity");
		headsetMeta.setLore(lore);
		headsetMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "exosuitheadset");
		headsetMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "SetID"), PersistentDataType.STRING, "exosuit");
		headsetMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "SetType"), PersistentDataType.STRING, "default");
		headsetMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		headsetMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "exosuit1");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzhlNDYyZjkyMDUxNWQwYjU1OTc5OTEwNGVlOGU1NGI2MGVjZjE1NjRmYzVkZTU5MWVlYzhmOGUzZDZiMiJ9fX0====="));
		Field profileField;
		try {
			profileField = headsetMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(headsetMeta, profile);
		}catch (Exception e) {
			
		}
		headsetMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		headsetMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		headsetMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
		headsetMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackresistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		headsetMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		headsetMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 3, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		headset.setItemMeta(headsetMeta);
		ExoSuit[3]=headset;
		ShapedRecipe headsetsr = new ShapedRecipe(NamespacedKey.minecraft("exosuitheadset"), headset);
		headsetsr.shape("xzx",
				 	  	"ywy", 
				 	  	"   ");
		headsetsr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		headsetsr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		headsetsr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.NeptuniumIngot));
		headsetsr.setIngredient('w', Material.NETHERITE_HELMET);
		Bukkit.getServer().addRecipe(headsetsr);
		array.add(headset);
		array.add("CraftingTable");
		array.add(headsetsr);
		RecipeBookSelectorEvent.addRecipe("exosuitheadset", 0, new ArrayList(array));
		array.clear();
		InfuserEvent.initItem("exosuitheadset");
		List<String> newlore = new ArrayList<>();
		array.clear();
		array.add(4);
		array.add("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzkyOWRkOGFhMzkzNjVhMzVhNjgyM2JiN2M3NzY2Mzc0ZGI2YjgwNzUyYTY4MTkxMTY4NjU2NDhiZDFmNGYifX19");
		array.add("SetType");
		array.add("gold");
		array.add(ChatColor.YELLOW+""+ChatColor.UNDERLINE+"Exo-Suit Type:");
		newlore.add(ChatColor.YELLOW+"Golden Coating - Piglins damage is nullified");
		newlore.add(ChatColor.WHITE+"RF cost - 2xDamage dealt");
		newlore.add(ChatColor.YELLOW+"Golden Enhancement - Golden weapons damage is quadrupled");
		newlore.add(ChatColor.WHITE+"RF cost - 8xBase damage dealt");
		array.add(new ArrayList<String>(newlore));
		newlore.clear();
		InfuserEvent.addRecipe("exosuitheadset", IngredientManager.CondensedGoldIngot,array);
		array.clear();
		array.add(4);
		array.add("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDM1NjdhYzM1NjMwMjU4MzE0N2Y0NjRhZjIxZGNiNWQ3ZWJjYjIyYWFiMjg0NzU1NGM3OWI3NTFmNWJmNDgifX19");
		array.add("SetType");
		array.add("redstone");
		array.add(ChatColor.YELLOW+""+ChatColor.UNDERLINE+"Exo-Suit Type:");
		newlore.add(ChatColor.RED+"Redstone Tank - All melee damage is doubled");
		newlore.add(ChatColor.WHITE+"RF cost - 4xBase damage dealt");
		array.add(new ArrayList<String>(newlore));
		newlore.clear();
		InfuserEvent.addRecipe("exosuitheadset", IngredientManager.CondensedRedstone,array);
		array.clear();
		array.add(2);
		array.add("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE5MjFjNzVjZDY0NWFhNzUzZDg5ZTFjMTQwOTdiZmVjYTIyYTQ5NzgyOWVkZDZmYzdhODc1OTU2YTEyODIifX19");
		array.add("SetType");
		array.add("food");
		array.add(ChatColor.YELLOW+""+ChatColor.UNDERLINE+"Exo-Suit Type:");
		newlore.add(ChatColor.DARK_GREEN+"Food Fabricator - Your hunger and saturation are always maxed");
		newlore.add(ChatColor.WHITE+"RF cost - 20 for every saturation bar");
		array.add(new ArrayList<String>(newlore));
		newlore.clear();
		InfuserEvent.addRecipe("exosuitheadset", FoodManager.UltimateHappyMeal,array);
		array.clear();
		array.add(1);
		array.add("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzM5NGE1YzM4OTA2MTM3ODhjNTgxNWZmMzI1Y2M0ZGRkOGNkNTJlMWYxNzgxODAzYWNiM2I5NWQ4NDJmODgyOSJ9fX0");
		array.add("SetType");
		array.add("elytra");
		array.add(ChatColor.YELLOW+""+ChatColor.UNDERLINE+"Exo-Suit Type:");
		newlore.add(ChatColor.DARK_RED+"Elytra - Your chestplate becomes an elytra");
		newlore.add(ChatColor.WHITE+"RF cost - none");
		newlore.add(ChatColor.DARK_RED+"Fire Jets - pressing shift boosts your speed");
		newlore.add(ChatColor.WHITE+"RF cost - 100 RF");
		array.add(new ArrayList<String>(newlore));
		newlore.clear();
		InfuserEvent.addRecipe("exosuitheadset", IngredientManager.MechanicalElytra, array);
		array.clear();
		array.add(1);
		array.add("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzUxMGQ5YjYxY2EzMzNkMjk0NmM2MWEyNmNiMTdlMzc0ZDRhZGI1NzNiNDZhZmRlYmFmODlmNjViYTVkNGFlMiJ9fX0=");
		array.add("SetType");
		array.add("water");
		array.add(ChatColor.YELLOW+""+ChatColor.UNDERLINE+"Exo-Suit Type:");
		newlore.add(ChatColor.DARK_BLUE+"Oxygen Tank - You have permanent water breathing.");
		newlore.add(ChatColor.WHITE+"RF cost - none");
		newlore.add(ChatColor.DARK_BLUE+"Flippers - high movement speed in water");
		newlore.add(ChatColor.WHITE+"RF cost - none");
		newlore.add(ChatColor.DARK_BLUE+"Air Jets - pressing shift while swimming boosts your speed");
		newlore.add(ChatColor.WHITE+"RF cost - 200 RF");
		array.add(new ArrayList<String>(newlore));
		newlore.clear();
		InfuserEvent.addRecipe("exosuitheadset", IngredientManager.OxygenTank, array);
		array.clear();
		array.add(1);
		array.add("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDlmNDgwNjBhOTZjZGVhZTNjYjQxNzNkMjRmNTQzMTcwNGIyYjEyNDQ4ZDk1ZjMxZDcxY2JlNGE3YjhkZWIifX19=");
		array.add("SetType");
		array.add("ghost");
		array.add(ChatColor.YELLOW+""+ChatColor.UNDERLINE+"Exo-Suit Type:");
		newlore.add(ChatColor.DARK_PURPLE+"Ghosting - You have permanent invisibility and glowing.");
		newlore.add(ChatColor.WHITE+"RF cost - none");
		newlore.add(ChatColor.DARK_PURPLE+"Float - You naturally float, holding shift cancels it.");
		newlore.add(ChatColor.WHITE+"RF cost - 40 RF per second");
		array.add(new ArrayList<String>(newlore));
		newlore.clear();
		InfuserEvent.addRecipe("exosuitheadset", IngredientManager.Ectoplasm, array);
		array.clear();
		//pink core
		array.add(1);
		array.add("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOThlZWZiMWQ0YjU5NzViMThjOGRkZDM3Y2JiMWNjOWUwZjM2ODc2YmQxMWUzOWNiMmQ0ZDhkOWI2OTg5In19fQ");
		array.add("SetType");
		array.add("rain");
		array.add(ChatColor.YELLOW+""+ChatColor.UNDERLINE+"Exo-Suit Type:");
		newlore.add(ChatColor.BLUE+"Catch The Rainbow - While it is raining");
		newlore.add(ChatColor.BLUE+"the user takes no damage and is capable");
		newlore.add(ChatColor.BLUE+"of creative flight.");
		newlore.add(ChatColor.WHITE+"The Suit costs 400 RF per Second.");
		array.add(new ArrayList<String>(newlore));
		newlore.clear();
		InfuserEvent.addRecipe("exosuitheadset", IngredientManager.SteelIngot, array);
		array.clear();
		//
		array.add(1);
		array.add("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjBiN2Q1MzdiMjYxNmU2OWQ0ZGUzMjNkOGE4M2EwYjhhNjFiYzdlMjQ5ZTVlZjEwZjlkOTFmNmJmN2IzIn19fQ===");
		array.add("SetType");
		array.add("flash");
		array.add(ChatColor.YELLOW+""+ChatColor.UNDERLINE+"Exo-Suit Type:");
		newlore.add(ChatColor.GOLD+"Speedster - You have permanent speed 2.");
		newlore.add(ChatColor.WHITE+"RF cost - none");
		newlore.add(ChatColor.GOLD+"High Jump - You have permanent jump boost 3.");
		newlore.add(ChatColor.WHITE+"RF cost - none");
		newlore.add(ChatColor.GOLD+"Super-sonic punch - All melee hits do 1.4 times damage.");
		newlore.add(ChatColor.WHITE+"RF cost - none");
		newlore.add(ChatColor.WHITE+"The Suit costs 200 RF per Second.");
		array.add(new ArrayList<String>(newlore));
		newlore.clear();
		InfuserEvent.addRecipe("exosuitheadset", IngredientManager.ParticleAccelerator, array);
		array.clear();
		/*
		Red Core - invincible
		all damage taken is nullified
		RF cost - 10*Damage
		*/
		
	}
	private static void createInfuser() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Infuser");
		List<String> lore = new ArrayList<>();
		lore.add("§7Fuses an item into another causing");
		lore.add("§7visible and sometimes fuctional changes.");
		lore.add("§dRight Click - Open Infuser");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING, "infuser");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "stage2infuser");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2I5MjVkM2E1Mjc1OWZkZThjMDI1ODg3MWZlZmQ5MTQxZTVjOTdmZGY0NTNhZjNkZjIxMTA0Y2M4YzQ4OCJ9fX0===="));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Infuser=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("infuser"), item);
		sr.shape("xzx",
				 "y y", 
				 "yzy");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.CondensedRedstone));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.CondensedIronIngot));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.ModularCasing));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("infuser", 2, new ArrayList(array));
		array.clear();
	}
	private static void createFissionReactor() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Fission Reactor");
		List<String> lore = new ArrayList<>();
		lore.add("§7Using different types of fuel cells,");
		lore.add("§7the fission reactor will generate high");
		lore.add("§7amounts of RF for a limited time and");
		lore.add("§7byproducts.");
		lore.add("§dRight Click - Open Fission Reactor");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING, "fissionreactor");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "stage4fissionreactor");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTUyZmZkMDg1MjhlYzgxODNiMzVhYWM2NThiMjkyMjZhZDhiOWFhY2FjOGRkOWUwNGNmMTg2YjExMDY0Y2E0ZCJ9fX0======"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		FissionReactor=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("fissionreactor"), item);
		sr.shape("xzx",
				 "wyw", 
				 "xzx");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.Rubber));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		sr.setIngredient('w', new RecipeChoice.ExactChoice(IngredientManager.Plastic));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("fissionreactor", 2, new ArrayList(array));
		array.clear();
	}
	private static void createCopperTank() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Copper Tank");
		List<String> lore = new ArrayList<>();
		lore.add("§7Can store up to 10,000mb of a single");
		lore.add("§7type of liquid or gass.");
		lore.add("§70/10000mb of none.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "coppertank");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, "");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER, 10000);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "tank1");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTFmNDM3YmQ5NzU0ZjM4NmUyNzY4N2RhYjdmNzYxZmQyM2YxNGJmM2E1ODM1MzY1ZDZiOTM4Yzk1MzEwYjE0NSJ9fX0======"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		CopperTank=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("coppertank"), item);
		sr.shape("xyx",
				 "yzy", 
				 "xyx");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.CondensedCopperIngot));
		sr.setIngredient('y', Material.GLASS);
		sr.setIngredient('z', Material.BUCKET);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("coppertank", 0, new ArrayList(array));
		array.clear();
	}
	private static void createIronTank() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta)item.getItemMeta();
		meta.setDisplayName("§9Iron Tank");
		List<String> lore = new ArrayList<>();
		lore.add("§7Can store up to 20,000mb of a single");
		lore.add("§7type of liquid or gass.");
		lore.add("§70/20000mb of none.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "irontank");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, "");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER, 20000);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "tank2");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWIwMmU3NWNjOTc5ZDljODU4ZmYxZjE4YjkzY2FhNTlhOGNmYzY2OWRlOGM5NzRkODBkY2U4ZTM5M2YxN2M4YyJ9fX0======"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		IronTank=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("irontank"), item);
		sr.shape("xyx",
				 "yzy", 
				 "xyx");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.CondensedIronIngot));
		sr.setIngredient('y', Material.GLASS);
		sr.setIngredient('z', new RecipeChoice.ExactChoice(CopperTank));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("irontank", 0, new ArrayList(array));
		array.clear();
	}
	private static void createSteelTank() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName("§9Steel Tank");
		List<String> lore = new ArrayList<>();
		lore.add("§7Can store up to 40,000mb of a single");
		lore.add("§7type of liquid or gass.");
		lore.add("§70/40000mb of none.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "steeltank");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, "");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER, 40000);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "tank3");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDU5ZGU1NjExN2I5NzU4MzlkNDA0YTUyNTc5ZDYzYTA0NDFiOWUzM2JkNTdhMzA0MmU3ZDRjYWZhYWQ2N2ZmOCJ9fX0======"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		SteelTank=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("steeltank"), item);
		sr.shape("xxx",
				 "xzx", 
				 "xxx");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IronTank));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("steeltank", 0, new ArrayList(array));
		array.clear();
	}
	private static void createIronMultiTool() {
		ItemStack item = new ItemStack(Material.IRON_PICKAXE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§9Iron Multi Tool");
		List<String> lore = new ArrayList<>();
		lore.add("§7A tool that gives haste 1 when");
		lore.add("§7breaking a block at the cost");
		lore.add("§7of 5000 RF.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "ironmultitool");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "multitool1");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		item.setItemMeta(meta);
		IronMultiTool=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("ironmultitool"), item);
		sr.shape("aps",
				 " x ", 
				 " x ");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.CondensedIronIngot));
		sr.setIngredient('a', Material.IRON_AXE);
		sr.setIngredient('p', Material.IRON_PICKAXE);
		sr.setIngredient('s', Material.IRON_SHOVEL);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("ironmultitool", 0, new ArrayList(array));
		array.clear();
	}
	private static void createSteelMultiTool() {
		ItemStack item = new ItemStack(Material.IRON_PICKAXE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§9Steel Multi Tool");
		List<String> lore = new ArrayList<>();
		lore.add("§7A tool that gives haste 2 when");
		lore.add("§7breaking a block at the cost");
		lore.add("§7of 1000 RF.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "steelmultitool");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "multitool2");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		item.setItemMeta(meta);
		SteelMultiTool=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("steelmultitool"), item);
		sr.shape("xxx",
				 " z ", 
				 " y ");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IronMultiTool));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.SteelRod));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("steelmultitool", 0, new ArrayList(array));
		array.clear();
	}
	private static void createDiamondMultiTool() {
		ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§9Diamond Multi Tool");
		List<String> lore = new ArrayList<>();
		lore.add("§7A tool that gives haste 2 when");
		lore.add("§7breaking a block at the cost");
		lore.add("§7of 250 RF.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "diamondmultitool");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "multitool3");
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		item.setItemMeta(meta);
		DiamondMultiTool=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("diamondmultitool"), item);
		sr.shape("xxx",
				 " z ", 
				 " y ");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.CondensedDiamond));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(SteelMultiTool));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.SteelRod));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("diamondmultitool", 0, new ArrayList(array));
		array.clear();
	}
	private static void createMultiBlockHammer() {
		ItemStack item = new ItemStack(Material.STICK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("§9MultiBlock Hammer");
		List<String> lore = new ArrayList<>();
		lore.add("§7Right click the main block of a multi");
		lore.add("§7block to create the multi block,");
		lore.add("§7required for Stage 3 multiblocks.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "multiblockhammer");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "multiblockhammer1");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		MultiBlockHammer=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("multiblockhammer"), item);
		sr.shape(" xy",
				 " zx", 
				 "z  ");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		sr.setIngredient('z', Material.STICK);
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("multiblockhammer", 0, new ArrayList(array));
		array.clear();
	}
	private static void createRadiatedMultiBlockHammer() {
		ItemStack item = new ItemStack(Material.STICK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"Radiated MultiBlock Hammer");
		List<String> lore = new ArrayList<>();
		lore.add("§7Right click the main block of a multi");
		lore.add("§7block to create the multi block,");
		lore.add("§7required for Stage 5 multiblocks.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "radiatedmultiblockhammer");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "multiblockhammer2");
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		RadiatedMultiBlockHammer=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("radiatedmultiblockhammer"), item);
		sr.shape(" xy",
				 " zx", 
				 "z  ");
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.NeptuniumIngot));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.NeptuniumFluxConductor));
		sr.setIngredient('z', new RecipeChoice.ExactChoice(MultiBlockHammer));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("radiatedmultiblockhammer", 0, new ArrayList(array));
		array.clear();
	}
	private static void createSpeedUpgrade() {
		ItemStack item = new ItemStack(Material.HEAVY_WEIGHTED_PRESSURE_PLATE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Speed Upgrade");
		List<String> lore = new ArrayList<>();
		lore.add("§7Boosts the speed of certain machines");
		lore.add("§7and multiblocks by 1.25% for every");
		lore.add("§7upgrade, with 64 giving a 80% boost.");
		lore.add("§7Each upgrade increases energy consumption");
		lore.add("§7by 2.5% for every upgrade.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "speedupgrade");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "universalupgrade1");
		item.setItemMeta(meta);
		SpeedUpgrade=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("speedupgrade"), item);
		sr.shape(" xy",
				 "xzx", 
				 "yx ");
		sr.setIngredient('x', Material.REDSTONE_BLOCK);
		sr.setIngredient('y', Material.LAPIS_BLOCK);
		sr.setIngredient('z', Material.HEAVY_WEIGHTED_PRESSURE_PLATE);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("speedupgrade", 0, new ArrayList(array));
		array.clear();
	}
	private static void createEnergyUpgrade() {
		ItemStack item = new ItemStack(Material.LIGHT_WEIGHTED_PRESSURE_PLATE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Energy Upgrade");
		List<String> lore = new ArrayList<>();
		lore.add("§7Reduces the energy consumption of certain machines");
		lore.add("§7and multiblocks by 1.25% for every upgrade, with");
		lore.add("§764 giving a total reduction of 80%.");
		lore.add("§7An upgrade is only active if it has a speed");
		lore.add("§7upgrade as well, A.K.A, 64 energy upgrades");
		lore.add("§7requires 64 speed upgrades in order to work.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "energyupgrade");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "universalupgrade2");
		item.setItemMeta(meta);
		EnergyUpgrade=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("energyupgrade"), item);
		sr.shape(" xy",
				 "xzx", 
				 "yx ");
		sr.setIngredient('x', Material.OBSIDIAN);
		sr.setIngredient('y', Material.QUARTZ_BLOCK);
		sr.setIngredient('z', Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("energyupgrade", 0, new ArrayList(array));
		array.clear();
	}
	private static void createVoidOreMinerUpgrades() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Void Ore Miner Tier 2 Upgrade");
		List<String> lore = new ArrayList<>();
		lore.add("§7tier 1 -> tier 2");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "voidoreminertier2upgrade");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "voidoreminerupgrade1");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjA4YzE4YjRjNjZhMDhmZmFmNzZiNTVkNDI0N2QzZjM2N2VmNGMzZGY2OTZjYTk3ZmNhYmUzMjY5ZjdiZDUxNSJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		VoidOreMinerUpgrades[0]=item;
		ShapedRecipe tier2sr = new ShapedRecipe(NamespacedKey.minecraft("voidoreminertier2upgrade"), item);
		tier2sr.shape(" xy",
				 	  "xzx", 
				 	  "yx ");
		tier2sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.CondensedIronIngot));
		tier2sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.CondensedCopperIngot));
		tier2sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.ModularCasing));
		Bukkit.getServer().addRecipe(tier2sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(tier2sr);
		RecipeBookSelectorEvent.addRecipe("voidoreminertier2upgrade", 0, new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Void Ore Miner Tier 3 Upgrade");
		lore.add("§7tier 2 -> tier 3");
		lore.add("§7this upgrade requires all previous upgrades to be installed");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "voidoreminertier3upgrade");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "voidoreminerupgrade2");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGQwYjBkZjA4ZjgwYjdlZGUwNWYxMjgzMmIyNmQ4ZGI3MWI2NGU5MjcyNTBiOWZiZDc1Y2UxOTFmODgxYTY5OSJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		VoidOreMinerUpgrades[1]=item;
		ShapedRecipe tier3sr = new ShapedRecipe(NamespacedKey.minecraft("voidoreminertier3upgrade"), item);
		tier3sr.shape(" xy",
				 	  "xzx", 
				 	  "yx ");
		tier3sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.CondensedIronIngot));
		tier3sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		tier3sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.ModularCasing));
		Bukkit.getServer().addRecipe(tier3sr);
		array.add(item);
		array.add("CraftingTable");
		array.add(tier3sr);
		RecipeBookSelectorEvent.addRecipe("voidoreminertier3upgrade", 0, new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Void Ore Miner Tier 4 Upgrade");
		lore.add("§7tier 3 -> tier 4");
		lore.add("§7this upgrade requires all previous upgrades to be installed");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "voidoreminertier4upgrade");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "voidoreminerupgrade3");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTRhZDIyOWQ4MDMwODA1OWZhN2FlZDg2NTQzNzc5Y2Y5MzNmOTFiNmE0Mzc0MzEyOTNkMGJiMzFhMDk1NWI3MSJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		VoidOreMinerUpgrades[2]=item;
		ShapedRecipe tier4sr = new ShapedRecipe(NamespacedKey.minecraft("voidoreminertier4upgrade"), item);
		tier4sr.shape(" xy",
				 	  "xzx", 
				 	  "yx ");
		tier4sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		tier4sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.Plastic));
		tier4sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		Bukkit.getServer().addRecipe(tier4sr);
		array.add(item);
		array.add("CraftingTable");
		array.add(tier4sr);
		RecipeBookSelectorEvent.addRecipe("voidoreminertier4upgrade", 0, new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Void Ore Miner Tier 5 Upgrade");
		lore.add("§7tier 4 -> tier 5");
		lore.add("§7this upgrade requires all previous upgrades to be installed");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "voidoreminertier5upgrade");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "voidoreminerupgrade4");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzk3ZTVmMTA4NWY0NTY1ZGViYjk5OWEwYmM0MWI0MWMwYTIyOWQwMjk4NzFhNjVhYjcwYjU0MDc4ZDE3Y2U4NyJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		VoidOreMinerUpgrades[3]=item;
		ShapedRecipe tier5sr = new ShapedRecipe(NamespacedKey.minecraft("voidoreminertier5upgrade"), item);
		tier5sr.shape(" xy",
				 	  "xzx", 
				 	  "yx ");
		tier5sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.ThoriumIngot));
		tier5sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.UraniumIngot));
		tier5sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		Bukkit.getServer().addRecipe(tier5sr);
		array.add(item);
		array.add("CraftingTable");
		array.add(tier5sr);
		RecipeBookSelectorEvent.addRecipe("voidoreminertier5upgrade", 0, new ArrayList(array));
		array.clear();
	}
	private static void createLenses() {
		ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Stone lens");
		List<String> lore = new ArrayList<>();
		lore.add("§7Causes the Void Ore Miner to generate");
		lore.add("§7stone, cobblestone, deepslate, and");
		lore.add("§7cobbled deepslate. Requires Void Ore");
		lore.add("§7Miner Tier 2");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "stonelens");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "voidoreminerlens1");
		item.setItemMeta(meta);
		VoidOreMinerLenses[0]=item;
		ShapedRecipe StoneLenssr = new ShapedRecipe(NamespacedKey.minecraft("stonelens"), item);
		StoneLenssr.shape("yxy", "xzx", "yxy");
		StoneLenssr.setIngredient('x', Material.GLASS);
		StoneLenssr.setIngredient('y', Material.STONE);
		StoneLenssr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.ModularCasing));
		Bukkit.getServer().addRecipe(StoneLenssr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(StoneLenssr);
		RecipeBookSelectorEvent.addRecipe("stonelens", 0, new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.YELLOW_STAINED_GLASS, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Ingot lens");
		lore.add("§7Causes the Void Ore Miner to generate");
		lore.add("§7stone and deepslate variants of iron,");
		lore.add("§7gold and copper ore. Requires Void Ore");
		lore.add("§7Miner Tier 2");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "ingotlens");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "voidoreminerlens2");
		item.setItemMeta(meta);
		VoidOreMinerLenses[1]=item;
		ShapedRecipe IngotLenssr = new ShapedRecipe(NamespacedKey.minecraft("ingotlens"), item);
		IngotLenssr.shape("yxy", "xzx", "yxy");
		IngotLenssr.setIngredient('x', Material.GLASS);
		IngotLenssr.setIngredient('y', Material.GOLD_INGOT);
		IngotLenssr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.ModularCasing));
		Bukkit.getServer().addRecipe(IngotLenssr);
		array.add(item);
		array.add("CraftingTable");
		array.add(IngotLenssr);
		RecipeBookSelectorEvent.addRecipe("ingotlens", 0, new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Gem lens");
		lore.add("§7Causes the Void Ore Miner to generate");
		lore.add("§7stone and deepslate variants of redstone,");
		lore.add("§7lapis, emerald and diamond ore. Requires Void Ore");
		lore.add("§7Miner Tier 3");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "gemlens");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "voidoreminerlens3");
		item.setItemMeta(meta);
		VoidOreMinerLenses[2]=item;
		ShapedRecipe GemLenssr = new ShapedRecipe(NamespacedKey.minecraft("gemlens"), item);
		GemLenssr.shape("yxy", "xzx", "yxy");
		GemLenssr.setIngredient('x', Material.GLASS);
		GemLenssr.setIngredient('y', Material.DIAMOND);
		GemLenssr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.ModularCasing));
		Bukkit.getServer().addRecipe(GemLenssr);
		array.add(item);
		array.add("CraftingTable");
		array.add(GemLenssr);
		RecipeBookSelectorEvent.addRecipe("gemlens", 0, new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.RED_STAINED_GLASS, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Nether lens");
		lore.add("§7Causes the Void Ore Miner to generate");
		lore.add("§7nether blocks and ores. Requires Void Ore");
		lore.add("§7Miner Tier 4");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "netherlens");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "voidoreminerlens4");
		item.setItemMeta(meta);
		VoidOreMinerLenses[3]=item;
		ShapedRecipe NetherLenssr = new ShapedRecipe(NamespacedKey.minecraft("netherlens"), item);
		NetherLenssr.shape("yxy", "xzx", "yxy");
		NetherLenssr.setIngredient('x', Material.GLASS);
		NetherLenssr.setIngredient('y', Material.NETHERITE_INGOT);
		NetherLenssr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		Bukkit.getServer().addRecipe(NetherLenssr);
		array.add(item);
		array.add("CraftingTable");
		array.add(NetherLenssr);
		RecipeBookSelectorEvent.addRecipe("netherlens", 0, new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.WHITE_STAINED_GLASS, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Condensed lens");
		lore.add("§7Causes the Void Ore Miner to generate");
		lore.add("§7Condensed resources, but has a high chance");
		lore.add("§7of failing, causing nothing to generate.");
		lore.add("§7Requires Void Ore Miner Tier 4");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "condensedlens");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "voidoreminerlens5");
		item.setItemMeta(meta);
		VoidOreMinerLenses[4]=item;
		ShapedRecipe CondensedLenssr = new ShapedRecipe(NamespacedKey.minecraft("condensedlens"), item);
		CondensedLenssr.shape("yxy", "xzx", "yxy");
		CondensedLenssr.setIngredient('x', Material.GLASS);
		CondensedLenssr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.CondensedDiamond));
		CondensedLenssr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		Bukkit.getServer().addRecipe(CondensedLenssr);
		array.add(item);
		array.add("CraftingTable");
		array.add(CondensedLenssr);
		RecipeBookSelectorEvent.addRecipe("condensedlens", 0, new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.END_STONE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"End lens");
		lore.add("§7Causes the Void Ore Miner to generate");
		lore.add("§7End blocks and very rarely an end city chest,");
		lore.add("§7but has a high chance of failing, causing nothing to generate.");
		lore.add("§7Requires Void Ore Miner Tier 5");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "endlens");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "voidoreminerlens6");
		item.setItemMeta(meta);
		VoidOreMinerLenses[5]=item;
		ShapedRecipe EndLenssr = new ShapedRecipe(NamespacedKey.minecraft("endlens"), item);
		EndLenssr.shape("wxy", "xzx", "yxw");
		EndLenssr.setIngredient('x', Material.GLASS);
		EndLenssr.setIngredient('w', Material.SHULKER_SHELL);
		EndLenssr.setIngredient('y', Material.ELYTRA);
		EndLenssr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.EnderOrb));
		Bukkit.getServer().addRecipe(EndLenssr);
		array.add(item);
		array.add("CraftingTable");
		array.add(EndLenssr);
		RecipeBookSelectorEvent.addRecipe("endlens", 0, new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.GREEN_STAINED_GLASS, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Radiated lens");
		lore.add("§7Causes the Void Ore Miner to generate");
		lore.add("§7radiated resources, but has a high chance");
		lore.add("§7of failing, causing nothing to generate.");
		lore.add("§7Requires Void Ore Miner Tier 5");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "radiatedlens");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "voidoreminerlens7");
		item.setItemMeta(meta);
		VoidOreMinerLenses[6]=item;
		ShapedRecipe RadiatedLenssr = new ShapedRecipe(NamespacedKey.minecraft("radiatedlens"), item);
		RadiatedLenssr.shape("yxy", "xzx", "yxy");
		RadiatedLenssr.setIngredient('x', Material.GLASS);
		RadiatedLenssr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.NeptuniumIngot));
		RadiatedLenssr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		Bukkit.getServer().addRecipe(RadiatedLenssr);
		array.add(item);
		array.add("CraftingTable");
		array.add(RadiatedLenssr);
		RecipeBookSelectorEvent.addRecipe("radiatedlens", 0, new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.FARMLAND, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Garden lens");
		lore.add("§7Causes the Void Ore Miner to generate");
		lore.add("§7crops. Requires Void Ore Miner Tier 3");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "gardenlens");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "voidoreminerlens8");
		item.setItemMeta(meta);
		VoidOreMinerLenses[7]=item;
		ShapedRecipe GardenLenssr = new ShapedRecipe(NamespacedKey.minecraft("gardenlens"), item);
		GardenLenssr.shape("yxy", "xzx", "yxy");
		GardenLenssr.setIngredient('x', Material.GLASS);
		GardenLenssr.setIngredient('y', Material.HAY_BLOCK);
		GardenLenssr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.ModularCasing));
		Bukkit.getServer().addRecipe(GardenLenssr);
		array.add(item);
		array.add("CraftingTable");
		array.add(GardenLenssr);
		RecipeBookSelectorEvent.addRecipe("gardenlens", 0, new ArrayList(array));
		array.clear();
		//
		item = new ItemStack(Material.LIME_STAINED_GLASS, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Experience lens");
		lore.add("§7Causes the Void Ore Miner to generate Experience");
		lore.add("§7bottles, but has a 98% chance to not generate");
		lore.add("§7anything. Requires Void Ore Miner Tier 5");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "explens");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "voidoreminerlens9");
		item.setItemMeta(meta);
		VoidOreMinerLenses[8]=item;
		ShapedRecipe ExpLenssr = new ShapedRecipe(NamespacedKey.minecraft("explens"), item);
		ExpLenssr.shape("yxy", "xzx", "yxy");
		ExpLenssr.setIngredient('x', Material.GLASS);
		ExpLenssr.setIngredient('y', Material.ENCHANTED_BOOK);
		ExpLenssr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.ModularCasing));
		Bukkit.getServer().addRecipe(ExpLenssr);
		array.add(item);
		array.add("CraftingTable");
		array.add(ExpLenssr);
		RecipeBookSelectorEvent.addRecipe("explens", 0, new ArrayList(array));
		array.clear();
		//
	}
	
	private static void createDrone() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"Tier 1 Drone");
		List<String> lore = new ArrayList<>();
		lore.add("§7Right click to summon.");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "dronetier1");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "generelizedID"), PersistentDataType.STRING, "drone");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "dronetier"), PersistentDataType.INTEGER, 1);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "drone1");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzhhNGZjODg5YjQ1MWRmMjQ0ODdjZjdkMWVmNWEyNDFmOWM1NDZlNTgyY2ZkYmVjNmNiMGVjNjc0N2ZmYzRhZCJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Drone[0]=item;
		ShapedRecipe tier1sr = new ShapedRecipe(NamespacedKey.minecraft("dronetier1"), item);
		tier1sr.shape("xwx",
				 	  "yzy",
				 	  "xwx");
		tier1sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.SteelIngot));
		tier1sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.UltimateModularCasing));
		tier1sr.setIngredient('w', Material.NETHERITE_INGOT);
		tier1sr.setIngredient('y', Material.GLASS);
		Bukkit.getServer().addRecipe(tier1sr);
		ArrayList arraytier1 = new ArrayList<>();
		arraytier1.add(item);
		arraytier1.add("CraftingTable");
		arraytier1.add(tier1sr);
		RecipeBookSelectorEvent.addRecipe("dronetier1", 0, new ArrayList(arraytier1));
		arraytier1.clear();
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"Tier 2 Drone");
		lore.add("§7Right click to summon.");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "dronetier2");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "generelizedID"), PersistentDataType.STRING, "drone");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "dronetier"), PersistentDataType.INTEGER, 2);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "drone2");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGEwZDgxNTI4YmNmZDIzNGRlZmRhMWMwNzI4MGU2MTkzMWVmMjJlYWJlM2FlNWU2MTZmMGYyZmZiZWQ2YWI3NSJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Drone[1]=item;
		ShapedRecipe tier2sr = new ShapedRecipe(NamespacedKey.minecraft("dronetier2"), item);
		tier2sr.shape("xwx",
				 	  "yzy",
				 	  "xwx");
		tier2sr.setIngredient('x', Material.ENDER_PEARL);
		tier2sr.setIngredient('z', new RecipeChoice.ExactChoice(Drone[0]));
		tier2sr.setIngredient('w', Material.NETHERITE_INGOT);
		tier2sr.setIngredient('y', Material.GLASS);
		Bukkit.getServer().addRecipe(tier2sr);
		ArrayList arraytier2 = new ArrayList<>();
		arraytier2.add(item);
		arraytier2.add("CraftingTable");
		arraytier2.add(tier2sr);
		RecipeBookSelectorEvent.addRecipe("dronetier2", 0, new ArrayList(arraytier2));
		arraytier2.clear();
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"Tier 3 Drone");
		lore.add("§7Right click to summon.");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "dronetier3");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "generelizedID"), PersistentDataType.STRING, "drone");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "dronetier"), PersistentDataType.INTEGER, 3);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "drone3");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTY2N2U2ZDg5MjllYWEyMDIzZjQxMmZhMGIwMzE0ZjcyZWU5MGI5NzYzYWQ4ZDE0YzVmN2E4OTk1NGFlNDQ4MiJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Drone[2]=item;
		ShapedRecipe tier3sr = new ShapedRecipe(NamespacedKey.minecraft("dronetier3"), item);
		tier3sr.shape("xwx",
				 	  "yzy",
				 	  "xwx");
		tier3sr.setIngredient('x', Material.HAY_BLOCK);
		tier3sr.setIngredient('z', new RecipeChoice.ExactChoice(Drone[1]));
		tier3sr.setIngredient('w', Material.NETHERITE_INGOT);
		tier3sr.setIngredient('y', Material.GLASS);
		Bukkit.getServer().addRecipe(tier3sr);
		ArrayList arraytier3 = new ArrayList<>();
		arraytier3.add(item);
		arraytier3.add("CraftingTable");
		arraytier3.add(tier3sr);
		RecipeBookSelectorEvent.addRecipe("dronetier3", 0, new ArrayList(arraytier3));
		arraytier3.clear();
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"Tier 4 Drone");
		lore.add("§7Right click to summon.");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "dronetier4");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "generelizedID"), PersistentDataType.STRING, "drone");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "dronetier"), PersistentDataType.INTEGER, 4);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "drone4");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjRkNzk0NDM1Mjg5YmJhZmYzNmIzMmY1NTU0N2M3N2M5NjE4YzNmMjI0NDFmMDc0ZmFiYzFiZjUyOWQ2MjJlOSJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Drone[3]=item;
		ShapedRecipe tier4sr = new ShapedRecipe(NamespacedKey.minecraft("dronetier4"), item);
		tier4sr.shape("xwx",
				 	  "yzy",
				 	  "xwx");
		tier4sr.setIngredient('w', new RecipeChoice.ExactChoice(IngredientManager.NeptuniumFluxConductor));
		tier4sr.setIngredient('z', new RecipeChoice.ExactChoice(Drone[2]));
		tier4sr.setIngredient('x', Material.NETHERITE_INGOT);
		tier4sr.setIngredient('y', Material.GLASS);
		Bukkit.getServer().addRecipe(tier4sr);
		ArrayList arraytier4 = new ArrayList<>();
		arraytier4.add(item);
		arraytier4.add("CraftingTable");
		arraytier4.add(tier4sr);
		RecipeBookSelectorEvent.addRecipe("dronetier4", 0, new ArrayList(arraytier4));
		arraytier4.clear();
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"Tier 5 Drone");
		lore.add("§7Right click to summon.");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "dronetier5");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "generelizedID"), PersistentDataType.STRING, "drone");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "dronetier"), PersistentDataType.INTEGER, 5);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "drone5");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWI2NDY5ZTgwYWRjNGUxMzE3YjE3OTMwMDNmZjUxN2U1OTRlZDIwOGNmM2RmODk4YjE5NzBmMzMxOTIwNzkyYiJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Drone[4]=item;
		ShapedRecipe tier5sr = new ShapedRecipe(NamespacedKey.minecraft("dronetier5"), item);
		tier5sr.shape("awx",
				 	  "yzy",
				 	  "xwb");
		tier5sr.setIngredient('w', new RecipeChoice.ExactChoice(IngredientManager.NeptuniumFluxConductor));
		tier5sr.setIngredient('a', new RecipeChoice.ExactChoice(PortableWorkbench));
		tier5sr.setIngredient('b', new RecipeChoice.ExactChoice(PortableEnderChest));
		tier5sr.setIngredient('z', new RecipeChoice.ExactChoice(Drone[3]));
		tier5sr.setIngredient('x', Material.NETHERITE_INGOT);
		tier5sr.setIngredient('y', Material.GLASS);
		Bukkit.getServer().addRecipe(tier5sr);
		ArrayList arraytier5 = new ArrayList<>();
		arraytier5.add(item);
		arraytier5.add("CraftingTable");
		arraytier5.add(tier5sr);
		RecipeBookSelectorEvent.addRecipe("dronetier5", 0, new ArrayList(arraytier5));
		arraytier5.clear();
	}
	private static void createStopWatch() {
		ItemStack item = new ItemStack(Material.CLOCK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.YELLOW+""+ChatColor.BOLD+"Stop Watch");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.YELLOW+""+ChatColor.BOLD+"ZA WARUDO");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "stopwatch");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "miscstopwatch");
		item.setItemMeta(meta);
		StopWatch=item;
		EnchantedWorkbenchRecipe recipe = new EnchantedWorkbenchRecipe("stopwatch", item);
		recipe.addIngredient(IngredientManager.UltimateModularCasing, 1);
		recipe.addIngredient(IngredientManager.CondensedGoldIngot, 64);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
	}
	private static void createBox() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE+"Box");
		List<String> lore = new ArrayList<>();
		lore.add("§7Can store a single block.");
		lore.add("§7Storing nothing.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "box");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "isStoring"), PersistentDataType.INTEGER, -1);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "miscbox");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDk2NDk2ODVjM2FkZmJkN2U2NWY5OTA1ZjcwNWZjNTY3NGJlNGM4ZWE1YTVkNmY1ZjcyZThlYmFkMTkyOSJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		Box=item;
		/*ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("box"), item);
		sr.shape("xxx",
				 "xzx", 
				 "xxx");
		sr.setIngredient('z', Material.SHULKER_BOX);
		sr.setIngredient('x', Material.PAPER);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("box", 0, new ArrayList(array));
		array.clear();*/
	}
	
	private static void createBossSummoningItems() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#e00081")+""+ChatColor.BOLD+"Ender Lord");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"Summons the Ender Lord,");
		lore.add(ChatColor.GRAY+"Can only be summoned in the End.");
		lore.add(ChatColor.BLACK+"-");
		lore.add(ChatColor.YELLOW+"Abilities");
		lore.add(ChatColor.GRAY+"750HP");
		lore.add(ChatColor.GRAY+"Strength 2 & Resistance 1");
		lore.add(ChatColor.GRAY+"Heals 5HP upon taking water damage (Standing in water)");
		lore.add(ChatColor.GRAY+"At 1/3 of HP gains Resistance 2");
		lore.add(ChatColor.BLACK+"-");
		lore.add(ChatColor.YELLOW+"Drops");
		lore.add(ChatColor.GRAY+"?");
		lore.add(ChatColor.GRAY+"1-2 Pure Ender");
		lore.add(ChatColor.GRAY+"128-256 endstone");
		lore.add(ChatColor.GRAY+"0-1 ?");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "enderlordsummon");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "summon01");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2E3NGI3OWY3YTdlNmUxNDJiMWUyMDc2ZGE0NDk1YWM1YTNkMmE3Y2QwODYwODI4YzRhMTg5MDhlMTEyNTY0NiJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		BossSummoningItems[0]=item;
		EnchantedWorkbenchRecipe recipe = new EnchantedWorkbenchRecipe("enderlordsummon", item);
		recipe.addIngredient(Material.ENDER_PEARL, 64);
		recipe.addIngredient(IngredientManager.LostSoul, 8);
		recipe.addIngredient(IngredientManager.EnderOrb, 1);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#bec0ba")+""+ChatColor.BOLD+"Steel Giant");
		lore.add(ChatColor.GRAY+"Summons the Steel Giant,");
		lore.add(ChatColor.GRAY+"Can only be summoned in the Overworld.");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Abilities");
		lore.add(ChatColor.GRAY+"500HP");
		lore.add(ChatColor.GRAY+"Regeneration 1 & Resistance 1 & Weakness 1");
		lore.add(ChatColor.GRAY+"All players have Blindness 1");
		lore.add(ChatColor.GRAY+"At 1/2 of HP gains Resistance 2");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Drops");
		lore.add(ChatColor.GRAY+"2-4 Perfect Steel Ingot");
		lore.add(ChatColor.GRAY+"2-5 iron blocks");
		lore.add(ChatColor.GRAY+"1 Random Giant's Armor Piece");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "steelgiantsummon");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "summon02");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDU1YzMzOTAyNDkyNjlkMWFjMWE2MzIwOWVkMzViNzFjNjY4MzBmYmMzNGI4NzIwNGJkY2VkZDQ2MTVlIn19fQ"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		BossSummoningItems[1]=item;
		recipe = new EnchantedWorkbenchRecipe("steelgiantsummon", item);
		recipe.addIngredient(IngredientManager.LostSoul, 8);
		recipe.addIngredient(IngredientManager.SteelIngot, 24);
		recipe.addIngredient(Material.IRON_BLOCK, 16);
		recipe.addIngredient(Material.DIAMOND, 2);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#067696")+""+ChatColor.BOLD+"Guardian of the Ocean");
		lore.add(ChatColor.GRAY+"Summons the Guardian of the Ocean,");
		lore.add(ChatColor.GRAY+"Summoning in an ocean biome is recommended.");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Abilities");
		lore.add(ChatColor.GRAY+"450HP");
		lore.add(ChatColor.of("#f8ff96")+"If summoned outside an ocean biome:");
		lore.add(ChatColor.GRAY+"+150HP");
		lore.add(ChatColor.GRAY+"Resistance 1");
		lore.add(ChatColor.GRAY+"All players have Poison 1");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Drops");
		lore.add(ChatColor.GRAY+"1-4 nautilus shells");
		lore.add(ChatColor.GRAY+"2-3 sponges");
		lore.add(ChatColor.GRAY+"1 Soul Of The Ocean");
		lore.add(ChatColor.GRAY+"30% Reinforced Trident");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "oceanguardiansummon");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "summon03");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWRmOTZjY2Q5Mjk5MGRkOGI4NTUzZDJjY2VhYjRjMmM5YjIwOGFhMjI1ZGIxNTc1ODJmY2Q1NjY5MTVkNGJjZiJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		BossSummoningItems[2]=item;
		recipe = new EnchantedWorkbenchRecipe("oceanguardiansummon", item);
		recipe.addIngredient(Material.COD, 32);
		recipe.addIngredient(Material.SALMON, 32);
		recipe.addIngredient(Material.PUFFERFISH, 16);
		recipe.addIngredient(Material.TROPICAL_FISH, 16);
		recipe.addIngredient(Material.HEART_OF_THE_SEA, 4);
		recipe.addIngredient(IngredientManager.LostSoul, 8);
		recipe.addIngredient(IngredientManager.UndeadHide, 16);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Awakened Wither");
		lore.add(ChatColor.GRAY+"Summons the Awakened Wither,");
		lore.add(ChatColor.GRAY+"Can only be summoned in the Nether.");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Abilities");
		lore.add(ChatColor.GRAY+"1000 HP");
		lore.add(ChatColor.GRAY+"Regeneration 1 & Resistance 2");
		lore.add(ChatColor.GRAY+"All players have Poison 1");
		lore.add(ChatColor.GRAY+"At 10% of HP all players get Instant Damage 2");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Drops");
		lore.add(ChatColor.GRAY+"?");
		lore.add(ChatColor.GRAY+"4-12 ancient debris");
		lore.add(ChatColor.GRAY+"16-32 gold ingots");
		lore.add(ChatColor.GRAY+"Awakened nether star");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "awakenedwithersummon");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "summon04");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTQzNTE2NGMwNWNlYTI5OWEzZjAxNmJiYmVkMDU3MDZlYmI3MjBkYWM5MTJjZTQzNTFjMjI5NjYyNmFlY2Q5YSJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		BossSummoningItems[3]=item;
		recipe = new EnchantedWorkbenchRecipe("awakenedwithersummon", item);
		recipe.addIngredient(IngredientManager.LostSoul, 32);
		recipe.addIngredient(IngredientManager.PerfectDiamond, 4);
		recipe.addIngredient(IngredientManager.CosmicLeather, 8);
		recipe.addIngredient(Material.NETHER_STAR, 3);
		recipe.addIngredient(Material.NETHERITE_INGOT, 6);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#3de3ff")+""+ChatColor.BOLD+"Diamond Giant");
		lore.add(ChatColor.GRAY+"Summons the Diamond Giant,");
		lore.add(ChatColor.GRAY+"Can only be summoned in the Overworld under y = 64.");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Abilities");
		lore.add(ChatColor.GRAY+"750HP");
		lore.add(ChatColor.GRAY+"Regeneration 2 & Resistance 2");
		lore.add(ChatColor.GRAY+"All players have Slowness 2");
		lore.add(ChatColor.GRAY+"At 75%,50%,25% of HP inflicts Instant Damage 1 to all players");
		lore.add(ChatColor.GRAY+"When hit, has a 10% chance of him teleporting to the player hitting him,");
		lore.add(ChatColor.GRAY+"destroying all blocks around");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Drops");
		lore.add(ChatColor.GRAY+"4-8 Perfect Steel Ingot");
		lore.add(ChatColor.GRAY+"1 Perfect Diamond");
		lore.add(ChatColor.GRAY+"10% Worn Out Giant's Glove");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "diamondgiantsummon");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "summon05");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjE3ZjQ2MDQ4NWIwNWI3ZTNkYmI1NjBiNWQxYzAyMWJhMTc1ODcyNDUwZDVkOGEzY2U2NGYyMjllMjc0MmY5NiJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		BossSummoningItems[4]=item;
		recipe = new EnchantedWorkbenchRecipe("diamondgiantsummon", item);
		recipe.addIngredient(IngredientManager.CondensedEmerald, 16);
		recipe.addIngredient(BossSummoningItems[1], 1);
		recipe.addIngredient(Material.DIAMOND_BLOCK, 5);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#e60c00")+""+ChatColor.BOLD+"Satan");
		lore.add(ChatColor.GRAY+"Summons Satan,");
		lore.add(ChatColor.GRAY+"Can only be summoned in the Overworld during night time.");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Abilities");
		lore.add(ChatColor.GRAY+"750HP");
		lore.add(ChatColor.GRAY+"Resistance 1");
		lore.add(ChatColor.GRAY+"All players have Blindness 1");
		lore.add(ChatColor.GRAY+"At 50% of HP inflicts Blindness 2 to all players");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Drops");
		lore.add(ChatColor.GRAY+"1 Dismembered Demon Head");
		lore.add(ChatColor.GRAY+"32-64 Demonic Flesh");
		lore.add(ChatColor.GRAY+"10% Satan's Blade");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "satansummon");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "summon06");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzJiNzIwZDFkODUzNjRhZThhNDZlZGFhNzQ5YTE1NGUyN2VhZWQ3YjE4N2U0MmMwZmU1ZGIwNmI3MGVlY2U5YiJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		BossSummoningItems[5]=item;
		recipe = new EnchantedWorkbenchRecipe("satansummon", item);
		recipe.addIngredient(IngredientManager.CondensedEmerald, 16);
		recipe.addIngredient(IngredientManager.UndeadHide, 128);
		recipe.addIngredient(IngredientManager.LostSoul, 64);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#bbe2ff")+""+ChatColor.BOLD+"Frozen Disaster");
		lore.add(ChatColor.GRAY+"Summons the Frozen Disaster,");
		lore.add(ChatColor.GRAY+"Can only be summoned in cold biomes.");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Abilities");
		lore.add(ChatColor.GRAY+"500HP");
		lore.add(ChatColor.GRAY+"Resistance 3");
		lore.add(ChatColor.GRAY+"All players have Slowness 1");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Drops");
		lore.add(ChatColor.GRAY+"12-24 Ancient Claws");
		lore.add(ChatColor.GRAY+"8-16 Ancient Fur");
		lore.add(ChatColor.GRAY+"10% Chilly Staff");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "frozendisastersummon");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "summon07");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGZlZWZmNGI3ZmNmY2U2OGIwZjc0ZGYwZGIwYWQwYzAxZjczMDFkMGM2ZDg5MzY5OWI0MDJiZDUwYmIzNzZiMCJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		BossSummoningItems[6]=item;
		recipe = new EnchantedWorkbenchRecipe("frozendisastersummon", item);
		recipe.addIngredient(IngredientManager.UndeadHide, 64);
		recipe.addIngredient(IngredientManager.LostSoul, 16);
		recipe.addIngredient(Material.SNOW_BLOCK, 64);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#855b14")+""+ChatColor.BOLD+"Lord of the Undead");
		lore.add(ChatColor.GRAY+"Summons the Lord of the Undead,");
		lore.add(ChatColor.GRAY+"Can only be summoned in the Overworld.");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Abilities");
		lore.add(ChatColor.GRAY+"250HP");
		lore.add(ChatColor.GRAY+"Resistance 1 & Strength 2");
		lore.add(ChatColor.GRAY+"All players have Blindness 1");
		lore.add(ChatColor.GRAY+"At 50%, 25% of HP summons reinforcements.");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Drops");
		lore.add(ChatColor.GRAY+"16-32 Undead Hide");
		lore.add(ChatColor.GRAY+"4-8 Lost Soul");
		lore.add(ChatColor.GRAY+"20% Undead Robe");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "undeadlordsummon");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "summon08");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2I4ZjM3Y2U0MTdiMmNjM2VhNDk0ZTllNGYwZWFmNjRiM2VhOGNhZDdhYzM5MGU0ZjVlYjZlZmM2MTc5ZGMifX19"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		BossSummoningItems[7]=item;
		recipe = new EnchantedWorkbenchRecipe("undeadlordsummon", item);
		recipe.addIngredient(IngredientManager.CondensedEmerald, 4);
		recipe.addIngredient(Material.ROTTEN_FLESH, 128);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#855b14")+""+ChatColor.BOLD+"Piglin Captain");
		lore.add(ChatColor.GRAY+"Summons the Piglin Captain,");
		lore.add(ChatColor.GRAY+"Can only be summoned in the Nether.");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Abilities");
		lore.add(ChatColor.GRAY+"500HP");
		lore.add(ChatColor.GRAY+"Regeneration 1 & Weakness 1");
		lore.add(ChatColor.GRAY+"At 50% of HP gains Resistance 1");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Drops");
		lore.add(ChatColor.GRAY+"16-32 Hoglin Skin");
		lore.add(ChatColor.GRAY+"1-2 Hoglin Tusk");
		lore.add(ChatColor.GRAY+"1-2 Piglin Tusk");
		lore.add(ChatColor.GRAY+"10% Brute's Axe");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "piglincaptainsummon");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "summon09");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzYzZTViNDdlN2UzYThhMWM0YmNlYzdiZmVmZjMyMDZlZWNkMzBiODg5ODc0MmMyYWIyY2U0ODliNTM2ZWFmYSJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		BossSummoningItems[8]=item;
		recipe = new EnchantedWorkbenchRecipe("piglincaptainsummon", item);
		recipe.addIngredient(IngredientManager.UndeadHide, 32);
		recipe.addIngredient(IngredientManager.AncientClaws, 16);
		recipe.addIngredient(IngredientManager.LostSoul, 2);
		recipe.addIngredient(Material.GOLD_INGOT, 128);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#2b5784")+""+ChatColor.BOLD+"Anomalous Ravager");
		lore.add(ChatColor.GRAY+"Summons the Anomalous Ravager,");
		lore.add(ChatColor.GRAY+"Can only be summoned near a villager.");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Abilities");
		lore.add(ChatColor.GRAY+"750HP");
		lore.add(ChatColor.GRAY+"Regeneration 1");
		lore.add(ChatColor.GRAY+"At 75% of HP summons Pillagers");
		lore.add(ChatColor.GRAY+"At 50% of HP summons Vindicators");
		lore.add(ChatColor.GRAY+"At 25% of HP summons Evokers");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Drops");
		lore.add(ChatColor.GRAY+"64-128 Ravager Skin");
		lore.add(ChatColor.GRAY+"1 Saddle");
		lore.add(ChatColor.GRAY+"10% Ravager Head");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "anomalousravagersummon");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "summon10");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2QyMGJmNTJlYzM5MGEwNzk5Mjk5MTg0ZmM2NzhiZjg0Y2Y3MzJiYjFiZDc4ZmQxYzRiNDQxODU4ZjAyMzVhOCJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		BossSummoningItems[9]=item;
		recipe = new EnchantedWorkbenchRecipe("anomalousravagersummon", item);
		recipe.addIngredient(IngredientManager.UndeadHide, 64);
		recipe.addIngredient(IngredientManager.AncientClaws, 16);
		recipe.addIngredient(IngredientManager.LostSoul, 16);
		recipe.addIngredient(IngredientManager.HoglinSkin, 64);
		recipe.addIngredient(IngredientManager.AncientFur, 32);
		recipe.addIngredient(IngredientManager.HoglinTusk, 4);
		recipe.addIngredient(IngredientManager.PiglinTusk, 4);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#f1f1f1")+""+ChatColor.BOLD+"Skeleton Master");
		lore.add(ChatColor.GRAY+"Summons the Skeleton Master,");
		lore.add(ChatColor.GRAY+"Can only be summoned in the Overworld at night.");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Abilities");
		lore.add(ChatColor.GRAY+"750HP");
		lore.add(ChatColor.GRAY+"Resistance 1 & Speed 3");
		lore.add(ChatColor.GRAY+"Inflicts Blindness 2 on nearby players");
		lore.add(ChatColor.GRAY+"At 50% of HP replicates himself");
		lore.add(ChatColor.GRAY+"At 25% of HP gets upgraded bow");
		lore.add("");
		lore.add(ChatColor.YELLOW+"Drops");
		lore.add(ChatColor.GRAY+"4-8 Scavanged Skeletal Remains");
		lore.add(ChatColor.GRAY+"20% RainBow");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "skeletonmastersummon");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "summon11");
		profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2UwZDNmMjhiOGUwZjBhNDVjYTJiNDJiNmY0OTViYmZkYTczNzMyZDNhM2YxMDhmNjM1MmU3NThlMWNiOWJhOSJ9fX0"));
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		BossSummoningItems[10]=item;
		recipe = new EnchantedWorkbenchRecipe("skeletonmastersummon", item);
		recipe.addIngredient(IngredientManager.UndeadHide, 64);
		recipe.addIngredient(IngredientManager.LostSoul, 16);
		recipe.addIngredient(IngredientManager.HoglinTusk, 1);
		recipe.addIngredient(IngredientManager.PiglinTusk, 1);
		recipe.addIngredient(Material.BONE, 128);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
	}
	
	private static void createReinforcedTrident() {
		ItemStack item = new ItemStack(Material.TRIDENT, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#c5f3ff")+"Reinforced Trident");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"A trident reinforced by the Guardian of the Ocean Boss.");
		lore.add(ChatColor.GRAY+"The trident summons lightning upon any enemy hit by it.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "reinforcedtrident");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmenttrident");
		meta.addEnchant(Enchantment.LOYALTY, 3, false);
		meta.addEnchant(Enchantment.CHANNELING, 1, false);
		item.setItemMeta(meta);
		ReinforcedTrident=item;
	}
	
	private static void createGiantArmor() {
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
		bootsMeta.setColor(Color.fromRGB(179, 180, 179));
		bootsMeta.setDisplayName(ChatColor.WHITE+"Giant's Boots");
		List<String> lore = new ArrayList<>();
		lore.add("§7A piece of the Giant's set.");
		bootsMeta.setLore(lore);
		bootsMeta.setUnbreakable(true);
		bootsMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		bootsMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "giantboots");
		bootsMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		bootsMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		bootsMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
		bootsMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackresistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		bootsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 4, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		bootsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 5, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		boots.setItemMeta(bootsMeta);
		GiantArmor[0]=boots;
		//
		ItemStack leggings = new ItemStack(boots);
		leggings.setType(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
		leggingsMeta.setDisplayName(ChatColor.WHITE+"Giant's Leggings");
		leggingsMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "giantleggings");
		leggingsMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		leggingsMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		leggingsMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
		leggingsMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackresistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		leggingsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 4, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		leggingsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		leggings.setItemMeta(leggingsMeta);
		GiantArmor[1]=leggings;
		//
		ItemStack chestplate = new ItemStack(boots);
		LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
		chestplate.setType(Material.LEATHER_CHESTPLATE);
		chestplateMeta.setDisplayName(ChatColor.WHITE+"Giant's Chestplate");
		chestplateMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "giantchestplate");
		chestplateMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		chestplateMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		chestplateMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
		chestplateMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackresistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		chestplateMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ea4"), "generic.armortoughness", 4, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		chestplateMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 10, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		chestplate.setItemMeta(chestplateMeta);
		GiantArmor[2]=chestplate;
		//
		ItemStack headset = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta headsetMeta = (SkullMeta) headset.getItemMeta();
		headsetMeta.setDisplayName(ChatColor.WHITE+"Giant's Headset");
		headsetMeta.setLore(lore);
		headsetMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "giantheadset");
		headsetMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDU1YzMzOTAyNDkyNjlkMWFjMWE2MzIwOWVkMzViNzFjNjY4MzBmYmMzNGI4NzIwNGJkY2VkZDQ2MTVlIn19fQ"));
		Field profileField;
		try {
			profileField = headsetMeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(headsetMeta, profile);
		}catch (Exception e) {
			
		}
		headsetMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		headsetMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		headsetMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
		headsetMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackresistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		headsetMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 4, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		headsetMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 5, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		headset.setItemMeta(headsetMeta);
		GiantArmor[3]=headset;
	}
	
	private static void createWornoutGiantGloves() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#91efff")+"Worn Out Giant's Glove");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"The fighting gloves used by the Diamond Giant.");
		lore.add(ChatColor.GRAY+"A weapon capable of being dual wielded.");
		lore.add(ChatColor.GRAY+"It's worn out, maybe fixing it will boost it's performance.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When in Main Hand:");
		lore.add(ChatColor.DARK_GREEN+" 3 Attack Damage");
		lore.add(ChatColor.DARK_GREEN+" 7 Attack Speed");
		lore.add(ChatColor.DARK_RED+" +15 Ferocity");
		lore.add(ChatColor.of("#fff881")+" -2 Reach");
		lore.add("");
		lore.add(ChatColor.GRAY+"When in Off Hand:");
		lore.add(ChatColor.DARK_GREEN+" 3 Attack Damage");
		lore.add(ChatColor.DARK_GREEN+" 7 Attack Speed");
		lore.add(ChatColor.DARK_RED+" +5 Ferocity");
		lore.add(ChatColor.of("#fff881")+" -2 Reach");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "wornoutgiantsglove");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "FerocityMainHand"), PersistentDataType.INTEGER, 15);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "FerocityOffHand"), PersistentDataType.INTEGER, 5);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ReachMainHand"), PersistentDataType.INTEGER, -2);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ReachOffHand"), PersistentDataType.INTEGER, -2);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "DualWield"), PersistentDataType.INTEGER, 7);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentgiantsglove0");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU3MzMwNzNlOTc2OTM1YzQ1YWQxZjJhNmFkZWZhZmNlNTc2MzhhZDFkMzI0M2RhNGUwYjhiNTgxMTlkMzVkMiJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attack.damage", 3, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack.speed", 7, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		WornOutGiantGloves=item;
	}
	
	private static void createGiantGloves() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#60e8ff")+"Giant's Glove");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"The fighting gloves used by the Diamond Giant.");
		lore.add(ChatColor.GRAY+"A weapon capable of being dual wielded.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When in Main Hand:");
		lore.add(ChatColor.DARK_GREEN+" 6 Attack Damage");
		lore.add(ChatColor.DARK_GREEN+" 10 Attack Speed");
		lore.add(ChatColor.DARK_RED+" +35 Ferocity");
		lore.add(ChatColor.of("#fff881")+" -1 Reach");
		lore.add("");
		lore.add(ChatColor.GRAY+"When in Off Hand:");
		lore.add(ChatColor.DARK_GREEN+" 6 Attack Damage");
		lore.add(ChatColor.DARK_GREEN+" 10 Attack Speed");
		lore.add(ChatColor.DARK_RED+" +15 Ferocity");
		lore.add(ChatColor.of("#fff881")+" -2 Reach");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "giantsglove");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "FerocityMainHand"), PersistentDataType.INTEGER, 35);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "FerocityOffHand"), PersistentDataType.INTEGER, 15);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ReachMainHand"), PersistentDataType.INTEGER, -1);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ReachOffHand"), PersistentDataType.INTEGER, -2);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "DualWield"), PersistentDataType.INTEGER, 10);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentgiantsglove1");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmZiYzdjNmNlNDkyYmMxNjlkYTEzMzM1MzdjMmE3NTVjZTU1NWRkYzJkMzdjYTE1NDI5NmYxOWJiZWExYWQxNiJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attack.damage", 6, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack.speed", 10, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		GiantGloves=item;
		EnchantedWorkbenchRecipe recipe = new EnchantedWorkbenchRecipe("giantsglove", item);
		recipe.addIngredient(WornOutGiantGloves, 1);
		recipe.addIngredient(IngredientManager.HoglinSkin, 96);
		recipe.addIngredient(IngredientManager.PerfectSteelIngot, 16);
		recipe.addIngredient(IngredientManager.AncientClaws, 32);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
	}
	
	private static void createGiantGlovesS() {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#00bede")+"Giant's Glove +");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"The final form of Giant's Gloves.");
		lore.add(ChatColor.GRAY+"A weapon capable of being dual wielded.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When in Main Hand:");
		lore.add(ChatColor.DARK_GREEN+" 8 Attack Damage");
		lore.add(ChatColor.DARK_GREEN+" 16 Attack Speed");
		lore.add(ChatColor.DARK_RED+" +50 Ferocity");
		lore.add("");
		lore.add(ChatColor.GRAY+"When in Off Hand:");
		lore.add(ChatColor.DARK_GREEN+" 8 Attack Damage");
		lore.add(ChatColor.DARK_GREEN+" 16 Attack Speed");
		lore.add(ChatColor.DARK_RED+" +30 Ferocity");
		lore.add(ChatColor.of("#fff881")+" -2 Reach");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "giantsglove+");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "FerocityMainHand"), PersistentDataType.INTEGER, 50);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "FerocityOffHand"), PersistentDataType.INTEGER, 30);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ReachOffHand"), PersistentDataType.INTEGER, -2);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "DualWield"), PersistentDataType.INTEGER, 16);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentgiantsglove2");
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmE5MDlhN2JhMDg4OTQxMjBjZWU1ZjI0MWY1MDRiZDRkMDcyZGM5YTg1ODk5ZjRhMWJlYWZhNDU5OWY4N2RiZiJ9fX0"));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attack.damage", 8, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack.speed", 20, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		GiantGlovesS=item;
		EnchantedWorkbenchRecipe recipe = new EnchantedWorkbenchRecipe("giantsglove+", item);
		recipe.addIngredient(GiantGloves, 1);
		recipe.addIngredient(IngredientManager.CosmicLeather, 4);
		recipe.addIngredient(IngredientManager.PerfectDiamond, 4);
		recipe.addIngredient(IngredientManager.RefinedSkeletalRemains, 4);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
	}
	
	private static void createSatanBlade() {
		ItemStack item = new ItemStack(Material.NETHERITE_SWORD, 1);
		ItemMeta meta =  item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#ff463c")+"Satan's Blade");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"The blade used by none other then Satan.");
		lore.add(ChatColor.GRAY+"A katana forged by blood of sinners.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When in Main Hand:");
		lore.add(ChatColor.DARK_GREEN+" 9 Attack Damage");
		lore.add(ChatColor.DARK_GREEN+" 1.8 Attack Speed");
		lore.add(ChatColor.DARK_RED+" +75 Ferocity");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "satanblade");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "FerocityMainHand"), PersistentDataType.INTEGER, 75);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentsatanblade0");
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attack.damage", 8, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack.speed", -2.2, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		SatanBlade=item;
	}
	
	private static void createHellKatana() {
		ItemStack item = new ItemStack(Material.NETHERITE_SWORD, 1);
		ItemMeta meta =  item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#eb7353")+"Hell Katana");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"The katana which is used to torture");
		lore.add(ChatColor.GRAY+"demons in hell.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When in Main Hand:");
		lore.add(ChatColor.DARK_GREEN+" 10 Attack Damage");
		lore.add(ChatColor.DARK_GREEN+" 2.4 Attack Speed");
		lore.add(ChatColor.DARK_RED+" +75 Ferocity");
		lore.add(ChatColor.DARK_AQUA+" +25 Crit");
		meta.setLore(lore);
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "hellkatana");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "FerocityMainHand"), PersistentDataType.INTEGER, 75);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "CritMainHand"), PersistentDataType.INTEGER, 25);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentsatanblade1");
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attack.damage", 9, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack.speed", -1.6, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		HellKatana=item;
		EnchantedWorkbenchRecipe recipe = new EnchantedWorkbenchRecipe("hellkatana", item);
		recipe.addIngredient(SatanBlade, 1);
		recipe.addIngredient(IngredientManager.DismemberedDemonHead, 4);
		recipe.addIngredient(IngredientManager.PerfectDiamond, 2);
		recipe.addIngredient(IngredientManager.DemonicFlesh, 256);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
	}
	
	private static void createUndeadRobe() {
		ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.fromRGB(187, 119, 28));
		meta.setDisplayName(ChatColor.WHITE+"Undead Robe");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"The robe wore by the Lord of the Undead.");
		lore.add(ChatColor.GRAY+"Pretty avarage clothing item, though a bit worn out,");
		lore.add(ChatColor.GRAY+"has no unique functionality in it's current state.");
		meta.setLore(lore);
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "undeadrobe");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentrobe0");
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 2, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 6, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		item.setItemMeta(meta);
		UndeadRobe=item;
	}
	
	private static void createBasicRobe() {
		ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.fromRGB(240, 203, 153));
		meta.setDisplayName(ChatColor.WHITE+"Basic Robe");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"A basic robe.");
		lore.add(ChatColor.GRAY+"Previously worn by the Lord Of The Undead.");
		lore.add(ChatColor.GRAY+"Capable of Mutation (Upgrading).");
		meta.setLore(lore);
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "basicrobe");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentrobe01");
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 7, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		item.setItemMeta(meta);
		BasicRobe=item;
		EnchantedWorkbenchRecipe recipe = new EnchantedWorkbenchRecipe("basicrobe", item);
		recipe.addIngredient(UndeadRobe, 1);
		recipe.addIngredient(IngredientManager.CosmicLeather, 2);
		recipe.addIngredient(IngredientManager.PerfectSteelIngot, 4);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
	}
	
	private static void createMutatedRobes() {
		ItemStack item = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.fromRGB(137, 238, 255));
		meta.setDisplayName(ChatColor.of("#89eeff")+"Close-Quarters Robe");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"This robe specializes in close-quarters combat.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When on Body:");
		lore.add(ChatColor.BLUE+" +7 Armor");
		lore.add(ChatColor.BLUE+" +4 Armor Toughness");
		lore.add(ChatColor.BLUE+" +4 Attack Damage");
		lore.add(ChatColor.DARK_AQUA+" +50 Crit");
		lore.add(ChatColor.of("#fff881")+" -1 Reach");
		meta.setLore(lore);
		lore.clear();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "closequartersrobe");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "CritChestplate"), PersistentDataType.INTEGER, 50);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ReachChestplate"), PersistentDataType.INTEGER, -1);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentrobe1");
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 5, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 7, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackdamage", 4, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		MutatedRobes[0]=item;
		EnchantedWorkbenchRecipe recipe = new EnchantedWorkbenchRecipe("closequartersrobe", item);
		recipe.addIngredient(BasicRobe, 1);
		recipe.addIngredient(IngredientManager.CosmicLeather, 4);
		recipe.addIngredient(GiantGloves, 2);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.LEATHER_CHESTPLATE);
		meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.fromRGB(255, 244, 52));
		meta.setDisplayName(ChatColor.of("#fff65b")+"Defensive Robe");
		lore.add(ChatColor.GRAY+"This robe specializes in defense.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When on Body:");
		lore.add(ChatColor.BLUE+" +12 Armor");
		lore.add(ChatColor.BLUE+" +6 Armor Toughness");
		lore.add(ChatColor.BLUE+" -2 Movement Speed");
		meta.setLore(lore);
		lore.clear();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "defensiverobe");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentrobe2");
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		meta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 6, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 12, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movementspeed", -0.03, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		MutatedRobes[1]=item;
		recipe = new EnchantedWorkbenchRecipe("defensiverobe", item);
		recipe.addIngredient(BasicRobe, 1);
		recipe.addIngredient(IngredientManager.CosmicLeather, 2);
		recipe.addIngredient(GiantArmor[2], 1);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.LEATHER_CHESTPLATE);
		meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.fromRGB(193, 131, 19));
		meta.setDisplayName(ChatColor.of("#c18313")+"Balanced Robe");
		lore.add(ChatColor.GRAY+"This robe gives a small increase to each");
		lore.add(ChatColor.GRAY+"one of the players stats.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When on Body:");
		lore.add(ChatColor.BLUE+" +8 Armor");
		lore.add(ChatColor.BLUE+" +4 Armor Toughness");
		lore.add(ChatColor.BLUE+" +2 Attack Damage");
		lore.add(ChatColor.BLUE+" +0.5 Attack Speed");
		lore.add(ChatColor.BLUE+" +1 Movement Speed");
		lore.add(ChatColor.DARK_RED+" +10 Ferocity");
		lore.add(ChatColor.DARK_AQUA+" +10 Crit");
		lore.add(ChatColor.of("#fff881")+" +1 Reach");
		meta.setLore(lore);
		lore.clear();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "balancedrobe");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "FerocityChestplate"), PersistentDataType.INTEGER, 10);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "CritChestplate"), PersistentDataType.INTEGER, 10);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ReachChestplate"), PersistentDataType.INTEGER, 1);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentrobe3");
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		meta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 4, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movementspeed", 0.01, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackdamage", 2, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackspeed", 0.5, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		MutatedRobes[2]=item;
		recipe = new EnchantedWorkbenchRecipe("balancedrobe", item);
		recipe.addIngredient(BasicRobe, 1);
		recipe.addIngredient(IngredientManager.CosmicLeather, 4);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.LEATHER_CHESTPLATE);
		meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.fromRGB(255, 136, 139));
		meta.setDisplayName(ChatColor.of("#ff5c60")+"Offensive Robe");
		lore.add(ChatColor.GRAY+"This robe specializes in offense.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When on Body:");
		lore.add(ChatColor.BLUE+" +5 Armor");
		lore.add(ChatColor.BLUE+" +2 Armor Toughness");
		lore.add(ChatColor.BLUE+" +0.5 Attack Speed");
		lore.add(ChatColor.BLUE+" +3 Attack Damage");
		lore.add(ChatColor.DARK_RED+" +25 Ferocity");
		meta.setLore(lore);
		lore.clear();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "offensiverobe");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "FerocityChestplate"), PersistentDataType.INTEGER, 25);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentrobe4");
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 2, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 5, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackdamage", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackspeed", 0.5, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		MutatedRobes[3]=item;
		recipe = new EnchantedWorkbenchRecipe("offensiverobe", item);
		recipe.addIngredient(BasicRobe, 1);
		recipe.addIngredient(IngredientManager.CosmicLeather, 2);
		recipe.addIngredient(Material.NETHERITE_SWORD, 1);
		recipe.addIngredient(Material.NETHERITE_AXE, 1);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.LEATHER_CHESTPLATE);
		meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.fromRGB(75, 75, 75));
		meta.setDisplayName(ChatColor.of("#6c6c6c")+"Executioner Robe");
		lore.add(ChatColor.GRAY+"This robe specializes in axe combat.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When on Body:");
		lore.add(ChatColor.BLUE+" +8 Armor");
		lore.add(ChatColor.BLUE+" +3 Armor Toughness");
		lore.add(ChatColor.BLUE+" +0.5 Attack Speed");
		lore.add(ChatColor.DARK_AQUA+" +20 Crit");
		lore.add(ChatColor.GRAY+" Double Axe Damage");
		meta.setLore(lore);
		lore.clear();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "executionerrobe");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "CritChestplate"), PersistentDataType.INTEGER, 20);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentrobe5");
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackspeed", 0.5, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		MutatedRobes[4]=item;
		recipe = new EnchantedWorkbenchRecipe("executionerrobe", item);
		recipe.addIngredient(BasicRobe, 1);
		recipe.addIngredient(IngredientManager.CosmicLeather, 2);
		recipe.addIngredient(Material.NETHERITE_AXE, 2);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.LEATHER_CHESTPLATE);
		meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.fromRGB(63, 213, 49));
		meta.setDisplayName(ChatColor.of("#6dff6d")+"Ranged Robe");
		lore.add(ChatColor.GRAY+"This robe specializes in ranged combat.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When on Body:");
		lore.add(ChatColor.BLUE+" +6 Armor");
		lore.add(ChatColor.BLUE+" +3 Armor Toughness");
		lore.add(ChatColor.DARK_AQUA+" +2 Movement Speed");
		lore.add(ChatColor.GRAY+" Double Bow Damage");
		meta.setLore(lore);
		lore.clear();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "rangedrobe");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentrobe6");
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		meta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 6, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movementspeed", 0.02, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		MutatedRobes[5]=item;
		recipe = new EnchantedWorkbenchRecipe("rangedrobe", item);
		recipe.addIngredient(BasicRobe, 1);
		recipe.addIngredient(IngredientManager.CosmicLeather, 3);
		recipe.addIngredient(Material.BOW, 4);
		recipe.addIngredient(Material.NETHERITE_INGOT, 2);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.LEATHER_CHESTPLATE);
		meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.fromRGB(51, 180, 160));
		meta.setDisplayName(ChatColor.of("#65d3c2")+"Scout Robe");
		lore.add(ChatColor.GRAY+"This robe specializes in scouting.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When on Body:");
		lore.add(ChatColor.BLUE+" +7 Armor");
		lore.add(ChatColor.BLUE+" +3 Armor Toughness");
		lore.add(ChatColor.BLUE+" +4 Movement Speed");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Shifting:");
		lore.add(ChatColor.GRAY+" Nearby enemies can't see you");
		meta.setLore(lore);
		lore.clear();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "scoutrobe");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "CritChestplate"), PersistentDataType.INTEGER, 20);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentrobe7");
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		meta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 7, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movementspeed", 0.04, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		MutatedRobes[6]=item;
		recipe = new EnchantedWorkbenchRecipe("scoutrobe", item);
		recipe.addIngredient(BasicRobe, 1);
		recipe.addIngredient(IngredientManager.CosmicLeather, 3);
		recipe.addIngredient(Material.SUGAR, 256);
		recipe.addIngredient(Material.NETHERITE_INGOT, 2);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		item = new ItemStack(Material.LEATHER_CHESTPLATE);
		meta = (LeatherArmorMeta) item.getItemMeta();
		meta.setColor(Color.fromRGB(177, 177, 177));
		meta.setDisplayName(ChatColor.of("#b7b7b7")+"Warrior Robe");
		lore.add(ChatColor.GRAY+"This robe specializes in sword combat.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When on Body:");
		lore.add(ChatColor.BLUE+" +8 Armor");
		lore.add(ChatColor.BLUE+" +3 Armor Toughness");
		lore.add(ChatColor.BLUE+" +2 Attack Damage");
		lore.add(ChatColor.DARK_RED+" +20 Ferocity");
		lore.add(ChatColor.GRAY+" Double Sword Damage");
		meta.setLore(lore);
		lore.clear();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "warriorrobe");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "FerocityChestplate"), PersistentDataType.INTEGER, 20);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentrobe8");
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 3, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 8, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackdamage", 2, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		MutatedRobes[7]=item;
		recipe = new EnchantedWorkbenchRecipe("warriorrobe", item);
		recipe.addIngredient(BasicRobe, 1);
		recipe.addIngredient(IngredientManager.CosmicLeather, 4);
		recipe.addIngredient(Material.NETHERITE_SWORD, 2);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
		//
		
	}
	
	private static void createChillyStaff() {
		ItemStack item = new ItemStack(Material.DIAMOND_SHOVEL, 1);
		ItemMeta meta =  item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#81b4dd")+"Chilly Staff");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"A staff the Frozen Disaster ate long");
		lore.add(ChatColor.GRAY+"ago, giving it it's abilities.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Right Clicked:");
		lore.add(ChatColor.AQUA+" Slows all nearby mobs.");
		meta.setLore(lore);
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "chillystaff");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentchillystaff");
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack.speed", -3, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		ChillyStaff=item;
	}
	
	private static void createBruteAxe() {
		ItemStack item = new ItemStack(Material.GOLDEN_AXE, 1);
		ItemMeta meta =  item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#ffe34f")+"Brute's Axe");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"The axe used by the Piglin Captain,");
		lore.add(ChatColor.GRAY+"an upgraded version of a piglin brute's axe,");
		lore.add(ChatColor.GRAY+"though this may not be it's final form.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When in Main Hand:");
		lore.add(ChatColor.DARK_GREEN+" 8 Attack Damage");
		lore.add(ChatColor.DARK_GREEN+" 1.2 Attack Speed");
		lore.add(ChatColor.DARK_RED+" +25 Ferocity");
		lore.add(ChatColor.DARK_AQUA+" +25 Crit");
		meta.setLore(lore);
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "brutesaxe");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "FerocityMainHand"), PersistentDataType.INTEGER, 25);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "CritMainHand"), PersistentDataType.INTEGER, 25);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentbrutesaxe");
		meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attack.damage", 7, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attack.speed", -2.8, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		BruteAxe=item;
	}
	
	private static void createRavagerHead() {
		ItemStack item = new ItemStack(Material.SHIELD, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#33689f")+"Ravager Head");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"The head of a Ravager,");
		lore.add(ChatColor.GRAY+"functions as a highly effective shield.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When in Main Hand:");
		lore.add(ChatColor.BLUE+" +2 Armor");
		lore.add("");
		lore.add(ChatColor.GRAY+"When in Off Hand:");
		lore.add(ChatColor.BLUE+" +2 Armor");
		meta.setLore(lore);
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "ravagerhead");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentravagerhead");
		meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.HAND));
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 2, Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND));
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		RavagerHead=item;
	}
	
	private static void createRainBow() {
		ItemStack item = new ItemStack(Material.BOW, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#4b6bd7")+"RainBow");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"A bow that rains down arrows");
		lore.add(ChatColor.GRAY+"at the specified location.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Arrow Lands:");
		lore.add(ChatColor.GRAY+" 5 arrows rain from the skies.");
		meta.setLore(lore);
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "rain bow");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentrainbow");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		RainBow=item;
	}
	
	private static void createRainbowBow() {
		ItemStack item = new ItemStack(Material.BOW, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#2f3fd0")+"Rainbow Bow");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"A bow that rains down arrows");
		lore.add(ChatColor.GRAY+"at the specified location.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Arrow Lands:");
		lore.add(ChatColor.GRAY+" 15 Arrows rain from the sky");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Left Clicked:");
		lore.add(ChatColor.GRAY+" Changes current target");
		lore.add(ChatColor.GRAY+" Target: All");
		meta.setLore(lore);
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "rainbow bow");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "equipmentrainbowbow");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Target"), PersistentDataType.STRING, "All");
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		RainbowBow=item;
		EnchantedWorkbenchRecipe recipe = new EnchantedWorkbenchRecipe("rainbow bow", item);
		recipe.addIngredient(RainBow, 2);
		recipe.addIngredient(IngredientManager.AncientFur, 256);
		recipe.addIngredient(IngredientManager.RavagerSkin, 128);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
	}
	
	private static void createIndustrialSawCatalyst() {
		ItemStack item = new ItemStack(Material.STONECUTTER, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#ecdaa7")+"Industrial Saw Catalyst");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"Use on the base block of the");
		lore.add(ChatColor.GRAY+"Industrial Saw to activate the multiblock.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "industrialsawcatalyst");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "machineindustrialsawcatalyst");
		item.setItemMeta(meta);
		IndustrialSawCatalyst=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("industrialsawcatalystcatalyst"), item);
		sr.shape(" b ",
				 "zcz", 
				 "ooo");
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.CondensedIronIngot));
		sr.setIngredient('c', Material.STONECUTTER);
		sr.setIngredient('b', new RecipeChoice.ExactChoice(ItemManager.MultiBlockHammer));
		sr.setIngredient('o', new RecipeChoice.MaterialChoice(new ArrayList<>(Arrays.asList(
				Material.ACACIA_LOG, Material.BIRCH_LOG, Material.DARK_OAK_LOG, Material.JUNGLE_LOG,
				Material.OAK_LOG, Material.SPRUCE_LOG, Material.CRIMSON_STEM, Material.WARPED_STEM))));
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("industrialsawcatalyst", 0, new ArrayList(array));
		array.clear();
	}
	
	private static void createEnchantedWorkbenchCatalyst() {
		ItemStack item = new ItemStack(Material.ENCHANTING_TABLE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#f40078")+"Enchanted Workbench Catalyst");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"Use on the base block of the");
		lore.add(ChatColor.GRAY+"Enchanted WorkBench to activate the multiblock.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "enchantedworkbenchcatalyst");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "machineenchantedworkbenchcatalyst");
		item.setItemMeta(meta);
		EnchantedWorkbenchCatalyst=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("enchantedworkbenchcatalyst"), item);
		sr.shape("yby",
				 "wzw", 
				 "ooo");
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.UltimateModularCasing));
		sr.setIngredient('y', new RecipeChoice.ExactChoice(IngredientManager.CondensedDiamond));
		sr.setIngredient('w', new RecipeChoice.ExactChoice(IngredientManager.CondensedLapis));
		sr.setIngredient('b', new RecipeChoice.ExactChoice(ItemManager.MultiBlockHammer));
		sr.setIngredient('o', Material.OBSIDIAN);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("enchantedworkbenchcatalyst", 0, new ArrayList(array));
		array.clear();
	}
	
	private static void createWeaponryCatalyst() {
		ItemStack item = new ItemStack(Material.SMITHING_TABLE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#b64432")+"Weaponry Catalyst");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"Use on the base block of the");
		lore.add(ChatColor.GRAY+"Weaponry to activate the multiblock.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "weaponrycatalyst");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "machineweaponrycatalyst");
		item.setItemMeta(meta);
		WeaponryCatalyst=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("weaponrycatalyst"), item);
		sr.shape("xbx",
				 "zsz", 
				 "xxx");
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.PerfectSteelIngot));
		sr.setIngredient('b', new RecipeChoice.ExactChoice(ItemManager.MultiBlockHammer));
		sr.setIngredient('s', Material.SMITHING_TABLE);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("weaponrycatalyst", 0, new ArrayList(array));
		array.clear();
	}
	
	private static void createAutomaticGardenCatalyst() {
		ItemStack item = new ItemStack(Material.FARMLAND, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#3bce22")+"Automatic Garden Catalyst");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"Use on the base block of the");
		lore.add(ChatColor.GRAY+"Automatic Garden to activate the multiblock.");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "automaticgardencatalyst");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "machineautomaticgardencatalyst");
		item.setItemMeta(meta);
		AutomaticGardenCatalyst=item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("automaticgardencatalyst"), item);
		sr.shape("szs",
				 "sbs", 
				 "xzx");
		sr.setIngredient('z', new RecipeChoice.ExactChoice(IngredientManager.AdvancedModularCasing));
		sr.setIngredient('x', new RecipeChoice.ExactChoice(IngredientManager.PerfectSteelIngot));
		sr.setIngredient('b', new RecipeChoice.ExactChoice(ItemManager.MultiBlockHammer));
		sr.setIngredient('s', Material.GLASS);
		Bukkit.getServer().addRecipe(sr);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("CraftingTable");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("automaticgardencatalyst", 0, new ArrayList(array));
		array.clear();
	}
	
	private static void createPistol() {
		ItemStack item = new ItemStack(Material.WOODEN_HOE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#fffff0")+"Pistol");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"A basic pistol");
		lore.add(ChatColor.GRAY+"Uses 9mm ammo.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Right Clicked:");
		lore.add(ChatColor.GRAY+" Shoots bullet");
		lore.add(ChatColor.DARK_GREEN+" 4 Attack Damage");
		lore.add(ChatColor.WHITE+" -2 Invincibility Frames");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Left Clicked:");
		lore.add(ChatColor.GRAY+" Reload Ammo");
		meta.setLore(lore);
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "pistol");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "gunPistol");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "InvFramesMainHand"), PersistentDataType.INTEGER, -2);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER, 0);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		Pistol=item;
	}
	
	private static void createUzi() {
		ItemStack item = new ItemStack(Material.WOODEN_HOE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#fffff0")+"Uzi");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"An uzi");
		lore.add(ChatColor.GRAY+"Uses 9mm ammo.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Right Clicked:");
		lore.add(ChatColor.GRAY+" Shoots bullet");
		lore.add(ChatColor.DARK_GREEN+" 3 Attack Damage");
		lore.add(ChatColor.WHITE+" -5 Invincibility Frames");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Left Clicked:");
		lore.add(ChatColor.GRAY+" Reload Ammo");
		meta.setLore(lore);
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "uzi");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "gunUzi");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "InvFramesMainHand"), PersistentDataType.INTEGER, -5);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER, 0);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		Uzi=item;
	}
	
	private static void createRevolver() {
		ItemStack item = new ItemStack(Material.WOODEN_HOE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#fffff0")+"Revolver");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"A revolver");
		lore.add(ChatColor.GRAY+"Uses 500 Magnum ammo.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Right Clicked:");
		lore.add(ChatColor.GRAY+" Shoots bullet");
		lore.add(ChatColor.DARK_GREEN+" 5 Attack Damage");
		lore.add(ChatColor.WHITE+" Double damage if headshot");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Left Clicked:");
		lore.add(ChatColor.GRAY+" Reload Ammo");
		meta.setLore(lore);
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "revolver");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "gunRevolver");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER, 0);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		Revolver=item;
	}
	
	private static void createBarrettM82() {
		ItemStack item = new ItemStack(Material.GOLDEN_HOE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#fffff0")+"Barret M82");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"A sniper rifle");
		lore.add(ChatColor.GRAY+"Uses 7.62mm ammo.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Right Clicked:");
		lore.add(ChatColor.GRAY+" Shoots bullet");
		lore.add(ChatColor.DARK_GREEN+" 10 Attack Damage");
		lore.add(ChatColor.WHITE+" Double damage if headshot");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Left Clicked:");
		lore.add(ChatColor.GRAY+" Reload Ammo");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Left Clicked while sneaking:");
		lore.add(ChatColor.GRAY+" Scope");
		meta.setLore(lore);
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "BarrettM82");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "gunSniperRifle01");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER, 0);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		BarrettM82=item;
	}
	
	private static void createSexPistols() {
		ItemStack item = new ItemStack(Material.NETHERITE_HOE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#bf0fcb")+"Sex Pistols");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"A magical revolver");
		lore.add(ChatColor.GRAY+"Uses 500 Magnum ammo.");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Right Clicked:");
		lore.add(ChatColor.GRAY+" Shoots bullet");
		lore.add(ChatColor.DARK_GREEN+" 4 Attack Damage");
		lore.add(ChatColor.WHITE+" Homes towards set target");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Left Clicked:");
		lore.add(ChatColor.GRAY+" Reload Ammo");
		lore.add("");
		lore.add(ChatColor.GRAY+"When Left Clicked while sneaking:");
		lore.add(ChatColor.GRAY+" Sets Target");
		lore.add(ChatColor.GRAY+" Target: None");
		meta.setLore(lore);
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "sexpistols");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "gunSexPistols");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER, 0);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Target"), PersistentDataType.INTEGER, -1);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		SexPistols=item;
		EnchantedWorkbenchRecipe recipe = new EnchantedWorkbenchRecipe("sexpistols", item);
		recipe.addIngredient(Revolver, 1);
		recipe.addIngredient(IngredientManager.RefinedSkeletalRemains, 8);
		recipe.addIngredient(IngredientManager.PerfectDiamond, 2);
		recipe.addIngredient(IngredientManager.PerfectSteelIngot, 32);
		EnchantedWorkBenchInventory.RecipeList.put(recipe.getRecipeID(), recipe);
	}
	
	private static void createGunAmmo() {
		ItemStack item = new ItemStack(Material.BRICK, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#23a221")+"Pistol Ammo Magazine");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"Contains 16 bullets of 9mm ammo,");
		lore.add(ChatColor.GRAY+"Used primarily by pistols.");
		meta.setLore(lore);
		lore.clear();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "9mmAmmo");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "Ammo1");
		item.setItemMeta(meta);
		GunAmmo[0]=item;
		//
		item = new ItemStack(Material.IRON_INGOT, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#23a221")+"Revolver Ammo Magazine");
		lore.add(ChatColor.GRAY+"Contains 6 bullets of 500 Magnum ammo,");
		lore.add(ChatColor.GRAY+"Used primarily by revolvers.");
		meta.setLore(lore);
		lore.clear();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "revolverAmmo");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "Ammo2");
		item.setItemMeta(meta);
		GunAmmo[1]=item;
		//
		item = new ItemStack(Material.COPPER_INGOT, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#23a221")+"Sniper Rifle Ammo Magazine");
		lore.add(ChatColor.GRAY+"Contains 2 bullets of 7.62mm ammo,");
		lore.add(ChatColor.GRAY+"Used primarily by sniper rifles.");
		meta.setLore(lore);
		lore.clear();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "sniperAmmo");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "Ammo3");
		item.setItemMeta(meta);
		GunAmmo[2]=item;
		//
		item = new ItemStack(Material.GOLD_INGOT, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#23a221")+"Rifle Ammo Magazine");
		lore.add(ChatColor.GRAY+"Contains 24 bullets of caliber 5.56 ammo,");
		lore.add(ChatColor.GRAY+"Used primarily by rifles.");
		meta.setLore(lore);
		lore.clear();
		meta.setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "rifleAmmo");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "Ammo4");
		item.setItemMeta(meta);
		GunAmmo[3]=item;
		//
	}
	
	private static void createSniperSet() {
		ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
		bootsMeta.setColor(Color.fromRGB(40, 148, 108));
		bootsMeta.setDisplayName(ChatColor.WHITE+"Sniper's Boots");
		List<String> lore = new ArrayList<>();
		lore.add("§7A piece of the Sniper's set.");
		bootsMeta.setLore(lore);
		bootsMeta.setUnbreakable(true);
		bootsMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		bootsMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "sniperboots");
		bootsMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "armorSniper0");
		bootsMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		bootsMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		bootsMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
		bootsMeta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
		bootsMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackresistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		bootsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		bootsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		bootsMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movementspeed", 0.01, Operation.ADD_NUMBER, EquipmentSlot.FEET));
		boots.setItemMeta(bootsMeta);
		SniperSet[0]=boots;
		//
		ItemStack leggings = new ItemStack(boots);
		leggings.setType(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
		leggingsMeta.setDisplayName(ChatColor.WHITE+"Sniper's Leggings");
		leggingsMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "sniperleggings");
		leggingsMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "armorSniper1");
		leggingsMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		leggingsMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		leggingsMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
		leggingsMeta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
		leggingsMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackresistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		leggingsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 1, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		leggingsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 4, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		leggingsMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movementspeed", 0.01, Operation.ADD_NUMBER, EquipmentSlot.LEGS));
		leggings.setItemMeta(leggingsMeta);
		SniperSet[1]=leggings;
		//
		ItemStack chestplate = new ItemStack(boots);
		LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
		chestplate.setType(Material.LEATHER_CHESTPLATE);
		chestplateMeta.setDisplayName(ChatColor.WHITE+"Sniper's Chestplate");
		chestplateMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "sniperchestplate");
		chestplateMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "armorSniper2");
		chestplateMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		chestplateMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		chestplateMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
		chestplateMeta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
		chestplateMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackresistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		chestplateMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 1, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		chestplateMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 6, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		chestplateMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movementspeed", 0.01, Operation.ADD_NUMBER, EquipmentSlot.CHEST));
		chestplate.setItemMeta(chestplateMeta);
		SniperSet[2]=chestplate;
		//
		ItemStack headset = new ItemStack(boots);
		LeatherArmorMeta headsetMeta = (LeatherArmorMeta) headset.getItemMeta();
		headset.setType(Material.LEATHER_HELMET);
		headsetMeta.setDisplayName(ChatColor.WHITE+"Sniper's Helmet");
		headsetMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "sniperhelmet");
		headsetMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "armorSniper3");
		headsetMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
		headsetMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
		headsetMeta.removeAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE);
		headsetMeta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
		headsetMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, new AttributeModifier(UUID.randomUUID(), "generic.knockbackresistance", 0.1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		headsetMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armortoughness", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		headsetMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", 1, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		headsetMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.movementspeed", 0.01, Operation.ADD_NUMBER, EquipmentSlot.HEAD));
		headset.setItemMeta(headsetMeta);
		SniperSet[3]=headset;
	}
	
	public static ItemStack createCustomChest(String lootTable) {
		ItemStack item = new ItemStack(Material.CHEST, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Chest");
		List<String> lore = new ArrayList<>();
		lore.add("§7This chest contains loot from a "+lootTable);
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING, lootTable);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack createLootBag(String lootTable, String texture) {
		ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"Loot Bag");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.WHITE+""+ChatColor.BOLD+"Contains the loot of a");
		lore.add(ChatColor.WHITE+lootTable);
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING, lootTable);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING, "lootbag");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER, 0);
		GameProfile profile = new GameProfile(UUID.fromString("419462e1-f285-4390-8058-354e1d7c1ead"), null);
		profile.getProperties().put("textures", new Property("textures", texture));
		Field profileField;
		try {
			profileField = meta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(meta, profile);
		}catch (Exception e) {
			
		}
		item.setItemMeta(meta);
		return item;
	}
}
