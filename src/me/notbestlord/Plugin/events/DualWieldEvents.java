package me.notbestlord.Plugin.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Particle.DustTransition;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;
import net.minecraft.network.protocol.game.PacketPlayOutAnimation;

public class DualWieldEvents implements Listener{
	
	@EventHandler
	private void onPlayerInteract(PlayerInteractAtEntityEvent event) {
		if(event.getHand() == EquipmentSlot.HAND) {
			return;
		}
		if(event.getRightClicked() == null) {
			return;
		}
		if(!(event.getRightClicked() instanceof LivingEntity)) {
			return;
		}
		boolean DualWield=false;
		int AttackSpeed = 0;
		Player player = event.getPlayer();
		if(player.getInventory().getItemInOffHand() != null && player.getInventory().getItemInOffHand().hasItemMeta()) {
			if(player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "DualWield"), PersistentDataType.INTEGER)) {
				DualWield = true;
				AttackSpeed = player.getInventory().getItemInOffHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "DualWield"), PersistentDataType.INTEGER);
			}
		}
		if(DualWield && ((LivingEntity)event.getRightClicked()).getNoDamageTicks() == 0) {
	        int reach = 4;
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
			//
			// Ferocity:
			int ferocity = 0;
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
			//
			int crit = 0;
			//Critical:
			if(player.getVelocity().getY()+0.0784000015258789 >= 0) {
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
			}
		
			if(player.getLocation().distance(event.getRightClicked().getLocation())>reach) {
				return;
			}
			double Damage = ((AttributeModifier)player.getInventory().getItemInOffHand().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).toArray()[0]).getAmount();
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
					Damage*=mult;
					DustTransition dustTransition = new DustTransition(Color.RED, Color.WHITE, 2.0F);
					event.getRightClicked().getLocation().getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, event.getRightClicked().getLocation().add(0,1,0), 25, dustTransition);
				}
			}

			if(crit>0) {
				Random random = new Random();
				if(random.nextInt(100)+1<crit) {
					Damage*=1.5;
					event.getRightClicked().getLocation().getWorld().spawnParticle(Particle.CRIT, event.getRightClicked().getLocation().add(0,1,0), 25);
				}
			}
	        LivingEntity entity = (LivingEntity)event.getRightClicked();
	        entity.damage(Damage, player);
	        if(AttackSpeed>2) {
		        entity.setNoDamageTicks(22-AttackSpeed);
	        }
	        else {
	        	entity.setNoDamageTicks(20-AttackSpeed);
	        }
			for(Player p : Bukkit.getOnlinePlayers()) {
				PacketPlayOutAnimation packet = new PacketPlayOutAnimation(((CraftPlayer)player).getHandle(), 3);
				((CraftPlayer)p).getHandle().b.sendPacket(packet);
			}
		}
	}
	
	@EventHandler
	private void onPlayerInteract2(EntityDamageByEntityEvent event) {
		if(!(event.getDamager() instanceof Player)) {
			return;
		}
		if(!(event.getEntity() instanceof LivingEntity)) {
			return;
		}
		boolean DualWield=false;
		int AttackSpeed = 0;
		Player player = (Player)event.getDamager();
		if(player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta()) {
			if(player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "DualWield"), PersistentDataType.INTEGER)) {
				DualWield = true;
				AttackSpeed = player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "DualWield"), PersistentDataType.INTEGER);
			}
		}
		if(DualWield) {
			event.setCancelled(true);
		}
		if(DualWield && ((LivingEntity)event.getEntity()).getNoDamageTicks() == 0) {
			// Reach:
				int reach = 4;
				if(player.getInventory().getItemInMainHand() != null && player.getInventory().getItemInMainHand().hasItemMeta()) {
					if(player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ReachMainHand"), PersistentDataType.INTEGER)) {
						reach+=player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ReachMainHand"), PersistentDataType.INTEGER);
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
			//
			int crit = 0;
			//Critical:
				if(player.getVelocity().getY()+0.0784000015258789 >= 0) {
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
				}
			
			if(player.getLocation().distance(event.getEntity().getLocation())>reach) {
				return;
			}
			double Damage = ((AttributeModifier)player.getInventory().getItemInMainHand().getItemMeta().getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE).toArray()[0]).getAmount();
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
					Damage*=mult;
					DustTransition dustTransition = new DustTransition(Color.RED, Color.WHITE, 2.0F);
					event.getEntity().getLocation().getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, event.getEntity().getLocation().add(0,1,0), 25, dustTransition);
				}
			}

			if(crit>0) {
				Random random = new Random();
				if(random.nextInt(100)+1<crit) {
					Damage*=1.5;
					event.getEntity().getLocation().getWorld().spawnParticle(Particle.CRIT, event.getEntity().getLocation().add(0,1,0), 25);
				}
			}
	        LivingEntity entity = (LivingEntity)event.getEntity();
	        entity.damage(Damage);
	        entity.setNoDamageTicks(20-AttackSpeed);
		}
	}
}
