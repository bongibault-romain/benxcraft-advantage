package fr.benxcraft.advantage.items.tools;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.*;

public class UdarItem extends ToolItem {
    @Override
    public void handleBreak(Player player, BlockBreakEvent event) {
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        ItemMeta itemMeta = itemInHand.getItemMeta();

        boolean isDamageable = itemMeta instanceof Damageable;

        int durability = isDamageable
                ? itemInHand.getType().getMaxDurability() - ((Damageable) itemMeta).getDamage()
                : 200;

        int brokenBlocks = this.breakBlock(event.getBlock(), event.getBlock().getLocation(), Math.min(200, durability));

        if(isDamageable && !player.getGameMode().equals(GameMode.CREATIVE)) {
            ((Damageable) itemMeta).setDamage(((Damageable) itemMeta).getDamage() + brokenBlocks);
            itemInHand.setItemMeta(itemMeta);

            if(durability - brokenBlocks <= 0) {
                itemInHand.setAmount(0);
            }
        }

    }

    private int breakBlock(Block block, Location startLocation, int maxBreak) {
        if (maxBreak == 0) return 0;

        if (block.getLocation().distance(startLocation) >= 50) return 0;

        Location blockLocation = block.getLocation();
        World world = blockLocation.getWorld();

        if(world == null) {
            return 0;
        }

        int blockX = blockLocation.getBlockX();
        int blockY = blockLocation.getBlockY();
        int blockZ = blockLocation.getBlockZ();

        block.breakNaturally();

        int brokenBlocks = 1;

        for (int x = blockX - 1; x <= blockX + 1; x++) {
            for (int y = blockY - 1; y <= blockY + 1; y++) {
                for (int z = blockZ - 1; z <= blockZ + 1; z++) {
                    Block nextBlock = world.getBlockAt(x, y, z);

                    if(this.getAffectedMaterials().contains(nextBlock.getType()))
                        brokenBlocks += this.breakBlock(nextBlock, startLocation, maxBreak - brokenBlocks);
                }
            }
        }

        return brokenBlocks;
    }

    @Override
    public String getIdentifier() {
        return "udar";
    }

    @Override
    public Material getMaterial() {
        return Material.DIAMOND_AXE;
    }

    @Override
    public int getCustomModelData() {
        return 1;
    }

    @Override
    public String getDisplayName() {
        return "§cUdar";
    }

    @Override
    public List<Material> getAffectedMaterials() {
        return new LinkedList<>(Arrays.asList(
                Material.OAK_LOG,
                Material.DARK_OAK_LOG,
                Material.BIRCH_LOG,
                Material.JUNGLE_LOG,
                Material.ACACIA_LOG,
                Material.MANGROVE_LOG,
                Material.SPRUCE_LOG
        ));
    }
}
