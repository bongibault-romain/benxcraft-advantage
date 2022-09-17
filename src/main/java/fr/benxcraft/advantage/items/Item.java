package fr.benxcraft.advantage.items;

import fr.benxcraft.advantage.Advantage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nullable;
import java.util.List;

public abstract class Item {

    /**
     * Called when the effect of the item has to by applied
     *
     * This function has to be call only when test function
     * is called before.
     */
    public abstract void handle(Player player, Event event);

    public ItemStack getItemStack() {
        ItemStack itemStack = new ItemStack(this.getMaterial(), 1);

        ItemMeta itemMeta =  itemStack.getItemMeta();

        if(itemMeta != null) {
            itemMeta.setCustomModelData(this.getCustomModelData());
            itemMeta.setDisplayName(this.getDisplayName());


            PersistentDataContainer container = itemMeta.getPersistentDataContainer();

            container.set(new NamespacedKey(Advantage.getInstance(), "identifier"), PersistentDataType.STRING, this.getIdentifier());
        }

        itemStack.setItemMeta(itemMeta);
        return  itemStack;
    }

    public abstract String getIdentifier();

    public abstract Material getMaterial();

    public abstract int getCustomModelData();

    public abstract String getDisplayName();

    public abstract boolean test(Player player, Event event);

    public boolean isSimilar(@Nullable ItemStack itemStack) {
        if(itemStack == null) return false;

        ItemMeta itemMeta = itemStack.getItemMeta();

        if(itemMeta != null) {
            PersistentDataContainer container = itemMeta.getPersistentDataContainer();

            String identifier = container.get(new NamespacedKey(Advantage.getInstance(), "identifier"), PersistentDataType.STRING);

            return this.getIdentifier().equals(identifier);
        }

        return false;
    }

    public abstract List<Class<?>> getHandlers();
}
