package me.notbestlord.Plugin.dataManagment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.notbestlord.Plugin.Main;
import me.notbestlord.Plugin.items.ItemManager;
import me.notbestlord.Plugin.items.TalismanManager;

public class RedstoneFluxDataManager {
	
	private HashMap<UUID, Integer> maxRedstoneFlux = new HashMap<UUID,Integer>();
	private HashMap<UUID, Integer> RedstoneFlux = new HashMap<UUID,Integer>();
	
	public Main plugin;
	
	public RedstoneFluxDataManager(Main plugin) {
		this.plugin = plugin;
	}
	
	public void saveMaxRedstoneFlux() throws FileNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			File file = new File("PluginData/maxRedstoneFlux.dat");
			
			ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
			
			if(maxRedstoneFlux.get(p.getUniqueId()) != null) {
				maxRedstoneFlux.put(p.getUniqueId(), maxRedstoneFlux.get(p.getUniqueId()));
			}
			
			try {
				output.writeObject(maxRedstoneFlux);
				output.flush();
				output.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public void loadMaxRedstoneFlux() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File("PluginData/maxRedstoneFlux.dat");
		if(file!=null) {
			
			ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
			Object readObject = input.readObject();
			input.close();
			
			if(!(readObject instanceof HashMap)) {
				throw new IOException("Data is not a hashmap");
			}
			
			maxRedstoneFlux = (HashMap<UUID, Integer>)readObject;
			
			for(UUID key : maxRedstoneFlux.keySet()) {
				maxRedstoneFlux.put(key, maxRedstoneFlux.get(key));
			}
		}
	}
	
