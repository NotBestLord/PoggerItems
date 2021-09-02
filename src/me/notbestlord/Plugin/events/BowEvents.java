package me.notbestlord.Plugin.events;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Particle.DustTransition;
import org.bukkit.craftbukkit.libs.org.codehaus.plexus.util.ReflectionUtils;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;

import me.notbestlord.Plugin.Main;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.Particles;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;
import net.minecraft.network.protocol.game.PacketPlayOutWorldParticles;

public class BowEvents implements Listener{
	
	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
			if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
				return;
			}
			if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("rainbow bow")) {
				if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
					String Target = event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Target"), PersistentDataType.STRING);
					if(Target.equals("All")) {
						ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
						meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Target"), PersistentDataType.STRING, "Mobs");
						List<String> lore = meta.getLore();
						lore.set(lore.size()-1, ChatColor.GRAY+" Target: Mobs");
						meta.setLore(lore);
						event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
					}
					else if(Target.equals("Mobs")) {
						ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
						meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Target"), PersistentDataType.STRING, "Players");
						List<String> lore = meta.getLore();
						lore.set(lore.size()-1, ChatColor.GRAY+" Target: Players");
						meta.setLore(lore);
						event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
					}
					else if(Target.equals("Players")) {
						ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
						meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Target"), PersistentDataType.STRING, "All");
						List<String> lore = meta.getLore();
						lore.set(lore.size()-1, ChatColor.GRAY+" Target: All");
						meta.setLore(lore);
						event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
					}
				}
			}
		}
	}
	
	@EventHandler
	private void onArrowShoot(ProjectileLaunchEvent event) {
		if(event.getEntity().getShooter() == null) {
			return;
		}
		if(!(event.getEntity().getShooter() instanceof LivingEntity)) {
			return;
		}
		LivingEntity entity = (LivingEntity) event.getEntity().getShooter();
		if(entity.getEquipment().getItemInMainHand() != null && entity.getEquipment().getItemInMainHand().hasItemMeta()) {
			if(!entity.getEquipment().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))
				return;
			if(entity.getEquipment().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("rain bow")) {
				Arrow arrow = (Arrow) event.getEntity();
				arrow.setMetadata("rain bow arrow", new FixedMetadataValue(Main.getMain(), true));
				if(entity.getEquipment().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.ARROW_DAMAGE)) {
					arrow.setMetadata("Power", new FixedMetadataValue(Main.getMain(), 
							entity.getEquipment().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.ARROW_DAMAGE)));
				}
			}
			else if(entity.getEquipment().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("rainbow bow")) {
				Arrow arrow = (Arrow) event.getEntity();
				arrow.setMetadata("rainbow bow arrow", new FixedMetadataValue(Main.getMain(), true));
				if(event.getEntity().getShooter() instanceof Player) {
					Player player = (Player) event.getEntity().getShooter();
					arrow.setMetadata("Target", new FixedMetadataValue(Main.getMain(), 
							player.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Target"), PersistentDataType.STRING)));
				}
				if(entity.getEquipment().getItemInMainHand().getItemMeta().hasEnchant(Enchantment.ARROW_DAMAGE)) {
					arrow.setMetadata("Power", new FixedMetadataValue(Main.getMain(), 
							entity.getEquipment().getItemInMainHand().getItemMeta().getEnchantLevel(Enchantment.ARROW_DAMAGE)));
				}
			}
		}
	}
	
	@EventHandler
	private void onArrowLand(ProjectileHitEvent event) {
		if(!(event.getEntity() instanceof Arrow)) {
			return;
		}
		Arrow arrow = (Arrow) event.getEntity();
		if(arrow.hasMetadata("rain bow arrow")) {
			if(event.getHitEntity() != null) {
				event.setCancelled(true);
			}
			Location location = arrow.getLocation();
			arrow.getWorld().spawnParticle(Particle.WATER_SPLASH, arrow.getLocation(), 10);
			int Powerlvl = 0;
			if(arrow.hasMetadata("Power")) {
				Powerlvl = arrow.getMetadata("Power").get(0).asInt();
			}
			Random random = new Random();
			for(int i=0; i<5; i++) {
				Location arrowlocation = new Location(location.getWorld(),
						location.getX()+random.nextInt(2)-2,
						location.getY()+10,
						location.getZ()+random.nextInt(2)-2);
				if(event.getHitEntity() != null && i == 0) {
					arrowlocation = new Location(event.getHitEntity().getWorld(),
							event.getHitEntity().getLocation().getX(),
							event.getHitEntity().getLocation().getY()+10,
							event.getHitEntity().getLocation().getZ());
				}
				Arrow temparrow = (Arrow) arrow.getWorld().spawnArrow(arrowlocation, new Vector(0, -1, 0), 1, 1);
				temparrow.setDamage(arrow.getDamage());
				temparrow.setShooter(arrow.getShooter());
				temparrow.setFireTicks(arrow.getFireTicks());
				temparrow.setKnockbackStrength(arrow.getKnockbackStrength());
				temparrow.setCritical(arrow.isCritical());
				if(arrow.getBasePotionData().getType() != PotionType.UNCRAFTABLE) {
					temparrow.setBasePotionData(arrow.getBasePotionData());
				}
				if(Powerlvl != 0) {
					temparrow.setDamage(temparrow.getDamage() * (1+(0.25*(Powerlvl+1))));
				}
				temparrow.setMetadata("rain bow arrow 2", new FixedMetadataValue(Main.getMain(), true));
			}
			arrow.remove();
		}
		else if(arrow.hasMetadata("rain bow arrow 2")) {
			arrow.getWorld().spawnParticle(Particle.WATER_DROP, arrow.getLocation(), 10);
			arrow.remove();
		}
		else if(arrow.hasMetadata("rainbow bow arrow")) {
			if(event.getHitEntity() != null) {
				event.setCancelled(true);
			}
			Location location = arrow.getLocation();
			arrow.getWorld().spawnParticle(Particle.WATER_SPLASH, arrow.getLocation(), 10);
			int Powerlvl = 0;
			if(arrow.hasMetadata("Power")) {
				Powerlvl = arrow.getMetadata("Power").get(0).asInt();
			}
			Random random = new Random();
			for(int i=0; i<15; i++) {
				Location arrowlocation = new Location(location.getWorld(),
						location.getX()+random.nextInt(6)-2.5,
						location.getY()+10,
						location.getZ()+random.nextInt(6)-2.5);
				if(event.getHitEntity() != null && i == 0) {
					arrowlocation = new Location(event.getHitEntity().getWorld(),
							event.getHitEntity().getLocation().getX(),
							event.getHitEntity().getLocation().getY()+10,
							event.getHitEntity().getLocation().getZ());
				}
				Arrow temparrow = (Arrow) arrow.getWorld().spawnArrow(arrowlocation, new Vector(0, -1, 0), 1, 1);
				temparrow.setDamage(arrow.getDamage());
				temparrow.setShooter(arrow.getShooter());
				temparrow.setFireTicks(arrow.getFireTicks());
				temparrow.setKnockbackStrength(arrow.getKnockbackStrength());
				temparrow.setCritical(arrow.isCritical());
				if(arrow.getBasePotionData().getType() != PotionType.UNCRAFTABLE) {
					temparrow.setBasePotionData(arrow.getBasePotionData());
				}
				if(Powerlvl != 0) {
					temparrow.setDamage(temparrow.getDamage() * (1+(0.25*(Powerlvl+1))));
				}
				temparrow.setMetadata("Target", new FixedMetadataValue(Main.getMain(), arrow.getMetadata("Target").get(0).asString()));
				temparrow.setMetadata("rainbow bow arrow 2", new FixedMetadataValue(Main.getMain(), true));
				temparrow.setMetadata("runnableId", new FixedMetadataValue(Main.getMain(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
					
					@Override
					public void run() {
						for(int offset=0; offset<4; ++offset) {
			                Location location = temparrow.getLocation().clone();
			                location.setX(location.getX() + temparrow.getVelocity().getX() * (double) offset / 4.0D);
			                location.setY(location.getY() + temparrow.getVelocity().getY() * (double) offset / 4.0D);
			                location.setZ(location.getZ() + temparrow.getVelocity().getZ() * (double) offset / 4.0D);
							DustOptions dustOptions = new DustOptions(Color.fromRGB(random.nextInt(256), random.nextInt(256), random.nextInt(256)), 1.0F);
							arrow.getWorld().spawnParticle(Particle.REDSTONE, location, 1, dustOptions);
			            }
					}
				}, 0, 1)));
				PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(temparrow.getEntityId());
				for(Player p : Bukkit.getOnlinePlayers()) {
					((CraftPlayer) p).getHandle().b.sendPacket(packet);
				}
				
			}
			arrow.remove();
		}
		else if(arrow.hasMetadata("rainbow bow arrow 2")) {
			Random random = new Random();
			PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(Particles.a, true,arrow.getLocation().getX(),
		    		arrow.getLocation().getY(), arrow.getLocation().getZ(), random.nextFloat(), random.nextFloat(), random.nextFloat(), random.nextFloat(), 10);
			for(Player p : Bukkit.getServer().getOnlinePlayers()) {
			    ((CraftPlayer) p).getHandle().b.sendPacket(packet);
			}
			Bukkit.getScheduler().cancelTask(arrow.getMetadata("runnableId").get(0).asInt());
			//
			if(event.getHitEntity() != null) {
				if(arrow.hasMetadata("Target")) {
					if(arrow.getMetadata("Target").get(0).asString().equals("Mobs") && event.getHitEntity() instanceof Player) {
						event.setCancelled(true);
					}
					else if(arrow.getMetadata("Target").get(0).asString().equals("Players") && !(event.getHitEntity() instanceof Player)) {
						event.setCancelled(true);
					}
				}
			}
			arrow.remove();
		}
	}
}
