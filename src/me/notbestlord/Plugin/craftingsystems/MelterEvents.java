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

public class MelterEvents implements Listener{
	
	public static Hashtable<ItemStack, ArrayList> Recipes = new Hashtable<>();
	
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock() == null) {
				return;
			}
			if(event.getClickedBlock().getType().equals(Material.LAVA_CAULDRON)) {
				Location TopBlockLocation = event.getClickedBlock().getLocation();
				TopBlockLocation.add(0, 1, 0);
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
					event.setCancelled(true);
					if(MelterInventory.inventory.containsKey(event.getClickedBlock().getLocation())) {
						event.getPlayer().openInventory(MelterInventory.inventory.get(event.getClickedBlock().getLocation()));
					}
					else {
						MelterInventory.createMelter(event.getClickedBlock().getLocation());
					}
					if(!(new Machine(event.getClickedBlock().getLocation(), "Melter").MachineInArray(Main.MachineList))) {
						MelterInventory.createMelter(event.getClickedBlock().getLocation());
						Machine machine = new Machine(event.getClickedBlock().getLocation(), MelterInventory.inventory.get(event.getClickedBlock().getLocation()), "Melter");
						Main.MachineList.add(machine);
						event.getPlayer().openInventory(MelterInventory.inventory.get(event.getClickedBlock().getLocation()));
					}
					else {
						event.getPlayer().openInventory(MelterInventory.inventory.get(event.getClickedBlock().getLocation()));
					}
				}
			}
		}
	}
	@EventHandler
	public static void onBreakBlock(BlockBreakEvent event) {
		if(event.getBlock().getType().equals(Material.LAVA_CAULDRON)) {
			Location TopBlock = event.getBlock().getLocation();
			TopBlock.add(0,1,0);
			if(event.getBlock().getWorld().getBlockAt(TopBlock).getType().equals(Material.IRON_TRAPDOOR)) {
				if(MelterInventory.inventory.containsKey(event.getBlock().getLocation())) {
					MelterInventory.inventory.remove(event.getBlock().getLocation());
					Machine.DeleteMachineFromList(new Machine(event.getBlock().getLocation(), "Melter"));
				}
			}
		}
		if(event.getBlock().getType().equals(Material.IRON_TRAPDOOR)) {
			Location BaseBlock = event.getBlock().getLocation();
			BaseBlock.subtract(0,1,0);
			if(event.getBlock().getWorld().getBlockAt(BaseBlock).getType().equals(Material.LAVA_CAULDRON)) {
				if(MelterInventory.inventory.containsKey(BaseBlock)) {
					MelterInventory.inventory.remove(BaseBlock);
					Machine.DeleteMachineFromList(new Machine(BaseBlock, "Melter"));
				}
			}
		}
		
	}
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(!event.getView().getTitle().equals(ChatColor.AQUA+"Melter") || event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
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
			if(event.getClickedInventory().getItem(10) == null || event.getClickedInventory().getItem(16) == null) {
				return;
			}
			if(event.getClickedInventory().getItem(16).getAmount() != 1) {
				player.sendMessage(ChatColor.RED + "Place a SINGLE Tank in output slot");
				return;
			}
			if(!event.getClickedInventory().getItem(16).hasItemMeta()) {
				player.sendMessage(ChatColor.RED + "Place Tank in output slot");
				return;
			}
			if(!event.getClickedInventory().getItem(16).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING)) {
				player.sendMessage(ChatColor.RED + "Place Tank in output slot");
				return;
			}
			ItemStack key = new ItemStack(event.getClickedInventory().getItem(10));
			key.setAmount(1);
			if(!key.getType().equals(Material.PLAYER_HEAD)) {
				if(Recipes.containsKey(key)) {
					ItemStack input = event.getClickedInventory().getItem(10);
					String outputFluidType = (String) Recipes.get(key).get(0);
					int outputFluidMB = (int) Recipes.get(key).get(1);
					ItemStack tank = event.getClickedInventory().getItem(16);
					if(tank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING).equals("") || tank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING).equals(outputFluidType)) {
						if(tank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER) + outputFluidMB <= tank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)) {
							SkullMeta tankMeta = (SkullMeta) tank.getItemMeta();
							tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, outputFluidType);
							tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER, tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+outputFluidMB);
							List<String> lore = new ArrayList<>();
							lore.addAll(tankMeta.getLore());
							lore.set(2, "§7"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of "+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING)+".");
							tankMeta.setLore(lore);
							tank.setItemMeta(tankMeta);
							input.setAmount(input.getAmount()-1);
							player.playSound(player.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1, 1);
							event.getClickedInventory().setItem(16, tank);
					    	event.getClickedInventory().setItem(10, input);
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
			else {
				Set<ItemStack> keys = Recipes.keySet();
				ItemStack[] keySet = new ItemStack[keys.size()];
				keys.toArray(keySet);
				for(int i=0; i<keySet.length;i++) {
					ItemStack item = new ItemStack(keySet[i]);
					if(item.hasItemMeta()) {
						if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)) {
							String recipeID = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING);
							if(key.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING).equals(recipeID)) {
								key = keySet[i];
								ItemStack input = event.getClickedInventory().getItem(10);
								String outputFluidType = (String) Recipes.get(key).get(0);
								int outputFluidMB = (int) Recipes.get(key).get(1);
								ItemStack tank = event.getClickedInventory().getItem(16);
								if(tank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING).equals("") || tank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING).equals(outputFluidType)) {
									if(tank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER) + outputFluidMB <= tank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)) {
										SkullMeta tankMeta = (SkullMeta) tank.getItemMeta();
										tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, outputFluidType);
										tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER, tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+outputFluidMB);
										List<String> lore = new ArrayList<>();
										lore.addAll(tankMeta.getLore());
										lore.set(2, "§7"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of "+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING)+".");
										tankMeta.setLore(lore);
										tank.setItemMeta(tankMeta);
										input.setAmount(input.getAmount()-1);
										player.playSound(player.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 1, 1);
										event.getClickedInventory().setItem(16, tank);
								    	event.getClickedInventory().setItem(10, input);
									}
									else {
										player.sendMessage(ChatColor.RED + "Tank is full.");
									}
								}
								else {
									player.sendMessage(ChatColor.RED + "Tank contains other type of liquid.");
								}
								break;
							}
						}
					}
				}
				
				
			}
		}
	}
	public static void addAllRecipes() {
		ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>();
		lore.add("§8Liquid");
		ItemStack item2 = new ItemStack(Material.COAL);
		ItemMeta meta2 = item2.getItemMeta();
		ArrayList array = new ArrayList<>();
		addRecipe(new ItemStack(Material.IRON_ORE, 1), "Liquid iron", 288);
		addRecipe(new ItemStack(Material.RAW_IRON, 1), "Liquid iron", 288);
		addRecipe(new ItemStack(Material.DEEPSLATE_IRON_ORE, 1), "Liquid iron", 432);
		addRecipe(new ItemStack(Material.IRON_INGOT, 1), "Liquid iron", 144);
		addRecipe(new ItemStack(Material.GOLD_ORE, 1), "Liquid gold", 288);
		addRecipe(new ItemStack(Material.RAW_GOLD, 1), "Liquid gold", 288);
		addRecipe(new ItemStack(Material.DEEPSLATE_GOLD_ORE, 1), "Liquid gold", 432);
		addRecipe(new ItemStack(Material.NETHER_GOLD_ORE, 1), "Liquid gold", 288);
		addRecipe(new ItemStack(Material.GOLD_INGOT, 1), "Liquid gold", 144);
		addRecipe(new ItemStack(Material.COPPER_ORE, 1), "Liquid copper", 288);
		addRecipe(new ItemStack(Material.RAW_COPPER, 1), "Liquid copper", 288);
		addRecipe(new ItemStack(Material.DEEPSLATE_COPPER_ORE, 1), "Liquid copper", 432);
		addRecipe(new ItemStack(Material.COPPER_INGOT, 1), "Liquid copper", 144);
		addRecipe(new ItemStack(Material.EMERALD_ORE, 1), "Liquid emerald", 288);
		addRecipe(new ItemStack(Material.DEEPSLATE_EMERALD_ORE, 1), "Liquid emerald", 432);
		addRecipe(new ItemStack(Material.EMERALD, 1), "Liquid emerald", 144);
		addRecipe(new ItemStack(Material.DIAMOND, 1), "Liquid diamond", 144);
		addRecipe(IngredientManager.CarbonIngot, "Liquid carbon", 160);
		addRecipe(IngredientManager.SteelIngot, "Liquid steel", 144);
		//
		addRecipe(new ItemStack(Material.COAL, 1), "Liquid carbon", 10);
		item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GRAY+"Liquid carbon");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidcarbon");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "nameextention"), PersistentDataType.STRING, "10mb of ");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidcarbon");
		item.setItemMeta(meta);
		item2 = new ItemStack(Material.COAL);
		meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.WHITE+"Coal");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "coal");
		item2.setItemMeta(meta2);
		array.add(item);
		array.add("Melter");
		array.add(item2);
		RecipeBookSelectorEvent.addRecipe("liquidcarbon", 1, new ArrayList(array));
		array.clear();
		//
		addRecipe(new ItemStack(Material.SUGAR, 1), "Liquid glucose", 100);
		item = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Liquid glucose");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidglucose");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "nameextention"), PersistentDataType.STRING, "100mb of ");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidglucose");
		item.setItemMeta(meta);
		item2 = new ItemStack(Material.SUGAR);
		meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.WHITE+"Sugar");
		meta2.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "sugar");
		item2.setItemMeta(meta2);
		array.add(item);
		array.add("Melter");
		array.add(item2);
		RecipeBookSelectorEvent.addRecipe("liquidglucose", 1, new ArrayList(array));
		array.clear();
		//
		addRecipe(new ItemStack(Material.COD, 1), "Liquid gelatin", 100);
		addRecipe(new ItemStack(Material.SALMON, 1), "Liquid gelatin", 100);
		addRecipe(new ItemStack(Material.PUFFERFISH, 1), "Liquid gelatin", 100);
		addRecipe(new ItemStack(Material.TROPICAL_FISH, 1), "Liquid gelatin", 100);
		item = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.YELLOW+"Liquid gelatin");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidgelatin");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "nameextention"), PersistentDataType.STRING, "100mb of ");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidgelatin");
		item.setItemMeta(meta);
		item2 = new ItemStack(Material.COD);
		meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.WHITE+"Any fish");
		item2.setItemMeta(meta2);
		array.add(item);
		array.add("Melter");
		array.add(item2);
		RecipeBookSelectorEvent.addRecipe("liquidgelatin", 1, new ArrayList(array));
		array.clear();
		//
		addRecipe(new ItemStack(Material.COCOA_BEANS, 1), "Liquid cocoa", 5);
		item = new ItemStack(Material.BROWN_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.of("#964e00")+"Liquid cocoa");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidcocoa");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "nameextention"), PersistentDataType.STRING, "5mb of ");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidcocoa");
		item.setItemMeta(meta);
		item2 = new ItemStack(Material.COCOA_BEANS);
		meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.WHITE+"Cocoa Beans");
		item2.setItemMeta(meta2);
		array.add(item);
		array.add("Melter");
		array.add(item2);
		RecipeBookSelectorEvent.addRecipe("liquidcocoa", 1, new ArrayList(array));
		array.clear();
		//
		addRecipe(new ItemStack(Material.SWEET_BERRIES, 1), "Liquid berries", 5);
		item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Liquid berries");
		meta.setLore(lore);
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING, "liquidberries");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "nameextention"), PersistentDataType.STRING, "5mb of ");
		meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "craftingName"), PersistentDataType.STRING, "liquidberries");
		item.setItemMeta(meta);
		item2 = new ItemStack(Material.SWEET_BERRIES);
		meta2 = item2.getItemMeta();
		meta2.setDisplayName(ChatColor.WHITE+"Sweet Berries");
		item2.setItemMeta(meta2);
		array.add(item);
		array.add("Melter");
		array.add(item2);
		RecipeBookSelectorEvent.addRecipe("liquidberries", 1, new ArrayList(array));
		array.clear();
	}
	public static void addRecipe(ItemStack inputItem, String fluidTypeOutput, int fluidMBOutput) {
		ArrayList array = new ArrayList<>();
		array.add(fluidTypeOutput);
		array.add(fluidMBOutput);
		Recipes.put(inputItem, new ArrayList(array));
	}
}
