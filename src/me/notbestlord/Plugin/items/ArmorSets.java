package me.notbestlord.Plugin.items;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;

public class ArmorSets {
	
	public static Hashtable<UUID, ArrayList> exosuitSet = new Hashtable<UUID, ArrayList>();
	
	public static void init(Player player) {
		ArrayList array = new ArrayList<>();
		array.add(false);
		array.add(null);
		exosuitSet.put(player.getUniqueId(), new ArrayList(array));
		
	}
	
	public static void noSet(Player player) {
		exosuitSet.get(player.getUniqueId()).set(0, false);
	}
	
	public static void CheckArmorSets(Player player) {
		ItemStack[] equipment = player.getEquipment().getArmorContents();
		
		if(equipment[0] == null || equipment[1] == null || equipment[2] == null || equipment[3] == null) {
			noSet(player);
			return;
		}
		if(!equipment[0].hasItemMeta() || !equipment[1].hasItemMeta() || !equipment[2].hasItemMeta() || !equipment[3].hasItemMeta()) {
			noSet(player);
			return;
		}
		if(!equipment[0].getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "SetID"), PersistentDataType.STRING)) {
			noSet(player);
			return;
		}
		if(!equipment[1].getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "SetID"), PersistentDataType.STRING)) {
			noSet(player);
			return;
		}
		if(!equipment[2].getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "SetID"), PersistentDataType.STRING)) {
			noSet(player);
			return;
		}
		if(!equipment[3].getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "SetID"), PersistentDataType.STRING)) {
			noSet(player);
			return;
		}
		
		String[] setidlst = new String[4];
		setidlst[0]=equipment[0].getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "SetID"), PersistentDataType.STRING);
		setidlst[1]=equipment[1].getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "SetID"), PersistentDataType.STRING);
		setidlst[2]=equipment[2].getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "SetID"), PersistentDataType.STRING);
		setidlst[3]=equipment[3].getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "SetID"), PersistentDataType.STRING);
		
		for(int i=0; i<setidlst.length-1;i++) {
			if(!setidlst[i].equals(setidlst[i+1])) {
				noSet(player);
				return;
			}
		}
		
		String SetID = setidlst[0];
		
		if(SetID.equals("exosuit")) {
			exosuitSet.get(player.getUniqueId()).set(0, true);
			exosuitSet.get(player.getUniqueId()).set(1, equipment[3].getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "SetType"), PersistentDataType.STRING));
			return;
		}
		
		
		
		
		
		
	}
}
