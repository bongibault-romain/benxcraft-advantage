package fr.benxcraft.advantage.items.armors;

import org.bukkit.Material;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class SpeederItem extends ArmorPotionEffectItem {
    @Override
    public String getIdentifier() {
        return "speeder";
    }

    @Override
    public Material getMaterial() {
        return Material.IRON_BOOTS;
    }

    @Override
    public int getCustomModelData() {
        return 1;
    }

    @Override
    public String getDisplayName() {
        return "§cSpeeder";
    }

    @Override
    public List<PotionEffect> getPotionEffects() {
        return List.of(
                new PotionEffect(PotionEffectType.SPEED, this.getInfiniteDuration(), 1, false, false)
        );
    }
}
