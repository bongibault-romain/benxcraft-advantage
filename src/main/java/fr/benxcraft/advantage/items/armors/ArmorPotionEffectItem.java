package fr.benxcraft.advantage.items.armors;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockDispenseArmorEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class ArmorPotionEffectItem extends ArmorItem {
    @Override
    public List<Class<?>> getHandlers() {
        return Arrays.asList(
                PlayerDropItemEvent.class,
                InventoryClickEvent.class,
                PlayerLoginEvent.class,
                PlayerQuitEvent.class,
                PlayerInteractEvent.class,
                BlockDispenseArmorEvent.class
        );
    }

    public abstract List<PotionEffect> getPotionEffects();

    @Override
    public void handle(Player player, Event event) {
        this.updatePotionEffects(player);
    }

    @Override
    public boolean test(Player player, Event event) {
        return true;
    }

    protected void updatePotionEffects(Player player) {
        if(!this.isEquipped(player)) {
            this.getPotionEffects().forEach(potionEffect -> {
                PotionEffect playerEffect = player.getActivePotionEffects().stream().filter(playerPotionEffect -> potionEffect.getType().equals(playerPotionEffect.getType())).findFirst().orElse(null);
                
                if(playerEffect != null && playerEffect.getDuration() >= this.getInfiniteDuration() / 1000)
                    player.removePotionEffect(potionEffect.getType());
            });
        }

        if(this.isEquipped(player)) {
            this.getPotionEffects().forEach(player::addPotionEffect);
        }
    }

    protected int getInfiniteDuration() {
        return Integer.MAX_VALUE;
    }
}
