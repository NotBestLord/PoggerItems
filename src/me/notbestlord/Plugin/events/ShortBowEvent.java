package me.notbestlord.Plugin.events;


import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ArrowBodyCountChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.projectiles.ProjectileSource;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.ItemManager;
import net.minecraft.world.entity.projectile.EntityArrow;


public class ShortBowEvent implements Listener{
	
	@EventHandler
	private void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
			return;
		if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
			if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))
					return;
			if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("shortbow")) {
				if(event.getPlayer().getInventory().first(Material.ARROW)!=-1 || event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.ARROW_INFINITE)) {
					event.setCancelled(true);
					Player p = event.getPlayer();
					Arrow arrow = p.getWorld().spawnArrow(p.getLocation().add(0, 1, 0), p.getLocation().getDirection(), 2.0f, 4.0f);
					arrow.setDamage(arrow.getDamage()*(1+(5/(double)event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.ARROW_DAMAGE))));
					arrow.setShooter((ProjectileSource)p);
					arrow.setFireTicks(200*event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.ARROW_FIRE));
					arrow.setKnockbackStrength(1*event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK));
					if(!event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.ARROW_INFINITE))
						p.getInventory().setItem(event.getPlayer().getInventory().first(Material.ARROW) ,new ItemStack(Material.ARROW, p.getInventory().getItem(p.getInventory().first(Material.ARROW)).getAmount()-1));
					}
			}
		}
	}
}
