package com.pietroarmellini.MineIsland.commands;

import com.pietroarmellini.MineIsland.commands.subcommands.NewSubCommand;

import org.mineacademy.fo.annotation.AutoRegister;
import org.mineacademy.fo.command.ReloadCommand;
import org.mineacademy.fo.command.SimpleCommandGroup;

@AutoRegister
public final class MineIslandCommand_1 extends SimpleCommandGroup {

	@Override
	protected void registerSubcommands() {
		this.registerSubcommand(new NewSubCommand());

		this.registerSubcommand(new ReloadCommand());
	}

}
