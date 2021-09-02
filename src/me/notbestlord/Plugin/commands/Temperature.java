package me.notbestlord.Plugin.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.attribute.Attribute;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.craftingsystems.RecipeBookInventory;
import me.notbestlord.Plugin.items.ItemManager;

public class Temperature implements CommandExecutor{
	
	private Main plugin;
	
	public Temperature(Main plugin) {
		this.plugin=plugin;
		plugin.getCommand("temperature").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Error");
			return true;
		}
		Player p = (Player) sender;
		List<Biome> ColdBiomes = new ArrayList<>(Arrays.asList(Biome.SNOWY_TUNDRA,Biome.COLD_OCEAN,Biome.DEEP_COLD_OCEAN
				,Biome.DEEP_FROZEN_OCEAN, Biome.ICE_SPIKES, Biome.SNOWY_TAIGA, Biome.SNOWY_TAIGA_MOUNTAINS
				,Biome.FROZEN_OCEAN, Biome.FROZEN_RIVER, Biome.SNOWY_BEACH, Biome.MOUNTAINS, Biome.GRAVELLY_MOUNTAINS,
				Biome.MODIFIED_GRAVELLY_MOUNTAINS ,Biome.WOODED_MOUNTAINS, Biome.TAIGA, Biome.TAIGA_MOUNTAINS, 
				Biome.GIANT_TREE_TAIGA ,Biome.GIANT_SPRUCE_TAIGA, Biome.STONE_SHORE));
		if(ColdBiomes.contains(p.getLocation().getBlock().getBiome())) {
			p.sendMessage("Cold.");
		}else {
			p.sendMessage("Temperate.");
		}
		return false;
	}

}
