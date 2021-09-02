package me.notbestlord.Plugin.events;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;

public class PortableSmelteryEvent implements Listener{

	private Hashtable<UUID, Inventory> inventory = new Hashtable<>();
	
	private void createSmeltery(Player player, Block furnace, ItemStack item) {
		Furnace furnaceBlock = (Furnace) furnace.getState();
		inventory.put(player.getUniqueId(), furnaceBlock.getInventory());
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<>();
		lore.addAll(meta.getLore());
		lore.set(3, ChatColor.GRAY+"Current Furnace - Dimension: "+furnace.getWorld().getEnvironment());
		lore.set(4, ChatColor.GRAY+"Coordinates: {"+furnace.getLocation().getBlockX()+", "+furnace.getLocation().getBlockY()+", "+furnace.getLocation().getBlockZ()+"}.");
		meta.setLore(lore);
		item.setItemMeta(meta);
	}
	
	@EventHandler
	private void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getItem() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))
					return;
				if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("portablesmeltery")) {
					if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
						if(event.getClickedBlock().getState() instanceof Furnace) {
							createSmeltery(event.getPlayer(), event.getClickedBlock(), event.getPlayer().getInventory().getItemInMainHand());
						}
						else if(inventory.keySet().contains(event.getPlayer().getUniqueId())) {
							event.getPlayer().openInventory(inventory.get(event.getPlayer().getUniqueId()));
						}
					}
					else if(inventory.keySet().contains(event.getPlayer().getUniqueId())) {
						event.getPlayer().openInventory(inventory.get(event.getPlayer().getUniqueId()));
					}
					
				}
			}
		}
	}
}
