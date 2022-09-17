package fr.benxcraft.advantage.managers;

import fr.benxcraft.advantage.Advantage;
import fr.benxcraft.advantage.items.Item;
import fr.benxcraft.advantage.items.armors.JumaItem;
import fr.benxcraft.advantage.items.armors.RubyAmuletItem;
import fr.benxcraft.advantage.items.armors.SpeederItem;
import fr.benxcraft.advantage.items.armors.UltimateVisionItem;
import fr.benxcraft.advantage.items.eatable.CannabisItem;
import fr.benxcraft.advantage.items.eatable.CocaineItem;
import fr.benxcraft.advantage.items.eatable.MethamphetamineItem;
import fr.benxcraft.advantage.items.tools.MultiPickaxeItem;
import fr.benxcraft.advantage.items.tools.UdarItem;
import fr.benxcraft.advantage.items.tools.multipickaxe.MultiPickaxeLevelOne;
import fr.benxcraft.advantage.items.tools.multipickaxe.MultiPickaxeLevelThree;
import fr.benxcraft.advantage.items.tools.multipickaxe.MultiPickaxeLevelTwo;
import org.bukkit.Material;
import org.bukkit.event.Event;

import javax.annotation.Nullable;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemManager {

    private static final ItemManager instance = new ItemManager();

    private final List<Item> items;

    public ItemManager() {
        this.items = new LinkedList<>();
    }

    public void load() {
        this.register(new CocaineItem());
        this.register(new MethamphetamineItem());
        this.register(new JumaItem());
        this.register(new UdarItem());
        this.register(new CannabisItem());
        this.register(new SpeederItem());
        this.register(new UltimateVisionItem());
        this.register(new RubyAmuletItem());
        this.register(new MultiPickaxeLevelOne());
        this.register(new MultiPickaxeLevelTwo());
        this.register(new MultiPickaxeLevelThree());
    }

    public void unload() {
        this.items.clear();
    }

    public void reload() {
        this.unload();
        this.load();
    }

    public void register(Item item) {
        if(!this.exists(item)) {
            this.items.add(item);
            return;
        }

        Advantage.getInstance().getLogger().warning("Two custom items are registered with the same ID (" + item.getIdentifier() + ")");
    }

    public boolean exists(Item item) {
        return this.items.stream().anyMatch(registeredItem -> registeredItem.getIdentifier().equals(item.getIdentifier()));
    }

    @Nullable
    public Item getItem(@Nullable String identifier) {
        return this.items.stream()
                .filter(item -> item.getIdentifier().equals(identifier))
                .findFirst()
                .orElse(null);
    }

    public List<Item> getItemsHandlingFor(Event event) {
        return this.items.stream()
                .filter(item -> item.getHandlers().stream().anyMatch(klass -> klass.isInstance(event)))
                .collect(Collectors.toList());
    }

    public static ItemManager getInstance() {
        return ItemManager.instance;
    }

    public List<Item> getItems() {
        return items;
    }
}
