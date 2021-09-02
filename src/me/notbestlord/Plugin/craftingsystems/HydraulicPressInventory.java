package me.notbestlord.Plugin.craftingsystems;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.notbestlord.Plugin.Main;
import net.md_5.bungee.api.ChatColor;

public class HydraulicPressInventory {
	
	public static Inventory inventory;
	public static Dictionary<UUID, Inventory> inventoryaccess = new Hashtable<>();
	
	public static void init(Player player) {
		createInventory(player);
	}
	
	
	public static void createInventory(Player player){
		inventory = Bukkit.createInventory(null, 27, "§3Hyderaulic Press");
		
		ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta meta = item1.getItemMeta();
		meta.setDisplayName(" ");
		item1.setItemMeta(meta);
		ItemStack piston = new ItemStack(Material.PLAYER_HEAD, 1);
		ItemStack piston2 = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta pmeta = (SkullMeta) piston.getItemMeta();
		SkullMeta pmeta2 = (SkullMeta) piston2.getItemMeta();
		pmeta.setDisplayName("§9Enter Items to press");
		pmeta2.setDisplayName("§9Enter Items to press");
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
		profile.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGQ1Y2Y5MmJjNzllYzE5ZjQxMDY0NDFhZmZmZjE0MDZhMTM2NzAxMGRjYWZiMTk3ZGQ5NGNmY2ExYTZkZTBmYyJ9fX0"));
		Field profileField;
		try {
			profileField = pmeta.getClass().getDeclaredField("profile");
			profileField.setAccessible(true);
			profileField.set(pmeta, profile);
		}catch (Exception e) {
			
		}
		piston.setItemMeta(pmeta);
		//Blanks
		inventory.setItem(0, item1);
		inventory.setItem(1, piston);
		inventory.setItem(2, item1);
		inventory.setItem(3, item1);
		inventory.setItem(5, item1);
		inventory.setItem(6, item1);
		inventory.setItem(7, item1);
		inventory.setItem(8, item1);
		inventory.setItem(9, item1);
		inventory.setItem(11, item1);
		inventory.setItem(12, item1);
		inventory.setItem(14, item1);
		inventory.setItem(16, item1);
		inventory.setItem(18, item1);
		GameProfile profile2 = new GameProfile(UUID.randomUUID(), null);
		Field profileField2;
		profile2.getProperties().put("textures", new Property("textures", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjlhOWQ4OWE0ZDAyNTMzZDg2NGFjNGEyZDVkOWY2ZGIzOWM2ZjNmNDdhMjk1NDUzNTc2NTI5MjRmYmZkNDA4OCJ9fX0"));
		try {
			profileField2 = pmeta2.getClass().getDeclaredField("profile");
			profileField2.setAccessible(true);
			profileField2.set(pmeta2, profile2);
		}catch (Exception e) {
			
		}
		piston2.setItemMeta(pmeta2);
		inventory.setItem(19, piston2);
		inventory.setItem(20, item1);
		inventory.setItem(21, item1);
		inventory.setItem(23, item1);
		inventory.setItem(24, item1);
		inventory.setItem(25, item1);
		inventory.setItem(26, item1);
		
		item1.setType(Material.WHITE_STAINED_GLASS_PANE);
		inventory.setItem(4, item1);
		inventory.setItem(22, item1);
		item1.setType(Material.LIME_STAINED_GLASS_PANE);
		meta.setDisplayName(ChatColor.WHITE+"Use Hydraulic Press");
		item1.setItemMeta(meta);
		inventory.setItem(13, item1);
		item1.setType(Material.BARRIER);
		meta.setDisplayName(ChatColor.RED+"Close");
		item1.setItemMeta(meta);
		inventory.setItem(17, item1);
		inventoryaccess.put(player.getUniqueId(), inventory);
	}
	
	public static void BlankSlot(int slot) {
		ItemStack Blank = new ItemStack(Material.LIGHT_GRAY_STAINED_GLASS_PANE);
		ItemMeta BlankMeta = Blank.getItemMeta();
		BlankMeta.setDisplayName(" ");
		Blank.setItemMeta(BlankMeta);
		inventory.setItem(slot, Blank);
	}
	
	
}
