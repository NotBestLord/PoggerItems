package me.notbestlord.Plugin.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.CoalGeneratorInventory;
import net.md_5.bungee.api.ChatColor;

public class CoalGeneratorEvent implements Listener{
	
	private static int CoalGeneratorTimer = 0;
	private static int coalgeneratortask = 0;
	
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
				return;
				}
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("coalgenerator")) {
					return;
				}
				event.getPlayer().openInventory(CoalGeneratorInventory.inventory.get(event.getPlayer().getUniqueId()));
			}
		}
	}
	@EventHandler
	public static void onLeftClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
				return;
				}
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("coalgenerator")) {
					return;
				}
				if(event.getAction() == Action.LEFT_CLICK_BLOCK) {
					event.setCancelled(true);
				}
				Inventory inventory = CoalGeneratorInventory.inventory.get(event.getPlayer().getUniqueId());
				if(!inventory.contains(Material.COAL)) {
					event.getPlayer().sendMessage(ChatColor.RED+"No coal in generator");
					return;
				}
				
				if(!CoalGeneratorInventory.coalgeneratoractivness.get(event.getPlayer().getUniqueId())) {
					CoalGeneratorInventory.coalgeneratoractivness.put(event.getPlayer().getUniqueId(), true);
					event.getPlayer().sendMessage("Activating Generator");
					coalgeneratortask = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
						
						@Override
						public void run() {
							if(!inventory.contains(Material.COAL)) {
								event.getPlayer().sendMessage(ChatColor.RED+"The generator ran out of coal");
								CoalGeneratorInventory.coalgeneratoractivness.put(event.getPlayer().getUniqueId(), false);
								Bukkit.getScheduler().cancelTask(coalgeneratortask);
								return;
							}
							CoalGeneratorTimer++;
							if(CoalGeneratorTimer==1) {
								ItemStack coal = new ItemStack(Material.COAL, inventory.getItem(inventory.first(Material.COAL)).getAmount()-1);
								inventory.setItem(inventory.first(Material.COAL), coal);
							}
							if(CoalGeneratorTimer==5) {
								CoalGeneratorTimer=0;
							}
							Main.RedstoneFluxManager.increaseRedstoneFlux(event.getPlayer(), 2);
						}
					}, 0L, 20L);
				}else {
					CoalGeneratorInventory.coalgeneratoractivness.put(event.getPlayer().getUniqueId(), false);
					Bukkit.getScheduler().cancelTask(coalgeneratortask);
					event.getPlayer().sendMessage("Deactivating Generator");
					CoalGeneratorTimer=0;
				}
				
			}
		}
	}
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(event.getInventory().equals(CoalGeneratorInventory.inventory.get(event.getWhoClicked().getUniqueId()))) {
			if(event.getSlot() == event.getWhoClicked().getInventory().getHeldItemSlot()) {
				event.setCancelled(true);
			}
		}
	}
}
