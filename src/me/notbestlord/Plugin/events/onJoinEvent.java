package me.notbestlord.Plugin.events;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerLeashEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.armorsetsevents.ExosuitEvent;
import me.notbestlord.Plugin.craftingsystems.FissionReactorEvent;
import me.notbestlord.Plugin.craftingsystems.FissionReactorInventory;
import me.notbestlord.Plugin.craftingsystems.HydraulicPressInventory;
import me.notbestlord.Plugin.craftingsystems.InfuserInventory;
import me.notbestlord.Plugin.craftingsystems.OilExtractorEvents;
import me.notbestlord.Plugin.craftingsystems.SpeciesEvents;
import me.notbestlord.Plugin.enchanting.EnchantingTableInventory;
import me.notbestlord.Plugin.items.ArmorSets;
import me.notbestlord.Plugin.items.CoalGeneratorInventory;
import me.notbestlord.Plugin.items.ItemManager;
import me.notbestlord.Plugin.mobs.Drone;

public class onJoinEvent implements Listener {
	@EventHandler
	private void onJoin(PlayerJoinEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
		HydraulicPressInventory.init(event.getPlayer());
		CoalGeneratorInventory.createCoalGenerator(event.getPlayer());
		EnchantingTableInventory.createInventory(event.getPlayer());
		ArmorSets.init(event.getPlayer());
		InfuserInventory.createInfuser(event.getPlayer());
		Main.RedstoneFluxManager.createBoard(event.getPlayer());
		ExosuitEvent.initPlayer(event.getPlayer());
		StopWatchEvent.cooldown.put(event.getPlayer().getUniqueId(), 0);
		SpeciesEvents.RestoreRunnable(event.getPlayer());
		if(event.getPlayer().hasMetadata("dronebuff")) {
			if(event.getPlayer().getMetadata("dronebuff").get(0).asString().equals("removed")) {
				Drone.loadDrone(event.getPlayer());
			}
		}
	}
	@EventHandler
	private void onLeave(PlayerQuitEvent event) throws FileNotFoundException, IOException {
		if(event.getPlayer().hasMetadata("dronebuff")) {
			Drone.saveDrone(event.getPlayer());
			Drone.removeDrone(event.getPlayer());
		}
	}
}
