package me.notbestlord.Plugin.events;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.notbestlord.Plugin.Main;

public class StopWatchEvent implements Listener{
	
	private ArrayList<Integer> Tasks = new ArrayList<>();
	public static Hashtable<UUID, Integer> cooldown = new Hashtable<UUID, Integer>();
	public static Hashtable<UUID, Integer> TaskID = new Hashtable<UUID, Integer>();
	
	@EventHandler
	private void onRightClick(PlayerInteractEvent event) throws InterruptedException {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))
					return;
				if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("stopwatch")) {
					if(!Tasks.isEmpty()) {
						event.getPlayer().sendMessage(ChatColor.RED + "Time is already stopped.");
						return;
					}
					if(cooldown.get(event.getPlayer().getUniqueId()) != 0) {
						event.getPlayer().sendMessage(ChatColor.RED + "The item is currently on cooldown for "+cooldown.get(event.getPlayer().getUniqueId())+" more seconds.");
						return;
					}
					
					for(Entity entity : event.getPlayer().getWorld().getNearbyEntities(event.getPlayer().getLocation(), 15, 15, 15)) {
						if((entity instanceof Player)) {
							if(!((Player)entity).getUniqueId().equals(event.getPlayer().getUniqueId())) {
								Location freezeLocation = entity.getLocation();
								Tasks.add(Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
									
									@Override
									public void run() {
										entity.teleport(freezeLocation);
										((Player) entity).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 21, 4));
									}
								}, 0, 1));
							}
						}else {
							Location freezeLocation = entity.getLocation();
							Tasks.add(Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
								
								@Override
								public void run() {
									entity.teleport(freezeLocation);
								}
							}, 0, 1));
						}
					}
					cooldown.put(event.getPlayer().getUniqueId(), 30);
					TaskID.put(event.getPlayer().getUniqueId(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
						
						@Override
						public void run() {
							if(cooldown.get(event.getPlayer().getUniqueId()) == 0) {
								Bukkit.getScheduler().cancelTask(TaskID.get(event.getPlayer().getUniqueId()));
							}
							else {
								cooldown.put(event.getPlayer().getUniqueId(), cooldown.get(event.getPlayer().getUniqueId())-1);
							}
						}
					}, 0, 20));
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
						
						@Override
						public void run() {
							for(int task : Tasks) {
								Bukkit.getScheduler().cancelTask(task);
							}
							Tasks.clear();
						}
					}, 100);
				}
			}
		}
		else if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))
					return;
				if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("stopwatch")) {
					if(!Tasks.isEmpty()) {
						event.getPlayer().sendMessage(ChatColor.RED + "Time is already stopped.");
						return;
					}
					if(cooldown.get(event.getPlayer().getUniqueId()) != 0) {
						event.getPlayer().sendMessage(ChatColor.RED + "The item is currently on cooldown for "+cooldown.get(event.getPlayer().getUniqueId())+" more seconds.");
						return;
					}
					
					for(Entity entity : event.getPlayer().getWorld().getNearbyEntities(event.getPlayer().getLocation(), 15, 15, 15)) {
						if(!(entity instanceof Player)) {
							
							Location freezeLocation = entity.getLocation();
							Tasks.add(Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
								
								@Override
								public void run() {
									entity.teleport(freezeLocation);
								}
							}, 0, 1));
						}
					}
					cooldown.put(event.getPlayer().getUniqueId(), 30);
					TaskID.put(event.getPlayer().getUniqueId(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
						
						@Override
						public void run() {
							if(cooldown.get(event.getPlayer().getUniqueId()) == 0) {
								Bukkit.getScheduler().cancelTask(TaskID.get(event.getPlayer().getUniqueId()));
							}
							else {
								cooldown.put(event.getPlayer().getUniqueId(), cooldown.get(event.getPlayer().getUniqueId())-1);
							}
						}
					}, 0, 20));
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
						
						@Override
						public void run() {
							for(int task : Tasks) {
								Bukkit.getScheduler().cancelTask(task);
							}
							Tasks.clear();
						}
					}, 100);
				}
			}
		}
	}
}
