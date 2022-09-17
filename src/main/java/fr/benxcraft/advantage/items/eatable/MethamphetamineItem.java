package fr.benxcraft.advantage.items.eatable;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
                new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200, 0, false, false),
                new PotionEffect(PotionEffectType.CONFUSION, 100, 0, false, false)
        );
    }

    @Override
    public String getIdentifier() {
        return "methamphetamine";
    }
}
