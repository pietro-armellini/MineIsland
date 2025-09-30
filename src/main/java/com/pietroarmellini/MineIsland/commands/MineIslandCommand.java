package com.pietroarmellini.MineIsland.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
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
			if (args[0].equalsIgnoreCase("tp")) {
				if (player.hasPermission("mineisland.command.tp") == false) {
					Common.tell(player, "You don't have permission to use this command.");
					return true;
				}
				if (!worldManager.hasRegion(player)) {
					Common.tell(player, "You don't own an island yet!");
					return true;
				}
				player.teleport(worldManager.getRegion(player).getSpawnLocation(Bukkit.getWorld(worldManager.getWorldName())));
				Common.tell(player, "You have been teleported to your island!");
				return true;
			} else if (args[0].equalsIgnoreCase("new")) {
				if (player.hasPermission("mineisland.command.new") == false) {
					Common.tell(player, "You don't have permission to use this command.");
					return true;
				}
				if (worldManager.hasRegion(player)) {
					Common.tell(player, "You already have an island!");
					return true;
				}
				// Assign region and teleport player to their spawn location
				Region region = worldManager.createRegion(player);
				player.teleport(region.getSpawnLocation(Bukkit.getWorld(worldManager.getWorldName())));
				Common.tell(player, "You have been teleported to your island!");
				return true;
			} else if (args[0].equalsIgnoreCase("menu")) {
				if (player.hasPermission("mineisland.command.menu") == false) {
					Common.tell(player, "You don't have permission to use this command.");
					return true;
				}
				if (!worldManager.hasRegion(player)) {
					Common.tell(player, "You don't own an island yet!");
					return true;
				}
				new RegionMenu(worldManager.getRegion(player)).displayTo(player);
				return true;
			} else if (args[0].equalsIgnoreCase("help")) {
				if (player.hasPermission("mineisland.command.help") == false) {
					Common.tell(player, "You don't have permission to use this command.");
					return true;
				}
				Common.tell(player, "MineIsland Commands:");
				Common.tell(player, "/mineisland tp - Teleport to your island.");
				Common.tell(player, "/mineisland new - Create a new island.");
				Common.tell(player, "/mineisland menu - Open the island management menu.");
				return true;
			}
		}
		// in case not of the subcommands matched
		Common.tell(player, "Unknown subcommand. Use '/mineisland help'");
		return true;

	}
}
