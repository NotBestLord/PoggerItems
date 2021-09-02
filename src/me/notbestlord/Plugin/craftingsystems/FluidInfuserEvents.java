package me.notbestlord.Plugin.craftingsystems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
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
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.dataManagment.Machine;
import me.notbestlord.Plugin.items.FoodManager;
import me.notbestlord.Plugin.items.IngredientManager;
import me.notbestlord.Plugin.items.ItemManager;
import net.md_5.bungee.api.ChatColor;

public class FluidInfuserEvents implements Listener{
	
	public static Hashtable<ItemStack, ArrayList> Recipes = new Hashtable<>();
	
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock() == null) {
				return;
			}
			if(event.getClickedBlock().getType().equals(Material.HOPPER)) {
				Location BottomBlockLocation = event.getClickedBlock().getLocation();
				Location TopBlockLocation = event.getClickedBlock().getLocation();
				BottomBlockLocation.subtract(0, 1, 0);
				TopBlockLocation.add(0, 1, 0);
				if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.DISPENSER)) {
					if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
						event.setCancelled(true);
						if(!(new Machine(event.getClickedBlock().getLocation(), "FluidInfuser").MachineInArray(Main.MachineList))) {
							if (event.getPlayer().getInventory().getItemInMainHand().equals(ItemManager.MultiBlockHammer) || event.getPlayer().getInventory().getItemInMainHand().equals(ItemManager.RadiatedMultiBlockHammer)){
								FluidInfuserInventory.createFluidInfuser(event.getClickedBlock().getLocation());
								Machine machine = new Machine(event.getClickedBlock().getLocation(), FluidInfuserInventory.inventory.get(event.getClickedBlock().getLocation()), "FluidInfuser");
								Main.MachineList.add(machine);
								event.getPlayer().openInventory(FluidInfuserInventory.inventory.get(event.getClickedBlock().getLocation()));
							}
						}
						else {
							event.getPlayer().openInventory(FluidInfuserInventory.inventory.get(event.getClickedBlock().getLocation()));
						}
					}
				}
			}
		}
	}
	@EventHandler
	public static void onBreakBlock(BlockBreakEvent event) {
		if(event.getBlock().getType().equals(Material.HOPPER)) {
			Location BottomBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			BottomBlockLocation.subtract(0, 1, 0);
			TopBlockLocation.add(0, 1, 0);
			if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.DISPENSER)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
					if(FluidInfuserInventory.inventory.containsKey(event.getBlock().getLocation())) {
						FluidInfuserInventory.inventory.remove(event.getBlock().getLocation());
						Machine.DeleteMachineFromList(new Machine(event.getBlock().getLocation(), "FluidInfuser"));
					}
				}
			}
		}
		if(event.getBlock().getType().equals(Material.DISPENSER)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location TopBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.add(0, 1, 0);
			TopBlockLocation.add(0, 2, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.HOPPER)) {
				if(event.getPlayer().getWorld().getBlockAt(TopBlockLocation).getType().equals(Material.IRON_TRAPDOOR)) {
					if(FluidInfuserInventory.inventory.containsKey(BaseBlockLocation)) {
						FluidInfuserInventory.inventory.remove(BaseBlockLocation);
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "FluidInfuser"));
					}
				}
			}
		}
		if(event.getBlock().getType().equals(Material.IRON_TRAPDOOR)) {
			Location BaseBlockLocation = event.getBlock().getLocation();
			Location BottomBlockLocation = event.getBlock().getLocation();
			BaseBlockLocation.subtract(0, 1, 0);
			BottomBlockLocation.subtract(0, 2, 0);
			if(event.getPlayer().getWorld().getBlockAt(BaseBlockLocation).getType().equals(Material.HOPPER)) {
				if(event.getPlayer().getWorld().getBlockAt(BottomBlockLocation).getType().equals(Material.DISPENSER)) {
					if(FluidInfuserInventory.inventory.containsKey(BaseBlockLocation)) {
						FluidInfuserInventory.inventory.remove(BaseBlockLocation);
						Machine.DeleteMachineFromList(new Machine(BaseBlockLocation, "FluidInfuser"));
					}
				}
			}
		}
	}
	@EventHandler
	public static void onClick(InventoryClickEvent event) {
		if(event.getSlot() == -999 || event.getClickedInventory()==null)
			return;
		if(!event.getView().getTitle().equals(ChatColor.AQUA+"Fluid Infuser") || event.getClickedInventory().equals(event.getWhoClicked().getInventory())) {
			return;
		}
		if(event.getSlot() != 1 && event.getSlot() != 19 && event.getSlot() != 16) {
			event.setCancelled(true);
		}
		
		Player player = (Player)event.getWhoClicked();
		if(event.getSlot() == 22) {
			player.closeInventory();
		}
		if(event.getSlot() == 13) {
			if(event.getClickedInventory().getItem(16) != null) {
				player.sendMessage(ChatColor.RED + "Empty output slot");
			return;
		}
			if(event.getClickedInventory().getItem(1) == null || event.getClickedInventory().getItem(19) == null) {
				return;
			}
			if(event.getClickedInventory().getItem(19).getAmount() != 1) {
				player.sendMessage(ChatColor.RED + "Place a SINGLE Tank in input slot");
				return;
			}
			if(!event.getClickedInventory().getItem(19).hasItemMeta()) {
				player.sendMessage(ChatColor.RED + "Place Tank in input slot");
				return;
			}
			if(!event.getClickedInventory().getItem(19).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING)) {
				player.sendMessage(ChatColor.RED + "Place Tank in input slot");
				return;
			}
			Collection<ArrayList> arrays = Recipes.values();
			ArrayList[] arrayList = new ArrayList[arrays.size()];
			arrays.toArray(arrayList);
			ItemStack[] inputs = new ItemStack[arrays.size()];
			ItemStack output = null;
			
			for(int i=0; i<arrayList.length; i++) {
				inputs[i] = (ItemStack) arrayList[i].get(0);
			}
			
			if(event.getClickedInventory().getItem(1).getType().equals(Material.PLAYER_HEAD)) {
				String ingID = event.getClickedInventory().getItem(1).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING);
				for(int i=0; i<inputs.length; i++) {
					if(inputs[i].hasItemMeta()) {
						if(inputs[i].getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)) {
							if(inputs[i].getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING).equals(ingID)) {
								ItemStack[] outputs = new ItemStack[arrays.size()];
								Recipes.keySet().toArray(outputs);
								output = outputs[i];
								break;
							}
						}
					}
				}
			}
			else {
				for(int i=0; i<arrayList.length; i++) {
					if(inputs[i].getType().equals(event.getClickedInventory().getItem(1).getType())) {
						ItemStack[] outputs = new ItemStack[arrays.size()];
						Recipes.keySet().toArray(outputs);
						output = outputs[i];
						break;
					}
				}
			}
			
			if(output == null) {
				return;
			}
			
			ArrayList recipeArray = Recipes.get(output);
			ItemStack recipeInput = (ItemStack) recipeArray.get(0);
			String recipeFluidType = (String) recipeArray.get(1);
			int recipeFluidMB = (int) recipeArray.get(2);
			
			if(!event.getClickedInventory().getItem(19).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING).equals(recipeFluidType)) {
				return;
			}
			if(event.getClickedInventory().getItem(19).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER) - recipeFluidMB >= 0) {
				if(event.getClickedInventory().getItem(1).getAmount() >= recipeInput.getAmount()) {
					if(event.getClickedInventory().getItem(1).getAmount() == recipeInput.getAmount()) {
						event.getClickedInventory().setItem(1, null);
					}
					else {
						ItemStack input1 = event.getClickedInventory().getItem(1);
						input1.setAmount(input1.getAmount()-recipeInput.getAmount());
						event.getClickedInventory().setItem(1, input1);
					}
					
					ItemStack tank = event.getClickedInventory().getItem(19);
					ItemMeta tankMeta = tank.getItemMeta();
					tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER, tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER) - recipeFluidMB);
					List<String> lore = new ArrayList<>();
					lore.addAll(tankMeta.getLore());
					if(tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER) == 0) {
						tankMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING, "");
						lore.set(2, "§7"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of none.");
					}
					else {
						lore.set(2, "§7"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMB"), PersistentDataType.INTEGER)+"/"+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidMBMax"), PersistentDataType.INTEGER)+"mb of "+tankMeta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "fluidType"), PersistentDataType.STRING)+".");
					}
					tankMeta.setLore(lore);
					tank.setItemMeta(tankMeta);
					event.getClickedInventory().setItem(19, tank);
					event.getClickedInventory().setItem(16, output);
				}
			}
			else {
				player.sendMessage(ChatColor.RED + "Not enough liquid");
				return;
			}
		}
	}
	public static void addAllRecipes() {
		addRecipe(FoodManager.FrenchFries, new ItemStack(Material.BAKED_POTATO, 64), "crudeoil", 2500);
		addRecipe(FoodManager.SweetBerrySoda, IngredientManager.PlasticCup, "Carbonated sweet berry juice", 4000);
	}
	public static void addRecipe(ItemStack outputItem, ItemStack inputItem, String fluidTypeInput, int fluidMBInput) {
		ArrayList array = new ArrayList<>();
		array.add(inputItem);
		array.add(fluidTypeInput);
		array.add(fluidMBInput);
		Recipes.put(outputItem, new ArrayList(array));
	}
}
