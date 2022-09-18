package fr.benxcraft.advantage.items.eatable.cannabis;

public class CannabisLevelOne extends CannabisItem {
    @Override
    public String getIdentifier() {
        return "cannabis_level_one";
    }

    @Override
    public int getCustomModelData() {
        return 4;
    }

    @Override
    public String getDisplayName() {
        return "§cCannabis";
    }

    @Override
    public int getAddedLevels() {
        return 1;
    }
}
