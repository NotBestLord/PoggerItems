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
import me.notbestlord.Plugin.items.ItemManager;
import net.md_5.bungee.api.ChatColor;

public class OilExtractorEvents implements Listener{
	
	public static Hashtable<UUID, Integer> OilExtractorActivness = new Hashtable<UUID, Integer>();
	
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock() == null) {
				return;
			}
			if(event.getClickedBlock().getType().equals(Material.HOPPER)) {
				Location BottomBlockLocation = event.getClickedBlock().getLocation();
				Location TopBlockLocation = event.getClickedBlock().getLocation();
				BottomBlockLocation.subtract(0, 1, 0);
				TopBlockLocation.add(0, 1, 0);
				if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.NETHER_BRICK_FENCE)) {
					if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
						event.setCancelled(true);
						if(!(new Machine(event.getClickedBlock().getLocation(), "OilExtractor").MachineInArray(Main.MachineList))) {
							if (event.getPlayer().getInventory().getItemInMainHand().equals(ItemManager.MultiBlockHammer) || event.getPlayer().getInventory().getItemInMainHand().equals(ItemManager.RadiatedMultiBlockHammer)){
								OilExtractorInventory.createOilExtractor(event.getClickedBlock().getLocation());
								Machine machine = new Machine(event.getClickedBlock().getLocation(), OilExtractorInventory.inventory.get(event.getClickedBlock().getLocation()), "OilExtractor");
								Main.MachineList.add(machine);
								event.getPlayer().openInventory(OilExtractorInventory.inventory.get(event.getClickedBlock().getLocation()));
							}
						}
						else {
							event.getPlayer().openInventory(OilExtractorInventory.inventory.get(event.getClickedBlock().getLocation()));
						}
					}
				}
			}
		}
	}
	@EventHandler
	public static void onBreakBlock(BlockBreakEvent event) {
		if(event.getBlock().getType().equals(Material.HOPPER)) {
			Location BottomBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			BottomBlockLocation.subtract(0, 1, 0);
			TopBlockLocation.add(0, 1, 0);
			if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.NETHER_BRICK_FENCE)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
					if(OilExtractorInventory.inventory.containsKey(event.getBlock().getLocation())) {
						OilExtractorInventory.inventory.remove(event.getBlock().getLocation());
						Machine.DeleteMachineFromList(new Machine(event.getBlock().getLocation(), "OilExtractor"));
					}
				}
			}
		}
		if(event.getBlock().getType().equals(Material.NETHER_BRICK_FENCE)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.add(0, 1, 0);
			TopBlockLocation.add(0, 2, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.HOPPER)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
					if(OilExtractorInventory.inventory.containsKey(BaseBlockLocation)) {
						OilExtractorInventory.inventory.remove(BaseBlockLocation);
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "OilExtractor"));
					}
				}
			}
		}
		if(event.getBlock().getType().equals(Material.IRON_TRAPDOOR)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location BottomBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.subtract(0, 1, 0);
			BottomBlockLocation.subtract(0, 2, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.HOPPER)) {
				if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.NETHER_BRICK_FENCE)) {
					if(OilExtractorInventory.inventory.containsKey(BaseBlockLocation)) {
						OilExtractorInventory.inventory.remove(BaseBlockLocation);
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "OilExtractor"));
					}
				}
			}
		}
	}
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(!event.getView().getTitle().equals(ChatColor.AQUA+"Oil Extractor") || event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
			return;
		}
		if(event.getSlot() != 13) {
			event.setCancelled(true);
		}
		
		Player player = (Player)event.getWhoClicked();
		if(event.getSlot() == 22) {
			player.closeInventory();
		}
		if(event.getSlot() == 10) {
			if(event.getClickedInventory().getItem(13) == null) {
				return;
			}
			if(event.getClickedInventory().getItem(13).getAmount() != 1) {
				player.sendMessage(ChatColor.RED + "Place a SINGLE Tank in output slot");
				return;
			}
			if(!event.getClickedInventory().getItem(13).hasItemMeta()) {
				player.sendMessage(ChatColor.RED + "Place Tank in output slot");
				return;
			}
			if(!event.getClickedInventory().getItem(13).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING)) {
				player.sendMessage(ChatColor.RED + "Place Tank in output slot");
				return;
			}
			String tankFluidType = event.getClickedInventory().getItem(13).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING);
			if((tankFluidType.equals("") || tankFluidType.equals("crudeoil")) && event.getClickedInventory().getItem(10).getType().equals(Material.LIME_STAINED_GLASS_PANE)) {
				ItemStack tank = new ItemStack(event.getClickedInventory().getItem(13));
				ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
				ItemMeta meta1 = item.getItemMeta();
				meta1.setDisplayName(ChatColor.WHITE+"Stop Oil Extractor");
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.WHITE+"Produces 10mb of crude oil every second");
				lore.add(ChatColor.WHITE+"and costs 200RF from the player using it.");
				lore.add(ChatColor.WHITE+"Oil extractor currently active.");
				meta1.setLore(lore);
				lore.clear();
				item.setItemMeta(meta1);
				event.getClickedInventory().setItem(10, item);
				OilExtractorActivness.put(player.getUniqueId(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
					
					@Override
					public void run() {
						if(!(event.getClickedInventory().getItem(13) == null)) {
							if(tank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+10 <= tank.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)){
								if(Main.RedstoneFluxManager.reduceRedstoneFlux(player, 200)) {
									ItemMeta meta = tank.getItemMeta();
									meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, "crudeoil");
									meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER, meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+10);
									List<String> lore = new ArrayList<>();
									lore.addAll(meta.getLore());
									lore.set(2, "§7"+meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of Crude oil.");
									meta.setLore(lore);
									tank.setItemMeta(meta);
									event.getClickedInventory().setItem(13, tank);
									
									
								}
								else {
									Bukkit.getScheduler().cancelTask(OilExtractorActivness.get(player.getUniqueId()));
									ItemStack item1 = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
									ItemMeta meta = item1.getItemMeta();
									meta.setDisplayName(ChatColor.WHITE+"Use Oil Extractor");
									List<String> lore = new ArrayList<>();
									lore.add(ChatColor.WHITE+"Produces 10mb of crude oil every second");
									lore.add(ChatColor.WHITE+"and costs 200RF from the player using it");
									meta.setLore(lore);
									lore.clear();
									item1.setItemMeta(meta);
									event.getClickedInventory().setItem(10, item1);
								}
							}
							else {
								player.sendMessage(ChatColor.RED+"Tank full.");
								Bukkit.getScheduler().cancelTask(OilExtractorActivness.get(player.getUniqueId()));
								ItemStack item1 = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
								ItemMeta meta = item1.getItemMeta();
								meta.setDisplayName(ChatColor.WHITE+"Use Oil Extractor");
								List<String> lore = new ArrayList<>();
								lore.add(ChatColor.WHITE+"Produces 10mb of crude oil every second");
								lore.add(ChatColor.WHITE+"and costs 200RF from the player using it");
								meta.setLore(lore);
								lore.clear();
								item1.setItemMeta(meta);
								event.getClickedInventory().setItem(10, item1);
							}
						}
						else {
							Bukkit.getScheduler().cancelTask(OilExtractorActivness.get(player.getUniqueId()));
							ItemStack item1 = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
							ItemMeta meta = item1.getItemMeta();
							meta.setDisplayName(ChatColor.WHITE+"Use Oil Extractor");
							List<String> lore = new ArrayList<>();
							lore.add(ChatColor.WHITE+"Produces 10mb of crude oil every second");
							lore.add(ChatColor.WHITE+"and costs 200RF from the player using it");
							meta.setLore(lore);
							lore.clear();
							item1.setItemMeta(meta);
							event.getClickedInventory().setItem(10, item1);
						}
					}
				}, 0L, 20L));
				
			}
			else if(event.getClickedInventory().getItem(10).getType().equals(Material.RED_STAINED_GLASS_PANE)) {
				if(!OilExtractorActivness.containsKey(player.getUniqueId())) {
					return;
				}
				Bukkit.getScheduler().cancelTask(OilExtractorActivness.get(player.getUniqueId()));
				ItemStack item1 = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
				ItemMeta meta = item1.getItemMeta();
				meta.setDisplayName(ChatColor.WHITE+"Use Oil Extractor");
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.WHITE+"Produces 10mb of crude oil every second");
				lore.add(ChatColor.WHITE+"and costs 200RF from the player using it");
				meta.setLore(lore);
				lore.clear();
				item1.setItemMeta(meta);
				event.getClickedInventory().setItem(10, item1);
			}
			else {
				player.sendMessage(ChatColor.RED + "Tank contains ");
				return;
			}
		}
	}
}
