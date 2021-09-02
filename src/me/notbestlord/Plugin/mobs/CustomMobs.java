package me.notbestlord.Plugin.mobs;

import java.util.Collection;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.Lootable;
import org.bukkit.metadata.FixedMetadataValue;

import me.notbestlord.Plugin.Main;
import net.minecraft.world.level.storage.loot.LootPredicateManager;
import net.minecraft.world.level.storage.loot.LootTables;

public class CustomMobs implements Listener{
	
	@EventHandler
	private void onEntitySpawn(EntitySpawnEvent event) {
		if(event.getEntity() instanceof Zombie) {
			Random r = new Random();
            float chance = r.nextFloat();
            if (chance <= 0.002f) // chance of 0.02%
            {
            	Zombie zombie = (Zombie) event.getEntity();
    			zombie.setCustomName(ChatColor.RED+"Zombie Commander");
    			zombie.setMetadata("CustomMob", new FixedMetadataValue(Main.getMain(), "Zombie Commander"));
    			zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);;
    			ItemStack item = new ItemStack(Material.LEATHER_BOOTS);
    			LeatherArmorMeta meta = (LeatherArmorMeta) item.getItemMeta();
    			meta.setColor(Color.RED);
    			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
    			item.setItemMeta(meta);
    			zombie.getEquipment().setBoots(item);
    			zombie.getEquipment().setBootsDropChance(0);
    			item = new ItemStack(Material.LEATHER_LEGGINGS);
    			meta = (LeatherArmorMeta) item.getItemMeta();
    			meta.setColor(Color.RED);
    			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
    			item.setItemMeta(meta);
    			zombie.getEquipment().setLeggings(item);
    			zombie.getEquipment().setLeggingsDropChance(0);
    			item = new ItemStack(Material.LEATHER_CHESTPLATE);
    			meta = (LeatherArmorMeta) item.getItemMeta();
    			meta.setColor(Color.RED);
    			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
    			item.setItemMeta(meta);
    			zombie.getEquipment().setChestplate(item);
    			zombie.getEquipment().setChestplateDropChance(0);
    			item = new ItemStack(Material.LEATHER_HELMET);
    			meta = (LeatherArmorMeta) item.getItemMeta();
    			meta.setColor(Color.RED);
    			meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
    			item.setItemMeta(meta);
    			zombie.getEquipment().setHelmet(item);
    			zombie.getEquipment().setHelmetDropChance(0);
    			item = new ItemStack(Material.DIAMOND_SWORD);
    			ItemMeta nMeta = item.getItemMeta();
    			nMeta.addEnchant(Enchantment.DAMAGE_ALL, 2, false);
    			item.setItemMeta(nMeta);
    			zombie.getEquipment().setItemInMainHand(item);
    			zombie.getEquipment().setItemInOffHand(item);
    			zombie.getEquipment().setItemInMainHandDropChance(0);
    			zombie.getEquipment().setItemInOffHandDropChance(0);
            }
			
		}
	}

}
