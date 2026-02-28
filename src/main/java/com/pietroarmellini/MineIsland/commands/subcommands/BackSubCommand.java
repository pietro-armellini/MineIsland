package com.pietroarmellini.MineIsland.commands.subcommands;

import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleSubCommand;

import com.pietroarmellini.MineIsland.managers.WorldManager;
import com.pietroarmellini.MineIsland.settings.MyLocalization;
import com.pietroarmellini.MineIsland.utils.Helper;

public class BackSubCommand extends SimpleSubCommand {

	public BackSubCommand() {
		super("back");
		this.setDescription("Teleport back to the world");
	}

	@Override
	protected void onCommand() {
		checkConsole();
		checkPerm("mineisland.command.back");
		final var player = getPlayer();

		if (player.getWorld().getName().equals(WorldManager.worldName) == false) {
			Common.tell(player, MyLocalization.Messages.NOT_IN_ISLAND_WORLD);
			return;
		}
		Helper.teleportPlayerToFallbackLocation(player);
		return;
	}
}