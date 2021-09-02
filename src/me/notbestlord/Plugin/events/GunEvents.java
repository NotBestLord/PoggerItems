package me.notbestlord.Plugin.events;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Particle.DustOptions;
import org.bukkit.Particle.DustTransition;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.ItemManager;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.network.protocol.game.PacketPlayOutEntityDestroy;

public class GunEvents implements Listener{
	
	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getHand() == EquipmentSlot.OFF_HAND) {
			return;
		}
		
		if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
			if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
				return;
			}
			//Pistol:
			if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("pistol")) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) > 0) {
						Arrow arrow = (Arrow) event.getPlayer().getWorld().spawnArrow(event.getPlayer().getLocation().add(0, 1, 0), event.getPlayer().getLocation().getDirection().normalize().multiply(1.2), 5, 1);
						arrow.setShooter(event.getPlayer());
						arrow.setDamage(0);
						arrow.setMetadata("pistol bullet", new FixedMetadataValue(Main.getMain(), true));
						arrow.setMetadata("bullet", new FixedMetadataValue(Main.getMain(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
							
							@Override
							public void run() {
								for(int offset=0; offset<4; ++offset) {
					                Location location = arrow.getLocation().clone();
					                location.setX(location.getX() + arrow.getVelocity().getX() * (double) offset / 4.0D);
					                location.setY(location.getY() + arrow.getVelocity().getY() * (double) offset / 4.0D);
					                location.setZ(location.getZ() + arrow.getVelocity().getZ() * (double) offset / 4.0D);
									DustTransition dustTransition = new DustTransition(Color.RED, Color.RED, 2.0F);
									arrow.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, location, 1, dustTransition);
					            }
							}
						}, 1, 0)));
						PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(arrow.getEntityId());
						for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						    ((CraftPlayer) p).getHandle().b.sendPacket(packet);
						}
						ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
						meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER,
								meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER)-1);
						meta.setDisplayName(ChatColor.of("#fffff0")+"Pistol - " +
						meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) +
						" Ammo");
						event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
					}
					else {
						if(ItemManager.doesContainItemID(event.getPlayer(), "9mmAmmo")) {
							Inventory inv = event.getPlayer().getInventory();
							for(int i=0; i<inv.getSize();i++) {
								if(inv.getItem(i) != null && inv.getItem(i).hasItemMeta()) {
									if(inv.getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
										if(inv.getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("9mmAmmo")) {
											ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
											meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER, 16);
											meta.setDisplayName(ChatColor.of("#fffff0")+"Pistol - " +
											meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) +
											" Ammo");
											event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
											inv.getItem(i).setAmount(inv.getItem(i).getAmount()-1);
											break;
										}
									}
								}
							}
						}
					}
				}
				else {
					if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) < 16) {
						if(ItemManager.doesContainItemID(event.getPlayer(), "9mmAmmo")) {
							Inventory inv = event.getPlayer().getInventory();
							for(int i=0; i<inv.getSize();i++) {
								if(inv.getItem(i) != null && inv.getItem(i).hasItemMeta()) {
									if(inv.getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
										if(inv.getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("9mmAmmo")) {
											ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
											meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER, 16);
											meta.setDisplayName(ChatColor.of("#fffff0")+"Pistol - " +
											meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) +
											" Ammo");
											event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
											inv.getItem(i).setAmount(inv.getItem(i).getAmount()-1);
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			//Uzi:
			else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("uzi")) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) > 0) {
						ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
						meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER,
								meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER)-1);
						meta.setDisplayName(ChatColor.of("#fffff0")+"Uzi - " +
						meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) +
						" Ammo");
						event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
						Arrow arrow = (Arrow) event.getPlayer().getWorld().spawnArrow(event.getPlayer().getLocation().add(0, 1, 0), event.getPlayer().getLocation().getDirection().normalize().multiply(1.2), 5, 1);
						arrow.setShooter(event.getPlayer());
						arrow.setDamage(0);
						arrow.setMetadata("uzi bullet", new FixedMetadataValue(Main.getMain(), true));
						arrow.setMetadata("bullet", new FixedMetadataValue(Main.getMain(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
							
							@Override
							public void run() {
								for(int offset=0; offset<4; ++offset) {
									Location location = arrow.getLocation().clone();
									location.setX(location.getX() + arrow.getVelocity().getX() * (double) offset / 4.0D);
									location.setY(location.getY() + arrow.getVelocity().getY() * (double) offset / 4.0D);
									location.setZ(location.getZ() + arrow.getVelocity().getZ() * (double) offset / 4.0D);
									DustTransition dustTransition = new DustTransition(Color.RED, Color.RED, 2.0F);
									arrow.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, location, 1, dustTransition);
						           }
							}
						}, 1, 0)));
						for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						    PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(arrow.getEntityId());
						    ((CraftPlayer) p).getHandle().b.sendPacket(packet);
						}
					}
					else {
						if(ItemManager.doesContainItemID(event.getPlayer(), "9mmAmmo")) {
							Inventory inv = event.getPlayer().getInventory();
							for(int i=0; i<inv.getSize();i++) {
								if(inv.getItem(i) != null && inv.getItem(i).hasItemMeta()) {
									if(inv.getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
										if(inv.getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("9mmAmmo")) {
											ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
											meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER, 16);
											meta.setDisplayName(ChatColor.of("#fffff0")+"Uzi - " +
											meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) +
											" Ammo");
											event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
											inv.getItem(i).setAmount(inv.getItem(i).getAmount()-1);
											break;
										}
									}
								}
							}
						}
					}
				}
				else {
					if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) < 16) {
						if(ItemManager.doesContainItemID(event.getPlayer(), "9mmAmmo")) {
							Inventory inv = event.getPlayer().getInventory();
							for(int i=0; i<inv.getSize();i++) {
								if(inv.getItem(i) != null && inv.getItem(i).hasItemMeta()) {
									if(inv.getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
										if(inv.getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("9mmAmmo")) {
											ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
											meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER, 16);
											meta.setDisplayName(ChatColor.of("#fffff0")+"Uzi - " +
											meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) +
											" Ammo");
											event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
											inv.getItem(i).setAmount(inv.getItem(i).getAmount()-1);
											break;
										}
									}
								}
							}
						}
					}
				}
				
			}
			//Revolver:
			else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("revolver")) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) > 0) {
						Arrow arrow = (Arrow) event.getPlayer().getWorld().spawnArrow(event.getPlayer().getLocation().add(0, 1, 0), event.getPlayer().getLocation().getDirection().normalize().multiply(1.2), 5, 1);
						arrow.setShooter(event.getPlayer());
						arrow.setDamage(0);
						arrow.setMetadata("revolver bullet", new FixedMetadataValue(Main.getMain(), true));
						arrow.setMetadata("Headshotable", new FixedMetadataValue(Main.getMain(), true));
						arrow.setMetadata("bullet", new FixedMetadataValue(Main.getMain(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
							
							@Override
							public void run() {
								for(int offset=0; offset<4; ++offset) {
					                Location location = arrow.getLocation().clone();
					                location.setX(location.getX() + arrow.getVelocity().getX() * (double) offset / 4.0D);
					                location.setY(location.getY() + arrow.getVelocity().getY() * (double) offset / 4.0D);
					                location.setZ(location.getZ() + arrow.getVelocity().getZ() * (double) offset / 4.0D);
									DustTransition dustTransition = new DustTransition(Color.RED, Color.RED, 2.0F);
									arrow.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, location, 1, dustTransition);
					            }
							}
						}, 1, 0)));
						for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						    PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(arrow.getEntityId());
						    ((CraftPlayer) p).getHandle().b.sendPacket(packet);
						}
						ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
						meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER,
								meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER)-1);
						meta.setDisplayName(ChatColor.of("#fffff0")+"Revolver - " +
						meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) +
						" Ammo");
						event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
					}
					else {
						if(ItemManager.doesContainItemID(event.getPlayer(), "revolverAmmo")) {
							Inventory inv = event.getPlayer().getInventory();
							for(int i=0; i<inv.getSize();i++) {
								if(inv.getItem(i) != null && inv.getItem(i).hasItemMeta()) {
									if(inv.getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
										if(inv.getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("revolverAmmo")) {
											ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
											meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER, 6);
											meta.setDisplayName(ChatColor.of("#fffff0")+"Revolver - " +
											meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) +
											" Ammo");
											event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
											inv.getItem(i).setAmount(inv.getItem(i).getAmount()-1);
											break;
										}
									}
								}
							}
						}
					}
				}
				else {
					if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) < 6) {
						if(ItemManager.doesContainItemID(event.getPlayer(), "revolverAmmo")) {
							Inventory inv = event.getPlayer().getInventory();
							for(int i=0; i<inv.getSize();i++) {
								if(inv.getItem(i) != null && inv.getItem(i).hasItemMeta()) {
									if(inv.getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
										if(inv.getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("revolverAmmo")) {
											ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
											meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER, 6);
											meta.setDisplayName(ChatColor.of("#fffff0")+"Revolver - " +
											meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) +
											" Ammo");
											event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
											inv.getItem(i).setAmount(inv.getItem(i).getAmount()-1);
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			//Barrett M82:
			else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("BarrettM82")) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) > 0) {
						Arrow arrow = (Arrow) event.getPlayer().getWorld().spawnArrow(event.getPlayer().getLocation().add(0, 1, 0), event.getPlayer().getLocation().getDirection().normalize().multiply(1.2), 8, 1);
						arrow.setShooter(event.getPlayer());
						arrow.setDamage(0);
						arrow.setMetadata("BarrettM82 bullet", new FixedMetadataValue(Main.getMain(), true));
						arrow.setMetadata("Headshotable", new FixedMetadataValue(Main.getMain(), true));
						arrow.setMetadata("bullet", new FixedMetadataValue(Main.getMain(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
							
							@Override
							public void run() {
								for(int offset=0; offset<4; ++offset) {
					                Location location = arrow.getLocation().clone();
					                location.setX(location.getX() + arrow.getVelocity().getX() * (double) offset / 4.0D);
					                location.setY(location.getY() + arrow.getVelocity().getY() * (double) offset / 4.0D);
					                location.setZ(location.getZ() + arrow.getVelocity().getZ() * (double) offset / 4.0D);
									DustTransition dustTransition = new DustTransition(Color.RED, Color.RED, 2.0F);
									arrow.getWorld().spawnParticle(Particle.DUST_COLOR_TRANSITION, location, 1, dustTransition);
					            }
							}
						}, 1, 0)));
						for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						    PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(arrow.getEntityId());
						    ((CraftPlayer) p).getHandle().b.sendPacket(packet);
						}
						ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
						meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER,
								meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER)-1);
						meta.setDisplayName(ChatColor.of("#fffff0")+"Barret M82 - " +
						meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) +
						" Ammo");
						event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
						
					}
					else {
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
							
							@Override
							public void run() {
								if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
									if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
										if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("BarrettM82")) {
											if(ItemManager.doesContainItemID(event.getPlayer(), "sniperAmmo")) {
												Inventory inv = event.getPlayer().getInventory();
												for(int i=0; i<inv.getSize();i++) {
													if(inv.getItem(i) != null && inv.getItem(i).hasItemMeta()) {
														if(inv.getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
															if(inv.getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("sniperAmmo")) {
																ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
																meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER, 2);
																meta.setDisplayName(ChatColor.of("#fffff0")+"Barret M82 - " +
																meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) +
																" Ammo");
																event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
																inv.getItem(i).setAmount(inv.getItem(i).getAmount()-1);
																event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_STEM_BREAK, 1, 1);
																break;
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}, 40);
					}
				}
				else {
					if(!event.getPlayer().isSneaking()) {
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
							
							@Override
							public void run() {
								if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
									if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
										if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("BarrettM82")) {
											if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) < 2) {
												if(ItemManager.doesContainItemID(event.getPlayer(), "sniperAmmo")) {
													Inventory inv = event.getPlayer().getInventory();
													for(int i=0; i<inv.getSize();i++) {
														if(inv.getItem(i) != null && inv.getItem(i).hasItemMeta()) {
															if(inv.getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
																if(inv.getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("sniperAmmo")) {
																	ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
																	meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER, 2);
																	meta.setDisplayName(ChatColor.of("#fffff0")+"Barret M82 - " +
																	meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) +
																	" Ammo");
																	event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
																	inv.getItem(i).setAmount(inv.getItem(i).getAmount()-1);
																	event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_STEM_BREAK, 1, 1);
																	break;
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}, 40);
					}else{
						if(event.getPlayer().hasMetadata("SniperScope")) {
							Bukkit.getScheduler().cancelTask(event.getPlayer().getMetadata("SniperScope").get(0).asInt());
							event.getPlayer().removeMetadata("SniperScope", Main.getMain());
						}
						else {
							event.getPlayer().setMetadata("SniperScope", new FixedMetadataValue(Main.getMain(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
								
								@Override
								public void run() {
									event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 2, 19, true));
								}
							}, 0, 1)));
						}
					}
				}
			}
			//Sex Pistols:
			else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("sexpistols")) {
				if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) > 0) {
						Arrow arrow = (Arrow) event.getPlayer().getWorld().spawnArrow(event.getPlayer().getLocation().add(0, 1, 0), event.getPlayer().getLocation().getDirection().normalize().multiply(1.2), 8, 1);
						arrow.setShooter(event.getPlayer());
						arrow.setDamage(0);
						if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Target"), PersistentDataType.INTEGER) != -1) {
							LivingEntity entity = null;
							for(Entity e : event.getPlayer().getNearbyEntities(50, 50, 50)) {
								if(e instanceof LivingEntity) {
									LivingEntity en = (LivingEntity) e;
									if(en.getEntityId() == event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Target"), PersistentDataType.INTEGER)) {
										entity = en;
										break;
									}
								}
							}
							if(entity != null) {
								arrow.setMetadata("Target", new FixedMetadataValue(Main.getMain(), entity));
								arrow.setMetadata("TargetRunnable", new FixedMetadataValue(Main.getMain(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
									
									@Override
									public void run() {
										LivingEntity target = (LivingEntity) arrow.getMetadata("Target").get(0).value();
										if(!target.isDead()) {
											Vector arrowVec = arrow.getLocation().toVector();
											Vector targetVec = target.getLocation().add(0,0.5D,0).toVector();
									        Vector finalVec = targetVec.subtract(arrowVec).normalize();
									        arrow.setVelocity(finalVec.add(new Vector(0.0, 0.03, 0.0)));
										}
									}
								}, 2, 1)));
							}
						}
						arrow.setMetadata("SexPistols bullet", new FixedMetadataValue(Main.getMain(), true));
						arrow.setMetadata("bullet", new FixedMetadataValue(Main.getMain(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
							
							@Override
							public void run() {
								for(int offset=0; offset<4; ++offset) {
					                Location location = arrow.getLocation().clone();
					                location.setX(location.getX() + arrow.getVelocity().getX() * (double) offset / 4.0D);
					                location.setY(location.getY() + arrow.getVelocity().getY() * (double) offset / 4.0D);
					                location.setZ(location.getZ() + arrow.getVelocity().getZ() * (double) offset / 4.0D);
									DustOptions dustTransition = new DustOptions(Color.PURPLE, 2.0F);
									arrow.getWorld().spawnParticle(Particle.REDSTONE, location, 1, dustTransition);
					            }
							}
						}, 1, 0)));
						for(Player p : Bukkit.getServer().getOnlinePlayers()) {
						    PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(arrow.getEntityId());
						    ((CraftPlayer) p).getHandle().b.sendPacket(packet);
						}
						ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
						meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER,
								meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER)-1);
						meta.setDisplayName(ChatColor.of("#bf0fcb")+"Sex Pistols - " +
						meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) +
						" Ammo");
						event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
						
					}
					else {
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
							
							@Override
							public void run() {
								if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
									if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
										if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("sexpistols")) {
											if(ItemManager.doesContainItemID(event.getPlayer(), "revolverAmmo")) {
												Inventory inv = event.getPlayer().getInventory();
												for(int i=0; i<inv.getSize();i++) {
													if(inv.getItem(i) != null && inv.getItem(i).hasItemMeta()) {
														if(inv.getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
															if(inv.getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("revolverAmmo")) {
																ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
																meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER, 6);
																meta.setDisplayName(ChatColor.of("#bf0fcb")+"Sex Pistols - " +
																meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) +
																" Ammo");
																event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
																inv.getItem(i).setAmount(inv.getItem(i).getAmount()-1);
																event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_STEM_BREAK, 1, 1);
																break;
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}, 2);
					}
				}
				else {
					if(!event.getPlayer().isSneaking()) {
						Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
							
							@Override
							public void run() {
								if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
									if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
										if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("sexpistols")) {
											if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) < 6) {
												if(ItemManager.doesContainItemID(event.getPlayer(), "revolverAmmo")) {
													Inventory inv = event.getPlayer().getInventory();
													for(int i=0; i<inv.getSize();i++) {
														if(inv.getItem(i) != null && inv.getItem(i).hasItemMeta()) {
															if(inv.getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
																if(inv.getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("revolverAmmo")) {
																	ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
																	meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER, 6);
																	meta.setDisplayName(ChatColor.of("#bf0fcb")+"Sex Pistols - " +
																	meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "Ammo"), PersistentDataType.INTEGER) +
																	" Ammo");
																	event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
																	inv.getItem(i).setAmount(inv.getItem(i).getAmount()-1);
																	event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_STEM_BREAK, 1, 1);
																	break;
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}, 2);
					}else{
						Player player = event.getPlayer();
						LivingEntity entity = null;
						for(Entity e : player.getNearbyEntities(50, 50, 50)) {
							if(e instanceof LivingEntity) {
								LivingEntity en = (LivingEntity) e;
								Location eye = player.getEyeLocation();
							    Vector toEntity = en.getEyeLocation().toVector().subtract(eye.toVector());
							    double dot = toEntity.normalize().dot(eye.getDirection());
							    if(dot>0.99D) {
							    	entity = en;
							    	break;
							    }
							}
						}
						if(entity == null) {
							return;
						}
						ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
						meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "Target"), PersistentDataType.INTEGER, entity.getEntityId());
						List<String> lore = meta.getLore();
						lore.set(lore.size()-1, ChatColor.GRAY+" Target: "+entity.getType().name().toLowerCase().replace("_", " "));
						meta.setLore(lore);
						player.getInventory().getItemInMainHand().setItemMeta(meta);
						player.sendMessage(ChatColor.GRAY+"Target Set");
					}
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
		if(arrow.hasMetadata("Headshotable")) {
			if(event.getHitEntity() != null) {
				double hit = arrow.getLocation().getY();
				double head = ((LivingEntity)event.getHitEntity()).getEyeLocation().getY();
				if(Math.abs(head-hit)<=0.7) {
					if(arrow.getShooter() instanceof Player) {
						((Player)arrow.getShooter()).sendMessage(ChatColor.GRAY+"Head Shot");
					}
					if(arrow.hasMetadata("revolver bullet")) {
						((LivingEntity)event.getHitEntity()).damage(10);
					}
					else if(arrow.hasMetadata("BarrettM82 bullet")) {
						((LivingEntity)event.getHitEntity()).damage(20);
					}
				}
				else {
					if(arrow.hasMetadata("revolver bullet")) {
						((LivingEntity)event.getHitEntity()).damage(5);
					}
					else if(arrow.hasMetadata("BarrettM82 bullet")) {
						((LivingEntity)event.getHitEntity()).damage(10);
					}
				}
			}
		}
		if(arrow.hasMetadata("pistol bullet")) {
			if(event.getHitEntity() != null) {
				((LivingEntity)event.getHitEntity()).damage(4);
			}
		}
		if(arrow.hasMetadata("uzi bullet")) {
			if(event.getHitEntity() != null) {
				((LivingEntity)event.getHitEntity()).damage(3);
			}
		}
		if(arrow.hasMetadata("SexPistols bullet")) {
			if(event.getHitEntity() != null) {
				((LivingEntity)event.getHitEntity()).damage(4);
			}
		}
		if(arrow.hasMetadata("TargetRunnable")) {
			Bukkit.getScheduler().cancelTask(arrow.getMetadata("TargetRunnable").get(0).asInt());
		}
		if(arrow.hasMetadata("bullet")) {
			Bukkit.getScheduler().cancelTask(arrow.getMetadata("bullet").get(0).asInt());
			arrow.remove();
		}
		
	}
}
