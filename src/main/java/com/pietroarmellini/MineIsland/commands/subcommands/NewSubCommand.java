package com.pietroarmellini.MineIsland.commands.subcommands;


import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleSubCommand;

import com.pietroarmellini.MineIsland.managers.WorldManager;
import com.pietroarmellini.MineIsland.settings.MyLocalization;
import com.pietroarmellini.MineIsland.utils.Region;

public class NewSubCommand extends SimpleSubCommand {

	public NewSubCommand() {
		super("new");
		this.setDescription("Create a new island");
	}

	@Override
	protected void onCommand() {
		checkConsole();
		checkPerm("mineisland.command.new");
		final var player = getPlayer();

		if (WorldManager.hasRegion(player)) {
			Common.tell(player, MyLocalization.Messages.ALREADY_OWN_ISLAND);
			return;
		}
		// Assign region and teleport player to their spawn location
		Region region = WorldManager.createRegion(player);
		player.teleport(region.getSpawnLocation());
		Common.tell(player, MyLocalization.Messages.TELEPORTED_TO_ISLAND);
	}
}