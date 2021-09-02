package me.notbestlord.Plugin.events;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;

public class ReinforcedTridentEvent implements Listener{
	
	@EventHandler
	private void onRightClick(EntityDamageByEntityEvent event) {
		if(!(event.getDamager() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getDamager();
		
		if(player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta()) {
			if(player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
				if(player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("reinforcedtrident")) {
					player.setInvulnerable(true);
					player.getWorld().strikeLightning(event.getEntity().getLocation());
					player.setInvulnerable(false);
				}
			}
		}
	}
	
}
