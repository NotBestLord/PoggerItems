package me.notbestlord.Plugin.events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Container;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import me.notbestlord.Plugin.Main;
import net.minecraft.world.level.block.entity.TileEntity;

public class BoxEvent implements Listener{
	
	private static HashMap<Integer, ArrayList> storedBlocks = new HashMap<Integer, ArrayList>();
	
	@EventHandler
	private void onRightClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
				if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))
					return;
				if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("box")) {
					if(event.getClickedBlock().getType().equals(Material.BEDROCK) || event.getClickedBlock().getType().equals(Material.BARRIER)  || event.getClickedBlock().getType().equals(Material.END_PORTAL_FRAME) || !event.getClickedBlock().getState().getClass().getName().endsWith("CraftBlockState")) {
						return;
					}
					ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
					if(meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "isStoring"), PersistentDataType.INTEGER) == -1) {
						int j=-1;
						for(int i : storedBlocks.keySet()) {
							if(storedBlocks.get(i).isEmpty()) {
								j=i;
								break;
							}
						}
						if(j == -1) {
							j = storedBlocks.size();
						}
						storedBlocks.put(j, new ArrayList(Arrays.asList(event.getClickedBlock().getType(), event.getClickedBlock().getBlockData())) );
						List<String> lore = new ArrayList<>();
						lore.add("§7Can store a single block.");
						lore.add("§7Storing "+event.getClickedBlock().getType().toString().toLowerCase().replace("_", " ")+".");
						meta.setLore(lore);
						meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "isStoring"), PersistentDataType.INTEGER, j);
						event.getClickedBlock().setType(Material.AIR);
						event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
						event.setCancelled(true);
					}
				}
			}
		}
	}
	@EventHandler
	private void onBlockPlace(BlockPlaceEvent event) {
		if(event.getPlayer().getInventory().getItemInMainHand() != null && event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
			if(!event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING))
				return;
			if(event.getPlayer().getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("box")) {
				ItemMeta meta = event.getPlayer().getInventory().getItemInMainHand().getItemMeta();
				if(meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "isStoring"), PersistentDataType.INTEGER) != -1) {
					int i = meta.getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "isStoring"), PersistentDataType.INTEGER);
					meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "isStoring"), PersistentDataType.INTEGER, -1);
					List<String> lore = new ArrayList<>();
					lore.add("§7Can store a single block.");
					lore.add("§7Storing nothing.");
					meta.setLore(lore);
					event.getBlockPlaced().setType((Material)storedBlocks.get(i).get(0));
					event.getBlockPlaced().setBlockData((BlockData)storedBlocks.get(i).get(1));
					event.getPlayer().getInventory().getItemInMainHand().setItemMeta(meta);
					storedBlocks.put(i, new ArrayList());
				}
			}
		}
	}
	
	public static void saveStoredBlocks() throws FileNotFoundException, IOException {
		File file = new File("PluginData/boxedBlocks.json");
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		Hashtable<Integer, ArrayList<String>> out = new Hashtable<Integer, ArrayList<String>>();
		for(int i=0; i<storedBlocks.size(); i++) {
			if(!storedBlocks.get(i).isEmpty()) {
				ArrayList<String> array = new ArrayList<String>();
				array.add(((Material)storedBlocks.get(i).get(0)).toString());
				array.add(((BlockData)storedBlocks.get(i).get(1)).getAsString());
				out.put(i, array);
			}
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
	
	public static void loadStoredBlocks() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File("PluginData/boxedBlocks.json");
		if(file!=null) {
			
			Gson gson = new Gson();
			Object readObject = gson.fromJson(new FileReader(file), HashMap.class);
			
			HashMap<String, ArrayList<String>> in = (HashMap<String, ArrayList<String>>) readObject;
			for(String i : in.keySet()) {
				ArrayList array = new ArrayList<>();
				int j = Integer.parseInt(i);
				array.add(Material.valueOf(((String)in.get(i).get(0))));
				array.add(Bukkit.createBlockData((String)in.get(i).get(1)));
				storedBlocks.put(j, array);
			}
			
		}
	}
}
