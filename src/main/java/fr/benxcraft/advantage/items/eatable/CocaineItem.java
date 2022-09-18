package fr.benxcraft.advantage.items.eatable;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

public class CocaineItem extends PotionItem {
    @Override
    public int getCustomModelData() {
        return 2;
    }

    @Override
    public String getDisplayName() {
        return "§cCocaine";
    }

    @Override
    public List<PotionEffect> getPotionEffects() {
        return Arrays.asList(
                new PotionEffect(PotionEffectType.SPEED, 600, 3, false, false),
                new PotionEffect(PotionEffectType.WEAKNESS, 600, 3, false, false)
        );
    }

    @Override
    public String getIdentifier() {
        return "cocaine";
    }
}
