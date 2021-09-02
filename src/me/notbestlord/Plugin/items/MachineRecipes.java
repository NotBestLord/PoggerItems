package me.notbestlord.Plugin.items;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.craftingsystems.RecipeBookSelectorEvent;
import net.md_5.bungee.api.ChatColor;

public class MachineRecipes {
	
	public static ItemStack Melter;
	public static ItemStack Solidifier;
	public static ItemStack LiquidMixer;
	public static ItemStack OilExtractor;
	public static ItemStack OilProcessor;
	public static ItemStack FluidInfuser;
	public static ItemStack VoidOreMiner;
	public static ItemStack IndustrialSaw;
	public static ItemStack EnchantedWorkBench;
	public static ItemStack Weaponry;
	public static ItemStack AutomaticGarden;
	
	
	
	public static void init() {
		MelterRecipe();
		SolidifierRecipe();
		LiquidMixerRecipe();
		OilExtractorRecipe();
		OilProcessorRecipe();
		FluidInfuserRecipe();
		VoidOreMinerRecipe();
		IndustrialSawRecipe();
		EnchantedWorkBenchRecipe();
		WeaponryRecipe();
		AutomaticGardenRecipe();
	}
	
	private static void MelterRecipe() {
		ItemStack item = new ItemStack(Material.CAULDRON);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED+""+ChatColor.BOLD+"Melter");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING, "melter");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "stage2melter");
		item.setItemMeta(meta);
		Melter = item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("melter"), item);
		sr.shape("   ",
				 " t ", 
				 " cl");
		sr.setIngredient('c', Material.CAULDRON);
		sr.setIngredient('l', Material.LAVA_BUCKET);
		sr.setIngredient('t', Material.IRON_TRAPDOOR);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("MultiBlock");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("melter", 2, new ArrayList(array));
		array.clear();
	}
	private static void SolidifierRecipe() {
		ItemStack item = new ItemStack(Material.CAULDRON);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA+""+ChatColor.BOLD+"Solidifier");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING, "solidifier");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "stage2solidifier");
		item.setItemMeta(meta);
		Solidifier = item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("solidifier"), item);
		sr.shape(" t ",
				 " cw", 
				 " i ");
		sr.setIngredient('c', Material.CAULDRON);
		sr.setIngredient('w', Material.WATER_BUCKET);
		sr.setIngredient('i', Material.PACKED_ICE);
		sr.setIngredient('t', Material.IRON_TRAPDOOR);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("MultiBlock");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("solidifier", 2, new ArrayList(array));
		array.clear();
	}
	private static void LiquidMixerRecipe() {
		ItemStack item = new ItemStack(Material.CAULDRON);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+""+ChatColor.BOLD+"Liquid Mixer");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING, "liquidmixer");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "stage2liquidmixer");
		item.setItemMeta(meta);
		LiquidMixer = item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("liquidmixer"), item);
		sr.shape(" t ",
				 " l ", 
				 " c ");
		sr.setIngredient('c', Material.CAULDRON);
		sr.setIngredient('l', Material.LIGHTNING_ROD);
		sr.setIngredient('t', Material.IRON_TRAPDOOR);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("MultiBlock");
		array.add(sr);
		RecipeBookSelectorEvent.addRecipe("liquidmixer", 2, new ArrayList(array));
		array.clear();
	}
	private static void OilExtractorRecipe() {
		ItemStack item = new ItemStack(Material.CAULDRON);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+""+ChatColor.BOLD+"Oil Extractor");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING, "oilextractor");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "stage3oilextractor");
		item.setItemMeta(meta);
		OilExtractor = item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("oilextractor"), item);
		sr.shape(" t ",
				 " h ", 
				 " f ");
		sr.setIngredient('t', Material.IRON_TRAPDOOR);
		sr.setIngredient('h', Material.HOPPER);
		sr.setIngredient('f', Material.NETHER_BRICK_FENCE);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("MultiBlock");
		array.add(sr);
		array.add(ItemManager.MultiBlockHammer);
		RecipeBookSelectorEvent.addRecipe("oilextractor", 2, new ArrayList(array));
		array.clear();
	}
	private static void OilProcessorRecipe() {
		ItemStack item = new ItemStack(Material.CAULDRON);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+""+ChatColor.BOLD+"Oil Processor");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING, "oilprocessor");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "stage3oilprocessor");
		item.setItemMeta(meta);
		OilProcessor = item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("oilprocessor"), item);
		sr.shape(" t ",
				 " c ", 
				 " c ");
		sr.setIngredient('c', Material.CAULDRON);
		sr.setIngredient('t', Material.IRON_TRAPDOOR);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("MultiBlock");
		array.add(sr);
		array.add(ItemManager.MultiBlockHammer);
		RecipeBookSelectorEvent.addRecipe("oilprocessor", 2, new ArrayList(array));
		array.clear();
	}
	private static void FluidInfuserRecipe() {
		ItemStack item = new ItemStack(Material.CAULDRON);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+""+ChatColor.BOLD+"Fluid Infuser");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING, "fluidinfuser");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "stage3fluidinfuser");
		item.setItemMeta(meta);
		FluidInfuser = item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("fluidinfuser"), item);
		sr.shape(" t ",
				 " h ", 
				 " d ");
		sr.setIngredient('t', Material.IRON_TRAPDOOR);
		sr.setIngredient('h', Material.HOPPER);
		sr.setIngredient('d', Material.DISPENSER);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("MultiBlock");
		array.add(sr);
		array.add(ItemManager.MultiBlockHammer);
		RecipeBookSelectorEvent.addRecipe("fluidinfuser", 2, new ArrayList(array));
		array.clear();
	}
	private static void VoidOreMinerRecipe() {
		ItemStack item = new ItemStack(Material.BEACON);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+""+ChatColor.BOLD+"Void Ore Miner");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING, "voidoreminer");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "stage5voidoreminer");
		item.setItemMeta(meta);
		VoidOreMiner = item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("voidoreminer"), item);
		sr.shape(" d ",
				 " a ", 
				 " b ");
		sr.setIngredient('d', Material.DISPENSER);
		sr.setIngredient('a', Material.BEACON);
		sr.setIngredient('b', Material.BARREL);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("MultiBlock");
		array.add(sr);
		array.add(ItemManager.RadiatedMultiBlockHammer);
		RecipeBookSelectorEvent.addRecipe("voidoreminer", 2, new ArrayList(array));
		array.clear();
	}
	private static void IndustrialSawRecipe() {
		ItemStack item = new ItemStack(Material.STONECUTTER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#f1e3be")+""+ChatColor.BOLD+"Industrial Saw");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING, "industrialsaw");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "stage2industrialsaw");
		item.setItemMeta(meta);
		IndustrialSaw = item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("industrialsaw"), item);
		sr.shape("   ",
				 " a ", 
				 " b ");
		sr.setIngredient('b', Material.DISPENSER);
		sr.setIngredient('a', Material.STONECUTTER);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("MultiBlock");
		array.add(sr);
		array.add(ItemManager.IndustrialSawCatalyst);
		RecipeBookSelectorEvent.addRecipe("industrialsaw", 2, new ArrayList(array));
		array.clear();
	}
	private static void EnchantedWorkBenchRecipe() {
		ItemStack item = new ItemStack(Material.ENCHANTING_TABLE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#ff47df")+""+ChatColor.BOLD+"Enchanted Workbench");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING, "enchantedworkbench");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "stage5enchantedworkbench");
		item.setItemMeta(meta);
		EnchantedWorkBench = item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("enchantedworkbench"), item);
		sr.shape(" e ",
				 " c ", 
				 " b ");
		sr.setIngredient('b', Material.BEACON);
		sr.setIngredient('c', Material.CRAFTING_TABLE);
		sr.setIngredient('e', Material.ENCHANTING_TABLE);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("MultiBlock");
		array.add(sr);
		array.add(ItemManager.EnchantedWorkbenchCatalyst);
		RecipeBookSelectorEvent.addRecipe("enchantedworkbench", 2, new ArrayList(array));
		array.clear();
	}
	private static void WeaponryRecipe() {
		ItemStack item = new ItemStack(Material.SMITHING_TABLE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#d06048")+""+ChatColor.BOLD+"Weaponry");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING, "weaponry");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "stage5weaponry");
		item.setItemMeta(meta);
		Weaponry = item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("weaponry"), item);
		sr.shape(" e ",
				 " c ", 
				 " b ");
		sr.setIngredient('b', Material.BEACON);
		sr.setIngredient('c', Material.SMITHING_TABLE);
		sr.setIngredient('e', Material.IRON_TRAPDOOR);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("MultiBlock");
		array.add(sr);
		array.add(ItemManager.WeaponryCatalyst);
		RecipeBookSelectorEvent.addRecipe("weaponry", 2, new ArrayList(array));
		array.clear();
	}
	private static void AutomaticGardenRecipe() {
		ItemStack item = new ItemStack(Material.FLOWER_POT);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#4fde36")+""+ChatColor.BOLD+"Automatic Garden");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING, "automaticgarden");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "stage3automaticgarden");
		item.setItemMeta(meta);
		AutomaticGarden = item;
		ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("automaticgarden"), item);
		sr.shape(" e ",
				 " c ", 
				 " b ");
		sr.setIngredient('b', Material.DISPENSER);
		sr.setIngredient('c', Material.GLASS);
		sr.setIngredient('e', Material.IRON_TRAPDOOR);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("MultiBlock");
		array.add(sr);
		array.add(ItemManager.AutomaticGardenCatalyst);
		RecipeBookSelectorEvent.addRecipe("automaticgarden", 2, new ArrayList(array));
		array.clear();
	}
	
}
