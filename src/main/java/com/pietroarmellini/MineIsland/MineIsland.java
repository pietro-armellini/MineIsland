package com.pietroarmellini.MineIsland;

import org.bukkit.Bukkit;
import org.mineacademy.fo.plugin.SimplePlugin;

import com.pietroarmellini.MineIsland.commands.MineIslandCommand;
import com.pietroarmellini.MineIsland.listeners.IslandsWorldListener;
import com.pietroarmellini.MineIsland.managers.WorldManager;
import com.pietroarmellini.MineIsland.settings.GeneralSettings;
import com.pietroarmellini.MineIsland.api_handlers.EconomyHandler;

public class MineIsland extends SimplePlugin {

	@Override
	public void onPluginStart() {
		EconomyHandler.setupEconomy();
		WorldManager.loadIslandsWorld();
		WorldManager.loadRegions();
		Bukkit.getPluginManager().registerEvents(new IslandsWorldListener(), this);
		getCommand("mineisland").setExecutor(new MineIslandCommand());
		System.out.println("[MineIsland] plugin enabled!");
		System.out.println("[MineIsland] Expandable Mode: " + GeneralSettings.EXPANDABLE_MODE);
	}

	@Override
	public void onPluginStop() {
		WorldManager.saveRegions();
		System.out.println("[MineIsland] plugin disabled!");
	}

	public static MineIsland getInstance() {
		return (MineIsland) SimplePlugin.getInstance();
	}
}
