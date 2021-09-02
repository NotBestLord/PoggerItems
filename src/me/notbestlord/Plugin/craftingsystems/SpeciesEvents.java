package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.PiglinBrute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.notbestlord.Plugin.Main;

public class SpeciesEvents implements Listener{
	
	private List<Material> HerbivoreFood = new ArrayList<>(Arrays.asList(Material.APPLE, Material.MUSHROOM_STEW,
			Material.MELON, Material.DRIED_KELP, Material.CARROT, Material.POTATO, Material.BAKED_POTATO,
			Material.POISONOUS_POTATO, Material.BEETROOT, Material.BEETROOT_SOUP, Material.SWEET_BERRIES,
			Material.GLOW_BERRIES, Material.GOLDEN_APPLE, Material.ENCHANTED_GOLDEN_APPLE, Material.GOLDEN_CARROT));
	private List<Material> CarnivoreFood = new ArrayList<>(Arrays.asList(Material.BEEF, Material.COOKED_BEEF,
			Material.CHICKEN, Material.COOKED_CHICKEN, Material.COD, Material.COOKED_COD, Material.ROTTEN_FLESH,
			Material.SALMON, Material.COOKED_SALMON, Material.MUTTON, Material.COOKED_MUTTON, Material.PORKCHOP,
			Material.COOKED_PORKCHOP, Material.RABBIT, Material.COOKED_RABBIT, Material.TROPICAL_FISH,
			Material.PUFFERFISH, Material.SPIDER_EYE, Material.GOLDEN_APPLE, Material.ENCHANTED_GOLDEN_APPLE,
			Material.GOLDEN_CARROT));
	private List<Material> Fish = new ArrayList<>(Arrays.asList(Material.COD, Material.COOKED_COD,
			Material.SALMON, Material.COOKED_SALMON, Material.TROPICAL_FISH, Material.PUFFERFISH));
	
	
	@EventHandler
	private void onEat(PlayerItemConsumeEvent event) {
		Player player = event.getPlayer();
		if(!event.getItem().getType().isEdible()) {
			return;
		}
		if(player.hasMetadata("Species")) {
			String Species = player.getMetadata("Species").get(0).asString();
			if(Species.equals("Florian")) {
				if(!HerbivoreFood.contains(event.getItem().getType())) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 400, 0));
				}
			}
			else if(Species.equals("Ape")) {
				if(!HerbivoreFood.contains(event.getItem().getType())) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 400, 0));
				}
			}
			else if(Species.equals("Automaton")) {
				if(event.getItem().getType() != Material.GOLDEN_APPLE && event.getItem().getType() != Material.ENCHANTED_GOLDEN_APPLE) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 400, 0));
				}
			}
			else if(Species.equals("Arachnid")) {
				if(!CarnivoreFood.contains(event.getItem().getType())) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 400, 0));
				}
			}
			else if(Species.equals("Avian")) {
				if(!CarnivoreFood.contains(event.getItem().getType())) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 400, 0));
				}
				if(Fish.contains(event.getItem().getType())) {
					player.setSaturation(player.getSaturation()+8);
				}
			}
			else if(Species.equals("Pigman")) {
				if(!CarnivoreFood.contains(event.getItem().getType())) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 400, 0));
				}
			}
			else if(Species.equals("Hylotel")) {
				if(!CarnivoreFood.contains(event.getItem().getType())) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 400, 0));
				}
				if(Fish.contains(event.getItem().getType())) {
					player.setSaturation(player.getSaturation()+8);
				}
			}
		}
	}
	
	@EventHandler
	private void onPiglinTarget(EntityTargetLivingEntityEvent event) {
		if(!(event.getTarget() instanceof Player)) {
			return;
		}
		if(!(event.getEntity() instanceof Piglin) && !(event.getEntity() instanceof PiglinBrute)) {
			return;
		}
		Player player = (Player) event.getTarget();
		if(!player.hasMetadata("Species")) {
			return;
		}
		String Species = player.getMetadata("Species").get(0).asString();
		if(Species.equals("Pigman")) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	private void onSugarConsume(PlayerInteractEvent event) {
		Player player = (Player) event.getPlayer();
		if(!player.hasMetadata("Species")) {
			return;
		}
		String Species = player.getMetadata("Species").get(0).asString();
		if(!Species.equals("Florian")) {
			return;
		}
		if(event.getItem().getType() == Material.SUGAR) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0));
			player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 0));
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 0));
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0));
			event.getItem().setAmount(event.getItem().getAmount()-1);
		}
	}
	
	
	
	@EventHandler
	private void onPlayerDamageEntity(EntityDamageByEntityEvent event) {
		if(!(event.getDamager() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getDamager();
		if(!player.hasMetadata("Species")) {
			return;
		}
		String Species = player.getMetadata("Species").get(0).asString();
		if(Species.equals("Arachnid")) {
			if(event.getEntity().getLocation().getBlock().getType() == Material.AIR) {
				event.getEntity().getLocation().getBlock().setType(Material.COBWEB);
				final Block block = event.getEntity().getLocation().getBlock();
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
					
					@Override
					public void run() {
						block.setType(Material.AIR);
					}
				}, 100);
			}
			
			float chance = new Random().nextFloat();
			if(chance <= 0.05) {
				CaveSpider minion = (CaveSpider) player.getWorld().spawnEntity(player.getLocation(), EntityType.CAVE_SPIDER);
				minion.setTarget((LivingEntity)event.getEntity());
				minion.setMaximumNoDamageTicks(200);
				minion.setNoDamageTicks(200);
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
					
					@Override
					public void run() {
						minion.remove();
					}
				}, 200);
			}
		}
		else if(Species.equals("Ape")) {
			int apes = 0;
			if(event.getEntity().isDead()) {
				return;
			}
			for(Entity e : player.getNearbyEntities(100, 100, 100)) {
				if(e instanceof Player) {
					Player p = (Player) e;
					if(p.hasMetadata("Species")) {
						if(p.getMetadata("Species").get(0).asString().equals("Ape")) {
							apes++;
						}
					}
				}
			}
			if(apes != 0) {
				((LivingEntity)event.getEntity()).damage(apes*5, player);
			}
		}
	}
	
	public static void RestoreRunnable(Player player) {
		if(!player.hasMetadata("Species")) {
			return;
		}
		String Species = player.getMetadata("Species").get(0).asString();
		if(Species.equals("Florian")) {
			player.setMetadata("SpeciesRunnable", new FixedMetadataValue(Main.getMain(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
				
				@Override
				public void run() {
					if(player.getWorld().getTime()>0 && player.getWorld().getTime()<12300) {
						player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
					}else {
						player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);
					}
				}
			}, 0, 1)));
			return;
		}
		else if(Species.equals("Avian")) {
			player.setMetadata("SpeciesRunnable", new FixedMetadataValue(Main.getMain(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
				
				@Override
				public void run() {
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 2, 1));
				}
			}, 0, 1)));
			return;
		}
		else if(Species.equals("Lunarian")) {
			player.setMetadata("SpeciesRunnable", new FixedMetadataValue(Main.getMain(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
				
				@Override
				public void run() {
					if(player.getWorld().getTime()>0 && player.getWorld().getTime()<12300) {
						if(player.getLocation().getBlock().getRelative(0, 1, 0).getLightFromSky() == 15) {
							boolean isWearingArmor=false;
							for(ItemStack item : player.getEquipment().getArmorContents()) {
								if(item!=null) {
									isWearingArmor = true;
									break;
								}
							}
							if(!isWearingArmor) {
								player.setFireTicks(20);
							}
						}
					}
				}
			}, 0, 1)));
			return;
		}
		else if(Species.equals("Hylotel")) {
			player.setMetadata("SpeciesRunnable", new FixedMetadataValue(Main.getMain(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
				
				@Override
				public void run() {
					player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 2, 0));
				}
			}, 0, 1)));
			return;
		}
		
		
	}
}
