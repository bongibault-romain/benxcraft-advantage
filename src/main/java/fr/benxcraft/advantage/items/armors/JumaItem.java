package fr.benxcraft.advantage.items.armors;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.Collections;
import java.util.List;

public class JumaItem extends ArmorItem {

    @Override
    public void handle(Player player, Event event) {
        if (event instanceof EntityDamageEvent) {
            this.handleDamage(player, (EntityDamageEvent) event);
        }
    }

    protected void handleDamage(Player player, EntityDamageEvent event) {
        player.playSound(player.getLocation(), Sound.BLOCK_SLIME_BLOCK_PLACE, 1.5F, 1.5F);
        event.setCancelled(true);
    }

    @Override
    public List<Class<?>> getHandlers() {
        return Collections.singletonList(EntityDamageEvent.class);
    }

    @Override
    public String getIdentifier() {
        return "juma";
    }

    @Override
    public Material getMaterial() {
        return Material.DIAMOND_BOOTS;
    }

    @Override
    public int getCustomModelData() {
        return 1;
    }

    @Override
    public String getDisplayName() {
        return "§cJuma";
    }

    @Override
    public boolean test(Player player, Event event) {
        if(event instanceof EntityDamageEvent) {
            return this.testDamage(player, (EntityDamageEvent) event);
        }

        return false;
    }

    protected boolean testDamage(Player player, EntityDamageEvent event) {
        return event.getCause() == EntityDamageEvent.DamageCause.FALL && this.isEquipped(player);
    }
}
