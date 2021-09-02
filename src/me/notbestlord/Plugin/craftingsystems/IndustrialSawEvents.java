package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.dataManagment.Machine;
import me.notbestlord.Plugin.items.IngredientManager;
import me.notbestlord.Plugin.items.ItemManager;
import net.md_5.bungee.api.ChatColor;

public class IndustrialSawEvents implements Listener{
	
	public static Hashtable<ItemStack, ItemStack> Recipes = new Hashtable<>();
	
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock() == null) {
				return;
			}
			if(event.getClickedBlock().getType().equals(Material.STONECUTTER)) {
				Location LowerBlockLocation = event.getClickedBlock().getLocation();
				LowerBlockLocation.add(0, -1, 0);
				if(event.getPlayer().getWorld().getBlockAt(LowerBlockLocation).getType().equals(Material.DISPENSER)) {
					event.setCancelled(true);
					if(IndustrialSawInventory.inventory.containsKey(event.getClickedBlock().getLocation())) {
						event.getPlayer().openInventory(IndustrialSawInventory.inventory.get(event.getClickedBlock().getLocation()));
					}
					else {
						IndustrialSawInventory.createIndustrialSaw(event.getClickedBlock().getLocation());
					}
					if(!(new Machine(event.getClickedBlock().getLocation(), "Industrial Saw").MachineInArray(Main.MachineList))) {
						if (event.getPlayer().getInventory().getItemInMainHand().equals(ItemManager.IndustrialSawCatalyst)){
							IndustrialSawInventory.createIndustrialSaw(event.getClickedBlock().getLocation());
							Machine machine = new Machine(event.getClickedBlock().getLocation(), IndustrialSawInventory.inventory.get(event.getClickedBlock().getLocation()), "Industrial Saw");
							Main.MachineList.add(machine);
							event.getPlayer().openInventory(IndustrialSawInventory.inventory.get(event.getClickedBlock().getLocation()));
							event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
						}
					}
					else {
						event.getPlayer().openInventory(IndustrialSawInventory.inventory.get(event.getClickedBlock().getLocation()));
					}
				}
			}
		}
	}
	@EventHandler
	public static void onBreakBlock(BlockBreakEvent event) {
		if(event.getBlock().getType().equals(Material.STONECUTTER)) {
			Location LowerBlock = event.getBlock().getLocation();
			LowerBlock.add(0,-1,0);
			if(event.getBlock().getWorld().getBlockAt(LowerBlock).getType().equals(Material.DISPENSER)) {
				if(IndustrialSawInventory.inventory.containsKey(event.getBlock().getLocation())) {
					IndustrialSawInventory.inventory.remove(event.getBlock().getLocation());
					Machine.DeleteMachineFromList(new Machine(event.getBlock().getLocation(), "Industrial Saw"));
					event.getPlayer().getInventory().addItem(ItemManager.IndustrialSawCatalyst);
				}
			}
		}
		if(event.getBlock().getType().equals(Material.DISPENSER)) {
			Location BaseBlock = event.getBlock().getLocation();
			BaseBlock.add(0,1,0);
			if(event.getBlock().getWorld().getBlockAt(BaseBlock).getType().equals(Material.STONECUTTER)) {
				if(IndustrialSawInventory.inventory.containsKey(BaseBlock)) {
					IndustrialSawInventory.inventory.remove(BaseBlock);
					Machine.DeleteMachineFromList(new Machine(BaseBlock, "Industrial Saw"));
					event.getPlayer().getInventory().addItem(ItemManager.IndustrialSawCatalyst);
				}
			}
		}
		
	}
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(!event.getView().getTitle().equals(ChatColor.AQUA+"Industrial Saw") || event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
			return;
		}
		if(event.getSlot() != 10 && event.getSlot() != 16) {
			event.setCancelled(true);
		}
		
		Player player = (Player)event.getWhoClicked();
		if(event.getSlot() == 22) {
			player.closeInventory();
		}
		if(event.getSlot() == 13) {
			if(event.getClickedInventory().getItem(10) == null) {
				return;
			}
			ItemStack key = new ItemStack(event.getClickedInventory().getItem(10));
			key.setAmount(1);
			if(!key.getType().equals(Material.PLAYER_HEAD)) {
				if(Recipes.containsKey(key)) {
					ItemStack input = event.getClickedInventory().getItem(10);
					ItemStack output = Recipes.get(key);
					if(event.getClickedInventory().getItem(16) != null) {
						if(!event.getClickedInventory().getItem(16).getType().equals(output.getType())) {
							player.sendMessage(ChatColor.RED + "Empty Output Slot.");
							return;
						}
						else {
							if(event.getClickedInventory().getItem(16).getAmount()+output.getAmount()>64) {
								player.sendMessage(ChatColor.RED + "Empty Output Slot.");
								return;
							}else {
								event.getClickedInventory().getItem(16).setAmount(event.getClickedInventory().getItem(16).getAmount()+output.getAmount());
							}
							
						}
					}
					else {
						event.getClickedInventory().setItem(16, output);
					}
					input.setAmount(input.getAmount()-1);
					player.playSound(player.getLocation(), Sound.UI_STONECUTTER_TAKE_RESULT, 1, 1);
				}
			}
		}
	}
	public static void addAllRecipes() {
		Recipes.put(new ItemStack(Material.OAK_LOG), new ItemStack(Material.OAK_PLANKS, 6));
		Recipes.put(new ItemStack(Material.ACACIA_LOG), new ItemStack(Material.ACACIA_PLANKS, 6));
		Recipes.put(new ItemStack(Material.BIRCH_LOG), new ItemStack(Material.BIRCH_PLANKS, 6));
		Recipes.put(new ItemStack(Material.DARK_OAK_LOG), new ItemStack(Material.DARK_OAK_PLANKS, 6));
		Recipes.put(new ItemStack(Material.SPRUCE_LOG), new ItemStack(Material.SPRUCE_PLANKS, 6));
		Recipes.put(new ItemStack(Material.JUNGLE_LOG), new ItemStack(Material.JUNGLE_PLANKS, 6));
		Recipes.put(new ItemStack(Material.CRIMSON_STEM), new ItemStack(Material.CRIMSON_PLANKS, 6));
		Recipes.put(new ItemStack(Material.WARPED_STEM), new ItemStack(Material.WARPED_PLANKS, 6));
		ItemStack item = new ItemStack(Material.OAK_LOG);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"Any Log");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "vanillalog");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "log");
		item.setItemMeta(meta);
		ItemStack item2 = new ItemStack(Material.OAK_PLANKS, 6);
		ItemMeta meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.GOLD+"Any Plank");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "vanillaplanks");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "planks");
		item2.setItemMeta(meta2);
		ArrayList array = new ArrayList<>();
		array.add(item2);
		array.add("IndustrialSaw");
		array.add(item);
		RecipeBookSelectorEvent.addRecipe("planks", 1, new ArrayList(array));
		array.clear();
		//
		Recipes.put(new ItemStack(Material.OAK_PLANKS), new ItemStack(Material.STICK, 3));
		Recipes.put(new ItemStack(Material.ACACIA_PLANKS), new ItemStack(Material.STICK, 3));
		Recipes.put(new ItemStack(Material.BIRCH_PLANKS), new ItemStack(Material.STICK, 3));
		Recipes.put(new ItemStack(Material.DARK_OAK_PLANKS), new ItemStack(Material.STICK, 3));
		Recipes.put(new ItemStack(Material.SPRUCE_PLANKS), new ItemStack(Material.STICK, 3));
		Recipes.put(new ItemStack(Material.JUNGLE_PLANKS), new ItemStack(Material.STICK, 3));
		Recipes.put(new ItemStack(Material.CRIMSON_PLANKS), new ItemStack(Material.STICK, 3));
		Recipes.put(new ItemStack(Material.WARPED_PLANKS), new ItemStack(Material.STICK, 3));
		item = new ItemStack(Material.OAK_PLANKS);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"Any Plank");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "vanillaplanks");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "planks");
		item.setItemMeta(meta);
		item2 = new ItemStack(Material.STICK, 3);
		meta2 = item2.getItemMeta();
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "vanillastick");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "stick");
		item2.setItemMeta(meta2);
		array = new ArrayList<>();
		array.add(item2);
		array.add("IndustrialSaw");
		array.add(item);
		RecipeBookSelectorEvent.addRecipe("stick", 1, new ArrayList(array));
		array.clear();
		//
		Recipes.put(new ItemStack(Material.SUGAR_CANE), new ItemStack(Material.PAPER, 2));
		item = new ItemStack(Material.SUGAR_CANE);
		meta = item.getItemMeta();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "vanillasugarcane");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "sugarcane");
		item.setItemMeta(meta);
		item2 = new ItemStack(Material.PAPER, 2);
		meta2 = item2.getItemMeta();
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "vanillapaper");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "paper");
		item2.setItemMeta(meta2);
		array.add(item2);
		array.add("IndustrialSaw");
		array.add(item);
		RecipeBookSelectorEvent.addRecipe("paper", 1, new ArrayList(array));
		array.clear();
	}
}
