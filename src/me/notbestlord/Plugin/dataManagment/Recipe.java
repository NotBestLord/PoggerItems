package me.notbestlord.Plugin.dataManagment;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Recipe {
	public static class EnchantedWorkbenchRecipe{
		
		private ArrayList<ItemStack> Ingredients;
		private ArrayList<Integer> IngredientAmounts;
		private String RecipeID;
		private ItemStack Result;

		public EnchantedWorkbenchRecipe(String RecipeID, ItemStack Result) {
			this.setRecipeID(RecipeID);
			Ingredients = new ArrayList<>();
			IngredientAmounts = new ArrayList<>();
			this.Result = new ItemStack(Result);
		}
		
		public EnchantedWorkbenchRecipe(String RecipeID, ArrayList<ItemStack> Ingredients, ArrayList<Integer> IngredientAmounts, ItemStack Result) {
			this.setRecipeID(RecipeID);
			this.Ingredients = Ingredients;
			this.IngredientAmounts = IngredientAmounts;
			this.Result = new ItemStack(Result);
		}
		
		public EnchantedWorkbenchRecipe clone() {
			return new EnchantedWorkbenchRecipe(this.RecipeID, new ArrayList<ItemStack>(this.Ingredients), new ArrayList<Integer>(this.IngredientAmounts), new ItemStack(this.Result));
		}
		
		public String getRecipeID() {
			return RecipeID;
		}
		public void setRecipeID(String recipeID) {
			this.RecipeID = recipeID;
		}
		public ArrayList<ItemStack> getIngredients() {
			return Ingredients;
		}
		public void setIngredients(ArrayList<ItemStack> ingredients) {
			this.Ingredients = ingredients;
		}
		public ArrayList<Integer> getIngredientAmounts() {
			return IngredientAmounts;
		}
		public void setIngredientAmounts(ArrayList<Integer> ingredientAmounts) {
			this.IngredientAmounts = ingredientAmounts;
		}
		public ItemStack getResult() {
			return new ItemStack(Result);
		}
		public void setResult(ItemStack result) {
			this.Result = result;
		}
		
		public void addIngredient(Object object, int amount) {
			if(object instanceof Material) {
				Material material = (Material) object;
				ItemStack item = new ItemStack(material);
				ItemMeta meta = item.getItemMeta();
				String[] name = material.toString().toLowerCase().split("_");
				String itemName = "";
				for(String s : name) {
					itemName += s.toUpperCase().charAt(0) +""+ s.substring(1) + " ";
				}
				itemName = itemName.substring(0, itemName.length()-1);
				meta.setDisplayName(ChatColor.WHITE+itemName);
				item.setItemMeta(meta);
				Ingredients.add(item);
				IngredientAmounts.add(amount);
			}
			else if(object instanceof ItemStack) {
				Ingredients.add((ItemStack) object);
				IngredientAmounts.add(amount);
			}
		}
		public void replaceIngredient(int slot, Object object, int amount) {
			if(Ingredients.size() > slot) {
				return;
			}
			if(object instanceof Material) {
				Material material = (Material) object;
				ItemStack item = new ItemStack(material);
				ItemMeta meta = item.getItemMeta();
				String[] name = material.toString().toLowerCase().split("_");
				String itemName = "";
				for(String s : name) {
					itemName += s.toUpperCase().charAt(0) +""+ s.substring(1) + " ";
				}
				itemName = itemName.substring(0, itemName.length()-1);
				meta.setDisplayName(ChatColor.WHITE+itemName);
				item.setItemMeta(meta);
				Ingredients.set(slot, item);
				IngredientAmounts.set(slot, amount);
			}
			else if(object instanceof ItemStack) {
				Ingredients.set(slot, (ItemStack) object);
				IngredientAmounts.set(slot, amount);
			}
		}
		public void removeIngredient(int slot) {
			if(Ingredients.size() > slot) {
				return;
			}
			Ingredients.remove(slot);
			IngredientAmounts.remove(slot);
		}
	}
	
	
	
}


