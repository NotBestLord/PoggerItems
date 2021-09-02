package me.notbestlord.Plugin.events;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Particle.DustTransition;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import me.notbestlord.Plugin.Main;

public class CustomStatsEvent implements Listener{
	
	@EventHandler
	private void onPlayerHit(EntityDamageByEntityEvent event) {
		if(!(event.getDamager() instanceof Player) && !(event.getDamager() instanceof Arrow)) {
			return;
		}
		Player player = null;
		if(event.getDamager() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getDamager();
			if(arrow.getShooter() != null) {
				if(arrow.getShooter() instanceof Player) {
					player = (Player) arrow.getShooter();
				}
				else {
					return;
				}
			}
			else {
				return;
			}
		}
		else {
			player = (Player) event.getDamager();
		}
		boolean DualWield=false;
		if(player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta()) {
			if(player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "DualWield"), PersistentDataType.INTEGER)) {
				DualWield = true;
			}
		}
		if(player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta()) {
			if(player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "DualWield"), PersistentDataType.INTEGER)) {
				DualWield = true;
			}
		}
		if(DualWield) {
			return;
		}
		// Reach:
		if(event.getDamager() instanceof Player) {
			int reach = 4;
			if(player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta()) {
				if(player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ReachMainHand"), PersistentDataType.INTEGER)) {
					reach+=player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ReachMainHand"), PersistentDataType.INTEGER);
				}
			}
			if(player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta()) {
				if(player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ReachOffHand"), PersistentDataType.INTEGER)) {
					reach+=player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ReachOffHand"), PersistentDataType.INTEGER);
				}
			}
			if(player.getEquipment().getHelmet() != null && player.getEquipment().getHelmet().hasItemMeta()) {
				if(player.getEquipment().getHelmet().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ReachHelmet"), PersistentDataType.INTEGER)) {
					reach+=player.getEquipment().getHelmet().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ReachHelmet"), PersistentDataType.INTEGER);
				}
			}
			if(player.getEquipment().getChestplate() != null && player.getEquipment().getChestplate().hasItemMeta()) {
				if(player.getEquipment().getChestplate().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ReachChestplate"), PersistentDataType.INTEGER)) {
					reach+=player.getEquipment().getChestplate().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ReachChestplate"), PersistentDataType.INTEGER);
				}
			}
			if(player.getEquipment().getLeggings() != null && player.getEquipment().getLeggings().hasItemMeta()) {
				if(player.getEquipment().getLeggings().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ReachLeggings"), PersistentDataType.INTEGER)) {
					reach+=player.getEquipment().getLeggings().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ReachLeggings"), PersistentDataType.INTEGER);
				}
			}
			if(player.getEquipment().getBoots() != null && player.getEquipment().getBoots().hasItemMeta()) {
				if(player.getEquipment().getBoots().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ReachBoots"), PersistentDataType.INTEGER)) {
					reach+=player.getEquipment().getBoots().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ReachBoots"), PersistentDataType.INTEGER);
				}
			}
			if(reach<1) {
				reach=1;
			}
			if(player.getLocation().distance(event.getEntity().getLocation())>reach) {
				event.setCancelled(true);
				return;
			}
		}
		//
		// Ferocity:
		int ferocity = 0;
		if(player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta()) {
			if(player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "FerocityMainHand"), PersistentDataType.INTEGER)) {
				ferocity+=player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "FerocityMainHand"), PersistentDataType.INTEGER);
			}
		}
		if(player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta()) {
			if(player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "FerocityOffHand"), PersistentDataType.INTEGER)) {
				ferocity+=player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "FerocityOffHand"), PersistentDataType.INTEGER);
			}
		}
		if(player.getEquipment().getHelmet() != null && player.getEquipment().getHelmet().hasItemMeta()) {
			if(player.getEquipment().getHelmet().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "FerocityHelmet"), PersistentDataType.INTEGER)) {
				ferocity+=player.getEquipment().getHelmet().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "FerocityHelmet"), PersistentDataType.INTEGER);
			}
		}
		if(player.getEquipment().getChestplate() != null && player.getEquipment().getChestplate().hasItemMeta()) {
			if(player.getEquipment().getChestplate().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "FerocityChestplate"), PersistentDataType.INTEGER)) {
				ferocity+=player.getEquipment().getChestplate().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "FerocityChestplate"), PersistentDataType.INTEGER);
			}
		}
		if(player.getEquipment().getLeggings() != null && player.getEquipment().getLeggings().hasItemMeta()) {
			if(player.getEquipment().getLeggings().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "FerocityLeggings"), PersistentDataType.INTEGER)) {
				ferocity+=player.getEquipment().getLeggings().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "FerocityLeggings"), PersistentDataType.INTEGER);
			}
		}
		if(player.getEquipment().getBoots() != null && player.getEquipment().getBoots().hasItemMeta()) {
			if(player.getEquipment().getBoots().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "FerocityBoots"), PersistentDataType.INTEGER)) {
				ferocity+=player.getEquipment().getBoots().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "FerocityBoots"), PersistentDataType.INTEGER);
			}
		}
		if(ferocity>0) {
			int mult = ferocity/100+1;
			int addit = ferocity%100;
			if(addit != 0) {
				Random random = new Random();
				if(random.nextInt(99)+1<addit) {
					mult++;
				}
			}
			if(mult>1) {
				event.setDamage(event.getDamage()*mult);
				DustTransition dustTransition = new DustTransition(Color.RED, Color.WHITE, 2.0F);
				event.getEntity().getLocation().getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, event.getEntity().getLocation().add(0,1,0), 25, dustTransition);
			}
		}
		//
		//Critical:
		if(event.getDamager() instanceof Player) {
			if(player.getVelocity().getY()+0.0784000015258789 >= 0) {
				int crit=0;
				if(player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta()) {
					if(player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "CritMainHand"), PersistentDataType.INTEGER)) {
						crit+=player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "CritMainHand"), PersistentDataType.INTEGER);
					}
				}
				if(player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta()) {
					if(player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "CritOffHand"), PersistentDataType.INTEGER)) {
						crit+=player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "CritOffHand"), PersistentDataType.INTEGER);
					}
				}
				if(player.getEquipment().getHelmet() != null && player.getEquipment().getHelmet().hasItemMeta()) {
					if(player.getEquipment().getHelmet().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "CritHelmet"), PersistentDataType.INTEGER)) {
						crit+=player.getEquipment().getHelmet().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "CritHelmet"), PersistentDataType.INTEGER);
					}
				}
				if(player.getEquipment().getChestplate() != null && player.getEquipment().getChestplate().hasItemMeta()) {
					if(player.getEquipment().getChestplate().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "CritChestplate"), PersistentDataType.INTEGER)) {
						crit+=player.getEquipment().getChestplate().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "CritChestplate"), PersistentDataType.INTEGER);
					}
				}
				if(player.getEquipment().getLeggings() != null && player.getEquipment().getLeggings().hasItemMeta()) {
					if(player.getEquipment().getLeggings().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "CritLeggings"), PersistentDataType.INTEGER)) {
						crit+=player.getEquipment().getLeggings().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "CritLeggings"), PersistentDataType.INTEGER);
					}
				}
				if(player.getEquipment().getBoots() != null && player.getEquipment().getBoots().hasItemMeta()) {
					if(player.getEquipment().getBoots().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "CritBoots"), PersistentDataType.INTEGER)) {
						crit+=player.getEquipment().getBoots().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "CritBoots"), PersistentDataType.INTEGER);
					}
				}
				if(crit>0) {
					Random random = new Random();
					if(random.nextInt(100)+1<crit) {
						event.setDamage(event.getDamage()*1.5);
						event.getEntity().getLocation().getWorld().spawnParticle(Particle.CRIT, event.getEntity().getLocation().add(0,1,0), 25);
					}
				}
			}
		}
		//
		//
		/*
		
			
		 */
	}
	
	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		int reach = 4;
		if(player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta()) {
			if(player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ReachMainHand"), PersistentDataType.INTEGER)) {
				reach+=player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ReachMainHand"), PersistentDataType.INTEGER);
			}
		}
		if(player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta()) {
			if(player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ReachOffHand"), PersistentDataType.INTEGER)) {
				reach+=player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ReachOffHand"), PersistentDataType.INTEGER);
			}
		}
		if(player.getEquipment().getHelmet() != null && player.getEquipment().getHelmet().hasItemMeta()) {
			if(player.getEquipment().getHelmet().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ReachHelmet"), PersistentDataType.INTEGER)) {
				reach+=player.getEquipment().getHelmet().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ReachHelmet"), PersistentDataType.INTEGER);
			}
		}
		if(player.getEquipment().getChestplate() != null && player.getEquipment().getChestplate().hasItemMeta()) {
			if(player.getEquipment().getChestplate().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ReachChestplate"), PersistentDataType.INTEGER)) {
				reach+=player.getEquipment().getChestplate().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ReachChestplate"), PersistentDataType.INTEGER);
			}
		}
		if(player.getEquipment().getLeggings() != null && player.getEquipment().getLeggings().hasItemMeta()) {
			if(player.getEquipment().getLeggings().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ReachLeggings"), PersistentDataType.INTEGER)) {
				reach+=player.getEquipment().getLeggings().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ReachLeggings"), PersistentDataType.INTEGER);
			}
		}
		if(player.getEquipment().getBoots() != null && player.getEquipment().getBoots().hasItemMeta()) {
			if(player.getEquipment().getBoots().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ReachBoots"), PersistentDataType.INTEGER)) {
				reach+=player.getEquipment().getBoots().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ReachBoots"), PersistentDataType.INTEGER);
			}
		}
		LivingEntity entity = null;
		for(Entity e : player.getNearbyEntities(reach, reach, reach)) {
			if(e instanceof LivingEntity) {
				LivingEntity en = (LivingEntity) e;
				Location eye = player.getEyeLocation();
			    Vector toEntity = en.getEyeLocation().toVector().subtract(eye.toVector());
			    double dot = toEntity.normalize().dot(eye.getDirection());
			    if(dot>0.99D) {
			    	entity = en;
			    }
			}
		}
		if(entity == null) {
			return;
		}
		if(reach<1) {
			reach=1;
		}
		
		if(player.getLocation().distance(entity.getLocation())<=reach) {
			player.attack(entity);
		}
	}
	
	
	@EventHandler
	private void onArrowHit(ProjectileHitEvent event) {
		if(!(event.getEntity() instanceof Arrow) || !(event.getEntity().getShooter() instanceof Player)) {
			return;
		}
		Arrow arrow = (Arrow) event.getEntity();
		Player player = (Player) arrow.getShooter();
		int Frames = 20;
		if(player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta()) {
			if(player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "InvFramesMainHand"), PersistentDataType.INTEGER)) {
				Frames+=player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "InvFramesMainHand"), PersistentDataType.INTEGER);
			}
		}
		if(player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta()) {
			if(player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "InvFramesOffHand"), PersistentDataType.INTEGER)) {
				Frames+=player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "InvFramesOffHand"), PersistentDataType.INTEGER);
			}
		}
		if(player.getEquipment().getHelmet() != null && player.getEquipment().getHelmet().hasItemMeta()) {
			if(player.getEquipment().getHelmet().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "InvFramesHelmet"), PersistentDataType.INTEGER)) {
				Frames+=player.getEquipment().getHelmet().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "InvFramesHelmet"), PersistentDataType.INTEGER);
			}
		}
		if(player.getEquipment().getChestplate() != null && player.getEquipment().getChestplate().hasItemMeta()) {
			if(player.getEquipment().getChestplate().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "InvFramesChestplate"), PersistentDataType.INTEGER)) {
				Frames+=player.getEquipment().getChestplate().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "InvFramesChestplate"), PersistentDataType.INTEGER);
			}
		}
		if(player.getEquipment().getLeggings() != null && player.getEquipment().getLeggings().hasItemMeta()) {
			if(player.getEquipment().getLeggings().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "InvFramesLeggings"), PersistentDataType.INTEGER)) {
				Frames+=player.getEquipment().getLeggings().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "InvFramesLeggings"), PersistentDataType.INTEGER);
			}
		}
		if(player.getEquipment().getBoots() != null && player.getEquipment().getBoots().hasItemMeta()) {
			if(player.getEquipment().getBoots().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "InvFramesBoots"), PersistentDataType.INTEGER)) {
				Frames+=player.getEquipment().getBoots().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "InvFramesBoots"), PersistentDataType.INTEGER);
			}
		}
		if(event.getHitEntity() != null) {
			LivingEntity entity = (LivingEntity) event.getHitEntity();
			if(Frames < 0) {
				entity.setMaximumNoDamageTicks(0);
				
			}else {
				entity.setMaximumNoDamageTicks(Frames);
			}
		}
	}
	
}
