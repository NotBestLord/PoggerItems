package me.notbestlord.Plugin.mobs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.metadata.FixedMetadataValue;

import me.notbestlord.Plugin.Main;

public class VillagerProfessions implements Listener{
	
	@EventHandler
	private void onVillagerObtainProfession(VillagerCareerChangeEvent event) {
		if(event.getProfession().equals(Profession.CARTOGRAPHER)) {
			Random r = new Random();
            float chance = r.nextFloat();
            if (chance <= 0.1f) // chance of 10%
            {
            	List<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();
    			MerchantRecipe recipe1 = new MerchantRecipe(new ItemStack(Material.EMERALD), 12);
    			recipe1.setIngredients(Arrays.asList(new ItemStack(Material.PRISMARINE, 16)));
    			recipe1.setVillagerExperience(3);
    			recipes.add(recipe1);
    			MerchantRecipe recipe2 = new MerchantRecipe(new ItemStack(Material.SEA_LANTERN, 2), 8);
    			recipe2.setIngredients(Arrays.asList(new ItemStack(Material.EMERALD, 4)));
    			recipe2.setVillagerExperience(2);
    			recipes.add(recipe2);
    			MerchantRecipe recipe3 = new MerchantRecipe(new ItemStack(Material.EMERALD), 12);
    			recipe3.setIngredients(Arrays.asList(new ItemStack(Material.DARK_PRISMARINE, 12)));
    			recipe3.setVillagerExperience(4);
    			recipes.add(recipe3);
    			MerchantRecipe recipe4 = new MerchantRecipe(new ItemStack(Material.MAP), 4);
    			recipe4.setIngredients(Arrays.asList(new ItemStack(Material.EMERALD, 12)));
    			recipe4.setVillagerExperience(6);
    			recipes.add(recipe4);
    			MerchantRecipe recipe5 = new MerchantRecipe(new ItemStack(Material.TRIDENT), 2);
    			recipe5.setIngredients(Arrays.asList(new ItemStack(Material.EMERALD, 36)));
    			recipe5.setVillagerExperience(12);
    			recipes.add(recipe5);
    			event.getEntity().setProfession(Villager.Profession.CARTOGRAPHER);
    			event.getEntity().setRecipes(new ArrayList<MerchantRecipe>(recipes));
            }
			
		}
		else if(event.getProfession().equals(Profession.FARMER)) {
			Random r = new Random();
            float chance = r.nextFloat();
            if (chance <= 0.05f) // chance of 5%
            {
            	List<MerchantRecipe> recipes = new ArrayList<MerchantRecipe>();
    			MerchantRecipe recipe1 = new MerchantRecipe(new ItemStack(Material.EMERALD), 16);
    			recipe1.setIngredients(Arrays.asList(new ItemStack(Material.HONEYCOMB, 9)));
    			recipe1.setVillagerExperience(3);
    			recipes.add(recipe1);
    			MerchantRecipe recipe2 = new MerchantRecipe(new ItemStack(Material.HONEY_BLOCK, 2), 8);
    			recipe2.setIngredients(Arrays.asList(new ItemStack(Material.EMERALD, 15)));
    			recipe2.setVillagerExperience(1);
    			recipes.add(recipe2);
    			MerchantRecipe recipe3 = new MerchantRecipe(new ItemStack(Material.EMERALD, 2), 12);
    			recipe3.setIngredients(Arrays.asList(new ItemStack(Material.HONEY_BOTTLE, 3)));
    			recipe3.setVillagerExperience(4);
    			recipes.add(recipe3);
    			MerchantRecipe recipe4 = new MerchantRecipe(new ItemStack(Material.BEE_NEST), 4);
    			recipe4.setIngredients(Arrays.asList(new ItemStack(Material.EMERALD, 24)));
    			recipe4.setVillagerExperience(6);
    			recipes.add(recipe4);
    			MerchantRecipe recipe5 = new MerchantRecipe(new ItemStack(Material.BEE_SPAWN_EGG), 2);
    			recipe5.setIngredients(Arrays.asList(new ItemStack(Material.EMERALD, 64), new ItemStack(Material.EMERALD, 64)));
    			recipe5.setVillagerExperience(12);
    			recipes.add(recipe5);
    			event.getEntity().setProfession(Villager.Profession.FARMER);
    			event.getEntity().setRecipes(new ArrayList<MerchantRecipe>(recipes));
            }
			
		}
	}
}
