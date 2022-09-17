package fr.benxcraft.advantage.listeners;

import fr.benxcraft.advantage.Advantage;
import fr.benxcraft.advantage.items.Item;
import fr.benxcraft.advantage.managers.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseArmorEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ArmorListeners implements Listener {
    @EventHandler()
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        ItemStack itemStack = e.getItemDrop().getItemStack();

        if(this.isArmor(itemStack.getType()))
            this.handleEvent(e.getPlayer(), e);
    }

    @EventHandler()
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player player)) {
            return;
        }

        ItemStack cursor = e.getCursor();
        ItemStack currentItem = e.getCurrentItem();


        if((cursor != null && this.isArmor(cursor.getType())) || (currentItem != null && this.isArmor(currentItem.getType())))
            this.handleEvent(player, e);
    }

    @EventHandler()
    public void onPlayerLogin(PlayerLoginEvent e) {
        this.handleEvent(e.getPlayer(), e);
    }

    @EventHandler()
    public void onPlayerQuit(PlayerQuitEvent e) {
        this.handleEvent(e.getPlayer(), e);
    }

    @EventHandler()
    public void onPlayerInteract(PlayerInteractEvent e) {
        ItemStack itemStack = e.getItem();

        if(itemStack != null && this.isArmor(itemStack.getType()))
            this.handleEvent(e.getPlayer(), e);
    }

    @EventHandler()
    public void onBlockDispenseArmor(BlockDispenseArmorEvent e) {
        if (!(e.getTargetEntity() instanceof Player player)) {
            return;
        }

        this.handleEvent(player, e);
    }

    private void handleEvent(Player player, Event event) {
        Bukkit.getScheduler().runTask(Advantage.getInstance(), () -> {

            List<Item> items = ItemManager.getInstance().getItemsHandlingFor(event);

            items.forEach(item -> {
                if (item.test(player, event)) {
                    item.handle(player, event);
                }
            });
        });
    }

    private boolean isArmor(Material material) {
        List<Material> armors =  Arrays.asList(
                Material.LEATHER_BOOTS,
                Material.IRON_BOOTS,
                Material.DIAMOND_BOOTS,
                Material.GOLDEN_BOOTS,
                Material.CHAINMAIL_BOOTS,
                Material.LEATHER_LEGGINGS,
                Material.IRON_LEGGINGS,
                Material.DIAMOND_LEGGINGS,
                Material.GOLDEN_LEGGINGS,
                Material.CHAINMAIL_LEGGINGS,
                Material.LEATHER_CHESTPLATE,
                Material.IRON_CHESTPLATE,
                Material.DIAMOND_CHESTPLATE,
                Material.GOLDEN_CHESTPLATE,
                Material.CHAINMAIL_CHESTPLATE,
                Material.LEATHER_HELMET,
                Material.IRON_HELMET,
                Material.DIAMOND_HELMET,
                Material.GOLDEN_HELMET,
                Material.CHAINMAIL_HELMET
        );

        return armors.contains(material);
    }

}