	public void saveRedstoneFlux() throws FileNotFoundException, IOException {
		for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
			File file = new File("PluginData/RedstoneFlux.dat");
			
			ObjectOutputStream output = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
			
			if(RedstoneFlux.get(p.getUniqueId()) != null) {
				RedstoneFlux.put(p.getUniqueId(), RedstoneFlux.get(p.getUniqueId()));
			}
			
			try {
				output.writeObject(RedstoneFlux);
				output.flush();
				output.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	public void loadRedstoneFlux() throws FileNotFoundException, IOException, ClassNotFoundException {
		File file = new File("PluginData/RedstoneFlux.dat");
		if(file!=null) {
			
			ObjectInputStream input = new ObjectInputStream(new GZIPInputStream(new FileInputStream(file)));
			Object readObject = input.readObject();
			input.close();
			
			if(!(readObject instanceof HashMap)) {
				throw new IOException("Data is not a hashmap");
			}
			
			RedstoneFlux = (HashMap<UUID, Integer>)readObject;
			
			for(UUID key : RedstoneFlux.keySet()) {
				RedstoneFlux.put(key, RedstoneFlux.get(key));
			}
		}
	}
	
	
	public void setMaxRedstoneFlux(Player player, int MaxRedstoneFlux) {
		maxRedstoneFlux.put(player.getUniqueId(), MaxRedstoneFlux);
	}
	
	public void setRedstoneFlux(Player player, int redstoneFlux) {
		RedstoneFlux.put(player.getUniqueId(), redstoneFlux);
	}
	
	public int getMaxRedstoneFlux(Player player) {
		if(maxRedstoneFlux.get(player.getUniqueId()) != null) {
			return maxRedstoneFlux.get(player.getUniqueId());
		}else {
			return 0;
		}
	}
	public int getRedstoneFlux(Player player) {
		if(RedstoneFlux.get(player.getUniqueId()) != null) {
			return RedstoneFlux.get(player.getUniqueId());
		}else {
			return 0;
		}
	}
	public boolean reduceRedstoneFlux(Player player, int reduce) {
		if(RedstoneFlux.get(player.getUniqueId()) != null && maxRedstoneFlux.get(player.getUniqueId()) != null) {
			int RF = RedstoneFlux.get(player.getUniqueId());
			if(RF<reduce) {
				return false;
			}else {
				RedstoneFlux.put(player.getUniqueId(), RF-reduce);
				return true;
			}
		}
		else {
			return false;
		}
	}
	public void increaseRedstoneFlux(Player player, int increase) {
		if(RedstoneFlux.get(player.getUniqueId()) != null && maxRedstoneFlux.get(player.getUniqueId()) != null) {
			int maxRF = maxRedstoneFlux.get(player.getUniqueId());
			int RF = RedstoneFlux.get(player.getUniqueId());
			if(RF+increase>maxRF) {
				RedstoneFlux.put(player.getUniqueId(), maxRedstoneFlux.get(player.getUniqueId()));
			}else {
				RedstoneFlux.put(player.getUniqueId(),RedstoneFlux.get(player.getUniqueId())+increase);
			}
		}
		
	}
	
	public void maxRedstoneFluxCalculate(Player player) {
		int maxRF=0;
		if(ItemManager.doesContainItemID(player, "gargantuanbattery")) {
			for(int i=0; i<player.getInventory().getSize();i++) {
				if(player.getInventory().getItem(i) != null) {
					if(player.getInventory().getItem(i).hasItemMeta()) {
						if(player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
							if(player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("gargantuanbattery")) {
								maxRF+=100000*player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "batteries"), PersistentDataType.INTEGER);
								if(getRedstoneFlux(player) != 0) {
									ItemStack item = player.getInventory().getItem(i);
									ItemMeta meta = item.getItemMeta();
									meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER, getRedstoneFlux(player));
									item.setItemMeta(meta);
									player.getInventory().setItem(i, item);
								}
								if(getMaxRedstoneFlux(player) == 0 && getRedstoneFlux(player) == 0) {
									setMaxRedstoneFlux(player, maxRF);
									setRedstoneFlux(player, player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER));
								}
								break;
							}
						}
					}
				}
			}
		}
		else if(ItemManager.doesContainItemID(player, "superbattery")) {
			for(int i=0; i<player.getInventory().getSize();i++) {
				if(player.getInventory().getItem(i) != null) {
					if(player.getInventory().getItem(i).hasItemMeta()) {
						if(player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
							if(player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("superbattery")) {
								maxRF+=2500000;
								if(getRedstoneFlux(player) != 0) {
									ItemStack item = player.getInventory().getItem(i);
									ItemMeta meta = item.getItemMeta();
									meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER, getRedstoneFlux(player));
									item.setItemMeta(meta);
									player.getInventory().setItem(i, item);
								}
								if(getMaxRedstoneFlux(player) == 0 && getRedstoneFlux(player) == 0) {
									setMaxRedstoneFlux(player, maxRF);
									setRedstoneFlux(player, player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER));
								}
								break;
							}
						}
					}
				}
			}
		}
		else if(ItemManager.doesContainItemID(player, "xtrabattery")) {
			for(int i=0; i<player.getInventory().getSize();i++) {
				if(player.getInventory().getItem(i) != null) {
					if(player.getInventory().getItem(i).hasItemMeta()) {
						if(player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
							if(player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("xtrabattery")) {
								maxRF+=1000000;
								if(getRedstoneFlux(player) != 0) {
									ItemStack item = player.getInventory().getItem(i);
									ItemMeta meta = item.getItemMeta();
									meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER, getRedstoneFlux(player));
									item.setItemMeta(meta);
									player.getInventory().setItem(i, item);
								}
								if(getMaxRedstoneFlux(player) == 0 && getRedstoneFlux(player) == 0) {
									setMaxRedstoneFlux(player, maxRF);
									setRedstoneFlux(player, player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER));
								}
								break;
							}
						}
					}
				}
			}
		}
		else if(ItemManager.doesContainItemID(player, "largebattery")) {
			for(int i=0; i<player.getInventory().getSize();i++) {
				if(player.getInventory().getItem(i) != null) {
					if(player.getInventory().getItem(i).hasItemMeta()) {
						if(player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
							if(player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("largebattery")) {
								maxRF+=100000;
								if(getRedstoneFlux(player) != 0) {
									ItemStack item = player.getInventory().getItem(i);
									ItemMeta meta = item.getItemMeta();
									meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER, getRedstoneFlux(player));
									item.setItemMeta(meta);
									player.getInventory().setItem(i, item);
								}
								if(getMaxRedstoneFlux(player) == 0 && getRedstoneFlux(player) == 0) {
									setMaxRedstoneFlux(player, maxRF);
									setRedstoneFlux(player, player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER));
								}
								break;
							}
						}
					}
				}
			}
		}
		else if(ItemManager.doesContainItemID(player, "mediumbattery")) {
			for(int i=0; i<player.getInventory().getSize();i++) {
				if(player.getInventory().getItem(i) != null) {
					if(player.getInventory().getItem(i).hasItemMeta()) {
						if(player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
							if(player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("mediumbattery")) {
								maxRF+=10000;
								if(getRedstoneFlux(player) != 0) {
									ItemStack item = player.getInventory().getItem(i);
									ItemMeta meta = item.getItemMeta();
									meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER, getRedstoneFlux(player));
									item.setItemMeta(meta);
									player.getInventory().setItem(i, item);
								}
								if(getMaxRedstoneFlux(player) == 0 && getRedstoneFlux(player) == 0) {
									setMaxRedstoneFlux(player, maxRF);
									setRedstoneFlux(player, player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER));
								}
								break;
							}
						}
					}
				}
			}
		}
		else if(ItemManager.doesContainItemID(player, "smallbattery")) {
			for(int i=0; i<player.getInventory().getSize();i++) {
				if(player.getInventory().getItem(i) != null) {
					if(player.getInventory().getItem(i).hasItemMeta()) {
						if(player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING)) {
							if(player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "ItemID"), PersistentDataType.STRING).equals("smallbattery")) {
								maxRF+=1000;
								if(getRedstoneFlux(player) != 0) {
									ItemStack item = player.getInventory().getItem(i);
									ItemMeta meta = item.getItemMeta();
									meta.getPersistentDataContainer().set(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER, getRedstoneFlux(player));
									item.setItemMeta(meta);
									player.getInventory().setItem(i, item);
								}
								if(getMaxRedstoneFlux(player) == 0 && getRedstoneFlux(player) == 0) {
									setMaxRedstoneFlux(player, maxRF);
									setRedstoneFlux(player, player.getInventory().getItem(i).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Main.getMain(), "redstoneflux"), PersistentDataType.INTEGER));
								}
								break;
							}
						}
					}
				}
			}
		}
		setMaxRedstoneFlux(player, maxRF);
	}
	
	
	
	public void RedstoneFluxCalculate(Player player) {
		int RF=0;
		if(player.getWorld().getTime()>0 && player.getWorld().getTime()<12300) {
			if(ItemManager.doesContainItem(player, TalismanManager.SmallSolarArray)) {
				RF+=250;
			}
			else if(ItemManager.doesContainItem(player, TalismanManager.LargeSolarPanel)) {
				RF+=150;
			}
			else if(ItemManager.doesContainItem(player, TalismanManager.MediumSolarPanel)) {
				RF+=50;
			}
			else if(ItemManager.doesContainItem(player, TalismanManager.SmallSolarPanel)) {
				RF+=2;
			}
		}
		increaseRedstoneFlux(player, RF);
		
	}
	
	public void createBoard(Player player) {
		ScoreboardManager Manager = Bukkit.getScoreboardManager();
		Scoreboard board = Manager.getNewScoreboard();
		Objective obj = board.registerNewObjective("Redstone Flux", "dummy", "Redstone Flux");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		Score maxRF = obj.getScore("Max Redstone Flux");
		maxRF.setScore(0);
		Score RF = obj.getScore("Redstone Flux");
		RF.setScore(0);
		player.setScoreboard(board);
	}
	
	public void updateBoard(Player player) {
		player.getScoreboard().getObjective("Redstone Flux").getScore("Max Redstone Flux").setScore(getMaxRedstoneFlux(player));
		player.getScoreboard().getObjective("Redstone Flux").getScore("Redstone Flux").setScore(getRedstoneFlux(player));
	}
	
}
