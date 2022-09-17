package fr.benxcraft.advantage.items.eatable;


import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;

import java.util.List;

public abstract class PotionItem extends EatableItem {

    @Override
    public ItemStack getItemStack() {
        ItemStack itemStack = super.getItemStack();

        ItemMeta itemMeta = itemStack.getItemMeta();

        if(itemMeta instanceof PotionMeta) {
            this.getPotionEffects().forEach(potionEffect -> ((PotionMeta) itemMeta).addCustomEffect(potionEffect, true));
        }

        itemStack.setItemMeta(itemMeta);
        
        return itemStack;
    }

    @Override
    public Material getMaterial() {
        return Material.POTION;
    }

    public abstract List<PotionEffect> getPotionEffects();

    @Override
    protected void handleConsume(Player player, PlayerItemConsumeEvent event) {
        this.getPotionEffects().forEach(player::addPotionEffect);

        if(!player.getGameMode().equals(GameMode.CREATIVE))
            event.setItem(null);
    }
}
