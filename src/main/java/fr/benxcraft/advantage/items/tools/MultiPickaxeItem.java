package fr.benxcraft.advantage.items.tools;

import fr.benxcraft.advantage.Advantage;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class MultiPickaxeItem extends ToolItem {

    @Override
    public List<Class<?>> getHandlers() {
        return Arrays.asList(
                BlockBreakEvent.class,
                PlayerInteractEvent.class
        );
    }

    @Override
    public void handle(Player player, Event event) {
        if(event instanceof PlayerInteractEvent) {
            this.handleInteract(player, (PlayerInteractEvent) event);
            return;
        }

        super.handle(player, event);
    }

    @Override
    public boolean test(Player player, Event event) {
        if(event instanceof PlayerInteractEvent) {
            return this.testInteract(player, (PlayerInteractEvent) event);
        }

        return super.test(player, event);
    }

    private boolean testInteract(Player player, PlayerInteractEvent event) {
        return event.getAction().equals(Action.LEFT_CLICK_BLOCK);
    }

    private void handleInteract(Player player, PlayerInteractEvent event) {
        player.setMetadata("last_left_click_interact_block_face", new FixedMetadataValue(Advantage.getInstance(), event.getBlockFace().toString()));
    }

    @Override
    public void handleBreak(Player player, BlockBreakEvent event) {
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

        ItemMeta itemMeta = itemInHand.getItemMeta();

        boolean isDamageable = itemMeta instanceof Damageable;

        int durability = isDamageable
                ? itemInHand.getType().getMaxDurability() - ((Damageable) itemMeta).getDamage()
                : 200;

        Block block = event.getBlock();

        int brokenBlocks = this.breakBlocks(player, block, Math.min(this.getTheoriesBrokenBlockAmount(), durability));

        if(isDamageable && !player.getGameMode().equals(GameMode.CREATIVE)) {
            ((Damageable) itemMeta).setDamage(((Damageable) itemMeta).getDamage() + brokenBlocks);
            itemInHand.setItemMeta(itemMeta);

            if(durability - brokenBlocks <= 0) {
                itemInHand.setAmount(0);
            }
        }
    }

    protected abstract int getRadius();

    protected int getTheoriesBrokenBlockAmount() {
        return this.getRadius() * this.getRadius();
    }

    private int breakBlocks(Player player, Block startBlock, int maxBreak) {
        Location blockLocation = startBlock.getLocation();
        World world = blockLocation.getWorld();

        int brokenBlock = 0;

        if (world == null) {
            return brokenBlock;
        }

        BlockFace face = this.getBlockFace(player);
        int[][] coordinates = getCoordinates(startBlock, face);

        for (int x = coordinates[0][0]; x <= coordinates[0][1]; x++) {
            for (int y = coordinates[1][0]; y <= coordinates[1][1]; y++) {
                for (int z = coordinates[2][0]; z <= coordinates[2][1]; z++) {
                    Block nextBlock = world.getBlockAt(x, y, z);

                    if (this.getAffectedMaterials().contains(nextBlock.getType())) {
                        nextBlock.breakNaturally();
                        brokenBlock++;
                    }

                    if(brokenBlock >= maxBreak) {
                        return brokenBlock;
                    }
                }
            }
        }

        return brokenBlock;
    }

    private BlockFace getBlockFace(Player player) {
        List<MetadataValue> faceMetadataValues = player.getMetadata("last_left_click_interact_block_face");
        MetadataValue faceMetadataValue = faceMetadataValues.stream().findFirst().orElse(null);

        if(faceMetadataValue == null) return BlockFace.NORTH;

        return BlockFace.valueOf(faceMetadataValue.asString());
    }

    private int[][] getCoordinates(Block startBlock, BlockFace face) {
        Location blockLocation = startBlock.getLocation();

        int blockX = blockLocation.getBlockX();
        int blockY = blockLocation.getBlockY();
        int blockZ = blockLocation.getBlockZ();

        int[][] coordinates = { {blockX, blockX}, {blockY, blockY}, {blockZ, blockZ} };

        if (face.equals(BlockFace.NORTH) || face.equals(BlockFace.SOUTH)) {
            coordinates[0][0] = blockX - Math.floorDiv(this.getRadius() - 1, 2);
            coordinates[0][1] = blockX + Math.ceilDiv(this.getRadius() - 1, 2);
            coordinates[1][0] = blockY - Math.floorDiv(this.getRadius() - 1, 2);
            coordinates[1][1] = blockY + Math.ceilDiv(this.getRadius() - 1, 2);
        } else if(face.equals(BlockFace.EAST) || face.equals(BlockFace.WEST)) {
            coordinates[1][0] = blockY - Math.floorDiv(this.getRadius() - 1, 2);
            coordinates[1][1] = blockY + Math.ceilDiv(this.getRadius() - 1, 2);
            coordinates[2][0] = blockZ - Math.floorDiv(this.getRadius() - 1, 2);
            coordinates[2][1] = blockZ + Math.ceilDiv(this.getRadius() - 1, 2);
        } else if(face.equals(BlockFace.UP) || face.equals(BlockFace.DOWN)) {
            coordinates[0][0] = blockX - Math.floorDiv(this.getRadius() - 1, 2);
            coordinates[0][1] = blockX + Math.ceilDiv(this.getRadius() - 1, 2);
            coordinates[2][0] = blockZ - Math.floorDiv(this.getRadius() - 1, 2);
            coordinates[2][1] = blockZ + Math.ceilDiv(this.getRadius() - 1, 2);
        }

        return coordinates;
    }

    @Override
    public List<Material> getAffectedMaterials() {
        return new LinkedList<>(List.of(
                Material.STONE
        ));
    }
}
