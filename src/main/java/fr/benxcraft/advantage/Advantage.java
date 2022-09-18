package fr.benxcraft.advantage;

import fr.benxcraft.advantage.commands.AGiveCommand;
import fr.benxcraft.advantage.listeners.ArmorListeners;
import fr.benxcraft.advantage.listeners.PlayerListeners;
import fr.benxcraft.advantage.managers.ItemManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Advantage extends JavaPlugin {

    private static Advantage instance;

    @Override
    public void onEnable() {
        Advantage.instance = this;

        ItemManager.getInstance().load();

        getServer().getPluginManager().registerEvents(new PlayerListeners(), this);
        getServer().getPluginManager().registerEvents(new ArmorListeners(), this);

        PluginCommand aGiveCommand = Objects.requireNonNull(getCommand("agive"));
        AGiveCommand command = new AGiveCommand();

        aGiveCommand.setExecutor(command);
        aGiveCommand.setTabCompleter(command);
    }

    @Override
    public void onDisable() {
        ItemManager.getInstance().unload();
    }

    public static Advantage getInstance() {
        return Advantage.instance;
    }

}
