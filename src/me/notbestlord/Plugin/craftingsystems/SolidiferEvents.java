package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

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
import me.notbestlord.Plugin.items.FoodManager;
import me.notbestlord.Plugin.items.IngredientManager;
import me.notbestlord.Plugin.items.ItemManager;
import net.md_5.bungee.api.ChatColor;

public class SolidiferEvents implements Listener{
	
	public static Hashtable<String, ArrayList> Recipes = new Hashtable<>();
	
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock() == null) {
				return;
			}
			if(event.getClickedBlock().getType().equals(Material.WATER_CAULDRON)) {
				Location TopBlockLocation = event.getClickedBlock().getLocation();
				Location BottomBlockLocation = event.getClickedBlock().getLocation();
				TopBlockLocation.add(0,1,0);
				BottomBlockLocation.subtract(0,1,0);
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR) && event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.PACKED_ICE)) {
					event.setCancelled(true);
					if(!(new Machine(event.getClickedBlock().getLocation(), "Solidifer").MachineInArray(Main.MachineList))) {
						SolidiferInventory.createSolidifer(event.getClickedBlock().getLocation());
						Machine machine = new Machine(event.getClickedBlock().getLocation(), SolidiferInventory.inventory.get(event.getClickedBlock().getLocation()), "Solidifer");
						Main.MachineList.add(machine);
						event.getPlayer().openInventory(SolidiferInventory.inventory.get(event.getClickedBlock().getLocation()));
					}
					else {
						event.getPlayer().openInventory(SolidiferInventory.inventory.get(event.getClickedBlock().getLocation()));
					}
				}
			}
		}
	}
	@EventHandler
	public static void onBreakBlock(BlockBreakEvent event) {
		if(event.getBlock().getType().equals(Material.WATER_CAULDRON)) {
			Location TopBlock = event.getBlock().getLocation();
			Location BottomBlock = event.getBlock().getLocation();
			TopBlock.add(0,1,0);
			BottomBlock.subtract(0,1,0);
			if(event.getBlock().getWorld().getBlockAt(TopBlock).getType().equals(Material.IRON_TRAPDOOR) && event.getBlock().getWorld().getBlockAt(BottomBlock).getType().equals(Material.PACKED_ICE)) {
				if(SolidiferInventory.inventory.containsKey(event.getBlock().getLocation())) {
					SolidiferInventory.inventory.remove(event.getBlock().getLocation());
					Machine.DeleteMachineFromList(new Machine(event.getBlock().getLocation(), "Solidifer"));
				}
			}
		}
		if(event.getBlock().getType().equals(Material.IRON_TRAPDOOR)) {
			Location BaseBlock = event.getBlock().getLocation();
			Location BottomBlock = event.getBlock().getLocation();
			BaseBlock.subtract(0,1,0);
			BottomBlock.subtract(0,2,0);
			if(event.getBlock().getWorld().getBlockAt(BaseBlock).getType().equals(Material.WATER_CAULDRON) && event.getBlock().getWorld().getBlockAt(BottomBlock).getType().equals(Material.PACKED_ICE)) {
				if(SolidiferInventory.inventory.containsKey(BaseBlock)) {
					SolidiferInventory.inventory.remove(BaseBlock);
					Machine.DeleteMachineFromList(new Machine(BaseBlock, "Solidifer"));
				}
			}
		}
		if(event.getBlock().getType().equals(Material.PACKED_ICE)) {
			Location BaseBlock = event.getBlock().getLocation();
			Location TopBlock = event.getBlock().getLocation();
			BaseBlock.add(0,1,0);
			TopBlock.add(0,2,0);
			if(event.getBlock().getWorld().getBlockAt(BaseBlock).getType().equals(Material.WATER_CAULDRON) && event.getBlock().getWorld().getBlockAt(TopBlock).getType().equals(Material.IRON_TRAPDOOR)) {
				if(SolidiferInventory.inventory.containsKey(BaseBlock)) {
					SolidiferInventory.inventory.remove(BaseBlock);
					Machine.DeleteMachineFromList(new Machine(BaseBlock, "Solidifer"));
				}
			}
		}
	}
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(!event.getView().getTitle().equals(ChatColor.AQUA+"Solidifier") || event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
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
			if(event.getClickedInventory().getItem(10).getAmount() != 1) {
				player.sendMessage(ChatColor.RED + "Place a SINGLE Tank in input slot");
				return;
			}
			if(!event.getClickedInventory().getItem(10).hasItemMeta()) {
				player.sendMessage(ChatColor.RED + "Place Tank in input slot");
				return;
			}
			if(!event.getClickedInventory().getItem(10).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING)) {
				player.sendMessage(ChatColor.RED + "Place Tank in input slot");
				return;
			}
			ItemStack tank = new ItemStack(event.getClickedInventory().getItem(10));
			if(!tank.hasItemMeta()) {
				return;
			}
			if(!tank.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING)) {
				return;
			}
			String key = tank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING);
			if(Recipes.containsKey(key)) {
				int inputFluidMB = (int) Recipes.get(key).get(0);
				ItemStack outputItem = (ItemStack) Recipes.get(key).get(1);
				if(!(event.getClickedInventory().getItem(16) == null)) {
					if(!outputItem.getType().equals(event.getClickedInventory().getItem(16).getType())) {
						player.sendMessage(ChatColor.RED + "Empty output slot.");
						return;
					}
					if(event.getClickedInventory().getItem(16).getAmount() == 64) {
						player.sendMessage(ChatColor.RED + "Output slot full.");
						return;
					}
				}
				if(tank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING).equals(key)) {
					if(tank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)-inputFluidMB >= 0) {
						SkullMeta tankMeta = (SkullMeta) tank.getItemMeta();
						if(tank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)-inputFluidMB == 0) {
							tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, "");
						}
						else {
							tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, key);
						}
						tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER, tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)-inputFluidMB);
						List<String> lore = new ArrayList<>();
						lore.addAll(tankMeta.getLore());
						if(tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING).equals("")) {
							lore.set(2, "§7"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of none.");
						}
						else {
							lore.set(2, "§7"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of "+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING)+".");
						}
						tankMeta.setLore(lore);
						tank.setItemMeta(tankMeta);
						player.playSound(player.getLocation(), Sound.AMBIENT_UNDERWATER_EXIT, 1, 1);
						event.getClickedInventory().setItem(10, tank);
						if(event.getClickedInventory().getItem(16) == null) {
							event.getClickedInventory().setItem(16, outputItem);;
						}
						else if(outputItem.getType().equals(event.getClickedInventory().getItem(16).getType())){
							event.getClickedInventory().getItem(16).setAmount(event.getClickedInventory().getItem(16).getAmount()+1);;
						}
					}
					else {
						player.sendMessage(ChatColor.RED + "Tank is full.");
					}
				}
				else {
					player.sendMessage(ChatColor.RED + "Tank contains other type of liquid.");
				}
			}
		}
	}
	public static void addAllRecipes() {
		addRecipe("Liquid iron", 144, new ItemStack(Material.IRON_INGOT, 1));
		ItemStack item = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"144mb of Liquid iron");
		List<String> lore = new ArrayList<>();
		lore.add("§8Liquid");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidiron");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidiron");
		item.setItemMeta(meta);
		ItemStack item2 = new ItemStack(Material.IRON_INGOT);
		ItemMeta meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.WHITE+"Iron Ingot");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "ironingot");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "vanillaingotiron");
		item2.setItemMeta(meta2);
		ArrayList array = new ArrayList<>();
		array.add(item2);
		array.add("Solidifier");
		array.add(item);
		RecipeBookSelectorEvent.addRecipe("ironingot", 1, new ArrayList(array));
		array.clear();
		//
		addRecipe("Liquid gold", 144, new ItemStack(Material.GOLD_INGOT, 1));
		item = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.YELLOW+"144mb of Liquid gold");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidgold");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidgold");
		item.setItemMeta(meta);
		item2 = new ItemStack(Material.GOLD_INGOT);
		meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.WHITE+"Gold Ingot");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "goldingot");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "vanillaingotgold");
		item2.setItemMeta(meta2);
		array.add(item2);
		array.add("Solidifier");
		array.add(item);
		RecipeBookSelectorEvent.addRecipe("goldingot", 1, new ArrayList(array));
		array.clear();
		//
		addRecipe("Liquid copper", 144, new ItemStack(Material.COPPER_INGOT, 1));
		item = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"144mb of Liquid copper");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidcopper");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidcopper");
		item.setItemMeta(meta);
		item2 = new ItemStack(Material.COPPER_INGOT);
		meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.WHITE+"Copper Ingot");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "copperingot");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "vanillaingotcopper");
		item2.setItemMeta(meta2);
		array.add(item2);
		array.add("Solidifier");
		array.add(item);
		RecipeBookSelectorEvent.addRecipe("copperingot", 1, new ArrayList(array));
		array.clear();
		//
		addRecipe("Liquid emerald", 144, new ItemStack(Material.EMERALD, 1));
		item = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN+"144mb of Liquid emerald");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidemerald");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidemerald");
		item.setItemMeta(meta);
		item2 = new ItemStack(Material.EMERALD);
		meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.WHITE+"Emerald");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "emerald");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "vanillaemerald");
		item2.setItemMeta(meta2);
		array.add(item2);
		array.add("Solidifier");
		array.add(item);
		RecipeBookSelectorEvent.addRecipe("emerald", 1, new ArrayList(array));
		array.clear();
		//
		addRecipe("Liquid steel", 144, IngredientManager.SteelIngot);
		item = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GRAY+"144mb of Liquid steel");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidsteel");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidsteel");
		item.setItemMeta(meta);
		array.add(IngredientManager.SteelIngot);
		array.add("Solidifier");
		array.add(item);
		RecipeBookSelectorEvent.addRecipe("steelingot", 1, new ArrayList(array));
		array.clear();
		//
		addRecipe("Liquid marshmallow", 144, FoodManager.Marshmallow);
		item = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"144mb of Liquid marshmallow");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidmarshmallow");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidmarshmallow");
		item.setItemMeta(meta);
		array.add(FoodManager.Marshmallow);
		array.add("Solidifier");
		array.add(item);
		RecipeBookSelectorEvent.addRecipe("marshmallow", 1, new ArrayList(array));
		array.clear();
		//
		addRecipe("Liquid chocolate", 144, FoodManager.Chocolate);
		item = new ItemStack(Material.BROWN_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#964e00")+"144mb of Liquid chocolate");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidchocolate");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidchocolate");
		item.setItemMeta(meta);
		array.add(FoodManager.Chocolate);
		array.add("Solidifier");
		array.add(item);
		RecipeBookSelectorEvent.addRecipe("chocolate", 1, new ArrayList(array));
		array.clear();
		//
	}
	public static void addRecipe(String fluidTypeInput, int fluidMBInput, ItemStack outputItem) {
		ArrayList arr = new ArrayList<>();
		arr.add(fluidMBInput);
		arr.add(outputItem);
		Recipes.put(fluidTypeInput, new ArrayList(arr));
	}
}
