package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.block.data.type.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.dataManagment.Machine;
import me.notbestlord.Plugin.items.FoodManager;
import me.notbestlord.Plugin.items.ItemManager;
import net.md_5.bungee.api.ChatColor;

public class AutomaticGardenEvents implements Listener{
	
	private HashMap<Location, Integer> AutomaticGardenActivness = new HashMap<>();
	public static HashMap<String, ArrayList> Crops = new HashMap<>();
	
	@EventHandler
	private void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock() == null) {
				return;
			}
			if(event.getClickedBlock().getType().equals(Material.GLASS)) {
				Location BottomBlockLocation = event.getClickedBlock().getLocation();
				Location TopBlockLocation = event.getClickedBlock().getLocation();
				BottomBlockLocation.subtract(0, 1, 0);
				TopBlockLocation.add(0, 1, 0);
				if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.DISPENSER)) {
					if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
						event.setCancelled(true);
						if(!(new Machine(event.getClickedBlock().getLocation(), "AutomaticGarden").MachineInArray(Main.MachineList))) {
							if (event.getPlayer().getInventory().getItemInMainHand().equals(ItemManager.AutomaticGardenCatalyst)){
								Block block = BottomBlockLocation.getBlock();
								Dispenser directional = (Dispenser) block.getBlockData();
								Location waterLocation = BottomBlockLocation.clone();
								waterLocation.add(directional.getFacing().getModX(), directional.getFacing().getModY(), directional.getFacing().getModZ());
								AutomaticGardenInventory.createMainInventory(event.getClickedBlock().getLocation(), waterLocation);
								Machine machine = new Machine(event.getClickedBlock().getLocation(), AutomaticGardenInventory.inventory.get(event.getClickedBlock().getLocation()), "AutomaticGarden");
								Main.MachineList.add(machine);
								event.getPlayer().openInventory(AutomaticGardenInventory.inventory.get(event.getClickedBlock().getLocation()));
								event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
							}
						}
						else {
							event.getPlayer().openInventory(AutomaticGardenInventory.inventory.get(event.getClickedBlock().getLocation()));
						}
					}
				}
			}
		}
	}
	@EventHandler
	private void onBreakBlock(BlockBreakEvent event) {
		if(event.getBlock().getType().equals(Material.GLASS)) {
			Location BottomBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			BottomBlockLocation.subtract(0, 1, 0);
			TopBlockLocation.add(0, 1, 0);
			if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.DISPENSER)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
					if(AutomaticGardenInventory.inventory.containsKey(event.getBlock().getLocation())) {
						if(AutomaticGardenActivness.containsKey(event.getBlock().getLocation())) {
							Bukkit.getScheduler().cancelTask(AutomaticGardenActivness.get(event.getBlock().getLocation()));
							AutomaticGardenActivness.remove(event.getBlock().getLocation());
						}
						AutomaticGardenInventory.inventory.remove(event.getBlock().getLocation());
						Machine.DeleteMachineFromList(new Machine(event.getBlock().getLocation(), "AutomaticGarden"));
						event.getPlayer().getInventory().addItem(ItemManager.AutomaticGardenCatalyst);
					}
				}
			}
		}
		if(event.getBlock().getType().equals(Material.DISPENSER)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.add(0, 1, 0);
			TopBlockLocation.add(0, 2, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.GLASS)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
					if(AutomaticGardenInventory.inventory.containsKey(BaseBlockLocation)) {
						if(AutomaticGardenActivness.containsKey(BaseBlockLocation)) {
							Bukkit.getScheduler().cancelTask(AutomaticGardenActivness.get(BaseBlockLocation));
							AutomaticGardenActivness.remove(BaseBlockLocation);
						}
						AutomaticGardenInventory.inventory.remove(BaseBlockLocation);
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "AutomaticGarden"));
						event.getPlayer().getInventory().addItem(ItemManager.AutomaticGardenCatalyst);
					}
				}
			}
		}
		if(event.getBlock().getType().equals(Material.IRON_TRAPDOOR)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location BottomBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.subtract(0, 1, 0);
			BottomBlockLocation.subtract(0, 2, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.GLASS)) {
				if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.DISPENSER)) {
					if(AutomaticGardenActivness.containsKey(BaseBlockLocation)) {
						Bukkit.getScheduler().cancelTask(AutomaticGardenActivness.get(BaseBlockLocation));
						AutomaticGardenActivness.remove(BaseBlockLocation);
					}
					if(AutomaticGardenInventory.inventory.containsKey(BaseBlockLocation)) {
						AutomaticGardenInventory.inventory.remove(BaseBlockLocation);
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "AutomaticGarden"));
						event.getPlayer().getInventory().addItem(ItemManager.AutomaticGardenCatalyst);
					}
				}
			}
		}
	}
	@EventHandler
	private void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(!event.getView().getTitle().equals(ChatColor.AQUA+"Automatic Garden") || event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
			return;
		}
		Player player = (Player) event.getWhoClicked();
		if(event.getSlot() != 1 && event.getSlot() != 19) {
			event.setCancelled(true);
		}
		if(event.getClickedInventory().getItem(13).getType() == Material.RED_STAINED_GLASS_PANE) {
			event.setCancelled(true);
		}
		if(event.getSlot() == 22) {
			player.closeInventory();
		}
		ItemMeta tempMeta = event.getClickedInventory().getItem(0).getItemMeta();
		String worldName = tempMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "locationWorld"), PersistentDataType.STRING);
		int x = tempMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "locationX"), PersistentDataType.INTEGER);
		int y = tempMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "locationY"), PersistentDataType.INTEGER);
		int z = tempMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "locationZ"), PersistentDataType.INTEGER);
		Location location = new Location(Bukkit.getServer().getWorld(worldName), x, y, z);
		
		if(event.getSlot() == 13 && event.getClickedInventory().getItem(13).getType() == Material.LIME_STAINED_GLASS_PANE) {
			x = tempMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "WaterLocationX"), PersistentDataType.INTEGER);
			y = tempMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "WaterLocationY"), PersistentDataType.INTEGER);
			z = tempMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "WaterLocationZ"), PersistentDataType.INTEGER);
			Location waterLocation = new Location(Bukkit.getServer().getWorld(worldName), x, y, z);
			String id = "";
			if(event.getClickedInventory().getItem(1) == null) {
				player.sendMessage(ChatColor.RED+"Invalid Seed");
				return;
			}
			if(!event.getClickedInventory().getItem(1).hasItemMeta()) {
				player.sendMessage(ChatColor.RED+"Invalid Seed");
				return;
			}
			if(!event.getClickedInventory().getItem(1).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
				player.sendMessage(ChatColor.RED+"Invalid Seed");
				return;
			}
			id = event.getClickedInventory().getItem(1).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING);
			if(!Crops.containsKey(id)) {
				player.sendMessage(ChatColor.RED+"Invalid Seed");
				return;
			}
			ItemStack groundBlock = null;
			String groundBlockID = null;
			if(Crops.get(id).get(0).getClass().equals(Material.class)) {
				groundBlock = new ItemStack((Material)Crops.get(id).get(0));
			}
			else if(Crops.get(id).get(0).getClass().equals(ItemStack.class)) {
				groundBlockID = ((ItemStack)Crops.get(id).get(0)).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING);
			}
			
			if(groundBlockID == null) {
				if(event.getClickedInventory().getItem(19) == null) {
					player.sendMessage(ChatColor.RED+"Invalid Ground Block");
					return;
				}
				if(event.getClickedInventory().getItem(19).getType() != groundBlock.getType()) {
					player.sendMessage(ChatColor.RED+"Invalid Ground Block");
					return;
				}
			}
			else if(groundBlock == null) {
				if(event.getClickedInventory().getItem(19) == null) {
					player.sendMessage(ChatColor.RED+"Invalid Ground Block");
					return;
				}
				if(!event.getClickedInventory().getItem(19).hasItemMeta()) {
					player.sendMessage(ChatColor.RED+"Invalid Ground Block");
					return;
				}
				if(!event.getClickedInventory().getItem(19).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
					player.sendMessage(ChatColor.RED+"Invalid Ground Block");
					return;
				}
				String inBlockID = event.getClickedInventory().getItem(1).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING);
				if(!inBlockID.equals(id)) {
					player.sendMessage(ChatColor.RED+"Invalid Ground Block");
					return;
				}
			}
			
			Location outputLocation = location.clone().subtract(0,1,0);
			Block outputBlock = outputLocation.getBlock();
			org.bukkit.block.Dispenser outputContainer = (org.bukkit.block.Dispenser) outputBlock.getState();
			if(!Crops.get(id).get(3).getClass().equals(Integer.class)) {
				player.sendMessage(ChatColor.RED+"Error Growth time not integer");
				return;
			}
			int growthTime = (int)Crops.get(id).get(3);
			tempMeta = event.getClickedInventory().getItem(0).getItemMeta();
			tempMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "recipeID"), PersistentDataType.STRING, id);
			event.getClickedInventory().getItem(0).setItemMeta(tempMeta);
			
			AutomaticGardenActivness.put(location, Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
				
				@Override
				public void run() {
					ItemMeta tempMeta = event.getClickedInventory().getItem(0).getItemMeta();
					int runnableTime = tempMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "runnableTimer"), PersistentDataType.INTEGER);
					tempMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "runnableTimer"), PersistentDataType.INTEGER, runnableTime+1);
					event.getClickedInventory().getItem(0).setItemMeta(tempMeta);
					ItemMeta tempmeta = event.getClickedInventory().getItem(16).getItemMeta();
					int Water = tempmeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "waterMB"), PersistentDataType.INTEGER);
					if(Water <= 0) {
						if(waterLocation.getBlock().getType() == Material.WATER) {
							Levelled waterLevel = (Levelled) waterLocation.getBlock().getBlockData();
							if(waterLevel.getLevel() == 0) {
								waterLocation.getBlock().setType(Material.AIR);
								Water = 1000;
							}
							else {
								return;
							}
						}
						else {
							return;
						}
					}
					tempmeta.setDisplayName(ChatColor.WHITE+"Water - "+Water+"mb");
					tempmeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "waterMB"), PersistentDataType.INTEGER, Water-1);
					event.getClickedInventory().getItem(16).setItemMeta(tempmeta);
					if(runnableTime >= growthTime) {
						tempMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "runnableTimer"), PersistentDataType.INTEGER, 0);
						event.getClickedInventory().getItem(0).setItemMeta(tempMeta);
						String tempId = event.getInventory().getItem(0).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "recipeID"), PersistentDataType.STRING);
						ItemStack output = null;
						if(Crops.get(tempId).get(2).getClass().equals(Material.class)) {
							output = new ItemStack((Material)Crops.get(tempId).get(2));
						}
						else if(Crops.get(tempId).get(2).getClass().equals(ItemStack.class)) {
							output = ((ItemStack)Crops.get(tempId).get(2));
						}
						outputContainer.getInventory().addItem(output);
					}
				}
			}, 0, 1));
			ItemStack item1 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
			ItemMeta meta = item1.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE+"De-Activate Automatic Garden");
			item1.setItemMeta(meta);
			event.getClickedInventory().setItem(13, item1);
		}
		else if(event.getSlot() == 13 && event.getClickedInventory().getItem(13).getType() == Material.RED_STAINED_GLASS_PANE) {
			if(AutomaticGardenActivness.containsKey(location)) {
				Bukkit.getScheduler().cancelTask(AutomaticGardenActivness.get(location));
				AutomaticGardenActivness.remove(location);
				ItemStack item1 = new ItemStack(Material.LIME_STAINED_GLASS_PANE); 
				ItemMeta meta = item1.getItemMeta();
				meta.setDisplayName(ChatColor.WHITE+"Activate Automatic Garden");
				item1.setItemMeta(meta);
				event.getClickedInventory().setItem(13, item1);
			}
			else {
				ItemStack item1 = new ItemStack(Material.LIME_STAINED_GLASS_PANE); 
				ItemMeta meta = item1.getItemMeta();
				meta.setDisplayName(ChatColor.WHITE+"Activate Automatic Garden");
				item1.setItemMeta(meta);
				event.getClickedInventory().setItem(13, item1);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void addAllRecipes() {
		ArrayList array = new ArrayList<>();
		array.add(Material.DIRT);
		array.add(FoodManager.TomatoSeed);
		array.add(FoodManager.Tomato);
		array.add(200);
		Crops.put("tomatoseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.DIRT);
		array.add(FoodManager.LettuceSeed);
		array.add(FoodManager.Lettuce);
		array.add(200);
		Crops.put("lettuceseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.DIRT);
		array.add(FoodManager.CornSeed);
		array.add(FoodManager.Corn);
		array.add(200);
		Crops.put("cornseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.DIRT);
		array.add(FoodManager.OnionSeed);
		array.add(FoodManager.Onion);
		array.add(200);
		Crops.put("onionseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.DIRT);
		array.add(FoodManager.BellPepperSeed);
		array.add(FoodManager.BellPepper);
		array.add(200);
		Crops.put("bellpepperseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.DIRT);
		array.add(FoodManager.GarlicSeed);
		array.add(FoodManager.Garlic);
		array.add(200);
		Crops.put("garlicseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.DIRT);
		array.add(FoodManager.AvocadoSeed);
		array.add(FoodManager.Avocado);
		array.add(200);
		Crops.put("avocadoseed", new ArrayList(array));
		array.clear();
		//
		//
		//
		//
		array.add(Material.DIRT);
		array.add(FoodManager.RiceSeed);
		array.add(FoodManager.Rice);
		array.add(200);
		Crops.put("riceseed", new ArrayList(array));
		array.clear();
		//
		//
		//
		//
		array.add(Material.DIRT);
		array.add(FoodManager.StrawberrySeed);
		array.add(FoodManager.Strawberry);
		array.add(200);
		Crops.put("strawberryseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.DIRT);
		array.add(FoodManager.OrangeSeed);
		array.add(FoodManager.Orange);
		array.add(200);
		Crops.put("orangeseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.DIRT);
		array.add(FoodManager.GrapesSeed);
		array.add(FoodManager.Grapes);
		array.add(200);
		Crops.put("grapesseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.DIRT);
		array.add(FoodManager.CherrySeed);
		array.add(FoodManager.Cherry);
		array.add(200);
		Crops.put("cherryseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.DIRT);
		array.add(FoodManager.MangoSeed);
		array.add(FoodManager.Mango);
		array.add(200);
		Crops.put("mangoseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.DIRT);
		array.add(FoodManager.LemonSeed);
		array.add(FoodManager.Lemon);
		array.add(200);
		Crops.put("lemonseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.DIRT);
		array.add(FoodManager.LimeSeed);
		array.add(FoodManager.Lime);
		array.add(200);
		Crops.put("limeseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.DIRT);
		array.add(FoodManager.PeachSeed);
		array.add(FoodManager.Peach);
		array.add(200);
		Crops.put("peachseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.DIRT);
		array.add(FoodManager.KiwiSeed);
		array.add(FoodManager.Kiwi);
		array.add(200);
		Crops.put("kiwiseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.SLIME_BLOCK);
		array.add(FoodManager.SlimeFruitSeed);
		array.add(FoodManager.SlimeFruit);
		array.add(600);
		Crops.put("slimefruitseed", new ArrayList(array));
		array.clear();
		//
		array.add(Material.DIRT);
		array.add(FoodManager.OilFruitSeed);
		array.add(FoodManager.OilFruit);
		array.add(400);
		Crops.put("oilfruitseed", new ArrayList(array));
		array.clear();
		//
	}
}
