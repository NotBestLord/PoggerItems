package me.notbestlord.Plugin.mobs;

import java.util.Random;

import org.bukkit.entity.Enderman;
import org.bukkit.entity.Husk;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Stray;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootTables;

import me.notbestlord.Plugin.items.IngredientManager;

public class MobDropsEvent implements Listener {
	
	
	@EventHandler
    private void onEntityDeath(EntityDeathEvent event){
        if(event.getEntity() instanceof Skeleton || event.getEntity() instanceof Zombie || event.getEntity() instanceof PigZombie || event.getEntity() instanceof WitherSkeleton || event.getEntity() instanceof Husk || event.getEntity() instanceof Stray) {
            Random r = new Random();
            float chance = r.nextFloat();
            if (chance <= 0.02f) // chance of 2%
            {
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), IngredientManager.Ectoplasm);
            }
        }
        if(event.getEntity() instanceof Enderman) {
            Random r = new Random();
            float chance = r.nextFloat();
            if (chance <= 0.005f) // chance of 0.5%
            {
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), IngredientManager.EnderOrb);
            }
        }
        if(event.getEntity().hasMetadata("CustomMob")) {
        	if(event.getEntity().getMetadata("CustomMob").get(0).asString().equals("Zombie Commander")) {
        		Random r = new Random();
                float chance = r.nextFloat();
                if (chance <= 0.75f) // chance of 0.5%
                {
                    event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), IngredientManager.Ectoplasm);
                }
                chance = r.nextFloat();
                if (chance <= 0.1f) // chance of 0.5%
                {
                    event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), IngredientManager.Ectoplasm);
                }
        	}
        }
    }
	
	
}
