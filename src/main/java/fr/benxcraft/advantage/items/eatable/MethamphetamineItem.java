package fr.benxcraft.advantage.items.eatable;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class MethamphetamineItem extends PotionItem {
    @Override
    public int getCustomModelData() {
        return 1;
    }

    @Override
    public String getDisplayName() {
        return "§cMéthamphétamine";
    }

    @Override
    public List<PotionEffect> getPotionEffects() {
        return Arrays.asList(
                new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 900, 0, false, false),
                new PotionEffect(PotionEffectType.CONFUSION, 200, 0, false, false)
        );
    }

    @Override
    public String getIdentifier() {
        return "methamphetamine";
    }
}
