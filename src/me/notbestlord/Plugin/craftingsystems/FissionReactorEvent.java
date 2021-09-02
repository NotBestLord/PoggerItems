package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.IngredientManager;
import net.md_5.bungee.api.ChatColor;

public class FissionReactorEvent implements Listener{
	
	public static Hashtable<String, ArrayList> Fuels = new Hashtable<String, ArrayList>();
	public static Hashtable<UUID, Boolean> FissionReactorActivness = new Hashtable<UUID, Boolean>();
	public static Hashtable<UUID, Integer> FissionReactorCounter = new Hashtable<UUID, Integer>();
	public static int FissionTask;
	
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(event.getInventory().equals(FissionReactorInventory.inventory.get(event.getWhoClicked().getUniqueId()))) {
			if(event.getSlot() == event.getWhoClicked().getInventory().getHeldItemSlot()) {
				event.setCancelled(true);
			}
		}
		if(!event.getClickedInventory().equals(FissionReactorInventory.inventory.get(event.getWhoClicked().getUniqueId()))) {
			return;
		}
		if(event.getSlot() != 19 && event.getSlot() != 22 && event.getSlot() != 25) {
			event.setCancelled(true);
		}
		
		Player player = (Player)event.getWhoClicked();
		if(event.getSlot() == 49) {
			player.closeInventory();
		}
		if(event.getSlot() == 13 && !FissionReactorActivness.get(player.getUniqueId())) {
			if(!(event.getClickedInventory().getItem(19) == null) || !(event.getClickedInventory().getItem(25) == null) || event.getClickedInventory().getItem(22) == null) {
				return;
			}
			if(!event.getClickedInventory().getItem(22).hasItemMeta()) {
				return;
			}
			if(!event.getClickedInventory().getItem(22).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)) {
				return;
			}
			String key = event.getClickedInventory().getItem(22).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING);
			if(Fuels.containsKey(key)) {
				ItemStack in = event.getClickedInventory().getItem(22);
				in.setAmount(in.getAmount()-1);
				event.getClickedInventory().setItem(22, in);
				int fuelTime = (Integer) Fuels.get(key).get(0);
				player.playSound(player.getLocation(), Sound.BLOCK_ANCIENT_DEBRIS_BREAK, 1, 1);
				
				FissionReactorActivness.put(player.getUniqueId(), true);
				FissionReactorCounter.put(player.getUniqueId(), fuelTime);
				FissionTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
					
					@Override
					public void run() {
						int byproductAmount = (Integer) Fuels.get(key).get(2);
						ItemStack byprodcut = (ItemStack) Fuels.get(key).get(1);
						byprodcut.setAmount(byproductAmount);
						int RFperSecond = (Integer) Fuels.get(key).get(3);
						double count = FissionReactorCounter.get(player.getUniqueId());
						double time = ((Integer) Fuels.get(key).get(0));
						double difference = count / time;
						difference*=100;
						ItemStack item1 = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
						ItemMeta meta = item1.getItemMeta();
						if(difference == 80) {
							meta.setDisplayName(ChatColor.WHITE+"20%");
							item1.setItemMeta(meta);
							event.getClickedInventory().setItem(38, item1);
						}
						else if(difference == 60) {
							meta.setDisplayName(ChatColor.WHITE+"40%");
							item1.setItemMeta(meta);
							event.getClickedInventory().setItem(39, item1);
						}
						else if(difference == 40) {
							meta.setDisplayName(ChatColor.WHITE+"60%");
							item1.setItemMeta(meta);
							event.getClickedInventory().setItem(40, item1);
						}
						else if(difference == 20) {
							meta.setDisplayName(ChatColor.WHITE+"80%");
							item1.setItemMeta(meta);
							event.getClickedInventory().setItem(41, item1);
						}
						else if(difference == 1) {
							meta.setDisplayName(ChatColor.WHITE+"100%");
							item1.setItemMeta(meta);
							event.getClickedInventory().setItem(42, item1);
						}
						
						if(FissionReactorCounter.get(player.getUniqueId()) == 0) {
							event.getClickedInventory().setItem(19, IngredientManager.EmptyFuelCell);
							event.getClickedInventory().setItem(25, byprodcut);
							player.playSound(player.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1);
							
							FissionReactorInventory.resetProgressBar(player);
							FissionReactorActivness.put(player.getUniqueId(), false);
							Bukkit.getScheduler().cancelTask(FissionTask);
							
						}
						Main.RedstoneFluxManager.setRedstoneFlux(player, Main.RedstoneFluxManager.getRedstoneFlux(player)+RFperSecond);
						FissionReactorCounter.put(player.getUniqueId(), FissionReactorCounter.get(player.getUniqueId())-1);
						
					}
				}, 0L, 20L);
				
			}
		}
	}
	
	public static void addFuel(String inputItem, ItemStack byproduct, int fuelTime, int byproductAmount, int RFperSecond) {
		ArrayList array = new ArrayList<>();
		array.add(fuelTime);
		array.add(byproduct);
		array.add(byproductAmount);
		array.add(RFperSecond);
		Fuels.put(inputItem, new ArrayList<>(array));
		array.clear();
	}
	
}
