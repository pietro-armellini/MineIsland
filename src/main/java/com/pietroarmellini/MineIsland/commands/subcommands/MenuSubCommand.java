package com.pietroarmellini.MineIsland.commands.subcommands;

import org.mineacademy.fo.Common;
import org.mineacademy.fo.command.SimpleSubCommand;

import com.pietroarmellini.MineIsland.managers.WorldManager;
import com.pietroarmellini.MineIsland.menus.RegionMenu;
import com.pietroarmellini.MineIsland.settings.MyLocalization;

public class MenuSubCommand extends SimpleSubCommand {

	public MenuSubCommand() {
		super("menu");
		this.setDescription(MyLocalization.CommandsDescription.MENU_COMMAND);
	}

	@Override
	protected void onCommand() {
		checkConsole();
		checkPerm("mineisland.command.menu");
		final var player = getPlayer();

		if (!WorldManager.hasRegion(player)) {
			Common.tell(player, MyLocalization.Messages.NOT_ISLAND_OWNER);
			return;
		}
		new RegionMenu(WorldManager.getRegion(player)).displayTo(player);
	}
}