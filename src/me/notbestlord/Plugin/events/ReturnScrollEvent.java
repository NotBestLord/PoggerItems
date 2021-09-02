package me.notbestlord.Plugin.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;


public class ReturnScrollEvent implements Listener{
	
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getItem() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))
					return;
				if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("returnscroll")) {
					Player p = event.getPlayer();
					p.teleport(p.getBedSpawnLocation().add(0,1,0));
					if(p.getGameMode().equals(GameMode.SURVIVAL)) {
						p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
					}
				}
			}
		}
	}
	
}
