package me.notbestlord.Plugin.armorsetsevents;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;

public class RobeEvents implements Listener {
	
	@EventHandler
	private void onPlayerDealDamage(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			ItemStack item = player.getEquipment().getChestplate();
			if(item != null) {
				if(item.hasItemMeta()) {
					if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
						if(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("executionerrobe")) {
							Material m = player.getInventory().getItemInMainHand().getType();
							if(m == Material.WOODEN_AXE || m == Material.STONE_AXE || m == Material.IRON_AXE || m == Material.GOLDEN_AXE || m == Material.DIAMOND_AXE || m == Material.NETHERITE_AXE) {
								event.setDamage(event.getDamage()*2);
							}
						}
						else if(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("warriorrobe")) {
							Material m = player.getInventory().getItemInMainHand().getType();
							if(m == Material.WOODEN_SWORD || m == Material.STONE_SWORD || m == Material.IRON_SWORD || m == Material.GOLDEN_SWORD || m == Material.DIAMOND_SWORD || m == Material.NETHERITE_SWORD) {
								event.setDamage(event.getDamage()*2);
							}
						}
					}
				}
			}
		}
		else if(event.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getDamager();
			if(arrow.getShooter() == null) {
				return;
			}
			if(!(arrow.getShooter() instanceof Player)) {
				return;
			}
			Player player = (Player) arrow.getShooter();
			ItemStack item = player.getEquipment().getChestplate();
			if(item != null) {
				if(item.hasItemMeta()) {
					if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
						if(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("rangedrobe")) {
							event.setDamage(event.getDamage()*2);
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	private void onPlayerTargeted(EntityTargetEvent event) {
		if(!(event.getTarget() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getTarget();
		ItemStack item = player.getEquipment().getChestplate();
		if(item != null) {
			if(item.hasItemMeta()) {
				if(item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
					if(item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("scoutrobe")) {
						if(!player.isSneaking()) {
							return;
						}
						if(event.getEntity().getLocation().distance(player.getLocation()) < 25) {
							event.setCancelled(true);
						}
					}
				}
			}
		}
	}
}
