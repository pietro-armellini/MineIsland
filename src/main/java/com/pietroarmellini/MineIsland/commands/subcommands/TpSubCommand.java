package com.pietroarmellini.MineIsland.commands.subcommands;

import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleSubCommand;

import com.pietroarmellini.MineIsland.managers.WorldManager;
import com.pietroarmellini.MineIsland.settings.MyLocalization;

public class TpSubCommand extends SimpleSubCommand {

	public TpSubCommand() {
		super("tp");
		this.setDescription("Teleport to your island");
	}

	@Override
	protected void onCommand() {
		checkConsole();
		checkPerm("mineisland.command.tp");
		final var player = getPlayer();

		if (!WorldManager.hasRegion(player)) {
			Common.tell(player, MyLocalization.Messages.NOT_ISLAND_OWNER);
			return;
		}
		player.teleport(WorldManager.getRegion(player).getSpawnLocation());
		Common.tell(player, MyLocalization.Messages.TELEPORTED_TO_ISLAND);
	}
}