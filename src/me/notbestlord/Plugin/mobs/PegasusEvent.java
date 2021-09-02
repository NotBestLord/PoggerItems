package me.notbestlord.Plugin.mobs;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scoreboard.Score;
import org.spigotmc.event.entity.EntityDismountEvent;
import org.spigotmc.event.entity.EntityMountEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.IngredientManager;
import me.notbestlord.Plugin.items.ItemManager;

public class PegasusEvent implements Listener{
	
	public static List<Location> blo = new ArrayList<>();
	public static BukkitTask Task;
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getItem() != null && event.getItem().hasItemMeta()) {
				if(!event.getItem().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "itemID"), PersistentDataType.STRING))
					return;
				if(event.getItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "itemID"), PersistentDataType.STRING).equals("pegasusspawnegg")) {
					if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
						event.setCancelled(true);
					Player p = event.getPlayer();
					if(p.getGameMode().equals(GameMode.SURVIVAL)) {
						p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
					}
					Horse horse = (Horse) p.getWorld().spawn(p.getLocation(), Horse.class);
					horse.setAdult();
					horse.setStyle(Style.WHITE);
					horse.setTamed(true);
					horse.setOwner(p);
					horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
					horse.setCustomName("Pegasus");
					horse.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "mobID"), PersistentDataType.STRING, "pegasus");
				}
			}
		}
		
	}
	@EventHandler
	public static void onDismount(EntityDismountEvent event) {
		if(event.getDismounted().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "mobID"), PersistentDataType.STRING)) {
			if(event.getDismounted().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "mobID"), PersistentDataType.STRING).equals("pegasus")) {
				for(int i=0; i<blo.size(); i++) {
					blo.get(i).getBlock().setType(Material.AIR);
					Task.cancel();
				}
			}
		}
	}
	@EventHandler
	public static void onMount(EntityMountEvent event) {
		if(event.getMount().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "mobID"), PersistentDataType.STRING)) {
			if(event.getMount().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "mobID"), PersistentDataType.STRING).equals("pegasus")) {
				Task = new BukkitRunnable() {
					
					@Override
					public void run() {
						if(event.getMount().getLocation().subtract(0,1,0).getBlock().getType() == null)
		            		return;
		            	if(event.getMount().getLocation().subtract(0,1,0).getBlock().getType().equals(Material.AIR)) {
		            		blo.add(event.getMount().getLocation().subtract(0,1,0));
		            		event.getMount().getLocation().subtract(0,1,0).getBlock().setType(Material.WHITE_STAINED_GLASS);
		            	}
					}
				}.runTaskTimer(Main.getMain(), 0, 1);
			}
		}
	}
}
