package me.notbestlord.Plugin;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import me.notbestlord.Plugin.armorsetsevents.ExosuitEvent;
import me.notbestlord.Plugin.armorsetsevents.RobeEvents;
import me.notbestlord.Plugin.commands.CheatBrickGive;
import me.notbestlord.Plugin.commands.DebugStickGive;
import me.notbestlord.Plugin.commands.Heal;
import me.notbestlord.Plugin.commands.HydraulicPressGive;
import me.notbestlord.Plugin.commands.MaxRF;
import me.notbestlord.Plugin.commands.PortableSmelteryGive;
import me.notbestlord.Plugin.commands.PortableWorkbenchGive;
import me.notbestlord.Plugin.commands.Recipes;
import me.notbestlord.Plugin.commands.ReturnScrollGive;
import me.notbestlord.Plugin.commands.ShortBowGive;
import me.notbestlord.Plugin.commands.Species;
import me.notbestlord.Plugin.commands.SpyBowGive;
import me.notbestlord.Plugin.commands.Temperature;
import me.notbestlord.Plugin.commands.WarpGive;
import me.notbestlord.Plugin.craftingsystems.AutomaticGardenEvents;
import me.notbestlord.Plugin.craftingsystems.EnchantedWorkBenchEvents;
import me.notbestlord.Plugin.craftingsystems.EnchantedWorkBenchInventory;
import me.notbestlord.Plugin.craftingsystems.FissionReactorEvent;
import me.notbestlord.Plugin.craftingsystems.FissionReactorOpenEvent;
import me.notbestlord.Plugin.craftingsystems.FluidInfuserEvents;
import me.notbestlord.Plugin.craftingsystems.HydraulicPressInventory;
import me.notbestlord.Plugin.craftingsystems.HydraulicPressOpenEvent;
import me.notbestlord.Plugin.craftingsystems.HydroPressEvent;
import me.notbestlord.Plugin.craftingsystems.IndustrialSawEvents;
import me.notbestlord.Plugin.craftingsystems.InfuserEvent;
import me.notbestlord.Plugin.craftingsystems.InfuserInventory;
import me.notbestlord.Plugin.craftingsystems.InfuserOpenEvent;
import me.notbestlord.Plugin.craftingsystems.LiquidMixerEvents;
import me.notbestlord.Plugin.craftingsystems.MelterEvents;
import me.notbestlord.Plugin.craftingsystems.OilExtractorEvents;
import me.notbestlord.Plugin.craftingsystems.OilProcessorEvents;
import me.notbestlord.Plugin.craftingsystems.RecipeBookEvent;
import me.notbestlord.Plugin.craftingsystems.RecipeBookSelectorEvent;
import me.notbestlord.Plugin.craftingsystems.RecipeBookSubInvEvent;
import me.notbestlord.Plugin.craftingsystems.SolidiferEvents;
import me.notbestlord.Plugin.craftingsystems.SpeciesEvents;
import me.notbestlord.Plugin.craftingsystems.SpeciesMenu;
import me.notbestlord.Plugin.craftingsystems.VoidOreMinerEvents;
import me.notbestlord.Plugin.craftingsystems.WeaponryEvents;
import me.notbestlord.Plugin.craftingsystems.WeaponryInventory;
import me.notbestlord.Plugin.dataManagment.Machine;
import me.notbestlord.Plugin.dataManagment.RedstoneFluxDataManager;
import me.notbestlord.Plugin.enchanting.EnchantingTableEvent;
import me.notbestlord.Plugin.enchanting.EnchantingTableInventory;
import me.notbestlord.Plugin.enchanting.EnchantingTableOpen;
import me.notbestlord.Plugin.events.BowEvents;
import me.notbestlord.Plugin.events.BoxEvent;
import me.notbestlord.Plugin.events.CheatBrickEvent;
import me.notbestlord.Plugin.events.ChillyStaffEvent;
import me.notbestlord.Plugin.events.CoalGeneratorEvent;
import me.notbestlord.Plugin.events.FoodEvent;
import me.notbestlord.Plugin.events.GunEvents;
import me.notbestlord.Plugin.events.CustomStatsEvent;
import me.notbestlord.Plugin.events.DualWieldEvents;
import me.notbestlord.Plugin.events.MiniatureEnchantingTableEvent;
import me.notbestlord.Plugin.events.MultiToolEvents;
import me.notbestlord.Plugin.events.PortableEnderChestEvent;
import me.notbestlord.Plugin.events.PortableSmelteryEvent;
import me.notbestlord.Plugin.events.PortableWorkbenchEvent;
import me.notbestlord.Plugin.events.PreloadedChestsEvent;
import me.notbestlord.Plugin.events.ReinforcedTridentEvent;
import me.notbestlord.Plugin.events.ReturnScrollEvent;
import me.notbestlord.Plugin.events.ShortBowEvent;
import me.notbestlord.Plugin.events.SpyBowEvent;
import me.notbestlord.Plugin.events.StopWatchEvent;
import me.notbestlord.Plugin.events.UnplaceableEvent;
import me.notbestlord.Plugin.events.WarpEvent;
import me.notbestlord.Plugin.events.onJoinEvent;
import me.notbestlord.Plugin.items.ArmorSets;
import me.notbestlord.Plugin.items.CoalGeneratorInventory;
import me.notbestlord.Plugin.items.FoodManager;
import me.notbestlord.Plugin.items.IngredientManager;
import me.notbestlord.Plugin.items.ItemManager;
import me.notbestlord.Plugin.items.MachineRecipes;
import me.notbestlord.Plugin.items.TalismanManager;
import me.notbestlord.Plugin.mobs.Bosses;
import me.notbestlord.Plugin.mobs.Drone;
import me.notbestlord.Plugin.mobs.DroneEvents;
import me.notbestlord.Plugin.mobs.MobDropsEvent;
import me.notbestlord.Plugin.mobs.PegasusEvent;
import me.notbestlord.Plugin.mobs.VillagerProfessions;

