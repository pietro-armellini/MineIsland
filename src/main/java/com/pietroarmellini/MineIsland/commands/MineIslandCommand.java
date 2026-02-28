package com.pietroarmellini.MineIsland.commands;

import com.pietroarmellini.MineIsland.commands.subcommands.SetSpawnSubCommand;
import com.pietroarmellini.MineIsland.commands.subcommands.TpSubCommand;
import com.pietroarmellini.MineIsland.settings.MyLocalization;
import com.pietroarmellini.MineIsland.commands.subcommands.BackSubCommand;
import com.pietroarmellini.MineIsland.commands.subcommands.MenuSubCommand;
import com.pietroarmellini.MineIsland.commands.subcommands.NewSubCommand;

import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.ReloadCommand;
import org.mineacademy.fo.command.SimpleCommandGroup;

@AutoRegister
public final class MineIslandCommand extends SimpleCommandGroup {

	@Override
	protected void registerSubcommands() {
		this.registerSubcommand(new NewSubCommand());
		this.registerSubcommand(new MenuSubCommand());
		this.registerSubcommand(new TpSubCommand());
		this.registerSubcommand(new BackSubCommand());
		this.registerSubcommand(new SetSpawnSubCommand());

		this.registerSubcommand(new ReloadCommand());
	}

	@Override
	protected String[] getHelpHeader() {
		return new String[] {
				MyLocalization.HelpMessage.HELPMESSAGE_HEADER_LINE1,
				MyLocalization.HelpMessage.HELPMESSAGE_HEADER_LINE2
		};
	}

}
