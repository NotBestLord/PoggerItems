package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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
import me.notbestlord.Plugin.items.IngredientManager;
import me.notbestlord.Plugin.items.ItemManager;
import net.md_5.bungee.api.ChatColor;

public class OilProcessorEvents implements Listener{
	
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock() == null) {
				return;
			}
			if(event.getClickedBlock().getType().equals(Material.CAULDRON)) {
				Location BottomBlockLocation = event.getClickedBlock().getLocation();
				Location TopBlockLocation = event.getClickedBlock().getLocation();
				BottomBlockLocation.subtract(0, 1, 0);
				TopBlockLocation.add(0, 1, 0);
				if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.CAULDRON)) {
					if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
						event.setCancelled(true);
						if(!(new Machine(event.getClickedBlock().getLocation(), "OilProcessor").MachineInArray(Main.MachineList))) {
							if (event.getPlayer().getInventory().getItemInMainHand().equals(ItemManager.MultiBlockHammer) || event.getPlayer().getInventory().getItemInMainHand().equals(ItemManager.RadiatedMultiBlockHammer)){
								OilProcessorInventory.createOilProcessor(event.getClickedBlock().getLocation());
								Machine machine = new Machine(event.getClickedBlock().getLocation(), OilProcessorInventory.inventory.get(event.getClickedBlock().getLocation()), "OilProcessor");
								Main.MachineList.add(machine);
								event.getPlayer().openInventory(OilProcessorInventory.inventory.get(event.getClickedBlock().getLocation()));
							}
						}
						else {
							event.getPlayer().openInventory(OilProcessorInventory.inventory.get(event.getClickedBlock().getLocation()));
						}
					}
				}
			}
		}
	}
	@EventHandler
	public static void onBreakBlock(BlockBreakEvent event) {
		if(event.getBlock().getType().equals(Material.CAULDRON)) {
			Location BottomBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			BottomBlockLocation.subtract(0, 1, 0);
			TopBlockLocation.add(0, 1, 0);
			if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.CAULDRON)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
					if(OilProcessorInventory.inventory.containsKey(event.getBlock().getLocation())) {
						OilProcessorInventory.inventory.remove(event.getBlock().getLocation());
						Machine.DeleteMachineFromList(new Machine(event.getBlock().getLocation(), "OilProcessor"));
					}
				}
			}
		}
		if(event.getBlock().getType().equals(Material.CAULDRON)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.add(0, 1, 0);
			TopBlockLocation.add(0, 2, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.CAULDRON)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
					if(OilProcessorInventory.inventory.containsKey(BaseBlockLocation)) {
						OilProcessorInventory.inventory.remove(BaseBlockLocation);
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "OilProcessor"));
					}
				}
			}
		}
		if(event.getBlock().getType().equals(Material.IRON_TRAPDOOR)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location BottomBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.subtract(0, 1, 0);
			BottomBlockLocation.subtract(0, 2, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.CAULDRON)) {
				if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.CAULDRON)) {
					if(OilProcessorInventory.inventory.containsKey(BaseBlockLocation)) {
						OilProcessorInventory.inventory.remove(BaseBlockLocation);
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "OilProcessor"));
					}
				}
			}
		}
	}
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(!event.getView().getTitle().equals(ChatColor.AQUA+"Oil Processor") || event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
			return;
		}
		if(event.getSlot() != 10 && event.getSlot() != 16) {
			event.setCancelled(true);
		}
		
		Player player = (Player)event.getWhoClicked();
		if(event.getSlot() == 22) {
			player.closeInventory();
		}
		if(event.getSlot() == 12 || event.getSlot() == 14) {
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
			String tankFluidType = event.getClickedInventory().getItem(10).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING);
			if(tankFluidType.equals("crudeoil")) {
				ItemStack tank = new ItemStack(event.getClickedInventory().getItem(10));
				ItemMeta tankMeta = tank.getItemMeta();
				List<String> lore = new ArrayList<>();
				if(event.getSlot() == 12) {
					if(tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)-500 >= 0) {
						tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER, tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)-500);
						if(tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER) == 0) {
							tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, "");
							lore.addAll(tankMeta.getLore());
							lore.set(2, "§7"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of none.");
							tankMeta.setLore(lore);
							lore.clear();
						}
						else {
							lore.addAll(tankMeta.getLore());
							lore.set(2, "§7"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of Crude oil.");
							tankMeta.setLore(lore);
							lore.clear();
						}
						tank.setItemMeta(tankMeta);
						event.getClickedInventory().setItem(10, tank);
						if(event.getClickedInventory().getItem(16) == null) {
							event.getClickedInventory().setItem(16, IngredientManager.Plastic);
						}
						else if(event.getClickedInventory().getItem(16).hasItemMeta()) {
							if(event.getClickedInventory().getItem(16).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)) {
								if(event.getClickedInventory().getItem(16).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING).equals("plastic")) {
									ItemStack out = new ItemStack(event.getClickedInventory().getItem(16));
									out.setAmount(out.getAmount()+1);
									event.getClickedInventory().setItem(16, out);
								}
							}
							else {
								player.sendMessage(ChatColor.RED + "Empty output slot");
								return;
							}
						}
						else {
							player.sendMessage(ChatColor.RED + "Empty output slot");
							return;
						}
					}
					else {
						player.sendMessage(ChatColor.RED+"Not enough crude oil in tank.");
						return;
					}
				}
				else if(event.getSlot() == 14) {
					if(tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)-250 >= 0) {
						tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER, tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)-250);
						if(tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER) == 0) {
							tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, "");
							lore.addAll(tankMeta.getLore());
							lore.set(2, "§7"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of none.");
							tankMeta.setLore(lore);
							lore.clear();
						}
						else {
							lore.addAll(tankMeta.getLore());
							lore.set(2, "§7"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of Crude oil.");
							tankMeta.setLore(lore);
							lore.clear();
						}
						tank.setItemMeta(tankMeta);
						event.getClickedInventory().setItem(10, tank);
						if(event.getClickedInventory().getItem(16) == null) {
							event.getClickedInventory().setItem(16, IngredientManager.Rubber);
						}
						else if(event.getClickedInventory().getItem(16).hasItemMeta()) {
							if(event.getClickedInventory().getItem(16).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)) {
								if(event.getClickedInventory().getItem(16).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING).equals("rubber")) {
									ItemStack out = new ItemStack(event.getClickedInventory().getItem(16));
									out.setAmount(out.getAmount()+1);
									event.getClickedInventory().setItem(16, out);
								}
							}
							else {
								player.sendMessage(ChatColor.RED + "Empty output slot");
								return;
							}
						}
						else {
							player.sendMessage(ChatColor.RED + "Empty output slot");
							return;
						}
					}
					else {
						player.sendMessage(ChatColor.RED+"Not enough crude oil in tank.");
						return;
					}
					
				}
			}
		}
	}
}
