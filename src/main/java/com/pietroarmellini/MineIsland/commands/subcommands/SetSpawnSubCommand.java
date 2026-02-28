package com.pietroarmellini.MineIsland.commands.subcommands;

import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleSubCommand;

import com.pietroarmellini.MineIsland.managers.WorldManager;
import com.pietroarmellini.MineIsland.settings.MyLocalization;
import com.pietroarmellini.MineIsland.utils.Region;

public class SetSpawnSubCommand extends SimpleSubCommand {

	public SetSpawnSubCommand() {
		super("setspawn");
		this.setDescription("Set the spawn location of your island");
	}

	@Override
	protected void onCommand() {
		checkConsole();
		checkPerm("mineisland.command.setspawn");
		final var player = getPlayer();

		if (!WorldManager.hasRegion(player)) {
			Common.tell(player, MyLocalization.Messages.NOT_ISLAND_OWNER);
			return;
		}
		Region region = WorldManager.getRegion(player);
		if (region.isLocationInRegion(player.getLocation()) == false) {
			Common.tell(player, MyLocalization.Messages.CANNOT_SET_SPAWN_HERE);
			return;
		}
		region.setSpawnLocation(player.getLocation());
		Common.tell(player, MyLocalization.Messages.SPAWN_SET);
		WorldManager.saveRegionsAsync();
	}
}