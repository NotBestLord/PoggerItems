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
import me.notbestlord.Plugin.dataManagment.Recipe;
import me.notbestlord.Plugin.items.ItemManager;
import net.md_5.bungee.api.ChatColor;

public class EnchantedWorkBenchEvents implements Listener{
	
	@EventHandler
	private void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock() == null) {
				return;
			}
			if(event.getClickedBlock().getType().equals(Material.CRAFTING_TABLE)) {
				Location BottomBlockLocation = event.getClickedBlock().getLocation();
				Location TopBlockLocation = event.getClickedBlock().getLocation();
				BottomBlockLocation.subtract(0, 1, 0);
				TopBlockLocation.add(0, 1, 0);
				if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.BEACON)) {
					if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.ENCHANTING_TABLE)) {
						event.setCancelled(true);
						if(!(new Machine(event.getClickedBlock().getLocation(), "EnchantedWorkBench").MachineInArray(Main.MachineList))) {
							if (event.getPlayer().getInventory().getItemInMainHand().equals(ItemManager.EnchantedWorkbenchCatalyst)){
								EnchantedWorkBenchInventory.createMainInventory(event.getClickedBlock().getLocation(), 0);
								Machine machine = new Machine(event.getClickedBlock().getLocation(), EnchantedWorkBenchInventory.inventory.get(event.getClickedBlock().getLocation()), "EnchantedWorkBench");
								Main.MachineList.add(machine);
								event.getPlayer().openInventory(EnchantedWorkBenchInventory.inventory.get(event.getClickedBlock().getLocation()));
								event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount()-1);
							}
						}
						else {
							event.getPlayer().openInventory(EnchantedWorkBenchInventory.inventory.get(event.getClickedBlock().getLocation()));
						}
					}
				}
			}
		}
	}
	@EventHandler
	private void onBreakBlock(BlockBreakEvent event) {
		if(event.getBlock().getType().equals(Material.CRAFTING_TABLE)) {
			Location BottomBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			BottomBlockLocation.subtract(0, 1, 0);
			TopBlockLocation.add(0, 1, 0);
			if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.BEACON)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.ENCHANTING_TABLE)) {
					if(EnchantedWorkBenchInventory.inventory.containsKey(event.getBlock().getLocation())) {
						EnchantedWorkBenchInventory.inventory.remove(event.getBlock().getLocation());
						Machine.DeleteMachineFromList(new Machine(event.getBlock().getLocation(), "EnchantedWorkBench"));
						event.getPlayer().getInventory().addItem(ItemManager.EnchantedWorkbenchCatalyst);
					}
				}
			}
		}
		if(event.getBlock().getType().equals(Material.BEACON)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.add(0, 1, 0);
			TopBlockLocation.add(0, 2, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.CRAFTING_TABLE)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.ENCHANTING_TABLE)) {
					if(EnchantedWorkBenchInventory.inventory.containsKey(BaseBlockLocation)) {
						EnchantedWorkBenchInventory.inventory.remove(BaseBlockLocation);
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "EnchantedWorkBench"));
						event.getPlayer().getInventory().addItem(ItemManager.EnchantedWorkbenchCatalyst);
					}
				}
			}
		}
		if(event.getBlock().getType().equals(Material.ENCHANTING_TABLE)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location BottomBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.subtract(0, 1, 0);
			BottomBlockLocation.subtract(0, 2, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.CRAFTING_TABLE)) {
				if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.BEACON)) {
					if(EnchantedWorkBenchInventory.inventory.containsKey(BaseBlockLocation)) {
						EnchantedWorkBenchInventory.inventory.remove(BaseBlockLocation);
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "EnchantedWorkBench"));
						event.getPlayer().getInventory().addItem(ItemManager.EnchantedWorkbenchCatalyst);
					}
				}
			}
		}
	}
	@EventHandler
	private void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(!event.getView().getTitle().equals(ChatColor.AQUA+"Enchanted WorkBench") || event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
			return;
		}
		Player player = (Player) event.getWhoClicked();
		event.setCancelled(true);
		if(event.getSlot() == 49) {
			player.closeInventory();
		}
		if(event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
			String id = event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING);
			player.openInventory(EnchantedWorkBenchInventory.createSubinventory(player, id));
		}
		else if(event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)) {
			String id = event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING);
			player.openInventory(EnchantedWorkBenchInventory.createSubinventory(player, id));
		}
		else {
			
		}
		if(event.getClickedInventory().getItem(event.getSlot()).hasItemMeta()) {
			int currentPage = event.getClickedInventory().getItem(0).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "page"), PersistentDataType.INTEGER);
			if(event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().equals(ChatColor.WHITE+"Page Up")) {
				player.openInventory(EnchantedWorkBenchInventory.createMainInventory(null, currentPage-1));
			}
			else if(event.getClickedInventory().getItem(event.getSlot()).getItemMeta().getDisplayName().equals(ChatColor.WHITE+"Page Down")) {
				player.openInventory(EnchantedWorkBenchInventory.createMainInventory(null, currentPage+1));
			}
		}
	}
	@EventHandler
	private void onSubClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(!event.getView().getTitle().equals(ChatColor.AQUA+"Enchanted Workbench") || event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
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
			Recipe.EnchantedWorkbenchRecipe recipe = EnchantedWorkBenchInventory.RecipeList.get(recipeID).clone();
			ArrayList<ItemStack> recipeInputs = new ArrayList<ItemStack>(recipe.getIngredients());
			ArrayList<Integer> amounts = new ArrayList<Integer>(recipe.getIngredientAmounts());
			ItemStack output = recipe.getResult();
			for(int i=0; i<recipeInputs.size(); i++) {
				ItemStack currentItem = recipeInputs.get(i);
				int currentItemAmount = amounts.get(i);
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
			player.openInventory(EnchantedWorkBenchInventory.createSubinventory(player, recipeID));
		}
	}
}
