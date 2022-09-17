package fr.benxcraft.advantage.commands;

import fr.benxcraft.advantage.items.Item;
import fr.benxcraft.advantage.managers.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

public class AGiveCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        if(!(sender instanceof Player player)) {
            sender.sendMessage("Cette commande doit être exécutée par un joueur.");
            return true;
        }

        Item givenItem = ItemManager.getInstance().getItem(args[0]);

        if(givenItem == null) {
            sender.sendMessage("Cette objet n'existe pas.");
            return true;
        }

        player.getInventory().addItem(givenItem.getItemStack());
        sender.sendMessage("L'objet a été ajouté dans votre inventaire.");

        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        return ItemManager.getInstance().getItems().stream().map(Item::getIdentifier).filter(identifier -> identifier.startsWith(args[0])).collect(Collectors.toList());
    }
}
