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
import me.notbestlord.Plugin.items.ItemManager;
import net.md_5.bungee.api.ChatColor;

public class LiquidMixerEvents implements Listener{
	
	public static Hashtable<String, ArrayList> Recipes = new Hashtable<>();
	
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock() == null) {
				return;
			}
			if(event.getClickedBlock().getType().equals(Material.CAULDRON)) {
				Location MiddleBlockLocation = event.getClickedBlock().getLocation();
				Location TopBlockLocation = event.getClickedBlock().getLocation();
				MiddleBlockLocation.add(0, 1, 0);
				TopBlockLocation.add(0, 2, 0);
				if(event.getPlayer().getWorld().getBlockAt(MiddleBlockLocation).getType().equals(Material.LIGHTNING_ROD)) {
					if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
						event.setCancelled(true);
						if(!(new Machine(event.getClickedBlock().getLocation(), "LiquidMixer").MachineInArray(Main.MachineList))) {
							LiquidMixerInventory.createLiquidMixer(event.getClickedBlock().getLocation());
							Machine machine = new Machine(event.getClickedBlock().getLocation(), LiquidMixerInventory.inventory.get(event.getClickedBlock().getLocation()), "LiquidMixer");
							Main.MachineList.add(machine);
							event.getPlayer().openInventory(LiquidMixerInventory.inventory.get(event.getClickedBlock().getLocation()));
						}
						else {
							event.getPlayer().openInventory(LiquidMixerInventory.inventory.get(event.getClickedBlock().getLocation()));
						}
					}
				}
			}
		}
	}
	@EventHandler
	public static void onBreakBlock(BlockBreakEvent event) {
		if(event.getBlock().getType().equals(Material.CAULDRON)) {
			Location MiddleBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			MiddleBlockLocation.add(0, 1, 0);
			TopBlockLocation.add(0, 2, 0);
			if(event.getPlayer().getWorld().getBlockAt(MiddleBlockLocation).getType().equals(Material.LIGHTNING_ROD)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
					if(LiquidMixerInventory.inventory.containsKey(event.getBlock().getLocation())) {
						LiquidMixerInventory.inventory.remove(event.getBlock().getLocation());
						Machine.DeleteMachineFromList(new Machine(event.getBlock().getLocation(), "LiquidMixer"));
					}
				}
			}
		}
		if(event.getBlock().getType().equals(Material.LIGHTNING_ROD)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.subtract(0, 1, 0);
			TopBlockLocation.add(0, 1, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.CAULDRON)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
					if(LiquidMixerInventory.inventory.containsKey(BaseBlockLocation)) {
						LiquidMixerInventory.inventory.remove(event.getBlock().getLocation());
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "LiquidMixer"));
					}
				}
			}
		}
		if(event.getBlock().getType().equals(Material.IRON_TRAPDOOR)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location MiddleBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.subtract(0, 2, 0);
			MiddleBlockLocation.subtract(0, 1, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.CAULDRON)) {
				if(event.getPlayer().getWorld().getBlockAt(MiddleBlockLocation).getType().equals(Material.LIGHTNING_ROD)) {
					if(LiquidMixerInventory.inventory.containsKey(BaseBlockLocation)) {
						LiquidMixerInventory.inventory.remove(event.getBlock().getLocation());
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "LiquidMixer"));
					}
				}
			}
		}
	}
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(!event.getView().getTitle().equals(ChatColor.AQUA+"Liquid Mixer") || event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
			return;
		}
		if(event.getSlot() != 1 && event.getSlot() != 16 && event.getSlot() != 19) {
			event.setCancelled(true);
		}
		
		Player player = (Player)event.getWhoClicked();
		if(event.getSlot() == 22) {
			player.closeInventory();
		}
		if(event.getSlot() == 13) {
			if(event.getClickedInventory().getItem(1) == null || event.getClickedInventory().getItem(19) == null || event.getClickedInventory().getItem(16) == null) {
				return;
			}
			if(event.getClickedInventory().getItem(16).getAmount() != 1 || event.getClickedInventory().getItem(1).getAmount() != 1 || event.getClickedInventory().getItem(19).getAmount() != 1) {
				player.sendMessage(ChatColor.RED + "Place a SINGLE Tank in each slot");
				return;
			}
			if(!event.getClickedInventory().getItem(16).hasItemMeta() || !event.getClickedInventory().getItem(1).hasItemMeta() || !event.getClickedInventory().getItem(19).hasItemMeta()) {
				player.sendMessage(ChatColor.RED + "Place Tanks in output slots");
				return;
			}
			if(!event.getClickedInventory().getItem(16).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING)) {
				player.sendMessage(ChatColor.RED + "Place Tanks in output slots");
				return;
			}
			if(!event.getClickedInventory().getItem(1).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING)) {
				player.sendMessage(ChatColor.RED + "Place Tanks in output slots");
				return;
			}
			if(!event.getClickedInventory().getItem(19).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING)) {
				player.sendMessage(ChatColor.RED + "Place Tanks in output slots");
				return;
			}
			String key = event.getClickedInventory().getItem(1).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING);
			if(Recipes.containsKey(key)) {
				if(Recipes.get(key).get(1).equals(event.getClickedInventory().getItem(19).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING))) {
					ItemStack input1 = event.getClickedInventory().getItem(1);
					String outputFluidType = (String) Recipes.get(key).get(3);
					int outputFluidMB = (int) Recipes.get(key).get(4);
					ItemStack outputTank = event.getClickedInventory().getItem(16);
					ItemStack inputTank1 = event.getClickedInventory().getItem(1);
					String inputFluidType1 = key;
					String inputFluidType2 = (String) Recipes.get(key).get(1);
					int inputFluidMB1 = (int) Recipes.get(key).get(0);
					int inputFluidMB2 = (int) Recipes.get(key).get(2);
					ItemStack inputTank2 = event.getClickedInventory().getItem(19);
					if(outputTank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING).equals("") || outputTank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING).equals(outputFluidType)) {
						if(outputTank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER) + outputFluidMB <= outputTank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)) {
							if(!inputTank1.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING).equals(inputFluidType1)) {
								return;
							}
							if(!(inputTank1.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER) >= inputFluidMB1)) {
								return;
							}
							if(!inputTank2.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING).equals(inputFluidType2)) {
								return;
							}
							if(!(inputTank2.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER) >= inputFluidMB2)) {
								return;
							}
							SkullMeta tankMeta = (SkullMeta) outputTank.getItemMeta();
							tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, outputFluidType);
							tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER, tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+outputFluidMB);
							List<String> lore = new ArrayList<>();
							lore.addAll(tankMeta.getLore());
							lore.set(2, "§7"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of "+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING)+".");
							tankMeta.setLore(lore);
							lore.clear();
							outputTank.setItemMeta(tankMeta);
							//
							tankMeta = (SkullMeta) inputTank1.getItemMeta();
							tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER, tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)-inputFluidMB1);
							lore.addAll(tankMeta.getLore());
							if(tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER) == 0) {
								tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, "");
								lore.set(2, "§7"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of none.");
							}
							else {
								tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, inputFluidType1);
								lore.set(2, "§7"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of "+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING)+".");
							}
							tankMeta.setLore(lore);
							lore.clear();
							inputTank1.setItemMeta(tankMeta);
							//
							tankMeta = (SkullMeta) inputTank2.getItemMeta();
							tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER, tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)-inputFluidMB2);
							lore.addAll(tankMeta.getLore());
							if(tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER) == 0) {
								tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, "");
								lore.set(2, "§7"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of none.");
							}
							else {
								tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, inputFluidType2);
								lore.set(2, "§7"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of "+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING)+".");
							}
							tankMeta.setLore(lore);
							lore.clear();
							inputTank2.setItemMeta(tankMeta);
							//
							player.playSound(player.getLocation(), Sound.BLOCK_BUBBLE_COLUMN_UPWARDS_INSIDE, 1, 1);
							event.getClickedInventory().setItem(16, outputTank);
					    	event.getClickedInventory().setItem(1, inputTank1);
					    	event.getClickedInventory().setItem(19, inputTank2);
						}
						else {
							player.sendMessage(ChatColor.RED + "Tank is full.");
						}
					}
				}
				else {
					player.sendMessage(ChatColor.RED + "Output tank contains other type of liquid.");
				}
			}
		}
	}
	public static void addAllRecipes() {
		addRecipe("Liquid iron", 288, "Liquid carbon", 80, "Liquid steel", 144);
		ItemStack item = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GRAY+"Liquid steel");
		List<String> lore = new ArrayList<>();
		lore.add("§8Liquid");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidsteel");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "nameextention"), PersistentDataType.STRING, "144mb of ");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidsteel");
		item.setItemMeta(meta);
		ItemStack item2 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);
		ItemMeta meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.WHITE+"288mb of Liquid iron");
		lore.add("§8Liquid");
		meta2.setLore(lore);
		lore.clear();
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidiron");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidiron");
		item2.setItemMeta(meta2);
		ItemStack item3 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		ItemMeta meta3 = item3.getItemMeta();
		meta3.setDisplayName(ChatColor.DARK_GRAY+"80mb of Liquid carbon");
		lore.add("§8Liquid");
		meta3.setLore(lore);
		lore.clear();
		meta3.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidcarbon");
		meta3.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidcarbon");
		item3.setItemMeta(meta3);
		ArrayList array = new ArrayList<>();
		array.add(item);
		array.add("LiquidMixer");
		array.add(item2);
		array.add(null);
		array.add(item3);
		RecipeBookSelectorEvent.addRecipe("liquidsteel", 1, new ArrayList(array));
		array.clear();
		//
		addRecipe("Liquid gelatin", 200, "Liquid glucose", 50, "Liquid marshmallow", 144);
		item = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Liquid marshmallow");
		lore.add("§8Liquid");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidmarshmallow");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "nameextention"), PersistentDataType.STRING, "144mb of ");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidmarshmallow");
		item.setItemMeta(meta);
		item2 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE, 1);
		meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.YELLOW+"50mb of Liquid gelatin");
		lore.add("§8Liquid");
		meta2.setLore(lore);
		lore.clear();
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidgelatin");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidgelatin");
		item2.setItemMeta(meta2);
		item3 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);
		meta3 = item3.getItemMeta();
		meta3.setDisplayName(ChatColor.WHITE+"200mb of Liquid glucose");
		lore.add("§8Liquid");
		meta3.setLore(lore);
		lore.clear();
		meta3.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidglucose");
		meta3.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidglucose");
		item3.setItemMeta(meta3);
		array.add(item);
		array.add("LiquidMixer");
		array.add(item2);
		array.add(null);
		array.add(item3);
		RecipeBookSelectorEvent.addRecipe("liquidmarshmallow", 1, new ArrayList(array));
		array.clear();
		//
		addRecipe("Liquid cocoa", 40, "Liquid glucose", 200, "Liquid chocolate", 144);
		item = new ItemStack(Material.BROWN_STAINED_GLASS_PANE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#5e3100")+"Liquid chocolate");
		lore.add("§8Liquid");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidchocolate");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "nameextention"), PersistentDataType.STRING, "144mb of ");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidchocolate");
		item.setItemMeta(meta);
		item2 = new ItemStack(Material.BROWN_STAINED_GLASS_PANE, 1);
		meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.of("#964e00")+"40mb of Liquid cocoa");
		lore.add("§8Liquid");
		meta2.setLore(lore);
		lore.clear();
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidcocoa");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidcocoa");
		item2.setItemMeta(meta2);
		item3 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);
		meta3 = item3.getItemMeta();
		meta3.setDisplayName(ChatColor.WHITE+"200mb of Liquid glucose");
		lore.add("§8Liquid");
		meta3.setLore(lore);
		lore.clear();
		meta3.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidglucose");
		meta3.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidglucose");
		item3.setItemMeta(meta3);
		array.add(item);
		array.add("LiquidMixer");
		array.add(item2);
		array.add(null);
		array.add(item3);
		RecipeBookSelectorEvent.addRecipe("liquidchocolate", 1, new ArrayList(array));
		array.clear();
		//
		addRecipe("Liquid berries", 200, "Liquid glucose", 200, "Liquid sweet berries", 400);
		item = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Liquid sweet berries");
		lore.add("§8Liquid");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidsweetberries");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "nameextention"), PersistentDataType.STRING, "400mb of ");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidsweetberries");
		item.setItemMeta(meta);
		item2 = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
		meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.RED+"200mb of Liquid berries");
		lore.add("§8Liquid");
		meta2.setLore(lore);
		lore.clear();
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidberries");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidberries");
		item2.setItemMeta(meta2);
		item3 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1);
		meta3 = item3.getItemMeta();
		meta3.setDisplayName(ChatColor.WHITE+"200mb of Liquid glucose");
		lore.add("§8Liquid");
		meta3.setLore(lore);
		lore.clear();
		meta3.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidglucose");
		meta3.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidglucose");
		item3.setItemMeta(meta3);
		array.add(item);
		array.add("LiquidMixer");
		array.add(item2);
		array.add(null);
		array.add(item3);
		RecipeBookSelectorEvent.addRecipe("liquidsweetberries", 1, new ArrayList(array));
		array.clear();
		//
		addRecipe("Liquid sweet berries", 400, "Liquid carbon", 100, "Carbonated sweet berry juice", 500);
		item = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Carbonated sweet berry juice");
		lore.add("§8Liquid");
		meta.setLore(lore);
		lore.clear();
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "carbonatedsweetberryjuice");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "nameextention"), PersistentDataType.STRING, "500mb of ");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidcarbonatedsweetberryjuice");
		item.setItemMeta(meta);
		item2 = new ItemStack(Material.RED_STAINED_GLASS_PANE, 1);
		meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.RED+"400mb of Liquid sweet berries");
		lore.add("§8Liquid");
		meta2.setLore(lore);
		lore.clear();
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidsweetberries");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidsweetberries");
		item2.setItemMeta(meta2);
		item3 = new ItemStack(Material.BLACK_STAINED_GLASS_PANE, 1);
		meta3 = item3.getItemMeta();
		meta3.setDisplayName(ChatColor.GRAY+"100mb of Liquid carbon");
		lore.add("§8Liquid");
		meta3.setLore(lore);
		lore.clear();
		meta3.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidcarbon");
		meta3.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidcarbon");
		item3.setItemMeta(meta3);
		array.add(item);
		array.add("LiquidMixer");
		array.add(item2);
		array.add(null);
		array.add(item3);
		RecipeBookSelectorEvent.addRecipe("carbonatedsweetberryjuice", 1, new ArrayList(array));
		array.clear();
		//
	}
	public static void addRecipe(String fluidTypeInput1, int fluidMBinput1, String fluidTypeInput2, int fluidMBinput2, String fluidTypeOutput, int fluidMBOutput) {
		ArrayList array = new ArrayList<>();
		array.add(fluidMBinput1);
		array.add(fluidTypeInput2);
		array.add(fluidMBinput2);
		array.add(fluidTypeOutput);
		array.add(fluidMBOutput);
		Recipes.put(fluidTypeInput1, new ArrayList(array));
	}
}
