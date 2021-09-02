package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Barrel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.dataManagment.Machine;
import me.notbestlord.Plugin.items.IngredientManager;
import me.notbestlord.Plugin.items.ItemManager;

public class VoidOreMinerEvents implements Listener{

	public static Hashtable<Location, Integer> VoidOreMinerActivness= new Hashtable<>();
	public static Hashtable<Location, Integer> VoidOreMinerTier= new Hashtable<>();
	public static Hashtable<String, ArrayList<ItemStack>> VoidOreMinerResources = new Hashtable<>();
	public static Hashtable<String, Integer> VoidOreMinerLenses = new Hashtable<>();
	
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock() == null) {
				return;
			}
			if(event.getClickedBlock().getType().equals(Material.BEACON)) {
				Location BottomBlockLocation = event.getClickedBlock().getLocation();
				Location TopBlockLocation = event.getClickedBlock().getLocation();
				BottomBlockLocation.subtract(0, 1, 0);
				TopBlockLocation.add(0, 1, 0);
				if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.BARREL)) {
					if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.DISPENSER)) {
						event.setCancelled(true);
						if(!(new Machine(event.getClickedBlock().getLocation(), "VoidOreMiner").MachineInArray(Main.MachineList))) {
							if (event.getPlayer().getInventory().getItemInMainHand().equals(ItemManager.RadiatedMultiBlockHammer)){
								VoidOreMinerInventory.createVoidOreMiner(event.getClickedBlock().getLocation());
								Machine machine = new Machine(event.getClickedBlock().getLocation(), VoidOreMinerInventory.inventory.get(event.getClickedBlock().getLocation()), "VoidOreMiner");
								Main.MachineList.add(machine);
								event.getPlayer().openInventory(VoidOreMinerInventory.inventory.get(event.getClickedBlock().getLocation()));
							}
						}
						else {
							event.getPlayer().openInventory(VoidOreMinerInventory.inventory.get(event.getClickedBlock().getLocation()));
						}
					}
				}
			}
		}
	}
	@EventHandler
	public static void onBreakBlock(BlockBreakEvent event) {
		if(event.getBlock().getType().equals(Material.BEACON)) {
			Location BottomBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			BottomBlockLocation.subtract(0, 1, 0);
			TopBlockLocation.add(0, 1, 0);
			if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.BARREL)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.DISPENSER)) {
					if(VoidOreMinerInventory.inventory.containsKey(event.getBlock().getLocation())) {
						VoidOreMinerInventory.inventory.remove(event.getBlock().getLocation());
						Machine.DeleteMachineFromList(new Machine(event.getBlock().getLocation(), "VoidOreMiner"));
					}
				}
			}
		}
		else if(event.getBlock().getType().equals(Material.BARREL)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.add(0, 1, 0);
			TopBlockLocation.add(0, 2, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.BEACON)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.DISPENSER)) {
					if(VoidOreMinerInventory.inventory.containsKey(BaseBlockLocation)) {
						VoidOreMinerInventory.inventory.remove(BaseBlockLocation);
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "VoidOreMiner"));
					}
				}
			}
		}
		else if(event.getBlock().getType().equals(Material.DISPENSER)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location BottomBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.subtract(0, 1, 0);
			BottomBlockLocation.subtract(0, 2, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.BEACON)) {
				if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.BARREL)) {
					if(VoidOreMinerInventory.inventory.containsKey(BaseBlockLocation)) {
						VoidOreMinerInventory.inventory.remove(BaseBlockLocation);
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "VoidOreMiner"));
					}
				}
			}
		}
	}
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(!event.getView().getTitle().equals(ChatColor.AQUA+"Void Ore Miner") || event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
			return;
		}
		if(event.getSlot() != 11 && event.getSlot() != 15) {
			event.setCancelled(true);
		}
		if(event.getSlot() == 17 || event.getSlot() == 26 || event.getSlot() == 35 || event.getSlot() == 44 || event.getSlot() == 31) {
			event.setCancelled(false);
		}
		Player player = (Player)event.getWhoClicked();
		if(event.getSlot() == 49) {
			player.closeInventory();
		}
		double x = event.getClickedInventory().getItem(0).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "x"), PersistentDataType.INTEGER);
		double y = event.getClickedInventory().getItem(0).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "y"), PersistentDataType.INTEGER);
		double z = event.getClickedInventory().getItem(0).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "z"), PersistentDataType.INTEGER);
		Location invLocation = new Location(player.getWorld(), x, y, z);
		Location outputInvLocation = new Location(player.getWorld(), x, y, z);
		outputInvLocation.subtract(0,1,0);
		if(event.getClickedInventory().getItem(13).getType().equals(Material.RED_STAINED_GLASS_PANE)) {
			event.setCancelled(true);
		}
		if(event.getSlot() == 13) {
			if(event.getClickedInventory().getItem(13).getType().equals(Material.LIME_STAINED_GLASS_PANE)) {
				Barrel outputBlock = (Barrel) player.getWorld().getBlockAt(outputInvLocation).getState();
				Inventory outputInventory = outputBlock.getInventory();
				ItemStack item1 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
				ItemMeta meta = item1.getItemMeta();
				meta.setDisplayName(ChatColor.WHITE+"Void Ore Miner Active");
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.GRAY+"Produces random ores and resources every 30 seconds");
				lore.add(ChatColor.GRAY+"at the cost of 7500RF upon resource generation.");
				meta.setLore(lore);
				lore.clear();
				item1.setItemMeta(meta);
				event.getClickedInventory().setItem(13, item1);
				int VoidOreMinerTierLocal = 1;
				if(event.getClickedInventory().getItem(17) != null) {
					if(event.getClickedInventory().getItem(17).hasItemMeta()) {
						if(event.getClickedInventory().getItem(17).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
							if(event.getClickedInventory().getItem(17).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("voidoreminertier2upgrade")) {
								VoidOreMinerTierLocal = 2;
							}
						}
					}
				}
				if(VoidOreMinerTierLocal == 2) {
					if(event.getClickedInventory().getItem(26) != null) {
						if(event.getClickedInventory().getItem(26).hasItemMeta()) {
							if(event.getClickedInventory().getItem(26).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
								if(event.getClickedInventory().getItem(26).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("voidoreminertier3upgrade")) {
									VoidOreMinerTierLocal = 3;
								}
							}
						}
					}
				}
				if(VoidOreMinerTierLocal == 3) {
					if(event.getClickedInventory().getItem(35) != null) {
						if(event.getClickedInventory().getItem(35).hasItemMeta()) {
							if(event.getClickedInventory().getItem(35).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
								if(event.getClickedInventory().getItem(35).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("voidoreminertier4upgrade")) {
									VoidOreMinerTierLocal = 4;
								}
							}
						}
					}
				}
				if(VoidOreMinerTierLocal == 4) {
					if(event.getClickedInventory().getItem(44) != null) {
						if(event.getClickedInventory().getItem(44).hasItemMeta()) {
							if(event.getClickedInventory().getItem(44).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
								if(event.getClickedInventory().getItem(44).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("voidoreminertier5upgrade")) {
									VoidOreMinerTierLocal = 5;
								}
							}
						}
					}
				}
				VoidOreMinerTier.put(invLocation, VoidOreMinerTierLocal);
				double speedModifier = 10000;
				if(event.getClickedInventory().getItem(11) != null) {
					if(event.getClickedInventory().getItem(11).hasItemMeta()) {
						if(event.getClickedInventory().getItem(11).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
							if(event.getClickedInventory().getItem(11).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("speedupgrade")) {
								speedModifier-=(125*event.getClickedInventory().getItem(11).getAmount());
							}
						}
					}
				}
				VoidOreMinerActivness.put(invLocation, Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
					
					@Override
					public void run() {
						if(outputInventory.firstEmpty() != -1) {
							double energyModifier = 10000;
							if(event.getClickedInventory().getItem(11) != null) {
								if(event.getClickedInventory().getItem(11).hasItemMeta()) {
									if(event.getClickedInventory().getItem(11).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
										if(event.getClickedInventory().getItem(11).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("speedupgrade")) {
											energyModifier+=(250*event.getClickedInventory().getItem(11).getAmount());
										}
									}
								}
							}
							if(event.getClickedInventory().getItem(15) != null) {
								if(event.getClickedInventory().getItem(15).hasItemMeta()) {
									if(event.getClickedInventory().getItem(15).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
										if(event.getClickedInventory().getItem(15).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("energyupgrade")) {
											if(event.getClickedInventory().getItem(11).getAmount() > event.getClickedInventory().getItem(15).getAmount()) {
												energyModifier-=(125*event.getClickedInventory().getItem(15).getAmount());
											}
											else {
												energyModifier-=(125*event.getClickedInventory().getItem(11).getAmount());
											}
										}
									}
								}
							}
							int BaseReduction = 7500;
							int Tier = VoidOreMinerTier.get(invLocation);
							if(Tier > 1) {
								BaseReduction-=187.5*(Tier-1);
							}
							String lens = "none";
							if(event.getClickedInventory().getItem(31) != null) {
								if(event.getClickedInventory().getItem(31).hasItemMeta()) {
									if(event.getClickedInventory().getItem(31).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
										lens = getLensType(event.getClickedInventory().getItem(31).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING));
									}
								}
							}
							if(Tier < VoidOreMinerLenses.get(lens)) {
								player.sendMessage(ChatColor.RED+"Lens requires higher tier of miner.");
								Bukkit.getScheduler().cancelTask(VoidOreMinerActivness.get(invLocation));
								ItemStack item1 = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
								ItemMeta meta = item1.getItemMeta();
								meta.setDisplayName(ChatColor.WHITE+"Activate Void Ore Miner");
								List<String> lore = new ArrayList<>();
								lore.add(ChatColor.GRAY+"Produces random ores and resources every 30 seconds");
								lore.add(ChatColor.GRAY+"at the cost of 7500RF upon resource generation.");
								lore.add(ChatColor.BLUE+"<- Speed Upgrades");
								lore.add(ChatColor.BLUE+"Energy Upgrades ->");
								lore.add(ChatColor.BLUE+"\\/ Lens");
								meta.setLore(lore);
								lore.clear();
								item1.setItemMeta(meta);
								event.getClickedInventory().setItem(13, item1);
								return;
							}
							if(Main.RedstoneFluxManager.reduceRedstoneFlux(player, (int)(BaseReduction*(energyModifier/10000)))) {
					            Random r = new Random();
					            int outputItem = r.nextInt(VoidOreMinerResources.get(lens).size());
					            outputInventory.addItem(VoidOreMinerResources.get(lens).get(outputItem));
							}
							else {
								player.sendMessage(ChatColor.RED+"No RF.");
								Bukkit.getScheduler().cancelTask(VoidOreMinerActivness.get(invLocation));
								ItemStack item1 = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
								ItemMeta meta = item1.getItemMeta();
								meta.setDisplayName(ChatColor.WHITE+"Activate Void Ore Miner");
								List<String> lore = new ArrayList<>();
								lore.add(ChatColor.GRAY+"Produces random ores and resources every 30 seconds");
								lore.add(ChatColor.GRAY+"at the cost of 7500RF upon resource generation.");
								lore.add(ChatColor.BLUE+"<- Speed Upgrades");
								lore.add(ChatColor.BLUE+"Energy Upgrades ->");
								lore.add(ChatColor.BLUE+"\\/ Lens");
								meta.setLore(lore);
								lore.clear();
								item1.setItemMeta(meta);
								event.getClickedInventory().setItem(13, item1);
							}
						}
						else {
							player.sendMessage(ChatColor.RED+"Output barrel is full.");
							Bukkit.getScheduler().cancelTask(VoidOreMinerActivness.get(invLocation));
							ItemStack item1 = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
							ItemMeta meta = item1.getItemMeta();
							meta.setDisplayName(ChatColor.WHITE+"Activate Void Ore Miner");
							List<String> lore = new ArrayList<>();
							lore.add(ChatColor.GRAY+"Produces random ores and resources every 30 seconds");
							lore.add(ChatColor.GRAY+"at the cost of 7500RF upon resource generation.");
							lore.add(ChatColor.BLUE+"<- Speed Upgrades");
							lore.add(ChatColor.BLUE+"Energy Upgrades ->");
							lore.add(ChatColor.BLUE+"\\/ Lens");
							meta.setLore(lore);
							lore.clear();
							item1.setItemMeta(meta);
							event.getClickedInventory().setItem(13, item1);
						}
					}
				}, (int)(600*(speedModifier/10000)), (int)(600*(speedModifier/10000))));
			}
			else {
				Bukkit.getScheduler().cancelTask(VoidOreMinerActivness.get(invLocation));
				ItemStack item1 = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item1.getItemMeta();
				meta.setDisplayName(ChatColor.WHITE+"Activate Void Ore Miner");
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.GRAY+"Produces random ores and resources every 30 seconds");
				lore.add(ChatColor.GRAY+"at the cost of 7500RF upon resource generation.");
				lore.add(ChatColor.BLUE+"<- Speed Upgrades");
				lore.add(ChatColor.BLUE+"Energy Upgrades ->");
				lore.add(ChatColor.BLUE+"\\/ Lens");
				meta.setLore(lore);
				lore.clear();
				item1.setItemMeta(meta);
				event.getClickedInventory().setItem(13, item1);
			}
		}
	}
	
	public static void addResources() {
		initLens("none", 1);
		addResource("none", new ItemStack(Material.IRON_ORE), 25);
		addResource("none", new ItemStack(Material.DEEPSLATE_IRON_ORE), 15);
		addResource("none", new ItemStack(Material.GOLD_ORE), 12);
		addResource("none", new ItemStack(Material.DEEPSLATE_GOLD_ORE), 5);
		addResource("none", new ItemStack(Material.COPPER_ORE), 25);
		addResource("none", new ItemStack(Material.DEEPSLATE_COPPER_ORE), 10);
		addResource("none", new ItemStack(Material.REDSTONE_ORE), 8);
		addResource("none", new ItemStack(Material.DEEPSLATE_REDSTONE_ORE), 10);
		addResource("none", new ItemStack(Material.LAPIS_ORE), 11);
		addResource("none", new ItemStack(Material.DEEPSLATE_LAPIS_ORE), 9);
		addResource("none", new ItemStack(Material.EMERALD_ORE), 4);
		addResource("none", new ItemStack(Material.DEEPSLATE_EMERALD_ORE), 1);
		addResource("none", new ItemStack(Material.DIAMOND_ORE), 4);
		addResource("none", new ItemStack(Material.DEEPSLATE_DIAMOND_ORE), 3);
		addResource("none", new ItemStack(Material.COAL_ORE), 32);
		addResource("none", new ItemStack(Material.DEEPSLATE_COAL_ORE), 2);
		//
		initLens("stonelens", 2);
		addResource("stonelens", new ItemStack(Material.COBBLESTONE), 35);
		addResource("stonelens", new ItemStack(Material.STONE), 35);
		addResource("stonelens", new ItemStack(Material.COBBLED_DEEPSLATE), 15);
		addResource("stonelens", new ItemStack(Material.DEEPSLATE), 15);
		//
		initLens("ingotlens", 2);
		addResource("ingotlens", new ItemStack(Material.IRON_ORE), 30);
		addResource("ingotlens", new ItemStack(Material.DEEPSLATE_IRON_ORE), 10);
		addResource("ingotlens", new ItemStack(Material.GOLD_ORE), 5);
		addResource("ingotlens", new ItemStack(Material.DEEPSLATE_GOLD_ORE), 5);
		addResource("ingotlens", new ItemStack(Material.COPPER_ORE), 40);
		addResource("ingotlens", new ItemStack(Material.DEEPSLATE_COPPER_ORE), 1);
		//
		initLens("gemlens", 3);
		addResource("gemlens", new ItemStack(Material.AIR), 118);
		addResource("gemlens", new ItemStack(Material.REDSTONE_ORE), 20);
		addResource("gemlens", new ItemStack(Material.DEEPSLATE_REDSTONE_ORE), 20);
		addResource("gemlens", new ItemStack(Material.LAPIS_ORE), 15);
		addResource("gemlens", new ItemStack(Material.DEEPSLATE_LAPIS_ORE), 15);
		addResource("gemlens", new ItemStack(Material.EMERALD_ORE), 2);
		addResource("gemlens", new ItemStack(Material.DEEPSLATE_EMERALD_ORE), 1);
		addResource("gemlens", new ItemStack(Material.DIAMOND_ORE), 5);
		addResource("gemlens", new ItemStack(Material.DEEPSLATE_DIAMOND_ORE), 4);
		//
		initLens("netherlens", 4);
		addResource("netherlens", new ItemStack(Material.NETHERRACK), 250);
		addResource("netherlens", new ItemStack(Material.NETHER_GOLD_ORE), 100);
		addResource("netherlens", new ItemStack(Material.NETHER_QUARTZ_ORE), 80);
		addResource("netherlens", new ItemStack(Material.SOUL_SAND), 150);
		addResource("netherlens", new ItemStack(Material.BONE_BLOCK), 40);
		addResource("netherlens", new ItemStack(Material.BLACKSTONE), 150);
		addResource("netherlens", new ItemStack(Material.GRAVEL), 250);
		addResource("netherlens", new ItemStack(Material.BASALT), 150);
		addResource("netherlens", new ItemStack(Material.ANCIENT_DEBRIS), 10);
		addResource("netherlens", new ItemStack(Material.GLOWSTONE), 160);
		addResource("netherlens", ItemManager.createCustomChest("Nether Fortress"), 1);
		addResource("netherlens", ItemManager.createCustomChest("Bastion"), 1);
		//
		initLens("condensedlens", 4);
		addResource("condensedlens", new ItemStack(Material.AIR), 142);
		addResource("condensedlens", IngredientManager.CondensedAmethyst, 8);
		addResource("condensedlens", IngredientManager.CondensedCopperIngot, 12);
		addResource("condensedlens", IngredientManager.CondensedDiamond, 1);
		addResource("condensedlens", IngredientManager.CondensedEmerald, 1);
		addResource("condensedlens", IngredientManager.CondensedGlowstone, 4);
		addResource("condensedlens", IngredientManager.CondensedGoldIngot, 9);
		addResource("condensedlens", IngredientManager.CondensedIronIngot, 10);
		addResource("condensedlens", IngredientManager.CondensedLapis, 6);
		addResource("condensedlens", IngredientManager.CondensedRedstone, 7);
		//
		initLens("endlens", 5);
		addResource("endlens", new ItemStack(Material.END_STONE), 499);
		addResource("endlens", new ItemStack(Material.PURPUR_BLOCK), 250);
		addResource("endlens", new ItemStack(Material.CHORUS_FRUIT), 100);
		addResource("endlens", new ItemStack(Material.CHORUS_FLOWER), 50);
		addResource("endlens", new ItemStack(Material.END_ROD), 100);
		addResource("endlens", ItemManager.createCustomChest("End City"), 1);
		//
		initLens("radiatedlens", 5);
		addResource("radiatedlens", new ItemStack(Material.AIR), 389);
		addResource("radiatedlens", IngredientManager.CondensedAmethyst, 500);
		addResource("radiatedlens", IngredientManager.UraniumIngot, 100);
		addResource("radiatedlens", IngredientManager.ThoriumIngot, 10);
		addResource("radiatedlens", IngredientManager.NeptuniumIngot, 1);
		//
		initLens("gardenlens", 3);
		addResource("gardenlens", new ItemStack(Material.WHEAT), 25);
		addResource("gardenlens", new ItemStack(Material.POTATO), 25);
		addResource("gardenlens", new ItemStack(Material.CARROT), 25);
		addResource("gardenlens", new ItemStack(Material.BEETROOT), 25);
		addResource("gardenlens", new ItemStack(Material.PUMPKIN), 15);
		addResource("gardenlens", new ItemStack(Material.MELON), 15);
		addResource("gardenlens", new ItemStack(Material.APPLE), 15);
		addResource("gardenlens", new ItemStack(Material.SWEET_BERRIES), 10);
		addResource("gardenlens", new ItemStack(Material.GLOW_BERRIES), 1);
		//
		initLens("explens", 5);
		addResource("explens", new ItemStack(Material.AIR), 98);
		addResource("explens", new ItemStack(Material.EXPERIENCE_BOTTLE), 2);
		//
	}
	
	private static void addResource(String lens, ItemStack inputItem, int weight) {
		ArrayList<ItemStack> array = VoidOreMinerResources.get(lens);
		for(int i=0; i < weight; i++) {
			array.add(inputItem);
		}
		VoidOreMinerResources.put(lens, array);
	}
	private static void initLens(String lens, int tier) {
		ArrayList<ItemStack> array = new ArrayList<ItemStack>();
		VoidOreMinerResources.put(lens, new ArrayList<ItemStack>(array));
		VoidOreMinerLenses.put(lens, tier);
	}
	private static String getLensType(String inputID){
		Set<String> keyset = VoidOreMinerResources.keySet();
		String[] keyArray = new String[keyset.size()];
		keyset.toArray(keyArray);
		String output = "none";
		for(int i=0; i<keyArray.length; i++) {
			if(keyArray[i].equals(inputID)) {
				output = inputID;
			}
		}
		return output;
	}
	
}
