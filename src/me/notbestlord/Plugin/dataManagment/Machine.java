package me.notbestlord.Plugin.dataManagment;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.craftingsystems.AutomaticGardenInventory;
import me.notbestlord.Plugin.craftingsystems.EnchantedWorkBenchInventory;
import me.notbestlord.Plugin.craftingsystems.FissionReactorEvent;
import me.notbestlord.Plugin.craftingsystems.FissionReactorInventory;
import me.notbestlord.Plugin.craftingsystems.FluidInfuserInventory;
import me.notbestlord.Plugin.craftingsystems.IndustrialSawInventory;
import me.notbestlord.Plugin.craftingsystems.LiquidMixerInventory;
import me.notbestlord.Plugin.craftingsystems.MelterInventory;
import me.notbestlord.Plugin.craftingsystems.OilExtractorEvents;
import me.notbestlord.Plugin.craftingsystems.OilExtractorInventory;
import me.notbestlord.Plugin.craftingsystems.OilProcessorEvents;
import me.notbestlord.Plugin.craftingsystems.OilProcessorInventory;
import me.notbestlord.Plugin.craftingsystems.SolidiferInventory;
import me.notbestlord.Plugin.craftingsystems.VoidOreMinerInventory;
import me.notbestlord.Plugin.craftingsystems.WeaponryInventory;

public class Machine {
	
	private ArrayList AsString;
	private Location multiBlockLocation;
	private Inventory inventory;
	private String inventoryType;
	private UUID playerUUID;
	
