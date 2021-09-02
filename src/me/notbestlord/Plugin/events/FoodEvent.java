package me.notbestlord.Plugin.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.notbestlord.Plugin.Main;


public class FoodEvent implements Listener{
	
	private static List<String> HerbivoreFood = new ArrayList<>();
	private static List<String> CarnivoreFood = new ArrayList<>();
	private static List<String> RoboticFood = new ArrayList<>();
	private static List<String> OtherFoods = new ArrayList<>();
	private static List<String> Fish = new ArrayList<>();
	private static HashMap<String, ItemStack> leftovers = new HashMap<String, ItemStack>();
	private static HashMap<String, Float> Saturation = new HashMap<String, Float>();
	private static HashMap<String, Integer> Hunger = new HashMap<String, Integer>();
	private static List<String> Drinks = new ArrayList<>();
	private static HashMap<String, List<PotionEffect>> PotionEffects = new HashMap<String, List<PotionEffect>>();
	
	public static void registerFood(String ID, String type, float saturation, int hunger, ItemStack leftover, boolean isDrink) {
		Saturation.put(ID, saturation);
		Hunger.put(ID, hunger);
		switch (type) {
		case "meat":
			CarnivoreFood.add(ID);
			break;
		case "plant":
			HerbivoreFood.add(ID);
			break;
		case "metal":
			RoboticFood.add(ID);
			break;
		default:
			OtherFoods.add(ID);
			break;
		}
		if(leftover != null) {
			leftovers.put(ID, leftover);
		}
		if(isDrink) {
			Drinks.add(ID);
		}
	}
	public static void addFoodType(String ID, String type) {
		switch (type) {
		case "meat":
			CarnivoreFood.add(ID);
			break;
		case "plant":
			HerbivoreFood.add(ID);
			break;
		case "metal":
			RoboticFood.add(ID);
			break;
		case "fish":
			Fish.add(ID);
			break;
		default:
			OtherFoods.add(ID);
			break;
		}
	}
	public static void addPotionEffect(String ID, PotionEffect effect) {
		if(!PotionEffects.containsKey(ID)) {
			PotionEffects.put(ID, new ArrayList<PotionEffect>());
		}
		PotionEffects.get(ID).add(effect);
	}
	@EventHandler
	private void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				//
				String id = "";
				ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
				if(meta.getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING)) {
					id = meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ingredientID"), PersistentDataType.STRING);
				}
				else if(meta.getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
					id = meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING);
				}
				if(id.equals("")) {
					return;
				}
				if(!Saturation.containsKey(id) && !Hunger.containsKey(id) && !PotionEffects.containsKey(id)) {
					return;
				}
				Player player = event.getPlayer();
				if(player.getFoodLevel() >= 20 || player.getSaturation() >= 30) {
					return;
				}
				float saturation = 0;
				int hunger = 0;
				if(Saturation.containsKey(id)) {
					saturation = Saturation.get(id);
				}
				
				if(Hunger.containsKey(id)) {
					hunger = Hunger.get(id);
				}
				final String runnableID = id;
				//
				if(saturation != 0) {
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
						
						@Override
						public void run() {
							player.setSaturation(player.getSaturation()+Saturation.get(runnableID));
							if(player.getSaturation() >= 30) {
								player.setSaturation(30);
							}
						}
					}, 40l);
				}
				//
				if(hunger != 0) {
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
						
						@Override
						public void run() {
							player.setFoodLevel(player.getFoodLevel()+Hunger.get(runnableID));
							if(player.getFoodLevel() >= 20) {
								player.setFoodLevel(20);
							}
						}
					}, 40l);
				}
				//
				if(Drinks.contains(id)){
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
						
						@Override
						public void run() {
							player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1, 1);
						}
					}, 40l);
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
						
						@Override
						public void run() {
							player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_DRINK, 1, 1);
						}
					}, 40l);
				}
				else {
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
						
						@Override
						public void run() {
							player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 1, 1);
						}
					}, 40l);
				}
				//
				if(PotionEffects.containsKey(id)) {
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
						
						@Override
						public void run() {
							for(PotionEffect effect : PotionEffects.get(runnableID)) {
								player.addPotionEffect(effect);
							}
						}
					}, 40l);
				}
				//
				if(player.hasMetadata("Species")) {
					String Species = player.getMetadata("Species").get(0).asString();
					if(RoboticFood.contains(id)) {
						if(!Species.equals("Automaton")) {
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
								
								@Override
								public void run() {
									player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 600, 0));
								}
							}, 40l);
						}
					}
					else if(Species.equals("Florian")) {
						if(!HerbivoreFood.contains(id)) {
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
								
								@Override
								public void run() {
									player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 600, 0));
								}
							}, 40l);
						}
					}
					else if(Species.equals("Ape")) {
						if(!HerbivoreFood.contains(id)) {
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
								
								@Override
								public void run() {
									player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 600, 0));
								}
							}, 40l);
						}
					}
					else if(Species.equals("Arachnid")) {
						if(!CarnivoreFood.contains(id)) {
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
								
								@Override
								public void run() {
									player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 600, 0));
								}
							}, 40l);
						}
					}
					else if(Species.equals("Avian")) {
						if(!CarnivoreFood.contains(id)) {
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
								
								@Override
								public void run() {
									player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 600, 0));
								}
							}, 40l);
							
						}
						if(Fish.contains(id)) {
							player.setSaturation(player.getSaturation()+8);
						}
					}
					else if(Species.equals("Pigman")) {
						if(id.equals("goldball")) {
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
								
								@Override
								public void run() {
									player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 0));
									player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 0));
									player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 0));
									player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 0));
								}
							}, 40l);
						}
						else if(!CarnivoreFood.contains(id)) {
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
								
								@Override
								public void run() {
									player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 600, 0));
								}
							}, 40l);
						}
					}
					else if(Species.equals("Hylotel")) {
						if(!CarnivoreFood.contains(id)) {
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
								
								@Override
								public void run() {
									player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 600, 0));
								}
							}, 40l);
						}
						if(Fish.contains(id)) {
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getMain(), new Runnable() {
								
								@Override
								public void run() {
									player.setSaturation(player.getSaturation()+8);
								}
							}, 40l);
						}
					}
				}

				player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount()-1);
			}
		}
	}
	
}
