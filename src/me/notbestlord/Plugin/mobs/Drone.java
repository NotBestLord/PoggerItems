package me.notbestlord.Plugin.mobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.ArmorStand.LockType;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.ItemManager;



public class Drone implements Listener{
	
	private int dronetier;
	private String dronebuff;
	
	public Drone(Player player, int tier) {
		ArmorStand drone = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, 2.5, 0), EntityType.ARMOR_STAND);
		drone.setBasePlate(false);
		drone.setInvisible(true);
		drone.setInvulnerable(true);
		drone.setSilent(true);
		drone.setSmall(true);
		drone.setGravity(false);
		drone.setCustomName(ChatColor.GOLD+player.getName()+"'s Drone");
		drone.setCustomNameVisible(true);
		drone.getEquipment().setHelmet(ItemManager.Drone[tier-1]);
		drone.addEquipmentLock(EquipmentSlot.HEAD, LockType.ADDING_OR_CHANGING);
		drone.addEquipmentLock(EquipmentSlot.HEAD, LockType.REMOVING_OR_CHANGING);
		drone.addEquipmentLock(EquipmentSlot.CHEST, LockType.ADDING_OR_CHANGING);
		drone.addEquipmentLock(EquipmentSlot.CHEST, LockType.REMOVING_OR_CHANGING);
		drone.addEquipmentLock(EquipmentSlot.LEGS, LockType.ADDING_OR_CHANGING);
		drone.addEquipmentLock(EquipmentSlot.LEGS, LockType.REMOVING_OR_CHANGING);
		drone.addEquipmentLock(EquipmentSlot.FEET, LockType.ADDING_OR_CHANGING);
		drone.addEquipmentLock(EquipmentSlot.FEET, LockType.REMOVING_OR_CHANGING);
		drone.setCollidable(false);
		drone.setMetadata("isdrone", new FixedMetadataValue(Main.getMain(), true));
		drone.setMetadata("owner", new FixedMetadataValue(Main.getMain(), player.getUniqueId()));
		drone.setMetadata("mode", new FixedMetadataValue(Main.getMain(), "none"));
		player.setMetadata("dronebuff", new FixedMetadataValue(Main.getMain(), "none"));
		player.setMetadata("dronetier", new FixedMetadataValue(Main.getMain(), tier));
		//
		//Pathfinding
		int pathfinderid = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
			
			@Override
			public void run() {
				Location idle = player.getLocation();
				idle.add(0, 2.5, 0);
				if(idle.getBlockY() < 1) {
					idle.setY(1);
				}
				drone.teleport(idle);
				if(player.getMetadata("dronebuff").get(0).asString().equals("fetch")) {
					Collection<Entity> col = player.getNearbyEntities(3, 3, 3);
					Entity[] entities = new Entity[col.size()];
					col.toArray(entities);
					for(int i=0; i<entities.length; i++) {
						if(entities[i].getType().equals(EntityType.DROPPED_ITEM)) {
							Item item = (Item) entities[i];
							player.getInventory().addItem(item.getItemStack());
							entities[i].remove();
						}
					}
				}
				else if(player.getMetadata("dronebuff").get(0).asString().equals("gardening")) {
					Block block = player.getTargetBlock(null, 8);
					if(block.getBlockData() instanceof Ageable) {
						Ageable crop = (Ageable) block.getBlockData();
						if(crop.getAge() == crop.getMaximumAge()) {
							for(int i=0; i<block.getDrops().size(); i++) {
								player.getWorld().dropItem(block.getLocation(), (ItemStack) block.getDrops().toArray()[i]);
							}
							crop.setAge(0);
							block.setBlockData(crop);
						}
					}
				}
			}
		}, 0L, 1L);
		drone.setMetadata("pathfindingid", new FixedMetadataValue(Main.getMain(), pathfinderid));
		drone.setMetadata("Inventory", new FixedMetadataValue(Main.getMain(), createDroneInventory("none", tier)));
		Main.playerDrones.put(player.getUniqueId(), drone);
	}
	
	public Drone(Player player, String mode, int tier) {
		ArmorStand drone = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, 2.5, 0), EntityType.ARMOR_STAND);
		drone.setBasePlate(false);
		drone.setInvisible(true);
		drone.setInvulnerable(true);
		drone.setSilent(true);
		drone.setSmall(true);
		drone.setGravity(false);
		drone.setCustomName(ChatColor.GOLD+player.getName()+"'s Drone");
		drone.setCustomNameVisible(true);
		drone.getEquipment().setHelmet(ItemManager.Drone[tier-1]);
		drone.addEquipmentLock(EquipmentSlot.HEAD, LockType.ADDING_OR_CHANGING);
		drone.addEquipmentLock(EquipmentSlot.HEAD, LockType.REMOVING_OR_CHANGING);
		drone.addEquipmentLock(EquipmentSlot.CHEST, LockType.ADDING_OR_CHANGING);
		drone.addEquipmentLock(EquipmentSlot.CHEST, LockType.REMOVING_OR_CHANGING);
		drone.addEquipmentLock(EquipmentSlot.LEGS, LockType.ADDING_OR_CHANGING);
		drone.addEquipmentLock(EquipmentSlot.LEGS, LockType.REMOVING_OR_CHANGING);
		drone.addEquipmentLock(EquipmentSlot.FEET, LockType.ADDING_OR_CHANGING);
		drone.addEquipmentLock(EquipmentSlot.FEET, LockType.REMOVING_OR_CHANGING);
		drone.setCollidable(false);
		drone.setMetadata("isdrone", new FixedMetadataValue(Main.getMain(), true));
		drone.setMetadata("owner", new FixedMetadataValue(Main.getMain(), player.getUniqueId()));
		drone.setMetadata("mode", new FixedMetadataValue(Main.getMain(), mode));
		player.setMetadata("dronebuff", new FixedMetadataValue(Main.getMain(), mode));
		player.setMetadata("dronetier", new FixedMetadataValue(Main.getMain(), tier));
		//
		int pathfinderid = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getMain(), new Runnable() {
			
			@Override
			public void run() {
				Location idle = player.getLocation();
				idle.add(0, 2.5, 0);
				drone.teleport(idle);
				if(player.getMetadata("dronebuff").get(0).asString().equals("fetch")) {
					Collection<Entity> col = player.getWorld().getNearbyEntities(player.getLocation(), 3, 3, 3);
					Entity[] entities = new Entity[col.size()];
					col.toArray(entities);
					for(int i=0; i<entities.length; i++) {
						if(entities[i].getType().equals(EntityType.DROPPED_ITEM)) {
							Item item = (Item) entities[i];
							player.getInventory().addItem(item.getItemStack());
							entities[i].remove();
						}
					}
				}
				else if(player.getMetadata("dronebuff").get(0).asString().equals("gardening")) {
					Block block = player.getTargetBlock(null, 100);
					if(block instanceof Ageable) {
						Ageable crop = (Ageable) block;
						for(int i=0; i<block.getDrops().size(); i++) {
							player.getWorld().dropItem(block.getLocation(), (ItemStack) block.getDrops().toArray()[i]);
						}
						crop.setAge(0);
						
					}
				}
				
			}
		}, 0L, 1L);
		drone.setMetadata("pathfindingid", new FixedMetadataValue(Main.getMain(), pathfinderid));
		drone.setMetadata("Inventory", new FixedMetadataValue(Main.getMain(), createDroneInventory(mode, tier)));
		Main.playerDrones.put(player.getUniqueId(), drone);
	}
	
	public Drone(int tier, String buff) {
		this.dronetier = tier;
		this.dronebuff = buff;
	}
	
	public int getTier(){
		return this.dronetier;
	}
	
	public String getBuff(){
		return this.dronebuff;
	}
	
	public static void removeDrone(Player player) {
		ArmorStand drone = (ArmorStand) Main.playerDrones.get(player.getUniqueId());
		Bukkit.getScheduler().cancelTask(drone.getMetadata("pathfindingid").get(0).asInt());
		drone.remove();
		player.setMetadata("dronebuff", new FixedMetadataValue(Main.getMain(), "removed"));
		player.removeMetadata("dronetier", Main.getMain());
	}
	
	public static void saveDrone(Player player) throws FileNotFoundException, IOException {
		if(player.hasMetadata("dronebuff")) {
			File file = new File("PluginData/"+player.getUniqueId()+"-droneData.json");
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			Drone drone = new Drone(player.getMetadata("dronetier").get(0).asInt(), player.getMetadata("dronebuff").get(0).asString());
			
			try {
				FileWriter writer = new FileWriter(file);
				writer.write(gson.toJson(drone));
				writer.flush();
				writer.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void loadDrone(Player player) throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File("PluginData/"+player.getUniqueId()+"-droneData.json");
		if(file!=null) {
			
			Gson gson = new Gson();
			Drone readObject = gson.fromJson(new FileReader(file), Drone.class);
			
			if(!(readObject instanceof Drone)) {
				throw new IOException("Data is not a drone");
			}
			Drone drone = (Drone)readObject;
			new Drone(player, drone.getBuff(), drone.getTier());
		}
	}
	
	private Inventory createDroneInventory(String mode, int tier) {
		Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GOLD+"Drone");
		
		ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = item1.getItemMeta();
		meta.setDisplayName(" ");
		item1.setItemMeta(meta);
		
		for(int i=0; i<inv.getSize(); i++) {
			inv.setItem(i, item1);
		}
		
		item1.setType(Material.BARRIER);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.RED+"Close");
		item1.setItemMeta(meta);
		inv.setItem(49, item1);
		
		item1.setType(Material.LIME_STAINED_GLASS_PANE);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Change Drone Mode");
		item1.setItemMeta(meta);
		inv.setItem(22, item1);
		
		item1.setType(Material.STONE_BUTTON);
		meta = item1.getItemMeta();
		meta.setDisplayName(ChatColor.WHITE+"Turn drone into item.");
		item1.setItemMeta(meta);
		inv.setItem(48, item1);
		List<String> lore = new ArrayList<>();
		switch (mode) {
		case "none":
			item1.setType(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
			meta = item1.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
			lore.add(ChatColor.BLUE+"none");
			meta.setLore(lore);
			item1.setItemMeta(meta);
			inv.setItem(13, item1);
			break;
		case "attack":
			item1 = new ItemStack(Material.RED_STAINED_GLASS_PANE);
			meta = item1.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
			lore.add(ChatColor.RED+"Damage Modification");
			lore.add(ChatColor.GRAY+"Increase player damage by 50%.");
			meta.setLore(lore);
			item1.setItemMeta(meta);
			inv.setItem(13, item1);
			break;
		case "defense":
			item1 = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
			meta = item1.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
			lore.add(ChatColor.YELLOW+"Armor Enhancment");
			lore.add(ChatColor.GRAY+"Reduce damage taken by player by 50%.");
			meta.setLore(lore);
			item1.setItemMeta(meta);
			inv.setItem(13, item1);
			break;
		case "novoid":
			item1 = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
			meta = item1.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
			lore.add(ChatColor.DARK_PURPLE+"Void B Gone");
			lore.add(ChatColor.GRAY+"If the player falls to the void, he");
			lore.add(ChatColor.GRAY+"will be teleported to his spawnpoint.");
			meta.setLore(lore);
			item1.setItemMeta(meta);
			inv.setItem(13, item1);
			break;
		case "gardening":
			item1 = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
			meta = item1.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
			lore.add(ChatColor.GREEN+"Crop Vision");
			lore.add(ChatColor.GRAY+"Automaticlly harvest nearby crops which you look at.");
			meta.setLore(lore);
			item1.setItemMeta(meta);
			inv.setItem(13, item1);
			break;
		case "fetch":
			item1 = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
			meta = item1.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
			lore.add(ChatColor.BLUE+"Fetch");
			lore.add(ChatColor.GRAY+"Automaticlly fetch nearby items.");
			meta.setLore(lore);
			item1.setItemMeta(meta);
			inv.setItem(13, item1);
		default:
			item1.setType(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
			meta = item1.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE+"Current Drone Mode:");
			lore.add(ChatColor.BLUE+"none");
			meta.setLore(lore);
			item1.setItemMeta(meta);
			inv.setItem(13, item1);
			break;
		}
		if(tier == 5) {
			item1 = new ItemStack(Material.CRAFTING_TABLE);
			meta = item1.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE+"Crafting Table");
			item1.setItemMeta(meta);
			inv.setItem(17, item1);
			//
			item1 = new ItemStack(Material.ENDER_CHEST);
			meta = item1.getItemMeta();
			meta.setDisplayName(ChatColor.WHITE+"Ender Chest");
			item1.setItemMeta(meta);
			inv.setItem(26, item1);
		}
		
		return inv;
	}
	
}
