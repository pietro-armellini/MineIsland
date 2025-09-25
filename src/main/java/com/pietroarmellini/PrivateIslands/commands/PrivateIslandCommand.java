package com.pietroarmellini.PrivateIslands.commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.pietroarmellini.PrivateIslands.managers.WorldManager;
import com.pietroarmellini.PrivateIslands.utils.Region;
import com.pietroarmellini.PrivateIslands.utils.RegionMenu;

public class PrivateIslandCommand implements CommandExecutor {

	private final WorldManager worldManager;

	public PrivateIslandCommand(WorldManager worldManager) {
		this.worldManager = worldManager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("Only players can use this command.");
			return true;
		}

		Player player = (Player) sender;

		if (args.length > 0) {
			if (args[0].equalsIgnoreCase("manage")) {
				if(!worldManager.hasRegion(player)){
					player.sendMessage("[PrivateIslands] You don't own a region yet. Use /privateisland or /pi to get one.");
					return true;
				}
				new RegionMenu(worldManager.getRegion(player)).displayTo(player);
				return true;
			} else {
				player.sendMessage("Unknown subcommand. Use /privateisland or /privateisland manage");
				return true;
			}
		}

		// Assign region and teleport player to their spawn location
		Region region = worldManager.getRegion(player);
		player.teleport(region.getSpawnLocation(Bukkit.getWorld(worldManager.getWorldName())));
		player.sendMessage("You have been teleported to your island!");
		return true;
	}
}
