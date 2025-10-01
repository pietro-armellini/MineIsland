package com.pietroarmellini.MineIsland.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mineacademy.fo.Common;
import com.pietroarmellini.MineIsland.managers.WorldManager;
import com.pietroarmellini.MineIsland.settings.GeneralSettings;
import com.pietroarmellini.MineIsland.utils.Region;
import com.pietroarmellini.MineIsland.utils.RegionMenu;

public class MineIslandCommand implements CommandExecutor {

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
				if (!WorldManager.hasRegion(player)) {
					Common.tell(player, "You don't own an island yet!");
					return true;
				}
				player.teleport(WorldManager.getRegion(player).getSpawnLocation());
				Common.tell(player, "You have been teleported to your island!");
				return true;
			} else if (args[0].equalsIgnoreCase("back")) {
				if (player.hasPermission("mineisland.command.back") == false) {
					Common.tell(player, "You don't have permission to use this command.");
					return true;
				}
				World fallbackWorld = Bukkit.getWorld(GeneralSettings.FALLBACK_WORLD); // Replace "world" with your main world
				if (fallbackWorld != null) {
					Location spawnLocation = fallbackWorld.getSpawnLocation();
					player.teleport(spawnLocation);
					Common.tell(player, "You left your island");
				} else {
					Common.tell(player,
							"You left your island but there is no fallback world setted, teleporting you to your island, contact an admin!");
				}
				return true;
			} else if (args[0].equalsIgnoreCase("new")) {
				if (player.hasPermission("mineisland.command.new") == false) {
					Common.tell(player, "You don't have permission to use this command.");
					return true;
				}
				if (WorldManager.hasRegion(player)) {
					Common.tell(player, "You already have an island!");
					return true;
				}
				// Assign region and teleport player to their spawn location
				Region region = WorldManager.createRegion(player);
				player.teleport(region.getSpawnLocation());
				Common.tell(player, "You have been teleported to your island!");
				return true;
			} else if (args[0].equalsIgnoreCase("menu")) {
				if (player.hasPermission("mineisland.command.menu") == false) {
					Common.tell(player, "You don't have permission to use this command.");
					return true;
				}
				if (!WorldManager.hasRegion(player)) {
					Common.tell(player, "You don't own an island yet!");
					return true;
				}
				new RegionMenu(WorldManager.getRegion(player)).displayTo(player);
				return true;
			} else if (args[0].equalsIgnoreCase("help")) {
				if (player.hasPermission("mineisland.command.help") == false) {
					Common.tell(player, "You don't have permission to use this command.");
					return true;
				}
				Common.tell(player, "MineIsland Commands:");
				Common.tell(player, "/mineisland new - Create a new island.");
				Common.tell(player, "/mineisland tp - Teleport to your island.");
				Common.tell(player, "/mineisland back - Teleport to the main world.");
				Common.tell(player, "/mineisland menu - Open the island management menu.");
				Common.tell(player, "/mineisland setspawn - Set the island spawn point (within owned area).");
				return true;
			} else if (args[0].equalsIgnoreCase("setspawn")) {
				if (player.hasPermission("mineisland.command.setspawn") == false) {
					Common.tell(player, "You don't have permission to use this command.");
					return true;
				}
				if (!WorldManager.hasRegion(player)) {
					Common.tell(player, "You don't own an island yet!");
					return true;
				}
				Region region = WorldManager.getRegion(player);
				if (region.isLocationInRegion(player.getLocation()) == false) {
					Common.tell(player, "You can only set the spawn point within your owned area!");
					return true;
				}
				region.setSpawnLocation(player.getLocation());
				Common.tell(player, "Island new spawn point set!");
				WorldManager.saveRegionsAsync();
				return true;
			}
		}
		// in case not of the subcommands matched
		Common.tell(player, "Unknown subcommand. Use '/mineisland help'");
		return true;

	}
}
