package com.pietroarmellini.MyRealm.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pietroarmellini.MyRealm.utils.Region;
import com.pietroarmellini.MyRealm.utils.WorldManager;

public class RealmCommand implements CommandExecutor {

    private final WorldManager worldManager;

    public RealmCommand(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }
        Player player = (Player) sender;
        // Assign region and teleport player to their spawn location
        Region region = worldManager.getRegion(player);
        player.teleport(region.getSpawnLocation(Bukkit.getWorld(worldManager.getWorldName())));
        player.sendMessage("You have been teleported to your realm!");
        return true;
    }
}
