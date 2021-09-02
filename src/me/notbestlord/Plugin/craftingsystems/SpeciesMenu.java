package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.notbestlord.Plugin.Main;
import net.md_5.bungee.api.ChatColor;

public class SpeciesMenu implements Listener{
	
	
	public static Inventory openMenu(Player player) {
		Inventory inv = Bukkit.createInventory(null, 27, ChatColor.AQUA+"Species Menu");
		
		ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = item1.getItemMeta();
		meta.setDisplayName(" ");
		item1.setItemMeta(meta);
		
		for(int i=0; i<27; i++) {
			inv.setItem(i, item1);
		}
		
		item1 = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Human");
		List<String> lore = new ArrayList<>();
		lore.add(ChatColor.GRAY+"An ordinary huaman.");
		lore.add(ChatColor.GRAY+" - Diet: Omnivore (Normal diet)");
		lore.add(ChatColor.GRAY+" - Human: You have no special abilitys.");
		meta.setLore(lore);
		lore.clear();
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item1.setItemMeta(meta);
		inv.setItem(4, item1);
		//
		item1 = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Florian");
		lore.add(ChatColor.GRAY+"A plant who gained a humanoid form after");
		lore.add(ChatColor.GRAY+"an expirement done by an evil organization.");
		lore.add(ChatColor.GRAY+" - Diet: Herbivore (Plant diet)");
		lore.add(ChatColor.GRAY+" - Photosynthesis - Due to being a plant,");
		lore.add(ChatColor.GRAY+"   You have double maximum health during daytime,");
		lore.add(ChatColor.GRAY+"   And half maximum health during nighttime.");
		lore.add(ChatColor.GRAY+" - Glucose intake - You can consume sugar for");
		lore.add(ChatColor.GRAY+"   a temporary buff.");
		meta.setLore(lore);
		lore.clear();
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item1.setItemMeta(meta);
		inv.setItem(9, item1);
		//
		item1 = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Ape");
		lore.add(ChatColor.GRAY+"An avarage");
		lore.add(ChatColor.GRAY+" - Diet: Herbivore (Plant diet)");
		lore.add(ChatColor.GRAY+" - Gorilla Strength - Apes are strong,");
		lore.add(ChatColor.GRAY+"   +10 Base Attack Damage");
		lore.add(ChatColor.GRAY+" - Apes together STRONG - When attacking,");
		lore.add(ChatColor.GRAY+"   Perform a second attack, whose damage is");
		lore.add(ChatColor.GRAY+"   5 x Amount of apes in 100 block radius.");
		meta.setLore(lore);
		lore.clear();
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item1.setItemMeta(meta);
		inv.setItem(10, item1);
		//
		item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Automaton");
		lore.add(ChatColor.GRAY+"A machine was brought to life using souls,");
		lore.add(ChatColor.GRAY+"With time it adapted a humanoid form.");
		lore.add(ChatColor.GRAY+" - Diet: Robotic (Metallic diet)");
		lore.add(ChatColor.GRAY+" - Absolute Unit - Metal is tough,");
		lore.add(ChatColor.GRAY+"   +8 Base Armor");
		lore.add(ChatColor.GRAY+"   +4 Base Armor Toughness");
		lore.add(ChatColor.GRAY+"   +2 Base Attack Damage");
		lore.add(ChatColor.GRAY+" - ");
		meta.setLore(lore);
		lore.clear();
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item1.setItemMeta(meta);
		inv.setItem(11, item1);
		//
		item1 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Arachnid");
		lore.add(ChatColor.GRAY+"A descendent of ancient spiders,");
		lore.add(ChatColor.GRAY+"Evolved to be more humanoid.");
		lore.add(ChatColor.GRAY+" - Diet: Carnivore (Meat diet)");
		lore.add(ChatColor.GRAY+" - Web Weaver - When attacking an enemy");
		lore.add(ChatColor.GRAY+"   create a temporary cobweb slowing them down.");
		lore.add(ChatColor.GRAY+" - Spiderlings - When attacking an enemy");
		lore.add(ChatColor.GRAY+"   there is a 5% of creating a temporary cave spider");
		lore.add(ChatColor.GRAY+"   that will help you.");
		meta.setLore(lore);
		lore.clear();
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item1.setItemMeta(meta);
		inv.setItem(12, item1);
		//
		item1 = new ItemStack(Material.ORANGE_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Avian");
		lore.add(ChatColor.GRAY+"Birds which adopted human forms for");
		lore.add(ChatColor.GRAY+"convenience a couple millennias ago.");
		lore.add(ChatColor.GRAY+" - Diet: Carnivore (Meat diet)");
		lore.add(ChatColor.GRAY+" - Slow Fall - Your wings allow you to");
		lore.add(ChatColor.GRAY+"   fall slowly ,Premenant slow falling.");
		lore.add(ChatColor.GRAY+" - Natural Pray - Cousming fish is in your");
		lore.add(ChatColor.GRAY+"   blood, Eating one grants +8 Staturation.");
		meta.setLore(lore);
		lore.clear();
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item1.setItemMeta(meta);
		inv.setItem(13, item1);
		//
		item1 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Pigman");
		lore.add(ChatColor.GRAY+"A lost ancestor of modern day piglins.");
		lore.add(ChatColor.GRAY+" - Diet: Carnivore (Meat diet)");
		lore.add(ChatColor.GRAY+" - Shared Blood - You and the piglins share the ");
		lore.add(ChatColor.GRAY+"   same blood, Piglins are peaceful to you.");
		lore.add(ChatColor.GRAY+" - Golden Tooth - You love gold so much you want");
		lore.add(ChatColor.GRAY+"   to eat it, Consuming the Gold Ball food item");
		lore.add(ChatColor.GRAY+"   grants you a temporary buff.");
		meta.setLore(lore);
		lore.clear();
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item1.setItemMeta(meta);
		inv.setItem(14, item1);
		//
		item1 = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Lunarian");
		lore.add(ChatColor.GRAY+"Aliens.");
		lore.add(ChatColor.GRAY+" - Diet: Omnivore (Normal diet)");
		lore.add(ChatColor.GRAY+" - Lunar Flare - You are highly allergic to sunlight,");
		lore.add(ChatColor.GRAY+"   Wearing no armor in direct sunlight burns you.");
		lore.add(ChatColor.GRAY+" - Sun Stats - Exposure to the yellow sun made you stronger,");
		lore.add(ChatColor.GRAY+"   + 4 Base Attack Damge");
		lore.add(ChatColor.GRAY+"   + 4 Base Armor");
		meta.setLore(lore);
		lore.clear();
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item1.setItemMeta(meta);
		inv.setItem(15, item1);
		//
		item1 = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Gas Man");
		lore.add(ChatColor.GRAY+"A cloud of unknown gas fused with souls.");
		lore.add(ChatColor.GRAY+" - Diet: Omnivore (Normal diet)");
		lore.add(ChatColor.GRAY+" - Flight - You can fly.");
		lore.add(ChatColor.GRAY+" - Weak Connectivity - The gas your body");
		lore.add(ChatColor.GRAY+"   is made of is weakly connected,");
		lore.add(ChatColor.GRAY+"   -8 Base Maximum Health.");
		meta.setLore(lore);
		lore.clear();
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item1.setItemMeta(meta);
		inv.setItem(16, item1);
		//
		item1 = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Hylotel");
		lore.add(ChatColor.GRAY+"Merfolk, live on both land and sea.");
		lore.add(ChatColor.GRAY+" - Diet: Carnivore (Meat diet)");
		lore.add(ChatColor.GRAY+" - Gills - You have gills, Breath in water.");
		lore.add(ChatColor.GRAY+" - Cannibalism - You love eating your own kind,");
		lore.add(ChatColor.GRAY+"   Eating fish +8 Saturation.");
		meta.setLore(lore);
		lore.clear();
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item1.setItemMeta(meta);
		inv.setItem(17, item1);
		//
		item1 = new ItemStack(Material.BARRIER);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Close");
		item1.setItemMeta(meta);
		inv.setItem(22, item1);
		
		if(player != null) {
			player.openInventory(inv);
			return null;
		}else {
			return inv;
		}
	}
	
	@EventHandler
	private void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(!event.getView().getTitle().equals(ChatColor.AQUA+"Species Menu") || event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
			return;
		}
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		if(event.getSlot() == 22) {
			player.closeInventory();
		}
		else if(event.getSlot() == 4) {
			player.setMetadata("Species", new FixedMetadataValue(Main.getMain(), "Human"));
			player.closeInventory();
			player.sendMessage(ChatColor.WHITE+"You are now a Human.");
			if(player.hasMetadata("SpeciesRunnable")) {
				Bukkit.getScheduler().cancelTask(player.getMetadata("SpeciesRunnable").get(0).asInt());
				player.removeMetadata("SpeciesRunnable", Main.getMain());
			}
			return;
		}
		else if(event.getSlot() == 9) {
			player.setMetadata("Species", new FixedMetadataValue(Main.getMain(), "Florian"));
			player.closeInventory();
			player.sendMessage(ChatColor.GREEN+"You are now a Florian.");
			if(player.hasMetadata("SpeciesRunnable")) {
				Bukkit.getScheduler().cancelTask(player.getMetadata("SpeciesRunnable").get(0).asInt());
				player.removeMetadata("SpeciesRunnable", Main.getMain());
			}
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
		else if(event.getSlot() == 10) {
			player.setMetadata("Species", new FixedMetadataValue(Main.getMain(), "Ape"));
			player.closeInventory();
			player.sendMessage(ChatColor.LIGHT_PURPLE+"You are now an Ape.");
			player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(14);
			if(player.hasMetadata("SpeciesRunnable")) {
				Bukkit.getScheduler().cancelTask(player.getMetadata("SpeciesRunnable").get(0).asInt());
				player.removeMetadata("SpeciesRunnable", Main.getMain());
			}
			return;
		}
		else if(event.getSlot() == 11) {
			player.setMetadata("Species", new FixedMetadataValue(Main.getMain(), "Automaton"));
			player.closeInventory();
			player.sendMessage(ChatColor.WHITE+"You are now an Automaton.");
			player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(8);
			player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(4);
			player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(6);
			if(player.hasMetadata("SpeciesRunnable")) {
				Bukkit.getScheduler().cancelTask(player.getMetadata("SpeciesRunnable").get(0).asInt());
				player.removeMetadata("SpeciesRunnable", Main.getMain());
			}
			return;
		}
		else if(event.getSlot() == 12) {
			player.setMetadata("Species", new FixedMetadataValue(Main.getMain(), "Arachnid"));
			player.closeInventory();
			player.sendMessage(ChatColor.RED+"You are now an Arachnid.");
			if(player.hasMetadata("SpeciesRunnable")) {
				Bukkit.getScheduler().cancelTask(player.getMetadata("SpeciesRunnable").get(0).asInt());
				player.removeMetadata("SpeciesRunnable", Main.getMain());
			}
			return;
		}
		else if(event.getSlot() == 13) {
			player.setMetadata("Species", new FixedMetadataValue(Main.getMain(), "Avian"));
			player.closeInventory();
			player.sendMessage(ChatColor.GOLD+"You are now an Avian.");
			if(player.hasMetadata("SpeciesRunnable")) {
				Bukkit.getScheduler().cancelTask(player.getMetadata("SpeciesRunnable").get(0).asInt());
				player.removeMetadata("SpeciesRunnable", Main.getMain());
			}
			player.setMetadata("SpeciesRunnable", new FixedMetadataValue(Main.getMain(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
				
				@Override
				public void run() {
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 2, 1));
				}
			}, 0, 1)));
			return;
		}
		else if(event.getSlot() == 14) {
			player.setMetadata("Species", new FixedMetadataValue(Main.getMain(), "Pigman"));
			player.closeInventory();
			player.sendMessage(ChatColor.YELLOW+"You are now a Pigman.");
			if(player.hasMetadata("SpeciesRunnable")) {
				Bukkit.getScheduler().cancelTask(player.getMetadata("SpeciesRunnable").get(0).asInt());
				player.removeMetadata("SpeciesRunnable", Main.getMain());
			}
			return;
		}
		else if(event.getSlot() == 15) {
			player.setMetadata("Species", new FixedMetadataValue(Main.getMain(), "Lunarian"));
			player.closeInventory();
			player.sendMessage(ChatColor.GRAY+"You are now a Lunarian.");
			player.getAttribute(Attribute.GENERIC_ARMOR).setBaseValue(4);
			player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(8);
			if(player.hasMetadata("SpeciesRunnable")) {
				Bukkit.getScheduler().cancelTask(player.getMetadata("SpeciesRunnable").get(0).asInt());
				player.removeMetadata("SpeciesRunnable", Main.getMain());
			}
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
		else if(event.getSlot() == 16) {
			player.setMetadata("Species", new FixedMetadataValue(Main.getMain(), "Gasumian"));
			player.closeInventory();
			player.sendMessage(ChatColor.DARK_PURPLE+"You are now a Gasumian.");
			player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(12);	
			
			if(player.hasMetadata("SpeciesRunnable")) {
				Bukkit.getScheduler().cancelTask(player.getMetadata("SpeciesRunnable").get(0).asInt());
				player.removeMetadata("SpeciesRunnable", Main.getMain());
			}
			player.setMetadata("SpeciesRunnable", new FixedMetadataValue(Main.getMain(), Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
				
				@Override
				public void run() {
					player.setAllowFlight(true);
				}
			}, 0, 1)));
			return;
		}
		else if(event.getSlot() == 17) {
			player.setMetadata("Species", new FixedMetadataValue(Main.getMain(), "Hylotel"));
			player.closeInventory();
			player.sendMessage(ChatColor.BLUE+"You are now a Hylotel.");
			if(player.hasMetadata("SpeciesRunnable")) {
				Bukkit.getScheduler().cancelTask(player.getMetadata("SpeciesRunnable").get(0).asInt());
				player.removeMetadata("SpeciesRunnable", Main.getMain());
			}
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
