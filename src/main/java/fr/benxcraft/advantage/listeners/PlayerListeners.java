package fr.benxcraft.advantage.listeners;

import fr.benxcraft.advantage.items.Item;
import fr.benxcraft.advantage.managers.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.List;

public class PlayerListeners implements Listener {

    @EventHandler()
    public void onEatEvent(PlayerItemConsumeEvent e) {
        Player player = e.getPlayer();

        this.handleEvent(player, e);
    }


    @EventHandler()
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player player)) {
            return;
        }

        this.handleEvent(player, e);
    }


    @EventHandler()
    public void onEnchant(EnchantItemEvent e) {
        Player player = e.getEnchanter();

        this.handleEvent(player, e);
    }


    @EventHandler()
    public void onPotionEffect(EntityPotionEffectEvent e) {
        if (!(e.getEntity() instanceof Player player)) {
            return;
        }

        this.handleEvent(player, e);
    }

    @EventHandler()
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();

        player.getLastTwoTargetBlocks(null, 100);

        this.handleEvent(player, e);
    }

    @EventHandler()
    public void onPrepareEnchant(PrepareItemEnchantEvent e) {
        Player player = e.getEnchanter();

        this.handleEvent(player, e);
    }

    @EventHandler()
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        this.handleEvent(player, e);
    }

    private void handleEvent(Player player, Event event) {
        List<Item> items = ItemManager.getInstance().getItemsHandlingFor(event);

        items.forEach(item -> {
            if (item.test(player, event)) {
                item.handle(player, event);
            }
        });
    }
}
