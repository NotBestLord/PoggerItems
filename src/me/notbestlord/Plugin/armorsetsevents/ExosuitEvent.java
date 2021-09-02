package me.notbestlord.Plugin.armorsetsevents;

import java.util.Hashtable;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.ArmorSets;
import me.notbestlord.Plugin.items.ItemManager;

public class ExosuitEvent implements Listener{
	
	public static Hashtable<UUID, Integer> exosuitCounter = new Hashtable<>();
	public static Hashtable<UUID, Integer> levitatecounter = new Hashtable<>();
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent event) {
		if(!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
		if( (Boolean) ArmorSets.exosuitSet.get(player.getUniqueId()).get(0)) {
			if(Main.RedstoneFluxManager.getMaxRedstoneFlux(player)/2 > Main.RedstoneFluxManager.getRedstoneFlux(player)) {
				if(exosuitCounter.get(player.getUniqueId()) == 0) {
					player.sendMessage(ChatColor.RED+"The Exosuit requires your RF to be higher then 50%.");
					exosuitCounter.put(player.getUniqueId(), 1);
				}
				return;
			}
			if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("god")) {
				int reduce = (int) event.getDamage()*2;
				if(Main.RedstoneFluxManager.reduceRedstoneFlux(player, reduce)) {
					exosuitCounter.put(player.getUniqueId(), 0);
					event.setCancelled(true);
				}
			}
			if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("rain")) {
				if(player.getWorld().hasStorm()) {
					exosuitCounter.put(player.getUniqueId(), 0);
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerDealDamage(EntityDamageByEntityEvent event) {
		if(!(event.getDamager() instanceof Player)) {
			return;
		}
		
		Player player = (Player) event.getDamager();
		
		if( (Boolean) ArmorSets.exosuitSet.get(player.getUniqueId()).get(0)) {
			if(Main.RedstoneFluxManager.getMaxRedstoneFlux(player)/2 > Main.RedstoneFluxManager.getRedstoneFlux(player)) {
				if(exosuitCounter.get(player.getUniqueId()) == 0) {
					player.sendMessage(ChatColor.RED+"The Exosuit requires your RF to be higher then 50%.");
					exosuitCounter.put(player.getUniqueId(), 1);
				}
				return;
			}
			if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("redstone")) {
				int reduce = (int) event.getDamage()*4;
				if(Main.RedstoneFluxManager.reduceRedstoneFlux(player, reduce)) {
					exosuitCounter.put(player.getUniqueId(), 0);
					event.setDamage(event.getDamage()*2);
				}
			}
			if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("flash")) {
				exosuitCounter.put(player.getUniqueId(), 0);
				event.setDamage(event.getDamage()*1.4);
			}
			if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("gold")) {
				if(player.getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_SWORD) || player.getInventory().getItemInMainHand().getType().equals(Material.GOLDEN_AXE)) {
					int reduce = (int) event.getDamage()*8;
					if(Main.RedstoneFluxManager.reduceRedstoneFlux(player, reduce)) {
						exosuitCounter.put(player.getUniqueId(), 0);
						event.setDamage(event.getDamage()*4);
					}
					
				}
			}
		}
	}
	
	@EventHandler
    public void OnPlayerTakeDamage(EntityDamageByEntityEvent event) {
		if(!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
		if( (Boolean) ArmorSets.exosuitSet.get(player.getUniqueId()).get(0)) {
			if(Main.RedstoneFluxManager.getMaxRedstoneFlux(player)/2 > Main.RedstoneFluxManager.getRedstoneFlux(player)) {
				if(exosuitCounter.get(player.getUniqueId()) == 0) {
					player.sendMessage(ChatColor.RED+"The Exosuit requires your RF to be higher then 50%.");
					exosuitCounter.put(player.getUniqueId(), 1);
				}
				return;
			}
			if(event.getDamager().getType().equals(EntityType.PIGLIN) && !(event.getDamager().getType().equals(EntityType.PIGLIN_BRUTE))) {
				if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("gold")) {
					int reduce = (int) event.getDamage()*4;
					if(Main.RedstoneFluxManager.reduceRedstoneFlux(player, reduce)) {
						exosuitCounter.put(player.getUniqueId(), 0);
						event.setCancelled(true);
					}
				}
			}
		}
	}
	@EventHandler
    public void OnPlayerSneak(PlayerToggleSneakEvent event) {
		Player player = (Player) event.getPlayer();
		
		if( (Boolean) ArmorSets.exosuitSet.get(player.getUniqueId()).get(0)) {
			
			if(Main.RedstoneFluxManager.getMaxRedstoneFlux(player)/2 > Main.RedstoneFluxManager.getRedstoneFlux(player)) {
				if(exosuitCounter.get(player.getUniqueId()) == 0) {
					player.sendMessage(ChatColor.RED+"The Exosuit requires your RF to be higher then 50%.");
					exosuitCounter.put(player.getUniqueId(), 1);
				}
				return;
			}
			if(player.isGliding()) {
				if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("elytra")) {
					int reduce = 100;
					if(Main.RedstoneFluxManager.reduceRedstoneFlux(player, reduce)) {
						exosuitCounter.put(player.getUniqueId(), 0);
						player.setVelocity(player.getLocation().getDirection().setY(player.getLocation().getDirection().getY()+0.2));
					}
				}
			}
			
			if(player.isSwimming()) {
				if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("water")) {
					int reduce = 100;
					if(Main.RedstoneFluxManager.reduceRedstoneFlux(player, reduce)) {
						exosuitCounter.put(player.getUniqueId(), 0);
						player.setVelocity(player.getLocation().getDirection().setY(player.getLocation().getDirection().getY()+0.2));
					}
				}
			}
			
			if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("ghost")) {
				if(player.isSneaking()) {
					int temp = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
						public void run() {
							int reduce = 40;
							if(Main.RedstoneFluxManager.reduceRedstoneFlux(player, reduce)) {
								player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 21, 0, true));
							}
						}
					}, 0, 20);
					levitatecounter.put(player.getUniqueId(), temp);
				}
				else if(levitatecounter.get(player.getUniqueId()) != -1){
					Bukkit.getScheduler().cancelTask(levitatecounter.get(player.getUniqueId()));
					levitatecounter.put(player.getUniqueId(), -1);
				}
			}
			
		}
	}
	
	public static void perTickChecks(Player player) {
		onPlayerHungry(player);
		elytraExosuitCheck(player);
		waterExosuit(player);
		waterExosuitCheck(player);
		flashExosuit(player);
		ghostExosuit(player);
		rainExosuitCheck(player);
	}
	public static void initPlayer(Player player) {
		exosuitCounter.put(player.getUniqueId(), 0);
		levitatecounter.put(player.getUniqueId(), -1);
	}
	
	public static void elytraExosuitCheck(Player player) {
		ItemStack[] armor = player.getInventory().getArmorContents();
		if(armor[2] == null)
			return;
		if((Boolean) ArmorSets.exosuitSet.get(player.getUniqueId()).get(0)) {
			if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("elytra")) {
				armor[2].setType(Material.ELYTRA);
				player.getInventory().setArmorContents(armor);
			}
		}
		else if(armor[2].hasItemMeta()) {
			if(armor[2].getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
				if(armor[2].getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("exosuitchestplate")) {
					armor[2].setType(Material.LEATHER_CHESTPLATE);
					LeatherArmorMeta meta = (LeatherArmorMeta) armor[2].getItemMeta();
					meta.setColor(Color.WHITE);
					armor[2].setItemMeta(meta);
					player.getInventory().setArmorContents(armor);
				}
			}
		}
	}
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999|| event.getClickedInventory()==null) {
			return;
		}
		if(event.getCurrentItem() == null) {
			return;
		}
		if(!event.getCurrentItem().hasItemMeta()) {
			return;
		}
		if(!event.getCurrentItem().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
			return;
		}
		Player player = (Player) event.getWhoClicked();
		if(event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("exosuitchestplate")) {
			if((event.getCurrentItem().getType() == Material.ELYTRA && !(Boolean) ArmorSets.exosuitSet.get(player.getUniqueId()).get(0)) || (event.getCurrentItem().getType() == Material.ELYTRA && ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("elytra"))) {
				if(event.getSlot() == 38) {
					ItemStack chestplate = event.getCurrentItem();
					chestplate.setType(Material.LEATHER_CHESTPLATE);
					LeatherArmorMeta meta = (LeatherArmorMeta) ItemManager.ExoSuit[2].getItemMeta();
					meta.setColor(Color.WHITE);
					chestplate.setItemMeta(meta);
					player.setItemOnCursor(chestplate);
					player.getInventory().setChestplate(null);
					player.updateInventory();
				}
			}
		}
		else if(event.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("exosuitboots")) {
			if((event.getCurrentItem().getEnchantmentLevel(Enchantment.DEPTH_STRIDER) == 3 && !(Boolean) ArmorSets.exosuitSet.get(player.getUniqueId()).get(0)) || ((event.getCurrentItem().getEnchantmentLevel(Enchantment.DEPTH_STRIDER) == 3) && ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("water"))) {
				if(event.getSlot() == 36) {
					ItemStack boots = event.getCurrentItem();
					LeatherArmorMeta meta = (LeatherArmorMeta) ItemManager.ExoSuit[0].getItemMeta();
					meta.removeEnchant(Enchantment.DEPTH_STRIDER);
					boots.setItemMeta(meta);
					player.setItemOnCursor(boots);
					player.getInventory().setBoots(null);
					player.updateInventory();
				}
			}
		}
	}
	public static void onPlayerHungry(Player player) {
		if(player.getSaturation() == 2)
			return;
		if( (Boolean) ArmorSets.exosuitSet.get(player.getUniqueId()).get(0)) {
			if(Main.RedstoneFluxManager.getMaxRedstoneFlux(player)/2 > Main.RedstoneFluxManager.getRedstoneFlux(player)) {
				if(exosuitCounter.get(player.getUniqueId()) == 0) {
					player.sendMessage(ChatColor.RED+"The Exosuit requires your RF to be higher then 50%.");
					exosuitCounter.put(player.getUniqueId(), 1);
				}
				return;
			}
			if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("food")) {
				float saturation = 20.0f-player.getSaturation();
				int reduce = 2*((int)saturation*10);
				if(Main.RedstoneFluxManager.reduceRedstoneFlux(player, reduce)) {
					exosuitCounter.put(player.getUniqueId(), 0);
					player.setSaturation(20.0f);
					player.setFoodLevel(21);
				}
			}
			
		}
	}
	public static void waterExosuit(Player player) {
		if((Boolean) ArmorSets.exosuitSet.get(player.getUniqueId()).get(0)) {
			if(Main.RedstoneFluxManager.getMaxRedstoneFlux(player)/2 > Main.RedstoneFluxManager.getRedstoneFlux(player)) {
				if(exosuitCounter.get(player.getUniqueId()) == 0) {
					player.sendMessage(ChatColor.RED+"The Exosuit requires your RF to be higher then 50%.");
					exosuitCounter.put(player.getUniqueId(), 1);
				}
				return;
			}
			if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("water")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 2, 0, true));
			}
			
		}
	}
	public static void waterExosuitCheck(Player player) {
		ItemStack[] armor = player.getInventory().getArmorContents();
		if(armor[2] == null)
			return;
		if( (Boolean) ArmorSets.exosuitSet.get(player.getUniqueId()).get(0)) {
			if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("water")) {
				armor[0].addEnchantment(Enchantment.DEPTH_STRIDER, 3);
				player.getInventory().setArmorContents(armor);
			}
		}
	}
	public static void flashExosuit(Player player) {
		if((Boolean) ArmorSets.exosuitSet.get(player.getUniqueId()).get(0)) {
			if(Main.RedstoneFluxManager.getMaxRedstoneFlux(player)/2 > Main.RedstoneFluxManager.getRedstoneFlux(player)) {
				if(exosuitCounter.get(player.getUniqueId()) == 0) {
					player.sendMessage(ChatColor.RED+"The Exosuit requires your RF to be higher then 50%.");
					exosuitCounter.put(player.getUniqueId(), 1);
				}
				return;
			}
			if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("flash")) {
				int reduction = 10;
				if(Main.RedstoneFluxManager.reduceRedstoneFlux(player, reduction)) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2, 1, true));
					player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 2, 2, true));
				}
			}
			
		}
	}
	public static void ghostExosuit(Player player) {
		if((Boolean) ArmorSets.exosuitSet.get(player.getUniqueId()).get(0)) {
			if(Main.RedstoneFluxManager.getMaxRedstoneFlux(player)/2 > Main.RedstoneFluxManager.getRedstoneFlux(player)) {
				if(exosuitCounter.get(player.getUniqueId()) == 0) {
					player.sendMessage(ChatColor.RED+"The Exosuit requires your RF to be higher then 50%.");
					exosuitCounter.put(player.getUniqueId(), 1);
				}
				return;
			}
			if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("ghost")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2, 0, true));
				player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 2, 0, true));
			}
		}
		else {
			if(levitatecounter.get(player.getUniqueId()) != -1) {
				Bukkit.getScheduler().cancelTask(levitatecounter.get(player.getUniqueId()));
				levitatecounter.put(player.getUniqueId(), -1);
			}
		}
	}
	public static void rainExosuitCheck(Player player) {
		/*if((Boolean) ArmorSets.exosuitSet.get(player.getUniqueId()).get(0)) {
			if(ArmorSets.exosuitSet.get(player.getUniqueId()).get(1).equals("rain")) {
				int reduction = 20;
				if(!Main.RedstoneFluxManager.reduceRedstoneFlux(player, reduction)) {
					if(player.getGameMode().equals(GameMode.SURVIVAL)) {
						player.setAllowFlight(false);
					}
					return;
				}
				if(player.getWorld().hasStorm()) {
					player.setAllowFlight(true);
				}
				else if(player.getGameMode().equals(GameMode.SURVIVAL)) {
					player.setAllowFlight(false);
				}
			}
			else if(player.getGameMode().equals(GameMode.SURVIVAL)) {
				player.setAllowFlight(false);
			}
		}
		else if(player.getGameMode().equals(GameMode.SURVIVAL)) {
			player.setAllowFlight(false);
		}*/
	}
}
