package com.pietroarmellini.MineIsland;

import org.bukkit.Bukkit;
import org.mineacademy.fo.plugin.SimplePlugin;

import com.pietroarmellini.MineIsland.commands.MineIslandCommand;
import com.pietroarmellini.MineIsland.listeners.IslandsWorldListener;
import com.pietroarmellini.MineIsland.managers.WorldManager;

public class MineIsland extends SimplePlugin {

	public WorldManager worldManager = new WorldManager();

	@Override
	public void onPluginStart() {
		System.out.println("[MineIsland] plugin enabled!");

		worldManager.loadIslandsWorld();
		worldManager.loadRegions();
		Bukkit.getPluginManager().registerEvents(new IslandsWorldListener(worldManager), this);
		getCommand("mineisland").setExecutor(new MineIslandCommand(worldManager));
	}

	public void onPluginStop() {
		System.out.println("[MineIsland] plugin disabled!");
		worldManager.saveRegions();
	}

	public static MineIsland getInstance() {
		return (MineIsland) SimplePlugin.getInstance();
	}
}
