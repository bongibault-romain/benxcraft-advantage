package fr.benxcraft.advantage.items.armors;

import fr.benxcraft.advantage.items.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public abstract class ArmorItem extends Item {

    public boolean isEquipped(Player player) {
        ItemStack helmet = player.getInventory().getHelmet();
        ItemStack chestplate = player.getInventory().getChestplate();
        ItemStack leggings = player.getInventory().getLeggings();
        ItemStack boots = player.getInventory().getBoots();

        return (helmet != null && this.isSimilar(helmet))
                || (chestplate != null && this.isSimilar(chestplate))
                || (leggings != null && this.isSimilar(leggings))
                || (boots != null && this.isSimilar(boots));
    }
}
