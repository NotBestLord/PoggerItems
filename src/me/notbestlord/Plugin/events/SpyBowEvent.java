package me.notbestlord.Plugin.events;


import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.projectiles.ProjectileSource;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.ItemManager;


public class SpyBowEvent implements Listener{
	
	@EventHandler
	public static void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
		if(event.getPlayer().isSneaking()) {
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))
					return;
				if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("spybow")) {
					if(event.getPlayer().getInventory().first(Material.ARROW)!=-1 || event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Infinite"), PersistentDataType.INTEGER) == 1) {
						Player p = event.getPlayer();
						ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
						if(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Arrow_speed"), PersistentDataType.INTEGER) == 0) {
							p.getWorld().spawnArrow(p.getLocation().add(0, 1, 0), p.getLocation().getDirection(), 3.0f, 0.0f);
						}else {
							p.getWorld().spawnArrow(p.getLocation().add(0, 1, 0), p.getLocation().getDirection(), 3.0f*item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Arrow_speed"), PersistentDataType.INTEGER), 0.0f);
							
						}
						if(!(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Infinite"), PersistentDataType.INTEGER) == 1))
							p.getInventory().setItem(event.getPlayer().getInventory().first(Material.ARROW) ,new ItemStack(Material.ARROW, p.getInventory().getItem(p.getInventory().first(Material.ARROW)).getAmount()-1));
					}
				}
			}
		}
	}
}
