package me.notbestlord.Plugin.mobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World.Environment;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Husk;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.PiglinBrute;
import org.bukkit.entity.Pillager;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
import org.bukkit.entity.Ravager;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Vindicator;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Wither;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.IngredientManager;
import me.notbestlord.Plugin.items.ItemManager;
import net.md_5.bungee.api.ChatColor;

public class Bosses implements Listener {
	
	private Random random = new Random();
	
	@EventHandler
	private void onPlayerRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getHand() == EquipmentSlot.OFF_HAND) {
				return;
			}
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))
					return;
				else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("awakenedwithersummon")) {
					Player player = event.getPlayer();
					if(player.getWorld().getEnvironment() != Environment.NETHER) {
						player.sendMessage(ChatColor.RED+"The Awakened Wither Can Only Be Summoned In The Nether");
						return;
					}
					player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
					
					Wither awakenedwither = (Wither) player.getWorld().spawnEntity(player.getLocation(), EntityType.WITHER);
					awakenedwither.setCustomNameVisible(true);
					awakenedwither.setCustomName(ChatColor.DARK_RED+"Awakened Wither");
					awakenedwither.setMetadata("BossName", new FixedMetadataValue(Main.getMain(), "Awakened Wither"));
					awakenedwither.setMetadata("DealInstantDamage", new FixedMetadataValue(Main.getMain(), false));
					awakenedwither.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(1000);
					awakenedwither.setHealth(awakenedwither.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
					awakenedwither.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 320000, 0));
					awakenedwither.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 320000, 1));
					awakenedwither.setTarget(player);
					for(Entity e : player.getWorld().getNearbyEntities(player.getLocation(), 25, 25, 25)) {
						if(e instanceof Player) {
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 320000, 0));
						}
					}
				}
				else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("oceanguardiansummon")) {
					Player player = event.getPlayer();
					player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
					ElderGuardian Mount = (ElderGuardian) player.getWorld().spawnEntity(player.getLocation(), EntityType.ELDER_GUARDIAN);
					Witch Rider = (Witch) player.getWorld().spawnEntity(player.getLocation(), EntityType.WITCH);
					Rider.setCustomNameVisible(true);
					Rider.setCustomName(ChatColor.DARK_BLUE+"Guardian of the Ocean");
					Rider.setMetadata("BossName", new FixedMetadataValue(Main.getMain(), "Guardian of the Ocean"));
					Rider.setMetadata("isEnraged", new FixedMetadataValue(Main.getMain(), false));
					Mount.setMetadata("MountName", new FixedMetadataValue(Main.getMain(), "Guardian of the Ocean"));
					List<Biome> OceanBiomes = new ArrayList<>(Arrays.asList(Biome.OCEAN,Biome.COLD_OCEAN,Biome.DEEP_COLD_OCEAN
							,Biome.DEEP_FROZEN_OCEAN, Biome.DEEP_LUKEWARM_OCEAN, Biome.DEEP_OCEAN, Biome.DEEP_WARM_OCEAN
							,Biome.FROZEN_OCEAN, Biome.LUKEWARM_OCEAN, Biome.WARM_OCEAN));
					if(OceanBiomes.contains(player.getLocation().getBlock().getBiome())) {
						Rider.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(200);
						Rider.setHealth(Rider.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
						Mount.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(250);
						Mount.setHealth(Rider.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
						
					}
					else {
						Rider.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(300);
						Rider.setHealth(Rider.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
						Mount.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(300);
						Mount.setHealth(Rider.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
						Rider.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 320000, 0));
						Mount.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 320000, 0));
						Rider.setMetadata("isEnraged", new FixedMetadataValue(Main.getMain(), true));
						for(Entity e : player.getWorld().getNearbyEntities(player.getLocation(), 25, 25, 25)) {
							if(e instanceof Player) {
								((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 320000, 0));
							}
						}
					}
					Mount.addPassenger(Rider);
					Mount.setTarget(player);
					Rider.setTarget(player);
				}
				else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("enderlordsummon")) {
					Player player = event.getPlayer();
					if(event.getPlayer().getWorld().getEnvironment() != Environment.THE_END) {
						player.sendMessage(ChatColor.RED+"The Ender Lord Can Only Be Summoned In The End");
						return;
					}
					player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
					Enderman enderlord = (Enderman) player.getWorld().spawnEntity(player.getLocation(), EntityType.ENDERMAN);
					enderlord.setCustomNameVisible(true);
					enderlord.setCustomName(ChatColor.of("#ad0063")+"Ender Lord");
					enderlord.setMetadata("BossName", new FixedMetadataValue(Main.getMain(), "Ender Lord"));
					enderlord.setTarget(player);
					enderlord.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(750);
					enderlord.setHealth(enderlord.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
					enderlord.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 320000, 0));
					enderlord.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 320000, 1));
				}
				else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("steelgiantsummon")) {
					Player player = event.getPlayer();
					if(event.getPlayer().getWorld().getEnvironment() != Environment.NORMAL) {
						player.sendMessage(ChatColor.RED+"The Steel Giant Can Only Be Summoned In The Overworld");
						return;
					}
					player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
					IronGolem steelgiant = (IronGolem) player.getWorld().spawnEntity(player.getLocation(), EntityType.IRON_GOLEM);
					steelgiant.setCustomNameVisible(true);
					steelgiant.setCustomName(ChatColor.of("#c9cac9")+"Steel Giant");
					steelgiant.setMetadata("BossName", new FixedMetadataValue(Main.getMain(), "Steel Giant"));
					steelgiant.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(500);
					steelgiant.setHealth(steelgiant.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
					steelgiant.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 320000, 0));
					steelgiant.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 320000, 0));
					steelgiant.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 320000, 0));
					steelgiant.setTarget(player);
					for(Entity e : player.getWorld().getNearbyEntities(player.getLocation(), 25, 25, 25)) {
						if(e instanceof Player) {
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 320000, 0));
						}
					}
				}
				else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("diamondgiantsummon")) {
					Player player = event.getPlayer();
					if(event.getPlayer().getWorld().getEnvironment() != Environment.NORMAL || event.getPlayer().getLocation().getBlockY()>=64) {
						player.sendMessage(ChatColor.RED+"The Diamond Giant Can Only Be Summoned In The Overworld under y = 64.");
						return;
					}
					player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
					IronGolem diamondgiant = (IronGolem) player.getWorld().spawnEntity(player.getLocation(), EntityType.IRON_GOLEM);
					diamondgiant.setCustomNameVisible(true);
					diamondgiant.setCustomName(ChatColor.of("#00a7c4")+"Diamond Giant");
					diamondgiant.setMetadata("BossName", new FixedMetadataValue(Main.getMain(), "Diamond Giant"));
					diamondgiant.setMetadata("75%", new FixedMetadataValue(Main.getMain(), false));
					diamondgiant.setMetadata("50%", new FixedMetadataValue(Main.getMain(), false));
					diamondgiant.setMetadata("25%", new FixedMetadataValue(Main.getMain(), false));
					diamondgiant.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(750);
					diamondgiant.setHealth(diamondgiant.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
					diamondgiant.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 320000, 1));
					diamondgiant.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 320000, 1));
					diamondgiant.setTarget(player);
					for(Entity e : player.getWorld().getNearbyEntities(player.getLocation(), 25, 25, 25)) {
						if(e instanceof Player) {
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 320000, 1));
						}
					}
				}
				else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("satansummon")) {
					Player player = event.getPlayer();
					if(event.getPlayer().getWorld().getEnvironment() != Environment.NORMAL || (player.getWorld().getTime()>0 && player.getWorld().getTime()<12300)) {
						player.sendMessage(ChatColor.RED+"Satan Can Only Be Summoned In The Overworld During Night.");
						return;
					}
					player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
					Zombie satan = (Zombie) player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
					satan.setCustomNameVisible(true);
					satan.setCustomName(ChatColor.of("#d40b00")+"Satan");
					satan.setBaby();
					satan.getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
					satan.getEquipment().setHelmetDropChance(0);
					satan.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
					satan.getEquipment().setChestplateDropChance(0);
					satan.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
					satan.getEquipment().setLeggingsDropChance(0);
					satan.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
					satan.getEquipment().setBootsDropChance(0);
					satan.setMetadata("BossName", new FixedMetadataValue(Main.getMain(), "Satan"));
					satan.setMetadata("50%", new FixedMetadataValue(Main.getMain(), false));
					satan.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(500);
					satan.setHealth(satan.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
					satan.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 320000, 0));
					satan.setTarget(player);
					for(Entity e : player.getWorld().getNearbyEntities(player.getLocation(), 25, 25, 25)) {
						if(e instanceof Player) {
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 320000, 0));
						}
					}
				}
				else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("undeadlordsummon")) {
					Player player = event.getPlayer();
					if(event.getPlayer().getWorld().getEnvironment() != Environment.NORMAL) {
						player.sendMessage(ChatColor.RED+"The Lord of the Undead Can Only Be Summoned In The Overworld.");
						return;
					}
					player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
					Husk undeadlord = (Husk) player.getWorld().spawnEntity(player.getLocation(), EntityType.HUSK);
					undeadlord.setCustomNameVisible(true);
					undeadlord.setCustomName(ChatColor.of("#b57b1c")+"Lord of the Undead");
					undeadlord.setAdult();
					undeadlord.getEquipment().setHelmet(new ItemStack(ItemManager.BossSummoningItems[7]));
					undeadlord.getEquipment().setHelmetDropChance(0);
					undeadlord.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
					undeadlord.getEquipment().setChestplateDropChance(0);
					undeadlord.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
					undeadlord.getEquipment().setLeggingsDropChance(0);
					undeadlord.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
					undeadlord.getEquipment().setBootsDropChance(0);
					undeadlord.setMetadata("BossName", new FixedMetadataValue(Main.getMain(), "Undead Lord"));
					undeadlord.setMetadata("50%", new FixedMetadataValue(Main.getMain(), false));
					undeadlord.setMetadata("25%", new FixedMetadataValue(Main.getMain(), false));
					undeadlord.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(250);
					undeadlord.setHealth(undeadlord.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
					undeadlord.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 320000, 0));
					undeadlord.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 320000, 1));
					undeadlord.setTarget(player);
					for(Entity e : player.getWorld().getNearbyEntities(player.getLocation(), 25, 25, 25)) {
						if(e instanceof Player) {
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 320000, 0));
						}
					}
				}
				else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("frozendisastersummon")) {
					Player player = event.getPlayer();
					List<Biome> ColdBiomes = new ArrayList<>(Arrays.asList(Biome.SNOWY_TUNDRA,Biome.COLD_OCEAN,Biome.DEEP_COLD_OCEAN
							,Biome.DEEP_FROZEN_OCEAN, Biome.ICE_SPIKES, Biome.SNOWY_TAIGA, Biome.SNOWY_TAIGA_MOUNTAINS
							,Biome.FROZEN_OCEAN, Biome.FROZEN_RIVER, Biome.SNOWY_BEACH, Biome.MOUNTAINS, Biome.GRAVELLY_MOUNTAINS,
							Biome.MODIFIED_GRAVELLY_MOUNTAINS ,Biome.WOODED_MOUNTAINS, Biome.TAIGA, Biome.TAIGA_MOUNTAINS, 
							Biome.GIANT_TREE_TAIGA ,Biome.GIANT_SPRUCE_TAIGA, Biome.STONE_SHORE));
					if(ColdBiomes.contains(player.getLocation().getBlock().getBiome())) {
						player.sendMessage(ChatColor.RED+"The Forzen Disaster Can Only Be Summoned In Cold Biomes.");
						return;
					}
					player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
					PolarBear bear1 = (PolarBear) player.getWorld().spawnEntity(player.getLocation(), EntityType.POLAR_BEAR);
					bear1.setCustomNameVisible(true);
					bear1.setCustomName(ChatColor.of("#addae9")+"Frozen Disaster");
					bear1.setAdult();
					bear1.setMetadata("BossName", new FixedMetadataValue(Main.getMain(), "Frozen Disaster"));
					bear1.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(250);
					bear1.setHealth(bear1.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
					bear1.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 320000, 2));
					bear1.setTarget(player);
					PolarBear bear2 = (PolarBear) player.getWorld().spawnEntity(player.getLocation(), EntityType.POLAR_BEAR);
					bear2.setCustomNameVisible(true);
					bear2.setCustomName(ChatColor.of("#addae9")+"Frozen Disaster");
					bear2.setAdult();
					bear2.setMetadata("BossName", new FixedMetadataValue(Main.getMain(), "Frozen Disaster"));
					bear2.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(250);
					bear2.setHealth(bear2.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
					bear2.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 320000, 2));
					bear2.setTarget(player);
					bear2.addPassenger(bear1);
					for(Entity e : player.getWorld().getNearbyEntities(player.getLocation(), 25, 25, 25)) {
						if(e instanceof Player) {
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 320000, 0));
						}
					}
				}
				else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("piglincaptainsummon")) {
					Player player = event.getPlayer();
					if(event.getPlayer().getWorld().getEnvironment() != Environment.NETHER) {
						player.sendMessage(ChatColor.RED+"The Piglin Captain Can Only Be Summoned In The Nether.");
						return;
					}
					player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
					PiglinBrute piglincaptain = (PiglinBrute) player.getWorld().spawnEntity(player.getLocation(), EntityType.PIGLIN_BRUTE);
					piglincaptain.setCustomNameVisible(true);
					piglincaptain.setCustomName(ChatColor.of("#bc101e")+"Piglin Captain");
					piglincaptain.setAdult();
					piglincaptain.getEquipment().setHelmet(new ItemStack(ItemManager.BossSummoningItems[8]));
					piglincaptain.getEquipment().setHelmetDropChance(0);
					piglincaptain.getEquipment().setChestplate(new ItemStack(Material.GOLDEN_CHESTPLATE));
					piglincaptain.getEquipment().setChestplateDropChance(0);
					piglincaptain.getEquipment().setLeggings(new ItemStack(Material.GOLDEN_LEGGINGS));
					piglincaptain.getEquipment().setLeggingsDropChance(0);
					piglincaptain.getEquipment().setBoots(new ItemStack(Material.GOLDEN_BOOTS));
					piglincaptain.getEquipment().setBootsDropChance(0);
					piglincaptain.setMetadata("BossName", new FixedMetadataValue(Main.getMain(), "Piglin Captain"));
					piglincaptain.setMetadata("50%", new FixedMetadataValue(Main.getMain(), false));
					piglincaptain.setMetadata("25%", new FixedMetadataValue(Main.getMain(), false));
					piglincaptain.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(500);
					piglincaptain.setHealth(piglincaptain.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
					piglincaptain.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 320000, 0));
					piglincaptain.setTarget(player);
				}
				else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("anomalousravagersummon")) {
					Player player = event.getPlayer();
					Location sl = null;
					for(Entity e : player.getWorld().getNearbyEntities(player.getLocation(), 25, 25, 25)) {
						if(e instanceof Villager) {
							sl = e.getLocation();
							e.remove();
							break;
						}
					}
					if(sl == null) {
						player.sendMessage(ChatColor.RED+"No Nearby Villagers.");
						return;
					}
					player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
					Ravager ravager = (Ravager) player.getWorld().spawnEntity(sl, EntityType.RAVAGER);
					ravager.setCustomNameVisible(true);
					ravager.setCustomName(ChatColor.of("#264d75")+"Anomalous Ravager");
					ravager.setMetadata("BossName", new FixedMetadataValue(Main.getMain(), "Anomalous Ravager"));
					ravager.setMetadata("75%", new FixedMetadataValue(Main.getMain(), false));
					ravager.setMetadata("50%", new FixedMetadataValue(Main.getMain(), false));
					ravager.setMetadata("25%", new FixedMetadataValue(Main.getMain(), false));
					ravager.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(750);
					ravager.setHealth(ravager.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
					ravager.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 320000, 0));
					ravager.setTarget(player);
				}
				else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("skeletonmastersummon")) {
					Player player = event.getPlayer();
					if(event.getPlayer().getWorld().getEnvironment() != Environment.NORMAL || (player.getWorld().getTime()>0 && player.getWorld().getTime()<12300)) {
						player.sendMessage(ChatColor.RED+"The Skeleton Master Can Only Be Summoned In The Overworld During Night.");
						return;
					}
					player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
					Skeleton skeletonmaster = (Skeleton) player.getWorld().spawnEntity(player.getLocation(), EntityType.SKELETON);
					skeletonmaster.setCustomNameVisible(true);
					skeletonmaster.setCustomName(ChatColor.of("#ededed")+"Skeleton Master");
					skeletonmaster.getEquipment().setHelmet(new ItemStack(ItemManager.BossSummoningItems[10]));
					skeletonmaster.getEquipment().setHelmetDropChance(0);
					skeletonmaster.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
					skeletonmaster.getEquipment().setChestplateDropChance(0);
					skeletonmaster.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
					skeletonmaster.getEquipment().setLeggingsDropChance(0);
					skeletonmaster.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
					skeletonmaster.getEquipment().setBootsDropChance(0);
					ItemStack bow = new ItemStack(ItemManager.RainBow);
					ItemMeta meta = bow.getItemMeta();
					meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
					bow.setItemMeta(meta);
					skeletonmaster.getEquipment().setItemInMainHand(bow);
					skeletonmaster.getEquipment().setItemInMainHandDropChance(0);
					skeletonmaster.getEquipment().setItemInOffHand(bow);
					skeletonmaster.getEquipment().setItemInOffHandDropChance(0);
					skeletonmaster.setMetadata("BossName", new FixedMetadataValue(Main.getMain(), "Skeleton Master"));
					skeletonmaster.setMetadata("50%", new FixedMetadataValue(Main.getMain(), false));
					skeletonmaster.setMetadata("25%", new FixedMetadataValue(Main.getMain(), false));
					skeletonmaster.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(750);
					skeletonmaster.setHealth(skeletonmaster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
					skeletonmaster.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 320000, 0));
					skeletonmaster.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 320000, 2));
					skeletonmaster.setTarget(player);
					for(Entity e : player.getWorld().getNearbyEntities(player.getLocation(), 25, 25, 25)) {
						if(e instanceof Player) {
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 320000, 1));
						}
					}
				}
			}
		}
	}
	
	@EventHandler
	private void onPlayerDamageEntity(EntityDamageByEntityEvent event) {
		if(!(event.getDamager() instanceof Player)) {
			return;
		}
		if(event.getEntity().hasMetadata("BossName")) {
			if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Awakened Wither")) {
				Wither mob = (Wither) event.getEntity();
				if(!mob.getMetadata("DealInstantDamage").get(0).asBoolean()) {
					if(mob.getHealth() <= 100) {
						for(Entity e : mob.getWorld().getNearbyEntities(mob.getLocation(), 25, 25, 25)) {
							if(e instanceof Player) {
								((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.HARM, 10, 1));
							}
						}
						mob.setMetadata("DealInstantDamage", new FixedMetadataValue(Main.getMain(), true));
					}
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Steel Giant")) {
				IronGolem mob = (IronGolem) event.getEntity();
				if(mob.getHealth() <= 250) {
					mob.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 320000, 1));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Diamond Giant")) {
				IronGolem mob = (IronGolem) event.getEntity();
				Float chance = new Random().nextFloat();
				if (chance <= 0.1f) // chance of 10%
				{
					mob.teleport(event.getDamager());
					for(int x=mob.getLocation().getBlockX()-1; x<=mob.getLocation().getBlockX()+1;x++) {
						for(int y=mob.getLocation().getBlockY(); y<=mob.getLocation().getBlockY()+2;y++) {
							for(int z=mob.getLocation().getBlockZ()-1; z<=mob.getLocation().getBlockZ()+1;z++) {
								mob.getWorld().getBlockAt(x,y,z).breakNaturally();
							}
						}
					}
				}
				if(mob.getHealth() <= 562.5 && !mob.getMetadata("75%").get(0).asBoolean()) {
					for(Entity e : mob.getWorld().getNearbyEntities(mob.getLocation(), 25, 25, 25)) {
						if(e instanceof Player) {
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.HARM, 10, 0));
						}
					}
					mob.setMetadata("75%", new FixedMetadataValue(Main.getMain(), true));
				}
				else if(mob.getHealth() <= 375 && !mob.getMetadata("50%").get(0).asBoolean()) {
					for(Entity e : mob.getWorld().getNearbyEntities(mob.getLocation(), 25, 25, 25)) {
						if(e instanceof Player) {
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.HARM, 10, 0));
						}
					}
					mob.setMetadata("50%", new FixedMetadataValue(Main.getMain(), true));
				}
				else if(mob.getHealth() <= 187.5 && !mob.getMetadata("25%").get(0).asBoolean()) {
					for(Entity e : mob.getWorld().getNearbyEntities(mob.getLocation(), 25, 25, 25)) {
						if(e instanceof Player) {
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.HARM, 10, 0));
						}
					}
					mob.setMetadata("25%", new FixedMetadataValue(Main.getMain(), true));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Satan")) {
				Zombie mob = (Zombie) event.getEntity();
				if(mob.getHealth() <= 250 && !mob.getMetadata("50%").get(0).asBoolean()) {
					for(Entity e : mob.getWorld().getNearbyEntities(mob.getLocation(), 25, 25, 25)) {
						if(e instanceof Player) {
							((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 320000, 1));
						}
					}
					mob.setMetadata("50%", new FixedMetadataValue(Main.getMain(), true));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Undead Lord")) {
				Husk mob = (Husk) event.getEntity();
				if(mob.getHealth() <= 125 && !mob.getMetadata("50%").get(0).asBoolean()) {
					for(int i=0; i<2; i++) {
						Husk minion = (Husk) mob.getWorld().spawnEntity(mob.getLocation(), EntityType.HUSK);
						minion.setCustomNameVisible(true);
						minion.setCustomName(ChatColor.of("#b57b1c")+"Undead Minion");
						minion.setBaby();
						minion.getEquipment().setArmorContents(mob.getEquipment().getArmorContents());
						minion.getEquipment().setHelmetDropChance(0);
						minion.getEquipment().setChestplateDropChance(0);
						minion.getEquipment().setLeggingsDropChance(0);
						minion.getEquipment().setBootsDropChance(0);
						minion.setTarget((LivingEntity) event.getDamager());
					}
					mob.setMetadata("50%", new FixedMetadataValue(Main.getMain(), true));
				}
				if(mob.getHealth() <= 62.5 && !mob.getMetadata("25%").get(0).asBoolean()) {
					for(int i=0; i<3; i++) {
						Husk minion = (Husk) mob.getWorld().spawnEntity(mob.getLocation(), EntityType.HUSK);
						minion.setCustomNameVisible(true);
						minion.setCustomName(ChatColor.of("#b57b1c")+"Undead Minion");
						minion.setBaby();
						minion.getEquipment().setArmorContents(mob.getEquipment().getArmorContents());
						minion.getEquipment().setHelmetDropChance(0);
						minion.getEquipment().setChestplateDropChance(0);
						minion.getEquipment().setLeggingsDropChance(0);
						minion.getEquipment().setBootsDropChance(0);
						minion.setTarget((LivingEntity) event.getDamager());
					}
					mob.setMetadata("25%", new FixedMetadataValue(Main.getMain(), true));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Piglin Captain")) {
				PiglinBrute mob = (PiglinBrute) event.getEntity();
				if(mob.getHealth() <= 250 && !mob.getMetadata("50%").get(0).asBoolean()) {
					mob.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 320000, 0));
					mob.setMetadata("50%", new FixedMetadataValue(Main.getMain(), true));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Anomalous Ravager")) {
				Ravager mob = (Ravager) event.getEntity();
				if(mob.getHealth() <= 562.5 && !mob.getMetadata("75%").get(0).asBoolean()) {
					for(int i=0; i<4; i++) {
						Pillager minion = (Pillager) mob.getWorld().spawnEntity(mob.getLocation(), EntityType.PILLAGER);
						minion.setTarget((LivingEntity) event.getDamager());
					}
					mob.setMetadata("75%", new FixedMetadataValue(Main.getMain(), true));
				}
				if(mob.getHealth() <= 375 && !mob.getMetadata("50%").get(0).asBoolean()) {
					for(int i=0; i<2; i++) {
						Vindicator minion = (Vindicator) mob.getWorld().spawnEntity(mob.getLocation(), EntityType.VINDICATOR);
						minion.setTarget((LivingEntity) event.getDamager());
					}
					mob.setMetadata("50%", new FixedMetadataValue(Main.getMain(), true));
				}
				if(mob.getHealth() <= 187.5 && !mob.getMetadata("25%").get(0).asBoolean()) {
					for(int i=0; i<2; i++) {
						Evoker minion = (Evoker) mob.getWorld().spawnEntity(mob.getLocation(), EntityType.EVOKER);
						minion.setTarget((LivingEntity) event.getDamager());
					}
					mob.setMetadata("25%", new FixedMetadataValue(Main.getMain(), true));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Skeleton Master")) {
				Skeleton mob = (Skeleton) event.getEntity();
				if(mob.getHealth() <= 375 && !mob.getMetadata("50%").get(0).asBoolean()) {
					for(int i=0; i<2; i++) {
						Skeleton duplicate = (Skeleton) mob.getWorld().spawnEntity(mob.getLocation(), EntityType.SKELETON);
						duplicate.setCustomNameVisible(true);
						duplicate.setCustomName(ChatColor.of("#ededed")+"Skeleton Master");
						duplicate.getEquipment().setHelmet(new ItemStack(Material.SKELETON_SKULL));
						duplicate.getEquipment().setHelmetDropChance(0);
						duplicate.getEquipment().setChestplate(new ItemStack(Material.IRON_CHESTPLATE));
						duplicate.getEquipment().setChestplateDropChance(0);
						duplicate.getEquipment().setLeggings(new ItemStack(Material.IRON_LEGGINGS));
						duplicate.getEquipment().setLeggingsDropChance(0);
						duplicate.getEquipment().setBoots(new ItemStack(Material.IRON_BOOTS));
						duplicate.getEquipment().setBootsDropChance(0);
						ItemStack bow = new ItemStack(ItemManager.RainBow);
						ItemMeta meta = bow.getItemMeta();
						meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
						bow.setItemMeta(meta);
						duplicate.getEquipment().setItemInMainHand(bow);
						duplicate.getEquipment().setItemInMainHandDropChance(0);
						duplicate.getEquipment().setItemInOffHand(bow);
						duplicate.getEquipment().setItemInOffHandDropChance(0);
						duplicate.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
						duplicate.setHealth(duplicate.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
						duplicate.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 320000, 0));
						duplicate.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 320000, 2));
						duplicate.setTarget((LivingEntity) event.getDamager());
					}
					mob.setMetadata("50%", new FixedMetadataValue(Main.getMain(), true));
				}
				if(mob.getHealth() <= 187.5 && !mob.getMetadata("25%").get(0).asBoolean()) {
					ItemStack bow = new ItemStack(ItemManager.RainbowBow);
					ItemMeta meta = bow.getItemMeta();
					meta.addEnchant(Enchantment.ARROW_DAMAGE, 3, false);
					bow.setItemMeta(meta);
					mob.getEquipment().setItemInMainHand(bow);
					mob.getEquipment().setItemInMainHandDropChance(0);
					mob.getEquipment().setItemInOffHand(bow);
					mob.getEquipment().setItemInOffHandDropChance(0);
					mob.setMetadata("25%", new FixedMetadataValue(Main.getMain(), true));
				}
			}
		}
	}
	@EventHandler
	private void onEntityTakeDamage(EntityDamageEvent event) {
		
		if(event.getEntity().hasMetadata("BossName")) {
			if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Ender Lord")) {
				if(event.getCause() == DamageCause.DROWNING) {
					Enderman enderlord = (Enderman) event.getEntity();
					if(enderlord.getHealth()+5>=750) {
						enderlord.setHealth(750);
					}
					else {
						enderlord.setHealth(enderlord.getHealth()+5);
					}
				}
			}
		}
	}
	
	@EventHandler
	private void onPlayerKillEntity(EntityDeathEvent event) {
		if(event.getEntity().hasMetadata("BossName")) {
			if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Awakened Wither")) {
				for(Entity e : event.getEntity().getWorld().getNearbyEntities(event.getEntity().getLocation(), 50, 50, 50)) {
					if(e instanceof Player) {
						if(((Player) e).hasPotionEffect(PotionEffectType.POISON)) {
							((Player) e).removePotionEffect(PotionEffectType.POISON);
						}
					}
				}
				if(event.getEntity().getKiller() == null) {
					event.getDrops().clear();
					event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ItemManager.createLootBag("Awakened Wither", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjczYTM2YzU1OTUzYmNkZDU2ZDE5ZWY4YTI0OTk0OTFiNWViZmE0MmY0NGRkMDhjNTg0MjAyMmFjMzhkMjJjZCJ9fX0"));
					
				}
				else {
					event.getDrops().clear();
					event.getEntity().getKiller().getInventory().addItem(ItemManager.createLootBag("Awakened Wither", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjczYTM2YzU1OTUzYmNkZDU2ZDE5ZWY4YTI0OTk0OTFiNWViZmE0MmY0NGRkMDhjNTg0MjAyMmFjMzhkMjJjZCJ9fX0"));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Guardian of the Ocean")) {
				if(event.getEntity().getMetadata("isEnraged").get(0).asBoolean()) {
					for(Entity e : event.getEntity().getWorld().getNearbyEntities(event.getEntity().getLocation(), 50, 50, 50)) {
						if(e instanceof Player) {
							if(((Player) e).hasPotionEffect(PotionEffectType.POISON)) {
								((Player) e).removePotionEffect(PotionEffectType.POISON);
							}
						}
					}
				}
				if(event.getEntity().getKiller() == null) {
					event.getDrops().clear();
					event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ItemManager.createLootBag("Guardian of the Ocean", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzYwYTA4ZGE4ZDNlNzNjNWFkZGNjMTAzODk1NjU2YWM0MDRmZjQyZjJkNWI1Yjc0NmNjZGU2MDdjZTkwNDQzYyJ9fX0"));
					
				}
				else {
					event.getDrops().clear();
					event.getEntity().getKiller().getInventory().addItem(ItemManager.createLootBag("Guardian of the Ocean", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzYwYTA4ZGE4ZDNlNzNjNWFkZGNjMTAzODk1NjU2YWM0MDRmZjQyZjJkNWI1Yjc0NmNjZGU2MDdjZTkwNDQzYyJ9fX0"));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Ender Lord")) {
				if(event.getEntity().getKiller() == null) {
					event.getDrops().clear();
					event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ItemManager.createLootBag("Ender Lord", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzljN2YxZGIxY2UyMWFkMGQyYzVkMTEyNDY2ZWVhNzk4NGRhM2EwMTMzMzBlMTBhYzljMWU3OWQxNjAyNWU5MiJ9fX0"));
					
				}
				else {
					event.getDrops().clear();
					event.getEntity().getKiller().getInventory().addItem(ItemManager.createLootBag("Ender Lord", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzljN2YxZGIxY2UyMWFkMGQyYzVkMTEyNDY2ZWVhNzk4NGRhM2EwMTMzMzBlMTBhYzljMWU3OWQxNjAyNWU5MiJ9fX0"));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Steel Giant")) {
				for(Entity e : event.getEntity().getWorld().getNearbyEntities(event.getEntity().getLocation(), 50, 50, 50)) {
					if(e instanceof Player) {
						if(((Player) e).hasPotionEffect(PotionEffectType.BLINDNESS)) {
							((Player) e).removePotionEffect(PotionEffectType.BLINDNESS);
						}
					}
				}
				if(event.getEntity().getKiller() == null) {
					event.getDrops().clear();
					event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ItemManager.createLootBag("Steel Giant", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODIyYTQ4YTU3NTllZGRlZjllMjkxOGZjODU5OTZmODQ5MWNjOTI1NzhkNTRkY2Q2MmUyYjZkOTEzYmZiNDIxZSJ9fX0"));
					
				}
				else {
					event.getDrops().clear();
					event.getEntity().getKiller().getInventory().addItem(ItemManager.createLootBag("Steel Giant", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODIyYTQ4YTU3NTllZGRlZjllMjkxOGZjODU5OTZmODQ5MWNjOTI1NzhkNTRkY2Q2MmUyYjZkOTEzYmZiNDIxZSJ9fX0"));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Diamond Giant")) {
				for(Entity e : event.getEntity().getWorld().getNearbyEntities(event.getEntity().getLocation(), 50, 50, 50)) {
					if(e instanceof Player) {
						if(((Player) e).hasPotionEffect(PotionEffectType.SLOW)) {
							((Player) e).removePotionEffect(PotionEffectType.SLOW);
						}
					}
				}
				if(event.getEntity().getKiller() == null) {
					event.getDrops().clear();
					event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ItemManager.createLootBag("Diamond Giant", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTk3M2ZmYWMyOGVmYzMzNGVmYWFjZjYxZmVjNTcyMmNmZjBjOTg1OTUxZTVkMjBhNjIyOWNkMTU0YjdlMTljIn19fQ"));
					
				}
				else {
					event.getDrops().clear();
					event.getEntity().getKiller().getInventory().addItem(ItemManager.createLootBag("Diamond Giant", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTk3M2ZmYWMyOGVmYzMzNGVmYWFjZjYxZmVjNTcyMmNmZjBjOTg1OTUxZTVkMjBhNjIyOWNkMTU0YjdlMTljIn19fQ"));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Satan")) {
				for(Entity e : event.getEntity().getWorld().getNearbyEntities(event.getEntity().getLocation(), 50, 50, 50)) {
					if(e instanceof Player) {
						if(((Player) e).hasPotionEffect(PotionEffectType.BLINDNESS)) {
							((Player) e).removePotionEffect(PotionEffectType.BLINDNESS);
						}
					}
				}
				if(event.getEntity().getKiller() == null) {
					event.getDrops().clear();
					event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ItemManager.createLootBag("Satan", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYxYTkxOGMwYzQ5YmE4ZDA1M2U1MjJjYjkxYWJjNzQ2ODkzNjdiNGQ4YWEwNmJmYzFiYTkxNTQ3MzA5ODVmZiJ9fX0"));
					
				}
				else {
					event.getDrops().clear();
					event.getEntity().getKiller().getInventory().addItem(ItemManager.createLootBag("Satan", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTYxYTkxOGMwYzQ5YmE4ZDA1M2U1MjJjYjkxYWJjNzQ2ODkzNjdiNGQ4YWEwNmJmYzFiYTkxNTQ3MzA5ODVmZiJ9fX0"));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Undead Lord")) {
				for(Entity e : event.getEntity().getWorld().getNearbyEntities(event.getEntity().getLocation(), 50, 50, 50)) {
					if(e instanceof Player) {
						if(((Player) e).hasPotionEffect(PotionEffectType.BLINDNESS)) {
							((Player) e).removePotionEffect(PotionEffectType.BLINDNESS);
						}
					}
				}
				if(event.getEntity().getKiller() == null) {
					event.getDrops().clear();
					event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ItemManager.createLootBag("Lord of the Undead", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjVmNTIxZGQyMTVhOTU4NjIxYTAwZmMwOGRmYTU1NTIyZTEzODNlNTNmZGM3ODFhOTMxNzY2ZDQ2ZDAyYWMwNyJ9fX0"));
					
				}
				else {
					event.getDrops().clear();
					event.getEntity().getKiller().getInventory().addItem(ItemManager.createLootBag("Lord of the Undead", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjVmNTIxZGQyMTVhOTU4NjIxYTAwZmMwOGRmYTU1NTIyZTEzODNlNTNmZGM3ODFhOTMxNzY2ZDQ2ZDAyYWMwNyJ9fX0"));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Frozen Disaster")) {
				for(Entity e : event.getEntity().getWorld().getNearbyEntities(event.getEntity().getLocation(), 50, 50, 50)) {
					if(e instanceof Player) {
						if(((Player) e).hasPotionEffect(PotionEffectType.SLOW)) {
							((Player) e).removePotionEffect(PotionEffectType.SLOW);
						}
					}
				}
				if(event.getEntity().getKiller() == null) {
					event.getDrops().clear();
					event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ItemManager.createLootBag("Frozen Disaster", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNThlYjYxZjgzNDAzNzYyM2UzMmZjYTJkMzJjZWU1MjMyOWFjNDk4YjY1ODU1N2IwMTAyY2FmOTE1NTgzOGQ0MiJ9fX0"));
					
				}
				else {
					event.getDrops().clear();
					event.getEntity().getKiller().getInventory().addItem(ItemManager.createLootBag("Frozen Disaster", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNThlYjYxZjgzNDAzNzYyM2UzMmZjYTJkMzJjZWU1MjMyOWFjNDk4YjY1ODU1N2IwMTAyY2FmOTE1NTgzOGQ0MiJ9fX0"));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Piglin Captain")) {
				if(event.getEntity().getKiller() == null) {
					event.getDrops().clear();
					event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ItemManager.createLootBag("Piglin Captain", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmU3YWFmMGJmZGNlMmZkMDM0NTQwYTM2MjIwNTc0NmFlMTMzM2Q4ODZkYTk4ZTdhY2JkY2IxNzA0N2Y5ZDA0NCJ9fX0"));
					
				}
				else {
					event.getDrops().clear();
					event.getEntity().getKiller().getInventory().addItem(ItemManager.createLootBag("Piglin Captain", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmU3YWFmMGJmZGNlMmZkMDM0NTQwYTM2MjIwNTc0NmFlMTMzM2Q4ODZkYTk4ZTdhY2JkY2IxNzA0N2Y5ZDA0NCJ9fX0"));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Anomalous Ravager")) {
				if(event.getEntity().getKiller() == null) {
					event.getDrops().clear();
					event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ItemManager.createLootBag("Anomalous Ravager", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDk5OTI2Y2NkZjYwZmIyOWY3YTdlMTg3ZjJiZDI5YWRiNmE1N2QzODQ3YmQ4NjExZDkxM2JjNDAwNWM1MGJmNyJ9fX0"));
					
				}
				else {
					event.getDrops().clear();
					event.getEntity().getKiller().getInventory().addItem(ItemManager.createLootBag("Anomalous Ravager", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDk5OTI2Y2NkZjYwZmIyOWY3YTdlMTg3ZjJiZDI5YWRiNmE1N2QzODQ3YmQ4NjExZDkxM2JjNDAwNWM1MGJmNyJ9fX0"));
				}
			}
			else if(event.getEntity().getMetadata("BossName").get(0).asString().equals("Skeleton Master")) {
				for(Entity e : event.getEntity().getWorld().getNearbyEntities(event.getEntity().getLocation(), 50, 50, 50)) {
					if(e instanceof Player) {
						if(((Player) e).hasPotionEffect(PotionEffectType.BLINDNESS)) {
							((Player) e).removePotionEffect(PotionEffectType.BLINDNESS);
						}
					}
				}
				if(event.getEntity().getKiller() == null) {
					event.getDrops().clear();
					event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), ItemManager.createLootBag("Skeleton Master", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQ1MTg0NTk0M2ZkMGMwN2Y2Mjk3MTFlMzQwMWE3MWEzMWNkMzcxY2MzY2IzNmYzZjk2MzdiMGU3NTljYzQ4YSJ9fX0"));
					
				}
				else {
					event.getDrops().clear();
					event.getEntity().getKiller().getInventory().addItem(ItemManager.createLootBag("Skeleton Master", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQ1MTg0NTk0M2ZkMGMwN2Y2Mjk3MTFlMzQwMWE3MWEzMWNkMzcxY2MzY2IzNmYzZjk2MzdiMGU3NTljYzQ4YSJ9fX0"));
				}
			}
		}
	}
	
	@EventHandler
	private void PlayerOpenLootBag(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getHand() == EquipmentSlot.OFF_HAND) {
				return;
			}
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))
					return;
				if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("lootbag")) {
					if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING).equals("Awakened Wither")) {
						int debri = random.nextInt(9)+4;
						int gold = random.nextInt(17)+16;
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), new ItemStack(Material.ANCIENT_DEBRIS, debri));
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), new ItemStack(Material.GOLD_INGOT, gold));
						event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
					}
					else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING).equals("Guardian of the Ocean")) {
						int shells = random.nextInt(4)+1;
						int sponge = random.nextInt(2)+2;
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), new ItemStack(Material.NAUTILUS_SHELL, shells));
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), new ItemStack(Material.SPONGE, sponge));
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), IngredientManager.SoulOfTheSea);
						float chance = random.nextFloat();
						if (chance <= 0.3f) // chance of 30%
						{
							event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), ItemManager.ReinforcedTrident);
						}
						event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
					}
					else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING).equals("Ender Lord")) {
						int endstone = random.nextInt(129)+128;
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), IngredientManager.PureEnder);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), new ItemStack(Material.END_STONE, endstone));
						event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
					}
					else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING).equals("Steel Giant")) {
						ItemStack psi = new ItemStack(IngredientManager.PerfectSteelIngot);
						psi.setAmount(random.nextInt(3)+2);
						int ironblock = random.nextInt(3)+3;
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), psi);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), new ItemStack(Material.IRON_BLOCK, ironblock));
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), ItemManager.GiantArmor[random.nextInt(4)]);
						event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
					}
					else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING).equals("Diamond Giant")) {
						ItemStack psi = new ItemStack(IngredientManager.PerfectSteelIngot);
						psi.setAmount(random.nextInt(5)+4);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), psi);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), IngredientManager.PerfectDiamond);
						float chance = random.nextFloat();
						if (chance <= 0.1f) // chance of 10%
						{
							event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), ItemManager.WornOutGiantGloves);
						}
						event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
					}
					else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING).equals("Satan")) {
						ItemStack felsh = new ItemStack(IngredientManager.DemonicFlesh);
						felsh.setAmount(random.nextInt(33)+32);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), felsh);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), IngredientManager.DismemberedDemonHead);
						float chance = random.nextFloat();
						if (chance <= 0.1f) // chance of 10%
						{
							event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), ItemManager.SatanBlade);
						}
						event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
					}
					else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING).equals("Lord of the Undead")) {
						ItemStack hide = new ItemStack(IngredientManager.UndeadHide);
						hide.setAmount(random.nextInt(17)+16);
						ItemStack soul = new ItemStack(IngredientManager.LostSoul);
						soul.setAmount(random.nextInt(5)+4);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), soul);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), hide);
						float chance = random.nextFloat();
						if (chance <= 0.2f) // chance of 20%
						{
							event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), ItemManager.UndeadRobe);
						}
						event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
					}
					else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING).equals("Frozen Disaster")) {
						ItemStack claw = new ItemStack(IngredientManager.AncientClaws);
						claw.setAmount(random.nextInt(7)+6);
						ItemStack fur = new ItemStack(IngredientManager.AncientFur);
						fur.setAmount(random.nextInt(5)+4);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), fur);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), claw);
						float chance = random.nextFloat();
						if (chance <= 0.1f) // chance of 10%
						{
							event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), ItemManager.ChillyStaff);
						}
						event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
					}
					else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING).equals("Piglin Captain")) {
						ItemStack skin = new ItemStack(IngredientManager.HoglinSkin);
						skin.setAmount(random.nextInt(17)+16);
						ItemStack tusk1 = new ItemStack(IngredientManager.HoglinTusk);
						tusk1.setAmount(random.nextInt(2)+1);
						ItemStack tusk2 = new ItemStack(IngredientManager.PiglinTusk);
						tusk2.setAmount(random.nextInt(2)+1);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), skin);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), tusk1);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), tusk2);
						float chance = random.nextFloat();
						if (chance <= 0.1f) // chance of 10%
						{
							event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), ItemManager.BruteAxe);
						}
						event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
					}
					else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING).equals("Anomalous Ravager")) {
						ItemStack skin = new ItemStack(IngredientManager.RavagerSkin);
						skin.setAmount(random.nextInt(65)+64);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), skin);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), new ItemStack(Material.SADDLE));
						float chance = random.nextFloat();
						if (chance <= 0.1f) // chance of 10%
						{
							event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), ItemManager.RavagerHead);
						}
						event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
					}
					else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING).equals("Skeleton Master")) {
						ItemStack bone = new ItemStack(IngredientManager.ScavangedSkeletalRemains);
						bone.setAmount(random.nextInt(5)+4);
						event.getPlayer().getInventory().addItem(bone);
						event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), bone);
						float chance = random.nextFloat();
						if (chance <= 0.2f) // chance of 20%
						{
							event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), ItemManager.RainBow);
						}
						event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
					}
				}
			}
		}
	}
}
