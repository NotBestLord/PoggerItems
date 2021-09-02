package me.notbestlord.Plugin.events;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.notbestlord.Plugin.Main;

public class ChillyStaffEvent implements Listener{
	
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if(event.getHand() == EquipmentSlot.OFF_HAND) {
			return;
		}
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))
					return;
				if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("chillystaff")) {
					Player player = event.getPlayer();
					for(Entity e : player.getWorld().getNearbyEntities(player.getLocation(), 10, 10, 10)) {
						if(!(e instanceof Player)) {
							if(e instanceof LivingEntity) {
								LivingEntity en = (LivingEntity) e;
								en.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 1));
							}
						}
					}
				}
			}
		}
	}
}
