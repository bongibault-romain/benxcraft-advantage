package fr.benxcraft.advantage.items.eatable;

import fr.benxcraft.advantage.items.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.Collections;
import java.util.List;

public abstract class EatableItem extends Item  {

    @Override
    public boolean test(Player player, Event event) {
        if(event instanceof PlayerItemConsumeEvent) {
            return this.testConsume(player, (PlayerItemConsumeEvent) event);
        }

        return false;
    }

    protected boolean testConsume(Player player, PlayerItemConsumeEvent event) {
        return this.isSimilar((event).getItem());
    }

    @Override
    public void handle(Player player, Event event) {
        if(event instanceof PlayerItemConsumeEvent) {
            this.handleConsume(player, (PlayerItemConsumeEvent) event);
        }
    }
    protected abstract void handleConsume(Player player, PlayerItemConsumeEvent event);

    @Override
    public List<Class<?>> getHandlers() {
        return Collections.singletonList(PlayerItemConsumeEvent.class);
    }
}
