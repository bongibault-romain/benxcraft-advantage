package fr.benxcraft.advantage.items.eatable;

import org.bukkit.Material;
import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class CannabisItem extends PotionItem {

    public int getAddedLevels() {
        return 2;
    }

    @Override
    public int getCustomModelData() {
        return 2;
    }

    @Override
    public String getDisplayName() {
        return "§cCannabis";
    }

    @Override
    public List<Class<?>> getHandlers() {
        return Arrays.asList(
                PlayerItemConsumeEvent.class,
                PrepareItemEnchantEvent.class,
                EnchantItemEvent.class
        );
    }

    @Override
    public void handle(Player player, Event event) {
        if(event instanceof PrepareItemEnchantEvent) {
            this.handlePrepareEnchant(player, (PrepareItemEnchantEvent) event);
            return;
        }

        if(event instanceof EnchantItemEvent) {
            this.handleEnchant(player, (EnchantItemEvent) event);
            return;
        }

        super.handle(player, event);
    }

    protected void handlePrepareEnchant(Player player, PrepareItemEnchantEvent event) {
        EnchantmentOffer[] offers = event.getOffers();

        for (int i = 0; i < event.getOffers().length; i++) {
            if(offers[i] != null)
                offers[i].setEnchantmentLevel(offers[i].getEnchantmentLevel() + this.getAddedLevels());
        }
    }

    protected void handleEnchant(Player player, EnchantItemEvent event) {
        event.getEnchantsToAdd().entrySet().forEach(enchantmentIntegerEntry -> enchantmentIntegerEntry.setValue(enchantmentIntegerEntry.getValue() + this.getAddedLevels()));
    }

    @Override
    public boolean test(Player player, Event event) {
        if(event instanceof PrepareItemEnchantEvent) {
            return this.testPrepareEnchant(player, event);
        }

        if(event instanceof EnchantItemEvent) {
            return this.testEnchant(player, event);
        }


        return super.test(player, event);
    }

    protected boolean testPrepareEnchant(Player player, Event event) {
        return this.isLucky(player);
    }

    protected boolean testEnchant(Player player, Event event) {
        return this.isLucky(player);
    }

    public boolean isLucky(Player player) {
        return player.getActivePotionEffects().stream().anyMatch(potionEffect -> potionEffect.getType().equals(PotionEffectType.LUCK));
    }

    @Override
    public String getIdentifier() {
        return "cannabis";
    }

    @Override
    public List<PotionEffect> getPotionEffects() {
        return Arrays.asList(
                new PotionEffect(PotionEffectType.LUCK, 1600, 0, false, false),
                new PotionEffect(PotionEffectType.HUNGER, 400, 1, false, false)
        );
    }
}