	public Machine(Location location, Inventory inventory, String inventoryType) {
		AsString = new ArrayList<>();
		String Loc = location.getWorld().getName();
		Loc += "/"+location.getBlockX();
		Loc += "/"+location.getBlockY();
		Loc += "/"+location.getBlockZ();
		AsString.add(Loc);
		ArrayList<String> inventoryAsString = new ArrayList<>();
		for(int i=0; i<inventory.getSize(); i++) {
			ItemStack item = inventory.getContents()[i];
			String temp = "";
			if(item != null) {
				if(item.getType() == Material.GRAY_STAINED_GLASS_PANE) {
					if(item.hasItemMeta()) {
						if(item.getItemMeta().getDisplayName().equals(" ")) {
							temp = "Blank";
						}
					}
				}
				if(temp.equals("")) {
					try {
						ByteArrayOutputStream io = new ByteArrayOutputStream();
						BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);
						
						os.writeObject(item);
						os.flush();
						
						temp = Base64.getEncoder().encodeToString(io.toByteArray());
					}catch (Exception e) {
						
					}
				}
			}
			else {
				temp = "null";
			}
			inventoryAsString.add(temp);
		}
		AsString.add(inventoryAsString);
		AsString.add(inventoryType);
		AsString.add("NoPlayerUUID");
		this.multiBlockLocation = location;
		this.inventory = inventory;
		this.inventoryType = inventoryType;
		this.playerUUID = null;
	}
	
	public Machine(UUID uuid, Inventory inventory, String inventoryType) {
		AsString = new ArrayList<>();
		AsString.add("noLocation");
		ArrayList<String> inventoryAsString = new ArrayList<>();
		for(int i=0; i<inventory.getSize(); i++) {
			ItemStack item = inventory.getContents()[i];
			String temp = "";
			if(item != null) {
				if(item.getType() == Material.GRAY_STAINED_GLASS_PANE) {
					if(item.hasItemMeta()) {
						if(item.getItemMeta().getDisplayName().equals(" ")) {
							temp = "Blank";
						}
					}
				}
				if(temp.equals("")) {
					try {
						ByteArrayOutputStream io = new ByteArrayOutputStream();
						BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);
						
						os.writeObject(item);
						os.flush();
						
						temp = Base64.getEncoder().encodeToString(io.toByteArray());
					}catch (Exception e) {
						
					}
				}
			}
			else {
				temp = "null";
			}
			inventoryAsString.add(temp);
		}
		AsString.add(inventoryAsString);
		AsString.add(inventoryType);
		AsString.add(uuid.toString());
		this.multiBlockLocation = null;
		this.inventory = inventory;
		this.inventoryType = inventoryType;
		this.playerUUID = uuid;
	}
	
	public Machine(Location location, String type) {
		this.multiBlockLocation = location;
		this.inventoryType = type;
		this.playerUUID = null;
	}
	
	public Machine(UUID uuid, String type) {
		this.playerUUID = uuid;
		this.inventoryType = type;
		this.multiBlockLocation = null;
	}
	
	public void saveMachine() {
		File file = new File("PluginData/MachineData.json");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(gson.toJson(this.AsString));
			writer.flush();
			writer.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void saveMachineList(ArrayList<Machine> array) {
		File file = new File("PluginData/MachineData.json");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		ArrayList<ArrayList> out = new ArrayList<>();
		for(Machine m : array) {
			out.add((ArrayList)m.AsString);
		}
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(gson.toJson(out));
			writer.flush();
			writer.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void loadMachineList(ArrayList<Machine> out) throws JsonSyntaxException, JsonIOException, IOException, ClassNotFoundException {	
		File file = new File("PluginData/MachineData.json");
		
		if(file!=null) {
			
			Gson gson = new Gson();
			Object readObject = gson.fromJson(new FileReader(file), Object.class);
			
			
			if(!(readObject instanceof ArrayList)) {
				throw new IOException("Data is not a ArrayList");
				
			}
			ArrayList<ArrayList> array = (ArrayList<ArrayList>) readObject;
			
			for(ArrayList arr : array) {
				ArrayList<String> s = (ArrayList<String>) arr.get(1);
				Inventory inv = Bukkit.createInventory(null, s.size(), (String) arr.get(2));
				ByteArrayInputStream in = null;
				BukkitObjectInputStream is = null;
				for(int i=0; i<s.size(); i++) {
					if(!s.get(i).equals("null")) {
						if(s.get(i).equals("Blank")) {
							ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
							ItemMeta meta = item.getItemMeta();
							meta.setDisplayName(" ");
							item.setItemMeta(meta);
							inv.setItem(i, item);
						}
						else {
							try {
								in = new ByteArrayInputStream(Base64.getDecoder().decode(s.get(i)));
								is = new BukkitObjectInputStream(in);
								ItemStack item = (ItemStack) is.readObject();
								inv.setItem(i, item);
							}catch (Exception e) {
								
							}
						}
					}
					else {
						inv.setItem(i, new ItemStack(Material.AIR));
					}
				}
				
				if(!((String)arr.get(0)).equals("noLocation")) {
					String[] loc = ((String)arr.get(0)).split("/");
					Location l = new Location(Bukkit.getServer().getWorld(loc[0]), Integer.parseInt(loc[1]), Integer.parseInt(loc[2]), Integer.parseInt(loc[3]));
					if (((String)arr.get(2)).equals("FluidInfuser")) {
						Inventory outInv = Bukkit.createInventory(null, inv.getSize(), ChatColor.AQUA+"Fluid Infuser");
						outInv.setContents(inv.getContents());
						FluidInfuserInventory.inventory.put(l, outInv);
						Machine machine = new Machine(l, outInv, (String)arr.get(2));
						out.add(machine);
					}
					else if (((String)arr.get(2)).equals("OilProcessor")) {
						Inventory outInv = Bukkit.createInventory(null, inv.getSize(), ChatColor.AQUA+"Oil Processor");
						outInv.setContents(inv.getContents());
						OilProcessorInventory.inventory.put(l, outInv);
						Machine machine = new Machine(l, outInv, (String)arr.get(2));
						out.add(machine);
					}
					else if (((String)arr.get(2)).equals("OilExtractor")) {
						Inventory outInv = Bukkit.createInventory(null, inv.getSize(), ChatColor.AQUA+"Oil Extractor");
						outInv.setContents(inv.getContents());
						OilExtractorInventory.inventory.put(l, outInv);
						Machine machine = new Machine(l, outInv, (String)arr.get(2));
						out.add(machine);
					}

					else if (((String)arr.get(2)).equals("LiquidMixer")) {
						Inventory outInv = Bukkit.createInventory(null, inv.getSize(), ChatColor.AQUA+"Liquid Mixer");
						outInv.setContents(inv.getContents());
						LiquidMixerInventory.inventory.put(l, outInv);
						Machine machine = new Machine(l, outInv, (String)arr.get(2));
						out.add(machine);
					}

					else if (((String)arr.get(2)).equals("Solidifer")) {
						Inventory outInv = Bukkit.createInventory(null, inv.getSize(), ChatColor.AQUA+"Solidifer");
						outInv.setContents(inv.getContents());
						SolidiferInventory.inventory.put(l, outInv);
						Machine machine = new Machine(l, outInv, (String)arr.get(2));
						out.add(machine);
					}

					else if (((String)arr.get(2)).equals("Melter")) {
						Inventory outInv = Bukkit.createInventory(null, inv.getSize(), ChatColor.AQUA+"Melter");
						outInv.setContents(inv.getContents());
						MelterInventory.inventory.put(l, outInv);
						Machine machine = new Machine(l, outInv, (String)arr.get(2));
						out.add(machine);
					}

					else if (((String)arr.get(2)).equals("VoidOreMiner")) {
						Inventory outInv = Bukkit.createInventory(null, inv.getSize(), ChatColor.AQUA+"Void Ore Miner");
						outInv.setContents(inv.getContents());
						VoidOreMinerInventory.inventory.put(l, outInv);
						Machine machine = new Machine(l, outInv, (String)arr.get(2));
						out.add(machine);
					}
					else if (((String)arr.get(2)).equals("Industrial Saw")) {
						Inventory outInv = Bukkit.createInventory(null, inv.getSize(), ChatColor.AQUA+"Industrial Saw");
						outInv.setContents(inv.getContents());
						IndustrialSawInventory.inventory.put(l, outInv);
						Machine machine = new Machine(l, outInv, (String)arr.get(2));
						out.add(machine);
					}
					else if (((String)arr.get(2)).equals("EnchantedWorkBench")) {
						Inventory outInv = Bukkit.createInventory(null, inv.getSize(), ChatColor.AQUA+"Enchanted WorkBench");
						outInv.setContents(inv.getContents());
						EnchantedWorkBenchInventory.inventory.put(l, outInv);
						Machine machine = new Machine(l, outInv, (String)arr.get(2));
						out.add(machine);
					}
					else if (((String)arr.get(2)).equals("Weaponry")) {
						Inventory outInv = Bukkit.createInventory(null, inv.getSize(), ChatColor.AQUA+"Weaponry");
						outInv.setContents(inv.getContents());
						WeaponryInventory.inventory.put(l, outInv);
						Machine machine = new Machine(l, outInv, (String)arr.get(2));
						out.add(machine);
					}
					else if (((String)arr.get(2)).equals("AutomaticGarden")) {
						Inventory outInv = Bukkit.createInventory(null, inv.getSize(), ChatColor.AQUA+"Automatic Garden");
						outInv.setContents(inv.getContents());
						AutomaticGardenInventory.inventory.put(l, outInv);
						Machine machine = new Machine(l, outInv, (String)arr.get(2));
						out.add(machine);
					}
				}
				else {
					if (((String)arr.get(2)).equals("FissionReactor")) {
						Inventory outInv = Bukkit.createInventory(null, inv.getSize(), ChatColor.AQUA+"Fission Reactor");
						outInv.setContents(inv.getContents());
						FissionReactorInventory.inventory.put(UUID.fromString((String)arr.get(3)), outInv);
						FissionReactorEvent.FissionReactorActivness.put(UUID.fromString((String)arr.get(3)), false);
						FissionReactorEvent.FissionReactorCounter.put(UUID.fromString((String)arr.get(3)), 0);
						Machine machine = new Machine(UUID.fromString((String)arr.get(3)), outInv, (String)arr.get(2));
						out.add(machine);
					}
				}
			}
			
		}
	}
	public void loadMachine() throws JsonSyntaxException, JsonIOException, IOException, ClassNotFoundException {	
		File file = new File("PluginData/MachineData.json");
		
		if(file!=null) {
			
			Gson gson = new Gson();
			Object readObject = gson.fromJson(new FileReader(file), Object.class);

			if(!(readObject instanceof ArrayList)) {
				throw new IOException("Data is not a ArrayList");
			}
			ArrayList array = (ArrayList) readObject;
			ArrayList<String> s = (ArrayList) array.get(1);
			Inventory inv = Bukkit.createInventory(null, s.size(), (String) array.get(2));
			ByteArrayInputStream in = null;
			BukkitObjectInputStream is = null;
			for(int i=0; i<s.size(); i++) {
				if(s.get(i) != "null") {
					try {
						in = new ByteArrayInputStream(Base64.getDecoder().decode(s.get(i)));
						is = new BukkitObjectInputStream(in);
						ItemStack item = (ItemStack) is.readObject();
						inv.setItem(i, item);
					}catch (Exception e) {
						
					}
				}
				else {
					inv.setItem(i, new ItemStack(Material.AIR));
				}
				
			}
			this.setInventory(inv);
			if(!((String)array.get(0)).equals("noLocation")) {
				String[] loc = ((String)array.get(0)).split("/");
				Location l = new Location(Bukkit.getServer().getWorld(loc[0]), Integer.parseInt(loc[1]), Integer.parseInt(loc[2]), Integer.parseInt(loc[3]));
				this.setMultiBlockLocation(l);
				this.setPlayerUUID(null);
			}
			else {
				this.setPlayerUUID(UUID.fromString((String)array.get(3)));
				this.setMultiBlockLocation(null);
			}
		}
	}

	public Location getMultiBlockLocation() {
		return multiBlockLocation;
	}

	public void setMultiBlockLocation(Location multiBlockLocation) {
		this.multiBlockLocation = multiBlockLocation;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public UUID getPlayerUUID() {
		return playerUUID;
	}

	public void setPlayerUUID(UUID playerUUID) {
		this.playerUUID = playerUUID;
	}

	public String getInventoryType() {
		return inventoryType;
	}

	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}
	public boolean MachineInArray(ArrayList<Machine> list) {
		if(this.getPlayerUUID() != null) {
			for(Machine in : list) {
				if(in.getInventoryType().equals(this.getInventoryType())) {
					if(in.getPlayerUUID().equals(this.getPlayerUUID())) {
						return true;
					}
				}
			}
		}
		else if(this.getMultiBlockLocation() != null) {
			for(Machine in : list) {
				if(in.getInventoryType().equals(this.getInventoryType())) {
					if(in.getMultiBlockLocation().equals(this.getMultiBlockLocation())) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static void UpdateMachineInventories() {
		ArrayList<Machine> machineList = Main.MachineList;
		for(Machine m : machineList) {
			Inventory inv = m.getInventory();
			ArrayList<String> inventoryAsString = new ArrayList<>();
			for(int i=0; i<inv.getSize(); i++) {
				ItemStack item = inv.getContents()[i];
				String temp = "";
				if(item != null) {
					try {
						ByteArrayOutputStream io = new ByteArrayOutputStream();
						BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);
						
						os.writeObject(item);
						os.flush();
						
						temp = Base64.getEncoder().encodeToString(io.toByteArray());
					}catch (Exception e) {
						
					}
					
				}
				else {
					temp = "null";
				}
				inventoryAsString.add(temp);
			}
			m.AsString.set(1, inventoryAsString);
			
		}
	}
	
	public static void DeleteMachineFromList(Machine machine) {
		for(Machine m : Main.MachineList) {
			if(m.playerUUID == null) {
				if(m.multiBlockLocation.equals(machine.multiBlockLocation)) {
					if(m.inventoryType.equals(machine.inventoryType)) {
						Main.MachineList.remove(m);
						return;
					}
				}
			}
			else {
				if(m.playerUUID.equals(machine.playerUUID)) {
					if(m.inventoryType.equals(machine.inventoryType)) {
						Main.MachineList.remove(m);
						return;
					}
				}
			}
		}
	}
}
