package fr.benxcraft.advantage.items.eatable;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemConsumeEvent;
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
                new PotionEffect(PotionEffectType.SPEED, 200, 3, false, false),
                new PotionEffect(PotionEffectType.WEAKNESS, 100, 3, false, false)
        );
    }

    @Override
    public String getIdentifier() {
        return "cocaine";
    }
}
