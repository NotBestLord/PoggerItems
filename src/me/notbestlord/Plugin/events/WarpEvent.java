package me.notbestlord.Plugin.events;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;

public class WarpEvent implements Listener{
	
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))
					return;
				if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("warpwand")) {
					Player p = event.getPlayer();
					Set<Material> lst = new HashSet<>(Arrays.asList(Material.AIR,Material.CAVE_AIR));
					List<Block> lineofsight = p.getLineOfSight(lst, 5+2*event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Range"), PersistentDataType.INTEGER));
					Location location = lineofsight.get(lineofsight.size()-1).getLocation();
					int cost = 50;
					Location finallocation = new Location(
							location.getWorld(),
							location.getX() + 0.5f,
							location.getY() + 1,
							location.getZ() + 0.5f,
							p.getLocation().getYaw(),
							p.getLocation().getPitch());
					if(!p.getGameMode().equals(GameMode.CREATIVE)) {
						if(Main.RedstoneFluxManager.reduceRedstoneFlux(p, cost)) {
							p.setFallDistance(0);
							p.teleport(finallocation);
						}else {
							p.sendMessage("§cNot enough redstone-flux");
						}
					}else {
						p.setFallDistance(0);
						p.teleport(finallocation);
					}
				}
			}
		}
	}
	
}
