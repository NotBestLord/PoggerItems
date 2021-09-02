package me.notbestlord.Plugin.events;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;

public class UnplaceableEvent implements Listener{
	
	@EventHandler
	private void onPlaceBlock(BlockPlaceEvent event) {
		if(event.getItemInHand() != null && event.getItemInHand().hasItemMeta()) {
			if(event.getItemInHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "unplaceable"), PersistentDataType.INTEGER)) {
				event.setCancelled(true);
			}
		}
	}
	
}
