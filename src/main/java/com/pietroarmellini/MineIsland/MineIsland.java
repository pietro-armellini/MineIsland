package com.pietroarmellini.MineIsland;

import org.bukkit.Bukkit;
import org.mineacademy.fo.plugin.SimplePlugin;

import com.pietroarmellini.MineIsland.commands.MineIslandCommand;
import com.pietroarmellini.MineIsland.listeners.IslandsWorldListener;
import com.pietroarmellini.MineIsland.managers.WorldManager;
import com.pietroarmellini.MineIsland.utils.EconomyHandler;

public class MineIsland extends SimplePlugin {

	public WorldManager worldManager = new WorldManager();
	public static String prefix = "§2[MineIsland] §8";

	@Override
	public void onPluginStart() {
		System.out.println("plugin enabled!");
		EconomyHandler.setupEconomy();
		worldManager.loadIslandsWorld();
		worldManager.loadRegions();
		Bukkit.getPluginManager().registerEvents(new IslandsWorldListener(worldManager), this);
		getCommand("mineisland").setExecutor(new MineIslandCommand(worldManager));
	}

	@Override
	public void onPluginStop() {
		System.out.println("plugin disabled!");
		worldManager.saveRegions();
	}


	public static MineIsland getInstance() {
		return (MineIsland) SimplePlugin.getInstance();
	}
}
