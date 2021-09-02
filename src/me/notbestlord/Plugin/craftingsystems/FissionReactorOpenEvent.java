package me.notbestlord.Plugin.craftingsystems;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataType;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.dataManagment.Machine;

public class FissionReactorOpenEvent implements Listener{
	
	@EventHandler
	public static void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getItem() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING))
					return;
				if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "MachineID"), PersistentDataType.STRING).equals("fissionreactor")) {
					if(!(new Machine(event.getPlayer().getUniqueId(), "FissionReactor").MachineInArray(Main.MachineList))) {
						FissionReactorInventory.createFissionReactor(event.getPlayer());
						FissionReactorEvent.FissionReactorActivness.put(event.getPlayer().getUniqueId(), false);
						FissionReactorEvent.FissionReactorCounter.put(event.getPlayer().getUniqueId(), 0);
						Main.MachineList.add(new Machine(event.getPlayer().getUniqueId(), FissionReactorInventory.inventory.get(event.getPlayer().getUniqueId()),"FissionReactor"));
						event.getPlayer().openInventory(FissionReactorInventory.inventory.get(event.getPlayer().getUniqueId()));
					}
					else {
						event.getPlayer().openInventory(FissionReactorInventory.inventory.get(event.getPlayer().getUniqueId()));
					}
				}
			}
		}
	}
}
