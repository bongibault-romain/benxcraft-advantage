package fr.benxcraft.advantage.items.tools.multipickaxe;

import fr.benxcraft.advantage.items.tools.MultiPickaxeItem;
import org.bukkit.Material;

public class MultiPickaxeLevelOne extends MultiPickaxeItem {
    @Override
    protected int getRadius() {
        return 3;
    }

    @Override
    public String getIdentifier() {
        return "multi_pickaxe_level_one";
    }

    @Override
    public Material getMaterial() {
        return Material.DIAMOND_PICKAXE;
    }

    @Override
    public int getCustomModelData() {
        return 1;
    }

    @Override
    public String getDisplayName() {
        return "§cMulti Pioche";
    }
}