public class Main extends JavaPlugin{
	
	public static RedstoneFluxDataManager RedstoneFluxManager;
	public static Hashtable<UUID, ArmorStand> playerDrones = new Hashtable<UUID, ArmorStand>();
	public static ArrayList<Machine> MachineList = new ArrayList<>();
	
	@Override
	public void onEnable() {
		plugin = this;
		RedstoneFluxManager = new RedstoneFluxDataManager(this);
		try {
			RedstoneFluxManager.loadMaxRedstoneFlux();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			RedstoneFluxManager.loadRedstoneFlux();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Machine.loadMachineList(MachineList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			BoxEvent.loadStoredBlocks();
		} catch (Exception e) {
			e.printStackTrace();
		}
		/* 
		Plans for FUTURE:
		\x\ - x is complete
		Notes - {
		 		a. balance and fix crafting recipes.
		 		b. new Crafting Recipes {
					- 2 wool -> 1 fabric (Industrial loom)
					- 8 vines -> 1 rope (Industrial loom)
					- 2 twines -> 1 string (Industrial loom)
					- haste, saturation and more potions (Brewing station)
		 	 		- custom bows (fletching table)
		 			- ender orb + ? -> Ender Lord Summoning item (Enchanted Crafting Bench)
		 			- nether star + ? -> new type of nether star (Enchanted Crafting Bench)
		 			- nautilus shells + ? -> water staff (Enchanted Crafting Bench)
		 			- nether warts + ? -> mutated nether warts (Enchanted Crafting Bench)
		 			- mutated nether warts + ? -> potion base (Brewing station)
		 			- ectoplasm + ? -> Ghost potion (Brewing station)
		 			- custom anvil ? (allows to change items name + change name color using hex)
		 			- end star (idk)
				}
				c. Bosses {
					- when killing a boss, the player who kills the boss gets a loot bag which drops the loot of the boss when opened.
					- Wither - 300HP (for comparison)
					
				}
		 	}
		1. cannibalism, players drop their organs on death, eat for yummy
		2. add species (gender but nots) {
			- Human - no changes - diet normal
			- Florian - plant man - diet meat
			- Ape - less smort - diet normal
			- Robot - robot - diet robot
			- Arachnidian - canibal - diet player organs & meat
			- Avian - avian - diet fish and meat
			- piglin - goldboi - diet meat
			- lunarian - night boi - diet normal
			- Plasmian - novakid - diet normal
			- Waterman - Hylotel - diet fish and sushi
		}
		3.1. nether star generator (Are you dumb)
		3. heavy duty crafting bench (SB Forge like system)
		4. brewing station (Custom Potion System)
		5. Nutrition system {
			Nutrition values {
				- good
		 		Vegetables
			 	Fruits
			 	Meats & Beans
		 		Grains
		 		Dairy
		 		
		 		- bad
		 		Sugars
		 		Fats
		 	}
		 	Good nutrition values -
		 	high amounts of them give the player buffs.
		 	low amounts of them will give the player debuffs.
		 	
		 	Bad nutrition values -
		 	high amounts of them give the player debuffs.
		 	
		 	Nutrition values of a player can be viewed using the nutrition band.
		 	
		 	Every food gives different amounts of nutrition values depending on the food.
		}
		6. more void ore miner lenses (Ocean lens, Redstone lens)
		7. custom bosses and mobs (Wither zombie, Ender witch, Super wither, God)
		8. \figure out how attributes work and fix exosuit\ + add more armor sets.
		9. make fletching table usable
		10. \enchanted crafting bench (SB Forge like system, used to make magical items)\
		11. add machines section to /recipes, and move all machines to there
		12. enchantment mover (moves all enchantments on one item into a book, not destroying anything)
		13. industrial loom (used to make clothes and cloth materials)
		14. \automatic garden (Immersive engineering garden cloche)\
		15. 
		
		 */
		getServer().getPluginManager().registerEvents(new RecipeBookEvent(), this);
		IngredientManager.init();
		FoodManager.init();
		TalismanManager.init();
		ItemManager.init();
		MachineRecipes.init();
		MelterEvents.addAllRecipes();
		SolidiferEvents.addAllRecipes();
		LiquidMixerEvents.addAllRecipes();
		FluidInfuserEvents.addAllRecipes();
		VoidOreMinerEvents.addResources();
		IndustrialSawEvents.addAllRecipes();
		WeaponryInventory.addAllRecipes();
		AutomaticGardenEvents.addAllRecipes();
		getServer().getPluginManager().registerEvents(new CheatBrickEvent(), this);
		new CheatBrickGive(this);
		getServer().getPluginManager().registerEvents(new SpyBowEvent(), this);
		new SpyBowGive(this);
		new Heal(this);
		getServer().getPluginManager().registerEvents(new ReturnScrollEvent(), this);
		new ReturnScrollGive(this);
		getServer().getPluginManager().registerEvents(new WarpEvent(), this);
		new WarpGive(this);
		getServer().getPluginManager().registerEvents(new PortableWorkbenchEvent(), this);
		new PortableWorkbenchGive(this);
		getServer().getPluginManager().registerEvents(new onJoinEvent(), this);
		new EnchantingTableOpen(this);
		getServer().getPluginManager().registerEvents(new EnchantingTableEvent(), this);
		getServer().getPluginManager().registerEvents(new PegasusEvent(), this);
		getServer().getPluginManager().registerEvents(new UnplaceableEvent(), this);
		getServer().getPluginManager().registerEvents(new PortableSmelteryEvent(), this);
		new PortableSmelteryGive(this);
		getServer().getPluginManager().registerEvents(new ShortBowEvent(), this);
		new ShortBowGive(this);
		getServer().getPluginManager().registerEvents(new HydraulicPressOpenEvent(), this);
		new HydraulicPressGive(this);
		getServer().getPluginManager().registerEvents(new HydroPressEvent(), this);
		getServer().getPluginManager().registerEvents(new PortableEnderChestEvent(), this);
		getServer().getPluginManager().registerEvents(new CoalGeneratorEvent(), this);
		getServer().getPluginManager().registerEvents(new MiniatureEnchantingTableEvent(), this);
		getServer().getPluginManager().registerEvents(new ExosuitEvent(), this);
		new MaxRF(this);
		getServer().getPluginManager().registerEvents(new InfuserOpenEvent(), this);
		getServer().getPluginManager().registerEvents(new InfuserEvent(), this);
		getServer().getPluginManager().registerEvents(new FoodEvent(), this);
		getServer().getPluginManager().registerEvents(new FissionReactorOpenEvent(), this);
		getServer().getPluginManager().registerEvents(new FissionReactorEvent(), this);
		new Recipes(this);
		getServer().getPluginManager().registerEvents(new RecipeBookSelectorEvent(), this);
		getServer().getPluginManager().registerEvents(new RecipeBookSubInvEvent(), this);
		getServer().getPluginManager().registerEvents(new MobDropsEvent(), this);
		getServer().getPluginManager().registerEvents(new MultiToolEvents(), this);
		getServer().getPluginManager().registerEvents(new MelterEvents(), this);
		getServer().getPluginManager().registerEvents(new SolidiferEvents(), this);
		getServer().getPluginManager().registerEvents(new LiquidMixerEvents(), this);
		getServer().getPluginManager().registerEvents(new OilExtractorEvents(), this);
		getServer().getPluginManager().registerEvents(new OilProcessorEvents(), this);
		getServer().getPluginManager().registerEvents(new FluidInfuserEvents(), this);
		getServer().getPluginManager().registerEvents(new VoidOreMinerEvents(), this);
		getServer().getPluginManager().registerEvents(new PreloadedChestsEvent(), this);
		getServer().getPluginManager().registerEvents(new DroneEvents(), this);
		getServer().getPluginManager().registerEvents(new StopWatchEvent(), this);
		getServer().getPluginManager().registerEvents(new VillagerProfessions(), this);
		new DebugStickGive(this);
		getServer().getPluginManager().registerEvents(new BoxEvent(), this);
		getServer().getPluginManager().registerEvents(new IndustrialSawEvents(), this);
		getServer().getPluginManager().registerEvents(new Bosses(), this);
		getServer().getPluginManager().registerEvents(new EnchantedWorkBenchEvents(), this);
		getServer().getPluginManager().registerEvents(new ReinforcedTridentEvent(), this);
		getServer().getPluginManager().registerEvents(new CustomStatsEvent(), this);
		getServer().getPluginManager().registerEvents(new ChillyStaffEvent(), this);
		getServer().getPluginManager().registerEvents(new RobeEvents(), this);
		getServer().getPluginManager().registerEvents(new BowEvents(), this);
		getServer().getPluginManager().registerEvents(new WeaponryEvents(), this);
		getServer().getPluginManager().registerEvents(new GunEvents(), this);
		getServer().getPluginManager().registerEvents(new DualWieldEvents(), this);
		getServer().getPluginManager().registerEvents(new AutomaticGardenEvents(), this);
		new Temperature(this);
		new Species(this);
		getServer().getPluginManager().registerEvents(new SpeciesMenu(), this);
		getServer().getPluginManager().registerEvents(new SpeciesEvents(), this);
		if(!Bukkit.getOnlinePlayers().isEmpty()) {
			for(Player online : Bukkit.getOnlinePlayers()) {
				HydraulicPressInventory.init(online);
				CoalGeneratorInventory.createCoalGenerator(online);
				EnchantingTableInventory.createInventory(online);
				ArmorSets.init(online);
				InfuserInventory.createInfuser(online);
				RedstoneFluxManager.createBoard(online);
				ExosuitEvent.initPlayer(online);
				StopWatchEvent.cooldown.put(online.getUniqueId(), 0);
				SpeciesEvents.RestoreRunnable(online);
				if(online.hasMetadata("dronebuff")) {
					try {
						Drone.loadDrone(online);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		BukkitScheduler scheduler = getServer().getScheduler();
        
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	for(Player online : Bukkit.getOnlinePlayers()) {
            		RedstoneFluxManager.maxRedstoneFluxCalculate(online);
    				ArmorSets.CheckArmorSets(online);
    				ExosuitEvent.perTickChecks(online);
    				RedstoneFluxManager.updateBoard(online);
            	}
            }
        }, 0L, 1L);
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	for(Player online : Bukkit.getOnlinePlayers()) {
            		RedstoneFluxManager.RedstoneFluxCalculate(online);
            	}
            }
        }, 0L, 20L);
	}
	
	@Override
	public void onDisable() {
		try {
			RedstoneFluxManager.saveMaxRedstoneFlux();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			RedstoneFluxManager.saveRedstoneFlux();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Machine.UpdateMachineInventories();
			Machine.saveMachineList(MachineList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			BoxEvent.saveStoredBlocks();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(!Bukkit.getOnlinePlayers().isEmpty()) {
			for(Player online : Bukkit.getOnlinePlayers()) {
				if(online.hasMetadata("dronebuff")) {
					try {
						Drone.saveDrone(online);
					} catch (Exception e) {
						e.printStackTrace();
					}
					Drone.removeDrone(online);
				}
			}
		}
	}
	
	private static Plugin plugin;
	
	public static Plugin getMain() {
		return plugin;
	}
}
