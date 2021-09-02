package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.dataManagment.Machine;
import me.notbestlord.Plugin.items.ItemManager;
import net.md_5.bungee.api.ChatColor;

public class WeaponryEvents implements Listener{
	
	@EventHandler
	private void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock() == null) {
				return;
			}
			if(event.getClickedBlock().getType().equals(Material.SMITHING_TABLE)) {
				Location BottomBlockLocation = event.getClickedBlock().getLocation();
				Location TopBlockLocation = event.getClickedBlock().getLocation();
				BottomBlockLocation.subtract(0, 1, 0);
				TopBlockLocation.add(0, 1, 0);
				if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.BEACON)) {
					if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
						event.setCancelled(true);
						if(!(new Machine(event.getClickedBlock().getLocation(), "Weaponry").MachineInArray(Main.MachineList))) {
							if (event.getPlayer().getInventory().getItemInMainHand().equals(ItemManager.WeaponryCatalyst)){
								WeaponryInventory.createMainInventory(event.getClickedBlock().getLocation(), 0);
								Machine machine = new Machine(event.getClickedBlock().getLocation(), WeaponryInventory.inventory.get(event.getClickedBlock().getLocation()), "Weaponry");
								Main.MachineList.add(machine);
								event.getPlayer().openInventory(WeaponryInventory.inventory.get(event.getClickedBlock().getLocation()));
								event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
							}
						}
						else {
							event.getPlayer().openInventory(WeaponryInventory.inventory.get(event.getClickedBlock().getLocation()));
						}
					}
				}
			}
		}
	}
	@EventHandler
	private void onBreakBlock(BlockBreakEvent event) {
		if(event.getBlock().getType().equals(Material.SMITHING_TABLE)) {
			Location BottomBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			BottomBlockLocation.subtract(0, 1, 0);
			TopBlockLocation.add(0, 1, 0);
			if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.BEACON)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
					if(WeaponryInventory.inventory.containsKey(event.getBlock().getLocation())) {
						WeaponryInventory.inventory.remove(event.getBlock().getLocation());
						Machine.DeleteMachineFromList(new Machine(event.getBlock().getLocation(), "Weaponry"));
						event.getPlayer().getInventory().addItem(ItemManager.WeaponryCatalyst);
					}
				}
			}
		}
		if(event.getBlock().getType().equals(Material.BEACON)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.add(0, 1, 0);
			TopBlockLocation.add(0, 2, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.SMITHING_TABLE)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
					if(WeaponryInventory.inventory.containsKey(BaseBlockLocation)) {
						WeaponryInventory.inventory.remove(BaseBlockLocation);
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "Weaponry"));
						event.getPlayer().getInventory().addItem(ItemManager.WeaponryCatalyst);
					}
				}
			}
		}
		if(event.getBlock().getType().equals(Material.IRON_TRAPDOOR)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location BottomBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.subtract(0, 1, 0);
			BottomBlockLocation.subtract(0, 2, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.SMITHING_TABLE)) {
				if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.BEACON)) {
					if(WeaponryInventory.inventory.containsKey(BaseBlockLocation)) {
						WeaponryInventory.inventory.remove(BaseBlockLocation);
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "Weaponry"));
						event.getPlayer().getInventory().addItem(ItemManager.WeaponryCatalyst);
					}
				}
			}
		}
	}
	@EventHandler
	private void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(!event.getView().getTitle().equals(ChatColor.AQUA+"Weaponry") || event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
			return;
		}
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		if(event.getSlot() == 49) {
			player.closeInventory();
		}
		if(event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
			String id = event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING);
			player.openInventory(WeaponryInventory.createSubinventory(player, id));
		}
		else if(event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)) {
			String id = event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING);
			player.openInventory(WeaponryInventory.createSubinventory(player, id));
		}
		else {
			
		}
	}
	@EventHandler
	private void onSubClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(!event.getView().getTitle().equals(ChatColor.AQUA+"weaponry") || event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
			return;
		}
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		if(event.getSlot() == 49) {
			player.closeInventory();
		}
		if(event.getSlot() == 31) {
			if(event.getClickedInventory().getItem(31).getType() != Material.LIME_STAINED_GLASS_PANE) {
				player.sendMessage(ChatColor.RED+"You Do Not Have The Required Materials.");
				return;
			}
			String recipeID = event.getClickedInventory().getItem(0).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "RecipeID"), PersistentDataType.STRING);
			ArrayList recipe = new ArrayList<>(WeaponryInventory.RecipeList.get(recipeID));
			ItemStack output = (ItemStack) recipe.get(recipe.size()-1);
			recipe.remove(recipe.size()-1);
			for(int i=0; i<recipe.size(); i+=2) {
				ItemStack currentItem = (ItemStack)recipe.get(i);
				int currentItemAmount = (int)recipe.get(i+1);
				if(currentItem.hasItemMeta()) {
					if(currentItem.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
						String id = currentItem.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING);
						for(int j=0; j<player.getInventory().getSize(); j++) {
							ItemStack a = player.getInventory().getItem(j);
							if(a != null) {
								if(a.hasItemMeta()) {
									if(a.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
										if(a.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals(id)) {
											if(a.getAmount() >= currentItemAmount) {
												a.setAmount(a.getAmount()-currentItemAmount);
												break;
											}
											else {
												currentItemAmount-=a.getAmount();
												a.setAmount(0);
											}
										}
									}
								}
							}
						}
					}
					else if(currentItem.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)) {
						String id = currentItem.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING);
						for(int j=0; j<player.getInventory().getSize(); j++) {
							ItemStack a = player.getInventory().getItem(j);
							if(a != null) {
								if(a.hasItemMeta()) {
									if(a.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)) {
										if(a.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING).equals(id)) {
											if(a.getAmount() >= currentItemAmount) {
												a.setAmount(a.getAmount()-currentItemAmount);
												break;
											}
											else {
												currentItemAmount-=a.getAmount();
												a.setAmount(0);
											}
											
										}
									}
								}
							}
						}
					}
					else {
						for(int j=0; j<player.getInventory().getSize(); j++) {
							ItemStack a = player.getInventory().getItem(j);
							if(a != null) {
								if(a.hasItemMeta()) {
									if(!a.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING) && !a.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)){
										if(a.getType() == currentItem.getType()) {
											if(a.getAmount() >= currentItemAmount) {
												a.setAmount(a.getAmount()-currentItemAmount);
												break;
											}
											else {
												currentItemAmount-=a.getAmount();
												a.setAmount(0);
											}
										}
									}
								}
								else if(a.getType() == currentItem.getType()) {
									if(a.getAmount() >= currentItemAmount) {
										a.setAmount(a.getAmount()-currentItemAmount);
										break;
									}
									else {
										currentItemAmount-=a.getAmount();
										a.setAmount(0);
									}
								}
							}
						}
					}
				}
				else {
					for(int j=0; j<player.getInventory().getSize(); j++) {
						ItemStack a = player.getInventory().getItem(j);
						if(a != null) {
							if(a.hasItemMeta()) {
								if(!a.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING) && !a.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)){
									if(a.getType() == currentItem.getType()) {
										if(a.getAmount() >= currentItemAmount) {
											a.setAmount(a.getAmount()-currentItemAmount);
											break;
										}
										else {
											currentItemAmount-=a.getAmount();
											a.setAmount(0);
										}
									}
								}
							}
							else if(a.getType() == currentItem.getType()) {
								if(a.getAmount() >= currentItemAmount) {
									a.setAmount(a.getAmount()-currentItemAmount);
									break;
								}
								else {
									currentItemAmount-=a.getAmount();
									a.setAmount(0);
								}
							}
						}
					}
				}
			}
			player.getInventory().addItem(output);
			player.openInventory(WeaponryInventory.createSubinventory(player, recipeID));
		}
	}
}
