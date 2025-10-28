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
import com.pietroarmellini.MineIsland.settings.MyLocalization;
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
					Common.tell(player, MyLocalization.Messages.NO_PERMISSION);
					return true;
				}
				if (!WorldManager.hasRegion(player)) {
					Common.tell(player, MyLocalization.Messages.NOT_ISLAND_OWNER);
					return true;
				}
				player.teleport(WorldManager.getRegion(player).getSpawnLocation());
				Common.tell(player, MyLocalization.Messages.TELEPORTED_TO_ISLAND);
				return true;
			} else if (args[0].equalsIgnoreCase("back")) {
				if (player.hasPermission("mineisland.command.back") == false) {
					Common.tell(player, MyLocalization.Messages.NO_PERMISSION);
					return true;
				}
				World fallbackWorld = Bukkit.getWorld(GeneralSettings.FALLBACK_WORLD); // Replace "world" with your main world
				if (fallbackWorld != null) {
					Location spawnLocation = fallbackWorld.getSpawnLocation();
					player.teleport(spawnLocation);
					Common.tell(player, MyLocalization.Messages.ISLAND_LEFT);
				} else {
					Common.tell(player,
							MyLocalization.Messages.NO_FALLBACK_WORLD_SET);
				}
				return true;
			} else if (args[0].equalsIgnoreCase("new")) {
				if (player.hasPermission("mineisland.command.new") == false) {
					Common.tell(player, MyLocalization.Messages.NO_PERMISSION);
					return true;
				}
				if (WorldManager.hasRegion(player)) {
					Common.tell(player, MyLocalization.Messages.ALREADY_OWN_ISLAND);
					return true;
				}
				// Assign region and teleport player to their spawn location
				Region region = WorldManager.createRegion(player);
				player.teleport(region.getSpawnLocation());
				Common.tell(player, MyLocalization.Messages.TELEPORTED_TO_ISLAND);
				return true;
			} else if (args[0].equalsIgnoreCase("menu")) {
				if (player.hasPermission("mineisland.command.menu") == false) {
					Common.tell(player, MyLocalization.Messages.NO_PERMISSION);
					return true;
				}
				if (!WorldManager.hasRegion(player)) {
					Common.tell(player, MyLocalization.Messages.NOT_ISLAND_OWNER);
					return true;
				}
				new RegionMenu(WorldManager.getRegion(player)).displayTo(player);
				return true;
			} else if (args[0].equalsIgnoreCase("help")) {
				if (player.hasPermission("mineisland.command.help") == false) {
					Common.tell(player, MyLocalization.Messages.NO_PERMISSION);
					return true;
				}
				Common.tell(player, MyLocalization.HelpMessage.HELPMESSAGE_LINE1);
				Common.tell(player, MyLocalization.HelpMessage.HELPMESSAGE_LINE2);
				Common.tell(player, MyLocalization.HelpMessage.HELPMESSAGE_LINE3);
				Common.tell(player, MyLocalization.HelpMessage.HELPMESSAGE_LINE4);
				Common.tell(player, MyLocalization.HelpMessage.HELPMESSAGE_LINE5);
				Common.tell(player, MyLocalization.HelpMessage.HELPMESSAGE_LINE6);
				Common.tell(player, MyLocalization.HelpMessage.HELPMESSAGE_LINE7);
				Common.tell(player, MyLocalization.HelpMessage.HELPMESSAGE_LINE8);
				Common.tell(player, MyLocalization.HelpMessage.HELPMESSAGE_LINE9);
				Common.tell(player, MyLocalization.HelpMessage.HELPMESSAGE_LINE10);
				return true;
			} else if (args[0].equalsIgnoreCase("setspawn")) {
				if (player.hasPermission("mineisland.command.setspawn") == false) {
					Common.tell(player, MyLocalization.Messages.NO_PERMISSION);
					return true;
				}
				if (!WorldManager.hasRegion(player)) {
					Common.tell(player, MyLocalization.Messages.NOT_ISLAND_OWNER);
					return true;
				}
				Region region = WorldManager.getRegion(player);
				if (region.isLocationInRegion(player.getLocation()) == false) {
					Common.tell(player, MyLocalization.Messages.CANNOT_SET_SPAWN_HERE);
					return true;
				}
				region.setSpawnLocation(player.getLocation());
				Common.tell(player, MyLocalization.Messages.SPAWN_SET);
				WorldManager.saveRegionsAsync();
				return true;
			}
		}
		// in case not of the subcommands matched
		Common.tell(player, MyLocalization.Messages.UNKNOWN_COMMAND);
		return true;

	}
}
