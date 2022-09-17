package fr.benxcraft.advantage.items.armors;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class UltimateVisionItem extends ArmorPotionEffectItem{
    @Override
    public String getIdentifier() {
        return "ultimate_vision";
    }

    @Override
    public Material getMaterial() {
        return Material.IRON_HELMET;
    }

    @Override
    public int getCustomModelData() {
        return 1;
    }

    @Override
    public String getDisplayName() {
        return "§cVision Ultime";
    }

    @Override
    public List<PotionEffect> getPotionEffects() {
        return List.of(
                new PotionEffect(PotionEffectType.NIGHT_VISION, this.getInfiniteDuration(), 0, false, false)
        );
    }
}
