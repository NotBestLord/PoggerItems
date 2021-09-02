package me.notbestlord.Plugin.mobs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.ItemManager;
import net.minecraft.world.entity.item.EntityFallingBlock;
import net.minecraft.world.item.ItemPickaxe;

public class DroneEvents implements Listener{
	
	@EventHandler
	private void onPlayerRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getHand() == EquipmentSlot.OFF_HAND) {
				return;
			}
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "generelizedID"), PersistentDataType.STRING))
					return;
				if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "generelizedID"), PersistentDataType.STRING).equals("drone")) {
					if(!event.getPlayer().hasMetadata("dronebuff")) {
						new Drone(event.getPlayer(), event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "dronetier"), PersistentDataType.INTEGER));
						event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
					}
					else {
						event.getPlayer().sendMessage(ChatColor.RED+"You already have a drone.");
						return;
					}
				}
			}
		}
	}
	
	@EventHandler
	private void onPlayerRightClickDrone(PlayerInteractAtEntityEvent event) {
		if(!(event.getRightClicked() instanceof ArmorStand)) {
			return;
		}
		if(!event.getRightClicked().hasMetadata("isdrone")) {
			return;
		}
		if(UUID.fromString(event.getRightClicked().getMetadata("owner").get(0).asString()).equals(event.getPlayer().getUniqueId())) {
			event.getPlayer().openInventory((Inventory) event.getRightClicked().getMetadata("Inventory").get(0).value());
		}
	}
	
	@EventHandler
	private void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(!event.getView().getTitle().equals(ChatColor.GOLD+"Drone") || event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
			return;
		}
		event.setCancelled(true);
		
		Player player = (Player)event.getWhoClicked();
		if(event.getSlot() == 49) {
			player.closeInventory();
		}
		if(event.getSlot() == 22) {
			String mode = event.getClickedInventory().getItem(13).getItemMeta().getLore().get(0);
			if(mode.equals(ChatColor.BLUE+"none")) {
				ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.RED+"Damage Modification");
				lore.add(ChatColor.GRAY+"Increase player damage by 50%.");
				meta.setLore(lore);
				item.setItemMeta(meta);
				event.getClickedInventory().setItem(13, item);
				player.setMetadata("dronebuff", new FixedMetadataValue(Main.getMain(), "attack"));
			}
			else if(mode.equals(ChatColor.RED+"Damage Modification")) {
				ItemStack item = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.YELLOW+"Armor Enhancment");
				lore.add(ChatColor.GRAY+"Reduce damage taken by player by 50%.");
				meta.setLore(lore);
				item.setItemMeta(meta);
				event.getClickedInventory().setItem(13, item);
				player.setMetadata("dronebuff", new FixedMetadataValue(Main.getMain(), "defense"));
			}
			else if(mode.equals(ChatColor.YELLOW+"Armor Enhancment")) {
				if(event.getWhoClicked().getMetadata("dronetier").get(0).asInt() == 1) {
					ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
					List<String> lore = new ArrayList<>();
					lore.add(ChatColor.RED+"Damage Modification");
					lore.add(ChatColor.GRAY+"Increase player damage by 50%.");
					meta.setLore(lore);
					item.setItemMeta(meta);
					event.getClickedInventory().setItem(13, item);
					player.setMetadata("dronebuff", new FixedMetadataValue(Main.getMain(), "attack"));
				}
				else {
					ItemStack item = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
					List<String> lore = new ArrayList<>();
					lore.add(ChatColor.DARK_PURPLE+"Void B Gone");
					lore.add(ChatColor.GRAY+"If the player falls to the void, he");
					lore.add(ChatColor.GRAY+"will be teleported to his spawnpoint.");
					meta.setLore(lore);
					item.setItemMeta(meta);
					event.getClickedInventory().setItem(13, item);
					player.setMetadata("dronebuff", new FixedMetadataValue(Main.getMain(), "novoid"));
				}
			}
			else if(mode.equals(ChatColor.DARK_PURPLE+"Void B Gone")) {
				if(event.getWhoClicked().getMetadata("dronetier").get(0).asInt() < 3) {
					ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
					List<String> lore = new ArrayList<>();
					lore.add(ChatColor.RED+"Damage Modification");
					lore.add(ChatColor.GRAY+"Increase player damage by 50%.");
					meta.setLore(lore);
					item.setItemMeta(meta);
					event.getClickedInventory().setItem(13, item);
					player.setMetadata("dronebuff", new FixedMetadataValue(Main.getMain(), "attack"));
				}
				else {
					ItemStack item = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
					List<String> lore = new ArrayList<>();
					lore.add(ChatColor.GREEN+"Crop Vision");
					lore.add(ChatColor.GRAY+"Automaticlly harvest nearby crops which you look at.");
					meta.setLore(lore);
					item.setItemMeta(meta);
					event.getClickedInventory().setItem(13, item);
					player.setMetadata("dronebuff", new FixedMetadataValue(Main.getMain(), "gardening"));
				}
			}
			else if(mode.equals(ChatColor.GREEN+"Crop Vision")) {
				if(event.getWhoClicked().getMetadata("dronetier").get(0).asInt() < 4) {
					ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
					List<String> lore = new ArrayList<>();
					lore.add(ChatColor.RED+"Damage Modification");
					lore.add(ChatColor.GRAY+"Increase player damage by 50%.");
					meta.setLore(lore);
					item.setItemMeta(meta);
					event.getClickedInventory().setItem(13, item);
					player.setMetadata("dronebuff", new FixedMetadataValue(Main.getMain(), "attack"));
				}
				else {
					ItemStack item = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
					ItemMeta meta = item.getItemMeta();
					meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
					List<String> lore = new ArrayList<>();
					lore.add(ChatColor.BLUE+"Fetch");
					lore.add(ChatColor.GRAY+"Automaticlly fetch nearby items.");
					meta.setLore(lore);
					item.setItemMeta(meta);
					event.getClickedInventory().setItem(13, item);
					player.setMetadata("dronebuff", new FixedMetadataValue(Main.getMain(), "fetch"));
				}
			}
			else{
				ItemStack item = new ItemStack(Material.RED_STAINED_GLASS_PANE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
				List<String> lore = new ArrayList<>();
				lore.add(ChatColor.RED+"Damage Modification");
				lore.add(ChatColor.GRAY+"Increase player damage by 50%.");
				meta.setLore(lore);
				item.setItemMeta(meta);
				event.getClickedInventory().setItem(13, item);
				player.setMetadata("dronebuff", new FixedMetadataValue(Main.getMain(), "attack"));
				
			}
			
		}
		else if(event.getSlot() == 48) {
			player.closeInventory();
			player.getInventory().addItem(ItemManager.Drone[player.getMetadata("dronetier").get(0).asInt()-1]);
			Drone.removeDrone(player);
			player.removeMetadata("dronebuff", Main.getMain());
			player.removeMetadata("dronetier", Main.getMain());
		}
		if(event.getClickedInventory().getItem(event.getSlot()).getType().equals(Material.CRAFTING_TABLE)) {
			player.openWorkbench(player.getLocation(), true);
		}
		else if(event.getClickedInventory().getItem(event.getSlot()).getType().equals(Material.ENDER_CHEST)) {
			player.openInventory(player.getEnderChest());
		}
	}
	
	@EventHandler
	private void onPlayerTakeDamage(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if(!player.hasMetadata("dronebuff")) {
				return;
			}
			if(player.getMetadata("dronebuff").get(0).asString().equals("defense")) {
				event.setDamage(event.getDamage()*0.5);
			}
			if(player.getMetadata("dronebuff").get(0).asString().equals("novoid") && event.getCause().equals(DamageCause.VOID)) {
				player.teleport(player.getBedSpawnLocation());
			}
		}
	}
	@EventHandler
	private void onPlayerDealDamage(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Player) {
			Player player = (Player) event.getDamager();
			if(!player.hasMetadata("dronebuff")) {
				return;
			}
			if(player.getMetadata("dronebuff").get(0).asString().equals("attack")) {
				event.setDamage(event.getDamage()*1.5);
			}
		}
	}
	@EventHandler
	private void onDroneDeath(EntityDeathEvent event) {
		if(!(event.getEntity() instanceof ArmorStand)) {
			return;
		}
		if(event.getEntity().hasMetadata("isdrone")) {
			Player player = Bukkit.getPlayer((UUID)event.getEntity().getMetadata("owner").get(0).value());
			int tier = player.getMetadata("dronetier").get(0).asInt();
			String mode = player.getMetadata("dronebuff").get(0).asString();
			Drone.removeDrone(player);			
			new Drone(player, mode, tier);
		}
	}
}
