package me.notbestlord.Plugin.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.ItemManager;

public class MultiToolEvents implements Listener{
	
	@EventHandler
	public static void onBlockDamage(BlockDamageEvent event) {
		if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
			if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))
				return;
			if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("ironmultitool")) {
				Player player = event.getPlayer();
				if(!event.getBlock().getType().equals(Material.BEDROCK)) {
					int reduce = 5000;
					if(Main.RedstoneFluxManager.reduceRedstoneFlux(player, reduce)) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20, 0, true));
					}
				}
			}
			else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("steelmultitool")) {
				Player player = event.getPlayer();
				if(!event.getBlock().getType().equals(Material.BEDROCK)) {
					int reduce = 1000;
					if(Main.RedstoneFluxManager.reduceRedstoneFlux(player, reduce)) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20, 1, true));
					}
				}
			}
			else if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("diamondmultitool")) {
				Player player = event.getPlayer();
				if(!event.getBlock().getType().equals(Material.BEDROCK)) {
					int reduce = 250;
					if(Main.RedstoneFluxManager.reduceRedstoneFlux(player, reduce)) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20, 1, true));
					}
				}
			}
		}
	}
	
}
