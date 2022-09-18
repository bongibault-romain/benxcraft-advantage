package fr.benxcraft.advantage.items.eatable.cannabis;

public class CannabisLevelThree extends CannabisItem {
    @Override
    public String getIdentifier() {
        return "cannabis_level_two";
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
        return 3;
    }
}
