package com.pietroarmellini.MineIsland.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import org.mineacademy.fo.Messenger;

import com.pietroarmellini.MineIsland.MineIsland;
import com.pietroarmellini.MineIsland.managers.WorldManager;
import com.pietroarmellini.MineIsland.utils.Region;
import com.pietroarmellini.MineIsland.utils.RegionMenu;

public class MineIslandCommand implements CommandExecutor {

	private final WorldManager worldManager;

	public MineIslandCommand(WorldManager worldManager) {
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
					Common.tell(player, "You don't own a region yet. Use /mineisland or /mi to get one.");
					return true;
				}
				new RegionMenu(worldManager.getRegion(player)).displayTo(player);
				return true;
			} else {
				Common.tell(player, "Unknown subcommand. Use /mineisland or /mineisland manage");
				return true;
			}
		}

		// Assign region and teleport player to their spawn location
		Region region = worldManager.getRegion(player);
		player.teleport(region.getSpawnLocation(Bukkit.getWorld(worldManager.getWorldName())));
		Common.tell(player, "You have been teleported to your island!");
		return true;
	}
}
