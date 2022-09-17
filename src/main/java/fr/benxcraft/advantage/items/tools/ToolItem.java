package fr.benxcraft.advantage.items.tools;

import fr.benxcraft.advantage.items.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Collections;
import java.util.List;

public abstract class ToolItem extends Item {

    @Override
    public List<Class<?>> getHandlers() {
        return Collections.singletonList(BlockBreakEvent.class);
    }

    @Override
    public boolean test(Player player, Event event) {
        if(event instanceof BlockBreakEvent) {
            return this.testBreak(player, (BlockBreakEvent) event);
        }

        return false;
    }

    protected boolean testBreak(Player player, BlockBreakEvent event) {
        return this.isInHand(player) && this.getAffectedMaterials().contains(event.getBlock().getType());
    }

    @Override
    public void handle(Player player, Event event) {
        if(event instanceof BlockBreakEvent) {
            this.handleBreak(player, (BlockBreakEvent) event);
        }
    }

    protected abstract void handleBreak(Player player, BlockBreakEvent event);

    public boolean isInHand(Player player) {
        return this.isSimilar(player.getInventory().getItemInMainHand());
    }

    public abstract List<Material> getAffectedMaterials();
}
