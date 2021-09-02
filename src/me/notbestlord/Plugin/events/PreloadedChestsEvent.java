package me.notbestlord.Plugin.events;

import java.util.Collection;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.LootTable;
import org.bukkit.loot.LootTables;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;

public class PreloadedChestsEvent implements Listener{
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		Block block = event.getBlock();
		if (block.getType() == Material.CHEST && event.getItemInHand().hasItemMeta()) {
			if(event.getItemInHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING)) {
				Chest chest = (Chest) block.getState();
				if(event.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING).equals("End City")) {
					Collection<ItemStack> collection = LootTables.END_CITY_TREASURE.getLootTable().populateLoot(new Random(), new LootContext.Builder(block.getLocation()).build());
					ItemStack[] array = new ItemStack[collection.size()];
					collection.toArray(array);
					chest.getInventory().setContents(array);
				}
				else if(event.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING).equals("Nether Fortress")) {
					Collection<ItemStack> collection = LootTables.NETHER_BRIDGE.getLootTable().populateLoot(new Random(), new LootContext.Builder(block.getLocation()).build());
					ItemStack[] array = new ItemStack[collection.size()];
					collection.toArray(array);
					chest.getInventory().setContents(array);
				}
				else if(event.getItemInHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "customLootTable"), PersistentDataType.STRING).equals("Bastion")) {
					Random r = new Random();
					int random = r.nextInt(3);
					Collection<ItemStack> collection = LootTables.BASTION_BRIDGE.getLootTable().populateLoot(new Random(), new LootContext.Builder(block.getLocation()).build());
					switch (random) {
					case 0:
						collection = LootTables.BASTION_HOGLIN_STABLE.getLootTable().populateLoot(new Random(), new LootContext.Builder(block.getLocation()).build());
						break;
					case 1:
						collection = LootTables.BASTION_OTHER.getLootTable().populateLoot(new Random(), new LootContext.Builder(block.getLocation()).build());
						break;
					case 2:
						collection = LootTables.BASTION_TREASURE.getLootTable().populateLoot(new Random(), new LootContext.Builder(block.getLocation()).build());
						break;
					default:
						break;
					}
					ItemStack[] array = new ItemStack[collection.size()];
					collection.toArray(array);
					chest.getInventory().setContents(array);
				}
			}
		}
	}

}
